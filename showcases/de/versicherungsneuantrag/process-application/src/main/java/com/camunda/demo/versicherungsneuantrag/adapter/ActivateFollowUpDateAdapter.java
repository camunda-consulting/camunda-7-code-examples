package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.Calendar;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;

public class ActivateFollowUpDateAdapter implements CaseExecutionListener {

  @Override
  public void notify(DelegateCaseExecution caseExecution) throws Exception {
    // Calculate follow-up date
    Calendar followUpCal = Calendar.getInstance();
    followUpCal.add(Calendar.DAY_OF_YEAR, 7); // TODO: Make configurable
    
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
    
    // Set a follow-up date
    task.setFollowUpDate(followUpCal.getTime());
    caseExecution.getProcessEngineServices().getTaskService().saveTask(task);
  }

}
