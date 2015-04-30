package org.camunda.bpm.unittest;

import java.util.logging.Logger;

public class TestAuthenticationBean {
  
  private static final Logger log = Logger.getLogger(TestAuthenticationBean.class.getName());

  public String returnUserId(String userId) {
    log.info("return user id: " + userId);
    return userId;
  }
}
