package org.camunda.demo.liferay.tasklist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.xml.namespace.QName;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.application.ProcessApplicationInfo;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.task.Task;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

@Named("tasklist")
public class TasklistBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private TaskService taskService;
  
  @Inject
  private ProcessEngine processEngine;
  
  private List<String> addedPortlets = new ArrayList<String>();
  
  public ProcessDefinition getProcessDefinitionForTask(Task task) {
    // TODO: Add cache
    return processEngine.getRepositoryService().getProcessDefinition(task.getProcessDefinitionId());
  }

  public List<Task> getPersonalTasks() {
    // TODO: add logic to query only user tasks
    User user = LiferayFacesContext.getInstance().getUser();
    // at the moment return all tasks
    return taskService.createTaskQuery().orderByTaskCreateTime().desc().list();
  }
  
  public List<ProcessDefinition> getStartableProcessDefinitions() {
    List<ProcessDefinition> processDefinitionList = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().active().list();
    return processDefinitionList;
  }
  
  public void startProcessInstance(ProcessDefinition pd) {
    if (pd.hasStartFormKey()) {
      String formKey = processEngine.getFormService().getStartFormKey(pd.getId());;
      String portletId = constructPortletId(pd.getId(), formKey);
      setSharedAttribute(pd);
      showPortlet(portletId);
    }
    else {      
      processEngine.getRuntimeService().startProcessInstanceById(pd.getId());
    }
  }

  public void selectTask(Task task) {
    String formKey = processEngine.getFormService().getTaskFormKey(task.getProcessDefinitionId(), task.getTaskDefinitionKey());
    String portletId = constructPortletId(task.getProcessDefinitionId(), formKey);
    
    setSharedAttribute(task);    
    showPortlet(portletId);
    // now send the event (to everybody interested - including the new portlet) 
    //    sendIpcEvent(task);
  }

  private void showPortlet(String portletId) {
    // this is hard coded at the moment - put it in the bigger column (make configurable?)
    String columnId = "column-2";
    
    try {
      FacesContext facesContext = FacesContext.getCurrentInstance();
      ThemeDisplay themeDisplay = (ThemeDisplay) facesContext.getExternalContext().getRequestMap().get(WebKeys.THEME_DISPLAY);              
      Layout layout = (Layout) facesContext.getExternalContext().getRequestMap().get(WebKeys.LAYOUT);
      LayoutTypePortlet layoutTypePortlet = (LayoutTypePortlet) layout.getLayoutType();
      long userId = themeDisplay.getUserId();
      
      removeAllOfSameTaskFormPortlets(portletId, userId, layoutTypePortlet);
      for (String portletIdToRemove : addedPortlets) {        
        layoutTypePortlet.removePortletId(userId, portletIdToRemove);
      }
      
      // add the new one
      // TODO: add message if task form portlet cannot be found
      String lastTaskPortletId = layoutTypePortlet.addPortletId(themeDisplay.getUserId(), portletId, columnId, -1);
      // http://www.liferay.com/de/community/forums/-/message_boards/message/3575947
      addedPortlets.add(lastTaskPortletId);

      LayoutLocalServiceUtil.updateLayout(layout);
    } catch (Exception ex) {
      throw new RuntimeException("Could not add portlet from tasklist. Root error: " + ex.getMessage(), ex);
    }
  }

  private String constructPortletId(String processDefinitionId, String formKey) {
    // now context path
    ProcessDefinition processDefinition = processEngine.getRepositoryService().getProcessDefinition(processDefinitionId);
    String processApplicationName = processEngine.getManagementService().getProcessApplicationForDeployment(processDefinition.getDeploymentId());
    String contextPath = BpmPlatform.getProcessApplicationService().getProcessApplicationInfo(processApplicationName).getProperties().get(ProcessApplicationInfo.PROP_SERVLET_CONTEXT_PATH);

    // this gives us the PortletId
    String portletId = getPortletId(formKey, contextPath);
    return portletId;
  }

  private void removeAllOfSameTaskFormPortlets(String portletId, long userId, LayoutTypePortlet layoutTypePortlet) throws PortalException,
      SystemException {
    for (Portlet p : layoutTypePortlet.getAllPortlets()) {
      // an instance ID is added at the end - so only check startsWith
      if (p.getPortletId().startsWith(portletId)) {
        layoutTypePortlet.removePortletId(userId, p.getPortletId());
      }
    }
  }

  /**
   * Do this as a workaround till IPC works correctly
   * see http://www.liferay.com/de/community/forums/-/message_boards/message/27295776
   */
  private void setSharedAttribute(Task task) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    PortletSession portletSession = (PortletSession) externalContext.getSession(false);
    // make sure you have <private-session-attributes>false</private-session-attributes> in liferay-portal.xml
    portletSession.setAttribute("camunda.bridge.selected.task.id", task.getId(), PortletSession.APPLICATION_SCOPE);
  }

  private void sendIpcEvent(Task task) {
    QName qName = new QName("http://camunda.org/events", "ipc.taskSelected");
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    ActionResponse actionResponse = (ActionResponse) externalContext.getResponse();
    actionResponse.setEvent(qName, task.getId());
  }

  private void setSharedAttribute(ProcessDefinition pd) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ExternalContext externalContext = facesContext.getExternalContext();
    PortletSession portletSession = (PortletSession) externalContext.getSession(false);
    // make sure you have <private-session-attributes>false</private-session-attributes> in liferay-portal.xml
    portletSession.setAttribute("camunda.bridge.selected.processdefinition.id", pd.getId(), PortletSession.APPLICATION_SCOPE);
  }
  
  /** copied from https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/service/impl/PortletLocalServiceImpl.java
   * 
   * see http://stackoverflow.com/questions/11151001/how-to-get-portlet-id-using-the-portlet-name-in-liferay
   */
  public String getPortletId(String portletName, String servletContextName) {
    String portletId = portletName;

    if (Validator.isNotNull(servletContextName)) {
      if (servletContextName.startsWith("/")) {
        servletContextName = servletContextName.substring(1);
      }
      portletId = portletId.concat(PortletConstants.WAR_SEPARATOR).concat(servletContextName);
    }

    portletId = PortalUtil.getJsSafePortletId(portletId);
    return portletId;
  }
}
