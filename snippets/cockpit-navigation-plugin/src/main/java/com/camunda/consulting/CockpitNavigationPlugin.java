package com.camunda.consulting;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class CockpitNavigationPlugin extends AbstractCockpitPlugin {
  public static final String ID = "cockpit-navigation-plugin";

  @Override
  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(CockpitNavigationPluginRootResource.class);
    return classes;
  }

}
