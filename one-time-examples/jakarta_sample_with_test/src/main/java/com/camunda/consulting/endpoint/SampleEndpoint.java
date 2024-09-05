package com.camunda.consulting.endpoint;

import com.camunda.consulting.model.SampleUserEntity;
import com.camunda.consulting.service.SampleUserService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/")
@Stateless
public class SampleEndpoint {
    private static final Logger log = LoggerFactory.getLogger(SampleEndpoint.class);
    @Inject
    private SampleUserService userService;

    @Inject
    RuntimeService runtimeService;

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(final @PathParam("name") String name) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("user", name);
        log.info("Create process with username {}", name);
        var process = runtimeService.startProcessInstanceByKey("Process_Sample", variables);
        log.info("process use id {}", process.getId());
        return Response.ok("Hello " + name).build();
    }

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok("ping").build();
    }
    @GET
    @Path("/hello/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<String> response = userService.getAllUsers();
        return Response.ok(response).build();
    }
}
