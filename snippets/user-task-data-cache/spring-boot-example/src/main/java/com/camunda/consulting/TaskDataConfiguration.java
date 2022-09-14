package com.camunda.consulting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.camunda.bpm.engine.ProcessEngineException;
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

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class TaskDataConfiguration {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final TaskDataService taskDataService;
  private final RuntimeService runtimeService;
  private final TaskService taskService;
  private final ThreadPoolExecutor executor =
      new ThreadPoolExecutor(1, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));

  @Autowired
  public TaskDataConfiguration(
      TaskDataService taskDataService,
      RuntimeService runtimeService,
      TaskService taskService) {
    this.taskDataService = taskDataService;
    this.runtimeService = runtimeService;
    this.taskService = taskService;
  }

  @EventListener(ProcessApplicationStartedEvent.class)
  public void onStartup() {
    int i = 0;
    while (i++ < 10) {
      runtimeService.startProcessInstanceByKey(
          "TestProcess",
          Map.of(
              "bankCode",
              RandomStringUtils.randomAlphabetic(8),
              "orderNumber",
              RandomStringUtils.randomNumeric(4) + "-" + RandomStringUtils.randomNumeric(4),
              "singleProcess",
              RandomStringUtils.randomNumeric(14)));
    }
  }

  @EventListener
  public void onTaskEvent(PayloadApplicationEvent<TaskEvent> event) {
    executor.execute(
        () -> {
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
          verifyIntegrity();
        });
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void rebuildTaskDataNightly() {
    executor.execute(this::rebuildTaskData);
  }

  private void handleCreate(TaskEvent event) {
    log.info("Handling create event...");
    TaskData taskData = createTaskData(event.getId(), event.getName());
    taskDataService.save(taskData);
    log.info("Create event handled");
  }

  private void handleUpdate(TaskEvent event) {
    log.info("Handling update event...");
    taskDataService
        .get(event.getId())
        .map(taskData -> updateTaskData(taskData, event.getName(), 10, 1000L))
        .ifPresentOrElse(taskDataService::save, this::rebuildTaskData);
    log.info("Update event handled");
  }

  private void verifyIntegrity() {
    for (Task task : taskService.createTaskQuery().active().list()) {
      Optional<TaskData> taskData = taskDataService.get(task.getId());
      if (taskData.isPresent()) {
        taskDataService.save(updateTaskData(taskData.get(), task.getName(), 10, 1000L));
      } else {
        rebuildTaskData();
        return;
      }
    }
    for (TaskData taskData : taskDataService.stream().toList()) {
      Optional<Task> task =
          Optional.ofNullable(
              taskService.createTaskQuery().taskId(taskData.getTaskId()).active().singleResult());
      if (task.isPresent()) {
        taskDataService.save(updateTaskData(taskData, task.get().getName(), 10, 1000L));
      } else {
        rebuildTaskData();
        return;
      }
    }
  }

  private void handleDelete(TaskEvent event) {
    log.info("Handling delete event...");
    taskDataService.delete(event.getId()).ifPresentOrElse(taskData -> {}, this::rebuildTaskData);
    log.info("Delete event handled");
  }

  private TaskData createTaskData(String taskId, String taskName) {
    TaskData taskData = new TaskData();
    taskData.setTaskId(taskId);
    return updateTaskData(taskData, taskName, 10, 1000L);
  }

  private TaskData updateTaskData(TaskData taskData, String taskName, int retries, long timeout) {
    try {
      taskData.setBankCode((String) taskService.getVariable(taskData.getTaskId(), "bankCode"));
      taskData.setOrderNumber(
          (String) taskService.getVariable(taskData.getTaskId(), "orderNumber"));
      taskData.setWorkStep(taskName);
      taskData.setSingleProcess(
          (String) taskService.getVariable(taskData.getTaskId(), "singleProcess"));
      return taskData;
    } catch (ProcessEngineException e) {
      if (retries == 1) {
        throw e;
      }
      try {
        Thread.sleep(timeout);
      } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
      }
      return updateTaskData(taskData, taskName, retries - 1, timeout);
    }
  }

  private void rebuildTaskData() {
    log.info("Rebuilding task data...");
    taskDataService.drop();
    log.info(
        "Task data rebuilt with {} tasks",
        taskService.createTaskQuery().active().list().stream()
            .map(task -> createTaskData(task.getId(), task.getName()))
            .collect(Collectors.collectingAndThen(Collectors.toList(), taskDataService::saveAll))
            .size());
  }
}
