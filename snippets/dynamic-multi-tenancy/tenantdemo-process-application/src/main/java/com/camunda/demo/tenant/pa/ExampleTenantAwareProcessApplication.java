package com.camunda.demo.tenant.pa;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

import com.camunda.demo.tenant.manager.TenantManager;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class ExampleTenantAwareProcessApplication extends ServletProcessApplication {

//  private static final String PROCESS_DEFINITION_KEY = "tenantdemo-process-application";

  @PostDeploy
  public void deployProcessesToTenantEngines() {
    // copy the process definitions deployed to the default engine to all tenants
    for (ProcessEngine processEngine : BpmPlatform.getProcessEngineService().getProcessEngines()) {
      if (processEngine != BpmPlatform.getDefaultProcessEngine()) {
        TenantManager.deployDefaultProcessesToEngine(processEngine);
      }
    }
  }

}
