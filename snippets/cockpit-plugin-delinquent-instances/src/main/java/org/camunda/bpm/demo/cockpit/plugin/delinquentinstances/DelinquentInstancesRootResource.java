package org.camunda.bpm.demo.cockpit.plugin.delinquentinstances;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

/**
 * root resource to select sub resource for the selected engine (as you could run multiple engines)
 * 
 * @author ruecker
 *
 */
@Path("plugin/" + DelinquentInstancesPlugin.ID)
public class DelinquentInstancesRootResource extends AbstractCockpitPluginRootResource {

  public DelinquentInstancesRootResource() {
    super(DelinquentInstancesPlugin.ID);
  }

  @Path("{engineName}/")
  public DelinquentProcessInstanceResource getProcessInstanceResource(@PathParam("engineName") String engineName) {
    return subResource(new DelinquentProcessInstanceResource(engineName), engineName);
  }
}
