package org.example;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Before;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.processEngine;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.processInstanceQuery;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends AbstractProcessEngineRuleTest {

  @Before
  public void setUp() throws Exception {
    Mocks.register("serviceTaskDelegateMayFail", new ServiceTaskDelegateMayFail());
    Mocks.register("modifyCallingProcess", new ModifyCallingProcess());
    Mocks.register("startErrorHandlingProcess", new StartErrorHandlingProcess());
  }

  @Test
  @Deployment(resources = {"GenericErrorHandlerProcess.bpmn","ErrorHandlingProcess.bpmn"})
  public void testHappyPath() {

    // Start process and let second task produce BPMNError
    var pi = processEngine().getRuntimeService()
        .startProcessInstanceByKey("GenericErrorHandlerProcess",
            "businessKey1",
            withVariables("failingTask", "ServiceTask2Task"));
    // Ensure event-based sub process has been triggered and started the handling process
    assertThat(pi).hasPassed("ServiceTask1Task","ErrorHandlingInitiatedEvent");

    //find user task in handling process (other process instance) and complete it
    var task = taskQuery().taskDefinitionKey("FixItTask").singleResult();
    taskService().complete(task.getId());

    // after completing the user task the error handling process should have modified the
    // process instance started initially and it should have continued and completed
    assertThat(pi).hasPassed("ServiceTask2Task","ServiceTask3Task")
        .isEnded();
  }
}
