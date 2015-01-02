package com.camunda.demo.webinar.cmmn.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.history.HistoricCaseActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState;
import org.camunda.bpm.engine.impl.persistence.deploy.DeploymentCache;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.cmmn.impl.CmmnModelConstants;

import com.camunda.demo.webinar.cmmn.domain.Application;
import com.camunda.demo.webinar.cmmn.domain.ApplicationDomainService;

@Named
@SessionScoped
public class CaseController implements Serializable {

  private static final long serialVersionUID = 1L;

  private Application application;

  @Inject
  private ApplicationDomainService service;

  @Inject
  private ProcessEngine engine;
  
  private CaseInstance caseInstance;

  private Task selectedTask;

  private CaseDefinition caseDefinition;

  @Inject
  private UserController currentUser;

  private Map<String, Object> taskFormVariables = new HashMap<String, Object>();

  private String activityForbiddenText;

  private List<CaseExecution> activeCaseExecutions;
  private List<CaseExecution> enabledCaseExecutions;

  private List<HistoricCaseActivityInstance> historicActivityInstances;

  public void initCaseByParameters() {
    // TODO: Make sure it is not executed every time we klick RELOAD!
    Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String taskId = requestParameterMap.get("taskId");
    String caseInstanceId = requestParameterMap.get("caseInstanceId");
    String activityId = requestParameterMap.get("activityId");

    if (activityId != null && caseInstanceId != null) {
      if (caseInstance != null && caseInstance.getId().equals(caseInstanceId) && selectedTask != null && selectedTask.getTaskDefinitionKey().equals(activityId)) {
        // already selected!
        return;
      }
      // this activity shall be started if possible
      initCaseInstanceAndStartActivityIfPossible(caseInstanceId, activityId);
    } else if (taskId != null) {
      selectTask(taskId);
      Task loadedTask = selectedTask;
      initByCaseId(selectedTask.getCaseInstanceId()); // does a reset!
      selectedTask = loadedTask;
    } else if (caseInstanceId != null) {
      initByCaseId(caseInstanceId);
    }

  }

  private void initCaseInstanceAndStartActivityIfPossible(String caseInstanceId, String activityId) {
//    selectedTask = null;
//    initByCaseId(caseInstanceId);
//    if (caseInstanceInfo.getAvailablePlanItemIds().contains(activityId)) {
//      executeCaseActivityAndSelectTask(activityId);
//    } else {
//      CaseActivity activity = null;
//      List<StageDefinition> stageDefinitions = caseDefinition.getStageDefinitions();
//      for (StageDefinition stageDefinition : stageDefinitions) {
//        Map<String, CaseActivity> activities = caseDefinition.getCaseActivityDefinitionMapForStage(stageDefinition.getId());
//        if (activities.containsKey(activityId)) {
//          activity = activities.get(activityId);
//        }
//      }
//      if (activity != null) {
//        activityForbiddenText = "Die Daten für Aktivität '" + activity.getName() + "' können im aktuellen Zustand leider nicht geändert werden!";
//      } else {
//        activityForbiddenText = "Die ausgewählten Daten können im aktuellen Zustand leider nicht geändert werden!";
//      }
//
//    }
  }

  private void reset() {
    selectedTask = null;
    caseInstance = null;
    caseDefinition = null;
    activityForbiddenText = null;
    taskFormVariables = new HashMap<String, Object>();
  }

  public void initByCaseId(final String caseId) {
    reset();
    application = service.findApplicationByCaseId(caseId);
    caseInstance = engine.getCaseService().createCaseInstanceQuery().caseInstanceId(caseId).singleResult();
    caseDefinition = engine.getRepositoryService().getCaseDefinition(caseInstance.getCaseDefinitionId());
    
    activeCaseExecutions = new ArrayList<CaseExecution>();
    enabledCaseExecutions = new ArrayList<CaseExecution>();
    
    List<CaseExecution> caseExecutions = engine.getCaseService().createCaseExecutionQuery().caseInstanceId(caseInstance.getId()).list();
    for (CaseExecution caseExecution : caseExecutions) {
      if (((CaseExecutionEntity)caseExecution).getCurrentState() == CaseExecutionState.ACTIVE) {
        activeCaseExecutions.add(caseExecution);
      }
      else if (((CaseExecutionEntity)caseExecution).getCurrentState() == CaseExecutionState.ENABLED) {
        enabledCaseExecutions.add(caseExecution);
      }
    }
    
    historicActivityInstances = engine.getHistoryService().createHistoricCaseActivityInstanceQuery() //
          .caseInstanceId(caseInstance.getId()) //
          .completed() //
          .list();

  }

//  public void executeCaseActivity(final String activityID) {
//    final HashMap<String, Object> variables = new HashMap<String, Object>();
//    variables.put("assign-group", null);
//    variables.put("assign-user", null);
//
//    // parameter for planning are passed via "normal" HTML form - no JSF
//    // binding applied
//    final Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//    // so read them manually here:
//    final String assignmentType = requestParameterMap.get("radio-" + activityID);
//    if ("group".equals(assignmentType)) {
//      final String groupName = requestParameterMap.get("group-" + activityID);
//      variables.put("assign-group", groupName);
//    } else if ("user".equals(assignmentType)) {
//      final String userName = requestParameterMap.get("user-" + activityID);
//      variables.put("assign-user", userName);
//    }
//
//    engine.getCaseService().executePlanItem(caseInstance.getId(), activityID, variables);
//    refreshCaseInfo();
//  }

//  public String executeCaseActivityAndSelectTask(final String activityID) {
//    engine.getCaseService().executePlanItem(caseInstance.getId(), activityID, null);
//
//    // Heuristik: Den letzten Task zum aktuellen Case selektieren - eine
//    // Aktivität könnte ja auch
//    // ein Prozess sein der keine oder mehrere Aufgaben erzeugt!
//    final List<Task> tasks = engine.getTaskService().createTaskQuery().caseInstanceId(caseInstance.getId()).orderByTaskCreateTime().desc().list();
//    if (tasks.size() > 0) {
//      selectedTask = tasks.get(0);
//      claimSelectedAskForCurrentUser();
//    }
//    refreshCaseInfo();
//    return getFormForTask(selectedTask.getId());
//  }

