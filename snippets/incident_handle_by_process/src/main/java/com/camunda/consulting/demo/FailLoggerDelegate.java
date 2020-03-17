package com.camunda.consulting.demo;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;


/**
 * This is an easy adapter implementation 
 * illustrating how a Java Delegate can be used 
 * from within a BPMN 2.0 Service Task.
 */
@Component("failLoggerDelegate")
public class FailLoggerDelegate implements JavaDelegate {
 
  private final Logger LOGGER = Logger.getLogger(getClass().getName());
  
  public void execute(DelegateExecution execution) throws Exception {

    Boolean doFail = (Boolean) execution.getVariable("doFail");
    
    LOGGER.info("\n\n  ... LoggerDelegate invoked by "
            + "processDefinitionId=" + execution.getProcessDefinitionId()
            + ", activtyId=" + execution.getCurrentActivityId()
            + ", activtyName='" + execution.getCurrentActivityName() + "'"
            + ", processInstanceId=" + execution.getProcessInstanceId()
            + ", businessKey=" + execution.getProcessBusinessKey()
            + ", executionId=" + execution.getId()
            + " \n\n");

    if(doFail){
      throw new Exception("A failure for incident...");
    }
  }

}
