package com.camunda.fox.demo.outerspace.errorhandling;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named
public class AccountSetupDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String accounttype = (String) execution.getVariable("accounttype");
    String errorMessage = "This customer already applied for a " + accounttype + " account.";
    
    // a normal exception is thrown up
    //throw new Exception(errorMessage);
    // a BpmnError is caught by the engine and triggers a BPMN Error Event 
    throw new BpmnError("duplicateApplication", errorMessage);
  }

}
