package org.camunda.bpm.demo.group_matrix;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.variable.impl.value.PrimitiveTypeValueImpl.StringValueImpl;

public class BranchVariable extends StringValueImpl {
  
  private static final long serialVersionUID = 1L;
  
  public static final String VARIABLE_NAME = "branch";

  private DelegateExecution execution;
  
  public BranchVariable(String value) {
    super(value);
  }

  public BranchVariable(DelegateExecution execution) {
    super((String) execution.getVariable(VARIABLE_NAME));
    this.execution = execution;
  }

  public BranchVariable() {
    super(null);
  }

  public void addTo(Map<String, Object> variables) {
    variables.put(VARIABLE_NAME, value);
  }

  public void setValue(String value) {
    this.value = value;
    if (execution != null) {
      execution.setVariable(VARIABLE_NAME, value);
    }
  }

  public String getVariable(DelegateExecution execution) {
    this.execution = execution;
    value = (String) execution.getVariable(VARIABLE_NAME);
    return value;
  }

}
