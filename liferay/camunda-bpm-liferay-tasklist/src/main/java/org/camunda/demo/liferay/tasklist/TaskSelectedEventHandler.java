package org.camunda.demo.liferay.tasklist;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.portlet.Event;
import javax.portlet.PortletSession;
import javax.portlet.faces.event.EventNavigationResult;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.application.ProcessApplicationInfo;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.task.Task;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

public class TaskSelectedEventHandler extends CdiEnabledBridgeEventHandler {

  @Inject
  private TaskService taskService;

  @Inject
  private ProcessEngine processEngine;

  // @Inject
  // private SelectedTaskBean selectedTaskBean;

  /**
   * internal method capable of CDI injections
   * 
   * @throws SystemException
   * @throws PortalException
   */
  protected EventNavigationResult handleEventWithCdi(FacesContext facesContext, Event event) {
    EventNavigationResult eventNavigationResult = null;
    String eventQName = event.getQName().toString();

    if (eventQName.equals("{http://camunda.org/events}ipc.taskSelected")) {
      String taskId = (String) event.getValue();

      Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
      if (task == null) {
        return null;
      }
      
//      new SelectedTaskBean().setTask(task);

      String formKey = processEngine.getFormService().getTaskFormData(task.getId()).getFormKey();

      // now context path
      ProcessDefinition processDefinition = processEngine.getRepositoryService().getProcessDefinition(task.getProcessDefinitionId());
      String processApplicationName = processEngine.getManagementService().getProcessApplicationForDeployment(processDefinition.getDeploymentId());
      String contextPath = BpmPlatform.getProcessApplicationService().getProcessApplicationInfo(processApplicationName).getProperties().get(ProcessApplicationInfo.PROP_SERVLET_CONTEXT_PATH);
   

      try {
        ThemeDisplay themeDisplay = (ThemeDisplay) facesContext.getExternalContext().getRequestMap().get(WebKeys.THEME_DISPLAY);

        String portletId = getPortletId(formKey, contextPath);
        System.out.println("###: " + portletId);
        
//        String dragPortletid = "tasklist_WAR_jsf2cdiportlet";         
        String columnId = "column-2";
                
        Layout layout = (Layout) facesContext.getExternalContext().getRequestMap().get(WebKeys.LAYOUT);
        LayoutTypePortlet layoutTypePortlet = (LayoutTypePortlet) layout.getLayoutType();
        for (Portlet p : layoutTypePortlet.getAllPortlets()) {
          // an instance ID is added at the end
          if (p.getPortletId().startsWith(portletId)) {
            layoutTypePortlet.removePortletId(themeDisplay.getUserId(), p.getPortletId());
          }
        }
        // add the new one
        String instanceId = layoutTypePortlet.addPortletId(themeDisplay.getUserId(), portletId, columnId, -1);
        // http://www.liferay.com/de/community/forums/-/message_boards/message/3575947

        LayoutLocalServiceUtil.updateLayout(layout);
      } catch (Exception ex) {
        throw new RuntimeException("Could not add portlet for task. Root error: " + ex.getMessage(), ex);
      }

      
    }
    return eventNavigationResult;

  }

  /** copied from https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/service/impl/PortletLocalServiceImpl.java
   * 
   * see http://stackoverflow.com/questions/11151001/how-to-get-portlet-id-using-the-portlet-name-in-liferay
   */
  public String getPortletId(String portletName, String servletContextName) {
    String portletId = portletName;

    if (Validator.isNotNull(servletContextName)) {
      // TODO: Remove leading "/"?
      if (servletContextName.startsWith("/")) {
        servletContextName = servletContextName.substring(1);
      }
      portletId = portletId.concat(PortletConstants.WAR_SEPARATOR).concat(servletContextName);
    }

    portletId = PortalUtil.getJsSafePortletId(portletId);
    return portletId;
  }

  // workaround for scopes - see
  // http://www.portletfaces.org/doc/portletfaces-bridge/2.0.0/docbook/en-US/html/ch04s05.html
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

  //
  // protected TaskBean getTaskBean(FacesContext facesContext) {
  // String elExpression = "#{taskBean}";
  // ELContext elContext = facesContext.getELContext();
  // ValueExpression valueExpression =
  // facesContext.getApplication().getExpressionFactory().createValueExpression(elContext,
  // elExpression, TaskBean.class);
  //
  // return (TaskBean) valueExpression.getValue(elContext);
  // }
}
