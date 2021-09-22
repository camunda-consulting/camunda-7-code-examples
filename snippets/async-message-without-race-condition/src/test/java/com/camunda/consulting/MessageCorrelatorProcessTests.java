package com.camunda.consulting;

import static com.camunda.consulting.GlobalMessageCorrelationConstants.DelegateName.*;
import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.camunda.consulting.correlator.ActualMessageCorrelator;
import com.camunda.consulting.correlator.GlobalMessageCorrelator;
import com.camunda.consulting.correlator.TestMessageCorrelationRunner;
import com.camunda.consulting.correlator.TestStartMessageCorrelationRunner;
import com.camunda.consulting.delegate.RedirectToActualReceiverDelegate;
import com.camunda.consulting.delegate.TestSendMessageDelegate;

/**
 * Testing the behaviour of the global message correlation process against a
 * test process
 * 
 * @author jonathanlukas
 *
 */
@ExtendWith(ProcessEngineExtension.class)
@Deployment(resources = { "global_message_correlation.bpmn", "async_communication_message.bpmn",
    "answer_only_message.bpmn", "multiple_candidates_message.bpmn" })
public class MessageCorrelatorProcessTests implements PsiScenarioCoverage {
  private static final String TEST_RESULT = "abc123";

  private GlobalMessageCorrelator correlator;

  @BeforeEach
  public void setupSingle() {
    this.correlator = new GlobalMessageCorrelator(processEngine());
    Mocks.register("test_send_message", new TestSendMessageDelegate());
    Mocks.register(RedirectToActualReceiverDlg,
        new RedirectToActualReceiverDelegate(new ActualMessageCorrelator(processEngine())));
  }

  /**
   * Here, a runner is created, containing a variable to proof this works with
   * Jackson serialization
   * 
   * @return
   */
  private static TestMessageCorrelationRunner mockTestMessageCorrelationRunner() {
    TestMessageCorrelationRunner runner = new TestMessageCorrelationRunner();
    runner.setResult(TEST_RESULT);
    return runner;
  }

  /**
   * Here, a runner is created, containing a variable to proof this works with
   * Jackson serialization
   * 
   * @return
   */
  private static TestStartMessageCorrelationRunner mockTestStartMessageCorrelationRunner() {
    TestStartMessageCorrelationRunner runner = new TestStartMessageCorrelationRunner();
    runner.setResult(TEST_RESULT);
    return runner;
  }

  @Test
  @Override
  public void answerBeforeProcess() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("AsyncCommunicationMessageProcess");
    assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
    // we will wait here (not yet ready to receive a message)
    ProcessInstance messageProcess = this.correlator.correlateMessage(mockTestMessageCorrelationRunner());
    assertThat(messageProcess).isWaitingAt(findId("Nachricht empfangen"));
    execute(job());
    assertThat(messageProcess).isWaitingAt(findId("Nachricht an eigentlichen Empfänger weitergeleitet"));
    // we simulate a race condition that this process is ended before the actual
    // message receiver is ready, here, the retry mechanism of the engine will
    // make
    // this happen over and over
    assertThrows(MismatchingMessageCorrelationException.class, () -> execute(job()));
    // now we move the receiver process in position
    execute(job(processInstance));
    // and send the message to it again
    execute(job(messageProcess));
    assertThat(messageProcess).isEnded();
    // everything worked out fine, proof is the variable that was set
    assertThat(processInstance).isEnded().variables().contains(entry("result", TEST_RESULT));
  }

  @Test
  @Override
  public void processBeforeAnswer() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("AsyncCommunicationMessageProcess");
    assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
    // we will wait here (not yet ready to receive a message)
    ProcessInstance messageProcess = this.correlator.correlateMessage(mockTestMessageCorrelationRunner());
    assertThat(messageProcess).isWaitingAt(findId("Nachricht empfangen"));
    execute(job());
    assertThat(messageProcess).isWaitingAt(findId("Nachricht an eigentlichen Empfänger weitergeleitet"));
    // now we move the receiver process in position
    execute(job(processInstance));
    // and send the message to it again
    execute(job(messageProcess));
    assertThat(messageProcess).isEnded();
    // everything worked out fine, proof is the variable that was set
    assertThat(processInstance).isEnded().variables().contains(entry("result", TEST_RESULT));

  }

  @Test
  @Override
  public void answerOnly() {
    ProcessInstance messageProcess = this.correlator.correlateMessage(mockTestStartMessageCorrelationRunner());
    assertThat(messageProcess).isWaitingAt(findId("Nachricht empfangen"));
    execute(job());
    assertThat(messageProcess).isWaitingAt(findId("Nachricht an eigentlichen Empfänger weitergeleitet"));
    execute(job());
    assertThat(messageProcess).isEnded();
    assertThat(processInstanceQuery().singleResult()).isNotNull();
    ProcessInstance startedInstance = processInstanceQuery().singleResult();
    assertThat(startedInstance).isWaitingAt(findId("Prozess gestartet mit Ergebnisdaten"));
    execute(job());
    assertThat(startedInstance).isEnded();
  }

  @Override
  public void multipleAnswerCandidates() {
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey("MultipleCandidatesConditionalProcess");
    assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));

  }
}
