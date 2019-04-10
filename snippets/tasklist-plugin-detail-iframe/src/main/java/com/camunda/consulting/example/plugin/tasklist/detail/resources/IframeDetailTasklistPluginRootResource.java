package com.camunda.consulting.example.plugin.tasklist.detail.resources;

import javax.ws.rs.Path;

import org.camunda.bpm.tasklist.resource.AbstractTasklistPluginRootResource;

import com.camunda.consulting.example.plugin.tasklist.detail.IframeDetailTasklistPlugin;

@Path("plugin/" + IframeDetailTasklistPlugin.ID)
public class IframeDetailTasklistPluginRootResource extends AbstractTasklistPluginRootResource {
  public IframeDetailTasklistPluginRootResource() {
    super(IframeDetailTasklistPlugin.ID);
  }
}
