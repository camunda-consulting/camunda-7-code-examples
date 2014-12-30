package com.camunda.bpm.cob.subprocess;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class StartProcessDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ProcessInstance ssiApprovalInstance = execution
				.getProcessEngineServices()
				.getRuntimeService()
				.startProcessInstanceByKey(
						"ssi-approval", 
						execution.getVariables());
		execution.setVariable("ssiApprovalInstanceId", ssiApprovalInstance.getProcessInstanceId());
	}

}
