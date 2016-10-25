package com.camunda.demo.failedjobhandler;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FailureAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    
    if (execution.hasVariable("fail")) {
      throw new RuntimeException((String)execution.getVariable("fail"));
    }

  }

}
