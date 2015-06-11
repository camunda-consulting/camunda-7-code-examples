package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;

public class DeactivateFollowUpDateAdapter implements CaseExecutionListener {
  
  private Logger LOG = Logger.getLogger(DeactivateFollowUpDateAdapter.class.getName());

  @Override
  public void notify(DelegateCaseExecution caseExecution) throws Exception {   
    try {
      // find case execution doing the humanTask AntragEntscheiden
      CaseExecution decisionCaseExecution = caseExecution.getProcessEngineServices().getCaseService().createCaseExecutionQuery() //
        .caseInstanceId(caseExecution.getCaseInstanceId())
        // TODO: make more generic
        .activityId("PI_humanTaskAntragEntscheiden")
        .singleResult();
      
      // find the according task
      Task task = caseExecution.getProcessEngineServices().getTaskService().createTaskQuery() //
        .caseExecutionId(decisionCaseExecution.getId())
        .singleResult();
      
      // Remove a follow-up date
      task.setFollowUpDate(null);
      caseExecution.getProcessEngineServices().getTaskService().saveTask(task);
    }
    catch (Exception ex) {
      // ignore exception to avoid problems because of canceled tasks or the like
      LOG.log(Level.INFO, "Could not deactivate follow-up date of decision task, maybe already ended?", ex);
    }
  }

}
