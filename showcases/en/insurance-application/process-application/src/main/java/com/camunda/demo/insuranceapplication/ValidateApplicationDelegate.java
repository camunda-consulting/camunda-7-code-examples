package com.camunda.demo.insuranceapplication;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ValidateApplicationDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    execution.setVariable("isrisk", true);
  }

}
