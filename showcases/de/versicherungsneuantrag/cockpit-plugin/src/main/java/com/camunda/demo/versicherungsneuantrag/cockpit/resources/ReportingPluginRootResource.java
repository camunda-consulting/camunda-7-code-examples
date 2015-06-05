package com.camunda.demo.versicherungsneuantrag.cockpit.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

import com.camunda.demo.versicherungsneuantrag.cockpit.VersicherungsneuantragPlugin;

@Path("plugin/" + VersicherungsneuantragPlugin.ID)
public class ReportingPluginRootResource extends AbstractCockpitPluginRootResource {

  public ReportingPluginRootResource() {
    super(VersicherungsneuantragPlugin.ID);
  }

  @Path("{engineName}/statistics")
  public ProcessInstanceCountResource getProcessInstanceResource(@PathParam("engineName") String engineName) {
    return subResource(new ProcessInstanceCountResource(engineName), engineName);
  }
  
}
