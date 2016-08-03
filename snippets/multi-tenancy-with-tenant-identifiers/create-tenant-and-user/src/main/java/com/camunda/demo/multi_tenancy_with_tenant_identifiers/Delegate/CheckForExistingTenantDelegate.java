package com.camunda.demo.multi_tenancy_with_tenant_identifiers.Delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckForExistingTenantDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String tenantName = (String) execution.getVariable("tenantName");
		
		String tenantId = tenantName.toLowerCase().replace(' ', '_');
		execution.setVariable("tenantId", tenantId);
		
		if (execution.getProcessEngineServices().getIdentityService().createTenantQuery().tenantId(tenantId).singleResult() != null){
			execution.setVariable("tenantExists", true);
		}
		
		else {
			execution.setVariable("tenantExists", false);
		}

	}

}
