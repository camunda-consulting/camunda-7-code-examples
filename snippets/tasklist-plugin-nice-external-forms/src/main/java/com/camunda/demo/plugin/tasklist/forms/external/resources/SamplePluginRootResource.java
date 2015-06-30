package com.camunda.demo.plugin.tasklist.forms.external.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.tasklist.resource.AbstractTasklistPluginRootResource;

import com.camunda.demo.plugin.tasklist.forms.external.NiceExternalFormsPlugin;

@Path("plugin/" + NiceExternalFormsPlugin.ID)
public class SamplePluginRootResource extends AbstractTasklistPluginRootResource {

  public SamplePluginRootResource() {
    super(NiceExternalFormsPlugin.ID);
  }

  @Path("{engineName}/process-instance")
  public ProcessInstanceResource getProcessInstanceResource(@PathParam("engineName") String engineName) {
    return subResource(new ProcessInstanceResource(engineName), engineName);
  }
}
