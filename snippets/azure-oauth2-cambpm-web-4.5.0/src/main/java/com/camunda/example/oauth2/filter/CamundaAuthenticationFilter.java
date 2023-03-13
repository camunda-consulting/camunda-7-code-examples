package com.camunda.example.oauth2.filter;

import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.camunda.bpm.webapp.impl.util.ServletContextUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class CamundaAuthenticationFilter extends ContainerBasedAuthenticationFilter {

    @Override
    protected String getRequestUri(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        int contextPathLength = contextPath.length();
        if (contextPathLength > 0) {
            requestURI = requestURI.substring(contextPathLength);
        }

        ServletContext servletContext = request.getServletContext();
        String applicationPath = ServletContextUtil.getAppPath(servletContext);
        int applicationPathLength = applicationPath.length();

        if (applicationPathLength > 0 && applicationPathLength < requestURI.length()) {
            requestURI = requestURI.substring(applicationPathLength);
        }

        return requestURI;
    }
}
