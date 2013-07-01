package com.camunda.fox;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.camunda.fox.model.dto.Address;
import com.camunda.fox.model.dto.Order;
import com.camunda.fox.model.dto.Person;

/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 *
 */
public class OrderUtil {
	public static Order createOrderObject() {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("CET"));
		Date date;
		try {
			date = formatter.parse("01-01-1970");
		} catch (ParseException e) {
			// this won't happen, unfortunately we are forced to catch the
			// and handle exception though...
			date = null;
		}
		Person person = new Person("John", "Doe", "Mr.", date, "Some Place",
				"male", "123456", "john.doe@somecompany.com");
		Address address = new Address("The Street", 1, "1234", "The City",
				"The State", "The Country");
		Order order = new Order("0001", person, address, "debit");
		return order;
	}

}
