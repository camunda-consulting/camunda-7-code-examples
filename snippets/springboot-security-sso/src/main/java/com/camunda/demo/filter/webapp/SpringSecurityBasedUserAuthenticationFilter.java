package com.camunda.demo.filter.webapp;

import org.camunda.bpm.webapp.impl.security.SecurityActions;
import org.camunda.bpm.webapp.impl.security.auth.Authentication;
import org.camunda.bpm.webapp.impl.security.auth.AuthenticationFilter;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

public class SpringSecurityBasedUserAuthenticationFilter extends AuthenticationFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityBasedUserAuthenticationFilter.class);

    /**
     * This method is a copy of {@link org.camunda.bpm.webapp.impl.security.auth.AuthenticationFilter#doFilter(ServletRequest, ServletResponse, FilterChain)}
     * except for the invocation of {@link #setKnownPrinicipal(HttpServletRequest, Authentications)}.
     * <p>
     * It should be kept in sync with the latest version from Camunda,
     * e.g. by doing a diff between the Java files.
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;

        // get authentication from session
        Authentications authentications = Authentications.getFromSession(req.getSession());
        setKnownPrinicipal(req, authentications);
        Authentications.setCurrent(authentications);
        try {

            SecurityActions.runWithAuthentications((SecurityActions.SecurityAction<Void>) () -> {
                chain.doFilter(request, response);
                return null;
            }, authentications);
        } finally {
            Authentications.clearCurrent();
            Authentications.updateSession(req.getSession(), authentications);
        }

    }


    private void setKnownPrinicipal(final HttpServletRequest request, Authentications authentications) {
        String username = getUserName(request);
        if (username != null && !username.isEmpty()) {
            for (Authentication auth : authentications.getAuthentications()) {
                if (username.equals(auth.getName())) {
                    // already in the list - nothing to do
                    LOGGER.debug(request.getSession().getId() + " already authorized.");
                    return;
                }
            }

            List<String> groupIds = getUserGroups(username);

            doLogin(authentications, username, "default", groupIds);

            LOGGER.debug(request.getSession().getId() + " " + username + " " + "default");
        } else {
            LOGGER.debug(request.getSession().getId() + " no user provided from spring security!");
        }
    }

    private void doLogin(Authentications authentications, String username, String engineName, List<String> groupIds) {
        new SpringSecurityBasedUserAuthenticationResource().doLogin(engineName, username, authentications, groupIds);
    }

    private String getUserName(final HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal != null ? principal.getName() : null;
    }

    private List<String> getUserGroups(String userId){

        List<String> groupIds;

        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        groupIds = authentication.getAuthorities().stream()
                .map(res -> res.getAuthority())
                .map(res -> res.substring(5)) // Strip "ROLE_"
                .collect(Collectors.toList());

        return groupIds;

    }

}


