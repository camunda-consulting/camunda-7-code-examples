package org.camunda.bpm.demo.loan_request_handling.nonarquillian;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.Message;
import org.camunda.bpm.model.bpmn.instance.MessageEventDefinition;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

  private static final String PROCESS_DEFINITION_KEY = "loan-request-handling";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Ignore
  @Test
  public void makeModelDeployable() throws IOException {
    InputStream stream = this.getClass().getResourceAsStream("/Final Model.bpmn");
    BpmnModelInstance modelInstance = Bpmn.readModelFromStream(stream);
    new ExecutableModelGenerator().makeExecutable(modelInstance);
    FileUtils.writeStringToFile(new File("src/test/resources/FinalModel.executable.bpmn"), Bpmn.convertToString(modelInstance));
  }
  
  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "FinalModel.executable.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }
  
  @Ignore
  @Test
  @Deployment(resources = "branchofficeonlyloan-ref-en.bpmn")
  public void testHappyPath() {
	  ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("BOLoanRequestHandling");
	  
	  assertThat(processInstance).isActive();
	  
	  complete(task());
  }
  
  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
