package com.camunda.potsdam.UserAndTenantCreator;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;

public class CreateUserDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String firstName = (String)execution.getVariable("firstName");
		String secondName = (String)execution.getVariable("secondName");
		
		String userName = (String) execution.getVariable("userName");
		String password = (String) execution.getVariable("password");
		
		String email = (String) execution.getVariable("emailAddress");
		
		final IdentityService identityService = execution.getProcessEngineServices().getIdentityService();
		 
		User user = identityService.newUser(userName);
	      user.setFirstName(firstName);
	      user.setLastName(secondName);
	      user.setPassword(password);
	      user.setEmail(email);
	      identityService.saveUser(user);
	      
	      Tenant tenant = identityService.newTenant(userName);
	      identityService.saveTenant(tenant);      
	   	  identityService.createTenantUserMembership(userName, userName);
	   
		
	}

}
