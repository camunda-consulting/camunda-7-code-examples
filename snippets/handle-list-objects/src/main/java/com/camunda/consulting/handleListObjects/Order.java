package com.camunda.consulting.handleListObjects;

import java.util.List;

public class Order {
  
  private double shipping;
  private double price;
  private List<OrderItem> items;
  public double getShipping() {
    return shipping;
  }
  public void setShipping(double shipping) {
    this.shipping = shipping;
  }
  public double getPrice() {
    return price;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  public List<OrderItem> getItems() {
    return items;
  }
  public void setItems(List<OrderItem> items) {
    this.items = items;
  }
  @Override
  public String toString() {
    return "Order with " + (items != null ? items.size() : " null ") + " items ";
  }

}
