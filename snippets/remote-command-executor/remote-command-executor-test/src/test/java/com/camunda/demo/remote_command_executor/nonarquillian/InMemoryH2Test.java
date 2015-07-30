package com.camunda.demo.remote_command_executor.nonarquillian;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.demo.remote.command.executor.CommandExecutionService;
import com.camunda.demo.remote.command.executor.CommandExecutionServiceBean;
import com.camunda.demo.remote.command.executor.RemoteProcessEngineConfiguration;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "remote-command-executor";

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
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
  public void testExecution() {
    CommandExecutionService commandExecutionService = new CommandExecutionServiceBean(rule.getProcessEngine());
    RemoteProcessEngineConfiguration configuration = new RemoteProcessEngineConfiguration(commandExecutionService);
    ProcessEngine processEngineClient = configuration.buildProcessEngine();
    
    processEngineClient.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
  }

}
