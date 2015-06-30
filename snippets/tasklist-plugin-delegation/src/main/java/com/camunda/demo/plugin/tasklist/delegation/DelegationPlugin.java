package com.camunda.demo.plugin.tasklist.delegation;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.tasklist.plugin.spi.impl.AbstractTasklistPlugin;

import com.camunda.demo.plugin.tasklist.delegation.resources.SamplePluginRootResource;

public class DelegationPlugin extends AbstractTasklistPlugin {

  public static final String ID = "delegation-plugin";

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
