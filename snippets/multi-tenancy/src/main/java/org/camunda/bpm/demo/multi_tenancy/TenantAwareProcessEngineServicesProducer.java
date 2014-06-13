package org.camunda.bpm.demo.multi_tenancy;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.cdi.impl.ProcessEngineServicesProducer;

public class TenantAwareProcessEngineServicesProducer extends
		ProcessEngineServicesProducer {

  @Inject
  private Tenant tenant;

  @Override
  @Specializes
  @Produces
  @Named
  @ApplicationScoped
  public ProcessEngine processEngine() {
    String processEngineName = tenant.getId();
    if (processEngineName != null) {
		ProcessEngine processEngine = BpmPlatform.getProcessEngineService().getProcessEngine(processEngineName);
	    if (processEngine == null) {
	    	processEngine = ProcessEngines.getProcessEngine(processEngineName, false);
	    }
	    if (processEngine != null) {
	    	return processEngine;
	    } else {
	    	throw new ProcessEngineException("No process engine found for tenant id '" + processEngineName + "'.");
	    }
    } else {
    	throw new ProcessEngineException("No tenant id specified. A process engine can only be retrieved based on a tenant.");
    }
  }

}
