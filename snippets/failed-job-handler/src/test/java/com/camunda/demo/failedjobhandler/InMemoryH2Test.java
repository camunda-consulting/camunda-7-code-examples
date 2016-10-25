package com.camunda.demo.failedjobhandler;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "failed-job-handler";

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
  public void testNoRetryBusiness() throws InterruptedException {
    JobExecutor jobExecutor = ((ProcessEngineImpl) processEngine()).getProcessEngineConfiguration().getJobExecutor();

    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(
	      PROCESS_DEFINITION_KEY, 
	      Variables.createVariables().putValue("fail", "business"));

    assertThat(processInstance).job();
    Thread.sleep(1000);
    
    assertEquals(0,
        processEngine().getHistoryService().createHistoricJobLogQuery()
        .processInstanceId(processInstance.getId()).failureLog().count());
    
    jobExecutor.shutdown();
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testRetryTechnicalError() throws InterruptedException {
    JobExecutor jobExecutor = ((ProcessEngineImpl) processEngine()).getProcessEngineConfiguration().getJobExecutor();

    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY, 
        Variables.createVariables().putValue("fail", "technical"));
    
    assertThat(processInstance).job();
    Thread.sleep(1000);

    assertEquals(3,
        processEngine().getHistoryService().createHistoricJobLogQuery().processInstanceId(processInstance.getId()).failureLog().count());

    jobExecutor.shutdown();
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
