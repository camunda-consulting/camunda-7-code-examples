package org.camunda.bpm.cockpit.plugin.processModelXML;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.processModelXML.resources.XMLCockpitPluginRootResource;
import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class XMLCockpitPlugin extends AbstractCockpitPlugin {

	public static final String ID = "xml-plugin";

	public String getId() {
		return ID;
	}

	@Override
	public Set<Class<?>> getResourceClasses() {
		Set<Class<?>> resourceClasses = new HashSet<Class<?>>();
		resourceClasses.add(XMLCockpitPluginRootResource.class);
		return resourceClasses;
	}
	
}
