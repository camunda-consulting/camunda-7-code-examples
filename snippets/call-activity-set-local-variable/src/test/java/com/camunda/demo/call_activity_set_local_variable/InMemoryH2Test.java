package com.camunda.demo.call_activity_set_local_variable;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.JobQueryImpl;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "call-activity-set-local-variable";

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = { "process.bpmn", "child-process.bpmn" })
  public void testParsingAndDeployment() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    
//    managementService().executeJob(managementService().createJobQuery().singleResult().getId());
  }

}
