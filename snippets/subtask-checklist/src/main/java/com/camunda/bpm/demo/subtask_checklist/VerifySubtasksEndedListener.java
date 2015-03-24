package com.camunda.bpm.demo.subtask_checklist;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class VerifySubtasksEndedListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    long unfinishedSubTasks = delegateTask.getProcessEngineServices()
      .getHistoryService().createHistoricTaskInstanceQuery()
      .taskParentTaskId(delegateTask.getId())
      .unfinished().count();
    
    if (unfinishedSubTasks>0) {
      throw new RuntimeException("Cannot end task, found " + unfinishedSubTasks + " unfinished subtasks.");
    }

  }

}
