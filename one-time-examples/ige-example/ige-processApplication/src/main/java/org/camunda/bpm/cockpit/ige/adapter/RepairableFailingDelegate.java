package org.camunda.bpm.cockpit.ige.adapter;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * This is an simple service implementation throwing a ProcessEngineException  
 * unless a processVariable 'REPAIRED' equals 'true'
 */
public class RepairableFailingDelegate implements JavaDelegate {
 
  public static final String REPAIRED = "REPAIRED";
  private final Logger LOGGER = Logger.getLogger(RepairableFailingDelegate.class.getName());
  
  public void execute(DelegateExecution execution) throws Exception {
    
    LOGGER.info("\n\n  ... " + RepairableFailingDelegate.class.getSimpleName()
            + " with " + REPAIRED + " = " + execution.getVariable(REPAIRED)
            + " invoked by "
            + "processDefinitionId=" + execution.getProcessDefinitionId()
            + ", activtyId=" + execution.getCurrentActivityId()
            + ", activtyName='" + execution.getCurrentActivityName() + "'"
            + ", processInstanceId=" + execution.getProcessInstanceId()
            + ", businessKey=" + execution.getProcessBusinessKey()
            + ", executionId=" + execution.getId());
    if ("true".equals(execution.getVariable(REPAIRED))) {
      return;
    } else {
      throw new ProcessEngineException("create an incident!");
    }
  }

}
