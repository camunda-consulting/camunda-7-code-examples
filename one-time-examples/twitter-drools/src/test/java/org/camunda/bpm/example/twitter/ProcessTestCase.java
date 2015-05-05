package org.camunda.bpm.example.twitter;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.test.TestHelper;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestCase {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  public void testDeployment() {
  }

  @Test
  @Deployment(resources = "TwitterDemoProcess.bpmn")
  public void testRejectedPath() {
    Map<String, Object> variables = new HashMap<String, Object>();
    Tweet tweet = new Tweet();
    tweet.setContent("We will never see this content on Twitter because it containes John Doe stories.");
    tweet.setEmail("bernd.ruecker@camunda.com");    
    variables.put("tweet", tweet);

    ProcessInstance processInstance = processEngineRule.getRuntimeService().startProcessInstanceByKey("TwitterDemoProcessDrools", variables);
    String id = processInstance.getId();
    System.out.println("Started process instance id " + id);
    
    // should be already finished in one go:
    TestHelper.assertProcessEnded(processEngineRule.getProcessEngine(), id);

    HistoricProcessInstance historicProcessInstance = processEngineRule.getHistoryService().createHistoricProcessInstanceQuery() //
            .processInstanceId(id) //
            .singleResult();
    assertNotNull(historicProcessInstance);

    System.out.println("Finished, took " + historicProcessInstance.getDurationInMillis() + " millis");
  }

  @Test
  @Deployment(resources = "TwitterDemoProcess.bpmn")
  public void testApprovedPath() {
    Map<String, Object> variables = new HashMap<String, Object>();
    Tweet tweet = new Tweet();
    tweet.setContent("Hello from Twitter Drools lab - no rejected content in here .Timestamp " + System.currentTimeMillis());
    tweet.setEmail("bernd.ruecker@camunda.com");    
    variables.put("tweet", tweet);

    ProcessInstance processInstance = processEngineRule.getRuntimeService().startProcessInstanceByKey("TwitterDemoProcessDrools", variables);
    String id = processInstance.getId();
    System.out.println("Started process instance id " + id);
    
    // should be already finished in one go:
    TestHelper.assertProcessEnded(processEngineRule.getProcessEngine(), id);

    HistoricProcessInstance historicProcessInstance = processEngineRule.getHistoryService().createHistoricProcessInstanceQuery() //
            .processInstanceId(id) //
            .singleResult();
    assertNotNull(historicProcessInstance);

    System.out.println("Finished, took " + historicProcessInstance.getDurationInMillis() + " millis");
  }
}