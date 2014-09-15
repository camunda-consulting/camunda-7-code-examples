package com.camunda.consulting.tasklist.fulltext;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.context.Context;

import com.camunda.consulting.tasklist.fulltext.entity.UserTask;

/**
 * A TaskListener that put some values of a task into an extra database table
 * to allow fast or fulltext queries for these userTasks.
 *  
 * @author Ingo Richtsmeier
 *
 */
@Named
public class FulltextTaskHandler implements TaskListener, Serializable {
  
  public static final String BUSINESS_USER_TASK_ID = "businessUserTaskId";
  
  public static final String INCIDENT_ACTIVITY_ID = "incidentActivityId";
  public static final String INCIDENT_PROCESS_DEFINITION_ID = "incidentProcessDefinitionId";
  public static final String INCIDENT_JOB_ID = "incidentJobId";
  public static final String INCIDENT_EXECUTION_ID = "incidentExecutionId";
  public static final String INCIDENT_PROCESS_INSTANCE_ID = "incidentProcessInstanceId";
  public static final String INCIDENT_MESSAGE = "incidentMessage";


  private static final long serialVersionUID = 1L;

  private static final Logger log = Logger.getLogger(FulltextTaskHandler.class.getName());
  
  @PersistenceContext(unitName="fulltext-tasklist-PU")
  private EntityManager em;
  
  public FulltextTaskHandler() {
    log.info("constructed");
  }

  @Override
  public void notify(DelegateTask delegateTask) {
    log.info("handle User Task in table USER_TASK");
    if (EVENTNAME_CREATE.equals(delegateTask.getEventName())) {
      createUserTask(delegateTask);
    } else if (EVENTNAME_COMPLETE.equals(delegateTask.getEventName())) {
      deleteUserTask(delegateTask);
    }
  }

  private void createUserTask(DelegateTask userTask) {
    log.info("Create User Task " + userTask.getName());
    UserTask businessUserTask = new UserTask();
    String businessUserTaskId = Context.getProcessEngineConfiguration().getIdGenerator().getNextId();
    businessUserTask.setId(businessUserTaskId);
    businessUserTask.setName(userTask.getName());
    businessUserTask.setTaskId(userTask.getId());
    businessUserTask.setTaskDefinitionKey(userTask.getTaskDefinitionKey());
    businessUserTask.setProcessInstanceId(userTask.getProcessInstanceId());
    businessUserTask.setProcessDefinitionId(userTask.getProcessDefinitionId());
    businessUserTask.setAssignee(userTask.getAssignee());
    businessUserTask.setBusinessKey(userTask.getExecution().getProcessBusinessKey());
    
    Map<String, Object> variables = userTask.getVariables();
    String jobId = (String) variables.get(INCIDENT_JOB_ID);
    businessUserTask.setIncidentProcessDefinitionId((String) variables.get(INCIDENT_PROCESS_DEFINITION_ID));
    businessUserTask.setIncidentActivityId((String) variables.get(INCIDENT_ACTIVITY_ID));
    businessUserTask.setIncidentJobId(jobId);
    businessUserTask.setIncidentMessage((String) variables.get(INCIDENT_MESSAGE));
    businessUserTask.setIncidentExecutionId((String) variables.get(INCIDENT_EXECUTION_ID));
    businessUserTask.setIncidentProcessInstanceId((String) variables.get(INCIDENT_PROCESS_INSTANCE_ID));
    
    if (jobId != null) {
      String stacktrace = userTask.getExecution().getProcessEngineServices().getManagementService().getJobExceptionStacktrace(jobId);
      log.info(stacktrace.substring(0, 30));
      businessUserTask.setIncidentException(stacktrace);
    } else {
      log.info("jobId is null, no incidentException");
    }
    
    em.persist(businessUserTask);
    userTask.setVariableLocal(BUSINESS_USER_TASK_ID, businessUserTaskId);
  }

  private void deleteUserTask(DelegateTask userTask) {
    log.info("Delete User Task " + userTask.getName());
    UserTask businessUserTask = em.find(UserTask.class, userTask.getVariableLocal(BUSINESS_USER_TASK_ID));
    em.remove(businessUserTask);
  }
  
  public List<UserTask> findUserTasksWithExceptionLike(String exceptionSnippet) {
    TypedQuery<UserTask> query = em.createQuery(
        "select ut from UserTask ut where inc_exception_ like :exceptionSnippet", 
        UserTask.class);
    query.setParameter("exceptionSnippet", "%" + exceptionSnippet + "%");
    return query.getResultList();
  }

}
