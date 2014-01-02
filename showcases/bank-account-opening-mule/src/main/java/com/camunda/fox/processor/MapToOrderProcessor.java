package com.camunda.fox.processor;

import java.util.Date;
import java.util.Map;

import com.camunda.fox.model.dto.Order;
import com.camunda.fox.model.dto.Person;
import com.camunda.fox.model.dto.Address;


/**
 * Processor that can be used in camel routes to transform a java.util.Map to a
 * com.camunda.fox.model.dto.Order.
 * 
 * @author Nils Preusker - n.preusker@gmail.com
 */
public class MapToOrderProcessor {

	public Order process(Map<String, Object> map) throws Exception {

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

		return order;
	}
}
