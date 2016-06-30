package com.camunda.consulting.example.validate_claims;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.value.TypedValue;
import org.camunda.spin.plugin.variable.SpinValues;
import org.camunda.spin.plugin.variable.value.JsonValue;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "validate-claims";

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
  public void testHappyPath() throws IOException, URISyntaxException {
    String claimsString = new String(
        Files.readAllBytes(Paths.get(getClass().getResource(
            "/claims/claims-rsp-ids-unique.json").toURI())));
    JsonValue claims = SpinValues.jsonValue(claimsString).create();
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
            withVariables("claims", claims, 
                "selectedRepairServicePartnerId", "11"));
    
    // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
    assertThat(processInstance).isEnded().hasPassed("End_validation_finished");
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    //ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testClaimsNotUnique() throws IOException, URISyntaxException {
    String claimsString = new String(
        Files.readAllBytes(Paths.get(getClass().getResource(
            "/claims/claims-rsp-ids-not-unique.json").toURI())));
    JsonValue claims = SpinValues.jsonValue(claimsString).create();
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
            withVariables("claims", claims));
    
    // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
    assertThat(processInstance).isEnded().hasPassed("End_validation_failed");

    HistoricVariableInstance result = historyService().createHistoricVariableInstanceQuery()
        .variableName("resultCheckUniqueRspID").singleResult();
    assertThat(result).isNotNull();
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    //ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  } 

  @Test
  @Deployment(resources = "process.bpmn")
  public void testRspIdNotSelected() throws IOException, URISyntaxException {
    String claimsString = new String(
        Files.readAllBytes(Paths.get(getClass().getResource(
            "/claims/claims-rsp-ids-unique.json").toURI())));
    JsonValue claims = SpinValues.jsonValue(claimsString).create();
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
            withVariables("claims", claims, 
                "selectedRepairServicePartnerId", "13"));
    
    // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
    assertThat(processInstance).isEnded().hasPassed("End_validation_failed");
    
    HistoricVariableInstance result = historyService().createHistoricVariableInstanceQuery()
        .variableName("resultCheckRspIDAgainstSelected").singleResult();
    assertThat(result).isNotNull();
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    //ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testSixClaims() throws IOException, URISyntaxException {
    String claimsString = new String(
        Files.readAllBytes(Paths.get(getClass().getResource(
            "/claims/six-claims.json").toURI())));
    JsonValue claims = SpinValues.jsonValue(claimsString).create();
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
            withVariables("claims", claims,
                "selectedRepairServicePartnerId", "1247001"));
    
    // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
    assertThat(processInstance).isEnded().hasPassed("End_validation_finished");
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    //ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testMandatoryFieldFormats() throws IOException, URISyntaxException {
    String claimsString = new String(
        Files.readAllBytes(Paths.get(getClass().getResource(
            "/claims/claims-not-mandatory-fieldvalues.json").toURI())));
    JsonValue claims = SpinValues.jsonValue(claimsString).create();
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
            withVariables("claims", claims,
                "selectedRepairServicePartnerId", "11"));
    
    // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
    assertThat(processInstance).isEnded().hasPassed("End_validation_finished");
    
    // To generate the coverage report for a single tests add this line as the last line of your test method:
    //ProcessTestCoverage.calculate(processInstance, rule.getProcessEngine());
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
