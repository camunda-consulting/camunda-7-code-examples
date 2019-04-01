package com.camunda.potsdam.UserAndTenantCreator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendEmailDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String firstName = (String)execution.getVariable("firstName");
		String secondName = (String)execution.getVariable("secondName");
		
		String userName = (String) execution.getVariable("userName");
		String password = (String) execution.getVariable("password");
		
		String email = (String) execution.getVariable("emailAddress");
		
		String emailBody = "Welcome to Camunda /n/n/n"
				+ "You can log in by going to this address: <need to do this>"
				+ "/n/n Username is "+ userName 
				+ "/n Password is "+ password 
				+ "/n/n Be aware the server will be wiped "
				+ "every night, you'll need to re-deploy your model to use it each time."
				;
		
		if(!validate(email)) {
			throw new BpmnError("invalidEmail");
		}else {
		
			System.out.println("Sending Email out to user");
			//TODO send email out to user :)
			System.out.println(emailBody
					);
		}

	}
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public static boolean validate(String emailStr) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		        return matcher.find();
		}

}
