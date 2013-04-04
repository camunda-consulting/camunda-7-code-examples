package com.camunda.fox.demo.outerspace.errorhandling;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {
  
  private static final String PROCESS_DEFINITION_KEY = "open-account-errorhandling";

  @Deployment
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
      .goOffline()
      .loadMetadataFromPom("pom.xml");
    
    // if you experience problems with the authentication to the camunda fox
    // repository the wrong maven configuration might be used.
    // use this code to use your maven settings.xml in this case:
    // .configureFrom(".../settings.xml")

    return ShrinkWrap
            .create(WebArchive.class, "bank-account-opening-error-handling.war")
            // prepare as process application archive for fox platform
            .addAsLibraries(resolver.artifact("com.camunda.fox.platform:fox-platform-client").resolveAsFiles())
            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebResource("META-INF/beans.xml", "WEB-INF/classes/META-INF/beans.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "com.camunda.fox.demo.outerspace.errorhandling") // not recursive to skip package 'nonarquillian'
            // add process definition
            .addAsResource("open-account-errorhandling.bpmn")
    // now you can add additional stuff required for your test case
    ;
  }

  @Inject
  private ProcessEngine processEngine;

  /**
   * Tests that the process is executable and reaches its end.
   */
  @Test
  public void testProcessExecution() throws Exception {
    cleanUpRunningProcessInstances();
    
    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertEquals(1, processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().count());
  }

  /**
   * Helper to delete all running process instances, which might disturb our Arquillian Test case
   * Better run test cases in a clean environment, but this is pretty handy for demo purposes
   */
  private void cleanUpRunningProcessInstances() {
    List<ProcessInstance> runningInstances = processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).list();
    for (ProcessInstance processInstance : runningInstances) {
      processEngine.getRuntimeService().deleteProcessInstance(processInstance.getId(), "deleted to have a clean environment for Arquillian");
    }
  }  
}
