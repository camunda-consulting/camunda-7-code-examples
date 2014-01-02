package com.camunda.demo.oop;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class NewOrderController implements Serializable {
  
  private static final long serialVersionUID = 1L;

  private Order newOrder = new Order();
  
  @Inject
  private OrderProcessService orderProcessService;

  public void submit() {
    orderProcessService.startNewOrderProcess(newOrder);
    reset();    
  }

  private void reset() {
    newOrder = new Order();
  }

  
  public Order getNewOrder() {
    return newOrder;
  }
}
