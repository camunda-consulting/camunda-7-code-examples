package com.camunda.fox.demo.outerspace.task_name_beautifier.nonarquillian;

import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiTestCase;
import org.activiti.engine.test.Deployment;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends ActivitiTestCase {

  private static final String PROCESS_DEFINITION_KEY = "task-name-beautifier";

  /**
   * Just tests if the process definition is deployable.
   */
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Deployment(resources = "process.bpmn")
  public void testTaskNameBeautification() {
    runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    Task task = taskService.createTaskQuery().list().get(0);
    assertEquals("Task with terribly long name", task.getName());
  }

}
