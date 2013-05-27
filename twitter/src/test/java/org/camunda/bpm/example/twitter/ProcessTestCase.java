package org.camunda.bpm.example.twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.test.TestHelper;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class ProcessTestCase {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  public void testDeployment() {
  }

  @Test
  @Deployment(resources = "TwitterDemoProcess.bpmn")
  public void testRejectedPath() {
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("content", "We will never see this content on Twitter");
    variables.put("email", "bernd.ruecker@camunda.com");

    ProcessInstance processInstance = processEngineRule.getRuntimeService().startProcessInstanceByKey("TwitterDemoProcess", variables);
    String id = processInstance.getId();
    System.out.println("Started process instance id " + id);

    Assert.assertThat(processEngineRule.getRuntimeService().getActiveActivityIds(id), JUnitMatchers.hasItem("user_task_review_tweet"));

    List<HistoricActivityInstance> historyActivities = processEngineRule.getProcessEngine().getHistoryService().createHistoricActivityInstanceQuery() //
            .processInstanceId(processInstance.getId()) //
            .finished() //
            .orderByActivityId().asc() //
            .list();

    assertEquals(1, historyActivities.size());
    assertEquals("start_event_new_tweet", historyActivities.get(0).getActivityId());

    Task task = processEngineRule.getTaskService().createTaskQuery().taskAssignee("demo").singleResult();
    variables.put("approved", Boolean.FALSE);
    variables.put("comments", "No, we will not publish this on Twitter");    
    processEngineRule.getTaskService().complete(task.getId(), variables);

    TestHelper.assertProcessEnded(processEngineRule.getProcessEngine(), id);

    HistoricProcessInstance historicProcessInstance = processEngineRule.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(id)
            .singleResult();
    assertNotNull(historicProcessInstance);

    System.out.println("Finished, took " + historicProcessInstance.getDurationInMillis() + " millis");

    historyActivities = processEngineRule.getProcessEngine().getHistoryService().createHistoricActivityInstanceQuery() //
            .processInstanceId(processInstance.getId()) //
            .finished() //
            .orderByActivityId().asc() // we order this way as it may happen that the time stamp is eactly the same in an in memory test case
            .list();

    assertEquals(6, historyActivities.size());
    assertEquals("end_event_tweet_handled", historyActivities.get(0).getActivityId());
    assertEquals("gateway_approved", historyActivities.get(1).getActivityId());
    assertEquals("gateway_join", historyActivities.get(2).getActivityId());
    assertEquals("service_task_send_rejection_notification", historyActivities.get(3).getActivityId());
    assertEquals("start_event_new_tweet", historyActivities.get(4).getActivityId());
    assertEquals("user_task_review_tweet", historyActivities.get(5).getActivityId());

  }

}