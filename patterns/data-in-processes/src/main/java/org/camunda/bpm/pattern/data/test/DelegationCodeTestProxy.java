package org.camunda.bpm.pattern.data.test;

import java.lang.reflect.Field;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.bpm.pattern.data.ProcessDataAccessor;

/**
 * Proxy to call delegation code (e.g. {@link JavaDelegate},
 * {@link ExecutionListener}) from Test case. Injects
 * {@link ProcessDataAccessor} into the delegation code before calling it.
 */
public class DelegationCodeTestProxy implements JavaDelegate, ExecutionListener {

  private JavaDelegate realDelegate;
  private ExecutionListener realExecutionListener;
  private Class<? extends ProcessDataAccessor> processDataAccessorClass;

  public DelegationCodeTestProxy(JavaDelegate realDelegate, Class<? extends ProcessDataAccessor> processDataAccessorClass) {
    this.realDelegate = realDelegate;
    this.processDataAccessorClass = processDataAccessorClass;
  }

  public DelegationCodeTestProxy(ExecutionListener executionListener, Class<? extends ProcessDataAccessor> processDataAccessorClass) {
    this.realExecutionListener = executionListener;
    this.processDataAccessorClass = processDataAccessorClass;
  }

  public void execute(DelegateExecution execution) throws Exception {
    ProcessDataAccessor processData = createProcessDataAccessor(execution);
    injectVariables(realDelegate, processData);
    realDelegate.execute(execution);
  }

  public void notify(DelegateExecution execution) throws Exception {
    ProcessDataAccessor processData = createProcessDataAccessor(execution);
    injectVariables(realExecutionListener, processData);
    realExecutionListener.notify(execution);
  }

  private ProcessDataAccessor createProcessDataAccessor(DelegateExecution execution) {
    try {
        return processDataAccessorClass.getDeclaredConstructor( VariableScope.class ).newInstance(execution);
    } catch (Exception e) {
      throw new ProcessEngineException("Cannot create new instance of " + processDataAccessorClass + ". Maybe constructor with VariableScope is missing?", e);
    }
  }

  /**
   * @param variables
   */
  private void injectVariables(Object delegate, ProcessDataAccessor processData) {
    try {
      Field[] declaredFields = delegate.getClass().getDeclaredFields();
      for (Field field : declaredFields) {
        if (field.getType().isAssignableFrom(processDataAccessorClass) 
            && field.isAnnotationPresent(Inject.class)) {
          
          // This is a field waiting for the data injected
          field.setAccessible(true);
          field.set(delegate, processData);
          field.setAccessible(false);
          
        }
        // Do you need more injections?
      }
    } catch (Exception ex) {
      throw new ProcessEngineException("Could not inject " + processData.getClass() + " into " + delegate.getClass() + ": " + ex.getMessage(), ex);
    }
    
  }

}
