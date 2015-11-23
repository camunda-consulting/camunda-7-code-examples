package com.camunda.demo.environment;

import org.camunda.bpm.application.ProcessApplicationReference;
import org.camunda.bpm.engine.ProcessEngine;

public class ProcessApplicationDemoSetup {

  public static void executeDefaultSetup(ProcessEngine engine, String processDefinitionKey) {
    executeDefaultSetup(engine, processDefinitionKey, null);
  }
  
  public static void executeDefaultSetup(ProcessEngine engine, String processDefinitionKey, ProcessApplicationReference reference) {
    synchronized (engine) {
      LicenseHelper.setLicense(engine);
      DemoDataGenerator.autoGenerateFor(engine, processDefinitionKey, 14, reference);
      UserDataGenerator.createDefaultUsers(engine);
    }
  }
}
