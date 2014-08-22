package com.camunda.consulting.job_retry_config.nonarquillian;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*; 
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.OptimisticLockingException;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.cmd.SetTaskVariablesCmd;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.impl.util.LogUtil.ThreadLogMode;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Ingo Richtsmeier
 *
 */
public class NoRetryConfigTest {
  
  private static final Logger log = Logger.getLogger(NoRetryConfigTest.class.getName());
  
  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();
  	
  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath();
    LogUtil.setThreadLogMode(ThreadLogMode.PRINT_ID);
    LogFactory.useJdkLogging();
  }

	static ControllableThread activeThread;

  @Test
	@Deployment(resources = "process.bpmn")
	public void runProcessWithoutRetry() {
		
		Map<String, Object> variables = withVariables("fail", "true");		
		ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByKey("job-retry-config", variables);
		
		// continue the async execution manually
		Job job = rule.getManagementService().createJobQuery().executionId(pi.getProcessInstanceId()).singleResult();
		try {
      rule.getManagementService().executeJob(job.getId());
    } catch (Exception e) {
      assertEquals("Wrong error massage", "Repairable Service Task should fail", e.getMessage());
    }
		
		// check for the incident after the first fail
		Incident incident = rule.getRuntimeService().createIncidentQuery().executionId(pi.getProcessInstanceId()).singleResult();
		assertNotNull("No incident found", incident);
		assertThat(incident.getIncidentMessage()).isEqualTo("Repairable Service Task should fail");
	}

  @Test
  @Deployment(resources = "process.bpmn")
  public void runServiceThrowingOptimisticLocking() {
    Map<String, Object> variables = withVariables("fail", "optimisticLockingException");    
    ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByKey("job-retry-config", variables);
    
    // continue the async execution manually
    Job job = rule.getManagementService().createJobQuery().executionId(pi.getProcessInstanceId()).singleResult();
    try {
      rule.getManagementService().executeJob(job.getId());
    } catch (Exception e) {
      assertEquals("Wrong error massage", "Service Task throws OptimisticLockingException", e.getMessage());
    }
    
    List<Job> jobs = rule.getManagementService().createJobQuery().executionId(pi.getProcessInstanceId()).list();
    assertTrue("not one job", jobs.size() == 1);
    assertTrue("not three retries", jobs.get(0).getRetries() == (3-1)); // Still decremented by 1
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void forceOptimiticLockingfrom2Threads() {
    Map<String, Object> variables = withVariables("fail", "false");
    ProcessInstance pi = rule.getRuntimeService().startProcessInstanceByKey("job-retry-config", variables);

    // continue the async execution manually
    Job job = rule.getManagementService().createJobQuery().executionId(pi.getProcessInstanceId()).singleResult();
    rule.getManagementService().executeJob(job.getId());

    String taskId = rule.getTaskService().createTaskQuery().singleResult().getId();
    String variableName = "varName";

    rule.getTaskService().setVariable(taskId, variableName, "someValue");

    SetTaskVariablesThread thread1 = new SetTaskVariablesThread(taskId, variableName, "someString");
    thread1.startAndWaitUntilControlIsReturned();

    // this fails with an optimistic locking exception
    SetTaskVariablesThread thread2 = new SetTaskVariablesThread(taskId, variableName, "someOtherString");
    thread2.startAndWaitUntilControlIsReturned();

    thread1.proceedAndWaitTillDone();
    thread2.proceedAndWaitTillDone();

    assertNull(thread1.optimisticLockingException);
    assertNotNull(thread2.optimisticLockingException);
        
    List<Job> jobs = rule.getManagementService().createJobQuery().executionId(pi.getProcessInstanceId()).list();
    assertTrue("No jobs found", jobs.size() > 0);
  }

  class SetTaskVariablesThread extends ControllableThread {
  
    OptimisticLockingException optimisticLockingException;
    Exception exception;
  
    protected Object variableValue;
    protected String taskId;
    protected String variableName;
  
    public SetTaskVariablesThread(String taskId, String variableName, Object variableValue) {
      this.taskId = taskId;
      this.variableName = variableName;
      this.variableValue = variableValue;
    }
  
    public synchronized void startAndWaitUntilControlIsReturned() {
      activeThread = this;
      super.startAndWaitUntilControlIsReturned();
    }
  
    public void run() {
      try {
        ProcessEngineImpl processEngine = (ProcessEngineImpl) rule.getProcessEngine();
        processEngine
        .getProcessEngineConfiguration()
        .getCommandExecutorTxRequired()
        .execute(new ControlledCommand(activeThread, new SetTaskVariablesCmd(taskId, Collections.singletonMap(variableName, variableValue), false)));
  
      } catch (OptimisticLockingException e) {
        this.optimisticLockingException = e;
      } catch (Exception e) {
        this.exception = e;
      }
      log.fine(getName()+" ends");
    }
  }
}
