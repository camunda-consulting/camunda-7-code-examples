package org.camunda.bpm.demo.orderconfirmation.bean;

import javax.inject.Named;

import org.camunda.bpm.demo.orderconfirmation.model.Order;

@Named
public class OrderList extends AbstractEntityList<Order> {

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<Order> getEntityType() {
    return Order.class;
  }

}
