package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * This delegate implementation modifies the waiting instance, restarting it at the failed activity
 */
@Component
public class ModifyCallingProcess implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {
    execution.getProcessEngine().getRuntimeService()
        .createProcessInstanceModification((String) execution.getVariable("callingProcessInstanceId"))
        // cancel waiting message event in error handling event sub process
        .cancelAllForActivity("ErrorHandlingCompletedEvent")
        // restart at activity in which the error occurred
        .startBeforeActivity((String) execution.getVariable("callingActivityId"))
        .execute();
  }
}
