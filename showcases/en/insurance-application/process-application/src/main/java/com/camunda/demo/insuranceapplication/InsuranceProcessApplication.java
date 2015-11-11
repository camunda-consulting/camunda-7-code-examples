package com.camunda.demo.insuranceapplication;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

import com.camunda.demo.environment.DemoDataGenerator;

@ProcessApplication
public class InsuranceProcessApplication extends ServletProcessApplication {

  @PostDeploy
  public void startFirstProcess(ProcessEngine processEngine) {
    DemoDataGenerator.autoGenerateFor(processEngine, "insurance-application", getReference());
  }
  
}
