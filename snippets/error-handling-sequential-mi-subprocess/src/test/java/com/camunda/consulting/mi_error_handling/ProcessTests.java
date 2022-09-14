package com.camunda.consulting.mi_error_handling;

import com.camunda.consulting.mi_error_handling.delegates.CallSomeServiceDelegate;
import com.camunda.consulting.mi_error_handling.delegates.FlagEntryDelegate;
import com.camunda.consulting.mi_error_handling.delegates.GenerateListDelegate;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.job;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(ProcessEngineExtension.class)
@Deployment(resources = {"example_process_boundary_event.bpmn"})
public class ProcessTests {
  ProcessEngine processEngine;
  @BeforeAll
  public static void setUpMocks() {
    Mocks.register("CallSomeService", new CallSomeServiceDelegate());
    Mocks.register("FlagEntry", new FlagEntryDelegate());
    Mocks.register("GenerateList", new GenerateListDelegate());
  }

  @Test
  public void testProcessWithoutErrorAndFailureCompletes() {
    RuntimeService runtimeService = processEngine.getRuntimeService();
    ProcessInstance pi = runtimeService.startProcessInstanceByKey("ErrorHandlingInMISubprocesses");

    assertThat(pi).isWaitingAt("EnterFailureIndex");
    complete(task(), withVariables(
        GenerateListDelegate.NUMBER_OF_ITEMS_VARIABLE_NAME, 5,
        GenerateListDelegate.FAILURE_INDEX_VARIABLE_NAME, Integer.MAX_VALUE,
        CallSomeServiceDelegate.BUSINESS_ERROR_INDEX_NAME, Integer.MAX_VALUE));

    IntStream.range(0, 5).forEach(i -> execute(job()));
    assertThat(pi).isEnded().hasNotPassed("FlagDatabaseEntry");
  }

  @Test
  public void testProcessWithBpmnErrorAndWithoutFailureCompletes() {
    RuntimeService runtimeService = processEngine.getRuntimeService();
    ProcessInstance pi = runtimeService.startProcessInstanceByKey("ErrorHandlingInMISubprocesses");

    assertThat(pi).isWaitingAt("EnterFailureIndex");
    complete(task(), withVariables(
        GenerateListDelegate.NUMBER_OF_ITEMS_VARIABLE_NAME, 5,
        GenerateListDelegate.FAILURE_INDEX_VARIABLE_NAME, Integer.MAX_VALUE,
        CallSomeServiceDelegate.BUSINESS_ERROR_INDEX_NAME, 2));

    IntStream.range(0, 5).forEach(i -> execute(job()));
    assertThat(pi).isEnded().hasPassed("FlagDatabaseEntry");
  }

  @Test
  public void testProcessWithoutBpmnErrorAndWithFailureDoesNotComplete() {
    RuntimeService runtimeService = processEngine.getRuntimeService();
    ProcessInstance pi = runtimeService.startProcessInstanceByKey("ErrorHandlingInMISubprocesses");

    assertThat(pi).isWaitingAt("EnterFailureIndex");

    complete(task(pi), withVariables(
        GenerateListDelegate.NUMBER_OF_ITEMS_VARIABLE_NAME, 5,
        GenerateListDelegate.FAILURE_INDEX_VARIABLE_NAME, 2,
        CallSomeServiceDelegate.BUSINESS_ERROR_INDEX_NAME, Integer.MAX_VALUE));

    execute(job());
    execute(job());
    assertThrows(ProcessEngineException.class, () -> execute(job()));
    assertThat(pi).isActive();
  }
}
