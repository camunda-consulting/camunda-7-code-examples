package org.camunda.com.ssoplugintest;

import javax.servlet.http.HttpServletRequest;

import org.camunda.optimize.plugin.security.authentication.AuthenticationExtractor;
import org.camunda.optimize.plugin.security.authentication.AuthenticationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Plugin has to be put into plugin folder of Optimize installation.
 * @author NormanLüring
 *
 */
public class SignInUser implements AuthenticationExtractor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * This method reads header information and authorizes users for optimize.
	 */
	@Override
	public AuthenticationResult extractAuthenticatedUser(HttpServletRequest servletRequest) {
		
		AuthenticationResult result = new AuthenticationResult();
		String user = servletRequest.getHeader("user");
		
		if(user == null || user.isEmpty()) {
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
