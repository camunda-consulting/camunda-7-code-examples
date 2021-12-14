package com.camunda.consulting;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.BusinessProcessEvent;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

@Singleton
@Startup
public class TaskDataConfiguration {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Inject
  TaskDataService taskDataService;
  @Inject
  RuntimeService runtimeService;
  @Inject
  TaskService taskService;

  @PostConstruct
  public void onStartup() {
    this.rebuildTaskData();
  }

  public void onProcessEvent(@Observes BusinessProcessEvent event) {
    switch (event.getType().getTypeName()) {
    case TaskListener.EVENTNAME_CREATE:
      handleCreate(event.getTask());
      break;
    case TaskListener.EVENTNAME_ASSIGNMENT:
    case TaskListener.EVENTNAME_UPDATE:
      handleUpdate(event.getTask());
      break;
    case TaskListener.EVENTNAME_COMPLETE:
    case TaskListener.EVENTNAME_DELETE:
      handleDelete(event.getTask());
      break;
    default:
      break;
    }
  }

  @Schedule
  public void rebuildTaskDataNightly() {
    rebuildTaskData();
  }

  private void handleCreate(DelegateTask event) {
    log.info("Handling create event...");
    updateTask(event.getId(), new TaskData(), event.getProcessInstanceId(), event.getName());
    log.info("Create event handled");
  }

  private void handleUpdate(DelegateTask event) {
    log.info("Handling update event...");
    taskDataService.get(event.getId()).ifPresentOrElse(
        taskData -> updateTask(event.getId(), taskData, event.getProcessInstanceId(), event.getName()),
        this::rebuildTaskData
    );
    log.info("Update event handled");

  }

  private void handleDelete(DelegateTask event) {
    log.info("Handling delete event...");
    taskDataService.delete(event.getId()).ifPresentOrElse(taskData -> {}, this::rebuildTaskData);
    log.info("Delete event handled");
  }

  private void updateTask(String taskId, TaskData taskData, String processInstanceId, String taskName) {
    taskData.setBankCode((String) runtimeService.getVariable(processInstanceId, "bankCode"));
    taskData.setOrderNumber((String) runtimeService.getVariable(processInstanceId, "orderNumber"));
    taskData.setWorkStep(taskName);
    taskData.setSingleProcess((String) runtimeService.getVariable(processInstanceId, "singleProcess"));
    taskData.setTaskId(taskId);
    taskDataService.save(taskData);
  }

  private void rebuildTaskData() {
    log.info("Rebuilding task data...");
    taskDataService.drop();
    List<Task> tasks = taskService.createTaskQuery().active().list();
    tasks.forEach(task -> updateTask(task.getId(), new TaskData(), task.getProcessInstanceId(), task.getName()));
    log.info("Task data rebuilt with {} tasks", tasks.size());
  }

}
