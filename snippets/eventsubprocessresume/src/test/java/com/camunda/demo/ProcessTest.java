package com.camunda.demo;

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

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import javax.annotation.PostConstruct;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ProcessTest {

  private static final String PROCESS_DEFINITION_KEY = "event-subprocess-resume";

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
  }

  @Test
  @Deployment(resources="process.bpmn")
  public void shouldDeploy(){}

  @Test
  @Deployment(resources="process.bpmn") // only required for process test coverage
  public void shouldRunHappyPath() {

    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertThat(processInstance).isWaitingAt("Task_DoThis");
    runtimeService().correlateMessage("cancel_instance");

    assertThat(processInstance).isWaitingAt("Task_CheckCancellation");
    complete(task(),withVariables("cancel", false));

    assertThat(processInstance).isWaitingAt("Task_DoThat").isWaitingAt("Task_ResumeProcessInstance");
    execute(job()); // async after
    complete(task());

    assertThat(processInstance).isEnded();

  }

}
