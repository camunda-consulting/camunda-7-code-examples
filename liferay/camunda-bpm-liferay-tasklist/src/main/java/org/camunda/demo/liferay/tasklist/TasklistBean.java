package org.camunda.demo.liferay.tasklist;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionResponse;
import javax.xml.namespace.QName;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.application.ProcessApplicationInfo;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.task.Task;

import com.liferay.faces.portal.context.LiferayFacesContext;
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

//@Named("tasklist")
@ManagedBean(name="tasklist")
@RequestScoped
public class TasklistBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private TaskService taskService;
  
  @Inject
  private ProcessEngine processEngine;

  private String lastTaskPortletId;
  
  public ProcessDefinition getProcessDefinitionForTask(Task task) {
    // TODO: Add cache
    return processEngine.getRepositoryService().getProcessDefinition(task.getProcessDefinitionId());
  }

  public List<Task> getPersonalTasks() {
    // TODO: add logic to query only user tasks
    User user = LiferayFacesContext.getInstance().getUser();
    // at the moment return all tasks
    return taskService.createTaskQuery().list();
  }
  
  public List<ProcessDefinition> getStartableProcessDefinitions() {
    List<ProcessDefinition> processDefinitionList = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().active().list();
    return processDefinitionList;
  }
  
  public void startProcessInstance(ProcessDefinition pd) {
    // TODO: Show start form if configured
    processEngine.getRuntimeService().startProcessInstanceById(pd.getId());
  }

  public void selectTask(Task task) {
    String formKey = processEngine.getFormService().getTaskFormData(task.getId()).getFormKey();

    // now context path
    ProcessDefinition processDefinition = processEngine.getRepositoryService().getProcessDefinition(task.getProcessDefinitionId());
    String processApplicationName = processEngine.getManagementService().getProcessApplicationForDeployment(processDefinition.getDeploymentId());
    String contextPath = BpmPlatform.getProcessApplicationService().getProcessApplicationInfo(processApplicationName).getProperties().get(ProcessApplicationInfo.PROP_SERVLET_CONTEXT_PATH);

    try {
      FacesContext facesContext = FacesContext.getCurrentInstance();
      ThemeDisplay themeDisplay = (ThemeDisplay) facesContext.getExternalContext().getRequestMap().get(WebKeys.THEME_DISPLAY);

      String portletId = getPortletId(formKey, contextPath);
      String columnId = "column-2";
              
      Layout layout = (Layout) facesContext.getExternalContext().getRequestMap().get(WebKeys.LAYOUT);
      LayoutTypePortlet layoutTypePortlet = (LayoutTypePortlet) layout.getLayoutType();
      
      // TODO: Think about which portlets to remove
      for (Portlet p : layoutTypePortlet.getAllPortlets()) {
        // an instance ID is added at the end - so only check startsWith
        if (p.getPortletId().startsWith(portletId)) {
          layoutTypePortlet.removePortletId(themeDisplay.getUserId(), p.getPortletId());
        }
      }
      if (lastTaskPortletId!=null) {
        layoutTypePortlet.removePortletId(themeDisplay.getUserId(), lastTaskPortletId);
      }
      
      // add the new one
      // TODO: add message if task form portlet cannot be found
      lastTaskPortletId = layoutTypePortlet.addPortletId(themeDisplay.getUserId(), portletId, columnId, -1);
      // http://www.liferay.com/de/community/forums/-/message_boards/message/3575947

      LayoutLocalServiceUtil.updateLayout(layout);
    } catch (Exception ex) {
      throw new RuntimeException("Could not add portlet for task. Root error: " + ex.getMessage(), ex);
    }
    
    // now send the event (to everybody interessted - inclduing the new portlet)    
    QName qName = new QName("http://camunda.org/events", "ipc.taskSelected");
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    ActionResponse actionResponse = (ActionResponse) externalContext.getResponse();
    actionResponse.setEvent(qName, task.getId());
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
