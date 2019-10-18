package org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.externaltask.ExternalTask;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener.CompleteExternalTaskWithoutLockCmd.completeExternalTaskWithoutLock;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class ProcessUnitTest {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "engine-plugin-external-task-listener";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() throws InterruptedException {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertThat(processInstance).isWaitingAt("Task_DoSomething");	  
	  
//    ExternalTaskService externalTaskService = processEngine().getExternalTaskService();
//    ExternalTask externalTask = externalTaskService.createExternalTaskQuery().singleResult();
//    completeExternalTaskWithoutLock(processEngine(), externalTask);
    Thread.sleep(1_000L);
    
    assertThat(processInstance).isEnded();
  }

}
