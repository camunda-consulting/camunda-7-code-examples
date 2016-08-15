package org.camunda.consulting.cockpit.plugin.kpioverview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;
import org.camunda.consulting.cockpit.plugin.kpioverview.resources.KPIOverviewPluginRootResource;

public class KPIOverviewPlugin extends AbstractCockpitPlugin {

  public static final String ID = "kpi-overview-plugin";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(KPIOverviewPluginRootResource.class);

    return classes;
  }
  
  @Override
  public List<String> getMappingFiles() {
    return Arrays.asList("org/camunda/consulting/cockpit/plugin/kpi/queries/camunda-kpi-queries.xml");
  }
}
