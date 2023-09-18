package com.camunda.consulting.springboot_integration_test_example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreditCardService {

  private static final Logger LOG = LoggerFactory.getLogger(CreditCardService.class);

  public void chargeAmount(String cardNumber, String cvc, String expiryDate, Double amount) {
    LOG.info("charging card {} that expires on {} and has cvc {} with amount of {}", 
        cardNumber,
        expiryDate, 
        cvc, 
        amount);
    if (expiryDate.length() > 5) {
      String message = "Expiry date " + expiryDate + " is invalid";
      LOG.info("Error message: {}", message);
      throw new CreditCardExpiredException(message);
    }

    LOG.info("payment completed");
  }
}
