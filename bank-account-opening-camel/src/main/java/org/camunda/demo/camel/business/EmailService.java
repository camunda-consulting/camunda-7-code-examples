package org.camunda.demo.camel.business;

import java.util.Map;

import javax.inject.Named;

@Named
public class EmailService {

	public void notifyUser(Map<String, Object> variables) {
		
		// TODO
		
		System.out.println("emailService notifyUser was called");
		
	}
	
}
