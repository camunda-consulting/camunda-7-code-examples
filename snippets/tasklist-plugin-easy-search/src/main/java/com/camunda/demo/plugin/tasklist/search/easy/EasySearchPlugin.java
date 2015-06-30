package com.camunda.demo.plugin.tasklist.search.easy;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.tasklist.plugin.spi.impl.AbstractTasklistPlugin;

import com.camunda.demo.plugin.tasklist.search.easy.resources.SamplePluginRootResource;

public class EasySearchPlugin extends AbstractTasklistPlugin {

  public static final String ID = "easy-search-plugin";

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
