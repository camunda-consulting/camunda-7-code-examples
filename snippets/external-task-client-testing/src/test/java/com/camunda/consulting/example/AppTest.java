package com.camunda.consulting.example;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.community.process_test_coverage.core.engine.ExcludeFromProcessCoverage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import java.time.Duration;

import static org.awaitility.Awaitility.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

/**
 * Here, the interaction between the (remote) engine and running workers is tested
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AppTest {

  @Autowired
  RuntimeService runtimeService;

  @Test
  public void shouldRun() {
    ProcessInstance exampleProcess = runtimeService.startProcessInstanceByKey("ExampleProcess");
    await()
        .timeout(Duration.ofSeconds(60))
        .untilAsserted(() -> assertThat(exampleProcess).isEnded());
  }
}
