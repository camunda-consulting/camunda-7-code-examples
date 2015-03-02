package com.camunda.consulting.extendedSerializationProcess;

import org.joda.money.Money;

public class ComplexDataObject {
  
  private String name; 
  private Money price;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Money getPrice() {
    return price;
  }
  public void setPrice(Money price) {
    this.price = price;
  }

}
