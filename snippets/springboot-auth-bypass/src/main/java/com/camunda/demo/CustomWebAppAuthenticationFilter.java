package com.camunda.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.webapp.impl.security.auth.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomWebAppAuthenticationFilter extends ContainerBasedAuthenticationFilter {


  // TODO: do not hardcode those credentials! Use e.g. spring boot properties or a db table instead
  private String bypassUserId = "foo";
  private String bypassUserPwd = "bar";

  @Override
  public void init(FilterConfig filterConfig) {
    userAuthentications = new AuthenticationService();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    log.warn("<<<<<<< FILTER INVOKED >>>>>>>>>>");

    final String username = request.getParameter("username");
    final String password = request.getParameter("password");

    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse resp = (HttpServletResponse) response;

    // current limitation: only works for default engine
    final String engineName = "default";
    // TODO: The existing matchers don't work for the login request - extract the engine name in a better way

//    String engineName = extractEngineName(req);

//    if (engineName == null) {
//      chain.doFilter(request, response);
//      return;
//    }


    if (username != null && password != null) {
      if (username.equals(bypassUserId) && password.equals(bypassUserPwd)) {

        log.warn("BYPASS USER detected");

        ProcessEngine engine = getAddressedEngine(engineName);

        if (engine == null) {
          resp.sendError(404, "Process engine " + engineName + " not available");
          return;
        }

        Authentications authentications = Authentications.getFromSession(req.getSession());
        String authenticatedUser = username;

        if (!existisAuthentication(authentications, engineName, authenticatedUser)) {
          List<String> groups = new ArrayList<>();
          List<String> tenants = new ArrayList<>();

          Authentication authentication = createAuthentication(engine, authenticatedUser, groups, tenants);
          authentications.addAuthentication(authentication);

          Authentications.setCurrent(authentications);
          Authentications.updateSession(req.getSession(), authentications);

          ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_OK);
          ((HttpServletResponse) response).setHeader("Content-Type", "application/json");
          AuthenticationDto authenticationDto = AuthenticationDto.fromAuthentication(authentication);

          String serialized = new ObjectMapper().writeValueAsString(authenticationDto);
          response.getOutputStream().write(serialized.getBytes());

          return;

        }
      }
    }

    chain.doFilter(request, response);

  }
}
