package com.camunda.demo.insuranceapplication;

import static com.camunda.demo.environment.DefaultFilter.FILTER_allTasksFilter;
import static com.camunda.demo.environment.DefaultFilter.FILTER_groupTasksFilter;
import static com.camunda.demo.environment.DefaultFilter.FILTER_myTasks;
import static com.camunda.demo.environment.UserDataGenerator.addFilterGroupAuthorization;
import static com.camunda.demo.environment.UserDataGenerator.addFilterUserAuthorization;
import static com.camunda.demo.environment.UserDataGenerator.addGroup;
import static com.camunda.demo.environment.UserDataGenerator.addUser;
import static com.camunda.demo.environment.UserDataGenerator.createGrantGroupAuthorization;
import static com.camunda.demo.environment.UserDataGenerator.createGrantUserAuthorization;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;

import com.camunda.demo.environment.ProcessApplicationDemoSetup;

@ProcessApplication
public class InsuranceProcessApplication extends ServletProcessApplication {

  @PostDeploy
  public void setupEnvironmentForDemo(ProcessEngine engine) {
    ProcessApplicationDemoSetup.executeDefaultSetup(engine, "insurance-application", getReference());
    
    addUser(engine, "ben", "ben", "Ben", "Brooks");
    addGroup(engine, "clerk", "Clerk", "ben");  
    addFilterGroupAuthorization(engine, "clerk", FILTER_myTasks, FILTER_groupTasksFilter, FILTER_allTasksFilter);   

    addUser(engine, "lisa", "lisa", "Lisa", "Floyd");
    addGroup(engine, "management", "Management", "lisa");
      // FILTER_Ueberfaellig, FILTER_Wiedervorlage, FILTER_PostkorbManagement
    addFilterUserAuthorization(engine, "lisa", FILTER_myTasks, FILTER_groupTasksFilter, FILTER_allTasksFilter);
    

    createGrantGroupAuthorization(engine, //
        new String[] { "clerk" }, //
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.UPDATE_INSTANCE }, //
        Resources.PROCESS_DEFINITION, //
        new String[] { "insurance-application" });

    // Admin Lisa
    createGrantUserAuthorization(engine, //
        new String[] { "lisa" }, //
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE }, //
        Resources.PROCESS_DEFINITION, //
        new String[] { "insurance-application" });
    createGrantUserAuthorization(engine, 
        new String[]{"lisa"},
        new Permission[]{Permissions.READ, Permissions.READ_HISTORY},
        Resources.DECISION_DEFINITION,
        new String[] {"riskAssessment"});
    createGrantUserAuthorization(engine, //
        new String[] { "lisa" }, //
        new Permission[] { Permissions.READ, Permissions.UPDATE }, //
        Resources.TASK, //
        new String[] { "*" });
    createGrantUserAuthorization(engine, //
        new String[] { "lisa" }, //
        new Permission[] { Permissions.ALL }, //
        Resources.DEPLOYMENT, //
        new String[] { "*" });
    createGrantUserAuthorization(engine, //
        new String[] { "lisa" }, //
        new Permission[] { Permissions.ACCESS }, //
        Resources.APPLICATION, //
        new String[] { "cockpit" });
  }

}
