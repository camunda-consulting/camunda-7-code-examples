package org.camunda.bpm.demo.invoice.arquillian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.camunda.bpm.demo.invoice.en.InvoiceServletProcessApplication;
import org.camunda.bpm.demo.invoice.test.mock.SvnDelegateMock;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class ArquillianTestCase {
  static String process ="camunda-invoice-en";

  @Deployment  
  public static WebArchive createDeployment() {
    MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).goOffline().loadMetadataFromPom("pom.xml");
    
    return ShrinkWrap.create(WebArchive.class, "test.war")
            .addAsLibraries(resolver.artifact("org.camunda.bpm:camunda-engine-cdi").resolveAsFiles())
            // add your own classes (could be done one by one as well)
            //.addPackages(true, "org.camunda.bpm.demo.invoice")
            .addPackages(true, "org.camunda.bpm.demo.invoice.test.mock")
            .addClass(InvoiceServletProcessApplication.class)
            // add process definition
            .addAsResource(process + ".bpmn")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsResource("META-INF/processes.xml")
            // now you can add additional stuff required for your test case
            ;
  }

  @Inject
  private RuntimeService runtimeService;

  @Inject
  private TaskService taskService;
  
  @Inject
  private HistoryService historyService;
  
  @Inject 
  public SvnDelegateMock svnService;
  
  @Test
  public void testProcessPath1() throws InterruptedException {
	//assign, approve YES, prepare, SVN, done!
    HashMap<String, Object> variables = new HashMap<String, Object>();
    
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(process, variables);
    String id = processInstance.getId();
    System.out.println("Started process instance id " + id);
    
    List<String> activityIds = runtimeService.getActiveActivityIds(id);
    
    //check for assignment
    assertEquals("assignApprover", activityIds.get(0));    
    runtimeService.setVariable(id, "approver", "kermit");    
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
    
    //check for approval
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("approveInvoice", activityIds.get(0));
    runtimeService.setVariable(id, "approved", true);
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
    
    //set variable for SvnService
    svnService.reset();
    
    //check for preparation
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("prepareBankTransfer", activityIds.get(0));
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());

    //check for SVN
    assertTrue(svnService.isCalled());

    //check for end
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionId(id).count());
  }
  
  @Test
  public void testProcessPath2() throws InterruptedException {	  
	  //assign, approve NO, clarify YES, approve YES, SVN, done!
    HashMap<String, Object> variables = new HashMap<String, Object>();
    
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(process, variables);
    String id = processInstance.getId();
    System.out.println("Started process instance id " + id);
    
    List<String> activityIds = runtimeService.getActiveActivityIds(id);
    
    //check for assignment
    assertEquals("assignApprover", activityIds.get(0));    
    runtimeService.setVariable(id, "approver", "kermit");    
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
    
    //check for approval
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("approveInvoice", activityIds.get(0));
    runtimeService.setVariable(id, "approved", false);
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
    
    //check for clarification
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("reviewInvoice", activityIds.get(0));
    runtimeService.setVariable(id, "clarified", "yes");
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());

    //check for approval
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("approveInvoice", activityIds.get(0));
    runtimeService.setVariable(id, "approved", true);
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
 
    //set variable for SvnService
    svnService.reset();

    //check for preparation
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("prepareBankTransfer", activityIds.get(0));
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());

    //check for SVN
    assertTrue(svnService.isCalled());
    
    //check for end
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionId(id).count());
  }

@Test
  public void testProcessPath3() throws InterruptedException {
	svnService.reset();
	  //assign, approve NO, clarify NO, done!
    HashMap<String, Object> variables = new HashMap<String, Object>();
    
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(process, variables);
    String id = processInstance.getId();
    System.out.println("Started process instance id " + id);
    
    List<String> activityIds = runtimeService.getActiveActivityIds(id);
    
    //check for assignment
    assertEquals("assignApprover", activityIds.get(0));    
    runtimeService.setVariable(id, "approver", "kermit");    
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
    
    //check for approval
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("approveInvoice", activityIds.get(0));
    runtimeService.setVariable(id, "approved", false);
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
    
    //check for clarification
    activityIds = runtimeService.getActiveActivityIds(id);
    assertEquals("reviewInvoice", activityIds.get(0));
    runtimeService.setVariable(id, "clarified", "no");
    taskService.complete(taskService.createTaskQuery().processInstanceId(id).singleResult().getId());
    
    //set variable for SvnService
    svnService.reset();
    
    //check for SVN
    assertFalse(svnService.isCalled());

    //check for end
    assertEquals(0, historyService.createHistoricProcessInstanceQuery().processDefinitionId(id).count());
  }  
}
