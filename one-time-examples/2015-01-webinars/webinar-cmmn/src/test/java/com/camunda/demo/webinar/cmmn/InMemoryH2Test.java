package com.camunda.demo.webinar.cmmn;

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.history.HistoricCaseActivityInstance;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseSentryPartEntity;
import org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState;
import org.camunda.bpm.engine.impl.persistence.entity.HistoricCaseActivityInstanceEntity;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

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
  @Deployment(resources = "underwriting.bpmn")
  public void testParseBpmn() {
    // nothing is done here, as we just want to check for exceptions during
    // deployment
  }

  @Test
  @Deployment(resources = "underwriting.cmmn")
  public void testParseCmmn() {
    // nothing is done here, as we just want to check for exceptions during
    // deployment
  }

  @Test
  @Deployment(resources = "underwriting.cmmn")
  public void testUnderwritingCase() {
    CaseInstance caseInstance = processEngine().getCaseService().createCaseInstanceByKey("underwriting");

    List<Task> tasks = processEngine().getTaskService().createTaskQuery().initializeFormKeys().list();
    assertEquals(1, tasks.size());

    assertThat(tasks.get(0)).hasDefinitionKey("PI_humanTaskDecide");
    assertEquals("app:form-decide-application.jsf", tasks.get(0).getFormKey());

    List<CaseExecution> caseExecutions = processEngine().getCaseService().createCaseExecutionQuery().list();// .caseInstanceId(caseInstanceId)
    for (CaseExecution caseExecution : caseExecutions) {
      if (caseExecution.isEnabled()) {
        System.out.println("Possible: " + caseExecution.getActivityName());
      }
    }

    processEngine().getCaseService().setVariable(caseInstance.getId(), "approved", Boolean.TRUE);

    complete(tasks.get(0));

    System.out.println("####################");
    caseExecutions = processEngine().getCaseService().createCaseExecutionQuery().caseInstanceId(caseInstance.getId()).list();
    for (CaseExecution otherCaseExecution : caseExecutions) {
      System.out.println(otherCaseExecution.getActivityName() + " -> " + ((CaseExecutionEntity) otherCaseExecution).getCurrentState().toString());
    }
    System.out.println("####################");
    List<HistoricCaseActivityInstance> caseActivityInstances = processEngine().getHistoryService().createHistoricCaseActivityInstanceQuery()
        .caseInstanceId(caseInstance.getId()).list();
    for (HistoricCaseActivityInstance historicCaseActivityInstance : caseActivityInstances) {
      System.out.println(historicCaseActivityInstance.getCaseActivityName() + " -> "
          + ((HistoricCaseActivityInstanceEntity) historicCaseActivityInstance).getCaseActivityInstanceState());
    }
    System.out.println("####################");

    caseInstance = processEngine().getCaseService().createCaseInstanceQuery().caseInstanceId(caseInstance.getId()).singleResult();
    assertTrue(caseInstance.isCompleted());

    HistoricCaseInstance historicCaseInstance = processEngine().getHistoryService().createHistoricCaseInstanceQuery().caseInstanceId(caseInstance.getId())
        .singleResult();
    assertTrue(historicCaseInstance.isCompleted());

    processEngine().getCaseService().closeCaseInstance(caseInstance.getId());

    assertEquals(0, processEngine().getCaseService().createCaseInstanceQuery().caseInstanceId(caseInstance.getId()).count());

  }

  @Test
  @Deployment(resources = {"underwriting.cmmn", "underwriting.bpmn"})
  public void testUnderwritingProcessIncludingCase() {
    
    ProcessInstance pi = processEngine().getRuntimeService().startProcessInstanceByMessage(Constants.MSG_START_ELECTRONIC_APPLICATION);
    
    assertThat(pi).task("userTaskReviewData");
    complete(task());
    
    assertThat(pi).isWaitingFor(Constants.MSG_UNDERWRITING_FINISHED);
    CaseInstance caseInstance = processEngine().getCaseService().createCaseInstanceQuery() //
          .variableValueEquals(Constants.VAR_NAME_PROCESS_INSTANCE_ID, pi.getProcessInstanceId()) //
          .singleResult();
    
    assertNotNull(caseInstance);

    Task task = processEngine().getTaskService().createTaskQuery().caseInstanceId(caseInstance.getId()).singleResult();
    assertNotNull(task);
    assertEquals("PI_humanTaskDecide", task.getTaskDefinitionKey());

    // set variable in case instead of just completing task because of bug: https://app.camunda.com/jira/browse/CAM-3261
    processEngine().getCaseService().setVariable(caseInstance.getId(), "approved", Boolean.TRUE);
    processEngine().getTaskService().complete(task.getId());

    assertThat(pi).isEnded().hasPassed("endEventApplicationProcessed");
  }
}
