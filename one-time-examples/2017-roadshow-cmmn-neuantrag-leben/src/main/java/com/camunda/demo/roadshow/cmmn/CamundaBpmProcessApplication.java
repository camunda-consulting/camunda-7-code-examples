package com.camunda.demo.roadshow.cmmn;

import static com.camunda.consulting.util.FilterGenerator.*;
import static com.camunda.consulting.util.UserGenerator.*;
import static com.camunda.consulting.util.UserGenerator.addGroup;
import static com.camunda.consulting.util.UserGenerator.addUser;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

import com.camunda.consulting.util.FilterGenerator;
import com.camunda.consulting.util.LicenseHelper;
import com.camunda.consulting.util.UserGenerator;

/**
 * Process Application exposing this application's resources the process engine. 
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

  @PostDeploy
  public void onDeploymentFinished(ProcessEngine engine) {
    LicenseHelper.setLicense(engine);
    UserGenerator.createDefaultUsers(engine);
    addFilterUserAuthorization(engine, "demo", FILTER_alleAufgaben, FILTER_Ueberfaellig);
  }

}
