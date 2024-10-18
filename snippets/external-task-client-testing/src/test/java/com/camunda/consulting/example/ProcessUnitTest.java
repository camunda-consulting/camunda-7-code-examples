package com.camunda.consulting.example;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest(properties = {"camunda.bpm.client.subscriptions.exampleWork.auto-open=false","camunda.bpm.job-execution.enabled=false"})
@ActiveProfiles("unitTest")
public class ProcessUnitTest {

  @Test
  public void shouldRunProcess()  {
    ProcessInstance exampleProcess = runtimeService().startProcessInstanceByKey("ExampleProcess");
    assertThat(exampleProcess).isWaitingAt(findId("Example work"));
    complete(externalTask());
    assertThat(exampleProcess).isEnded();
  }
}
