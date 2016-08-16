package com.camunda.demo.sso;

import static org.camunda.bpm.engine.authorization.Permissions.ACCESS;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.webapp.impl.security.SecurityActions;
import org.camunda.bpm.webapp.impl.security.SecurityActions.SecurityAction;
import org.camunda.bpm.webapp.impl.security.auth.Authentication;
import org.camunda.bpm.webapp.impl.security.auth.AuthenticationFilter;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;
import org.camunda.bpm.webapp.impl.security.auth.UserAuthentication;

/**
 * Replaces {@link AuthenticationFilter} by an extension which can auto-logon a
 * user by given username as URL parameter, e.g.
 * 
 * http://localhost:8080/camunda/?auto-login-username=admin
 * 
 * Of course this IS A SECURITY ISSUE! It is only meant to be used in test
 * environments
 *
 */
public class AutoLoginAuthenticationFilter implements Filter {

	private static final String[] APPS = new String[] { "cockpit", "tasklist" };

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;

		// get authentication from session
		Authentications authentications = Authentications.getFromSession(req.getSession());

		// This function is added to the normal AuthenticationFilter
		setAutoLoginAuthentication(request, authentications);

		// set current authentication to the one restored from session (maybe
		// auto login was added)
		Authentications.setCurrent(authentications);

		try {

			SecurityActions.runWithAuthentications(new SecurityAction<Void>() {
				public Void execute() {
					try {
						chain.doFilter(request, response);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					return null;
				}
			}, authentications);
		} finally {
			Authentications.clearCurrent();
			// store updated authentication object in session for next request
			Authentications.updateSession(req.getSession(), authentications);
		}
	}

	/**
	 * Reads the auto-login-username from the URL parameters and create an
	 * {@link Authentication} for it containing its groups, tenants and
	 * authorized apps.
	 * 
	 * No password check is done here, so you can log onto every user without a
	 * password. Only makes sense in demo environments!
	 */
	protected void setAutoLoginAuthentication(final ServletRequest request, Authentications authentications) {
		final HttpServletRequest req = (HttpServletRequest) request;
		final ProcessEngine engine = getEngine();

		// Get the username from the user in SSO
		String username = retrieveUsername(req);

		// if not set - no auto login
		if (username == null) {
			return;
		}

		// if already in the list of logged in users - nothing to do
		Authentication authentication = authentications.getAuthenticationForProcessEngine(engine.getName());
		if (authentication != null && authentication.getName() == username) {
			return;
		}

		AuthorizationService authorizationService = engine.getAuthorizationService();

		// query group information
		List<String> groupIds = getGroupsOfUser(engine, username);
		List<String> tenantIds = getTenantsOfUser(engine, username);

		// check user's app authorizations by iterating of list of apps and ask
		// if permitted
		HashSet<String> authorizedApps = new HashSet<String>();
		authorizedApps.add("admin");
		if (engine.getProcessEngineConfiguration().isAuthorizationEnabled()) {
			for (String application : APPS) {
				if (authorizationService.isUserAuthorized(username, groupIds, ACCESS, APPLICATION, application)) {
					authorizedApps.add(application);
				}
			}
		} else {
			Collections.addAll(authorizedApps, APPS);
		}

		// create new authentication object to store authentication
		UserAuthentication newAuthentication = new UserAuthentication(username, engine.getName());
		newAuthentication.setGroupIds(groupIds);
		newAuthentication.setTenantIds(tenantIds);
		newAuthentication.setAuthorizedApps(authorizedApps);

		// and add the new logged in user
		authentications.addAuthentication(newAuthentication);
	}

	/**
	 * Get the username - this is different based on the SSO technology used,
	 * see e.g.
	 * https://github.com/camunda/camunda-sso-jboss/blob/master/camunda-bpm-
	 * build-webapp/src/main/java/de/novatec/bpm/webapp/impl/security/auth/
	 * AuthenticationFilter.java#L57 for an implementation using Kerberos on
	 * JBoss AS 7
	 */
	protected String retrieveUsername(final HttpServletRequest req) {
		// Simply read it from a URL parameter in this case
		return req.getParameter("auto-login-username");
	}

	/**
	 * copied from
	 * org.camunda.bpm.webapp.impl.security.auth.UserAuthenticationResource
	 */
	protected List<String> getGroupsOfUser(ProcessEngine engine, String userId) {
		List<Group> groups = engine.getIdentityService().createGroupQuery().groupMember(userId).list();

		List<String> groupIds = new ArrayList<String>();
		for (Group group : groups) {
			groupIds.add(group.getId());
		}
		return groupIds;
	}

	protected List<String> getTenantsOfUser(ProcessEngine engine, String userId) {
		List<Tenant> tenants = engine.getIdentityService().createTenantQuery().userMember(userId).includingGroupsOfUser(true).list();

		List<String> tenantIds = new ArrayList<String>();
		for (Tenant tenant : tenants) {
			tenantIds.add(tenant.getId());
		}
		return tenantIds;
	}

	private ProcessEngine getEngine() {
		// TODO: Only works in single engine environment!
		return BpmPlatform.getDefaultProcessEngine();
	}

}
