package com.camunda.consulting.webapp.security.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
  
  private List<String> forbiddenPostRequests = new ArrayList<String>();

  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("init Reject Change Filter");
    loadAllowedRequests(filterConfig);
  }

  protected void loadAllowedRequests(FilterConfig filterConfig) throws ServletException {
    String configFileName = filterConfig.getInitParameter("configFile");
    InputStream configFileResource = filterConfig.getServletContext().getResourceAsStream(configFileName);
    if (configFileResource == null) {
      throw new ServletException("Could not read security filter config file '"+configFileName+"': no such resource in servlet context.");
    } else {
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(configFileResource));
        String forbidden = reader.readLine();
        while (forbidden != null) {
          forbiddenPostRequests.add(forbidden);
          forbidden = reader.readLine();
        }
      } catch (Exception e) {
        throw new RuntimeException("Exception while parsing '" + configFileName + "'", e);
      } finally {
        if (configFileResource != null) {
          try {
            configFileResource.close();
          } catch (IOException ignore) {
            // Ignore this exception silently
          }
        }
      }
    }
    
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    doFilterSecure((HttpServletRequest)request, (HttpServletResponse)response, chain);
  }

  public void doFilterSecure(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.fine("do Filter with request " + request + " resonse: " + response +  " Filter Chain " + chain);
    log.fine("method: " + request.getMethod());
    String requestUri = getRequestUri(request);
    
    if (request.getMethod().equals("POST")) {
      log.info("POST requestURI: " + requestUri);
      if (forbiddenPostRequests.contains(requestUri)) {
        log.info("forbidden requestURI: " + requestUri);
        response.sendError(403, "POST forbidden");
      } else {
        chain.doFilter(request, response);
      }
    } else if (request.getMethod().equals("DELETE")) {
      response.sendError(403, "DELETE forbidden");
    } else if (request.getMethod().equals("PUT")) {
      response.sendError(403, "PUT forbidden");
    } else {
      chain.doFilter(request, response);
    }
  }

  public void destroy() {
    log.info("destroy Reject Change Filter");
  }
  
  protected String getRequestUri(HttpServletRequest request) {
    String contextPath = request.getContextPath();
    return request.getRequestURI().substring(contextPath.length());
  }

}
