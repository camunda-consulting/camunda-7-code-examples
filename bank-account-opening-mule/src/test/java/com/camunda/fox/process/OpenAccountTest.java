package com.camunda.fox.process;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.fox.mule.MuleService;

public class OpenAccountTest {

	@Rule
	public ProcessEngineRule activitiRule = new ProcessEngineRule();

	@Test
	@Deployment(resources="open-account.bpmn")
	public void testApprovedPath() throws Exception {
		// Get services
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();
		HistoryService historyService = activitiRule.getHistoryService();
		
		// Prepare mock for muleService
		MuleService muleService = mock(MuleService.class);
		Mocks.register("mule", muleService);
		
		// Start process instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("open-account-mule");
		
		runtimeService.signal(processInstance.getId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", true);
		
		Task task = taskService.createTaskQuery().singleResult();
		taskService.complete(task.getId(), variables);
		
		assertEquals(0, runtimeService.createProcessInstanceQuery().list().size());
		
		List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list();
		assertEquals(7, activityInstances.size());
		
		assertEquals("order_received", activityInstances.get(0).getActivityId());
		assertEquals("wait_for_postident", activityInstances.get(1).getActivityId());
		assertEquals("check_documents", activityInstances.get(2).getActivityId());
		assertEquals("documents_approved_gateway", activityInstances.get(3).getActivityId());
		assertEquals("set_up_account", activityInstances.get(4).getActivityId());
		assertEquals("merging_gateway_1", activityInstances.get(5).getActivityId());
		assertEquals("order_processed", activityInstances.get(6).getActivityId());
	}

	@Test
	@Deployment(resources="open-account.bpmn")
	public void testNonApprovedPath() throws Exception {
		// Get services
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();
		HistoryService historyService = activitiRule.getHistoryService();
		
		// Prepare mock for muleService
    MuleService muleService = mock(MuleService.class);
    Mocks.register("mule", muleService);
		
		// Start process instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("open-account-mule");
		
		runtimeService.signal(processInstance.getId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", false);
		
		Task task = taskService.createTaskQuery().singleResult();
		taskService.complete(task.getId(), variables);
		
		assertEquals(0, runtimeService.createProcessInstanceQuery().list().size());
		
		List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list();
		assertEquals(7, activityInstances.size());
		
		assertEquals("order_received", activityInstances.get(0).getActivityId());
		assertEquals("wait_for_postident", activityInstances.get(1).getActivityId());
		assertEquals("check_documents", activityInstances.get(2).getActivityId());
		assertEquals("documents_approved_gateway", activityInstances.get(3).getActivityId());
		assertEquals("inform_customer", activityInstances.get(4).getActivityId());
		assertEquals("merging_gateway_1", activityInstances.get(5).getActivityId());
		assertEquals("order_processed", activityInstances.get(6).getActivityId());
	}
	
}
