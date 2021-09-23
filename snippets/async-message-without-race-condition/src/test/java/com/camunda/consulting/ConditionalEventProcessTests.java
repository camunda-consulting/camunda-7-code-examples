package com.camunda.consulting;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Testing the behaviour of the Conditional Event on Async set Variable
 * 
 * @author jonathanlukas
 *
 */
@ExtendWith(ProcessEngineExtension.class)
public class ConditionalEventProcessTests implements PsiScenarioCoverage {
  private static final String TEST_RESULT = "abc123";

  private static final Job jobWithoutProcessInstanceId() {
    return jobQuery().list().stream().filter(job -> job.getProcessInstanceId() == null).findFirst().get();
  }

  private static final void executeSetVariableJob() {
    execute(jobWithoutProcessInstanceId()); // batch-seed-job
    execute(jobWithoutProcessInstanceId()); // set-variables
    execute(jobWithoutProcessInstanceId()); // batch-monitor-job
  }

  @Test
  @Deployment(resources = { "async_communication_conditional.bpmn" })
  @Override
  public void answerBeforeProcess() {
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey("AsyncCommunicationConditionalProcess");
    assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
    // we will wait here (not yet ready to receive a message)
    runtimeService().setVariablesAsync(List.of(processInstance.getId()), withVariables("result", TEST_RESULT));
    // set variable in job
    executeSetVariableJob();
    // move token over the Conditional Event
    execute(job());
    assertThat(processInstance).isEnded().variables().contains(entry("result", TEST_RESULT));
  }

  @Test
  @Deployment(resources = { "async_communication_conditional.bpmn" })
  @Override
  public void processBeforeAnswer() {
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey("AsyncCommunicationConditionalProcess");
    assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
    // we will wait here (not yet ready to receive a message)
    runtimeService().setVariablesAsync(List.of(processInstance.getId()), withVariables("result", TEST_RESULT));
    // move token towards the Conditional Event
    execute(job());
    assertThat(processInstance).isWaitingAt(findId("Ergebnisdaten erhalten"));
    // no job can be found as variable is not yet set
    assertNull(job());
    // set variable in job
    executeSetVariableJob();
    assertThat(processInstance).isEnded().variables().contains(entry("result", TEST_RESULT));

  }

  @Test
  @Override
  @Deployment(resources = "answer_only_conditional.bpmn")
  public void answerOnly() {
    List<ProcessInstance> processInstances = runtimeService().createConditionEvaluation()
        .setVariable("result", TEST_RESULT).evaluateStartConditions();
    assertThat(processInstances).hasSize(1);
    ProcessInstance processInstance = processInstances.get(0);
    assertThat(processInstance).isWaitingAt(findId("Prozess gestartet mit Ergebnisdaten"));
    execute(job());
    assertThat(processInstance).isEnded();
  }

  @Override
  @Test
  @Deployment(resources = { "multiple_candidates_conditional.bpmn" })
  public void multipleAnswerCandidates() {
    // Use result 1 to continue the process, set BEFORE the process is ready
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey("MultipleCandidatesConditionalProcess");
    assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
    runtimeService().setVariablesAsync(List.of(processInstance.getId()), withVariables("result1", TEST_RESULT));
    executeSetVariableJob();
    execute(job());
    assertThat(processInstance).isEnded().hasPassed(findId("Prozess beendet 1"));
    // use result 2 to continue the process, set AFTER the process is ready
    processInstance = runtimeService().startProcessInstanceByKey("MultipleCandidatesConditionalProcess");
    assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
    runtimeService().setVariablesAsync(List.of(processInstance.getId()), withVariables("result2", TEST_RESULT));
    execute(job());
    executeSetVariableJob();
    assertThat(processInstance).isEnded().hasPassed(findId("Prozess beendet 2"));
  }
}
