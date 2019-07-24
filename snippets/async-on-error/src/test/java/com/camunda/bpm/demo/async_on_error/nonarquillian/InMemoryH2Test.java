package com.camunda.bpm.demo.async_on_error.nonarquillian;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.bpm.demo.async_on_error.AsyncOnError;
import com.camunda.bpm.demo.async_on_error.BusinessLogicDelegate;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

//  @ClassRule
//  @Rule
//  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();
  
  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "async-on-error";

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
  public void testServiceTask() {
    Mocks.register("asyncOnError", new AsyncOnError());
    Mocks.register("businessLogicDelegate", new BusinessLogicDelegate());
    
    Map<String,Object> variables = new HashMap<String, Object>();
    variables.put("throwException", true);
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).job("Task_DoSomethingThatMightFail").hasExceptionMessage();
    
    runtimeService().setVariable(processInstance.getId(), "throwException", false);
    
    execute(job());
    
    assertThat(processInstance).isEnded();
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testIncidentCreated() {
    Mocks.register("asyncOnError", new AsyncOnError());
    Mocks.register("businessLogicDelegate", new BusinessLogicDelegate());

    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, 
        withVariables("throwException", true));
    
    assertThat(processInstance).job("Task_DoSomethingThatMightFail").hasExceptionMessage();
    // do the retry
    for (int i = 1; i < 4; i++) { 
      try {
        execute(job());
      } catch (Exception e) {
      }      
    }
    assertThat(processInstance).isWaitingAt("Task_DoSomethingThatMightFail");
    
    Incident incident = runtimeService().createIncidentQuery().singleResult();
    assertThat(incident).isNotNull();
  }

  @Test
  @Deployment(resources = "process-with-async-before-start.bpmn")
  public void testStart() {
    Mocks.register("businessLogicDelegate", new BusinessLogicDelegate());
    
    Map<String,Object> variables = new HashMap<String, Object>();
    variables.put("throwException", true);
    ProcessInstantiationBuilder processInstantiationBuilder = runtimeService()
        .createProcessInstanceByKey("process-with-async-before-start")
        .setVariables(variables);
    
    ProcessInstanceWithVariables processInstance = 
        AsyncOnError.startProcessInstance(processEngine(), processInstantiationBuilder);

    assertThat(processInstance).job("StartEvent_ProcessStarted").hasExceptionMessage();

    runtimeService().setVariable(processInstance.getId(), "throwException", false);
  
    execute(job());

    assertThat(processInstance).isEnded();
  }

}
