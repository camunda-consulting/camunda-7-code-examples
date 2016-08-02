package com.camunda.demo.multi_tenancy_with_tenant_identifiers.Delegate;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.camunda.demo.multi_tenancy_with_tenant_identifiers.Model.PlatformUser;
import com.camunda.demo.multi_tenancy_with_tenant_identifiers.Service.UserManagementService;


public class CreateUserDelegate implements JavaDelegate{

	
	public void execute(DelegateExecution execution) throws Exception {

		PlatformUser user = (PlatformUser) execution.getVariable("platformUser");
		
		String tenantId  = (String) execution.getVariable("tenantId");
		
		IdentityService identityService = execution.getProcessEngineServices().getIdentityService();
		String userId = UserManagementService.addUser(identityService, user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
		
		if (userId != null){
			identityService.createTenantUserMembership(tenantId, userId);
		}
		
		
	}

}
