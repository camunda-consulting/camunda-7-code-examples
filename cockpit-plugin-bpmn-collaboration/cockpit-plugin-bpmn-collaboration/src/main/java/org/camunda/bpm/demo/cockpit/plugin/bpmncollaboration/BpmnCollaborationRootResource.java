package org.camunda.bpm.demo.cockpit.plugin.bpmncollaboration;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractPluginRootResource;

/**
 * root resource to select sub resource for the selected engine (as you could run multiple engines)
 * 
 * @author ruecker
 *
 */
@Path("plugin/" + BpmnCollaborationPlugin.ID)
public class BpmnCollaborationRootResource extends AbstractPluginRootResource {

  public BpmnCollaborationRootResource() {
    super(BpmnCollaborationPlugin.ID);
  }

  @Path("{engineName}/")
  public BpmnCollaborationEngineSpecificResource getProcessInstanceResource(@PathParam("engineName") String engineName) {
    return subResource(new BpmnCollaborationEngineSpecificResource(engineName), engineName);
  }
}
