package com.camunda.demo.filter.webapp;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.exception.InvalidRequestException;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;
import org.camunda.bpm.webapp.impl.security.auth.UserAuthentication;
import org.camunda.bpm.webapp.impl.security.auth.UserAuthenticationResource;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class SpringSecurityBasedUserAuthenticationResource extends UserAuthenticationResource {

    /**
     * Copied from {@link UserAuthenticationResource#APPS} because its private.
     */
    public static final String[] APPS = new String[] { "cockpit", "tasklist", "admin"};

    /**
     * Login a user that has already been authenticated.
     *
     * @param engineName Name of the engine to login to
     * @param username Id of the authenticated user
     * @param authentications Current authentications from the session
     */
    public void doLogin(
            String engineName,
            String username,
            Authentications authentications) {
        doLogin(engineName, username, authentications, null);
    }

    /**
     * Login a user that has already been authenticated and
     * optionally provide its groups.
     *
     * This method is a copy of {@link UserAuthenticationResource#doLogin(String, String, String, String)}
     * except that it neither checks the password nor for application permissions
     * and works on a given list of authentications.
     *
     * The password (or any other proof of identity) MUST be checked by the
     * application server before it passes the request to the application.
     *
     * Application permissions are checked by the applications themselves.
     *
     * It should be kept in sync with the latest version from Camunda,
     * e.g. by doing a diff between the Java files.
     * Hint: Ignore whitespace when doing the diff.
     *
     * @param engineName Name of the engine to login to
     * @param username Id of the authenticated user
     * @param authentications Current authentications from the session
     * @param groupIds Groups of the authenticated user
     *   If groupIds is null, they will be retrieved from the {@link IdentityService}.
     */
    public void doLogin(
            String engineName,
            String username,
            Authentications authentications,
            List<String> groupIds) {

        final ProcessEngine processEngine = lookupProcessEngine(engineName);
        if(processEngine == null) {
            throw new InvalidRequestException(Response.Status.BAD_REQUEST, "Process engine with name "+engineName+" does not exist");
        }

        // make sure authentication is executed without authentication :)
        processEngine.getIdentityService().clearAuthentication();

        if (groupIds == null)
            groupIds = getGroupsOfUser(processEngine, username);
        List<String> tenantIds = getTenantsOfUser(processEngine, username);

        // check user's app authorizations
        AuthorizationService authorizationService = processEngine.getAuthorizationService();

        HashSet<String> authorizedApps = new HashSet<String>();
        authorizedApps.add("welcome");

        if (processEngine.getProcessEngineConfiguration().isAuthorizationEnabled()) {
            for (String application: APPS) {
                if (isAuthorizedForApp(authorizationService, username, groupIds, application)) {
                    authorizedApps.add(application);
                }
            }

        } else {
            Collections.addAll(authorizedApps, APPS);
        }

        // create new authentication
        UserAuthentication newAuthentication = new UserAuthentication(username, engineName);
        newAuthentication.setGroupIds(groupIds);
        newAuthentication.setTenantIds(tenantIds);
        newAuthentication.setAuthorizedApps(authorizedApps);
        authentications.addAuthentication(newAuthentication);
    }

}
