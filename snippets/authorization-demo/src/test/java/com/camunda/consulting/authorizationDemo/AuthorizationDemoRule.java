package com.camunda.consulting.authorizationDemo;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.runners.model.FrameworkMethod;

public class AuthorizationDemoRule extends ProcessEngineRule {
  
  private static final Logger log = Logger.getLogger(AuthorizationDemoRule.class.getName());

  @Override
  public void finished(FrameworkMethod method) {
    try {
      identityService.setAuthenticatedUserId("admin");
      super.finished(method);
    } catch (ProcessEngineException e) {
      log.info("During undeploy: " + e.getMessage());
    }
  }

}
