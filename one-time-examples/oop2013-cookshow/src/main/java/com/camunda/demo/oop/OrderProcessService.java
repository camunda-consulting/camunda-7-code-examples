package com.camunda.demo.oop;

import java.util.HashMap;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.cdi.BusinessProcess;

@Stateless
public class OrderProcessService {

  @Inject
  private RuntimeService runtimeService;

  @PersistenceContext
  private EntityManager entityManager;

  public long startNewOrderProcess(Order order) {
    entityManager.persist(order);

    HashMap<String, Object> variables = new HashMap<String, Object>();
    variables.put("orderId", order.getId());

    runtimeService.startProcessInstanceByKey("oop", variables);
    return order.getId();
  }

  @Inject
  private BusinessProcess businessProcess;

  @Produces
  @Named("order")
  public Order produceOrderForProcessInstance() {
    long orderId = (Long) businessProcess.getVariable("orderId");
    return findOrder(orderId);
  }

  public Order findOrder(long orderId) {
    return entityManager.find(Order.class, orderId);
  }

}
