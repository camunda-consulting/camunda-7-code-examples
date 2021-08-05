package com.camunda.consulting;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.camunda.consulting.delegate.TestSendMessageDelegate;

/**
 * Testing the behaviour of the global message correlation process against a
 * test process
 * 
 * @author jonathanlukas
 *
 */
@ExtendWith(ProcessEngineExtension.class)
public class ConditionalEventProcessTests
{
	private static final String TEST_RESULT = "abc123";

	@Test
	@Deployment(resources = { "async_comm_conditional.bpmn" })
	public void shouldContinueWhenVariableIsSet()
	{
		Mocks.register("test_send_message", new TestSendMessageDelegate());
		ProcessInstance processInstance = runtimeService()
			.startProcessInstanceByKey("AsyncCommunicatorWithConditionalEventProcess");
		assertThat(processInstance).isWaitingAt(findId("Prozess gestartet"));
		execute(job());
		assertThat(processInstance).isWaitingAt(findId("Nachricht senden"));
		execute(job());
		assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
		// we will wait here (not yet ready to receive a message)
		runtimeService()
			.setVariablesAsync(List.of(processInstance.getId()), withVariables("result", TEST_RESULT));
		// move token towards the Conditional Event
		execute(job());
		assertThat(processInstance).isWaitingAt(findId("Ergebnisdaten erhalten"));
		// no job can be found as variable is not yet set
		assertNull(job());
		// set variable
		execute(jobWithoutProcessInstanceId()); // batch-seed-job
		execute(jobWithoutProcessInstanceId()); // set-variables
		execute(jobWithoutProcessInstanceId()); // batch-monitor-job
		assertThat(processInstance).isEnded().variables().contains(entry("result", TEST_RESULT));
	}

	@Test
	@Deployment(resources = { "async_comm_conditional.bpmn" })
	public void shouldContinueWhenVariableWasAlreadySet()
	{
		Mocks.register("test_send_message", new TestSendMessageDelegate());
		ProcessInstance processInstance = runtimeService()
			.startProcessInstanceByKey("AsyncCommunicatorWithConditionalEventProcess");
		assertThat(processInstance).isWaitingAt(findId("Prozess gestartet"));
		execute(job());
		assertThat(processInstance).isWaitingAt(findId("Nachricht senden"));
		execute(job());
		assertThat(processInstance).isWaitingAt(findId("Künstlicher Wartezustand"));
		// we will wait here (not yet ready to receive a message)
		runtimeService()
			.setVariablesAsync(List.of(processInstance.getId()), withVariables("result", TEST_RESULT));
		// set variable
		execute(jobWithoutProcessInstanceId()); // batch-seed-job
		execute(jobWithoutProcessInstanceId()); // set-variables
		execute(jobWithoutProcessInstanceId()); // batch-monitor-job
		// move token towards the Conditional Event
		execute(job());
		assertThat(processInstance).isEnded().variables().contains(entry("result", TEST_RESULT));
	}

	private static final Job jobWithoutProcessInstanceId()
	{
		return jobQuery().list().stream().filter(job -> job.getProcessInstanceId() == null).findFirst().get();
	}
}
