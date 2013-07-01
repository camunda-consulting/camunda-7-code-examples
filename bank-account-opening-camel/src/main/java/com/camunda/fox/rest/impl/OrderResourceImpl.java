package com.camunda.fox.rest.impl;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.camunda.fox.jms.OrderJmsService;
import com.camunda.fox.model.dto.Order;
import com.camunda.fox.rest.OrderResource;

/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 *
 */
public class OrderResourceImpl implements OrderResource {

	private final static Logger log = Logger.getLogger(OrderResourceImpl.class.getCanonicalName());

	@Inject
	OrderJmsService orderJmsService;

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
