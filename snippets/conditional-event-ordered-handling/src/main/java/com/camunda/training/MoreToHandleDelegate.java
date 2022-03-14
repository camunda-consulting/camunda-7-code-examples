package com.camunda.training;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class MoreToHandleDelegate implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    boolean more = EventVariableUtil.forVariables(execution.getVariables()).hasMoreEvents();
    execution.setVariable("more",more);
  }
}
