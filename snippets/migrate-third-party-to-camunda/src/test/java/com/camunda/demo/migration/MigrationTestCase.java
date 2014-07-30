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

public class MigrationTestCase extends ProcessEngineTestCase {

  private static final String PROCESS_DEFINITION_KEY_SUPER = "migration-test-cases-super-process";
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


  @Deployment(resources = {"test-cases/super-process.bpmn", "test-cases/process-c.bpmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Deployment(resources = {"test-cases/super-process.bpmn", "test-cases/process-c.bpmn"})
  public void testScenario04() {
    String migrationScenario = "04";
    // Scenario 04: Jump into sub process and there into a parallel Gateway
    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIOS",  //
        withVariables( //
            "migrationScenario", migrationScenario, //
            "decision", "C"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded().isWaitingAtExactly("CallActivityC");
    
    // search for existing called process instance and assert it as well
    ProcessInstance piC = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piC).isStarted().isNotEnded().isWaitingAtExactly("UserTaskA");

    // now continue in the process execution
    assertThat(piC).task("UserTaskA");
    complete(task());
    
    assertThat(piC).isEnded();
  }
  


  @Deployment(resources = {"test-cases/super-process.bpmn", "test-cases/process-e.bpmn"})
  public void testScenario06() {
    String migrationScenario = "06";
    // Scenario 04: Jump into sub process and there into a parallel Gateway
    
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIOS",  //
        withVariables( //
            "migrationScenario", migrationScenario, //
            "decision", "E"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded().isWaitingAtExactly("CallActivityE");
    
    // search for existing called process instance and assert it as well
    ProcessInstance piC = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piC).isStarted().isNotEnded().isWaitingAtExactly("UserTaskDoTheWork");

    // now continue in the process execution
    assertThat(piC).task("UserTaskDoTheWork");
    complete(task());
    
    assertThat(piC).isEnded();
  }
  
  @Deployment(resources = {"test-cases/super-process.bpmn", "test-cases/process-f.bpmn"})
  public void testScenario07() {
    String migrationScenario = "07";
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIOS",  //
        withVariables( //
            "migrationScenario", migrationScenario, //
            "decision", "F"));

    // check that the process instance exists
    assertThat(piSuper).isStarted().isNotEnded().isWaitingAtExactly("CallActivityF");
    
    // search for existing called process instance and assert it as well
    ProcessInstance piC = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piC).isStarted().isNotEnded().isWaitingAtExactly("UserTaskDoTheWork");

    // now continue in the process execution
    assertThat(piC).task("UserTaskDoTheWork");
    complete(task());
    
    assertThat(piC).isEnded();
  }
 
  @Deployment(resources = {"test-cases/super-process.bpmn", "test-cases/process-f.bpmn"})
  public void testScenario07Timer() {
    String migrationScenario = "07";
    ProcessInstance piSuper = runtimeService().startProcessInstanceByMessage(
        PROCESS_DEFINITION_KEY_SUPER + "#MIGRATION_SCENARIOS",  //
        withVariables( //
            "migrationScenario", migrationScenario, //
            "decision", "F"));

    // search for existing called process instance and assert it as well
    ProcessInstance piC = runtimeService().createProcessInstanceQuery().superProcessInstanceId(piSuper.getId()).singleResult();
    assertThat(piC).isStarted().isNotEnded().isWaitingAtExactly("UserTaskDoTheWork");
    assertThat(piC).job("UserTaskDoTheWork");
    execute(job());   

    // now continue in the process execution
    assertThat(piC).task("UserTaskEscalation");
    complete(task());
    
    assertThat(piC).isEnded();
  }
}
