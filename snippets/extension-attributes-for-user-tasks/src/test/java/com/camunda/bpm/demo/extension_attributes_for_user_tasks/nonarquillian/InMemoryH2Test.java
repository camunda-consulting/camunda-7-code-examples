package com.camunda.bpm.demo.extension_attributes_for_user_tasks.nonarquillian;

import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.test.Deployment;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY = "extension-attributes-for-user-tasks";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Deployment(resources = "process.bpmn")
  public void testExec() {
	  runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
	  Task task = taskService.createTaskQuery().singleResult();
	  assertNotNull(task);
	  assertNotNull(task.getDueDate());
  }

}
