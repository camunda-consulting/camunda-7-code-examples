package com.camunda.demo.insuranceapplication;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.demo.insuranceapplication.ProcessVariables;
import com.camunda.demo.insuranceapplication.model.Application;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class NeuantragProcessTest {


  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "insurance-application";

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
  @Deployment(resources = {"InsuranceApplication.bpmn", "RiskAssessment.dmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "RiskAssessment.dmn"})
  public void testDunkelverarbeitungPoliciert() {
    Application neuantrag = DemoData.createNewApplication(40, "VW", "Golf V");
    
    VariableMap variables = Variables.createVariables().putValue(
        ProcessVariables.VAR_NAME_application,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // send task
    
    assertThat(processInstance).isEnded();    
  }

  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "RiskAssessment.dmn"})
  public void testDunkelverarbeitungAbgelehnt() {
    Application neuantrag = DemoData.createNewApplication(20, "Porsche", "911");
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_application,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // send task
        
    assertThat(processInstance)
      .isEnded();    
  }

  
  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "RiskAssessment.dmn"})
  public void testHellManuelleVerarbeitung() {
    Application neuantrag = DemoData.createNewApplication(40, "Porsche", "911"); //I
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_application,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).job();
    execute(job()); // start event
    
    assertThat(processInstance).task()
      .hasDefinitionKey("userTaskAntragEntscheiden")
      .hasCandidateGroup("clerk");
    
    complete(task(), withVariables("approved", Boolean.TRUE));
    
    assertThat(processInstance).job();
    execute(job()); // send task
    
    assertThat(processInstance).isEnded();    
  }  
  
  
}
