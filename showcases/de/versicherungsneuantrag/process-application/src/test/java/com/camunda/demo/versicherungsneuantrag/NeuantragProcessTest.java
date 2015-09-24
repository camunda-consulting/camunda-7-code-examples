package com.camunda.demo.versicherungsneuantrag;

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

import com.camunda.demo.versicherungsneuantrag.ProcessVariables;
import com.camunda.demo.versicherungsneuantrag.model.Neuantrag;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class NeuantragProcessTest {


  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_BPMN_FILE = "NeuantragKfz.bpmn";
  private static final String PROCESS_DEFINITION_KEY = "versicherungsneuantrag";
//  private static final String PROCESS_BPMN_FILE = "NeuantragKfzMitDokumentenerstellung.bpmn";
//  private static final String PROCESS_DEFINITION_KEY = "versicherungsneuantragMitDokumentenerstellung";

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
  @Deployment(resources = {PROCESS_BPMN_FILE, "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn", "Risikopruefung.dmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = {PROCESS_BPMN_FILE, "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn", "Risikopruefung.dmn"})
  public void testDunkelverarbeitungPoliciert() {
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
  @Deployment(resources = {PROCESS_BPMN_FILE, "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn", "Risikopruefung.dmn"})
  public void testDunkelverarbeitungAbgelehnt() {
    Neuantrag neuantrag = DemoData.createNeuantrag(20, true, "Porsche", "911");
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_neuantrag,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance)
      .hasPassedInOrder("BusinessRuleTaskAntragAutomatischPruefen", "SendTaskAblehnungZusenden", "EndEventAntragAbgelehnt")
      .isEnded();    
  }

  
  @Test
  @Deployment(resources = {PROCESS_BPMN_FILE, "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn", "Risikopruefung.dmn"})
  public void testHell() {
    Neuantrag neuantrag = DemoData.createNeuantrag(30, false, "BMW", "525"); //I
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_neuantrag,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).isWaitingAtExactly("CallActivityAntragManuellPruefen");
    
    // TODO
  }  
  
  @Test
  @Deployment(resources = {PROCESS_BPMN_FILE, "Neuantragspruefung.cmmn", "DokumentAnfordern.bpmn", "Risikopruefung.dmn"})
  public void testCase() {
    Neuantrag neuantrag = DemoData.createNeuantrag(30, false, "BMW", "525i");
    
    VariableMap variables = Variables.createVariables();
    variables.putValue(
        ProcessVariables.VAR_NAME_neuantrag,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, variables);
    
    assertThat(processInstance).isWaitingAtExactly("CallActivityAntragManuellPruefen");
    
    CaseInstance caseInstance = processEngine().getCaseService().createCaseInstanceQuery()
      .superProcessInstanceId(processInstance.getId()).singleResult();

    CaseExecution caseExecution = processEngine().getCaseService().createCaseExecutionQuery()
        .caseInstanceId(caseInstance.getId()) //
        .activityId("PI_humanTaskAntragsBeurteilung").singleResult();

    // Check lister for follow-up date when activating one task
    // before no follow-up date
    Task task = processEngine().getTaskService().createTaskQuery() //
        .caseInstanceId(caseInstance.getId())
        .taskDefinitionKey("PI_humanTaskAntragEntscheiden")
        .singleResult();
    assertNull(task.getFollowUpDate());

    processEngine().getCaseService().manuallyStartCaseExecution(caseExecution.getId());
    
    // after: follow-up date
    task = processEngine().getTaskService().createTaskQuery() //
      .caseInstanceId(caseInstance.getId())
      .taskDefinitionKey("PI_humanTaskAntragEntscheiden")
      .singleResult();
    assertNotNull(task.getFollowUpDate());

    // complete activity via human task -> follow up date will be removed
    task = processEngine().getTaskService().createTaskQuery() //
        .caseInstanceId(caseInstance.getId())
        .taskDefinitionKey("PI_humanTaskAntragsBeurteilung")
        .singleResult();
    complete(task);  
    
    // after: follow-up date
    task = processEngine().getTaskService().createTaskQuery() //
      .caseInstanceId(caseInstance.getId())
      .taskDefinitionKey("PI_humanTaskAntragEntscheiden")
      .singleResult();
    assertNull(task.getFollowUpDate());  

  }  
}
