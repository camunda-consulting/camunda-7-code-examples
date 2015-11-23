package com.camunda.demo.versicherungsneuantrag;

import static com.camunda.demo.environment.DefaultFilter.*;
import static com.camunda.demo.environment.UserDataGenerator.*;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;

import com.camunda.demo.environment.DemoDataGenerator;
import com.camunda.demo.environment.LicenseHelper;
import com.camunda.demo.environment.ProcessApplicationDemoSetup;

@ProcessApplication
public class VersicherungsneuantragProcessApplication extends ServletProcessApplication {

  @PostDeploy
  public void setupEnvironmentForDemo(ProcessEngine engine) {
    ProcessApplicationDemoSetup.executeDefaultSetup(engine, "versicherungsneuantrag", getReference());
    
    addUser(engine, "marc", "marc", "Marc", "Mustermann");
    addGroup(engine, "sachbearbeiter", "Sachbearbeiter", "marc");
    addFilterGroupAuthorization(engine, "sachbearbeiter", FILTER_MeineAufgaben, FILTER_GruppenAufgaben, FILTER_Ueberfaellig, FILTER_Wiedervorlage);

    addUser(engine, "hugo", "hugo", "Hugo", "Halbmann");
    addGroup(engine, "gruppenleiter", "Gruppenleiter", "hugo");
    addFilterGroupAuthorization(engine, "gruppenleiter", FILTER_MeineAufgaben, FILTER_GruppenAufgaben, FILTER_Ueberfaellig, FILTER_Wiedervorlage);

    addUser(engine, "susi", "susi", "Susi", "Sonnenschein");
    addGroup(engine, "underwriter", "Underwriter", "susi");
    addFilterGroupAuthorization(engine, "underwriter", FILTER_MeineAufgaben, FILTER_GruppenAufgaben, FILTER_Ueberfaellig, FILTER_Wiedervorlage);

    addUser(engine, "paul", "paul", "Paul", "Pohl");
    addGroup(engine, "management", "Management", "paul");
    addFilterUserAuthorization(engine, "paul", FILTER_MeineAufgaben, FILTER_GruppenAufgaben, FILTER_Ueberfaellig, FILTER_Wiedervorlage, FILTER_PostkorbManagement, FILTER_alleAufgaben);
    
    createGrantGroupAuthorization(engine, 
        new String[]{"sachbearbeiter", "underwriter", "gruppenleiter"},
        new Permission[]{Permissions.READ, Permissions.READ_HISTORY, Permissions.UPDATE_INSTANCE},
        Resources.PROCESS_DEFINITION,
        new String[] {"versicherungsneuantragMitDokumentenerstellung", "versicherungsneuantrag"});
    
    // Admin Paul
    createGrantUserAuthorization(engine, 
        new String[]{"paul"},
        new Permission[]{Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE},
        Resources.PROCESS_DEFINITION,
        new String[] {"versicherungsneuantragMitDokumentenerstellung", "versicherungsneuantrag", "dokumentAnfordern"});
    createGrantUserAuthorization(engine, 
        new String[]{"paul"},
        new Permission[]{Permissions.READ, Permissions.UPDATE},
        Resources.TASK,
        new String[] {"*"});    
    createGrantUserAuthorization(engine, 
        new String[]{"paul"},
        new Permission[]{Permissions.ALL},
        Resources.DEPLOYMENT,
        new String[] {"*"});    
    createGrantUserAuthorization(engine, 
        new String[]{"paul"},
        new Permission[]{Permissions.ACCESS},
        Resources.APPLICATION,
        new String[] {"cockpit"});    
  }

}
