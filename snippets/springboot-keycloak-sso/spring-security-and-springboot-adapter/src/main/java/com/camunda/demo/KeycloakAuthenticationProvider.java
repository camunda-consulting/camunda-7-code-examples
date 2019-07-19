package com.camunda.demo;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

public class KeycloakAuthenticationProvider extends ContainerBasedAuthenticationProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakAuthenticationProvider.class);

  @Override
  public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {

    LOGGER.info("Extracting auth details from keycloak");

    KeycloakSecurityContext authentication = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    if (authentication == null) {
      return AuthenticationResult.unsuccessful();
    }

    String name = authentication.getIdToken().getPreferredUsername();
    if (name == null || name.isEmpty()) {
      return AuthenticationResult.unsuccessful();
    }

    AuthenticationResult authenticationResult = new AuthenticationResult(name, true);

    Set<String> roles = authentication.getToken().getRealmAccess().getRoles();
    authenticationResult.setGroups(new ArrayList<>(roles));

    return authenticationResult;
  }
}
