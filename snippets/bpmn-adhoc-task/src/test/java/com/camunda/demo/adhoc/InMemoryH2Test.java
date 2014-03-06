package com.camunda.demo.adhoc;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;

import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.junit.Before;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY = "bpmn-adhoc-snippet";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }
  
  public void setUp() throws Exception {
    super.setUp();
    init(processEngine);
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Deployment(resources = "process.bpmn")
  public void testAdHoc() {
    
    ProcessInstance pi = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    
    assertThat(pi).isStarted();
    assertThat(pi).task("UserTaskA").isNotNull();
    assertThat(pi).task("UserTaskB").isNotNull();
    assertThat(pi).task("UserTaskC").isNotNull();
    
    // Rule is: When TaskA and TaskB are finished - process should continue
    complete(task("UserTaskA"));
    complete(task("UserTaskB"));
    
    assertThat(pi).isEnded();
  }

  @Deployment(resources = "process.bpmn")
  public void testAdHocAllTAsksNecessary() {
    
    ProcessInstance pi = runtimeService.startProcessInstanceByKey("bpmn-adhoc-snippet");
    
    assertThat(pi).isStarted();
    assertThat(pi).task("UserTaskA").isNotNull();
    assertThat(pi).task("UserTaskB").isNotNull();
    assertThat(pi).task("UserTaskC").isNotNull();
    
    // Rule is: When TaskA and TaskB are finished - process should continue
    complete(task("UserTaskA"));
    complete(task("UserTaskC"));
    
    assertThat(pi).isActive();

    complete(task("UserTaskB"));
    assertThat(pi).isEnded();    
  }
}
