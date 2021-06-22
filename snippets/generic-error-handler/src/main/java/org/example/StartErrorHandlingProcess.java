package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * This is an easy adapter implementation
 * illustrating how a Java Delegate can be used
 * from within a BPMN 2.0 Service Task.
 */
@Component("startErrorHandlerProcess")
public class StartErrorHandlingProcess implements JavaDelegate {

  
  public void execute(DelegateExecution execution) throws Exception {
    HashMap input = new HashMap();
    input.put("callingProcessInstanceId", execution.getProcessInstanceId());
    input.put("callingActivityId", execution.getVariable("errorTaskId"));


    execution.getProcessEngine().getRuntimeService().startProcessInstanceByKey("ErrorHandling",input);
  }

}
