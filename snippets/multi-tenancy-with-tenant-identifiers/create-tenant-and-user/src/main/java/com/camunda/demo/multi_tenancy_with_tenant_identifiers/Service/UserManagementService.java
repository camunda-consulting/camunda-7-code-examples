package com.camunda.demo.multi_tenancy_with_tenant_identifiers.Service;

import java.util.logging.Logger;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.identity.User;

public final class UserManagementService {
	
	  private final static Logger LOGGER = Logger.getLogger(UserManagementService.class.getName());

	  public static String addUser(IdentityService identityService, String userName, String password, String firstname, String lastname, String email) {
		    if (identityService.isReadOnly()) {
		      LOGGER.severe("Identity service provider is Read Only, could not create user.");
		      return null;
		    }
		    if (identityService.createUserQuery().userId(userName).count() > 0) {
		      LOGGER.info("User " + userName + " already exists, could not create that user again.");
		      return null;
		    }
		    User user = identityService.newUser(userName);
		    user.setFirstName(firstname);
		    user.setLastName(lastname);
		    user.setPassword(password);
		    user.setEmail(email);
		    identityService.saveUser(user);
		    
		    return user.getId();
		  }

	  
	  public static void createGrantUserAuthorization(AuthorizationService authorizationService, String[] userIds, Permission[] permissions, Resource resource, String[] resourceIds) {
		    for (String userId : userIds) {
		      for (String resourceId : resourceIds) {

		        if (authorizationService.createAuthorizationQuery().userIdIn(userId).resourceId(resourceId).count() == 0) {
		          Authorization createAuth = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
		          createAuth.setUserId(userId);
		          for (Permission permission : permissions) {
		            createAuth.addPermission(permission);
		          }
		          createAuth.setResourceId(resourceId);
		          createAuth.setResource(resource);
		          authorizationService.saveAuthorization(createAuth);
		        }
		      }
		    }
		  }
}
