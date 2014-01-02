package org.camunda.demo.custom.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.demo.custom.query.Customer;
import org.camunda.demo.custom.query.CustomerService;
import org.camunda.demo.custom.query.TaskDTO;
import org.camunda.demo.custom.query.TasklistService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ArquillianTestCase {

  @Deployment  
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
      .goOffline()
      .loadMetadataFromPom("pom.xml");
     
    return ShrinkWrap.create(WebArchive.class, "custom-queries.war")            
            .addAsLibraries(resolver.artifact("org.camunda.bpm.javaee:camunda-ejb-client").resolveAsFiles())
            .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
            .addAsWebResource("test-processes.xml", "WEB-INF/classes/META-INF/processes.xml")
            .addAsWebResource("test-persistence.xml", "WEB-INF/classes/META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            
            .addPackages(false, "org.camunda.demo.custom.query")
            
            .addAsResource("oneTaskProcess.bpmn")
            .addAsResource("customTaskMappings.xml")
            .addAsResource("mappings.xml");    
  }

  @After
  public void after() {
    customerService.removeAll();
  }
  
//  @Inject
//  private ProcessEngine processEngine;

  @Inject
  private RuntimeService runtimeService;

//  @Inject
//  private TaskService taskService;
//  
//  @Inject
//  private HistoryService historyService;
  
  @Inject
  private TasklistService tasklistService;

  @Inject
  private CustomerService customerService;

  @Test
  public void test() throws Exception {
    // create unique city
    String regionUnderTest = "Berlin." + System.currentTimeMillis();

    Customer customer = new Customer();
    customer.setName("camunda");
    customer.setRegion(regionUnderTest);
    customerService.persist(customer);
    long customerId = customer.getId();
    
    HashMap<String, Object> variables = new HashMap<String, Object>();
    variables.put("customerId", customerId);
    variables.put("someContent", "0815");
    
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("oneTaskProcess", variables);
    
    System.out.println("Started process instance id " + processInstance.getId());
    
    assertThat(runtimeService.getActiveActivityIds(processInstance.getId()), hasItem("theTask"));
    
    List<TaskDTO> tasksForRegion = tasklistService.getTasksForRegion("kermit", regionUnderTest);
    assertEquals(1, tasksForRegion.size());
    TaskDTO task = tasksForRegion.get(0);

    assertNotNull(task.getCustomer());
    assertEquals(regionUnderTest, task.getCustomer().getRegion());
    assertEquals("camunda", task.getCustomer().getName());
    assertEquals(customerId, task.getCustomer().getId());
    
    assertEquals(2, task.getVariables().size());
//    assertThat(task.getVariables(), hasItem("theTask"));
  }
}
