package com.camunda.potsdam.UserAndTenantCreator;

import java.util.HashMap;
import java.util.Map;


import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;


/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  private static final String PROCESS_DEFINITION_KEY = "UserAndTenantCreator";

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {
	  
	  User user = processEngine.getIdentityService().createUserQuery().userId("niall").singleResult();
	  if(user != null) {
		  return;
	  }
	  
	  // Create admin user.... 
	  createInitialUserInternal(processEngine);
	  // create camunda admin group.
	  //ensureCamundaAdminGroupExists(processEngine);
	  // give admin all resources. 
	  // example: 
	  // https://github.com/camunda/camunda-bpm-webapp/blob/master/src/main/java/org/camunda/bpm/admin/impl/web/SetupResource.java 
	  
	  
	  
    // start an initial process instance
//    Map<String, Object> variables = new HashMap<String, Object>();
//    variables.put("name", "John");
//    
//    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
  }
  protected void createInitialUserInternal(ProcessEngine processEngine) {
	  
	  final IdentityService identityService = processEngine.getIdentityService();
		
	  String userName = "niall";
	    // make sure we can process this request at this time
	  User user = identityService.newUser(userName);
      user.setFirstName("Niall");
      user.setLastName("Deehan");
      user.setPassword("Tassilo");
      user.setEmail("nope");
      identityService.saveUser(user);

	    // crate the camunda admin group
	    ensureCamundaAdminGroupExists(processEngine);

	    // create group membership (add new user to admin group)
	    processEngine.getIdentityService()
	      .createMembership(user.getId(), Groups.CAMUNDA_ADMIN);
	}
  

  protected void ensureCamundaAdminGroupExists(ProcessEngine processEngine) {

    final IdentityService identityService = processEngine.getIdentityService();
    final AuthorizationService authorizationService = processEngine.getAuthorizationService();

    // create group
    if(identityService.createGroupQuery().groupId(Groups.CAMUNDA_ADMIN).count() == 0) {
      Group camundaAdminGroup = identityService.newGroup(Groups.CAMUNDA_ADMIN);
      camundaAdminGroup.setName("camunda BPM Administrators");
      camundaAdminGroup.setType(Groups.GROUP_TYPE_SYSTEM);
      identityService.saveGroup(camundaAdminGroup);
    }

    // create ADMIN authorizations on all built-in resources
    for (Resource resource : Resources.values()) {
      if(authorizationService.createAuthorizationQuery().groupIdIn(Groups.CAMUNDA_ADMIN).resourceType(resource).resourceId(ANY).count() == 0) {
        AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
        userAdminAuth.setGroupId(Groups.CAMUNDA_ADMIN);
        userAdminAuth.setResource(resource);
        userAdminAuth.setResourceId(ANY);
        userAdminAuth.addPermission(ALL);
        authorizationService.saveAuthorization(userAdminAuth);
      }
    }

}

}
