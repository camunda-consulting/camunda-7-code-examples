package com.camunda.demo.versicherungsneuantrag.adapter;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;

public class DeactivateFollowUpDateAdapter implements CaseExecutionListener {

  @Override
  public void notify(DelegateCaseExecution caseExecution) throws Exception {   
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

}
