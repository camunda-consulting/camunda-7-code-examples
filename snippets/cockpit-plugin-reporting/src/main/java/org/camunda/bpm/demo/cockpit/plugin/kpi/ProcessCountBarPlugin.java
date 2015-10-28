package org.camunda.bpm.demo.cockpit.plugin.kpi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;
import org.camunda.bpm.demo.cockpit.plugin.kpi.resources.RootResource;

public class ProcessCountBarPlugin extends AbstractCockpitPlugin {

  public static final String ID = "reporting-process-count";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(RootResource.class);

    return classes;
  }

  @Override
  public List<String> getMappingFiles() {
    return Arrays.asList("org/camunda/bpm/demo/cockpit/plugin/kpi/queries/camunda-kpi-queries.xml");
  }
}
