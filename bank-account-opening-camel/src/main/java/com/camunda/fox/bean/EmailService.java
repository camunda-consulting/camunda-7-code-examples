package com.camunda.fox.bean;

import java.util.Map;

import javax.inject.Named;

/**
 * 
 * @author Nils Preusker - nils.preusker@camunda.com
 *
 */
@Named
public class EmailService {

	public void notifyUser(Map<String, Object> variables) {
		
		// TODO
		
		System.out.println("emailService notifyUser was called");
		
	}
	
}
