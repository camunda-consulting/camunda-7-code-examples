package com.camunda.demo.plugin.tasklist.search.easy.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.tasklist.resource.AbstractTasklistPluginResource;

public class ProcessInstanceResource extends AbstractTasklistPluginResource {

  public ProcessInstanceResource(String engineName) {
    super(engineName);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getProcessInstanceCounts() {
    return "hello world";
  }
}
