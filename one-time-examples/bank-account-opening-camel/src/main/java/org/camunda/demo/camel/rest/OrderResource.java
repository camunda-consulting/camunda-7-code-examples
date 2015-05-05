package org.camunda.demo.camel.rest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.camunda.demo.camel.dto.Order;


@Path("orders")
public interface OrderResource {

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Order createOrder(Order order);
	
	@GET
	@Path("/{id}")
	public Order readOrder(@PathParam("id") String id);
}
