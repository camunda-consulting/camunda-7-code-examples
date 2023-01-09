package com.camunda.consulting;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
public class MyMainApplication {
  
  private static final Logger lOG = LoggerFactory.getLogger(MyMainApplication.class);
  
  public static ProcessEngine processEngine;
  public static MyProcessApplication processApplication;

  @PostConstruct
  public void startEngine() {
    lOG.info("Starting engine");
    
    // instantiate the process application
    processApplication = new MyProcessApplication();

    // deploy the process application
    processApplication.deploy();

    // interact with the process engine
    processEngine = BpmPlatform.getDefaultProcessEngine();
  }
  
  @PreDestroy
  public void stopEngine() {
    lOG.info("Stopping engine");
    // undeploy the process application
    processApplication.undeploy();
  }
  
}
