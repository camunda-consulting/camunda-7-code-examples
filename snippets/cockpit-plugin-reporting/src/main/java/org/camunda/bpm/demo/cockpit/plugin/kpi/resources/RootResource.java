package org.camunda.bpm.demo.cockpit.plugin.kpi.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;
import org.camunda.bpm.demo.cockpit.plugin.kpi.ProcessCountBarPlugin;

@Path("plugin/" + ProcessCountBarPlugin.ID)
public class RootResource extends AbstractCockpitPluginRootResource {

  public RootResource() {
    super(ProcessCountBarPlugin.ID);
  }

  @Path("{engineName}/{processDefinitionKey}/process-instance-count")
  public ProcessInstanceCountResource getProcessInstanceResource(@PathParam("engineName") String engineName, @PathParam("processDefinitionKey") String processDefinitionKey) {
    return subResource(new ProcessInstanceCountResource(engineName, processDefinitionKey), engineName);
  }
  

  @Path("{engineName}/process-instance-count")
  public ProcessInstanceCountResource getProcessInstanceResource(@PathParam("engineName") String engineName) {
    return subResource(new ProcessInstanceCountResource(engineName, null), engineName);
  }

  @Path("{engineName}/{processDefinitionKey}/cycle-time/hour")
  public CycleTimePerHourResourceResource getCycleTimePerHour(@PathParam("engineName") String engineName, @PathParam("processDefinitionKey") String processDefinitionKey) {
    return subResource(new CycleTimePerHourResourceResource(engineName, processDefinitionKey), engineName);
  }

  @Path("{engineName}/{processDefinitionKey}/ratio")
  public RatioResource getRatioResurce(@PathParam("engineName") String engineName, @PathParam("processDefinitionKey") String processDefinitionKey) {
    return subResource(new RatioResource(engineName, processDefinitionKey), engineName);
  }


}
