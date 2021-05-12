package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Deployment(resources = "historyTtlRules.dmn")
public class TimeToLiveTableTests
{

	static
	{
		LogFactory.useSlf4jLogging(); // MyBatis
	}

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule(true);

	@Test
	public void testHit()
	{
		DmnDecisionTableResult evaluateDecisionTableByKey = decisionService()
			.evaluateDecisionTableByKey("historyTtlRules", Map.of("businessKey", "sdufbpeubfooaefbsf"));
		Integer singleEntry = evaluateDecisionTableByKey.getSingleEntry();
		assertEquals(10, singleEntry);
	}

	@Test
	public void testNotHit()
	{
		DmnDecisionTableResult evaluateDecisionTableByKey = decisionService()
			.evaluateDecisionTableByKey("historyTtlRules", Map.of("businessKey", "asdqwe"));
		Integer singleEntry = evaluateDecisionTableByKey.getSingleEntry();
		assertNull(singleEntry);
	}

}
