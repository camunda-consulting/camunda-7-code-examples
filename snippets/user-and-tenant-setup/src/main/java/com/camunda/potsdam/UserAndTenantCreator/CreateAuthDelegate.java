package com.camunda.potsdam.UserAndTenantCreator;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_REVOKE;
import static org.camunda.bpm.engine.authorization.Permissions.ACCESS;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;
import static org.camunda.bpm.engine.authorization.Permissions.READ;
import static org.camunda.bpm.engine.authorization.Permissions.UPDATE;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;
import static org.camunda.bpm.engine.authorization.Resources.PROCESS_DEFINITION;
import static org.camunda.bpm.engine.authorization.Resources.FILTER;
import static org.camunda.bpm.engine.authorization.Resources.TASK;
import static org.camunda.bpm.engine.authorization.Resources.*;


import java.security.acl.Permission;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;

public class CreateAuthDelegate implements JavaDelegate {

	@Override
    public void execute(DelegateExecution execution) throws Exception {
        String userName = (String) execution.getVariable("userName");
        String password = (String) execution.getVariable("password");
        
        final AuthorizationService authorizationService = execution.getProcessEngineServices().getAuthorizationService();
        final IdentityService identityService = execution.getProcessEngineServices().getIdentityService();
        
        
        AuthorizationEntity userTasklistAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
        userTasklistAuth.setUserId(userName);
        userTasklistAuth.addPermission(ACCESS);
        userTasklistAuth.setResourceId("tasklist");
        userTasklistAuth.setResource(APPLICATION);
        authorizationService.saveAuthorization(userTasklistAuth);
        
        AuthorizationEntity userCockpitAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
        userCockpitAuth.setUserId(userName);
        userCockpitAuth.addPermission(ACCESS);
        userCockpitAuth.setResourceId("cockpit");
        userCockpitAuth.setResource(APPLICATION);
        authorizationService.saveAuthorization(userCockpitAuth);
        
        // create ADMIN authorizations on all built-in resources
        for (Resource resource : Resources.values()) {
                if (resource != APPLICATION &&
                    resource != AUTHORIZATION &&
                    resource != TENANT &&
                    resource != TENANT_MEMBERSHIP &&
                    resource != USER &&
                    resource != GROUP &&
                    resource != GROUP_MEMBERSHIP) {
                    AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
                    userAdminAuth.setUserId(userName);
                    userAdminAuth.setResource(resource);
                    userAdminAuth.setResourceId(ANY);
                    userAdminAuth.addPermission(ALL);
                    authorizationService.saveAuthorization(userAdminAuth);
                }
        }

        // remove access to UserAndTenantCreator process
        AuthorizationEntity userCreateProcessAuth = new AuthorizationEntity(AUTH_TYPE_REVOKE);
        userCreateProcessAuth.setUserId(userName);
        userCreateProcessAuth.setResource(PROCESS_DEFINITION);
        userCreateProcessAuth.setResourceId("UserAndTenantCreator");
        userCreateProcessAuth.removePermission(ALL);
        authorizationService.saveAuthorization(userCreateProcessAuth);
    }
	    
		
}

