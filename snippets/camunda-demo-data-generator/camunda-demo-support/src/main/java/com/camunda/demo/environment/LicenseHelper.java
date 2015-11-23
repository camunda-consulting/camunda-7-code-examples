package com.camunda.demo.environment;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;

public class LicenseHelper {

  private final static Logger LOGGER = Logger.getLogger(LicenseHelper.class.getName());

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
    LOGGER.info("Set license to provided license key");

    processEngine.getManagementService().setProperty("camunda-license-key", licenseKey);
  }
}
