package com.camunda.consulting.handleListObjects;

import java.util.List;

public class Customer {
  
  private String name;
  private List<Order> orders;
  private double balance;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public List<Order> getOrders() {
    return orders;
  }
  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
  public double getBalance() {
    return balance;
  }
  public void setBalance(double balance) {
    this.balance = balance;
  }
  @Override
  public String toString() {
    return "Customer with " + (orders != null ? orders.size() : "null ") + " orders ";
  }

}
