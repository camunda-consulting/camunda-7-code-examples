package com.camunda.poc.demo;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.job;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessUnitTest {

  private static final String PROCESS_DEFINITION_KEY = "mhs-biomarker-process";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Rule
  public final ProcessEngineRule processEngine = new StandaloneInMemoryTestConfiguration().rule();

  @Test
  public void testHappyPath() {
    
//	Map<String, Object> variables = new HashMap<String, Object>();
//	variables.put("score", 1);
//	variables.put("mindstrongUserID", "a");

	// Either: Drive the process by API and assert correct behavior by camunda-bpm-assert, e.g.:
//    ProcessInstance processInstance = processEngine().getRuntimeService()
//    		.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
//	assertThat(processInstance).isActive();
//
//	assertThat(processInstance).isWaitingAt("StartEvent_1");
//	execute(job());
//
//	assertThat(processInstance).isWaitingAt("high-priportiy-task");  		
//	complete(task(), withVariables("confirmed", true));
//
//	assertThat(processInstance).isWaitingAt("wait-for-reply");
//	processEngine().getRuntimeService()
//		.correlateMessage("wiat_for_high_priority_reply_message");
//
//	assertThat(processInstance).isEnded();

	    
  }

}
