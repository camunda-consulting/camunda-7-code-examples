package com.camunda.consulting.email_incident_handler_plugin;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.history.HistoricIncident;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.util.ClockUtil;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import com.camunda.consulting.email_incident_handler_plugin.delegates.ResolveableTaskDelegate;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "email-incident-handler-plugin";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
    Mocks.register("resolveableTask", new ResolveableTaskDelegate());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {
	  ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

	  assertThat(processInstance).isWaitingAt("Task_DoSomething");
	  
	  complete(task(), withVariables("shouldFail", true));
	  
	  assertThat(processInstance).isWaitingAt("fiveMinutesTimer");
	  execute(job());
	  
	  Job asyncJob = job();
	  managementService().setJobRetries(asyncJob.getId(), 1);
	  
	  try {
      execute(asyncJob);
    } catch (Exception e) {  }
	  
	  List<Incident> incidentList = runtimeService().createIncidentQuery().list();
	  assertThat(incidentList).hasSize(1);
	  Incident incident = incidentList.get(0);
	  
	  assertThat(incident.getIncidentMessage()).isEqualTo("my custom error");
	  
	  List<Job> jobList = jobQuery().list();
	  assertThat(jobList).hasSize(2);
	  
	  Job reminderJob = jobQuery().timers().singleResult();
	  assertThat(((JobEntity) reminderJob).getJobHandlerConfigurationRaw()).isEqualTo(incident.getId());
	  assertThat(reminderJob.getProcessDefinitionKey()).isEqualTo(PROCESS_DEFINITION_KEY);
	  
	  execute(reminderJob);
	  
	  runtimeService().setVariable(processInstance.getId(), "shouldFail", false);
	  asyncJob = jobQuery().messages().singleResult();
	  managementService().setJobRetries(asyncJob.getId(), 1);
	  execute(asyncJob);
	  
	  assertThat(processInstance).isEnded();
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testSolveIncidentInTimeAndReminderJobDeleted() {
    ProcessInstance processInstance = runtimeService()
        .createProcessInstanceByKey(PROCESS_DEFINITION_KEY)
        .startAfterActivity("fiveMinutesTimer")
        .setVariables(withVariables("shouldFail", true))
        .execute();
    
    assertThat(processInstance).isWaitingAt("raiseIncidentTask");
    Job asyncJob = job();
    managementService().setJobRetries(asyncJob.getId(), 1);
    try {
      execute(asyncJob);
    } catch (Exception e) {    }
    Incident incident = runtimeService().createIncidentQuery().singleResult();
    assertThat(incident.getIncidentMessage()).isEqualTo("my custom error");
    
    Job reminderJob = jobQuery().timers().singleResult();
    assertThat(((JobEntity) reminderJob).getJobHandlerConfigurationRaw()).isEqualTo(incident.getId());
    
    runtimeService().setVariable(processInstance.getId(), "shouldFail", false);
    asyncJob = jobQuery().messages().singleResult();
    managementService().setJobRetries(asyncJob.getId(), 1);
    execute(asyncJob);
    
    HistoricIncident historicIncident = historyService().createHistoricIncidentQuery().singleResult();
    assertThat(historicIncident.isResolved()).isTrue();
    
    assertThat(jobQuery().timers().list()).hasSize(0);
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testRepeatReminderJobThreeTimes() {
    LocalDateTime startTime = LocalDateTime.now();
    ProcessInstance processInstance = runtimeService()
        .createProcessInstanceByKey(PROCESS_DEFINITION_KEY)
        .startAfterActivity("fiveMinutesTimer")
        .setVariables(withVariables("shouldFail", true))
        .execute();
    
    assertThat(processInstance).isWaitingAt("raiseIncidentTask");
    Job actualJob = job();
    managementService().setJobRetries(actualJob.getId(), 1);
    try {
      execute(actualJob);
    } catch (Exception e) {    }
    Incident incident = runtimeService().createIncidentQuery().singleResult();
    assertThat(incident.getIncidentMessage()).isEqualTo("my custom error");
    
    Job reminderJob = jobQuery().timers().singleResult();
    assertThat(((JobEntity) reminderJob).getJobHandlerConfigurationRaw()).isEqualTo(incident.getId());
    
    // move the time 20 min forward
    ClockUtil.setCurrentTime(Date.from(startTime.plusMinutes(EmailIncidentHandler.REMINDER_MINUTES).atZone(ZoneId.systemDefault()).toInstant()));
    execute(reminderJob);
    
    reminderJob = jobQuery().timers().singleResult();
    assertThat(reminderJob).isNotNull();
    
    // move the time 20 min forward
    ClockUtil.setCurrentTime(Date.from(startTime.plusMinutes(2 * EmailIncidentHandler.REMINDER_MINUTES).atZone(ZoneId.systemDefault()).toInstant()));
    execute(reminderJob); // 1. repeat
    
    reminderJob = jobQuery().timers().singleResult();
    assertThat(reminderJob).isNotNull();
    
    // move the time 20 min forward
    ClockUtil.setCurrentTime(Date.from(startTime.plusMinutes(3 * EmailIncidentHandler.REMINDER_MINUTES).atZone(ZoneId.systemDefault()).toInstant()));
    execute(reminderJob); // 2. repeat
    
    reminderJob = jobQuery().timers().singleResult();
    assertThat(reminderJob).isNotNull();

    // move the time 20 min forward
    ClockUtil.setCurrentTime(Date.from(startTime.plusMinutes(4 * EmailIncidentHandler.REMINDER_MINUTES).atZone(ZoneId.systemDefault()).toInstant()));
    execute(reminderJob); // 3. repeat
    
    reminderJob = jobQuery().timers().singleResult();
    assertThat(reminderJob).isNull();
  }

  /**
   * Check the Job behavior in the logs with a non-interrupting attached boundary timer event.
   * 
   * set logLevel 'org.camunda.bpm.engine.impl.persistence.entity' to debug.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testRepeatableTimer() {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    
    assertThat(processInstance).isWaitingAt("Task_DoSomething");
    Job nonInterrruptingRepeatableTimer = jobQuery().timers().singleResult();
    execute(nonInterrruptingRepeatableTimer);
    
    nonInterrruptingRepeatableTimer = jobQuery().timers().singleResult();
    assertThat(nonInterrruptingRepeatableTimer).isNotNull();
  }
  
}
