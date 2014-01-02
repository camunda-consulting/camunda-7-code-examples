package org.camunda.bpm.examples;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class FourEyesAdvancedTest {
  
  @Deployment
  public static WebArchive createDeployment() {
    PomEquippedResolveStage resolver = Maven.resolver()
        .offline()
        .loadPomFromFile("pom.xml");

    return ShrinkWrap
            .create(WebArchive.class, "fourEyesAdvanced.war")
            // prepare as process application archive for fox platform
            .addAsLibraries(resolver.resolve("org.camunda.bpm.javaee:camunda-ejb-client").withoutTransitivity().as(JavaArchive.class))
            .addAsLibraries(resolver.resolve("org.camunda.bpm:camunda-engine-cdi").withoutTransitivity().as(JavaArchive.class))
            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebResource("META-INF/beans.xml", "WEB-INF/classes/META-INF/beans.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "org.camunda.bpm.examples")
            // add process definition
            .addAsResource("FourEyesAdvanced.bpmn")
            .addAsResource("FourEyesAdvanced.png")
            // now you can add additional stuff required for your test case
    ;
  }

  @Inject
  private ProcessEngine processEngine;
  @Inject
  private TaskListService taskListService;

  /**
   * Tests that a user cannot claim or complete a task if he completed the first task
   */
  @Test
  public void testInvalidClaim() throws Exception {
    cleanUpRunningProcessInstances();
    
    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(TaskListService.PROCESS_DEFINITION_KEY);

    MockAuthenticationService.loggedInUser = "bernd";
    List<Task> taskForCurrentUser = taskListService.getTaskForCurrentUser();
    assertEquals(2, taskForCurrentUser.size());

    // bernds first task
    MockAuthenticationService.loggedInUser = "bernd";
    taskListService.claim(taskForCurrentUser.get(0));
    taskListService.complete(taskForCurrentUser.get(0));
    // remember which asset we did, since the order is random
    String assetBerndHasReviewedFirst = getAssetName(taskForCurrentUser.get(0));

    // romans first task
    MockAuthenticationService.loggedInUser = "roman";
    taskListService.claim(taskForCurrentUser.get(1));
    taskListService.complete(taskForCurrentUser.get(1));

    // now get the next two tasks
    taskForCurrentUser = taskListService.getTaskForCurrentUser();
    assertEquals(2, taskForCurrentUser.size());
    
    // Figure out the right tasks
    Task taskBernd = taskForCurrentUser.get(0);
    Task taskRoman = taskForCurrentUser.get(1);
    if (assetBerndHasReviewedFirst.equals(getAssetName(taskBernd))) {
      // switch
      taskRoman = taskForCurrentUser.get(0);      
      taskBernd = taskForCurrentUser.get(1);
    }
    
    assertNotSame(taskRoman.getExecutionId(), taskBernd.getExecutionId());
    
    try {
      MockAuthenticationService.loggedInUser = "bernd";
      // bernd cannot claim romans task
      taskListService.claim(taskRoman);
      fail("same user cannot claim twice");
    } catch (Exception ex) {
      assertTrue(ex.getMessage().contains("You cannot claim or complete task"));
      
      // but roman can
      MockAuthenticationService.loggedInUser = "roman";
      taskListService.complete(taskRoman);
    }

    taskForCurrentUser = taskListService.getTaskForCurrentUser();
    assertEquals(1, taskForCurrentUser.size());
    assertEquals(taskBernd.getId(), taskForCurrentUser.get(0).getId());
    assertNotSame(taskBernd.getExecutionId(), taskForCurrentUser.get(0).getExecutionId());
    
    taskBernd = taskForCurrentUser.get(0);
    assertEquals(1, processEngine.getRuntimeService().createExecutionQuery().executionId(taskBernd.getExecutionId()).count());
    assertEquals(1, processEngine.getRuntimeService().getActiveActivityIds(taskBernd.getExecutionId()).size());

    try {
      MockAuthenticationService.loggedInUser = "roman";
      // roman cannot claim bernd task
      taskListService.claim(taskBernd);
      fail("same user cannot claim twice");
    } catch (Exception ex) {
      assertTrue(ex.getMessage() + " is 'cannot claim' exception", ex.getMessage().contains("You cannot claim or complete task"));
      
      // but bernd can
      MockAuthenticationService.loggedInUser = "bernd";
      taskListService.complete(taskBernd);
    }

    assertEquals(1, processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().count());
  }

  private String getAssetName(Task task) {
    // WARNING: THis substring has to be treated with care since it relies on the task name! 
    String name = task.getName();
    int startIndex = name.indexOf("asset");
    return name.substring(startIndex, startIndex + 6); // 6 = "asset".lenght + 1
  }

  
  /**
   * Helper to delete all running process instances which might disturb our Arquillian Test case
   * Better run test cases in a clean environment, but this is pretty handy for demo purposes
   */
  private void cleanUpRunningProcessInstances() {
    List<ProcessInstance> runningInstances = processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(TaskListService.PROCESS_DEFINITION_KEY).list();
    for (ProcessInstance processInstance : runningInstances) {
      processEngine.getRuntimeService().deleteProcessInstance(processInstance.getId(), "deleted to have a clean environment for Arquillian");
    }
  }  
}
