package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.camunda.bpm.engine.exception.NullValueException;
import org.camunda.bpm.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.camunda.bpm.engine.variable.VariableMap;

public class OnDemandCallActivityBehavior extends CallActivityBehavior {
  
  @Override
  public void execute(ActivityExecution execution) throws Exception {
    try {
      super.execute(execution);
    } catch (NullPointerException e) {
      e.printStackTrace();
      leave(execution);
    }
  }
  
  @Override
  protected void startInstance(ActivityExecution execution, VariableMap variables, String businessKey) {
    try {
      super.startInstance(execution, variables, businessKey);
    } catch (NullValueException e) {
      e.printStackTrace();
    }
  }

}
