package org.camunda.showcase.underwritingBPMN_CMMN;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.CaseExecution;
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
    LogFactory.useJdkLogging();
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
    CaseInstance ci = rule
        .getProcessEngine()
        .getCaseService()
        .withCaseDefinitionByKey("Case_manual_underwriting")
        .create();
    assertThat(ci).isNotNull();
  }
  
  @Test
  @Deployment(resources = "manual_underwriting.cmmn")
  public void runCaseWhenStartedFromScratch() {
    CaseInstance ci = rule
        .getProcessEngine()
        .getCaseService()
        .withCaseDefinitionByKey("Case_manual_underwriting")
        .create();
    CaseExecution stage = rule
        .getProcessEngine()
        .getCaseService()
        .createCaseExecutionQuery()
        .activityId("PI_evaluation_stage")
        .singleResult();
    assertThat(stage).isNotNull();
    List<Task> tasks = rule
        .getProcessEngine()
        .getTaskService()
        .createTaskQuery()
        .caseInstanceId(ci.getId())
        .list();
    assertThat(tasks).isNotEmpty();
    assertThat(tasks).hasSize(1);
    Task task = tasks.get(0);
    assertThat(task).hasDefinitionKey("PI_decide_task");
  }
  
  @Test
  @Deployment(resources = "manual_underwriting.cmmn")
  public void completeCase() {
    CaseInstance ci = rule
        .getProcessEngine()
        .getCaseService()
        .withCaseDefinitionByKey("Case_manual_underwriting")
        .create();
    // complete Task
    Task task = rule.getTaskService()
        .createTaskQuery()
        .caseInstanceId(ci.getId())
        .singleResult();
    rule.getTaskService().complete(task.getId());
    // complete Case
    rule.getProcessEngine().getCaseService().withCaseExecution(ci.getId()).complete();
    List<CaseExecution> cases = rule
        .getProcessEngine()
        .getCaseService()
        .createCaseExecutionQuery()
        .caseExecutionId(ci.getId())
        .list();
    assertThat(cases).isNotEmpty();
    // close Case
    rule.getProcessEngine().getCaseService().withCaseExecution(ci.getId()).close();
    cases = rule.getProcessEngine()
        .getCaseService()
        .createCaseExecutionQuery()
        .caseExecutionId(ci.getId())
        .list();
    assertThat(cases).isEmpty();
  }
  
  @Test
  @Deployment(resources = { "underwritingBPMN_CMMN.bpmn", 
                            "manual_underwriting.cmmn" })
  public void startCaseFromProcess() {
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

  @Test
  @Deployment(resources = { "underwritingBPMN_CMMN.bpmn", 
                            "manual_underwriting.cmmn" })
  public void completeCaseFromProcessManually() {
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
    Map<String, Object> caseVars = rule.getProcessEngine().getCaseService().getVariables(ci.getId());
    assertThat(caseVars).containsKey("callActivityId");
    Task task = rule
        .getTaskService()
        .createTaskQuery()
        .caseInstanceId(ci.getId())
        .singleResult();
    rule.getTaskService().complete(task.getId());
    rule.getProcessEngine().getCaseService().withCaseExecution(ci.getId()).complete();
    rule.getProcessEngine().getCaseService().withCaseExecution(ci.getId()).close();    
    assertThat(pi).isEnded();
  }

}
