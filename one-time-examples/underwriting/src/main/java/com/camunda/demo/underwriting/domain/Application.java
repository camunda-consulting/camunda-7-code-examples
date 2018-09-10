package com.camunda.demo.underwriting.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Application {

  private String id;

  private String customerId;
  private String customerName;
  
  private int age;
  private String risk;
  private String type;
  
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getRisk() {
    return risk;
  }

  public void setRisk(String risk) {
    this.risk = risk;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
