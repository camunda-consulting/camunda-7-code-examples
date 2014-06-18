package org.camunda.bpm.cockpit.ige.plugin.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.camunda.bpm.cockpit.ige.plugin.IGEBusinessCockpitPlugin;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

@Path("plugin/" + IGEBusinessCockpitPlugin.ID)
public class IGEBusinessPluginRootResource extends AbstractCockpitPluginRootResource {

  public IGEBusinessPluginRootResource() {
    super(IGEBusinessCockpitPlugin.ID);
  }

  @Path("{engineName}/business-data")
  public IGEBusinessDataResource getBusinessDataResource(@PathParam("engineName") String engineName) {
    return subResource(new IGEBusinessDataResource(engineName), engineName);
  }
}
