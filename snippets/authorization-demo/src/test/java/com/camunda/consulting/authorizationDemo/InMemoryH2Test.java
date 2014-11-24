package com.camunda.consulting.authorizationDemo;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @Rule
  public ProcessEngineRule rule = new AuthorizationDemoRule();

  public static final String PROCESS_DEFINITION_KEY = "authorization-demo";

  public static final String AUTH_DEMO_MANAGEMENT_RESOURCE_ID = PROCESS_DEFINITION_KEY + "-management";
  public static final String AUTH_DEMO_SALES_RESOURCE_ID = PROCESS_DEFINITION_KEY + "-sales";

  // enable more detailed logging
  static {
    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
    
    List<Authorization> authorizationsForProcessDefinition = 
        authorizationService()
          .createAuthorizationQuery()
          .resourceId(AUTH_DEMO_MANAGEMENT_RESOURCE_ID)
          .list();
    if (authorizationsForProcessDefinition.size() == 0) {
      // Grant management access to the process definition resource
      Resource processDefinitionResource = AuthorizationResources.resource;
      Authorization auth1 = authorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      auth1.setGroupId("management");
      auth1.setPermissions(new Permissions[] { Permissions.ACCESS, Permissions.CREATE });
      auth1.setResource(processDefinitionResource);
      auth1.setResourceId(AUTH_DEMO_MANAGEMENT_RESOURCE_ID);
      authorizationService().saveAuthorization(auth1);
      
      // Grant sales access to the process definition sales resource
      Authorization auth2 = authorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      auth2.setGroupId("sales");
      auth2.setPermissions(new Permissions[] { Permissions.ACCESS, Permissions.CREATE });
      auth2.setResource(processDefinitionResource);
      auth2.setResourceId(AUTH_DEMO_SALES_RESOURCE_ID);
      authorizationService().saveAuthorization(auth2);
      
      // Grant admin all permissions
      Authorization authAdmin = authorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      authAdmin.setUserId("admin");
      authAdmin.setPermissions(new Permissions[] { Permissions.ALL });
      authAdmin.setResource(processDefinitionResource);
      authAdmin.setResourceId("*");
      authorizationService().saveAuthorization(authAdmin);
    }
  }
  
  /**
   * Show how a user will be bound to current process engine call
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void userIdStartedAProcessInstance() {
    identityService().setAuthenticatedUserId("peter");
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    HistoricProcessInstance histPi = historyService().createHistoricProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
    assertThat(histPi.getStartUserId()).isEqualTo("peter");
    
    identityService().clearAuthentication();
    ProcessInstance piWithoutUser = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    HistoricProcessInstance histPiWithoutUser = historyService().createHistoricProcessInstanceQuery().processInstanceId(piWithoutUser.getId()).singleResult();
    assertThat(histPiWithoutUser.getStartUserId()).isNull();
  }
  
  @Test
  @Deployment(resources = "process.bpmn") 
  public void testAuthorization() {
    identityService().setAuthenticatedUserId("john");
    Resource processDefinitionResource = AuthorizationResources.resource;
    assertThat(
        authorizationService().isUserAuthorized(
            "john", 
            null, 
            Permissions.ACCESS, 
            processDefinitionResource,
            AUTH_DEMO_MANAGEMENT_RESOURCE_ID)
        ).isFalse();
    
    assertThat(
        authorizationService().isUserAuthorized(
            "peter",                                             // comes from LDAP
            Arrays.asList(new String[] {"management"}),          // come from LDAP
            Permissions.ACCESS,                                  // what to do in this case
            processDefinitionResource,                 // the resource working on (needed to get the type)
            AUTH_DEMO_MANAGEMENT_RESOURCE_ID           // the resource id build from process definition + lane 
            )
        ).isTrue();
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void completeTaskOnlyIfAuthorized() {
    identityService().setAuthentication("peter", Arrays.asList("management"));
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    Task firstTask = taskQuery().singleResult();
    taskService().claim(firstTask.getId(), "peter");
    taskService().complete(firstTask.getId());
    assertThat(pi).hasPassed("StartEvent_1", "Task_1");
  }
  
  @Test
  @Deployment(resources = "process.bpmn")
  public void completeForbidden() {
    identityService().setAuthentication("mary", Arrays.asList("accountant"));
    ProcessInstance pi = runtimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    Task firstTask = taskQuery().singleResult();
    taskService().claim(firstTask.getId(), "mary");
    try {
      taskService().complete(firstTask.getId());
    } catch (ProcessEngineException e) {
      assertThat(e).hasMessageContaining("User mary");
    }
    assertThat(pi).isWaitingAt("Task_1");
  }

}