  public boolean isRunning(String activityId) {
//    for (Task task : caseInstanceInfo.getActiveTasks()) {
//      if (task.getTaskDefinitionKey().equals(activityId)) {
//        return true;
//      }
//    }
//    for (ProcessInstance pi : caseInstanceInfo.getActiveProcesses()) {
//      if (pi.getProcessDefinitionId().equals(activityId)) {
//        return true;
//      }
//    }
    return false;
  }

  public boolean isFinished(String activityId) {
//    for (HistoricTaskInstance task : caseInstanceInfo.getHistoricTasks()) {
//      if (task.getTaskDefinitionKey().equals(activityId)) {
//        return true;
//      }
//    }
//    for (HistoricProcessInstance pi : caseInstanceInfo.getHistoricProcesses()) {
//      if (pi.getProcessDefinitionId().equals(activityId)) {
//        return true;
//      }
//    }
    return false;
  }

  public String getTaskFormLink(CaseExecution caseExecution) {
    if (caseExecution.getActivityType()==CmmnModelConstants.CMMN_ELEMENT_HUMAN_TASK) {
      Task task = engine.getTaskService().createTaskQuery().caseExecutionId(caseExecution.getId()).initializeFormKeys().singleResult();
      String formKey = task.getFormKey();
      return formKey.replaceAll("app:", "") + "?taskId=" + task.getId();
    }
    else return null;
  }

  public void refreshCaseInfo() {
    initByCaseId(caseInstance.getId());
  }

  public void loadDefaultRating() {
    application.setRatingSchufa("B");
    application.setRatingCreditreform("A");
  }

  public void deselectTask() {
    selectedTask = null;
  }

  public void selectTask(final String taskId) {
    selectedTask = engine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
    claimSelectedTaskForCurrentUser();
  }

  private void claimSelectedTaskForCurrentUser() {
    // unclaim (tink about it - at the moment we need it as otherwise we
    // canno claim something already assigned to somebody else -> exception)
    engine.getTaskService().claim(selectedTask.getId(), null);
    // and claim for me
    engine.getTaskService().claim(selectedTask.getId(), currentUser.getUserId());
  }

  @Produces
  @Named("application")
  public Application getApplicationForSelectedCaseInstance() {
    return application;
  }

  public Map<String, Object> getTaskFormVariables() {
    return taskFormVariables;
  }

  public String completeSelectedTask() {
    service.update(application);
    
    HashMap<String, Object> variables = new HashMap<String, Object>();
    // workaround to use boolean variables
    for (Entry<String, Object> entry : taskFormVariables.entrySet()) {
      if (entry.getValue()!=null && ("true".equals(entry.getValue()) || "false".equals(entry.getValue()))) {
        variables.put(entry.getKey(), Boolean.valueOf((String)entry.getValue()));        
      }
      else {
        variables.put(entry.getKey(), entry.getValue());
      }
    }
    
    // Workaround for bug: https://app.camunda.com/jira/browse/CAM-3261
    engine.getCaseService().setVariables(selectedTask.getCaseExecutionId(), variables);
    // and complete task (variables are ignored due to bug mentioned above)
    engine.getTaskService().complete(selectedTask.getId(), variables);

    // reset task form
    taskFormVariables = new HashMap<String, Object>();
    refreshCaseInfo();
    selectedTask = null;

    return "case-form.jsf?caseInstanceId=" + caseInstance.getId();
  }

  public void saveSelectedTask() {
    service.update(application);
  }

  public String getProcessDefinitionName(final String processDefinitionId) {
    return engine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult().getName();
  }

  public CaseInstance getCaseInstance() {
    return caseInstance;
  }

  public Application getApplication() {
    return application;
  }

  public Task getSelectedTask() {
    return selectedTask;
  }

  public CaseDefinition getCaseDefinition() {
    return caseDefinition;
  }

  public String getActivityForbiddenText() {
    return activityForbiddenText;
  }

  ////////////////////////
  
  public List<String> getAccomplishedStages() {
    return new ArrayList<String>();
  }
  
  public String getCurrentStage() {
    return "STAGE";
  }
  
  public List<String> getFutureStages() {
    return new ArrayList<String>();
  }
  
  public List<CaseExecution> getActiveCaseExecutions() {
   return activeCaseExecutions; 
  }
  
  public List<CaseExecution> getEnabledCaseExecutions() {
    return enabledCaseExecutions;
  } 
  
  public List<HistoricCaseActivityInstance> getHistoricCaseActivityInstances() {
    return historicActivityInstances;
  } 
  
}
