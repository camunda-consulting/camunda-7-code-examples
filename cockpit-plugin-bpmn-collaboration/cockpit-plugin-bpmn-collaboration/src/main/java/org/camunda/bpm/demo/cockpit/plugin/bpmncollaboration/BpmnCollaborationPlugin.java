package org.camunda.bpm.demo.cockpit.plugin.bpmncollaboration;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class BpmnCollaborationPlugin extends AbstractCockpitPlugin {

  public static final String ID = "bpmn-collaboration";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(BpmnCollaborationRootResource.class);

    return classes;
  }

}
