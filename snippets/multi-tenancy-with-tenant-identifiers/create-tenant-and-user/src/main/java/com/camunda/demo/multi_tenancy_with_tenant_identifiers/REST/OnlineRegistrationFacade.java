package com.camunda.demo.multi_tenancy_with_tenant_identifiers.REST;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.Variables;
import com.camunda.demo.multi_tenancy_with_tenant_identifiers.Model.NewRegistration;
import com.camunda.demo.multi_tenancy_with_tenant_identifiers.Model.PlatformUser;


@Path("/")
public class OnlineRegistrationFacade {
  

  @POST
  @Path("create-tenant-specific-user/newrequest")
  @Consumes(MediaType.APPLICATION_JSON)
  public void submitNewRegistration(NewRegistration newRegistration) {
	  
	  LinkedList<PlatformUser> listOfCockpitUsers = new LinkedList<PlatformUser>();
	  LinkedList<PlatformUser> listOfTasklistUsers = new LinkedList<PlatformUser>();

	  if(newRegistration.getUser().getUserType().equals("tasklist")){
		  listOfTasklistUsers.add(newRegistration.getUser());
	  }
	  
	  if(newRegistration.getUser().getUserType().equals("cockpit")){
		  listOfCockpitUsers.add(newRegistration.getUser());
	  }
	  	  
	  // currently only one user can be passed with the request. Possible enhancement for 
	  // future versions: add multiple users at once
	
	  BpmPlatform.getDefaultProcessEngine().getIdentityService().setAuthentication("demo", Collections.singletonList("camunda-admin"), Collections.singletonList(""));  

	  BpmPlatform.getDefaultProcessEngine().getRuntimeService().startProcessInstanceByKey( //
        "create-tenant-specific-user", 
       	Variables.createVariables()
    	.putValue("tenantName", newRegistration.getTenantName())
    	.putValue("listOfCockpitUsers", listOfCockpitUsers)
    	.putValue("listOfTasklistUsers", listOfTasklistUsers)
    	.putValue("allowedProcessDefinitions", newRegistration.getAllowedProcessDefinitions()));  

  }

}
