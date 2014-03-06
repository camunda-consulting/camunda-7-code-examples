package com.camunda.demo.adhoc;

import java.util.Collections;
import java.util.List;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.HistoryServiceImpl;
import org.camunda.bpm.engine.impl.TaskServiceImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutorImpl;
import org.camunda.bpm.engine.task.Task;

public class EnforceAdHocRukesTaskListener implements TaskListener {
  
  /**
   * avoid recursion when completing tasks from the inside
   */
  private static ThreadLocal<Boolean> running = new ThreadLocal<Boolean>(); 

  @Override
  public void notify(DelegateTask delegateTask) {
    if (running.get() != null && running.get()==true) {
      return;
    }
    running.set(Boolean.TRUE);
    
    TaskServiceImpl taskService = new TaskServiceImpl();
    taskService.setCommandExecutor(new CommandExecutorImpl());
    HistoryServiceImpl historyService = new HistoryServiceImpl();
    historyService.setCommandExecutor(new CommandExecutorImpl());
    
    List<Task> tasks = taskService.createTaskQuery().processInstanceId(delegateTask.getProcessInstanceId()).list();   
    List<HistoricTaskInstance> historicTasks = historyService.createHistoricTaskInstanceQuery().processInstanceId(delegateTask.getProcessInstanceId()).finished().list();

    // now we have everything we need to evaluate some rules. Really simple in this case:
    boolean finishedA = false;
    boolean finishedB = false;
    
    if (delegateTask.getTaskDefinitionKey().equals("UserTaskA")) {
      finishedA = true;
    }
    if (delegateTask.getTaskDefinitionKey().equals("UserTaskB")) {
      finishedB = true;
    }
    
    for (HistoricTaskInstance historicTaskInstance : historicTasks) {
     if (historicTaskInstance.getTaskDefinitionKey().equals("UserTaskA")) {
       finishedA = true;
     }
     if (historicTaskInstance.getTaskDefinitionKey().equals("UserTaskB")) {
       finishedB = true;
     }
    }

    if (finishedA && finishedB) {
      finishRunningTasks(taskService, tasks, delegateTask);
    }
    
    running.set(Boolean.FALSE);    
  }

  private void finishRunningTasks(TaskService taskService, List<Task> tasks, DelegateTask currentTask) {
    for (Task task : tasks) {
      if (!task.getId().equals(currentTask.getId())) { // skip current task which is still running in the current query
        taskService.complete(task.getId(), Collections.<String, Object>singletonMap("AD_HOC_SKIPPED", Boolean.TRUE));
      }
    }    
  }

}
