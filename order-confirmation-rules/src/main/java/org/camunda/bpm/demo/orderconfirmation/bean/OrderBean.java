package org.camunda.bpm.demo.orderconfirmation.bean;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.annotation.StartProcess;
import org.drools.runtime.StatefulKnowledgeSession;

import org.camunda.bpm.demo.orderconfirmation.model.Order;
import org.camunda.bpm.demo.orderconfirmation.model.PersistentOrder;
import org.camunda.bpm.demo.orderconfirmation.model.TransientOrder;

@Named
@Stateless // we need an EJB to get a proper transaction
public class OrderBean {

  @PersistenceContext
  private EntityManager entityManager;

  @Inject
  private DroolsRulebaseBean droolsRulebaseBean;

  @Inject
  private BusinessProcess businessProcess;

  private Order order;

  @Produces
  @Named("newOrder")
  @TransientOrder
  public Order getNewOrder() {
    if (order == null) {
      order = new Order();
    }
    return order;
  }

  /**
   * Alternative to do a
   * "businessProcess.startProcessByKey('OrderConfirmation')" in JSF
   */
  @StartProcess("OrderConfirmation")
  public void save() {
    saveOrder();
  }

  /**
   * This code will be replaced by fox / Java EE integration soon :-)
   */
  public void saveOrder() {
    entityManager.persist(order.getCustomer());
    entityManager.persist(order);

    // flush to get the id generated
    entityManager.flush();

    businessProcess.setVariable("orderId", order.getId());
    order = null;
  }
  
  public void deleteOrder() {
    entityManager.remove( getOrderEntity() );
  }  

  public void applyRules() {
    StatefulKnowledgeSession workingMemory = droolsRulebaseBean.createNewWorkingMemory();
    workingMemory.insert(getOrderEntity());
    workingMemory.fireAllRules();
    workingMemory.dispose();
  }

  @Produces
  @Named("orderEntity")
  @PersistentOrder
  public Order getOrderEntity() {
    Order o = entityManager.find(Order.class, businessProcess.getVariable("orderId"));
    return o;
  }
  
  public void sendEmail() {
    System.out.println("Now we would send an email...");
    /**
<extensionElements>
        <activiti:field name="to" expression="#{orderEntity.customer.email}"/>
        <activiti:field name="from" expression="info@camunda.com"/>
        <activiti:field name="subject" expression="Your order was approved"/>
        <activiti:field name="text">
          <activiti:expression><![CDATA[Dear #{orderEntity.customer.company}.

Your order #{orderEntity.id} was approved with discount #{orderEntity.discount} because of rule '#{orderEntity.discountReason}'.

Thanks and cheers
your camunda fox demo application]]></activiti:expression>
        </activiti:field>
      </extensionElements>
     */
  }

}
