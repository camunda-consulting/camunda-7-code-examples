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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.camunda.consulting.correlator.ActualMessageCorrelator;
import com.camunda.consulting.correlator.GlobalMessageCorrelator;
import com.camunda.consulting.correlator.TestMessageCorrelationRunner;
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
public class MessageCorrelatorProcessTests
{
	private static final String TEST_RESULT = "abc123";

	@Test
	@Deployment(resources = { "global_message_correlation.bpmn", "asyc_communicator.bpmn" })
	public void shouldRun()
	{
		Mocks.register("test_send_message", new TestSendMessageDelegate());
		Mocks
			.register(
				RedirectToActualReceiverDlg,
				new RedirectToActualReceiverDelegate(new ActualMessageCorrelator(processEngine())));
		ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("AsyncCommunicatorProcess");
		assertThat(processInstance).isWaitingAt(findId("Prozess gestartet"));
		execute(job());
		assertThat(processInstance).isWaitingAt(findId("Nachricht senden"));
		execute(job());
		assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
		// we will wait here (not yet ready to receive a message)
		GlobalMessageCorrelator global = new GlobalMessageCorrelator(processEngine());
		ProcessInstance messageProcess = global.correlateMessage(mockTestMessageCorrelationRunner());
		assertThat(messageProcess).isWaitingAt(findId("Nachricht empfangen"));
		execute(job());
		assertThat(messageProcess).isWaitingAt(findId("Nachricht an eigentlichen Empfänger weitergeleitet"));
		// we simulate a race condition that this process is ended before the actual
		// message receiver is ready, here, the retry mechanism of the engine will make
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

	/**
	 * Here, a runner is created, containing a variable to proof this works with
	 * Jackson serialization
	 * 
	 * @return
	 */
	private static TestMessageCorrelationRunner mockTestMessageCorrelationRunner()
	{
		TestMessageCorrelationRunner runner = new TestMessageCorrelationRunner();
		runner.setResult(TEST_RESULT);
		return runner;
	}
}
