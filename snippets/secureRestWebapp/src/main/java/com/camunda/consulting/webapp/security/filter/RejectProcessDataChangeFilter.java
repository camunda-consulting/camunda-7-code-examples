package com.camunda.consulting.webapp.security.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ingo Richtsmeier
 *
 */
public class RejectProcessDataChangeFilter implements Filter {
  
  private static final Logger log = Logger.getLogger(RejectProcessDataChangeFilter.class.getName());
  
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("init Reject Changes Filter");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    doFilterSecure((HttpServletRequest)request, (HttpServletResponse)response, chain);
  }

  public void doFilterSecure(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.fine("do Filter with request " + request + " resonse: " + response +  " Filter Chain " + chain);
    
    if (request.getMethod().equals("POST")) {
      response.sendError(403, "POST forbidden");
    } else if (request.getMethod().equals("DELETE")) {
      response.sendError(403, "DELETE forbidden");
    } else if (request.getMethod().equals("PUT")) {
      response.sendError(403, "PUT forbidden");
    } else {
      chain.doFilter(request, response);
    }
  }

  public void destroy() {
    System.out.println("destroy Reject Change Filter");

  }
}
