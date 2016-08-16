package com.camunda.demo.multi_tenancy_with_tenant_identifiers;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  private static final String PROCESS_DEFINITION_KEY = "multi-tenancy-with-tenant-identifiers";

  /**
   * In a @PostDeploy Hook you can interact with the process engine and access 
   * the processes the application has deployed. 
   */
  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {

  }

}
