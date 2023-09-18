package com.camunda.consulting.springboot_integration_test_example.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

  private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

  /**
   * The customer credit are the last digits of the customer id
   */
  private Pattern pattern = Pattern.compile("(.*?)(\\d*)");

  /**
   * Deduct the credit for the given customer and the given amount
   * 
   * @param customerId
   * @param amount
   * @param credit
   * @return the open order amount
   */
  public Double deductCredit(String customerId, Double amount, Double credit) {
    // Double credit = getCustomerCredit(customerId);
    Double openAmount;
    Double deductedCredit;
    if (credit > amount) {
      deductedCredit = amount;
      openAmount = 0.0;
    } else {
      openAmount = amount - credit;
      deductedCredit = credit;
    }
    LOG.info("charged {} from the credit, open amount is {}", deductedCredit, openAmount);
    return openAmount;
  }

  /**
   * Get the current customer credit
   * 
   * @param customerId
   * @return the current credit of the given customer
   */
  public Double getCustomerCredit(String customerId) {
    Double credit = 0.0;
    Matcher matcher = pattern.matcher(customerId);

    if (matcher.matches() && matcher.group(2) != null && matcher.group(2).length() > 0) {
      credit = Double.valueOf(matcher.group(2));
    }

    LOG.info("customer {} has credit of {}", customerId, credit);

    return credit;
  }

}
