package com.camunda.consulting.springexample.config;

import java.util.logging.Logger;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.PreUndeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.spring.application.SpringServletProcessApplication;

@ProcessApplication
public class ProcessApplicationBean extends SpringServletProcessApplication {

  
  private static final Logger log = Logger.getLogger(ProcessApplicationBean.class.getName());
  
  @PostDeploy
  public void startup(ProcessEngine engine) {
    log.info("process engine name:" + engine.getName());
  }
  
  @PreUndeploy
  public void stopAndUndeploy() {
    log.info("stop and shutdown process application now");
  }
}
