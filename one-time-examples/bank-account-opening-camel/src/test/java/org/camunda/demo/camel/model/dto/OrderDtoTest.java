package org.camunda.demo.camel.model.dto;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.camunda.demo.camel.OrderUtil;
import org.camunda.demo.camel.dto.Order;
import org.junit.Test;


/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 *
 */
public class OrderDtoTest extends CamelTestSupport {

	private static final Order order = OrderUtil.createOrderObject();

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				// route to test marshaling (transforming order to XML)
				from("direct:order").marshal(
						new JaxbDataFormat(Order.class.getPackage().getName()))
						.to("mock:xml");

				// route to test un-marshaling (transforming XML to order)
				from("direct:xml").unmarshal(
						new JaxbDataFormat(Order.class.getPackage().getName()))
						.to("mock:order");
			}
		};
	}

	@Test
	public void testMarshal() throws Exception {
		MockEndpoint xmlMock = getMockEndpoint("mock:xml");
		xmlMock.expectedMessageCount(1);
		template.sendBody("direct:order", order);

		xmlMock.assertIsSatisfied();
		String xml = xmlMock.getExchanges().get(0).getIn()
				.getBody(String.class);

		MockEndpoint orderMock = getMockEndpoint("mock:order");
		orderMock.expectedMessageCount(1);
		orderMock.expectedBodiesReceived(order);

		template.sendBody("direct:xml", xml);

		orderMock.assertIsSatisfied();
	}

}
