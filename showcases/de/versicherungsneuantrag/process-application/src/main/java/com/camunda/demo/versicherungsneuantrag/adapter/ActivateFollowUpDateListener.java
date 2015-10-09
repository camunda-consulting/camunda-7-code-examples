package com.camunda.demo.versicherungsneuantrag.adapter;

import java.util.Calendar;

import org.camunda.bpm.engine.delegate.CaseExecutionListener;
import org.camunda.bpm.engine.delegate.DelegateCaseExecution;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.task.Task;

public class ActivateFollowUpDateListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    // Calculate follow-up date
    Calendar followUpCal = Calendar.getInstance();
    followUpCal.add(Calendar.DAY_OF_YEAR, 7); // TODO: Make configurable
        
    // find the according task
    Task task = execution.getProcessEngineServices().getTaskService().createTaskQuery() //
      .processInstanceId(execution.getProcessInstanceId())
      .taskDefinitionKey("userTaskAntragEntscheiden") // TODO: Think about it
      .singleResult();
    
    // Set a follow-up date
    task.setFollowUpDate(followUpCal.getTime());
    execution.getProcessEngineServices().getTaskService().saveTask(task);
  }

 

}
