package com.camunda.demo.outerspace.wjax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.camunda.demo.outerspace.wjax.controller.TaskListController;
import com.camunda.demo.outerspace.wjax.controller.performance.OrderProcessDTO;
import com.camunda.demo.outerspace.wjax.entity.Article;
import com.camunda.demo.outerspace.wjax.entity.Order;
import com.camunda.demo.outerspace.wjax.entity.OrderItem;
import com.camunda.demo.outerspace.wjax.service.OrderService;
import com.camunda.demo.outerspace.wjax.service.StockService;

@RunWith(Arquillian.class)
public class OrderProcessTest {

  @PersistenceContext
  private EntityManager entityManager;

  @Inject
  private ProcessEngine processEngine;

  @Inject
  private OrderService orderService;

  @Inject
  private StockService stockService;

  @Inject
  private TaskListController taskListController;

  @Deployment
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml").goOffline();

    return ShrinkWrap
            .create(WebArchive.class, "wjax2012.war")
            // prepare as process application archive for fox platform
            .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
            .addAsLibraries(resolver.artifact("org.camunda.bpm.javaee:camunda-ejb-client").resolveAsFiles())
            .addAsWebResource("META-INF/test-persistence.xml", "WEB-INF/classes/META-INF/persistence.xml")
            .addAsWebResource("META-INF/test-processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsResource("customMappings.xml", "customMappings.xml")
            .addAsResource("customQueries.xml", "customQueries.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(true, "com.camunda.demo.outerspace.wjax")
            // add process definition
            .addAsResource("Bestellprozess.bpmn").addAsResource("Lieferprozess.bpmn")
    // now you can add additional stuff required for your test case
    ;
  }

  /**
   * Tests that a user cannot claim or complete a task if he completed the first
   * task
   */
  @Test
  public void testOrderOnStockViaProcessEngineApi() throws Exception {
  	Order order = createExampleOrder(true, "bag1");
  	orderService.persist(order);

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("orderId", order.getId());
    ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("wjax2012-bestellprozess", variables);

    assertNotNull(pi);
    assertNotNull(pi.getId());

    // Instance has ended
    HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery() //
            .processInstanceId(pi.getId()) //
            .singleResult();
    assertNotNull(historicProcessInstance.getEndTime());

    // Was removed from runtime database
    assertEquals(0, processEngine.getRuntimeService().createProcessInstanceQuery() //
            .processInstanceId(pi.getId()) //
            .count());
  }

  @Test
  public void testOrderWithItemsOnStock() throws Exception {
    long orderId = orderService.placeOrder(createExampleOrder(true, "bag1"));

    // Instance has ended
//    assertEquals(1, processEngine.getHistoryService().createHistoricProcessInstanceQuery() //
//            .variableValueEquals("orderId", orderId) //
//            .finished() //
//            .count());

    List<OrderProcessDTO> orders = taskListController.getOrders();
    assertEquals(1, orders.size());
    assertEquals(orderId, orders.get(0).getOrderId());
    assertEquals("auftrag-geliefert", orders.get(0).getLatestActivityId());
  }

  @Test
  public void testOrderWithItemsNotOnStock() throws Exception {
    long orderId = orderService.placeOrder(createExampleOrder(false, "bag1"));
    // Instance has NOT ended
    assertEquals(0, processEngine.getHistoryService().createHistoricProcessInstanceQuery() //
            .variableValueEquals("orderId", orderId) //
            .finished() //
            .count());

    // check the current state of the process instance
    ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery().variableValueEquals("orderId", orderId).singleResult();
    List<String> activeActivityIds = processEngine.getRuntimeService().getActiveActivityIds(pi.getId());
    assertEquals(1, activeActivityIds.size());
    // it is in task "kunde-umberaten"
    assertEquals("kunde-umberaten", activeActivityIds.get(0));

    // which is a user task (on the task list)
    List<Task> tasks = processEngine.getTaskService().createTaskQuery() //
            .taskAssignee("demo") //
            // in test cases this may make sense:
            .processVariableValueEquals("orderId", orderId) //
            .list();

    assertEquals(1, tasks.size());
    Task task = tasks.get(0);
    assertEquals(orderId, processEngine.getRuntimeService().getVariable(task.getProcessInstanceId(), "orderId"));

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("umberatung", "erfolgreich");
    processEngine.getTaskService().complete(task.getId(), variables);

    HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
    assertEquals("auftrag-geliefert", historicProcessInstance.getEndActivityId());
  }

  private Order createExampleOrder(boolean onStock, String articleId) throws Exception {
    Order order = new Order();
    OrderItem item = new OrderItem();
    if (onStock) {
      item.setAmount(1);
    } else {
      item.setAmount(20);
    }
    item.setArticle(findOrCreate(articleId));
    order.getItems().add(item);
    return order;
  }

  private Article findOrCreate(String articleId) throws Exception {
    Article a = entityManager.find(Article.class, articleId);
    if (a == null) {
      a = new Article();
      a.setId(articleId);
      a.setDescription("Awesome bag");
      a.setPriceInCents(999);
      stockService.persist(a);

      stockService.setAmountOnStock(articleId, 5);
    }
    return a;
  }

}
