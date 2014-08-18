package com.camunda.bpm.demo.custom_incident.nonarquillian;

import static org.junit.Assert.*;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "custom-incident";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    LogFactory.useJdkLogging(); // MyBatis
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testExecution() {
    RuntimeService runtimeService = processEngineRule.getRuntimeService();
	runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    
    TaskService taskService = processEngineRule.getTaskService();
	Task task = taskService.createTaskQuery().singleResult();
    assertNotNull(task);
    
    Incident incident = runtimeService.createIncidentQuery().singleResult();
    assertNotNull(incident);
    
    taskService.complete(task.getId());
    assertEquals(0, runtimeService.createIncidentQuery().count());
  }
  
}
