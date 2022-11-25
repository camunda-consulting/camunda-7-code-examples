package com.camunda.consulting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest
public class ContainerProcessTest {

  @Test
  void shouldCreateInstances() {

    runtimeService().startProcessInstanceByKey("createProcessInstances");
    assertThat(
            historyService()
                .createHistoricProcessInstanceQuery()
                .processDefinitionKey("dummy")
                .count())
        .isEqualTo(1000);
  }
}
