package com.camunda.consulting;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

@SpringBootTest
public class ApplicationTest {

  @Autowired
  RuntimeService runtimeService;

  @Autowired
  TaskService taskService;

  @Autowired
  TaskDataService taskDataService;

  @Test
  public void testSingularityOfTaskDataServiceEntry() {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("TestProcess");
    Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    assertThat(taskDataService.get(task.getId())).isPresent();
    taskService.setAssignee(taskService.createTaskQuery()
        .processInstanceId(processInstance.getId())
        .singleResult()
        .getId(), "demo");
    assertThat(taskDataService.get(task.getId())).isPresent();
    assertThat(taskDataService.stream()
        .filter(taskData -> taskData.getTaskId().equals(task.getId()))
        .collect(Collectors.toSet())).hasSize(1);
    taskService.complete(taskService.createTaskQuery()
        .processInstanceId(processInstance.getId())
        .singleResult()
        .getId());
    assertThat(taskDataService.get(task.getId())).isEmpty();
    assertThat(taskDataService.stream()
        .filter(taskData -> taskData.getTaskId().equals(task.getId()))
        .collect(Collectors.toSet())).isEmpty();
    assertThat(processInstance).isEnded();
  }
}
