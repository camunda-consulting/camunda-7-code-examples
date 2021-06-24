package org.example;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.logging.Logger;

/**
 * This delegate logs values of interest and randomly causes a BPMNError
 */
@Component("logger")
public class LoggerDelegate implements JavaDelegate {

  private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

  public void execute(DelegateExecution execution) throws Exception {

    LOGGER.info("\n\n  ... LoggerDelegate invoked by "
        + "activtyName='" + execution.getCurrentActivityName() + "'"
        + ", activtyId=" + execution.getCurrentActivityId()
        + ", processDefinitionId=" + execution.getProcessDefinitionId()
        + ", processInstanceId=" + execution.getProcessInstanceId()
        + ", businessKey=" + execution.getProcessBusinessKey()
        + ", executionId=" + execution.getId()
        + ", variables=" + execution.getVariables()
        + " \n\n");

    // Flip a coin to determine if error should be thrown
    if (new Random().nextInt(2) == 0) {
      throw new BpmnError("GenericError", execution.getCurrentActivityId());
    }
  }
}
