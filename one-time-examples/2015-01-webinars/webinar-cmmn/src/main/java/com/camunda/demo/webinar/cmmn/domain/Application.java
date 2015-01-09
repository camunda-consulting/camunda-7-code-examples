package com.camunda.demo.webinar.cmmn.domain;

import java.util.UUID;

public class Application {

  private String id;

  private String customerId;
  private String customerName;
  
  private boolean smoker;
  
  public Application() {
    id = UUID.randomUUID().toString();
    id = id.substring(0, 8);
  }

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

  public void setId(String id) {
    this.id = id;
  }

  public boolean isSmoker() {
    return smoker;
  }

  public void setSmoker(boolean smoker) {
    this.smoker = smoker;
  }

}
