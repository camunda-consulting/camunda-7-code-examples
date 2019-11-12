package com.camunda.bpm.example.external_task_worker_process;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.camunda.bpm.engine.impl.externaltask.LockedExternalTaskImpl;
import org.camunda.bpm.engine.impl.persistence.entity.ExternalTaskEntity;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.util.List;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

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
  public void testHappyPath() {
    String businessKey = "23";
    ProcessInstance parentProcessInstance = processEngine().getRuntimeService()
        .startProcessInstanceByKey(ProcessConstants.PARENT_PROCESS, businessKey);
    
    assertThat(parentProcessInstance).isWaitingAt("ExternalServiceTask");
    
    String workerId = "child-process";
    List<LockedExternalTask> lockedExternalTasks = processEngine().getExternalTaskService()
      .fetchAndLock(1, workerId)
      .topic("parentTaskTopic", 5000L)
      .execute();
    ProcessInstance childProcessInstance = null;
    for (LockedExternalTask lockedExternalTask : lockedExternalTasks) {
      childProcessInstance = handleExternalTask(workerId, lockedExternalTask);
    }
    
    assertThat(childProcessInstance).isWaitingAt("Task_in_child_process");
    complete(task());
	  
    assertThat(parentProcessInstance).isEnded();

    assertEquals(ProcessConstants.CHILD_PROCESS,
        historyService().createHistoricProcessInstanceQuery()
        .processDefinitionKey(ProcessConstants.CHILD_PROCESS)
        .variableValueEquals(ProcessConstants.VAR_PARENT_PROCESS_INSTANCE_ID, parentProcessInstance.getId())
        .singleResult()
        .getProcessDefinitionKey());
  }

  private ProcessInstance handleExternalTask(String workerId, LockedExternalTask lockedExternalTask) {
    ProcessInstance childProcessInstance;
    childProcessInstance = processEngine().getRuntimeService()
      .createProcessInstanceByKey(ProcessConstants.CHILD_PROCESS)
      .businessKey(lockedExternalTask.getBusinessKey())
      .setVariable(ProcessConstants.VAR_PARENT_PROCESS_INSTANCE_ID, lockedExternalTask.getProcessInstanceId())
      .setVariable(ProcessConstants.VAR_EXTERNAL_TASK_ID, lockedExternalTask.getId())
      .setVariable(ProcessConstants.VAR_WORKER_ID, workerId)
      .execute();
    return childProcessInstance;
  }

}
