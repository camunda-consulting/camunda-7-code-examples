package org.camunda.showcase.underwritingBPMN_CMMN;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.util.Map;

import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {
  
  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "underwritingBPMN_CMMN";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = { "underwritingBPMN_CMMN.bpmn", 
                            "manual_underwriting.cmmn" })
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }
  
  @Test
  @Deployment(resources = "underwritingBPMN_CMMN.bpmn")
  public void startProcessWithCaseElectronical() {
    ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByMessage("electronicApplication");
    assertThat(pi).isNotEnded();
    assertThat(pi).isWaitingAt("reviewData");
  }
  
  @Test
  @Deployment(resources = "underwritingBPMN_CMMN.bpmn")
  public void startProcessWithCasePaper() {
    ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByMessage("paperApplication");
    assertThat(pi).isNotEnded();
    assertThat(pi).isWaitingAt("enterApplicationData");
  }
  
  @Test
  @Deployment(resources = "underwritingBPMN_CMMN.bpmn")
  public void endWithDuplicate() {
    Map<String, Object> vars = withVariables("applier", "john duplicate");
    ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByMessage("electronicApplication", vars);
    assertThat(pi).isEnded();
    assertThat(pi).hasPassed("EndDuplicate");
  }
  
  @Test
  @Deployment(resources = "manual_underwriting.cmmn")
  public void startCaseFromScratch() {
    CaseInstance ci = rule.getProcessEngine().getCaseService().withCaseDefinitionByKey("Case_manual_underwriting").create();
    
    assertThat(ci).isNotNull();
  }
  
  @Test
  @Deployment(resources = { "underwritingBPMN_CMMN.bpmn", 
                            "manual_underwriting.cmmn" })
  public void startCase() {
    ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByMessage("electronicApplication");
    Task reviewData = rule
        .getTaskService()
        .createTaskQuery()
        .processInstanceId(pi.getProcessInstanceId())
        .singleResult();
    complete(reviewData);
    assertThat(pi).isNotEnded();
    assertThat(pi).isWaitingAt("underwriteApplication");
    CaseInstance ci = rule
        .getProcessEngine()
        .getCaseService()
        .createCaseInstanceQuery()
        .caseDefinitionKey("Case_manual_underwriting")
        .singleResult();
    assertThat(ci).isNotNull();
  }

}
