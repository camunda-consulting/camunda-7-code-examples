package com.camunda.optimize.plugin.sso;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.camunda.optimize.plugin.security.authentication.AuthenticationExtractor;
import org.camunda.optimize.plugin.security.authentication.AuthenticationResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeycloakAuthenticationProvider implements AuthenticationExtractor {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request) {
		AuthenticationResult result = new AuthenticationResult();
		String user = request.getHeader("KEYCLOAK_USERNAME");

		if (user == null ||  user.isEmpty()) {
			logger.info("Did not find user.");
			result.setAuthenticated(false);
			return result;
		} else {
			logger.info("User logged info", user);
			result.setAuthenticatedUser(user);
		    result.setAuthenticated(true);
		    return result;
		}
	}

}
