package org.camunda.bpm.engine.cdi.impl.context;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.portlet.PortletSession;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.cdi.ProcessEngineCdiException;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;

/**
 * {@link ContextAssociationManager} which stores the association in the {@link PortletSession} as workaround
 * to problems with CDI {@link Conversation}s (http://www.liferay.com/community/forums/-/message_boards/message/27323345).
 * 
 * I experimented to use {@link SessionScoped} in order to store the association - but this causes class loading problems
 * on hot redeployment if objects are still in the session with old classes. 
 * 
 * It can be influenced by an attribute in the {@link PortletSession} set by another portlet as workaround
 * to not use IPC because of problems (http://www.liferay.com/community/forums/-/message_boards/message/27267640).  
 * 
 * @author ruecker
 */
@Specializes
public class PortalContextAssociationManager extends DefaultContextAssociationManager {

  private static final long serialVersionUID = 1L;

  public static final String BRIDGE_TASK_ID = "camunda.bridge.selected.task.id";
  public static final String BRIDGE_PROCESS_DEFINITION_ID = "camunda.bridge.selected.processdefinition.id";

  // public static final String ASSOCIATED_TASK_ID =
  // "camunda.business.process.associated.task.id";
  public static final String ASSOCIATED_TASK = "camunda.business.process.associated.task";
  // public static final String ASSOCIATED_EXECUTION_ID =
  // "camunda.business.process.associated.execution.id";
  public static final String ASSOCIATED_EXECUTION = "camunda.business.process.associated.execution";

  public static final String VARIABLE_CACHE = "camunda.business.process.variable.cache";

  @Inject
  private transient ProcessEngine processEngine;

  public static Object getSharedSessionAttribute(String key) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    PortletSession portletSession = (PortletSession) externalContext.getSession(false);
    return portletSession.getAttribute(key, PortletSession.APPLICATION_SCOPE);
  }

  public static void setSharedSessionAttribute(String key, Object value) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    PortletSession portletSession = (PortletSession) externalContext.getSession(false);
    portletSession.setAttribute(key, value, PortletSession.APPLICATION_SCOPE);
  }

  @Override
  public void setExecution(Execution execution) {
    // setSharedSessionAttribute(ASSOCIATED_EXECUTION_ID, execution.getId());
    setSharedSessionAttribute(ASSOCIATED_EXECUTION, execution);
  }

  @Override
  public void disAssociate() {
    setSharedSessionAttribute(ASSOCIATED_TASK, null);
    setSharedSessionAttribute(ASSOCIATED_EXECUTION, null);
    setSharedSessionAttribute(VARIABLE_CACHE, null);

    // TODO: really do this?
    setSharedSessionAttribute(BRIDGE_TASK_ID, null);
  }

  @Override
  public Execution getExecution() {
    ExecutionEntity execution = getExecutionFromContext();
    if (execution != null) {
      return execution;
    } else {
      checkTaskSelectedViaBridge();
      return (Execution) getSharedSessionAttribute(ASSOCIATED_EXECUTION);
    }
  }

  @Override
  public Object getVariable(String variableName) {
    ExecutionEntity execution = getExecutionFromContext();
    if (execution != null) {
      return execution.getVariable(variableName);
    } else {
      // load from execution and store it in cache (if not yet there)
      Object value = getCachedVariables().get(variableName);
      if (value == null) {
        Execution e = getExecution();
        if (e != null) {
          value = processEngine.getRuntimeService().getVariable(e.getId(), variableName);
          getCachedVariables().put(variableName, value);
        }
      }
      return value;
    }
  }

  @Override
  public void setVariable(String variableName, Object value) {
    ExecutionEntity execution = getExecutionFromContext();
    if(execution != null) {
      execution.setVariable(variableName, value);
      execution.getVariable(variableName);
    } else {
      getCachedVariables().put(variableName, value);
    }
  }

  @Override
  public Task getTask() {
    checkTaskSelectedViaBridge();
    return (Task) getSharedSessionAttribute(ASSOCIATED_TASK);
  }

  @Override
  public void setTask(Task task) {
    // setSharedSessionAttribute(ASSOCIATED_TASK_ID, task.getId());
    setSharedSessionAttribute(ASSOCIATED_TASK, task);
  }

  /**
   * "lazy" load task if bridge state has changed in the meantime
   */
  private void checkTaskSelectedViaBridge() {
    String bridgeTaskId = (String) getSharedSessionAttribute(BRIDGE_TASK_ID);
    Task selectedTask = (Task) getSharedSessionAttribute(ASSOCIATED_TASK);

    if (selectedTask == null && bridgeTaskId != null) {
      switchTaskId(bridgeTaskId);
    }
    if (selectedTask != null && bridgeTaskId != null && !selectedTask.getId().equals(bridgeTaskId)) {
      // task switched, TODO: think about if correct like this
      switchTaskId(bridgeTaskId);
    }
  }
  
  private void switchTaskId(String taskId) {
    Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
    if (task == null) {
      throw new ProcessEngineCdiException("Task with id '" + taskId + "' does not exist.");
    }
    setSharedSessionAttribute(ASSOCIATED_TASK, task);

    Execution execution = processEngine.getRuntimeService().createExecutionQuery().executionId(task.getExecutionId()).singleResult();
    if (execution == null) {
      // we owe you a beer if this will ever happen!
      throw new ProcessEngineCdiException("Execution with id '" + task.getExecutionId() + "' does not exist.");
    }
    setSharedSessionAttribute(ASSOCIATED_EXECUTION, execution);
    
    // evict cached variables
    setSharedSessionAttribute(VARIABLE_CACHE, null);
    
    // reset switch
    setSharedSessionAttribute(BRIDGE_TASK_ID, null);
  }

  @Override
  public Map<String, Object> getCachedVariables() {
     Map<String, Object> cachedVariables = (Map<String, Object>) getSharedSessionAttribute(VARIABLE_CACHE);
     if (cachedVariables==null) {
       cachedVariables = new HashMap<String, Object>();
       setSharedSessionAttribute(VARIABLE_CACHE, cachedVariables);
     }
     return cachedVariables;
  }
}
