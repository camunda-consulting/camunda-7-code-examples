package com.camunda.demo.plugin.tasklist.forms.external;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.tasklist.plugin.spi.impl.AbstractTasklistPlugin;

import com.camunda.demo.plugin.tasklist.forms.external.resources.SamplePluginRootResource;

public class NiceExternalFormsPlugin extends AbstractTasklistPlugin {

  public static final String ID = "nice-external-forms-plugin";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(SamplePluginRootResource.class);

    return classes;
    
    
  }
  
}
