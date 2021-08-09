package org.camunda.optimize.example;

import org.camunda.optimize.plugin.security.authentication.AuthenticationExtractor;
import org.camunda.optimize.plugin.security.authentication.AuthenticationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class SSOPlugin implements AuthenticationExtractor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request) {

        AuthenticationResult result = new AuthenticationResult();
        // A user ID can be extracted from the request or as you can see here, simply hardcoded. It must map to a valid user
        // in a given Identity Service
        result.setAuthenticatedUser("demo");
        result.setAuthenticated(true);
        return result;

    }
}
