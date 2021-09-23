package com.camunda.consulting;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ProcessEngineExtension.class)
@Deployment(resources = "conditional-event.bpmn")
public class ConditionalEventTests {

  @Test
  public void shouldWaitAtAsyncBeforeFlag() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("ConditionalEventsProcess");
    assertThat(processInstance).isWaitingAt(findId("Warte auf Variable"));
    // Variable is set when process is waiting at async before position
    runtimeService().setVariable(processInstance.getId(), "test", "test");
    assertThat(processInstance).isWaitingAt(findId("Warte auf Variable"));
    // this jobs evaluates the condition and moves the token directly further
    execute(job());
    assertThat(processInstance).isEnded();
  }

  @Test
  public void shouldNotWaitAfterAsnycBeforeFlag() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("ConditionalEventsProcess");
    assertThat(processInstance).isWaitingAt(findId("Warte auf Variable"));
    // we move over the async before flag
    execute(job());
    // this setting variable ends the process
    runtimeService().setVariable(processInstance.getId(), "test", "test");
    assertThat(processInstance).isEnded();
  }
}
