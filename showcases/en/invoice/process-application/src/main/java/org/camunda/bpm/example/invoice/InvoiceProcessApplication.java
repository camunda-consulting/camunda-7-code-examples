package org.camunda.bpm.example.invoice;

import static com.camunda.demo.environment.DefaultFilter.FILTER_allTasksFilter;
import static com.camunda.demo.environment.DefaultFilter.FILTER_groupTasksFilter;
import static com.camunda.demo.environment.DefaultFilter.FILTER_management;
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
public class InvoiceProcessApplication extends ServletProcessApplication {

  @PostDeploy
  public void setupEnvironmentForDemo(ProcessEngine engine) {
    ProcessApplicationDemoSetup.executeDefaultSetup(engine, "incoming-invoice", getReference()); 
    
    addUser(engine, "john", "john", "John", "Doe");
    addGroup(engine, "team-assistence", "Team Assistence", "john");
    addFilterGroupAuthorization(engine, "team-assistence", FILTER_myTasks, FILTER_groupTasksFilter);

    addUser(engine, "mary", "mary", "Mary", "Anne");
    addGroup(engine, "accounting", "Accounting", "mary");
    addFilterGroupAuthorization(engine, "accounting", FILTER_myTasks, FILTER_groupTasksFilter);

    addUser(engine, "peter", "peter", "Peter", "Meter");
    addGroup(engine, "management", "Management", "peter");
    addFilterUserAuthorization(engine, "peter", FILTER_myTasks, FILTER_groupTasksFilter, FILTER_management, FILTER_allTasksFilter);
    
    createGrantGroupAuthorization(engine, //
        new String[]{"team-assistence", "accounting"}, //
        new Permission[]{Permissions.CREATE}, //
        Resources.PROCESS_INSTANCE, //
        new String[] {"*"});
    createGrantGroupAuthorization(engine, // 
        new String[]{"team-assistence"}, //
        new Permission[]{Permissions.CREATE_INSTANCE, Permissions.READ}, //
        Resources.PROCESS_DEFINITION, //
        new String[] {"incoming-invoice"});  
    createGrantGroupAuthorization(engine, // 
        new String[]{"accounting"}, //
        new Permission[]{Permissions.READ}, //
        Resources.PROCESS_DEFINITION, //
        new String[] {"incoming-invoice"});
    createGrantUserAuthorization(engine, // 
        new String[]{"peter"}, //
        new Permission[]{Permissions.READ, Permissions.READ_INSTANCE, Permissions.READ_HISTORY}, //
        Resources.PROCESS_DEFINITION, //
        new String[] {"incoming-invoice"});
    createGrantUserAuthorization(engine, // 
        new String[]{"peter"}, //
        new Permission[]{Permissions.READ, Permissions.UPDATE}, //
        Resources.TASK, //
        new String[] {"*"});       
    createGrantUserAuthorization(engine, // 
        new String[]{"peter"}, //
        new Permission[]{Permissions.ACCESS}, //
        Resources.APPLICATION, //
        new String[] {"cockpit"});   
  }

}
