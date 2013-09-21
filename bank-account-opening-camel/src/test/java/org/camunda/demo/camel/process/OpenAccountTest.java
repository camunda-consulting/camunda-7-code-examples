package org.camunda.demo.camel.process;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.camel.common.CamelService;
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


/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 * @author Rafael Cordones - rafael@cordones.me
 *
 */
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

		CamelService camelService = mock(CamelService.class);
		Mocks.register("camel", camelService);
		
		// Start process instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("open-account");
		
		runtimeService.signal(processInstance.getId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", true);
		
		Task task = taskService.createTaskQuery().singleResult();
		taskService.complete(task.getId(), variables);
		
		assertEquals(0, runtimeService.createProcessInstanceQuery().list().size());
		
		List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list();
		assertEquals(6, activityInstances.size());
		
		assertEquals("order_received", activityInstances.get(0).getActivityId());
		assertEquals("wait_for_postident", activityInstances.get(1).getActivityId());
		assertEquals("check_documents", activityInstances.get(2).getActivityId());
		assertEquals("documents_approved_gateway", activityInstances.get(3).getActivityId());
		assertEquals("set_up_account", activityInstances.get(4).getActivityId());
		assertEquals("order_processed", activityInstances.get(5).getActivityId());
	}

	@Test
	@Deployment(resources="open-account.bpmn")
	public void testNonApprovedPath() throws Exception {
		// Get services
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		TaskService taskService = activitiRule.getTaskService();
		HistoryService historyService = activitiRule.getHistoryService();

    CamelService camelService = mock(CamelService.class);
    Mocks.register("camel", camelService);
		
		// Start process instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("open-account");
		
		runtimeService.signal(processInstance.getId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approved", false);
		
		Task task = taskService.createTaskQuery().singleResult();
		taskService.complete(task.getId(), variables);
		
		assertEquals(0, runtimeService.createProcessInstanceQuery().list().size());
		
		List<HistoricActivityInstance> activityInstances = historyService.createHistoricActivityInstanceQuery().orderByHistoricActivityInstanceStartTime().asc().list();
		assertEquals(6, activityInstances.size());
		
		assertEquals("order_received", activityInstances.get(0).getActivityId());
		assertEquals("wait_for_postident", activityInstances.get(1).getActivityId());
		assertEquals("check_documents", activityInstances.get(2).getActivityId());
		assertEquals("documents_approved_gateway", activityInstances.get(3).getActivityId());
		assertEquals("inform_customer", activityInstances.get(4).getActivityId());		
		assertEquals("order_rejected", activityInstances.get(5).getActivityId());
	}
	
}
