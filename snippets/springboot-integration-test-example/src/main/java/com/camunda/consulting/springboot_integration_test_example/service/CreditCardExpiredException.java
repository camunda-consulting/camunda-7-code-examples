package com.camunda.consulting.springboot_integration_test_example.service;

public class CreditCardExpiredException extends RuntimeException {

  public CreditCardExpiredException(String message) {
    super(message);
  }

}
