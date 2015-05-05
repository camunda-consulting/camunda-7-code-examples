package org.camunda.bpm.demo.orderconfirmation.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.camunda.bpm.demo.orderconfirmation.model.Order;
import org.camunda.bpm.demo.orderconfirmation.model.TransientOrder;

@Named
@SessionScoped
public class NewOrderController implements Serializable  {

  private static final long serialVersionUID = 1L;

  @EJB
  private OrderBean orderBean;

  private Order newOrder;

  @Produces
  @Named("newOrder")
  @TransientOrder
  public Order getNewOrder() {
    if (newOrder == null) {
      newOrder = new Order();
    }
    return newOrder;
  }

  public void save() {
    orderBean.saveNewOrder(newOrder);
    newOrder = new Order();
  }
  


}
