package com.camunda.demo.oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTest {
  
  private static final String PROCESS_DEFINITION_KEY = "oop-demo";

  @Deployment
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
      .goOffline()
      .loadMetadataFromPom("pom.xml");
    
    // if you experience problems with the authentication to the camunda fox
    // repository the wrong maven configuration might be used.
    // use this code to use your maven settings.xml in this case:
    // .configureFrom(".../settings.xml")

    return ShrinkWrap
            .create(WebArchive.class, "oop-demo.war")
            // prepare as process application archive for fox platform
            .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
            .addAsLibraries(resolver.artifact("org.camunda.bpm.javaee:camunda-ejb-client").resolveAsFiles())
            .addAsLibraries(resolver.artifact("commons-email:commons-email").resolveAsFiles())
            .addAsWebResource("META-INF/processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebResource("META-INF/beans.xml", "WEB-INF/classes/META-INF/beans.xml")
            .addAsWebResource("META-INF/persistence.xml", "WEB-INF/classes/META-INF/persistence.xml")
            // add your own classes (could be done one by one as well)
            .addPackages(false, "com.camunda.demo.oop", "net.webservicex") // not recursive to skip package 'nonarquillian'
            // add process definition
            .addAsResource("oop.bpmn")
    // now you can add additional stuff required for your test case
    ;
  }

  @Inject
  private ProcessEngine processEngine;
  
  @EJB
  private OrderProcessService orderProcessService;
  
  @EJB
  private SupplierAdapter supplierAdapter;

  /**
   * Tests that the process is executable and reaches its end.
   */
  @Test
  public void testProcessExecution() throws Exception {
    cleanUpRunningProcessInstances();
    
    Order order = new Order();
    
    order.setZip("70182");
    order.setEmail("bernd.ruecker@camunda.com");
    order.setAmount(300);
    
    long orderId = orderProcessService.startNewOrderProcess(order );
    
    List<Task> tasks = processEngine.getTaskService().createTaskQuery().processVariableValueEquals("orderId", orderId).list();
    assertEquals(1, tasks.size());
    
    Order persistentOrder = orderProcessService.findOrder(orderId);
    assertEquals("Stuttgart", persistentOrder.getCity());
    
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("approved", true);
    processEngine.getTaskService().complete(tasks.get(0).getId(), variables );
    
    String correlationId = (String) processEngine.getRuntimeService().getVariable(tasks.get(0).getProcessInstanceId(), SupplierAdapter.SUPPLIER_CORRELATION_ID);
    assertNotNull(correlationId);
    supplierAdapter.orderConfirmationReceived(correlationId, "Hallo OOP");

    persistentOrder = orderProcessService.findOrder(orderId);
    assertEquals(" 33 F (1 C)", persistentOrder.getWeatherInfo());

  }

  /**
   * Helper to delete all running process instances, which might disturb our Arquillian Test case
   * Better run test cases in a clean environment, but this is pretty handy for demo purposes
   */
  private void cleanUpRunningProcessInstances() {
    List<ProcessInstance> runningInstances = processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).list();
    for (ProcessInstance processInstance : runningInstances) {
      processEngine.getRuntimeService().deleteProcessInstance(processInstance.getId(), "deleted to have a clean environment for Arquillian");
    }
  }  
}
