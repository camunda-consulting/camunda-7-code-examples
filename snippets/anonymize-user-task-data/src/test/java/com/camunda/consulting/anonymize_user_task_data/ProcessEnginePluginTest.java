package com.camunda.consulting.anonymize_user_task_data;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.UserOperationLogEntry;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class ProcessEnginePluginTest {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "UsertaskdataexamplesProcess";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @After
  public void cleanUserOperationLog() {
    List<UserOperationLogEntry> userOperationLogEntries = historyService().createUserOperationLogQuery().list();
    for(UserOperationLogEntry userOperationLogEntry : userOperationLogEntries) {
      historyService().deleteUserOperationLogEntry(userOperationLogEntry.getId());
    }
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
  public void testHappyPath() {
	  ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
	  
	  // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
	  assertThat(processInstance).isActive().task().hasCandidateGroup("management");
	  identityService().setAuthenticatedUserId("peter");
	  claim(task(), "peter");
	  assertThat(taskQuery().taskAssignee("peter").list()).hasSize(1);
	  assertThat(taskQuery().taskAssignee("peter").singleResult()).isEqualTo(task());
	  assertThat(historyService().createHistoricTaskInstanceQuery().taskAssignee("peter").list()).isEmpty();
	  complete(task());

	  // History without user id?
	  HistoricTaskInstance historicTaskInstance = historyService()
	      .createHistoricTaskInstanceQuery()
	      .taskDefinitionKey("DoSomethingWithoutListenerTask")
	      .singleResult();
	  assertThat(historicTaskInstance.getAssignee()).isNull();

	  // User operations log without user id?
	  List<UserOperationLogEntry> userOperationsList = historyService().createUserOperationLogQuery().list();
	  assertThat(userOperationsList).hasSize(2);
	  for (UserOperationLogEntry userOperationLogEntry : userOperationsList) {
      assertThat(userOperationLogEntry.getOrgValue()).isNotEqualTo("peter");
      assertThat(userOperationLogEntry.getNewValue()).isNotEqualTo("peter");
    }

	  // Try the second task, used to test listener (unsuccessfully)
	  assertThat(processInstance).isWaitingAt("DoSomethingWithCleanupListenerTask").task().hasCandidateGroup("management");
	  Task taskWithCleanupListener = task();
    claim(taskWithCleanupListener, "peter");
	  complete(taskWithCleanupListener);
    historicTaskInstance = historyService()
        .createHistoricTaskInstanceQuery()
        .taskDefinitionKey("DoSomethingWithCleanupListenerTask")
        .singleResult();
    assertThat(historicTaskInstance.getAssignee()).isNull();
	  
    List<UserOperationLogEntry> userOperationsListWithCleanup = historyService()
        .createUserOperationLogQuery()
        .taskId(taskWithCleanupListener.getId())
        .list();
    assertThat(userOperationsListWithCleanup).hasSize(2);
    
    for (UserOperationLogEntry userOperationLogEntry : userOperationsListWithCleanup) {
      assertThat(userOperationLogEntry.getUserId()).isNull();
      assertThat(userOperationLogEntry.getOrgValue()).isNotEqualTo("peter");
      assertThat(userOperationLogEntry.getNewValue()).isNotEqualTo("peter");
    }
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testClaimAndUnclaim() {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertThat(processInstance).isActive();
    Task currentTask = task();
    assertThat(currentTask).hasCandidateGroup("management");
    identityService().setAuthenticatedUserId("peter");
    claim(currentTask, "peter");
    unclaim(currentTask);

    List<UserOperationLogEntry> userOperationLogEntries = historyService()
        .createUserOperationLogQuery()
        .taskId(currentTask.getId())
        .list();

    assertThat(userOperationLogEntries).hasSize(2);
    for (UserOperationLogEntry userOperationLogEntry: userOperationLogEntries) {
      assertThat(userOperationLogEntry.getUserId()).isNull();
      assertThat(userOperationLogEntry.getOrgValue()).isNotEqualTo("peter");
      assertThat(userOperationLogEntry.getNewValue()).isNotEqualTo("peter");
    }
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testAssignTask() {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertThat(processInstance).isActive();
    Task currentTask = task();
    assertThat(currentTask).hasCandidateGroup("management");
    identityService().setAuthenticatedUserId("peter");
    taskService().setAssignee(currentTask.getId(), "john");

    List<UserOperationLogEntry> userOperationLogEntries = historyService()
        .createUserOperationLogQuery()
        .taskId(currentTask.getId())
        .list();

    assertThat(userOperationLogEntries).hasSize(1);
    assertThat(userOperationLogEntries.get(0).getUserId()).isNull();
    assertThat(userOperationLogEntries.get(0).getNewValue()).isNotEqualTo("john");
  }
}
