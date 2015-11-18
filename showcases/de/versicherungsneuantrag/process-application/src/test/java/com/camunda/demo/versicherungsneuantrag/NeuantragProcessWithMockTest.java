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
public class NeuantragProcessWithMockTest {


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
  @Deployment(resources = {"Versicherungsneuantrag.bpmn", "Dokumentenanforderung.bpmn", "Risikopruefung.dmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment    
  }

  @Test
  @Deployment(resources = {"Versicherungsneuantrag.bpmn", "Risikopruefung.dmn"})
  public void testDunkelverarbeitungPoliciert() {
    Neuantrag neuantrag = DemoData.createNeuantrag(40, true, "VW", "Golf V");
    
    VariableMap variables = Variables.createVariables().putValue(
        ProcessVariables.VAR_NAME_neuantrag,
        Variables.objectValue(neuantrag).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("versicherungsneuantrag", variables);
    
    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // send task
        
    // Dunkelverarbeitung!
    assertThat(processInstance).isEnded()
      .hasPassed("BusinessRuleTaskAntragAutomatischPruefen", "ServiceTaskPoliceAusstellen", "SendTaskPoliceZusenden", "EndEventAntragPoliciert");    
  }

}
