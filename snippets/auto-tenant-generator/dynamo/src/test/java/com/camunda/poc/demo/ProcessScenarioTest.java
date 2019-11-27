package com.camunda.poc.demo;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.spring.boot.starter.test.helper.StandaloneInMemoryTestConfiguration;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.camunda.bpm.scenario.ProcessScenario;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;


/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessScenarioTest {

  private static final String PROCESS_DEFINITION_KEY = "poc-camunda-mindstrong";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Rule
  public final ProcessEngineRule processEngine = new StandaloneInMemoryTestConfiguration().rule();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Mock
  private ProcessScenario myProcess;

  @Test
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
