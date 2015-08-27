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
    // TODO identify for which method the tearDown is called, e.g. using: String testCaseName = this.getClass().getName() + "." + getName();
    super.tearDown();
  }

  @Deployment(resources = "process.bpmn")
  public void testPathA() {
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("path", "A");
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);

    // calculate coverage for this method, but also add to the overall coverage of the process
    ProcessTestCoverage.calculate(processInstance, processEngine);
  }

  @Deployment(resources = "process.bpmn")
  public void testPathB() {
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("path", "B");
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    // calculate coverage for this method, but also add to the overall coverage of the process
    ProcessTestCoverage.calculate(processInstance.getId(), processEngine);
  }
  
}
