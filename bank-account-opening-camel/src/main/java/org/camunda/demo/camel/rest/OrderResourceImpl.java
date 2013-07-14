package org.camunda.demo.camel.rest;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.camunda.demo.camel.dto.Order;
import org.camunda.demo.camel.jms.OrderJmsService;


public class OrderResourceImpl implements OrderResource {

	private final static Logger log = Logger.getLogger(OrderResourceImpl.class.getCanonicalName());

	@Inject
	private OrderJmsService orderJmsService;

	@Override
	public Order createOrder(Order order) {
		log.info("=======================");
		log.info("Received order: '" + order + "'");
		log.info("=======================");
		orderJmsService.deliverMessageToOrderQueue(order);
		return order;
	}

	@Override
	public Order readOrder(String id) {
		// TODO
		return null;
	}

}
