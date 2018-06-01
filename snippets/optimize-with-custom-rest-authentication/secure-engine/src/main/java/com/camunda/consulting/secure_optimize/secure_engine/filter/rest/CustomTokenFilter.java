package com.camunda.consulting.secure_optimize.secure_engine.filter.rest;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

public class CustomTokenFilter extends GenericFilterBean {
  
  public static String secretToken = Optional.ofNullable(System.getenv("CUSTOM_TOKEN")).orElse("CustomToken");

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest httpRequest = (HttpServletRequest) request;

    // extract secretToken from header
    final String accessToken = httpRequest.getHeader("Custom-Auth-Token");
    
    // check secretToken
    if (secretToken.equals(accessToken)) {
      // Populate SecurityContextHolder by fetching relevant information
      // using secretToken
      final User user = new User("demo", "demo", true, true, true, true, Collections.emptyList());
      final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }
}