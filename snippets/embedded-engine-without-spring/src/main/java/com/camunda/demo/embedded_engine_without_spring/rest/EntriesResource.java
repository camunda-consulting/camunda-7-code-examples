package com.camunda.demo.embedded_engine_without_spring.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.camunda.demo.embedded_engine_without_spring.businesslogic.GuestService;
import com.camunda.demo.embedded_engine_without_spring.businesslogic.persistence.GuestEntry;

@Stateless
@Path("entries")
public class EntriesResource {

  @Inject
  GuestService service;
  
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<GuestEntry> all() {
    return service.all();
  }
  
  @GET
  @Path("{id}")
  public GuestEntry find(@PathParam("id") long id) {
    return this.service.find(id);
  }
  
  @GET
  public GuestEntry findbyid(@QueryParam("content") String content) {
    return new GuestEntry(content);
  }
  
  @POST
  public Response save(GuestEntry entry, @Context UriInfo info) {
    GuestEntry created = this.service.save(entry);
    long id = created.getId();
    URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
    return Response.created(uri).build();
  }
}
