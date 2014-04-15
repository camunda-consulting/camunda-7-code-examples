package org.camunda.bpm.demo.cockpit.plugin.recentinstances;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

/**
 * root resource to select sub resource for the selected engine (as you could run multiple engines)
 * 
 * @author ruecker
 *
 */
@Path("plugin/" + RecentInstancesPlugin.ID)
public class RecentInstancesRootResource extends AbstractCockpitPluginRootResource {

  public RecentInstancesRootResource() {
    super(RecentInstancesPlugin.ID);
  }

  @Path("{engineName}/")
  public RecentProcessInstanceResource getProcessInstanceResource(@PathParam("engineName") String engineName) {
    return subResource(new RecentProcessInstanceResource(engineName), engineName);
  }
}
