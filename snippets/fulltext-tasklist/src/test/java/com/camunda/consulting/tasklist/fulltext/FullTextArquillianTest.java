package com.camunda.consulting.tasklist.fulltext;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.management.TablePage;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class FullTextArquillianTest {
  
  private static final String PROCESS_DEFINITION_KEY = "fulltext-tasklist";
  private static final String INCIDENT_PROCESS_DEFINITION = "incident-process";

  @Deployment
  public static WebArchive createDeployment() {
    // resolve given dependencies from Maven POM
    File[] libs = Maven.resolver()
      .offline(false)
      .loadPomFromFile("pom.xml")
      .importRuntimeAndTestDependencies().resolve().withTransitivity().asFile();

    return ShrinkWrap
            .create(WebArchive.class, "fulltext-tasklist.war")
            // add needed dependencies
            .addAsLibraries(libs)
            // prepare as process application archive for camunda BPM Platform
            .addAsResource("META-INF/processes.xml", "META-INF/processes.xml")
            // enable CDI
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            // boot JPA persistence unit
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "com.camunda.consulting.tasklist.fulltext") // not recursive to skip package 'nonarquillian'
            .addPackages(false, "com.camunda.consulting.tasklist.fulltext.entity")
            // add process definition
            .addAsResource("process.bpmn")
            .addAsResource("incidentProcess.bpmn")
            // add process image for visualizations
            .addAsResource("process.png")
            .addAsResource("incidentProcess.png")
            // now you can add additional stuff required for your test case
    ;
  }

  @Inject
  private ProcessEngine processEngine;

  /**
   * Tests that the process is executable and reaches its end.
   */
  @Test
  public void insertUserTask() throws Exception {
    cleanUpRunningProcessInstances();
    
    String businessKey = "" + new Random(System.currentTimeMillis()).nextInt(1000);
    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, businessKey);

    assertEquals(1, processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
    
    TablePage userTasks = processEngine.getManagementService().createTablePageQuery().tableName("USER_TASK").listPage(0, 100);
    assertTrue("No Usertasks in database", userTasks.getSize() > 0);
    List<Map<String, Object>> userTaskRows = userTasks.getRows();
    boolean businessKeyFound = false;
    for (Map<String, Object> userTaskRow : userTaskRows) {
      System.out.println("" + userTaskRow.get("PROC_INST_ID_"));
      if (userTaskRow.get("PROC_INST_ID_").equals(processInstance.getId())) {
        if (userTaskRow.get("BUSINESS_KEY_").equals(businessKey)) {
          businessKeyFound = true;
        }
      } 
    }
    assertTrue("Business Key not found", businessKeyFound);
  }

  @Test
  public void findUserTaskId() {
    cleanUpRunningProcessInstances();
    String businessKey = "" + new Random(System.currentTimeMillis()).nextInt(1000);
    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, businessKey);

    Task userTask1 = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    String businessUserTaskId = (String) processEngine
        .getTaskService()
        .getVariableLocal(userTask1.getId(), FulltextTaskHandler.BUSINESS_USER_TASK_ID);
    System.out.println("businessUserTaskId: " + businessUserTaskId);
    TablePage userTasks = processEngine.getManagementService().createTablePageQuery().tableName("USER_TASK").listPage(0, 100);
    List<Map<String, Object>> userTaskRows = userTasks.getRows();
    boolean businessUserTaskIdFound = false;
    for (Map<String, Object> userTaskRow : userTaskRows) {
      if (userTaskRow.get("ID_").equals(businessUserTaskId)) {
        businessUserTaskIdFound = true;
      }
    }
    assertTrue("user task for task not found", businessUserTaskIdFound);
  }
  
  @Test
  public void completeUserTask() {
    cleanUpRunningProcessInstances();
    
    String businessKey = "" + new Random(System.currentTimeMillis()).nextInt(1000);
    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, businessKey);
    
    TablePage userTasks = processEngine.getManagementService().createTablePageQuery().tableName("USER_TASK").listPage(0, 100);
    String taskId1 = null;
    List<Map<String, Object>> userTaskRows = userTasks.getRows();
    for (Map<String, Object> userTaskRow : userTaskRows) {
      if (userTaskRow.get("PROC_INST_ID_").equals(processInstance.getId())) {
        taskId1 = (String) userTaskRow.get("TASK_ID_");
        processEngine.getTaskService().complete(taskId1);
      }
    }
    
    Task userTask2 = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    assertEquals("User Task 2", userTask2.getName());
    
    userTasks = processEngine.getManagementService().createTablePageQuery().tableName("USER_TASK").listPage(0, 100);
    userTaskRows = userTasks.getRows();
    boolean userTask1Found = false;
    for (Map<String, Object> userTaskRow : userTaskRows) {
      if (userTaskRow.get("TASK_ID_").equals(taskId1)) {
        userTask1Found = true;
      }  
    }
    assertFalse("User task 1 after completion in user task table", userTask1Found);
  }
  
  @Test
  public void forceIncident() throws Exception {
    cleanUpRunningProcessInstances();
    ProcessInstance incidentProcessInstance = processEngine.getRuntimeService().startProcessInstanceByKey(INCIDENT_PROCESS_DEFINITION);
    Thread.sleep(1000);
    Incident incident = processEngine.getRuntimeService().createIncidentQuery().processInstanceId(incidentProcessInstance.getId()).singleResult();
    assertNotNull("No incident found", incident);
  }
  
  @Test
  public void startProcessWithIncident() throws Exception {
    cleanUpRunningProcessInstances();
    String businessKey = "" + new Random(System.currentTimeMillis()).nextInt(1000);
    Incident incident = startProcessWithIncident(businessKey);
    Job job = processEngine.getManagementService().createJobQuery().processInstanceId(incident.getProcessInstanceId()).singleResult();
    Map<String, Object> procVars = collectIncidentVariables(incident, job);

    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, businessKey, procVars);

    TablePage userTasks = processEngine.getManagementService().createTablePageQuery().tableName("USER_TASK").listPage(0, 100);
    assertTrue("No Usertasks in database", userTasks.getSize() > 0);
    List<Map<String, Object>> userTaskRows = userTasks.getRows();
    boolean incidentJobIdFound = false;
    for (Map<String, Object> userTaskRow : userTaskRows) {
      System.out.println("" + userTaskRow.get("PROC_INST_ID_"));
      if (userTaskRow.get("PROC_INST_ID_").equals(processInstance.getId())) {
        if (userTaskRow.get("INC_JOB_ID_").equals(job.getId())) {
          incidentJobIdFound = true;
        }
      } 
    }
    assertTrue("Incident Job Id not found", incidentJobIdFound);
  }

  @Test
  public void startProcessWithExceptionStacktrace() throws Exception {
    cleanUpRunningProcessInstances();
    String businessKey = "" + new Random(System.currentTimeMillis()).nextInt(1000);
    Incident incident = startProcessWithIncident(businessKey);
    Job job = processEngine.getManagementService().createJobQuery().processInstanceId(incident.getProcessInstanceId()).singleResult();
    Map<String, Object> procVars = collectIncidentVariables(incident, job);
    
    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, businessKey, procVars);

    TablePage userTasks = processEngine.getManagementService().createTablePageQuery().tableName("USER_TASK").listPage(0, 100);
    assertTrue("No Usertasks in database", userTasks.getSize() > 0);
    List<Map<String, Object>> userTaskRows = userTasks.getRows();
    boolean stackTraceFound = false;
    for (Map<String, Object> userTaskRow : userTaskRows) {
      System.out.println("" + userTaskRow.get("PROC_INST_ID_"));
      if (userTaskRow.get("PROC_INST_ID_").equals(processInstance.getId())) {
        System.out.println(userTaskRow.get("INC_EXCEPTION_"));
        if (userTaskRow.get("INC_EXCEPTION_") != null) {
          // Result is a map, ibatis doesn't bring more than 'clob10: SPACE(9427 /* table: 170 id: 710 */)'
          stackTraceFound = true;
        }
      } 
    }
    assertTrue("Stack trace not found", stackTraceFound);
  }
  
  private Incident startProcessWithIncident(String businessKey) throws Exception {
    ProcessInstance incidentProcessInstance = processEngine.getRuntimeService().startProcessInstanceByKey(INCIDENT_PROCESS_DEFINITION, businessKey);
    Thread.sleep(1000);
    return processEngine.getRuntimeService().createIncidentQuery().processInstanceId(incidentProcessInstance.getId()).singleResult();
  }

  private Map<String, Object> collectIncidentVariables(Incident incident, Job job) {
    Map<String, Object> procVars = new HashMap<String, Object>();
    procVars.put(FulltextTaskHandler.INCIDENT_PROCESS_DEFINITION_ID, incident.getProcessDefinitionId());
    procVars.put(FulltextTaskHandler.INCIDENT_ACTIVITY_ID, incident.getActivityId());
    procVars.put(FulltextTaskHandler.INCIDENT_JOB_ID, job.getId());
    procVars.put(FulltextTaskHandler.INCIDENT_MESSAGE, incident.getIncidentMessage());
    procVars.put(FulltextTaskHandler.INCIDENT_EXECUTION_ID, incident.getExecutionId());
    procVars.put(FulltextTaskHandler.INCIDENT_PROCESS_INSTANCE_ID, incident.getProcessInstanceId());
    return procVars;
  }

  /**
   * Helper to delete all running process instances, which might disturb our Arquillian Test case
   * Better run test cases in a clean environment, but this is pretty handy for demo purposes
   */
  private void cleanUpRunningProcessInstances() {
    List<ProcessInstance> runningInstances = processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).list();
    runningInstances.addAll(processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(INCIDENT_PROCESS_DEFINITION).list());
    for (ProcessInstance processInstance : runningInstances) {
      processEngine.getRuntimeService().deleteProcessInstance(processInstance.getId(), "deleted to have a clean environment for Arquillian");
    }
  }  
}
