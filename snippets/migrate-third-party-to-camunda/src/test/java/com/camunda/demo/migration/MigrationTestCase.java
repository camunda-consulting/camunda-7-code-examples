package com.camunda.demo.migration;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.withVariables;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineTestCase;
import org.camunda.bpm.engine.test.mock.MockExpressionManager;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class MigrationTestCase extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY_SUPER = "migration-example-super-process";
  
  private static ProcessEngine currentProcessEngine;

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
  }
  
  @Override
  protected void initializeProcessEngine() {
    if (currentProcessEngine!=null) {
      processEngine = currentProcessEngine;
    }
    else {
      StandaloneInMemProcessEngineConfiguration configuration = new StandaloneInMemProcessEngineConfiguration();
      configuration.setExpressionManager(new MockExpressionManager());
      configuration.getProcessEnginePlugins().add(new MigrationProcessEnginePlugin());
      processEngine = configuration.buildProcessEngine();
  
      // init camunda-bpm-assert
      init(processEngine);
      
      // and remember the engine (would normally done by TestHelper)
      currentProcessEngine = processEngine;
    }
  }


  /**
   * Just tests if the process definition is deployable.
   */
  @Deployment(resources = {"super-process.bpmn", "called-process-1.bpmn", "called-process-2.bpmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Deployment(resources = {"super-process.bpmn", "called-process-1.bpmn", "called-process-2.bpmn"})
  public void testMigrationScenario01() {
    // Scenario 01: Jump into second hierarchy and there into the Human Task within a Sub Process started by an error
    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIO_01", withVariables("migrationScenario", "01"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded() // 
      // and is in correct node
      .isWaitingAtExactly("CallActivityProcessA") //
      // TODO: And has not passed "ServiceTaskCallSomeService"      
      ;
    
    // search for existing called process instance and assert it as well
    ProcessInstance piA = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piA).isStarted().isNotEnded().isWaitingAtExactly("CallActivityProcessB");
    
    // and the next pi down the hierarchy
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piA.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded().isWaitingAtExactly("UserTaskHandleManually");

    
  }

  @Deployment(resources = {"super-process.bpmn", "called-process-1.bpmn", "called-process-2.bpmn"})
  public void testMigrationScenario02() {
    // Scenario 01: Jump into second hierarchy and there into the Human Task within a Sub Process started by an error
    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIO_02", withVariables("migrationScenario", "02"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded() // 
      // and is in correct node
      .isWaitingAtExactly("CallActivityProcessB") //
      // TODO: And has not passed "ServiceTaskCallSomeService"      
      ;
    
    // and the next pi down the hierarchy
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded().isWaitingAtExactly("UserTaskDoTheWork");
  }
 
}
