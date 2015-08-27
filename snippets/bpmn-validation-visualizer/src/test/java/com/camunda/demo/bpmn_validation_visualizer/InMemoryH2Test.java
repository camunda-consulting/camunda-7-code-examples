package com.camunda.demo.bpmn_validation_visualizer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.demo.bpmn_validation.BpmnValidationVisualizer;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "bpmn-validation-visualizer";

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  public void testParsingAndDeployment() {
    try {
      rule.getRepositoryService()
        .createDeployment()
        .addClasspathResource("process.bpmn")
        .deploy();
      fail();
    } catch(ProcessEngineException e) {
       Map<String, List<String>> errors = BpmnValidationVisualizer.getErrors(e);
       assertTrue(errors.containsKey("SequenceFlow_4"));
       assertTrue(errors.containsKey("SequenceFlow_5"));
       assertTrue(errors.containsKey("ServiceTask_1"));
       assertTrue(errors.containsKey("ExclusiveGateway_1"));
    }
  }

}
