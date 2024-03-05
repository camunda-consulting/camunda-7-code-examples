package org.camunda.consulting;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.ContainerBasedAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
public class CustomAuthenticationProvider extends ContainerBasedAuthenticationProvider {

  private static final Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

  @Override
  public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    if (authentication == null) {
      return AuthenticationResult.unsuccessful();
    }

    OidcUser user = (OidcUser) authentication.getPrincipal();
    String name = user.getGivenName();
    //name = "demo";
    List<String> groups = user.getClaimAsStringList("groups");

    LOG.debug("extracted user: {}", name);
    if (name == null || name.isEmpty()) {
      return AuthenticationResult.unsuccessful();
    }

    AuthenticationResult authenticationResult = new AuthenticationResult(name, true);
    authenticationResult.setGroups(groups);

    return authenticationResult;
  }

}
