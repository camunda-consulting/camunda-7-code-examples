package com.camunda.consulting.springexample.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class CamundaApplicationInitializer implements WebApplicationInitializer {

  public void onStartup(ServletContext servletContext) throws ServletException {
    AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
    rootCtx.register(CamundaConfig.class, CamundaSpringConfig.class);

    // add listener to the servlet context
    servletContext.addListener(new ContextLoaderListener(rootCtx));
    servletContext.addListener(new RequestContextListener());

    // create a dispatcher servlet and add mapping to the root of the
    // context path
    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
    DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);
    ServletRegistration.Dynamic dispatcher =
        servletContext.addServlet("Dispatcher", dispatcherServlet);
    dispatcher.setLoadOnStartup(1);
  }

}
