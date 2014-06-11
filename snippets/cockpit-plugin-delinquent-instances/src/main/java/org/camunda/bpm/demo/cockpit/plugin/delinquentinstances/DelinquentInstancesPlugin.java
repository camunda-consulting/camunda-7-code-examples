package org.camunda.bpm.demo.cockpit.plugin.delinquentinstances;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class DelinquentInstancesPlugin extends AbstractCockpitPlugin {

  public static final String ID = "delinquent-instances";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(DelinquentInstancesRootResource.class);

    return classes;
  }

}
