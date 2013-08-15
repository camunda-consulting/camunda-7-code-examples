package org.camunda.demo.liferay.tasklist;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.ProcessEngineCdiException;
import org.camunda.bpm.engine.cdi.impl.context.PortalContextAssociationManager;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.theme.ThemeDisplay;

@Named("portal.taskForm")
public class TaskForm {

  @Inject
  private BusinessProcess businessProcess;

  public void completeTask() {

    businessProcess.completeTask();

    removePortlet();
  }

  public void startProcessInstance() {
    String processDefinitionId = (String) PortalContextAssociationManager
        .getSharedSessionAttribute(PortalContextAssociationManager.BRIDGE_PROCESS_DEFINITION_ID);
    if (processDefinitionId == null) {
      throw new ProcessEngineCdiException("no process definition selected to start");
    }
    businessProcess.startProcessById(processDefinitionId);
    removePortlet();
  }

  private void removePortlet() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ThemeDisplay themeDisplay = (ThemeDisplay) facesContext.getExternalContext().getRequestMap().get(WebKeys.THEME_DISPLAY);
    Layout layout = (Layout) facesContext.getExternalContext().getRequestMap().get(WebKeys.LAYOUT);
    LayoutTypePortlet layoutTypePortlet = (LayoutTypePortlet) layout.getLayoutType();

    String portletId = (String) facesContext.getExternalContext().getRequestMap().get(WebKeys.PORTLET_ID);

    layoutTypePortlet.removePortletId(themeDisplay.getUserId(), portletId);
  }
}
