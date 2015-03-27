package com.camunda.consulting.handleListObjects;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.withVariables;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.spring.impl.test.SpringProcessEngineTestCase;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.camunda.consulting.handleListObjects.Customer;
import com.camunda.consulting.handleListObjects.Order;
import com.camunda.consulting.handleListObjects.OrderItem;

@ContextConfiguration("SpringProcessTest-context.xml")
public class SpringProcessTest extends SpringProcessEngineTestCase {
  
  private static final String PROCESS_DEFINITION_KEY = "evaluate-list-objects";

  @Test
  @Deployment(resources = "process.bpmn") 
  public void testGetOrderItemPrice() {
    Customer customer = createCustomerWithOrders();
    
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, withVariables("customer", customer, "completePrice", 0.0));
    
    assertThat(processInstance).isEnded();
    HistoricVariableInstance var = historyService.createHistoricVariableInstanceQuery().variableName("completePrice").singleResult();
    assertThat(var.getValue()).isEqualTo(5.0);
  }

  @Test
  @Deployment(resources = "process_multi_instance.bpmn")
  public void testGetOrderItemPrices() {
    Customer customer = createCustomerWithOrders();
    
    ProcessInstance pi = runtimeService.startProcessInstanceByKey("process_multi_instance", withVariables("customer", customer, "completePrice", 0.0));
    assertThat(pi).isEnded();
    HistoricVariableInstance var = historyService.createHistoricVariableInstanceQuery().variableName("completePrice").singleResult();
    assertThat(var.getValue()).isEqualTo(18.6);
  }

  protected Customer createCustomerWithOrders() {
    OrderItem item1 = new OrderItem(1, 2.5, 2);
    OrderItem item2 = new OrderItem(2, 3.4, 4);
    List<OrderItem> orderItems = new ArrayList<OrderItem>();
    orderItems.add(item1);
    orderItems.add(item2);
    Order order1 = new Order();
    order1.setItems(orderItems);
    order1.setShipping(4.5);
    List<Order> orders = new ArrayList<Order>();
    orders.add(order1);
    Customer customer = new Customer();
    customer.setOrders(orders);
    return customer;
  }
  

}
