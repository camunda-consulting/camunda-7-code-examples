package com.camunda.demo.webinar.cmmn.domain;

import java.util.UUID;

public class Application {

  private String id = UUID.randomUUID().toString();

  private String customerId;
  private String customerName;

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getId() {
    return id;
  }

}
