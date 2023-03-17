package com.camunda.example.oauth2.config;

import com.nimbusds.jose.shaded.json.JSONArray;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class SpringSecurityOAuth2AuthenticationProvider
    extends ContainerBasedAuthenticationProvider {
  private static final Logger LOG =
      LoggerFactory.getLogger(SpringSecurityOAuth2AuthenticationProvider.class);

  @Override
  public AuthenticationResult extractAuthenticatedUser(
      HttpServletRequest request, ProcessEngine engine) {
    // Get Authentication from SecurityContextHolder
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // fail if no authentication exists
    if (authentication == null) {
      LOG.debug("Authentication failed, no authentication in security context");
      return AuthenticationResult.unsuccessful();
    }
    // get name from authentication
    String name = authentication.getName();
    // fail if name is not set
    if (name == null || name.isEmpty()) {
      LOG.debug("Authentication failed, name is not set in authentication");
      return AuthenticationResult.unsuccessful();
    }
    // rely on previous authentication mechanism
    AuthenticationResult authenticationResult = AuthenticationResult.successful(name);
    // set correct groups
    authenticationResult.setGroups(getRoles(authentication, engine));
    return authenticationResult;
  }

  /**
   * Extract all possible roles of the user
   *
   * @param authentication the authentication from spring security
   * @param processEngine the current process engine context
   * @return user roles as List of Strings
   */
  protected List<String> getRoles(Authentication authentication, ProcessEngine processEngine) {
    LOG.debug("Gathering roles from token and process engine");
    List<String> processEngineGroups =
        getProcessEngineGroups(authentication.getName(), processEngine);
    List<String> tokenRoles = getTokenRoles(authentication);
    List<String> result =
        Stream.of(processEngineGroups, tokenRoles).flatMap(List::stream).distinct().toList();
    LOG.debug("Roles found: {}", result);
    return result;
  }

  private List<String> getProcessEngineGroups(String name, ProcessEngine processEngine) {
    LOG.debug("Querying process engine for groups with user '{}'", name);
    return processEngine.getIdentityService().createGroupQuery().groupMember(name).list().stream()
        .map(Group::getName)
        .toList();
  }

  private List<String> getTokenRoles(Authentication authentication) {
    LOG.debug("Querying token for roles");
    JSONArray rolesArray = getUserFromAuthentication(authentication).getAttribute("roles");
    if (rolesArray == null) {
      return Collections.emptyList();
    }
    return rolesArray.stream().map(String.class::cast).collect(Collectors.toList());
  }

  /**
   * Extract User from Authentication
   *
   * @param authentication the authentication
   * @return the OidcUser
   */
  @SuppressWarnings("unchecked")
  private <T extends OAuth2AuthenticatedPrincipal> T getUserFromAuthentication(
      Authentication authentication) {
    return (T) authentication.getPrincipal();
  }
}
