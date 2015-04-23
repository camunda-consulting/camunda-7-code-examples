package com.camunda.demo.webinar.testing.scope1;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.execute;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.job;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;

import java.sql.SQLException;

import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestCase {

  /**
   * The processEngineRule will control the lifecycle of the process engine. The default creates a process engine
   * by using the camunda.cfg.xml in the test classpath.
   */
  @Rule
  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

  @Deployment(resources = "process.bpmn")
  public void testDeployment() {
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testPathB() {    
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("webinar-testing");

    // assert that the User Task was created and complete it directly to advance the process flow
    assertThat(processInstance).isStarted() //
        .isWaitingAt("UserTaskA") //
        .task() //
        .hasCandidateGroup("management");
    complete(task(), Variables.createVariables().putValue("path", "B"));
    
    // or using the Java API
    // Task task = processEngineRule.getTaskService().createTaskQuery().taskAssignee("demo").singleResult();
    // processEngine().getTaskService().complete(task().getId(), variables);
    
    
    // assert that we are waiting in the Timer
    assertThat(processInstance)    
      .job("TimerEvent");
    
    // and trigger the timer right away via API (as we do not want to wait 2 hours ;-))
    execute(job());
    
    // or using the Java API
    //Job job = processEngineRule.getManagementService().createJobQuery().timers().singleResult();
    //processEngine().getManagementService().executeJob(job.getId());

    // assert that process instances has ended and all activities where passed (including Service Task B)
    assertThat(processInstance) //
        .isEnded() //
        .hasPassed("StartEvent", "UserTaskA", "TimerEvent", "ExclusiveGateway", "ServiceTaskB", "EndEventB");
    
    // visualize path in process instance
    ProcessTestCoverage.calculate(processInstance.getId(), processEngineRule.getProcessEngine());    
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testPathC() {
    Mocks.register("customerServiceAdapter", new JavaDelegate() {// simply ignore delegate call for now
      public void execute(DelegateExecution execution) throws Exception {}
    });
    
    // THis time we skip the comments - you can see how easily readable process tests can be!
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("webinar-testing");

    assertThat(processInstance).isWaitingAt("UserTaskA").task().hasCandidateGroup("management");
    complete(task(), Variables.createVariables().putValue("path", "C"));
    execute(job());
    assertThat(processInstance).isEnded() //
        .hasPassed("StartEvent", "UserTaskA", "TimerEvent", "ExclusiveGateway", "ServiceTaskC", "EndEventC");
    
    // visualize path in process instance
    ProcessTestCoverage.calculate(processInstance.getId(), processEngineRule.getProcessEngine());    
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void testShowcaseDatabase() throws SQLException {
    org.h2.tools.Server.createWebServer("-web").start();
    // now you have the webtool ready at http://localhost:8082/
    
    //org.h2.tools.Server.createTcpServer( "-tcp", "-tcpAllowOthers").start();
    // now we can inspect the database via JDBC, URL:  jdbc:h2:tcp://localhost:9092/mem:camunda
    
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("webinar-testing");
    assertThat(processInstance).isActive();
    System.out.println("This is a great place for a breakpoint!");
    
    complete(task(), Variables.createVariables().putValue("path", "B"));
    System.out.println("This is a great place for a breakpoint!");
    
    execute(job());
    System.out.println("This is a great place for a breakpoint!");
    
    assertThat(processInstance).isEnded();
  }

  @After
  public void tearDown() throws Exception {
    // visualize coverage of test suite
    ProcessTestCoverage.calculate(processEngineRule.getProcessEngine());
  }

}