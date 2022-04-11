package com.camunda.training;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

@ExtendWith(ProcessEngineCoverageExtension.class)
public class ProcessJUnitTest {
  @Deployment(resources = "conditional-events-ordered.bpmn")
  @Test
  public void shouldRun() {
    ConditionService service = new ConditionService(runtimeService());
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("ConditionalCircusProcess");
    assertThat(processInstance).isWaitingAt(findId("Wait here until A is true"));
    service.createCondition("b","b", processInstance.getId());
    service.createCondition("c","c", processInstance.getId());
    service.createCondition("d","d", processInstance.getId());
    service.createCondition("a","a", processInstance.getId());
    service.createCondition("b","b", processInstance.getId());
    assertThat(processInstance)
        .isWaitingAt(findId(("Action for B")))
        .variables()
        .contains(entry("a", false))
        .contains(entry("b", true))
        .contains(entry("c", true))
        .contains(entry("d", true));
    complete(task());
    assertThat(processInstance)
        .isWaitingAt(findId(("Action for C")))
        .variables()
        .contains(entry("a", false))
        .contains(entry("b", true))
        .contains(entry("c", false))
        .contains(entry("d", true));
    complete(task());
    assertThat(processInstance)
        .isWaitingAt(findId(("Action for D")))
        .variables()
        .contains(entry("a", false))
        .contains(entry("b", true))
        .contains(entry("c", false))
        .contains(entry("d", false));
    complete(task());
    assertThat(processInstance)
        .isWaitingAt(findId(("Action for B")))
        .variables()
        .contains(entry("a", false))
        .contains(entry("b", false))
        .contains(entry("c", false))
        .contains(entry("d", false));
    complete(task());
    assertThat(processInstance).isEnded();

  }
  @Deployment(resources = "conditional-events-ordered.bpmn")
  @Test
  public void shouldCreateHistoricVariableInstances(){
    ConditionService service = new ConditionService(runtimeService());
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("ConditionalCircusProcess");
    service.createCondition("a","a",processInstance.getId());
    service.createCondition("a","a",processInstance.getId());
    service.createCondition("a","a",processInstance.getId());
    service.createCondition("a","a",processInstance.getId());
    service.createCondition("a","a",processInstance.getId());
    historyService().createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("a").includeDeleted().list().forEach(System.out::println);

  }

  private CreateConditionEvent mockEvent(String eventType) {
    CreateConditionEvent event = new CreateConditionEvent();
    event.eventType = eventType;
    event.eventState = CreateConditionEvent.EventState.INITIAL;
    try {
      Thread.sleep(10L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    event.creationTimestamp = new Date();
    event.body = eventType;
    return event;
  }

}
