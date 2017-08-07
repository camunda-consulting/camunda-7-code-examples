package com.camunda.consulting.cmmn_listener_extension;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

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
  @Deployment(resources = "assigned-tasks.cmmn")
  public void testHappyPath() {
    CaseInstance caseInstance = caseService().createCaseInstanceByKey("AssignTasksCase");

	  assertThat(caseInstance).humanTask("forPeterHumanTask").isActive();
	  
	  assertThat(caseInstance).humanTask("forJohnHumanTask").isEnabled();
	  
	  manuallyStart(caseExecution("forJohnHumanTask", caseInstance));
	  assertThat(caseInstance).humanTask("forJohnHumanTask").isActive();
  }

}
