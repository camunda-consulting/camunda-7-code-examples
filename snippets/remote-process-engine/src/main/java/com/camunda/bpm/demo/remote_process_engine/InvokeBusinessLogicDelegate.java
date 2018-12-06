package com.camunda.bpm.demo.remote_process_engine;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * This is an empty service implementation illustrating how to use a plain Java 
 * class as a BPMN 2.0 Service Task delegate.
 */
public class InvokeBusinessLogicDelegate implements JavaDelegate {
 
  private final Logger LOGGER = Logger.getLogger(InvokeBusinessLogicDelegate.class.getName());
  
  public void execute(DelegateExecution execution) throws Exception {
    
    LOGGER.info("\n\n  ... InvokeBusinessLogicDelegate invoked by "
            + "processDefinitionId=" + execution.getProcessDefinitionId()
            + ", activtyId=" + execution.getCurrentActivityId()
            + ", activtyName='" + execution.getCurrentActivityName() + "'"
            + ", processInstanceId=" + execution.getProcessInstanceId()
            + ", businessKey=" + execution.getProcessBusinessKey()
            + ", executionId=" + execution.getId()
            + " \n\n");
    
    String name = (String) execution.getVariable("name");
    
    String greeting = "Hello " + name + "!";
    LOGGER.info(greeting);
    
    execution.setVariable("greeting", greeting);
    
  }

}
