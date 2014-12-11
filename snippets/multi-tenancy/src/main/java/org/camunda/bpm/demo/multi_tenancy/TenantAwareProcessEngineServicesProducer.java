package org.camunda.bpm.demo.multi_tenancy;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.cdi.impl.ProcessEngineServicesProducer;

@Specializes
public class TenantAwareProcessEngineServicesProducer extends ProcessEngineServicesProducer {

  @Inject
  private Tenant tenant;

  @Override
  @Named
  @Produces
  @RequestScoped
  public ProcessEngine processEngine() {
    String processEngineName = tenant.getId();
    if (processEngineName != null) {
      ProcessEngine processEngine = BpmPlatform.getProcessEngineService().getProcessEngine(processEngineName);
	    if (processEngine != null) {
	    	return processEngine;
	    } else {
	    	throw new ProcessEngineException("No process engine found for tenant id '" + processEngineName + "'.");
	    }
    } else {
    	throw new ProcessEngineException("No tenant id specified. A process engine can only be retrieved based on a tenant.");
    }
  }

  @Override @Produces @Named @RequestScoped public RuntimeService runtimeService() { return processEngine().getRuntimeService(); }

  @Override @Produces @Named @RequestScoped public TaskService taskService() { return processEngine().getTaskService(); }

  @Override @Produces @Named @RequestScoped public RepositoryService repositoryService() { return processEngine().getRepositoryService(); }

  @Override @Produces @Named @RequestScoped public FormService formService() { return processEngine().getFormService(); }

  @Override @Produces @Named @RequestScoped public HistoryService historyService() { return processEngine().getHistoryService(); }

  @Override @Produces @Named @RequestScoped public IdentityService identityService() { return processEngine().getIdentityService(); }

  @Override @Produces @Named @RequestScoped public ManagementService managementService() { return processEngine().getManagementService(); }

}
