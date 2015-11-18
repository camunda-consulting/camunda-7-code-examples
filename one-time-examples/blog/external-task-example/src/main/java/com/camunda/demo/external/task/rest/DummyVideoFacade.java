package com.camunda.demo.external.task.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class DummyVideoFacade {  

  @POST
  @Path("{video}/publish")
  @Consumes(MediaType.APPLICATION_JSON)
  public void publishVideo(@PathParam("video") String video) {    
    System.out.println("VIDEO '" + video + "' published");
  }


}
