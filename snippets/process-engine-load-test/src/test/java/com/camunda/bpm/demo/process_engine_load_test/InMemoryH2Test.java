package com.camunda.bpm.demo.process_engine_load_test;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.util.UUID;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "process-engine-load-test";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
    repositoryService().createDeployment()
      .addModelInstance("SequenceOf100ServiceTasks.bpmn", ProcessGenerator.generateSequenceOf100ServiceTasks())
    .deploy();
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    VariableMap variables = Variables.createVariables();
    for(int i = 0; i < 20; i++) {
      variables.put("variable1"+i, "Some String");
      variables.put("variable2"+i, Math.random());
      variables.put("variable3"+i, UUID.randomUUID());
      variables.put("variable4"+i, true);
      variables.put("variable5"+i, 23);
    }
    for(int i = 0; i < 5000; i++) {
      if (i % 1000 == 0) {
        System.out.println(i);
      }
      runtimeService().startProcessInstanceByKey("SequenceOf100ServiceTasks", variables);
    }
  }

}
