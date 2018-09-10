package com.camunda.demo.underwriting;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricCaseActivityInstance;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricCaseActivityInstanceEntity;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.demo.underwriting.domain.Application;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  // enable more detailed logging
  static {
    // LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    // LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "underwriting-process.bpmn")
  public void testParseBpmn() {
    // nothing is done here, as we just want to check for exceptions during
    // deployment
  }

  @Test
  @Deployment(resources = "underwriting-case.cmmn")
  public void testParseCmmn() {
    // nothing is done here, as we just want to check for exceptions during
    // deployment
  }

  @Test
  @Deployment(resources = "determine-underwriter.dmn")
  public void testParseDmn() {
    // nothing is done here, as we just want to check for exceptions during
    // deployment
  }
  
  @Test
  public void testExtendedRule() {
    
    Application application = new Application();
    application.setAge(70);
    application.setRisk("low");
    application.setType("extended");
    
    DmnEngine dmnEngine = new DefaultDmnEngineConfiguration().buildEngine();
    DmnDecisionTableResult decisionResult = dmnEngine.evaluateDecisionTable(
        dmnEngine.parseDecision("determineUnderwriter", this.getClass().getResourceAsStream("/determine-underwriter.dmn")),
        Variables.createVariables().putValue("application", application));
    
    System.out.println(decisionResult.get(0));
  }
  
  @Test
  @Deployment(resources = "underwriting-case.cmmn")
  public void testUnderwritingCase() {
    ProcessEngine processEngine = processEngine();

    // HashMap containing variables to be added to case instance
    VariableMap variables = Variables.createVariables() //
        .putValue("application", new Application()) //
        .putValue("underwriter", "demo");

    // create the case instance
    CaseInstance caseInstance = processEngine.getCaseService().createCaseInstanceByKey("underwriting", variables);

    // load tasks via Query API and assert that we have one task created
    // (corresponding to one active Activity)
    List<Task> tasks = processEngine.getTaskService().createTaskQuery().list();
    assertEquals(1, tasks.size());
    // use camunda assertions to check we created the right task:
    assertThat(tasks.get(0)).hasDefinitionKey("PI_humanTaskDecide");

    // load all so called "executions" of a Case Instance, basically
    // corresponding to Activities in our case
    List<CaseExecution> caseExecutions = processEngine.getCaseService().createCaseExecutionQuery().list();
    for (CaseExecution caseExecution : caseExecutions) {
      if (caseExecution.isAvailable()) {
        System.out.println("Impossible to start ('available'): " + caseExecution.getActivityName() + " [" + caseExecution.getActivityType() + "]");
      } else if (caseExecution.isEnabled()) {
        System.out.println("Possible to start ('enabled'): " + caseExecution.getActivityName() + " [" + caseExecution.getActivityType() + "]");
      } else if (caseExecution.isActive()) {
        System.out.println("Running ('active'): " + caseExecution.getActivityName() + " [" + caseExecution.getActivityType() + "]");
      }
    }
    processEngine.getCaseService().setVariable(caseInstance.getId(), "approved", Boolean.TRUE);

    // complete task - changes the state of the case instance
    processEngine.getTaskService().complete(tasks.get(0).getId());

    // we could check completed activities now:
    processEngine.getHistoryService().createHistoricCaseActivityInstanceQuery().caseInstanceId(caseInstance.getId()).list();

    if (true)
      return;

    System.out.println("####################");
    caseExecutions = processEngine.getCaseService().createCaseExecutionQuery().caseInstanceId(caseInstance.getId()).list();
    for (CaseExecution otherCaseExecution : caseExecutions) {
      System.out.println(otherCaseExecution.getActivityName() + " -> " + ((CaseExecutionEntity) otherCaseExecution).getCurrentState().toString());
    }
    System.out.println("####################");
    List<HistoricCaseActivityInstance> caseActivityInstances = processEngine.getHistoryService().createHistoricCaseActivityInstanceQuery()
        .caseInstanceId(caseInstance.getId()).list();
    for (HistoricCaseActivityInstance historicCaseActivityInstance : caseActivityInstances) {
      System.out.println(historicCaseActivityInstance.getCaseActivityName() + " -> "
          + ((HistoricCaseActivityInstanceEntity) historicCaseActivityInstance).getCaseActivityInstanceState());
    }
    System.out.println("####################");

    caseInstance = processEngine.getCaseService().createCaseInstanceQuery().caseInstanceId(caseInstance.getId()).singleResult();
    assertTrue(caseInstance.isCompleted());

    HistoricCaseInstance historicCaseInstance = processEngine.getHistoryService().createHistoricCaseInstanceQuery().caseInstanceId(caseInstance.getId())
        .singleResult();
    assertTrue(historicCaseInstance.isCompleted());

    processEngine.getCaseService().closeCaseInstance(caseInstance.getId());

    assertEquals(0, processEngine.getCaseService().createCaseInstanceQuery().caseInstanceId(caseInstance.getId()).count());

  }

  @Test
  @Deployment(resources = { "underwriting-case.cmmn", "underwriting-process.bpmn", "determine-underwriter.dmn" })
  public void testUnderwritingProcessIncludingCase() {
    Application application = new Application();
    application.setType("extended"); // peter
    VariableMap variables = Variables.createVariables().putValue("application", application);

    ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByMessage(Constants.MSG_START_ELECTRONIC_APPLICATION, variables);

    assertThat(pi).isWaitingAtExactly("CallActivityCaseUnderwriting");
    CaseInstance caseInstance = processEngine().getCaseService().createCaseInstanceQuery() //
        // .variableValueEquals(Constants.VAR_NAME_PROCESS_INSTANCE_ID,
        // pi.getProcessInstanceId()) //
        .singleResult();

    assertNotNull(caseInstance);
    // assertEquals(pi.getProcessInstanceId(), caseInstance.get());

    Task task = processEngine().getTaskService().createTaskQuery().caseInstanceId(caseInstance.getId()).singleResult();
    assertNotNull(task);
    assertEquals("PI_humanTaskDecide", task.getTaskDefinitionKey());

    // on <= 7.2.x set variable in a seperate statement because of
    // https://app.camunda.com/jira/browse/CAM-3261
    // processEngine().getCaseService().setVariable(caseInstance.getId(),
    // "approved", Boolean.TRUE);
    processEngine().getTaskService().complete(task.getId(), Variables.createVariables().putValue("approved", Boolean.TRUE));

    assertThat(pi).isEnded().hasPassed("endEventApplicationProcessed");

    System.out.println(processEngine().getHistoryService().createHistoricCaseActivityInstanceQuery().caseInstanceId(caseInstance.getId()).list());

    // assert case instance
    assertEquals(1, processEngine().getHistoryService().createHistoricCaseActivityInstanceQuery().caseInstanceId(caseInstance.getId()) //
        .caseActivityId("PI_milestoneApproved").count());

    HistoricCaseInstance historicCaseInstance = processEngine().getHistoryService().createHistoricCaseInstanceQuery().caseInstanceId(caseInstance.getId())
        .singleResult();
    assertTrue(historicCaseInstance.isClosed());
    // assertTrue(historicCaseInstance.isCompleted());

    // processEngine().getCaseService().closeCaseInstance(caseInstance.getId());
    assertEquals(0, processEngine().getCaseService().createCaseInstanceQuery().caseInstanceId(caseInstance.getId()).count());

  }

  @Test
  @Deployment(resources = { "underwriting-case.cmmn", "underwriting-process.bpmn", "determine-underwriter.dmn" })
  public void testAssignmentRules() {
    Application application = new Application();
    application.setAge(30);
    application.setRisk("low");
    application.setType("basic");

    ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByMessage( //
        Constants.MSG_START_ELECTRONIC_APPLICATION, //
        Variables.createVariables().putValue("application", application));

    Task task = processEngine().getTaskService().createTaskQuery().singleResult();

    System.out.println(processEngine().getRuntimeService().getVariable(pi.getId(), "capableUnderwriters"));
    System.out.println(processEngine().getTaskService().getIdentityLinksForTask(task.getId()).toString());

    
//    assertThat(processEngine().getTaskService().getIdentityLinksForTask(task.getId()), //
//        hasItem(hasProperty(""), is("")));
//    
//    "[demo, mary, peter]"
    processEngine().getTaskService().complete(task.getId(), Variables.createVariables().putValue("approved", true));
  }
  
  @Test
  @Deployment(resources = { "underwriting-case.cmmn", "underwriting-process.bpmn", "determine-underwriter.dmn" })
  public void testAssignmentRules2() {
    Application application = new Application();
    application.setAge(70);
    application.setRisk("low");
    application.setType("extended");

    ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByMessage( //
        Constants.MSG_START_ELECTRONIC_APPLICATION, //
        Variables.createVariables().putValue("application", application));

    Task task = processEngine().getTaskService().createTaskQuery().singleResult();
    System.out.println(processEngine().getRuntimeService().getVariable(pi.getId(), "capableUnderwriters"));
    System.out.println(processEngine().getTaskService().getIdentityLinksForTask(task.getId()).toString());
//    assertEquals("[demo]", processEngine().getTaskService().getIdentityLinksForTask(task.getId()).toString());
    
    processEngine().getTaskService().complete(task.getId(), Variables.createVariables().putValue("approved", true));

  }
}
