package com.camunda.consulting.sso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

/**
 * Keycloak Logout Handler.
 */
@Service
public class KeycloakLogoutHandler implements LogoutSuccessHandler {

  /** This class' logger. */
  private static final Logger LOG = LoggerFactory.getLogger(KeycloakLogoutHandler.class);

  /** Redirect strategy. */
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  /** Keycloak's logout URI. */
  private String oauth2UserLogoutUri;

  /**
   * Default constructor.
   * 
   * @param oauth2UserAuthorizationUri
   *          configured keycloak authorization URI
   */
  public KeycloakLogoutHandler(
      @Value("${spring.security.oauth2.client.provider.keycloak.authorization-uri:}") String oauth2UserAuthorizationUri) {
    if (!StringUtils.isEmpty(oauth2UserAuthorizationUri)) {
      // in order to get the valid logout uri: simply replace "/auth" at the end
      // of
      // the user authorization uri with "/logout"
      this.oauth2UserLogoutUri = oauth2UserAuthorizationUri.replace("openid-connect/auth", "openid-connect/logout");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    if (!StringUtils.isEmpty(this.oauth2UserLogoutUri)) {
      // Calculate redirect URI for Keycloak, something like
      // http://<host:port>/camunda
      String requestUrl = request.getRequestURL().toString();
      String redirectUri = requestUrl.substring(0, requestUrl.indexOf("/camunda"));
      // Complete logout URL
      String logoutUrl = this.oauth2UserLogoutUri + "?redirect_uri=" + redirectUri;

      // Do logout by redirecting to Keycloak logout
      LOG.debug("Redirecting to logout URL {}", logoutUrl);
      this.redirectStrategy.sendRedirect(request, response, logoutUrl);
    }
  }

}
