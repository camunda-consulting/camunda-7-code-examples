package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * This is an easy adapter implementation
 * illustrating how a Java Delegate can be used
 * from within a BPMN 2.0 Service Task.
 */
@Component("modifyCallingProcess")
public class ModifyCallingProcess implements JavaDelegate {

  
  public void execute(DelegateExecution execution) throws Exception {
    execution.getProcessEngine().getRuntimeService().createProcessInstanceModification((String) execution.getVariable("callingProcessInstanceId"))
            .startAfterActivity((String) execution.getVariable("callingActivityId"))
            .execute();
  }

}
