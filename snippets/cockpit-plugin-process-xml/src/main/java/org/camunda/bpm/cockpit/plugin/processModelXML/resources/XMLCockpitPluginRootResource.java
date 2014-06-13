package org.camunda.bpm.cockpit.plugin.processModelXML.resources;

import javax.ws.rs.Path;
import org.camunda.bpm.cockpit.plugin.processModelXML.XMLCockpitPlugin;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;

@Path("plugin/" + XMLCockpitPlugin.ID)
public class XMLCockpitPluginRootResource extends
		AbstractCockpitPluginRootResource {
  
  public XMLCockpitPluginRootResource() {
    super(XMLCockpitPlugin.ID);
  }
    
	public XMLCockpitPluginRootResource(String pluginName) {
		super(XMLCockpitPlugin.ID);
	}
}