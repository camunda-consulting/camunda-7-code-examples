package org.camunda.bpm.demo.non_interrupting_message_event_sub_process_in_sub_process;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "non-interrupting-message-event-sub-process-in-sub-process";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
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
  public void testHappyPath() {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    
    assertThat(processInstance).task("UserTask_machbarkeitPruefen");
    
    complete(task());
    
    assertThat(processInstance).isEnded();
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testEventSubProcessIsBlocking() {
    ProcessInstance processInstance = startProcessAndSendMessage();

    complete(task("UserTask_machbarkeitPruefen"));
    
    assertThat(processInstance).isActive()
      .task("UserTask_vertragsManagement");
    
    complete(task());
    
    assertThat(processInstance).isEnded();
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testEventSubProcessFinishesFirst() {
    ProcessInstance processInstance = startProcessAndSendMessage();

    complete(task("UserTask_vertragsManagement"));
    
    assertThat(processInstance).isActive()
      .task("UserTask_machbarkeitPruefen");
    
    complete(task());
    
    assertThat(processInstance).isEnded();
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  private ProcessInstance startProcessAndSendMessage() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    
    assertThat(processInstance).task("UserTask_machbarkeitPruefen");
    
    runtimeService().correlateMessage("vertragsManagementNeeded");
    
    assertThat(processInstance).task("UserTask_vertragsManagement");
    return processInstance;
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
