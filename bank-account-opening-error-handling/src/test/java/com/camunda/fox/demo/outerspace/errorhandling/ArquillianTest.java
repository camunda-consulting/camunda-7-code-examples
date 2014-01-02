package com.camunda.fox.demo.outerspace.errorhandling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cmd.SetProcessDefinitionVersionCmd;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
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

    // if you experience problems with the authentication to the camunda bpm
    // repository the wrong maven configuration might be used.
    // use this code to use your maven settings.xml in this case:
    // .configureFrom(".../settings.xml")

    return ShrinkWrap
            .create(WebArchive.class, "bank-account-opening-error-handling.war")
            // prepare as process application archive for camunda BPM platform
            .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
            .addAsLibraries(resolver.artifact("commons-lang:commons-lang").resolveAsFiles())
            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
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

    Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).taskAssignee("demo").singleResult();
    assertNotNull(task);

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("approved", true);
    processEngine.getTaskService().complete(task.getId(), variables);

    task = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).taskAssignee("demo").singleResult();
    assertNotNull(task);
    processEngine.getTaskService().complete(task.getId());

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
  
  @Test
  public void testSetProcessDefinitionVersionCommand() {
    String processInstanceId =
      "2d4e6f46-5db7-11e3-939c-955eff2bd0a1";
    int newVersion = 7;
    SetProcessDefinitionVersionCmd command = 
      new SetProcessDefinitionVersionCmd(processInstanceId, newVersion);
    ((ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine())
        .getProcessEngineConfiguration()
        .getCommandExecutorTxRequired().execute(command);  
  }
}
