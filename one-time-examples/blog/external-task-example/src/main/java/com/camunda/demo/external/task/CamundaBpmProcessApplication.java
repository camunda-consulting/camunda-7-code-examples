package com.camunda.demo.external.task;

import static com.camunda.demo.environment.UserDataGenerator.createDefaultUsers;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

import com.camunda.demo.environment.LicenseHelper;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {


  @PostDeploy
  public void onDeploymentFinished(ProcessEngine processEngine) {
    LicenseHelper.setLicense(processEngine);
    createDefaultUsers(processEngine);
  }

}
