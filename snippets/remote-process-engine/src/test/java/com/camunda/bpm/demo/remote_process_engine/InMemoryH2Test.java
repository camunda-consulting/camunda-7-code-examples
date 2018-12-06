package com.camunda.bpm.demo.remote_process_engine;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "RemoteProcessEngineProcess";

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
  public void testHappyPath() throws Exception {
    ProcessInstance processInstance = runtimeService()
        .startProcessInstanceByKey(PROCESS_DEFINITION_KEY, withVariables("name", "Falko"));

    assertThat(processInstance).isWaitingAt("ExternalServiceTask_InvokeBusinessLogic");

    ExternalTaskService externalTaskService = processEngine().getExternalTaskService();
    String workerId = "JUnit";
    List<LockedExternalTask> tasks = externalTaskService.fetchAndLock(1, workerId)
      .topic("invokeBusinessLogicDelegate", 60000)
      .execute();
    
    for (LockedExternalTask task : tasks) {
      // TODO CDI lookup
      InvokeBusinessLogicDelegate delegate = new InvokeBusinessLogicDelegate();
      Map<String, Object> variables = executeJavaDelegateWithExternalTask(task, delegate);
      externalTaskService.complete(task.getId(), workerId, variables);
    }
    
    assertThat(processInstance).isEnded();

    String greeting = (String) historyService().createHistoricVariableInstanceQuery().variableName("greeting").singleResult().getValue();
    assertEquals("Hello Falko!", greeting);
    
    // To inspect the DB, run the following line in the debugger
    // then connect your browser to: http://localhost:8082
    // and enter the JDBC URL: jdbc:h2:mem:camunda
    org.h2.tools.Server.createWebServer("-web").start();

  }

  private VariableMap executeJavaDelegateWithExternalTask(LockedExternalTask task, InvokeBusinessLogicDelegate delegate) throws Exception {
    AbstractExternalTaskDelegateExecution execution = new LockedExternalTaskDelegateExecution(task);
    execution.setRepositoryService(repositoryService());
    delegate.execute(execution);
    return execution.getVariables();
  }

}
