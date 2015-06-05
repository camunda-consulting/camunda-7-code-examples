package com.camunda.demo.versicherungsneuantrag.cockpit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

import com.camunda.demo.versicherungsneuantrag.cockpit.resources.ReportingPluginRootResource;

public class VersicherungsneuantragPlugin extends AbstractCockpitPlugin {

  public static final String ID = "versicherungsneuantrag";

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
    return Arrays.asList("com/camunda/demo/versicherungsneuantrag/cockpit/queries/reporting-queries.xml");
  }
}
