package com.camunda.consulting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthorizationConfigTests {

  @Autowired
  AuthorizationService authService;

  @Test
  public void shouldParseAuthorizations() {
    boolean userAuthorized = this.authService
      .isUserAuthorized("peter", List.of(), Permissions.ACCESS, Resources.APPLICATION);
    assertTrue(userAuthorized);
    userAuthorized = this.authService
      .isUserAuthorized("peter", List.of(), Permissions.CREATE, Resources.PROCESS_INSTANCE);
    assertFalse(userAuthorized);
  }
}
