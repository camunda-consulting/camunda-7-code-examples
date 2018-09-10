package com.camunda.demo.underwriting.jsf;

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
import org.camunda.bpm.engine.history.HistoricCaseActivityInstance;
import org.camunda.bpm.engine.impl.cmmn.entity.runtime.CaseExecutionEntity;
import org.camunda.bpm.engine.impl.cmmn.execution.CaseExecutionState;
import org.camunda.bpm.engine.repository.CaseDefinition;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.model.cmmn.impl.CmmnModelConstants;

import com.camunda.demo.underwriting.Constants;
import com.camunda.demo.underwriting.domain.Application;

@Named
@SessionScoped
public class CaseController implements Serializable {

  private static final long serialVersionUID = 1L;

  private Application application;

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
    if (taskId != null) {
      selectTask(taskId);
      if (selectedTask!=null) {
        Task loadedTask = selectedTask;
        initByCaseInstanceId(selectedTask.getCaseInstanceId()); // does a reset!
        selectedTask = loadedTask;
       }
    } else if (caseInstanceId != null) {
      initByCaseInstanceId(caseInstanceId);
    }

  }

  private void reset() {
    selectedTask = null;
    caseInstance = null;
    caseDefinition = null;
    activityForbiddenText = null;
    taskFormVariables = new HashMap<String, Object>();
  }

  public void initByCaseInstanceId(final String caseInstanceId) {
    reset();
    if (engine.getCaseService().createCaseExecutionQuery().caseInstanceId(caseInstanceId).count()==0) {
      return;
    }
    application = (Application) engine.getCaseService().getVariable(caseInstanceId, Constants.VAR_NAME_APPLICATION);
    caseInstance = engine.getCaseService().createCaseInstanceQuery().caseInstanceId(caseInstanceId).singleResult();
    caseDefinition = engine.getRepositoryService().getCaseDefinition(caseInstance.getCaseDefinitionId());
    
    activeCaseExecutions = new ArrayList<CaseExecution>();
    enabledCaseExecutions = new ArrayList<CaseExecution>();
    
    loadCaseInstanceStatus();
  }

  private void loadCaseInstanceStatus() {
    List<CaseExecution> caseExecutions = engine.getCaseService().createCaseExecutionQuery().caseInstanceId(caseInstance.getId()).list();
    for (CaseExecution caseExecution : caseExecutions) {
      if (caseExecution.isActive()) {
        activeCaseExecutions.add(caseExecution);
      }
      else if (caseExecution.isEnabled()) {
        enabledCaseExecutions.add(caseExecution);
      }
    }
    
    historicActivityInstances = engine.getHistoryService().createHistoricCaseActivityInstanceQuery() //
          .caseInstanceId(caseInstance.getId()) //
          .completed() //
          .list();
  }
  
  public Object getTaskVariable(String name) {
    if (selectedTask!=null) {
      return engine.getTaskService().getVariable(selectedTask.getId(), "capableUnderwriters");
    }
    return null;
  }

  public String executeCaseActivityAndSelectTask(CaseExecution execution) {
    engine.getCaseService().manuallyStartCaseExecution(execution.getId());
    
    List<Task> tasks = engine.getTaskService().createTaskQuery().caseExecutionId(execution.getId()).list();
    if (tasks.size() > 0) {
      selectedTask = tasks.get(0);
    }
    
    // now enabled/active activities have changed
    refreshCaseInfo();
    
    // Heuristik: Den letzten Task zum aktuellen Case selektieren - eine
    // Aktivität könnte ja auch
    // ein Prozess sein der keine oder mehrere Aufgaben erzeugt!
//    final List<Task> tasks = engine.getTaskService().createTaskQuery().caseInstanceId(caseInstance.getId()).orderByTaskCreateTime().desc().list();
//    if (tasks.size() > 0) {
//      selectedTask = tasks.get(0);
////      claimSelectedAskForCurrentUser();
//    }
//    refreshCaseInfo();
    return "";
    //return getTaskFormLink(selectedTask.getId());
  }

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
      return getFormLink(task);
    }
    else return null;
  }
  
  public String getFormLink(Task task) {
    if (task.getCaseInstanceId()==null ){
      // no task from case
      return "/../camunda/app/tasklist/default/#/?task="+task.getId()+"&detailsTab=task-detail-form";
    }
    
    String formKey = task.getFormKey();
    if (formKey == null) {
      return null;
    }    
    return formKey.replaceAll("app:", "") + "?taskId=" + task.getId();    
  }

  public void refreshCaseInfo() {
    initByCaseInstanceId(caseInstance.getId());
  }

  public void deselectTask() {
    selectedTask = null;
  }

  public void selectTask(final String taskId) {
    selectedTask = engine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
    if(selectedTask==null) {
      return;
    }
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
    
    HashMap<String, Object> variables = saveVariables();
    
    // complete task (variables are ignored due to bug https://app.camunda.com/jira/browse/CAM-3261)
    // hence variables are saved beforehand (workaround)
    engine.getTaskService().complete(selectedTask.getId(), variables);

    // reset task form
    taskFormVariables = new HashMap<String, Object>();
    refreshCaseInfo();
    selectedTask = null;
    if (caseInstance==null) {
      return "case-instances.jsf";      
    } else {
      return "case-form.jsf?caseInstanceId=" + caseInstance.getId();
    }
  }

  private HashMap<String, Object> saveVariables() {
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
    // save changed to variables object bound to JSF UI
    variables.put(Constants.VAR_NAME_APPLICATION, application);
    
    engine.getCaseService().setVariables(selectedTask.getCaseExecutionId(), variables);
    return variables;
  }

  public void saveSelectedTask() {
    saveVariables();
//    engine.getCaseService().setVariable(selectedTask.getCaseExecutionId(), Constants.VAR_NAME_APPLICATION, application);
//    engine.getCaseService().setVariable(selectedTask.getCaseExecutionId(), Constants.VAR_NAME_EVALUATION_COMMENTS, application);
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
