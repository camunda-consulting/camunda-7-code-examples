package com.camunda.consulting.single_task_process;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "single-task-process";
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

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
  @Deployment(resources = "single-task-process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = "single-task-process.bpmn")
  public void testHappyPath() throws ParseException {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
        withVariables("taskName", "My single Task",
            "assignee", "john",
            "candidateGroups", "management",
            "dueDate", "2016-09-23",
            "followUpDate", "2016-09-16",
            "priority", "10",
            "formKey", "embedded:app:forms/task-form.html",
            "description", "some documentation for this task"));
    
    assertThat(processInstance).isWaitingAt("singleUserTask")
        .task()
        .hasName("My single Task")
        .hasDueDate(dateFormat.parse("23.09.2016"))
        .isAssignedTo("john");
    assertThat(taskQuery().initializeFormKeys().singleResult().getFormKey()).isEqualTo("embedded:app:forms/task-form.html");
    assertThat(taskQuery().taskCandidateGroup("management").includeAssignedTasks().list()).isNotEmpty();
    assertThat(task().getDescription()).isEqualTo("some documentation for this task");
    
  }

  @Test
  @Deployment(resources = "single-task-process.bpmn")
  public void testEmptyValues() throws ParseException {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY,
        withVariables("taskName", "My single Task",
            "assignee", "john",
            "candidateGroups", "",
            "dueDate", null,
            "followUpDate", null,
            "priority", null,
            "formKey", "embedded:app:forms/task-form.html",
            "description", null));
    
    assertThat(processInstance).isWaitingAt("singleUserTask")
        .task()
        .hasName("My single Task")
        .isAssignedTo("john");
    assertThat(taskQuery().initializeFormKeys().singleResult().getFormKey()).isEqualTo("embedded:app:forms/task-form.html");
    assertThat(task().getDueDate()).isNull();
    
  }

  @After
  public void calculateCoverageForAllTests() throws Exception {
    ProcessTestCoverage.calculate(rule.getProcessEngine());
  }  

}
