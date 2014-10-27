package com.camunda.bpm.demo.uel_variable_evaluator.nonarquillian;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.bpm.demo.uel_variable_evaluator.LoggerDelegate;
import com.camunda.bpm.demo.uel_variable_evaluator.UEL;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "uel-variable-evaluator";

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
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testExecution() {
    LoggerDelegate logger = new LoggerDelegate();
    Mocks.register("logger", logger);
    Mocks.register("uel", new UEL());
    
    Map <String, Object> variables = new HashMap<String, Object>();
    variables.put("expression", "${logger.execute(execution)}");
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance)
    .hasVariables("loggerInvoked");
  }

}
