package com.camunda.fox.demo.outerspace.task_name_beautifier;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
  
  private static final String PROCESS_DEFINITION_KEY = "task-name-beautifier";

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
            .create(WebArchive.class, "task-name-beautifier-test.war")
            // prepare as process application archive for fox platform
            .addAsLibraries(resolver.artifact("com.camunda.fox.platform:fox-platform-client").resolveAsFiles())
            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebResource("META-INF/beans.xml", "WEB-INF/classes/META-INF/beans.xml")
            // add your own classes (could be done one by one as well)
            .addClass(ArquillianTest.class)
            // add process definition
            .addAsResource("process.bpmn")
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
    Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);
    assertEquals("Task with terribly long name", task.getName());

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
