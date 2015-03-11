package com.camunda.consulting.extendedSerialization;

import org.joda.money.Money;
import org.joda.time.LocalDate;

public class Product {
  
  private String name;
  private Money price;
  private LocalDate dateCreated;
  
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
  public LocalDate getDateCreated() {
    return dateCreated;
  }
  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }
}
