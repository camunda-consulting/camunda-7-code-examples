package org.camunda.bpm.cockpit.ige.plugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.cockpit.ige.plugin.resources.IGEBusinessPluginRootResource;
import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class IGEBusinessCockpitPlugin extends AbstractCockpitPlugin {

  public static final String ID = "ige-business-cockpit";

  public String getId() {
    return ID;
  }

  @Override
  public List<String> getMappingFiles() {
    return Arrays.asList("org/camunda/bpm/cockpit/ige/plugin/queries/igeBusinessDataQuery.xml");
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> resourceClasses = new HashSet<Class<?>>();
    resourceClasses.add(IGEBusinessPluginRootResource.class);
    return resourceClasses;
  }

}
