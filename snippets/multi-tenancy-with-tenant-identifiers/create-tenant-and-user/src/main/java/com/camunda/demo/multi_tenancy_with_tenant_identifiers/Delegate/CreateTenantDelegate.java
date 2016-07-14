package com.camunda.demo.multi_tenancy_with_tenant_identifiers.Delegate;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Tenant;

public class CreateTenantDelegate implements JavaDelegate{
	
	public void execute(DelegateExecution execution) throws Exception {
		
		// get tenant name which was entered in the web form and create tenant ID from it
		
		String tenantId = (String) execution.getVariable("tenantId");
				
		String tenantName = (String) execution.getVariable("tenantName");
				
		IdentityService identityService = execution.getProcessEngineServices().getIdentityService();
		
		Tenant tenant = identityService.newTenant(tenantId);
		
		tenant.setName(tenantName);
		
		identityService.saveTenant(tenant);
		
	}

}
