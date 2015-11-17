package com.camunda.demo.environment;

import org.camunda.bpm.engine.ProcessEngine;

public class LicenseHelper {

  /**
   * Set license from user home
   */
  public static void setLicense(ProcessEngine processEngine) {
    String license = UserProperties.readProperty("camunda.license");
    if (license != null) {
      setLicense(processEngine, license);
    }
  }

  public static void setLicense(ProcessEngine processEngine, String licenseKey) {
    processEngine.getManagementService().setProperty("camunda-license-key", licenseKey);

  }
}
