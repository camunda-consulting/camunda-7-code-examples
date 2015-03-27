package com.camunda.consulting.handleListObjects;

import java.util.logging.Logger;

public class OrderCalculator {
  
  private static final Logger log = Logger.getLogger(OrderCalculator.class.getName());
  
  public double evaluate(Object orderItemObject, Object completePrice) {
    OrderItem orderItem = (OrderItem) orderItemObject;
    double completePriceDouble = (Double) completePrice;
    double itemPrice = orderItem.getProductPrice() * orderItem.getQuantity();
    log.info("Item price: " + itemPrice);
    return itemPrice + completePriceDouble;
  }

}
