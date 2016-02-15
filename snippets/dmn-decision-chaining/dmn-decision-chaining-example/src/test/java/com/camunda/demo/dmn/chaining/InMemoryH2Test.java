package com.camunda.demo.dmn.chaining;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();

	static {
		LogFactory.useSlf4jLogging(); // MyBatis
	}

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	@Test
	@Deployment(resources = { "determineDish.dmn", "chained-dmn/determineBeverages.dmn"})
	public void testHappyPath() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("season", "Fall");
		variables.put("guestCount", 4);
		variables.put("children", Boolean.FALSE);
		DmnDecisionTableResult result = processEngine().getDecisionService().evaluateDecisionTableByKey("beverages", variables);
		assertEquals(2, result.getResultList().size());
		assertEquals("Beer", result.get(0).getSingleEntry());
		assertEquals("Water", result.get(1).getSingleEntry());
	}

	@SuppressWarnings("unchecked")
	@Test
	@Deployment(resources = {"decision-flow/determineBeveragesDecisionFlow.bpmn", "determineDish.dmn", "decision-flow/determineBeverages.dmn"})
	public void testDecisionFlow() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("season", "Fall");
		variables.put("guestCount", 4);
		variables.put("children", Boolean.FALSE);
		ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("determineBeverages", variables);
		
		List<String> beverages = (List<String>) processEngine().getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).variableName("beverages").singleResult().getValue();
		assertEquals(2, beverages.size());
		assertEquals("Beer", beverages.get(0));
		assertEquals("Water", beverages.get(1));
	}
}
