package com.camunda.demo.call_activity_set_local_variable;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

public class SetLocalVariableExecutionListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    String variableName = "childProcessVariable";
    
    // obtain the value of the global variable set by the Call Activity
    Object variableValue = execution.getVariable(variableName);
    // delete the global variable before it gets written to the database
    execution.removeVariable(variableName);
    // set a local variable in the correct scope
    ((ExecutionEntity) execution).getParent().setVariableLocal(variableName, variableValue);
  }

}
