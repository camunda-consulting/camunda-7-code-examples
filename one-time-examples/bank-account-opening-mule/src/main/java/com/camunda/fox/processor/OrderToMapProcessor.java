package com.camunda.fox.processor;

import java.util.HashMap;
import java.util.Map;

import com.camunda.fox.model.dto.Order;


/**
 * Processor that can be used in camel routes to transform a
 * com.camunda.fox.model.dto.Order to a java.util.Map.
 * 
 * @author Nils Preusker - n.preusker@gmail.com
 */
public class OrderToMapProcessor {

	public Map<String, Object> process(Order order) throws Exception {

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

		return map;
	}

}
