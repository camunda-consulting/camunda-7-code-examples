package com.camunda.consulting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStartedEvent;
import org.camunda.bpm.spring.boot.starter.event.TaskEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
public class TaskDataConfiguration {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final TaskDataService taskDataService;
  private final ObjectMapper objectMapper;
  private final RuntimeService runtimeService;
  private final TaskService taskService;

  @Autowired
  public TaskDataConfiguration(
      TaskDataService taskDataService, ObjectMapper objectMapper, RuntimeService runtimeService, TaskService taskService
  ) {
    this.taskDataService = taskDataService;
    this.objectMapper = objectMapper;
    this.runtimeService = runtimeService;
    this.taskService = taskService;
  }

  @EventListener(ProcessApplicationStartedEvent.class)
  public void onStartup() {
    int i = 0;
    while (i++ < 100) {
      runtimeService.startProcessInstanceByKey("TestProcess", Map.of(
          "bankCode",
          RandomStringUtils.randomAlphabetic(8),
          "orderNumber",
          RandomStringUtils.randomNumeric(4) + "-" + RandomStringUtils.randomNumeric(4),
          "singleProcess",
          RandomStringUtils.randomNumeric(14)
      ));
    }
    this.rebuildTaskData();
  }

  @EventListener
  public void onTaskEvent(PayloadApplicationEvent<TaskEvent> event) throws JsonProcessingException {
    log.info(
        "Task {}: \n\n{}\n",
        event.getPayload().getEventName(),
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(event.getPayload())
    );
    switch (event.getPayload().getEventName()) {
    case TaskListener.EVENTNAME_CREATE:
      handleCreate(event.getPayload());
      break;
    case TaskListener.EVENTNAME_ASSIGNMENT:
    case TaskListener.EVENTNAME_UPDATE:
      handleUpdate(event.getPayload());
      break;
    case TaskListener.EVENTNAME_COMPLETE:
    case TaskListener.EVENTNAME_DELETE:
      handleDelete(event.getPayload());
      break;
    default:
      break;
    }
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void rebuildTaskDataNightly() {
    rebuildTaskData();
  }

  private void handleCreate(TaskEvent event) {
    log.info("Handling create event...");
    updateTask(event.getId(), new TaskData(), event.getProcessInstanceId(), event.getName());
    log.info("Create event handled");
  }

  private void handleUpdate(TaskEvent event) {
    log.info("Handling update event...");
    taskDataService.get(event.getId())
        .ifPresentOrElse(
            taskData -> updateTask(event.getId(), taskData, event.getProcessInstanceId(), event.getName()),
            this::rebuildTaskData
        );
    log.info("Update event handled");

  }

  private void handleDelete(TaskEvent event) {
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
