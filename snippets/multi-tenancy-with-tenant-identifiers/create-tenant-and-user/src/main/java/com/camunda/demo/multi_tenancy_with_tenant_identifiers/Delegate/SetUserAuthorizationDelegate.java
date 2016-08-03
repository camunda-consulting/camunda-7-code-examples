package com.camunda.demo.multi_tenancy_with_tenant_identifiers.Delegate;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.camunda.demo.multi_tenancy_with_tenant_identifiers.Model.PlatformUser;
import com.camunda.demo.multi_tenancy_with_tenant_identifiers.Service.UserManagementService;

public class SetUserAuthorizationDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		PlatformUser user = (PlatformUser) execution.getVariable("platformUser");
		
		String[] allowedProcessDefinitions = (String[]) execution.getVariable("allowedProcessDefinitions");
		
		AuthorizationService authorizationService = execution.getProcessEngineServices().getAuthorizationService();
		
		String userId = execution.getProcessEngineServices().getIdentityService().createUserQuery().userEmail(user.getEmail()).singleResult().getId();
					
		String[] userIds = new String[]{userId};
		
		String userType = user.getUserType();
		
		// Set authorizations for cockpit users only
		
		if (userType.equals("cockpit")){
			
			UserManagementService.
				createGrantUserAuthorization(
						authorizationService, 
						userIds, 
						new Permission[]{Permissions.ACCESS}, 
						Resources.APPLICATION,
				        new String[] {"*"}
						);
			
			UserManagementService.
				createGrantUserAuthorization(
					authorizationService, 
					userIds, 
					new Permission[]{Permissions.ALL}, 
					Resources.PROCESS_DEFINITION,
					allowedProcessDefinitions
					);
			
			UserManagementService.
				createGrantUserAuthorization(
					authorizationService, 
					userIds, 
					new Permission[]{Permissions.ALL}, 
					Resources.PROCESS_INSTANCE,
					new String[]{"*"}
				);
			
			UserManagementService.
			createGrantUserAuthorization(
				authorizationService, 
				userIds, 
				new Permission[]{Permissions.ALL}, 
				Resources.FILTER,
				new String[]{"*"}
			);
					
		}
	
		// Set authorizations for tasklist users

		if (userType.equals("tasklist")){
		
			UserManagementService.
				createGrantUserAuthorization(
					authorizationService, 
					userIds, 
					new Permission[]{Permissions.ACCESS}, 
					Resources.APPLICATION,
					new String[] {"tasklist"}
					);
			
			UserManagementService.
				createGrantUserAuthorization(
					authorizationService, 
					userIds, 
					new Permission[]{Permissions.READ, 
							Permissions.CREATE_INSTANCE, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE,
							Permissions.READ_TASK, Permissions.UPDATE_TASK}, 
					Resources.PROCESS_DEFINITION,
					allowedProcessDefinitions
					);
			
			UserManagementService.
				createGrantUserAuthorization(
					authorizationService, 
					userIds, 
					new Permission[]{Permissions.CREATE, Permissions.UPDATE, Permissions.READ}, 
					Resources.PROCESS_INSTANCE,
					new String[]{"*"}
			);
		}
	}

}
