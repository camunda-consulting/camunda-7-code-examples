package org.camunda.demo.liferay.tasklist;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletSession;

public class PortletSessionUtil {

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
}