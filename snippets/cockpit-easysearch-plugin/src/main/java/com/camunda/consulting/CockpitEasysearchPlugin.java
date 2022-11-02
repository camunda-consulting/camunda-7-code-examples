package com.camunda.consulting;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class CockpitEasysearchPlugin extends AbstractCockpitPlugin {
  public static final String ID = "cockpit-easysearch-plugin";

  @Override
  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(CockpitEasysearchPluginRootResource.class);
    return classes;
  }

}
