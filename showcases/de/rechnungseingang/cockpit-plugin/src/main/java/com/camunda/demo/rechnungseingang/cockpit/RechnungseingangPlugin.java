package com.camunda.demo.rechnungseingang.cockpit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

import com.camunda.demo.rechnungseingang.cockpit.resources.ReportingPluginRootResource;

public class RechnungseingangPlugin extends AbstractCockpitPlugin {

  public static final String ID = "rechnungseingang";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(ReportingPluginRootResource.class);

    return classes;
  }

  @Override
  public List<String> getMappingFiles() {
    return Arrays.asList("com/camunda/demo/rechnungseingang/cockpit/queries/reporting-queries.xml");
  }
}
