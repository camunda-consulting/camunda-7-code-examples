package org.camunda.bpm.example.twitter;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestCase {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  @Test
  @Deployment(resources = "TwitterDemoProcess.bpmn")
  public void testRejectedPath() {
    Mocks.register("emailAdapter", new RejectionNotificationDelegate()); // get expression to work without Spring or CDI in JUnit
    
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("content", "We will never see this content on Twitter");
    variables.put("email", "bernd.ruecker@camunda.com");
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("TwitterDemoProcess", variables);

    System.out.println("Started process instance " + processInstance);
    
    // check userTask
    // using camunda-bpm-assert
    assertThat(processInstance).isStarted() //
    	.isWaitingAt("user_task_review_tweet") //
    	.task() //
    	.isAssignedTo("demo");
    
    complete(task(), Variables.createVariables().putValue("approved", Boolean.FALSE));
    
    // or using the Java API
//    Task task = processEngineRule.getTaskService().createTaskQuery().taskCandidateGroup("management").singleResult();
//    variables.put("approved", Boolean.FALSE);
//    variables.put("comments", "No, we will not publish this on Twitter");
//    processEngine().getTaskService().complete(task.getId(), variables);

    // assert that all activities where passed
    assertThat(processInstance) //
    	.isEnded() //
    	.hasPassed("end_event_tweet_handled", "gateway_approved", "gateway_join", "service_task_send_rejection_notification", "start_event_new_tweet", "user_task_review_tweet");

    ProcessTestCoverage.calculate(processInstance, processEngineRule.getProcessEngine());
  }
  
  
  @After
  public void calculateCoverage() throws Exception {
    // calculate coverage for all tests
    ProcessTestCoverage.calculate(processEngineRule.getProcessEngine());
  }  

}