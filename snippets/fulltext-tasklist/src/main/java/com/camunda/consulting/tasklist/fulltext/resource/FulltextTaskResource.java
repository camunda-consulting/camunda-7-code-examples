package com.camunda.consulting.tasklist.fulltext.resource;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.camunda.consulting.tasklist.fulltext.FulltextTaskHandler;
import com.camunda.consulting.tasklist.fulltext.entity.UserTask;

@Path("/exceptiontext")
public class FulltextTaskResource {
  
  public static final String EXCEPTION_SNIPPET = "exceptionSnippet";

  private static final Logger log = Logger.getLogger(FulltextTaskResource.class.getName());

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<UserTask> getExceptionTasks(@Context UriInfo uriInfo) {
    log.info("getExceptionTasks " + uriInfo);
    MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
    log.info("queryParameter: " + queryParameters);
    
    String exceptionSnippet = null;
    if (queryParameters.containsKey(EXCEPTION_SNIPPET)) {
      exceptionSnippet = queryParameters.getFirst(EXCEPTION_SNIPPET);
      log.info("snippet: " + exceptionSnippet);
    } else {
      log.info("No exceptionSnippet");
    }

    return fulltextHandler.findUserTasksWithExceptionLike(exceptionSnippet);
//    return new FulltextData().fixedList();
  }
  
  @Inject
  private FulltextTaskHandler fulltextHandler;
  
}
