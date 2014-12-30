package com.camunda.bpm.cob.subprocess;

import java.util.List;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;

public class CancelProcessDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String subProcessInstanceId = (String) execution.getVariable("ssiApprovalInstanceId");
		RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		List<Execution> subProcessInstances = runtimeService.createExecutionQuery().executionId(subProcessInstanceId).list();
		if (subProcessInstances.isEmpty() == false) {
			runtimeService.setVariable(subProcessInstanceId, "cancelReason", "original counterparty request is rejected");
			runtimeService.deleteProcessInstance(subProcessInstanceId, "original counterparty request is rejected");
		}
	}

}
