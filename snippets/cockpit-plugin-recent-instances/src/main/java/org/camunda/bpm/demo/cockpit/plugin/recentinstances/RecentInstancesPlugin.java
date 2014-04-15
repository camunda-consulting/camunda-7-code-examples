package org.camunda.bpm.demo.cockpit.plugin.recentinstances;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class RecentInstancesPlugin extends AbstractCockpitPlugin {

  public static final String ID = "recent-instances";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(RecentInstancesRootResource.class);

    return classes;
  }

}
