package com.camunda.consulting;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.rest.impl.JaxRsTwoDefaultProcessEngineRestServiceImpl;

@RequestScoped
@Path("engine-rest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuarkusProcessEngineRestServiceImpl {

  @Context
  ResourceContext context;

  @Path("")
  public JaxRsTwoDefaultProcessEngineRestServiceImpl defaultProcessEngineService() {
    return this.context.getResource(JaxRsTwoDefaultProcessEngineRestServiceImpl.class);
  }

}
