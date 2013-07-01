package com.camunda.fox.processor;

import java.util.HashMap;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.camunda.fox.OrderUtil;
import com.camunda.fox.model.dto.Order;

/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 *
 */
public class OrderProcessorsTest extends CamelTestSupport {

	private final MapToOrderProcessor mapToOrderProcessor = new MapToOrderProcessor();
	private final OrderToMapProcessor orderToMapProcessor = new OrderToMapProcessor();
	private final Order order = OrderUtil.createOrderObject();

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:order").process(orderToMapProcessor)
						.to("mock:map");

				from("direct:map").process(mapToOrderProcessor)
						.to("mock:order");
			}
		};
	}

	@Test
	public void testProcessor() throws InterruptedException {
		MockEndpoint orderMock = getMockEndpoint("mock:order");
		orderMock.expectedBodiesReceived(order);

		MockEndpoint mapMock = getMockEndpoint("mock:map");
		mapMock.expectedMessageCount(1);

		template.sendBody("direct:order", order);
		mapMock.assertIsSatisfied();

		template.sendBody("direct:map", mapMock.getExchanges().get(0).getIn()
				.getBody(HashMap.class));
		orderMock.assertIsSatisfied();
	}

}
