package com.camunda.demo.webinar.testing.scope2;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.camunda.demo.webinar.testing.Customer;

@RunWith(Arquillian.class)
public class ArquillianTest {
  
  @Deployment
  public static WebArchive createDeployment() {
    // resolve given dependencies from Maven POM
    File[] libs = Maven.resolver()
      .offline(false)
      .loadPomFromFile("pom.xml")
      .importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();

    return ShrinkWrap
            .create(WebArchive.class, "webinar-testing.war")
            // add needed dependencies
            .addAsLibraries(libs)
            // prepare as process application archive for camunda BPM Platform
            .addAsResource("META-INF/processes.xml", "META-INF/processes.xml")
            // enable CDI
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // boot JPA persistence unit
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "com.camunda.demo.webinar.testing") // not recursive to skip package 'scope2'
            // add process definition
            .addAsResource("process.bpmn")
            // add process image for visualizations
            .addAsResource("process.png")
            // now you can add additional stuff required for your test case
    ;
  }

  @Inject
  private ProcessEngine processEngine;

  @Before
  public void setup() {
    init(processEngine);
  }
  
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Tests that the process is executable and reaches its end.
   */
  @Test
  public void testProcessExecution() throws Exception {
    cleanUpRunningProcessInstances();

    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey( //
        "webinar-testing", //
        Variables.createVariables().putValue("path", "C").putValue("customerName", "Daniel"));
    assertThat(processInstance).task();
    complete(task());
    execute(job());
    assertThat(processInstance).isEnded();
    
    String customerId = (String) historyService().createHistoricVariableInstanceQuery().variableName("customerId").singleResult().getValue();
    
    // now we can test if the entity was created
    Customer customer = entityManager.find(Customer.class, Long.valueOf(customerId));
    assertEquals("Daniel", customer.getName());
  }

  /**
   * Helper to delete all running process instances, which might disturb our
   * Arquillian Test case.
   * Better run test cases in a clean environment, but this
   * is pretty handy for demo purposes
   */
  private void cleanUpRunningProcessInstances() {
    List<ProcessInstance> runningInstances = processInstanceQuery().processDefinitionKey("webinar-testing").list();
    for (ProcessInstance processInstance : runningInstances) {
      runtimeService().deleteProcessInstance(processInstance.getId(), "deleted to have a clean environment for Arquillian");
    }
  }
}
