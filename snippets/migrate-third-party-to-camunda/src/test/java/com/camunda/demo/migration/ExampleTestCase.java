package com.camunda.demo.migration;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.*;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

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
public class ExampleTestCase extends ProcessEngineTestCase {

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
  @Deployment(resources = {"example/super-process.bpmn", "example/process-a.bpmn", "example/process-b.bpmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Deployment(resources = {"example/super-process.bpmn", "example/process-a.bpmn", "example/process-b.bpmn"})
  public void testNormalRun() {
    ProcessInstance piSuper = runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY_SUPER, withVariables("decision", "A"));
    
    assertThat(piSuper).isStarted().isNotEnded() // 
      .isWaitingAtExactly("CallActivityProcessA") //
      .hasPassed("ServiceTaskCallSomeService");
    
    // search for existing called process instance and assert it as well
    ProcessInstance piA = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piA).isStarted().isNotEnded() //
      .isWaitingAtExactly("UserTaskDoSomething") //
      .task();
    complete(task());
    
    // and the next pi down the hierarchy
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piA.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded() //
      .isWaitingAtExactly("UserTaskDoTheWork") //
      .hasPassed("ServiceTaskWithError") //
      .task();
    complete(task());
    
    assertThat(piB).isEnded();
    assertThat(piA).isEnded();
    assertThat(piSuper).isEnded();
  }
  
  @Deployment(resources = {"example/super-process.bpmn", "example/process-b.bpmn"})
  public void testErrorHandlingSubProcess() {
    ProcessInstance piSuper = runtimeService().startProcessInstanceByKey(
        PROCESS_DEFINITION_KEY_SUPER, withVariables("decision", "B", "throwError", Boolean.TRUE));
    
    assertThat(piSuper).isStarted().isNotEnded() // 
      .isWaitingAtExactly("CallActivityProcessB") //
      .hasPassed("ServiceTaskCallSomeService");
        
    // sub process
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded() //
      .isWaitingAtExactly("UserTaskHandleManually") //
      .hasPassed("ServiceTaskWithError", "ServiceTaskDoCompensationLogic") //
      .task();
    complete(task());

    assertThat(piB) //
      .isWaitingAtExactly("UserTaskDoTheWork") //
      .task();
    complete(task());

    assertThat(piB).isEnded();
    assertThat(piSuper).isEnded();
  }  
    
  @Deployment(resources = {"example/super-process.bpmn",  "example/process-b.bpmn"})
  public void testMigrationIntoErrorSubProcess() {
    // Scenario 01: Jump into second hierarchy and there into the Human Task within a Sub Process started by an error    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIO_03", withVariables("migrationScenario", "03"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded().isWaitingAtExactly("CallActivityProcessB");
    
    // search for existing called process instance and assert it as well
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded().isWaitingAtExactly("UserTaskHandleManually");

    // now continue in the process execution
    assertThat(piB).task("UserTaskHandleManually");
    complete(task());
    
    assertThat(piB).isNotEnded().isWaitingAtExactly("UserTaskDoTheWork").task("UserTaskDoTheWork");
    complete(task());
    
    assertThat(piB).isEnded();
    assertThat(piSuper).isEnded();
  }
  
  @Deployment(resources = {"example/super-process.bpmn", "example/process-a.bpmn", "example/process-b.bpmn"})
  public void testMigrationScenario01() {
    // Scenario 01: Jump into second hierarchy and there into the Human Task within a Sub Process started by an error    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIO_01", withVariables("migrationScenario", "01"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded() // 
      // and is in correct node
      .isWaitingAtExactly("CallActivityProcessA") //
      // TODO: And has not passed "ServiceTaskCallSomeService" (added in camunda-bpm-assert 1.1 once it is released)       
      ;
    
    // search for existing called process instance and assert it as well
    ProcessInstance piA = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piA).isStarted().isNotEnded().isWaitingAtExactly("CallActivityProcessB");
    
    // and the next pi down the hierarchy
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piA.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded().isWaitingAtExactly("UserTaskHandleManually");

    // now continue in the process execution
    assertThat(piB).task("UserTaskHandleManually");
    complete(task());
    
    assertThat(piB).isNotEnded().isWaitingAtExactly("UserTaskDoTheWork").task("UserTaskDoTheWork");
    complete(task());

    assertThat(piB).isEnded();
    assertThat(piA).isEnded();
    assertThat(piSuper).isEnded();
  }

  @Deployment(resources = {"example/super-process.bpmn", "example/process-a.bpmn", "example/process-b.bpmn"})
  public void testMigrationScenario02() {
    // Scenario 02: Jump into second hierarchy and there into the Human Task within a Sub Process started by an error
    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIO_02", withVariables("migrationScenario", "02"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded() // 
      // and is in correct node
      .isWaitingAtExactly("CallActivityProcessB") //
      // TODO: And has not passed "ServiceTaskCallSomeService" (added in camunda-bpm-assert 1.1 once it is released)    
      ;
    
    // and the next pi down the hierarchy
    ProcessInstance piB = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piB).isStarted().isNotEnded().isWaitingAtExactly("UserTaskDoTheWork");
    
    // now continue in the process execution
    assertThat(piB).task("UserTaskDoTheWork");
    complete(task());

    assertThat(piB).isEnded();
    assertThat(piSuper).isEnded();
  }
 
}
