package org.camunda.bpm.example.acm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.acm.CaseDefinition;
import org.camunda.bpm.engine.acm.CaseInstance;
import org.camunda.bpm.engine.acm.CaseInstanceInfo;
import org.camunda.bpm.engine.acm.StageDefinition;
import org.camunda.bpm.engine.acm.activity.CaseActivity;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.persistence.deploy.DeploymentCache;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.example.acm.cmis.CmisClient;
import org.camunda.bpm.example.acm.cmis.DocumentDto;
import org.camunda.bpm.example.acm.domain.CreditApplication;
import org.camunda.bpm.example.acm.domain.CreditApplicationService;


@Named
@SessionScoped
public class CaseController implements Serializable {

    private static final long serialVersionUID = 1L;

    private CreditApplication creditApplication;

    @Inject
    private CreditApplicationService service;

    @Inject
    private ProcessEngine engine;

    private CaseInstanceInfo caseInstanceInfo;

    private CaseInstance caseInstance;

    private Task selectedTask;

    private CaseDefinition caseDefinition;

    private List<DocumentDto> documents;
    
    @Inject
    private UserController currentUser;
    
    private Map<String, Object> taskFormVariables = new HashMap<String, Object>();
    
    private String activityForbiddenText;

    public void initCaseByParameters() {
      // TODO: Make sure it is not executed every time we klick RELOAD!
        final Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        final String taskId = requestParameterMap.get("taskId");
        final String caseInstanceId = requestParameterMap.get("caseInstanceId");
        final String activityId = requestParameterMap.get("activityId");
        
        if (activityId != null && caseInstanceId!=null) {
          if (caseInstance!=null && caseInstance.getId().equals(caseInstanceId) && selectedTask!=null && selectedTask.getTaskDefinitionKey().equals(activityId)) {
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

    private void initCaseInstanceAndStartActivityIfPossible(final String caseInstanceId, final String activityId) {
      selectedTask = null;
      initByCaseId(caseInstanceId);
      if (caseInstanceInfo.getAvailablePlanItemIds().contains(activityId)) {
        executeCaseActivityAndSelectTask(activityId);            
      } else {
        CaseActivity activity = null;
        List<StageDefinition> stageDefinitions = caseDefinition.getStageDefinitions();
        for (StageDefinition stageDefinition : stageDefinitions) {
          Map<String, CaseActivity> activities = caseDefinition.getCaseActivityDefinitionMapForStage(stageDefinition.getId());
          if (activities.containsKey(activityId)) {
            activity = activities.get(activityId);
          }
        }
        if (activity!=null) {
          activityForbiddenText = "Die Daten für Aktivität '" + activity.getName() + "' können im aktuellen Zustand leider nicht geändert werden!";
        }
        else {
          activityForbiddenText = "Die ausgewählten Daten können im aktuellen Zustand leider nicht geändert werden!";
        }
        
      }
    }

    private void reset() {
      selectedTask = null;
      caseInstance = null;
      caseInstanceInfo = null;
      caseDefinition = null;
      activityForbiddenText = null;
      documents = new ArrayList<DocumentDto>();
    }

    public void initByCaseId(final String caseId) {
      reset();
      creditApplication = service.findCreditApplicationByCaseId(caseId);
        caseInstanceInfo = engine.getCaseService().getCaseInstanceInfo(caseId);
        caseInstance = engine.getCaseService().createCaseInstanceQuery().caseInstanceId(caseId).singleResult();
        caseDefinition = DeploymentCache.findCaseDefinitionById(caseInstance.getCaseDefinitionId());
    }

    public void executeCaseActivity(final String activityID) {
        final HashMap<String, Object> variables = new HashMap<String, Object>();
        variables.put("assign-group", null);
        variables.put("assign-user", null);

        // parameter for planning are passed via "normal" HTML form - no JSF
        // binding applied
        final Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        // so read them manually here:
        final String assignmentType = requestParameterMap.get("radio-" + activityID);
        if ("group".equals(assignmentType)) {
            final String groupName = requestParameterMap.get("group-" + activityID);
            variables.put("assign-group", groupName);
        } else if ("user".equals(assignmentType)) {
            final String userName = requestParameterMap.get("user-" + activityID);
            variables.put("assign-user", userName);
        }

        engine.getCaseService().executePlanItem(caseInstance.getId(), activityID, variables);
        refreshCaseInfo();
    }

    public String executeCaseActivityAndSelectTask(final String activityID) {
        engine.getCaseService().executePlanItem(caseInstance.getId(), activityID, null);

        // Heuristik: Den letzten Task zum aktuellen Case selektieren - eine
        // Aktivität könnte ja auch
        // ein Prozess sein der keine oder mehrere Aufgaben erzeugt!
        final List<Task> tasks = engine.getTaskService().createTaskQuery().caseInstanceId(caseInstance.getId()).orderByTaskCreateTime().desc().list();
        if (tasks.size() > 0) {
            selectedTask = tasks.get(0);
            claimSelectedAskForCurrentUser();
        }
        refreshCaseInfo();
        return getFormForTask(selectedTask.getId());
    }
    
    public boolean isRunning(String activityId) {      
      for (Task task : caseInstanceInfo.getActiveTasks()) {
        if (task.getTaskDefinitionKey().equals(activityId)) {
          return true;
        }
      }
      for (ProcessInstance pi : caseInstanceInfo.getActiveProcesses()) {
        if (pi.getProcessDefinitionId().equals(activityId)) {
          return true;
        }
      }
      return false;
    }
    
    public boolean isFinished(String activityId) {
      for (HistoricTaskInstance task : caseInstanceInfo.getHistoricTasks()) {
        if (task.getTaskDefinitionKey().equals(activityId)) {
          return true;
        }
      }
      for (HistoricProcessInstance pi : caseInstanceInfo.getHistoricProcesses()) {
        if (pi.getProcessDefinitionId().equals(activityId)) {
          return true;
        }
      }
      return false;      
    }

    public String getFormForTask(final String taskId) {
        final String formKey = engine.getFormService().getTaskFormData(taskId).getFormKey();
        return formKey.replaceAll("app:", "");
    }

    public void refreshCaseInfo() {
        caseInstanceInfo = engine.getCaseService().getCaseInstanceInfo(caseInstance.getId());
    }
    
    public void loadDefaultRating() {
      creditApplication.setRatingSchufa("B");
      creditApplication.setRatingCreditreform("A");
    }

    public void deselectTask() {
      selectedTask = null;
    }
    
    public void selectTask(final String taskId) {
        selectedTask = engine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
        claimSelectedAskForCurrentUser();
    }

    private void claimSelectedAskForCurrentUser() {
        // unclaim (tink about it - at the moment we need it as otherwise we
        // canno claim something already assigned to somebody else -> exception)
        engine.getTaskService().claim(selectedTask.getId(), null);
        // and claim for me
        engine.getTaskService().claim(selectedTask.getId(), currentUser.getUserId());
    }
    
    @Produces
    @Named("creditApplication")
    public CreditApplication getCreditApplicationForSelectedCaseInstance() {
      return creditApplication;
    }
    
//    @Produces
//    @Named("taskVariables")
    public Map<String, Object> getTaskFormVariables() {
      return taskFormVariables;
    }   

    public String completeSelectedTask() {
        service.update(creditApplication);

        engine.getTaskService().complete(selectedTask.getId(), taskFormVariables);
        taskFormVariables = new HashMap<String, Object>();
        refreshCaseInfo();
        selectedTask = null;

        return "case-form.jsf?caseInstanceId=" + caseInstance.getId();
    }

    public void saveSelectedTask() {
        service.update(creditApplication);
    }
    
//    public void deleteSelectedTask() {
//      engine.getTaskService().deleteTask(selectedTask.getId(), "Wunsch von " + currentUser.getUserId());
//    }

    public String getProcessDefinitionName(final String processDefinitionId) {
        return engine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult().getName();
    }

    public List<DocumentDto> getDocuments() {
        if (caseInstance == null) {
            return new ArrayList<DocumentDto>();
        } else if (documents == null) {
            documents = CmisClient.getDocumentsForCase(caseInstance.getId());
        }
        return documents;
    }

    public void resetDocuments() {
        documents = null;
    }

    /**
     * @return the caseInstanceInfo
     */
    public CaseInstanceInfo getCaseInstanceInfo() {
        return caseInstanceInfo;
    }

    /**
     * @return the caseInstance
     */
    public CaseInstance getCaseInstance() {
        return caseInstance;
    }

    public CreditApplication getCreditApplication() {
        return creditApplication;
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

}
