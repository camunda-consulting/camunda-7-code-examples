package com.camunda.consulting;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;

import java.util.List;

import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProcessEngineExtension.class)
@Deployment(resources = "conditional_boundary_event.bpmn")
public class ConditionalBoundaryEventTests {

  private static final Job jobWithoutProcessInstanceId() {
    return jobQuery().list().stream().filter(job -> job.getProcessInstanceId() == null).findFirst().get();
  }

  private static final void executeSetVariableJob() {
    execute(jobWithoutProcessInstanceId()); // batch-seed-job
    execute(jobWithoutProcessInstanceId()); // set-variables
    execute(jobWithoutProcessInstanceId()); // batch-monitor-job
  }

  @Test
  public void shouldNotTriggerTwice() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("ConditionalBoundaryEventProcess");
    assertThat(processInstance).isWaitingAt(findId("Auf Antwort warten"));
    runtimeService().setVariablesAsync(List.of(processInstance.getId()), withVariables("test", "test"));
    executeSetVariableJob();
    assertThat(externalTaskQuery().list()).hasSize(1);
    complete(externalTask());
    runtimeService().setVariablesAsync(List.of(processInstance.getId()), withVariables("test", "test123"));
    executeSetVariableJob();
    assertThat(externalTaskQuery().list()).isEmpty();
    runtimeService().correlateMessage("stop-the-wait");
    assertThat(processInstance).isEnded().variables().contains(entry("test", "test123"));
  }
}
