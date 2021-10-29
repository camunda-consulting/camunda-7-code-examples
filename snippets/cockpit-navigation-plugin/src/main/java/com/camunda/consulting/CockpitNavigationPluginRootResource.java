package com.camunda.consulting;

import javax.ws.rs.Path;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

@Path("plugin/" + CockpitNavigationPlugin.ID)
public class CockpitNavigationPluginRootResource extends AbstractCockpitPluginRootResource {

  public CockpitNavigationPluginRootResource() {
    super(CockpitNavigationPlugin.ID);
  }

}
