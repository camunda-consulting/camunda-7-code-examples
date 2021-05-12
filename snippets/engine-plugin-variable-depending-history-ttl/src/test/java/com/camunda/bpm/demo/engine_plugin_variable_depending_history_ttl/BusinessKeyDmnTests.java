package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
public class BusinessKeyDmnTests
{

	static
	{
		LogFactory.useSlf4jLogging(); // MyBatis
	}

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule("camundaBusinessKeyDmn.cfg.xml", true);

	@Test
	@Deployment(resources = { "process.bpmn", "historyTtlRules.dmn" })
	public void testHappyPath()
	{
		// Start the process and add the required variable
		Mocks.register("logger", new LoggerDelegate());
		ProcessInstance processInstance = processEngine()
			.getRuntimeService()
			.createProcessInstanceByKey(ProcessConstants.PROCESS_DEFINITION_KEY)
			.businessKey("siefbfooawidb")
			.execute();
		// assert that the process instance has ended and still contains the required
		// variable
		assertThat(processInstance).isEnded();
		LocalDate removalTime = historyService()
			.createHistoricProcessInstanceQuery()
			.processInstanceId(processInstance.getProcessInstanceId())
			.singleResult()
			.getRemovalTime()
			.toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		// assert that the removal time was calculated correctly
		assertEquals(LocalDate.now().plusDays(10), removalTime);
	}

}
