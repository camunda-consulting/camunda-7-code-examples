package org.camunda.consulting.patterns.data;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.consulting.patterns.data.adapter.AbstractProcessVariableAdapter;
import org.camunda.consulting.patterns.data.adapter.BusinessProcessVariableAdapter;
import org.camunda.consulting.patterns.data.adapter.MapProcessVariableAdapter;
import org.camunda.consulting.patterns.data.adapter.VariableScopeProcessVariableAdapter;

public class ProcessDataAccessor  {
  
  private final AbstractProcessVariableAdapter accessor;

  /**
   * Create new instance wrapping {@link PayloadAdapter}.
   * 
   * @param payloadAdapter
   *          adapter wrapping concrete variables implementation
   */
  public ProcessDataAccessor(final AbstractProcessVariableAdapter accessor) {
    this.accessor = accessor;
  }

  /**
   * Initialize with CDI {@link BusinessProcess} (scoped).
   */
  @Inject
  public ProcessDataAccessor(final BusinessProcess businessProcess) {
    this(new BusinessProcessVariableAdapter(businessProcess));
  }

  /**
   * Initialize with DelegateTask, DelegateExecution (or any other
   * VariableScope).
   * 
   * @param variableScope
   *          - any delegate instance implementing VariableScope
   */
  public ProcessDataAccessor(final VariableScope variableScope) {
    this(new VariableScopeProcessVariableAdapter(variableScope));
  }

  /**
   * Initialize with arbitrary map.
   * 
   * @param variables
   *          - map
   */
  public ProcessDataAccessor(final Map<String, Object> variables) {
    this(new MapProcessVariableAdapter(variables));
  }

  public <T extends Serializable> T getVariable(final String name) {
    return accessor.getVariable(name);
  }

  public <T extends Serializable> void setVariable(final String name, final T value) {
    accessor.setVariable(name, value);
  }
  
  public Map<String, Object> getVariablesMap() {
    return accessor.getVariablesMap();
  }

}
