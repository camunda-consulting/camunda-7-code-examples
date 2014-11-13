package org.camunda.bpm.demo.multi_tenancy;

import static org.junit.Assert.*;

import java.io.File;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.RuntimeService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {
  
  private static final String TENANT_ID = "default";

  private static final String PROCESS_DEFINITION_KEY = "multi-tenancy";

  @Deployment
  public static WebArchive createDeployment() {
    // resolve given dependencies from Maven POM
    File[] libs = Maven.resolver()
      .offline(false)
      .loadPomFromFile("pom.xml")
      .importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();

    return ShrinkWrap
            .create(WebArchive.class, "multi-tenancy.war")
            // add needed dependencies
            .addAsLibraries(libs)
            // prepare as process application archive for camunda BPM Platform
            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            // enable CDI
            .addAsWebResource("WEB-INF/beans.xml", "WEB-INF/beans.xml")
            // boot JPA persistence unit
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "org.camunda.bpm.demo.multi_tenancy") // not recursive to skip package 'nonarquillian'
            // add process definition
            .addAsResource("process.bpmn")
            // add process image for visualizations
            .addAsResource("process.png")
            // now you can add additional stuff required for your test case
    ;
  }

  @Inject
  private ProcessEngine processEngine;
  
  @Inject
  private RuntimeService runtimeService;

  @Inject
  private Tenant tenant;
  
  @Test
  public void testTenantAwareProcessEngineInjectionWithoutTenant() throws Exception {
	  try {
		  processEngine.getRuntimeService();
	  } catch (ProcessEngineException e) {
		  assertEquals("No tenant id specified. A process engine can only be retrieved based on a tenant.", e.getMessage());
	  }
  }  

  @Test
  public void testTenantAwareRuntimeServiceInjectionWithoutTenant() throws Exception {
	  try {
		  runtimeService.createExecutionQuery();
	  } catch (ProcessEngineException e) {
		  assertEquals("No tenant id specified. A process engine can only be retrieved based on a tenant.", e.getMessage());
	  }
  }  
  
  @Test
  public void testTenantAwareProcessEngineInjection() throws Exception {
	  tenant.setId(TENANT_ID);
	  assertNotNull(processEngine.getRuntimeService());
  }  

  @Test
  public void testTenantAwareRuntimeServiceInjection() throws Exception {
	  tenant.setId(TENANT_ID);
	  assertNotNull(runtimeService.createExecutionQuery());
  }  

  @Test
  public void testProcessExecution() throws Exception {
	  tenant.setId(TENANT_ID);
	  runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
  }  
}
