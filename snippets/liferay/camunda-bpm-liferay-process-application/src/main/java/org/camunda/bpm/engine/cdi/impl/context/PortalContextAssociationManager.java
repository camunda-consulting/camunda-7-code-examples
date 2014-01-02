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
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;

/**
 * {@link ContextAssociationManager} which stores the association in the
 * {@link PortletSession} as workaround to problems with CDI
 * {@link Conversation}s
 * (http://www.liferay.com/community/forums/-/message_boards/message/27323345).
 * 
 * I experimented to use {@link SessionScoped} in order to store the association
 * - but this causes class loading problems on hot redeployment if objects are
 * still in the session with old classes.
 * 
 * It can be influenced by an attribute in the {@link PortletSession} set by
 * another portlet as workaround to not use IPC because of problems
 * (http://www.liferay.com/community/forums/-/message_boards/message/27267640).
 * 
 * @author ruecker
 */
@Specializes
public class PortalContextAssociationManager extends DefaultContextAssociationManager {

  private static final long serialVersionUID = 1L;

  public static final String BRIDGE_TASK_ID = "camunda.bridge.selected.task.id";
  public static final String BRIDGE_PROCESS_DEFINITION_ID = "camunda.bridge.selected.processdefinition.id";

  public static final String ASSOCIATED_TASK_ID = "camunda.business.process.associated.task.id";
  public static final String ASSOCIATED_EXECUTION_ID = "camunda.business.process.associated.execution.id";

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
    if (isTriggeredWithinEngine()) {
      super.setExecution(execution);
      return;
    }
    setSharedSessionAttribute(ASSOCIATED_EXECUTION_ID, execution.getId());
  }

  @Override
  public void disAssociate() {
    if (isTriggeredWithinEngine()) {
      super.disAssociate();
      return;
    }

    setSharedSessionAttribute(ASSOCIATED_TASK_ID, null);
    setSharedSessionAttribute(ASSOCIATED_EXECUTION_ID, null);
    setSharedSessionAttribute(VARIABLE_CACHE, null);

    // TODO: really do this?
    setSharedSessionAttribute(BRIDGE_TASK_ID, null);
  }

  @Override
  public Execution getExecution() {
    if (isTriggeredWithinEngine()) {
      return super.getExecution();
    }
    checkTaskSelectedViaBridge();
    ExecutionEntity execution = getExecutionFromContext();
    if (execution != null) {
      return execution;
    } else {
      checkTaskSelectedViaBridge();
      return loadExecution();
    }
  }

  @Override
  public Object getVariable(String variableName) {
    if (isTriggeredWithinEngine()) {
      return super.getVariable(variableName);
    }

    checkTaskSelectedViaBridge();
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
    if (isTriggeredWithinEngine()) {
      super.setVariable(variableName, value);
      return;
    }

    checkTaskSelectedViaBridge();
    ExecutionEntity execution = getExecutionFromContext();
    if (execution != null) {
      execution.setVariable(variableName, value);
      execution.getVariable(variableName);
    } else {
      getCachedVariables().put(variableName, value);
    }
  }

  @Override
  public Task getTask() {
    if (isTriggeredWithinEngine()) {
      return super.getTask();
    }
    checkTaskSelectedViaBridge();
    return loadTask();
  }

  @Override
  public void setTask(Task task) {
    if (isTriggeredWithinEngine()) {
      super.setTask(task);
      return;
    }

    setSharedSessionAttribute(ASSOCIATED_TASK_ID, task.getId());
  }

  private boolean isTriggeredWithinEngine() {
    return (Context.getCommandContext() != null);
  }

  /**
   * "lazy" load task if bridge state has changed in the meantime
   */
  private void checkTaskSelectedViaBridge() {
    String bridgeTaskId = (String) getSharedSessionAttribute(BRIDGE_TASK_ID);
    String selectedTaskId = (String) getSharedSessionAttribute(ASSOCIATED_TASK_ID);

    if (selectedTaskId == null && bridgeTaskId != null) {
      switchTaskId(bridgeTaskId);
    }
    if (selectedTaskId != null && bridgeTaskId != null && !selectedTaskId.equals(bridgeTaskId)) {
      // task switched, TODO: think about if correct like this
      switchTaskId(bridgeTaskId);
    }
  }

  private Task loadTask() {
    String taskId = (String) getSharedSessionAttribute(ASSOCIATED_TASK_ID);
    if (taskId == null) {
      return null;
    }
    Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
    if (task == null) {
      throw new ProcessEngineCdiException("Task with id '" + taskId + "' does not exist.");
    }
    return task;
  }

  private Execution loadExecution() {
    String executionId = (String) getSharedSessionAttribute(ASSOCIATED_EXECUTION_ID);
    if (executionId == null) {
      return null;
    }
    Execution execution = processEngine.getRuntimeService().createExecutionQuery().executionId(executionId).singleResult();
    if (execution == null) {
      // we owe you a beer if this will ever happen!
      throw new ProcessEngineCdiException("Execution with id '" + executionId + "' does not exist.");
    }
    return execution;
  }

  private void switchTaskId(String taskId) {
    Task task = processEngine.getTaskService().createTaskQuery().taskId(taskId).singleResult();
    if (task == null) {
      throw new ProcessEngineCdiException("Task with id '" + taskId + "' does not exist.");
    }
    setSharedSessionAttribute(ASSOCIATED_TASK_ID, taskId);
    setSharedSessionAttribute(ASSOCIATED_EXECUTION_ID, task.getExecutionId());

    // evict cached variables
    setSharedSessionAttribute(VARIABLE_CACHE, null);

    // reset switch
    setSharedSessionAttribute(BRIDGE_TASK_ID, null);
  }

  @Override
  public Map<String, Object> getCachedVariables() {
    if (isTriggeredWithinEngine()) {
      return super.getCachedVariables();
    }

    Map<String, Object> cachedVariables = (Map<String, Object>) getSharedSessionAttribute(VARIABLE_CACHE);
    if (cachedVariables == null) {
      cachedVariables = new HashMap<String, Object>();
      setSharedSessionAttribute(VARIABLE_CACHE, cachedVariables);
    }
    return cachedVariables;
  }
}
