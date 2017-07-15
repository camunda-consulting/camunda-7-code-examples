package com.camunda.demo.embedded_engine_without_spring.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;

import com.camunda.demo.embedded_engine_without_spring.businesslogic.persistence.GuestEntry;

@Stateless
@Path("process")
public class ProcessResource {

  @Inject
  RuntimeService runtimeService;
  
  @Inject
  HistoryService historyService;
  
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<HistoricProcessInstance> all() {
    return historyService.createHistoricProcessInstanceQuery().list();
  }
  
  @GET
  @Path("{id}")
  public HistoricProcessInstance find(@PathParam("id") String id) {
    return historyService.createHistoricProcessInstanceQuery().processInstanceId(id).singleResult();
  }
  
  @POST
  public Response start(GuestEntry entry, @Context UriInfo info) {
    String content = entry.getContent();
    Map<String,Object> variables = new HashMap<String, Object>();
    variables.put("content", content);
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("embedded-engine-without-spring", variables);
    String id = processInstance.getId();
    URI uri = info.getAbsolutePathBuilder().path("/" + id).build();
    return Response.created(uri).build();
  }

}
