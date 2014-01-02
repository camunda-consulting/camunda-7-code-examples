package com.camunda.fox.rest;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.camunda.fox.model.dto.Order;

@Path("orders")
public interface OrderResource {

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Order createOrder(Order order);
	
}
