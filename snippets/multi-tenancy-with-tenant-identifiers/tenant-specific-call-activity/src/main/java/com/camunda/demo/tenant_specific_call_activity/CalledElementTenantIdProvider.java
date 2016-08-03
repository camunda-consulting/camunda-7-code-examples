package com.camunda.demo.tenant_specific_call_activity;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.model.bpmn.instance.CallActivity;

public class CalledElementTenantIdProvider {

	  public String resolveTenantId(DelegateExecution execution) {
	    RepositoryService repositoryService = execution.getProcessEngineServices().getRepositoryService();

	    String tenantId = execution.getTenantId();

	    // resolve the process definition key
	    CallActivity callActivity = (CallActivity) execution.getBpmnModelElementInstance();
	    String processDefinitionKey = callActivity.getCalledElement();

	    // and check if a process definition is deployed for the tenant
	    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
	      .processDefinitionKey(processDefinitionKey)
	      .tenantIdIn(tenantId)
	      .latestVersion()
	      .singleResult();

	    if (processDefinition != null) {
	      // use tenant-specific process definition
	      return tenantId;
	    } else {
	      // use default process definition
	      return null;
	    }
	  }

	}