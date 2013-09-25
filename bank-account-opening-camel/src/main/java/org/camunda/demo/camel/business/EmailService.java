package org.camunda.demo.camel.business;

import java.util.Map;

import javax.inject.Named;

@Named
public class EmailService {

  public void notifyUser(Map<String, Object> variables) {
    if (((String) variables.get("city")).equals("fail")) {
      throw new RuntimeException("city 'fail' not accepted");
    }

    // TODO

    System.out.println("emailService was called to notify user");

  }

}
