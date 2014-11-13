package org.camunda.bpm.demo.skip_service_task_expression;

import java.util.HashMap;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.test.mock.Mocks;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY = "skip-service-task-expression";

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
	  Mocks.register("logger", new LoggerDelegate());
	  Mocks.register("skip", new Skip());
	  HashMap<String, Object> variables = new HashMap<String, Object>();
	  variables.put("skipNextExpression", true);
	  runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
  }

}
