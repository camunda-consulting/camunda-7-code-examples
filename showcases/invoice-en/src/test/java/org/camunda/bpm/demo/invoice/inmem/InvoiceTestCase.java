package org.camunda.bpm.demo.invoice.inmem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.camunda.bpm.demo.invoice.test.mock.SvnDelegateMock;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.test.mock.Mocks;


public class InvoiceTestCase extends ProcessEngineTestCase {
  
//  @Inject
//  private Conversation conversation;
	
	@Deployment(resources="camunda-invoice-de.bpmn")
	public void testHappyPath() {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("camunda-invoice-de");
		
		List<Task> tasks = taskService.createTaskQuery()
		        .processInstanceId(pi.getId())
		        .list();
		
		assertEquals(1, tasks.size());
		assertEquals("user-task-select-approver", tasks.get(0).getTaskDefinitionKey());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("approver", "somebody");
		taskService.complete(tasks.get(0).getId(), variables);

		tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
		
		assertEquals(1, tasks.size());
		assertEquals("approveInvoice", tasks.get(0).getTaskDefinitionKey());
		assertEquals("somebody", tasks.get(0).getAssignee());
		
		Mocks.register("archiveService", new SvnDelegateMock());
		
		variables = new HashMap<String, Object>();
		variables.put("approved", Boolean.TRUE);
		taskService.complete(tasks.get(0).getId(), variables);

		tasks = taskService.createTaskQuery().processInstanceId(pi.getId()).list();

		assertEquals(1, tasks.size());
		assertEquals("prepareBankTransfer", tasks.get(0).getTaskDefinitionKey());
		taskService.complete(tasks.get(0).getId());
		
		assertProcessEnded(pi.getId());
	}

}
