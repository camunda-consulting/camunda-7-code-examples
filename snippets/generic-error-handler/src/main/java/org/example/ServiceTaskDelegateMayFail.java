package org.example;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.logging.Logger;

/**
 * This delegate logs values of interest and randomly causes a BPMNError
 */
@Slf4j
@Component
public class ServiceTaskDelegateMayFail implements JavaDelegate {

  public void execute(DelegateExecution execution) throws Exception {

    log.info("\n\n  ... LoggerDelegate invoked by "
        + "activtyName='" + execution.getCurrentActivityName() + "'"
        + ", activtyId=" + execution.getCurrentActivityId()
        + ", processDefinitionId=" + execution.getProcessDefinitionId()
        + ", processInstanceId=" + execution.getProcessInstanceId()
        + ", businessKey=" + execution.getProcessBusinessKey()
        + ", executionId=" + execution.getId()
        + ", variables=" + execution.getVariables()
        + " \n");

    // fail if data is set accordingly
    if (execution.getVariable("failingTask").equals(execution.getCurrentActivityId())) {
      throw new BpmnError("GenericError", execution.getCurrentActivityId());
    }
  }
}
