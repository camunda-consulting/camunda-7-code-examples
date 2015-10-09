package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.task.Task;

public class DeactivateFollowUpDateListener implements ExecutionListener {
  
  private Logger LOG = Logger.getLogger(DeactivateFollowUpDateListener.class.getName());

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    try {
      // find the according task
      Task task = execution.getProcessEngineServices().getTaskService().createTaskQuery() //
        .processInstanceId(execution.getProcessInstanceId())
        .taskDefinitionKey("userTaskAntragEntscheiden") // TODO: Think about it
        .singleResult();
      
      // Remove a follow-up date
      task.setFollowUpDate(null);
      execution.getProcessEngineServices().getTaskService().saveTask(task);
    }
    catch (Exception ex) {
      // ignore exception to avoid problems because of canceled tasks or the like
      LOG.log(Level.INFO, "Could not deactivate follow-up date of decision task, maybe already ended?", ex);
    }
  }


}
