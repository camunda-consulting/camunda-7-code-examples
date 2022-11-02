package com.camunda.consulting;

import javax.ws.rs.Path;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

@Path("plugin/" + CockpitEasysearchPlugin.ID)
public class CockpitEasysearchPluginRootResource extends AbstractCockpitPluginRootResource {

  public CockpitEasysearchPluginRootResource() {
    super(CockpitEasysearchPlugin.ID);
  }

}
