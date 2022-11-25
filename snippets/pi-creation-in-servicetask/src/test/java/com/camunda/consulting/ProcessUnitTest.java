package com.camunda.consulting;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@ExtendWith(ProcessEngineExtension.class)
public class ProcessUnitTest {

  @BeforeEach
  public void registerDelegates() {
    Mocks.register("createProcessInstances", new CreateProcessInstancesDelegate(processEngine()));
  }

  @Test
  @Deployment(resources = {"test-pi-creation-in-delegate.bpmn"})
  public void shouldRunProcess() {
    ProcessInstance processInstance =
        runtimeService().startProcessInstanceByKey("createProcessInstances");
    assertThat(processInstance).isActive().isWaitingAt(findId("Create Process Instances"));
    assertThat(
            historyService()
                .createHistoricProcessInstanceQuery()
                .processDefinitionKey("dummy")
                .count())
        .isEqualTo(1000);
  }
}
