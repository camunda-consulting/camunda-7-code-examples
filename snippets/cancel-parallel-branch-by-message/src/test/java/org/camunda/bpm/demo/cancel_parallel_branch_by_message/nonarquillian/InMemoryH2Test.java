package org.camunda.bpm.demo.cancel_parallel_branch_by_message.nonarquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  private static final String PROCESS_DEFINITION_KEY = "cancel-parallel-branch-by-message";

  public static RuntimeService runtimeService;
  
  

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
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
  public void testExecution() {
    runtimeService = processEngineRule.getRuntimeService();
    String pid = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY).getId();
    
    // execute failing job until it becomes an incident
    executeAvailableJobsRecursive();
    assertEquals(1, runtimeService.createIncidentQuery().count());
    
    // execute second branch
    runtimeService.correlateMessage("continue");
    
    ManagementService managementService = processEngineRule.getManagementService();
    Job job = managementService.createJobQuery().withRetriesLeft().singleResult();
    managementService.executeJob(job.getId());
    
//    runtimeService.correlateMessage("cancel");

    // assert that parallel branch has been canceled by the message
    HistoricActivityInstance historicActivityInstance = processEngineRule.getHistoryService().createHistoricActivityInstanceQuery().activityId("canceled").singleResult();
    assertNotNull(historicActivityInstance);
    
    List<Execution> list = runtimeService.createExecutionQuery().list();
    for (Execution execution : list) {
		System.out.println(execution);
	}
    
    job = managementService.createJobQuery().withRetriesLeft().singleResult();
    assertNotNull(job);
    managementService.executeJob(job.getId());
  }

  public void executeAvailableJobsRecursive() {
	  ManagementService managementService = processEngineRule.getManagementService();
	  List<Job> jobs = managementService.createJobQuery().withRetriesLeft().list();
	  
	  if (jobs.isEmpty()) {
		  return;
	  }
	  
	  for (Job job : jobs) {
		  try {
			  managementService.executeJob(job.getId());
		  } catch (Exception e) {};
	  }
	  
	  executeAvailableJobsRecursive();
  }
}
