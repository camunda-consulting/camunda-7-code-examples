package com.camunda.demo.gateway.or.split.items;

import java.util.ArrayList;
import java.util.List;

public class Order {

  private List<Item> items = new ArrayList<Item>();

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
  
  public Order addItem(Item item) {
    items.add(item);
    return this;
  }
}
