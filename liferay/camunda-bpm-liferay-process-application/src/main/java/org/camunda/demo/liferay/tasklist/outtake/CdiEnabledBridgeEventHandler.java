package org.camunda.demo.liferay.tasklist.outtake;

import javax.faces.context.FacesContext;
import javax.portlet.Event;
import javax.portlet.faces.BridgeEventHandler;
import javax.portlet.faces.event.EventNavigationResult;

import org.camunda.bpm.engine.cdi.impl.util.ProgrammaticBeanLookup;

/**
 * Workaround to handle mising CDI injection in BridgeEventHandler (https://issues.apache.org/jira/browse/PORTLETBRIDGE-197).
 * Lookup the bean itself via CDI and call the real internal method.
 * 
 * @author ruecker
 */
public abstract class CdiEnabledBridgeEventHandler implements BridgeEventHandler {
  
  public final EventNavigationResult handleEvent(FacesContext facesContext, Event event) {    
    CdiEnabledBridgeEventHandler taskSelectedEventHandler = ProgrammaticBeanLookup.lookup(this.getClass());
    return taskSelectedEventHandler.handleEventWithCdi(facesContext, event);
  }

  protected abstract EventNavigationResult handleEventWithCdi(FacesContext facesContext, Event event);

}
