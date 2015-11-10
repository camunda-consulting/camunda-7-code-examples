package com.camunda.training.twitterQa.nonarquillian;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
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

  private static final String PROCESS_DEFINITION_KEY = "twitter-qa";

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
  }
  
  @Before
  public void setup() {
	init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }
  
//  @Test
  @Deployment(resources = "process.bpmn")
  public void testStartSimpleProcessForLab3() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("content", "I did it with variable and user task! Cheers The Trainer"));
    Task reviewTask = taskQuery().taskCandidateGroup("management").singleResult();
    assertThat(reviewTask).hasName("Review Tweet");
    taskService().complete(reviewTask.getId(), withVariables("approved", true));
    assertThat(pi).isEnded().hasPassed("ServiceTask_1");
  }
  
//  @Test
  @Deployment(resources = "process.bpmn")
  public void testRejectTweetForLab4() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("content", "I did it with variable! Cheers The Trainer"));
    Task reviewTask = taskQuery().taskCandidateGroup("management").singleResult();
    assertThat(reviewTask).hasName("Review Tweet");
    taskService().complete(reviewTask.getId(), withVariables("approved", false));
    assertThat(pi).isEnded().hasPassed("ServiceTask_2");    
  }
  
  @Test
  @Deployment(resources = {"process.bpmn", "table.dmn"})
  public void testReviewWithDecision() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("content", "from my DMN-test", "email", "john.doe@camunda.com"));
    assertThat(pi).isEnded().hasPassed("ServiceTask_2");
  }

  @Test
  @Deployment(resources = {"process.bpmn", "table.dmn"})
  public void testReviewWithDecisionAndContains() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("content", "hack days!", "email", "falko.menge@camunda.com"));
    assertThat(pi).isEnded().hasPassed("ServiceTask_2");
  }

//  @Test
  @Deployment(resources = {"process.bpmn", "table.dmn"})
  public void testReviewWithDecisionNoMatch() {
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("content", "foo", "email", "falko.menge@camunda.com"));
    assertThat(pi).isEnded().hasPassed("ServiceTask_2");
  }

}
