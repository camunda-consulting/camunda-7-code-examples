package com.camunda.training.twitterQa.nonarquillian;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.SerializationDataFormat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.training.twitterQa.Tweet;

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
    Tweet tweet = new Tweet();
    tweet.setContent("I did it with variable and user task! Cheers The Trainer");
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("tweet", tweet));
    Task reviewTask = taskQuery().taskCandidateGroup("management").singleResult();
    assertThat(reviewTask).hasName("Review Tweet");
    tweet.setApproved(true);
    taskService().complete(reviewTask.getId(), withVariables("tweet", tweet));
    assertThat(pi).isEnded().hasPassed("ServiceTask_1");
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testRejectTweetForLab4() {
    Tweet tweet = new Tweet();
    tweet.setContent("I did it with variable and user task! Cheers The Trainer");
    
    ObjectValue tweetObjectValue = Variables.objectValue(tweet).serializationDataFormat(SerializationDataFormats.JSON).create();
    
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("tweet", tweetObjectValue));
    
    Task reviewTask = taskQuery().taskCandidateGroup("management").singleResult();
    assertThat(reviewTask).hasName("Review Tweet");
    
    tweet = (Tweet) taskService().getVariable(reviewTask.getId(), "tweet");
    tweet.setApproved(false);
    taskService().complete(reviewTask.getId(), withVariables("tweet", tweet));
    assertThat(pi).isEnded().hasPassed("ServiceTask_2");    
  }

}
