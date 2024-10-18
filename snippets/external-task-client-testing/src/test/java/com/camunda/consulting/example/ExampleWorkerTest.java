package com.camunda.consulting.example;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = ExampleWorker.class)
public class ExampleWorkerTest {
  @Autowired
  ExampleWorker exampleWorker;

  @Test
  void shouldInvokeExampleWorker() {
    // given
    ExternalTask externalTask = mock(ExternalTask.class);
    ExternalTaskService externalTaskService = mock(ExternalTaskService.class);
    // when
    exampleWorker.execute(externalTask, externalTaskService);
    // then
    verify(externalTaskService, times(1)).complete(externalTask);
  }
}
