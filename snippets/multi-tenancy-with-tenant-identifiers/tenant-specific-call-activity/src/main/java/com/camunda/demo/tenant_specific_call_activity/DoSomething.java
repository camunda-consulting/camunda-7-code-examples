package com.camunda.demo.tenant_specific_call_activity;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DoSomething implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		execution.getProcessEngineServices().getRuntimeService().setVariable(execution.getId(), "calledElementTenantIdProvider", new CalledElementTenantIdProvider());
		
	}

}
