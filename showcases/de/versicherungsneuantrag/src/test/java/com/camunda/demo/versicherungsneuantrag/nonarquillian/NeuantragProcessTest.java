package com.camunda.demo.versicherungsneuantrag.nonarquillian;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;
import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class NeuantragProcessTest {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "versicherungsneuantrag";

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
  @Deployment(resources = {"NeuantragKfz.bpmn", "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = {"NeuantragKfz.bpmn", "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn"})
  public void testDunkelverarbeitungPiliciert() {
    Neuantrag neuantrag = DemoData.createNeuantrag(40, true, "VW", "Golf V");
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_neuantrag,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).isEnded()
      .hasPassedInOrder("BusinessRuleTaskAntragAutomatischPruefen", "ServiceTaskPoliceAusstellen", "SendTaskPoliceZusenden", "EndEventAntragPoliciert");    
  }

  @Test
  @Deployment(resources = {"NeuantragKfz.bpmn", "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn"})
  public void testDunkelverarbeitungAbgelehnt() {
    Neuantrag neuantrag = DemoData.createNeuantrag(20, true, "Porsche", "911");
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_neuantrag,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).isEnded()
      .hasPassedInOrder("BusinessRuleTaskAntragAutomatischPruefen", "SendTaskAblehnungZusenden", "EndEventAntragAbgelehnt");    
  }

  
  @Test
  @Deployment(resources = {"NeuantragKfz.bpmn", "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn"})
  public void testHell() {
    Neuantrag neuantrag = DemoData.createNeuantrag(30, false, "BMW", "525i");
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_neuantrag,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).isWaitingAtExactly("CallActivityAntragManuellPruefen");
    
    // TODO
  }  
}
