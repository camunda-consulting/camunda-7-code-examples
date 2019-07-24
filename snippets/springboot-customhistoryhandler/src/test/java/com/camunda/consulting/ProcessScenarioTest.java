package com.camunda.consulting;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.run.ProcessRunner.ExecutableRunner;

import org.mockito.Mock;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import javax.annotation.PostConstruct;

import org.mockito.MockitoAnnotations;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessScenarioTest {

  private static final String PROCESS_DEFINITION_KEY = "customhistoryeventhandler";

  @Autowired
  private ProcessEngine processEngine;

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Rule @ClassRule
  public static ProcessEngineRule rule;

  @PostConstruct
  void initRule() {
    rule = TestCoverageProcessEngineRuleBuilder.create(processEngine).build();
  }

  @Before
  public void setup() {
    init(processEngine);
    MockitoAnnotations.initMocks(this);
  }

  @Mock
  private ProcessScenario myProcess;

  @Test
  @Deployment(resources="process.bpmn") // only required for process test coverage
  public void testHappyPath() {
    // Define scenarios by using camunda-bpm-assert-scenario:

    //ExecutableRunner starter = Scenario.run(myProcess) //
    //    .startByKey(PROCESS_DEFINITION_KEY);

    // when(myProcess.waitsAtReceiveTask(anyString())).thenReturn((messageSubscription) -> {
    //  messageSubscription.receive();
    // });
    // when(myProcess.waitsAtUserTask(anyString())).thenReturn((task) -> {
    //  task.complete();
    // });

    // OK - everything prepared - let's go and execute the scenario
    //Scenario scenario = starter.execute();

    // now you can do some assertions   
    //verify(myProcess).hasFinished("EndEvent");
  }

}
