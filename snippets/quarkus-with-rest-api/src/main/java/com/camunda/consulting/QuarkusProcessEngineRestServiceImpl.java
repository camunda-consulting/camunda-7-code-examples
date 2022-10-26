package com.camunda.consulting;

import org.camunda.bpm.engine.rest.impl.DefaultProcessEngineRestServiceImpl;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("engine-rest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuarkusProcessEngineRestServiceImpl {

  @Context
  ResourceContext context;

  @Path("")
  public DefaultProcessEngineRestServiceImpl defaultProcessEngineService() {
    return this.context.getResource(DefaultProcessEngineRestServiceImpl.class);
  }

}
