package org.camunda.bpm.demo.multi_tenancy;

import static org.junit.Assert.*;

import java.io.File;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {

  private static final String PROCESS_DEFINITION_KEY = "multi-tenancy";

  @Deployment
  public static WebArchive createDeployment() {
    // resolve given dependencies from Maven POM
    File[] libs = Maven.resolver()
      .offline(false)
      .loadPomFromFile("pom.xml")
      .importRuntimeDependencies().resolve().withTransitivity().asFile();

    return ShrinkWrap
            .create(WebArchive.class, "multi-tenancy.war")
            // add needed dependencies
            .addAsLibraries(libs)
            // prepare as process application archive for camunda BPM Platform
            .addAsResource("META-INF/processes.xml", "META-INF/processes.xml")
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
  private Tenant tenant;

  @Test
  public void testTenantAwareProcessEngineInjectionWithoutTenant() throws Exception {
    // given
    // tenant id is null
    assertNull(tenant.getId());

    // then
    // the process engine cannot be injected
	  try {
		  processEngine.getName();
		  fail("Exception expected");
	  } catch (ProcessEngineException e) {
		  assertEquals("No tenant id specified. A process engine can only be retrieved based on a tenant.", e.getMessage());
	  }
  }

  @Test
  public void testTenantAwareProcessEngineInjectionTenant1() throws Exception {

    // given
    // that tenant id is set to 'tenant1'
    tenant.setId("tenant1");

    // then
    // the 'tenant1' engine is injected
    assertEquals("tenant1", processEngine.getName());
  }

  @Test
  public void testTenantAwareProcessEngineInjectionTenant2() throws Exception {

    // given
    // that tenant id is set to 'tenant2'
    tenant.setId("tenant2");

    // then
    // the 'tenant2' engine is injected
    assertEquals("tenant2", processEngine.getName());
  }


  @Test
  public void testProcessExecutionTenant1() throws Exception {

    // given
    // that tenant 1 is selected
    tenant.setId("tenant1");

    // then
    // the process can be executed
    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
  }

  @Test
  public void testProcessExecutionTenant2() throws Exception {

    // given
    // that tenant 2 is selected
    tenant.setId("tenant2");

    // then
    // the process can be executed
    processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
  }
}
