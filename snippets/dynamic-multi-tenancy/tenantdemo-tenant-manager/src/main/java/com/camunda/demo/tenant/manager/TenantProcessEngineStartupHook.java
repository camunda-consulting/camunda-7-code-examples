package com.camunda.demo.tenant.manager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Process Application exposing this application's resources the process engine. 
 */
//@ProcessApplication
@WebListener
public class TenantProcessEngineStartupHook implements ServletContextListener { // extends ServletProcessApplication {

  private TenantManager tenantManager = new TenantManager();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    tenantManager.startProcessEngines();
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    tenantManager.stopProcessEngines();    
  } 


}
