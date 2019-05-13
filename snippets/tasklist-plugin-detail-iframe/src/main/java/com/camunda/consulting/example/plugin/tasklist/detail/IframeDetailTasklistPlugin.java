package com.camunda.consulting.example.plugin.tasklist.detail;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.tasklist.plugin.spi.impl.AbstractTasklistPlugin;

import com.camunda.consulting.example.plugin.tasklist.detail.resources.IframeDetailTasklistPluginRootResource;

public class IframeDetailTasklistPlugin extends AbstractTasklistPlugin {

  public static final String ID = "iframe-detail-tasklist-plugin";

  public String getId() {
    return ID;
  }
  
  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(IframeDetailTasklistPluginRootResource.class);
    return classes;
  }
  
}
