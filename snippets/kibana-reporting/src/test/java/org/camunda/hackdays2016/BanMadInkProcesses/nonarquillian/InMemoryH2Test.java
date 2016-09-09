package org.camunda.hackdays2016.BanMadInkProcesses.nonarquillian;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "InsurencePaymentCheck";

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
  @Deployment(resources = {"process.bpmn", "assignment.dmn","fraudRating.dmn", "generateTestData.bpmn", "loadElasticSearch.bpmn"})
  public void testLoadelasticGeneration() {
	  Map<String, Object> vars = new HashMap<String, Object>();
	  vars.put("numberOfInstances", 10);
	  
	  ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("loadToElasticSearch");
	  
//	  execute(job());
//	  
//	  assertThat(processInstance.isEnded());
  }
  
  @Test
  @Deployment(resources = {"process.bpmn", "assignment.dmn","fraudRating.dmn", "generateTestData.bpmn"})
  public void testDataGeneration() {
	  Map<String, Object> vars = new HashMap<String, Object>();
	  vars.put("numberOfInstances", 10);
	  
	  ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("generateDataProcess", vars);
	  
//	  execute(job());
//	  
//	  assertThat(processInstance.isEnded());
  }

  @Test
  @Deployment (resources =  {"process.bpmn", "assignment.dmn", "fraudRating.dmn"})
  public void testHappyPath() {
	  ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
	  
	  assertThat(processInstance).isStarted();
	  
	  Map<String, Object> vars = new HashMap<String, Object>();
	  
	  vars.put("candidateName", "Niall");
	  vars.put("claimType", "Car");
	  vars.put("claimAmount", 12344.00);
	  vars.put("region", "Berlin");
	  vars.put("paymentRejected", false);
	  vars.put("numberOfPayouts", 0);
	  vars.put("historyOfFraud", true);
	  vars.put("claimAmount", 500);
	  vars.put("audit", false);

	  // Task_EnterCandidateDetails

//	  execute(job());
//	  	  
//	  Task theFirstTask = rule.getTaskService().createTaskQuery().singleResult();
//	  
//	  rule.getTaskService().complete(theFirstTask.getId(), vars);
//	  
	  // Task_EnterApplicationDetails
	  
//	  execute(job()); 
//	  complete(task());
//	  
//	  // IntermediateThrowEvent_0l7781p
//	  
//	  execute(job());
//
//	  
//	  // Task_DecideOnFurtherFraudCheck
//	  
//	  execute(job());
//	  complete(task());
//
//	  
//
//	  
//	  assertThat(processInstance).isEnded();
	  
	// To generate the coverage report for a single tests add this line as the last line of your test method:
	//ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
