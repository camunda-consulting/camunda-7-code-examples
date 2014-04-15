package com.camunda.demo.tenant.manager;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.camunda.bpm.BpmPlatform;

/**
 * Webservice for testing (done in Tomcat via JAX-WS, see http://maksim.sorokin.dk/it/2011/01/20/jax-ws-web-services-maven-tomcat/) 
 */
@WebService
public class ManagementWebservice {

  @WebMethod
  public void addTenant(String name) {
    new TenantManager().addTenant(name);
  }
  
  @WebMethod
  public void startProcess(String tenant) {
    BpmPlatform.getProcessEngineService().getProcessEngine(tenant).getRuntimeService().startProcessInstanceByKey("tenantdemo");
  }
  
  @WebMethod
  public void deployProcessDefinition(String resourceName, String processDefinitionXmlAsString) {
    BpmPlatform.getDefaultProcessEngine().getRepositoryService().createDeployment() //
      .addString(resourceName, processDefinitionXmlAsString) //
      .enableDuplicateFiltering() //
      .deploy();
    
    // TODO: How do we now register the right WAR for it? How do we address the ProcessApplication?
  }
}
 