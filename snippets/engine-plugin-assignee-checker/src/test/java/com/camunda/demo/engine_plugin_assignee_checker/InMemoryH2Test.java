package com.camunda.demo.engine_plugin_assignee_checker;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 * 
 * @author Falko Menge (Camunda)
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "engine-plugin-assignee-checker";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {
    Group group = identityService().newGroup("management");
    User  user  = identityService().newUser("alice");
    User  user2  = identityService().newUser("bob");
    identityService().saveGroup(group);
    identityService().saveUser(user);
    identityService().saveUser(user2);
    identityService().createMembership("alice", "management");
    
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertThat(processInstance).task("Task_DoSomething");
    
    claim(task(), "alice"); // is in candidate group
    unclaim(task());
    claim(task(), "bob"); // is a candidate user
    
    complete(task());
    
    assertThat(processInstance).isEnded();
  }

  @Test(expected = ProcessEngineException.class)
  @Deployment(resources = "process.bpmn")
  public void testAssigneeUnknownException() {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertThat(processInstance).task("Task_DoSomething");
    
    claim(task(), "malory");
  }

  @Test(expected = ProcessEngineException.class)
  @Deployment(resources = "process.bpmn")
  public void testAssigneeNotInCandidatesException() {
    User user = identityService().newUser("malory");
    identityService().saveUser(user);

    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

    assertThat(processInstance).task("Task_DoSomething");
    
    claim(task(), "malory");
  }

}
