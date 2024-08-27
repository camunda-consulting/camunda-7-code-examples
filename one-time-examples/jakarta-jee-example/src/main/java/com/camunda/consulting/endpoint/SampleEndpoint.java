package com.camunda.consulting.endpoint;

import com.camunda.consulting.model.SampleUserEntity;
import com.camunda.consulting.service.SampleService;
import com.camunda.consulting.service.SampleUserService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
@Stateless
public class SampleEndpoint {

    @Inject
    private SampleService service;

    @Inject
    private SampleUserService userService;

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(final @PathParam("name") String name) {
        String response = service.hello(name);
        return Response.ok(response).build();
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<SampleUserEntity> response = userService.getAllUsers();
        return Response.ok(response).build();
    }
}
