package com.camunda.fox.processor;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.camunda.fox.model.dto.Order;

/**
 * Processor that can be used in camel routes to transform a
 * com.camunda.fox.model.dto.Order to a java.util.Map.
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 */
public class OrderToMapProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Order order = exchange.getIn().getBody(Order.class);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounttype", order.getAccounttype());
		map.put("city", order.getAddress().getCity());
		map.put("country", order.getAddress().getCountry());
		map.put("number", order.getAddress().getNumber());
		map.put("state", order.getAddress().getState());
		map.put("street", order.getAddress().getStreet());
		map.put("zipcode", order.getAddress().getZipcode());
		map.put("ordernumber", order.getOrdernumber());
		map.put("dateofbirth", order.getPerson().getDateofbirth());
		map.put("email", order.getPerson().getEmail());
		map.put("firstname", order.getPerson().getFirstname());
		map.put("gender", order.getPerson().getGender());
		map.put("lastname", order.getPerson().getLastname());
		map.put("phonenumber", order.getPerson().getPhonenumber());
		map.put("placeofbirth", order.getPerson().getPlaceofbirth());
		map.put("title", order.getPerson().getTitle());

		exchange.getIn().setBody(map);
	}

}
