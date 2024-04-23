package org.camunda.consulting.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collections;
import java.util.List;

public class UserExtractionUtility {
  private static final Logger LOG = LoggerFactory.getLogger(UserExtractionUtility.class);

  public static OidcUser getOidcUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof OidcUser) {
      return (OidcUser) authentication.getPrincipal();
    }
    LOG.debug("No OidcUser found in current authentication context.");
    return null;
  }

  public static List<String> extractGroups(OidcUser oidcUser) {
    if (oidcUser != null) {
      return oidcUser.getClaimAsStringList("groups");
    }
    return Collections.emptyList();
  }

  public static String extractGivenName(OidcUser oidcUser) {
    if (oidcUser != null) {
      return oidcUser.getGivenName();
    }
    return null;
  }
}