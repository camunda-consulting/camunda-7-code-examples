package org.camunda.bpm.pattern.data.test;

import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.pattern.data.ProcessDataAccessor;

public abstract class ProcessDataAccessorTestCase extends ProcessEngineTestCase {

  /**
   * register {@link JavaDelegate} mock under default CDI name (camelCase of className)
   * using proxy to do {@link ProcessDataAccessor} injection.
   */
  protected void registerNamedDelegateMock(JavaDelegate delegate, Class<? extends ProcessDataAccessor> processDataAccessorClass) {
    registerNamedDelegateMock(retrieveCdiDefaultName(delegate), delegate, processDataAccessorClass);
  }

  /**
   * register {@link JavaDelegate} mock using proxy to do {@link ProcessDataAccessor} injection.
   */
  protected void registerNamedDelegateMock(String beanName, JavaDelegate delegate, Class<? extends ProcessDataAccessor> processDataAccessorClass) {
    Mocks.register(beanName, new DelegationCodeTestProxy(delegate, processDataAccessorClass));
  }

  /**
   * register {@link ExecutionListener} mock under default CDI name (camelCase of className)
   * using proxy to do {@link ProcessDataAccessor} injection.
   */
  protected void registerNamedDelegateMock(ExecutionListener listener, Class<? extends ProcessDataAccessor> processDataAccessorClass) {
    registerNamedDelegateMock(retrieveCdiDefaultName(listener), listener, processDataAccessorClass);
  }

  /**
   * register {@link ExecutionListener} mock using proxy to do {@link ProcessDataAccessor} injection.
   */
  protected void registerNamedDelegateMock(String beanName, ExecutionListener listener, Class<? extends ProcessDataAccessor> processDataAccessorClass) {
    Mocks.register(beanName, new DelegationCodeTestProxy(listener, processDataAccessorClass));
  }
  
  protected String retrieveCdiDefaultName(Object delegate) {
    String simpleName = delegate.getClass().getSimpleName();
    // This is what CDI does with @Named
    String beanName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    return beanName;
  }

}
