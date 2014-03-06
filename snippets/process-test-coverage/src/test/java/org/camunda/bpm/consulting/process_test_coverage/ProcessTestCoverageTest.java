package org.camunda.bpm.consulting.process_test_coverage;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class ProcessTestCoverageTest extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY = "process-test-coverage";

  @Override
  protected void tearDown() throws Exception {
	// calculate coverage for all tests
	ProcessTestCoverage.calculate(processEngine);
//	String testCaseName = this.getClass().getName() + "." + getName();
    super.tearDown();
  }

  @Deployment(resources = "process.bpmn")
  public void testPathA() {
	ProcessInstance processInstance = executePathA();
    ProcessTestCoverage.calculate(processInstance.getId(), processEngine);
  }

  @Deployment(resources = "process.bpmn")
  public void testPathB() {
	Map<String, Object> variables = new HashMap<String, Object>();
	variables.put("path", "B");
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    ProcessTestCoverage.calculate(processInstance.getId(), processEngine);
  }
  
  @Deployment(resources = "process.bpmn")
  public void testGetBpmnFileName() {
	ProcessInstance processInstance = executePathA();
	String bpmnFileName = ProcessTestCoverage.getBpmnFileName(processInstance.getId(), processEngine);
	assertEquals("process.bpmn", bpmnFileName);
  }

  private ProcessInstance executePathA() {
	Map<String, Object> variables = new HashMap<String, Object>();
	variables.put("path", "A");
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
	return processInstance;
  }
  
}
