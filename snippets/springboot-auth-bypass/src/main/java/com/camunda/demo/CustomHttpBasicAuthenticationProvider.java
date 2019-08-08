package com.camunda.demo;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider;

@Slf4j
public class CustomHttpBasicAuthenticationProvider extends HttpBasicAuthenticationProvider {

  // TODO: do not hardcode those credentials! Use e.g. spring boot properties or a db table instead
  private String bypassUserId = "foo";
  private String bypassUserPwd = "bar";

  @Override
  protected boolean isAuthenticated(ProcessEngine engine, String userName, String password) {

    log.warn("Bypass credentials are: " + bypassUserId + " : " + bypassUserPwd);

    log.info("Check authentication for {}", userName);
    if (userName.equals(bypassUserId) && password.equals(bypassUserPwd)) {
      log.warn("Authenticated as bypass user (rest api)");
      return true;
    }
    return engine.getIdentityService().checkPassword(userName, password);
  }

}
