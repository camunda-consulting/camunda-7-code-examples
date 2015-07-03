package com.camunda.demo.rechnungseingang.cockpit.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

import com.camunda.demo.rechnungseingang.cockpit.RechnungseingangPlugin;

@Path("plugin/" + RechnungseingangPlugin.ID)
public class ReportingPluginRootResource extends AbstractCockpitPluginRootResource {

  public ReportingPluginRootResource() {
    super(RechnungseingangPlugin.ID);
  }

  @Path("{engineName}/statistics")
  public ProcessInstanceCountResource getProcessInstanceResource(@PathParam("engineName") String engineName) {
    return subResource(new ProcessInstanceCountResource(engineName), engineName);
  }
  
}
