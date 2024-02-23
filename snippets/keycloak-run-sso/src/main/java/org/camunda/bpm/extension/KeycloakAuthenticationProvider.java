package org.camunda.bpm.extension;

import jakarta.servlet.http.HttpServletRequest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * OAuth2 Authentication Provider for usage with Keycloak and KeycloakIdentityProviderPlugin.
 */
public class KeycloakAuthenticationProvider extends ContainerBasedAuthenticationProvider {
  private static final Logger LOG = LoggerFactory.getLogger(KeycloakAuthenticationProvider.class);
  @Override
  public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {
    LOG.info("Extracting authenticated user");
    // Extract user-name-attribute of the OAuth2 token
    Authentication authentication = SecurityContextHolder
        .getContext().getAuthentication();
    if (!(authentication instanceof OAuth2AuthenticationToken) || !(authentication.getPrincipal() instanceof OidcUser)) {
      LOG.info("Authentication is not of type OAuth but {}", authentication.getClass());
      return AuthenticationResult.unsuccessful();
    }
    LOG.info("{}",authentication);
    String userId = ((OidcUser)authentication.getPrincipal()).getName();
    if (!StringUtils.hasText(userId)) {
      LOG.info("UserId is not set");
      return AuthenticationResult.unsuccessful();
    }

    // Authentication successful
    AuthenticationResult authenticationResult = new AuthenticationResult(userId, true);
    authenticationResult.setGroups(getUserGroups(userId, engine));

    return authenticationResult;
  }

  private List<String> getUserGroups(String userId, ProcessEngine engine){
    List<String> groupIds = new ArrayList<>();
    // query groups using KeycloakIdentityProvider plugin
    engine.getIdentityService().createGroupQuery().groupMember(userId).list()
          .forEach( g -> groupIds.add(g.getId()));
    return groupIds;
  }

}