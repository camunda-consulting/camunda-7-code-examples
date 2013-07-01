package com.camunda.fox.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.camunda.fox.model.dto.Address;
import com.camunda.fox.model.dto.Order;
import com.camunda.fox.model.dto.Person;

/**
 * Processor that can be used in camel routes to transform a java.util.Map to a
 * com.camunda.fox.model.dto.Order.
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 */
public class MapToOrderProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = exchange.getIn().getBody(HashMap.class);

		Person person = new Person((String) map.get("firstname"),
				(String) map.get("lastname"), (String) map.get("title"),
				(Date) map.get("dateofbirth"),
				(String) map.get("placeofbirth"), (String) map.get("gender"),
				(String) map.get("phonenumber"), (String) map.get("email"));

		Address address = new Address((String) map.get("street"),
				(Integer) map.get("number"), (String) map.get("zipcode"),
				(String) map.get("city"), (String) map.get("state"),
				(String) map.get("country"));

		Order order = new Order((String) map.get("ordernumber"), person,
				address, (String) map.get("accounttype"));

		exchange.getIn().setBody(order);
	}
}
