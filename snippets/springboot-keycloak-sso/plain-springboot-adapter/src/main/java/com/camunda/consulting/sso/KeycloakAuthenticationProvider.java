package com.camunda.consulting.sso;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

/**
 * OAuth2 Authentication Provider for usage with Keycloak and
 * KeycloakIdentityProviderPlugin.
 */
public class KeycloakAuthenticationProvider extends ContainerBasedAuthenticationProvider {
  private static final Logger log = LoggerFactory.getLogger(KeycloakAuthenticationProvider.class);

  @Override
  public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {

    // Extract user-name-attribute of the OAuth2 token
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof OAuth2AuthenticationToken)
        || !(authentication.getPrincipal() instanceof OidcUser)) {
      return AuthenticationResult.unsuccessful();
    }
    log.debug("Authenticaton: {}", authentication);
    String userId = ((OidcUser) authentication.getPrincipal()).getName();
    log.debug("User ID: {}", userId);
    if (StringUtils.isEmpty(userId)) {
      return AuthenticationResult.unsuccessful();
    }

    // Authentication successful
    AuthenticationResult authenticationResult = new AuthenticationResult(userId, true);
    authenticationResult.setGroups(this.getUserGroups(userId, engine));

    return authenticationResult;
  }

  private List<String> getUserGroups(String userId, ProcessEngine engine) {
    List<String> groupIds = new ArrayList<>();
    engine.getIdentityService().createGroupQuery().groupMember(userId).list().forEach(g -> groupIds.add(g.getId()));
    return groupIds;
  }

}
