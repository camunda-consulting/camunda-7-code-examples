package org.camunda.bpm.example.invoice;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ACCESS;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;
import static org.camunda.bpm.engine.authorization.Permissions.READ;
import static org.camunda.bpm.engine.authorization.Permissions.UPDATE;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;
import static org.camunda.bpm.engine.authorization.Resources.FILTER;
import static org.camunda.bpm.engine.authorization.Resources.TASK;
import static org.camunda.bpm.engine.authorization.Resources.USER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.filter.Filter;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;
import org.camunda.bpm.engine.task.TaskQuery;

/**
 * Creates demo credentials to be used in the invoice showcase.
 *
 * @author drobisch
 */
public class DemoDataGenerator {

    private final static Logger LOGGER = Logger.getLogger(DemoDataGenerator.class.getName());

    public void createUsers(ProcessEngine engine) {

      final IdentityService identityService = engine.getIdentityService();

      if(identityService.isReadOnly()) {
        LOGGER.info("Identity service provider is Read Only, not creating any demo users.");
        return;
      }

      User singleResult = identityService.createUserQuery().userId("demo").singleResult();
      if (singleResult != null) {
        return;
      }

      LOGGER.info("Generating demo data for invoice showcase");

      User user = identityService.newUser("demo");
      user.setFirstName("Demo");
      user.setLastName("Demo");
      user.setPassword("demo");
      user.setEmail("demo@camunda.org");
      identityService.saveUser(user);

      User user1 = identityService.newUser("user1");
      user1.setFirstName("John");
      user1.setLastName("Doe");
      user1.setPassword("user1");
      user1.setEmail("john@camunda.org");

      identityService.saveUser(user1);

      User user2 = identityService.newUser("user2");
      user2.setFirstName("Mary");
      user2.setLastName("Anne");
      user2.setPassword("user2");
      user2.setEmail("mary@camunda.org");

      identityService.saveUser(user2);

      User user3 = identityService.newUser("user3");
      user3.setFirstName("Peter");
      user3.setLastName("Meter");
      user3.setPassword("user3");
      user3.setEmail("peter@camunda.org");
      
      identityService.saveUser(user3);

      User user4 = identityService.newUser("user4");
      user4.setFirstName("Sandra");
      user4.setLastName("Schmidt");
      user4.setPassword("user4");
      user4.setEmail("sandra@camunda.org");
      
      identityService.saveUser(user4);
      
      User user5 = identityService.newUser("user5");
      user5.setFirstName("David");
      user5.setLastName("Sander");
      user5.setPassword("user5");
      user5.setEmail("david@camunda.org");
      
      identityService.saveUser(user5);
      
      User user6 = identityService.newUser("user6");
      user6.setFirstName("Lara");
      user6.setLastName("Meier");
      user6.setPassword("user6");
      user6.setEmail("lara@camunda.org");
      
      identityService.saveUser(user6);
      
      User user7 = identityService.newUser("user7");
      user7.setFirstName("Robert");
      user7.setLastName("Huber");
      user7.setPassword("user7");
      user7.setEmail("robert@camunda.org");
      
      identityService.saveUser(user7);
      
      User user8 = identityService.newUser("user8");
      user8.setFirstName("Alexander");
      user8.setLastName("Müller");
      user8.setPassword("user8");
      user8.setEmail("alexander@camunda.org");
      
      identityService.saveUser(user8);
      
      User user9 = identityService.newUser("user9");
      user9.setFirstName("Alexandra");
      user9.setLastName("Meiners");
      user9.setPassword("user9");
      user9.setEmail("alexandra@camunda.org");
      
      identityService.saveUser(user9);
      
      User user10 = identityService.newUser("user10");
      user10.setFirstName("Lars");
      user10.setLastName("Schulz");
      user10.setPassword("user10");
      user10.setEmail("lars@camunda.org");
      
      identityService.saveUser(user10);

      Group salesGroup = identityService.newGroup("sales");
      salesGroup.setName("Sales");
      salesGroup.setType("WORKFLOW");
      identityService.saveGroup(salesGroup);

      Group accountingGroup = identityService.newGroup("accounting");
      accountingGroup.setName("Accounting");
      accountingGroup.setType("WORKFLOW");
      identityService.saveGroup(accountingGroup);

      Group managementGroup = identityService.newGroup("management");
      managementGroup.setName("Management");
      managementGroup.setType("WORKFLOW");
      identityService.saveGroup(managementGroup);

      final AuthorizationService authorizationService = engine.getAuthorizationService();

      // create group
      if(identityService.createGroupQuery().groupId(Groups.CAMUNDA_ADMIN).count() == 0) {
        Group camundaAdminGroup = identityService.newGroup(Groups.CAMUNDA_ADMIN);
        camundaAdminGroup.setName("camunda BPM Administrators");
        camundaAdminGroup.setType(Groups.GROUP_TYPE_SYSTEM);
        identityService.saveGroup(camundaAdminGroup);
      }

      // create ADMIN authorizations on all built-in resources
      for (Resource resource : Resources.values()) {
        if(authorizationService.createAuthorizationQuery().groupIdIn(Groups.CAMUNDA_ADMIN).resourceType(resource).resourceId(ANY).count() == 0) {
          AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
          userAdminAuth.setGroupId(Groups.CAMUNDA_ADMIN);
          userAdminAuth.setResource(resource);
          userAdminAuth.setResourceId(ANY);
          userAdminAuth.addPermission(ALL);
          authorizationService.saveAuthorization(userAdminAuth);
        }
      }

      identityService.createMembership("demo", "sales");
      identityService.createMembership("demo", "accounting");
      identityService.createMembership("demo", "management");      
      identityService.createMembership("demo", "camunda-admin");
      
      identityService.createMembership("user1", "sales");
      identityService.createMembership("user2", "accounting"); 
      identityService.createMembership("user3", "management");
      identityService.createMembership("user4", "sales");
      identityService.createMembership("user5", "accounting"); 
      identityService.createMembership("user6", "sales");
      identityService.createMembership("user7", "accounting");
      identityService.createMembership("user8", "sales");
      identityService.createMembership("user9", "management");
      identityService.createMembership("user10", "sales");
   

      // authorize groups for tasklist only:

      Authorization salesTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesTasklistAuth.setGroupId("sales");
      salesTasklistAuth.addPermission(ACCESS);
      salesTasklistAuth.setResourceId("tasklist");
      salesTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(salesTasklistAuth);

      Authorization salesReadProcessDefinition = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesReadProcessDefinition.setGroupId("sales");
      salesReadProcessDefinition.addPermission(Permissions.READ);
      salesReadProcessDefinition.addPermission(Permissions.READ_HISTORY);
      salesReadProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
      // restrict to invoice process definition only
      salesReadProcessDefinition.setResourceId("invoice");
      authorizationService.saveAuthorization(salesReadProcessDefinition);

      Authorization accountingTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      accountingTasklistAuth.setGroupId("accounting");
      accountingTasklistAuth.addPermission(ACCESS);
      accountingTasklistAuth.setResourceId("tasklist");
      accountingTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(accountingTasklistAuth);

      Authorization accountingReadProcessDefinition = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      accountingReadProcessDefinition.setGroupId("accounting");
      accountingReadProcessDefinition.addPermission(Permissions.READ);
      accountingReadProcessDefinition.addPermission(Permissions.READ_HISTORY);
      accountingReadProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
      // restrict to invoice process definition only
      accountingReadProcessDefinition.setResourceId("invoice");
      authorizationService.saveAuthorization(accountingReadProcessDefinition);

      Authorization managementTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      managementTasklistAuth.setGroupId("management");
      managementTasklistAuth.addPermission(ACCESS);
      managementTasklistAuth.setResourceId("tasklist");
      managementTasklistAuth.setResource(APPLICATION);
      authorizationService.saveAuthorization(managementTasklistAuth);

      Authorization managementReadProcessDefinition = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      managementReadProcessDefinition.setGroupId("management");
      managementReadProcessDefinition.addPermission(Permissions.READ);
      managementReadProcessDefinition.addPermission(Permissions.READ_HISTORY);
      managementReadProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
      // restrict to invoice process definition only
      managementReadProcessDefinition.setResourceId("invoice");
      authorizationService.saveAuthorization(managementReadProcessDefinition);

      Authorization salesDemoAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesDemoAuth.setGroupId("sales");
      salesDemoAuth.setResource(USER);
      salesDemoAuth.setResourceId("demo");
      salesDemoAuth.addPermission(READ);
      authorizationService.saveAuthorization(salesDemoAuth);

      Authorization salesJohnAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesJohnAuth.setGroupId("sales");
      salesJohnAuth.setResource(USER);
      salesJohnAuth.setResourceId("user1");
      salesJohnAuth.addPermission(READ);
      authorizationService.saveAuthorization(salesJohnAuth);

      Authorization manDemoAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      manDemoAuth.setGroupId("management");
      manDemoAuth.setResource(USER);
      manDemoAuth.setResourceId("demo");
      manDemoAuth.addPermission(READ);
      authorizationService.saveAuthorization(manDemoAuth);

      Authorization manPeterAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      manPeterAuth.setGroupId("management");
      manPeterAuth.setResource(USER);
      manPeterAuth.setResourceId("user3");
      manPeterAuth.addPermission(READ);
      authorizationService.saveAuthorization(manPeterAuth);
      
      Authorization manAlexandraAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      manAlexandraAuth.setGroupId("management");
      manAlexandraAuth.setResource(USER);
      manAlexandraAuth.setResourceId("user9");
      manAlexandraAuth.addPermission(READ);
      authorizationService.saveAuthorization(manAlexandraAuth);

      Authorization accDemoAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      accDemoAuth.setGroupId("accounting");
      accDemoAuth.setResource(USER);
      accDemoAuth.setResourceId("demo");
      accDemoAuth.addPermission(READ);
      authorizationService.saveAuthorization(accDemoAuth);

      Authorization accMaryAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      accMaryAuth.setGroupId("accounting");
      accMaryAuth.setResource(USER);
      accMaryAuth.setResourceId("user2");
      accMaryAuth.addPermission(READ);
      authorizationService.saveAuthorization(accMaryAuth);

      Authorization taskMaryAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      taskMaryAuth.setUserId("user2");
      taskMaryAuth.setResource(TASK);
      taskMaryAuth.setResourceId(ANY);
      taskMaryAuth.addPermission(READ);
      taskMaryAuth.addPermission(UPDATE);
      authorizationService.saveAuthorization(taskMaryAuth);
      
      
      Authorization accDavidAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      accDavidAuth.setGroupId("accounting");
      accDavidAuth.setResource(USER);
      accDavidAuth.setResourceId("user5");
      accDavidAuth.addPermission(READ);
      authorizationService.saveAuthorization(accDavidAuth);
      
      Authorization taskDavidAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      taskDavidAuth.setUserId("user5");
      taskDavidAuth.setResource(TASK);
      taskDavidAuth.setResourceId(ANY);
      taskDavidAuth.addPermission(READ);
      taskDavidAuth.addPermission(UPDATE);
      authorizationService.saveAuthorization(taskDavidAuth);
      
      Authorization accRobertAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      accRobertAuth.setGroupId("accounting");
      accRobertAuth.setResource(USER);
      accRobertAuth.setResourceId("user7");
      accRobertAuth.addPermission(READ);
      authorizationService.saveAuthorization(accRobertAuth);
      
      Authorization taskRobertAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      taskRobertAuth.setUserId("user7");
      taskRobertAuth.setResource(TASK);
      taskRobertAuth.setResourceId(ANY);
      taskRobertAuth.addPermission(READ);
      taskRobertAuth.addPermission(UPDATE);
      authorizationService.saveAuthorization(taskRobertAuth);
      
      Authorization salesSandraAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesSandraAuth.setGroupId("sales");
      salesSandraAuth.setResource(USER);
      salesSandraAuth.setResourceId("user4");
      salesSandraAuth.addPermission(READ);
      authorizationService.saveAuthorization(salesSandraAuth);
      
      Authorization salesLaraAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesLaraAuth.setGroupId("sales");
      salesLaraAuth.setResource(USER);
      salesLaraAuth.setResourceId("user6");
      salesLaraAuth.addPermission(READ);
      authorizationService.saveAuthorization(salesLaraAuth);
      
      Authorization salesAlexanderAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesAlexanderAuth.setGroupId("sales");
      salesAlexanderAuth.setResource(USER);
      salesAlexanderAuth.setResourceId("user8");
      salesAlexanderAuth.addPermission(READ);
      authorizationService.saveAuthorization(salesAlexanderAuth);

      Authorization salesLarsAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
      salesLarsAuth.setGroupId("sales");
      salesLarsAuth.setResource(USER);
      salesLarsAuth.setResourceId("user10");
      salesLarsAuth.addPermission(READ);
      authorizationService.saveAuthorization(salesLarsAuth);

      // create default filters

      FilterService filterService = engine.getFilterService();

      Map<String, Object> filterProperties = new HashMap<String, Object>();
      filterProperties.put("description", "Tasks assigned to me");
      filterProperties.put("priority", -10);
      addVariables(filterProperties);
      TaskService taskService = engine.getTaskService();
      TaskQuery query = taskService.createTaskQuery().taskAssigneeExpression("${currentUser()}");
      Filter myTasksFilter = filterService.newTaskFilter().setName("My Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
      filterService.saveFilter(myTasksFilter);

      filterProperties.clear();
      filterProperties.put("description", "Tasks assigned to my Groups");
      filterProperties.put("priority", -5);
      addVariables(filterProperties);
      query = taskService.createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned();
      Filter groupTasksFilter = filterService.newTaskFilter().setName("My Group Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
      filterService.saveFilter(groupTasksFilter);

      // global read authorizations for these filters

      Authorization globalMyTaskFilterRead = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GLOBAL);
      globalMyTaskFilterRead.setResource(FILTER);
      globalMyTaskFilterRead.setResourceId(myTasksFilter.getId());
      globalMyTaskFilterRead.addPermission(READ);
      authorizationService.saveAuthorization(globalMyTaskFilterRead);

      Authorization globalGroupFilterRead = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GLOBAL);
      globalGroupFilterRead.setResource(FILTER);
      globalGroupFilterRead.setResourceId(groupTasksFilter.getId());
      globalGroupFilterRead.addPermission(READ);
      authorizationService.saveAuthorization(globalGroupFilterRead);

      // management filter

      filterProperties.clear();
      filterProperties.put("description", "Tasks for Group Accounting");
      filterProperties.put("priority", -3);
      addVariables(filterProperties);
      query = taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList("accounting")).taskUnassigned();
      Filter candidateGroupTasksFilter = filterService.newTaskFilter().setName("Accounting").setProperties(filterProperties).setOwner("demo").setQuery(query);
      filterService.saveFilter(candidateGroupTasksFilter);

      Authorization managementGroupFilterRead = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      managementGroupFilterRead.setResource(FILTER);
      managementGroupFilterRead.setResourceId(candidateGroupTasksFilter.getId());
      managementGroupFilterRead.addPermission(READ);
      managementGroupFilterRead.setGroupId("accounting");
      authorizationService.saveAuthorization(managementGroupFilterRead);

      // john's tasks

      filterProperties.clear();
      filterProperties.put("description", "Tasks assigned to John");
      filterProperties.put("priority", -1);
      addVariables(filterProperties);
      query = taskService.createTaskQuery().taskAssignee("john");
      Filter johnsTasksFilter = filterService.newTaskFilter().setName("John's Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
      filterService.saveFilter(johnsTasksFilter);

      // mary's tasks

      filterProperties.clear();
      filterProperties.put("description", "Tasks assigned to Mary");
      filterProperties.put("priority", -1);
      addVariables(filterProperties);
      query = taskService.createTaskQuery().taskAssignee("mary");
      Filter marysTasksFilter = filterService.newTaskFilter().setName("Mary's Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
      filterService.saveFilter(marysTasksFilter);

      // peter's tasks

      filterProperties.clear();
      filterProperties.put("description", "Tasks assigned to Peter");
      filterProperties.put("priority", -1);
      addVariables(filterProperties);
      query = taskService.createTaskQuery().taskAssignee("peter");
      Filter petersTasksFilter = filterService.newTaskFilter().setName("Peter's Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
      filterService.saveFilter(petersTasksFilter);

      // all tasks

      filterProperties.clear();
      filterProperties.put("description", "All Tasks - Not recommended to be used in production :)");
      filterProperties.put("priority", 10);
      addVariables(filterProperties);
      query = taskService.createTaskQuery();
      Filter allTasksFilter = filterService.newTaskFilter().setName("All Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
      filterService.saveFilter(allTasksFilter);

    }

    protected void addVariables(Map<String, Object> filterProperties) {
      List<Object> variables = new ArrayList<Object>();

      addVariable(variables, "amount", "Invoice Amount");
      addVariable(variables, "invoiceNumber", "Invoice Number");
      addVariable(variables, "creditor", "Creditor");
      addVariable(variables, "approver", "Approver");

      filterProperties.put("variables", variables);
    }

    protected void addVariable(List<Object> variables, String name, String label) {
      Map<String, String> variable = new HashMap<String, String>();
      variable.put("name", name);
      variable.put("label", label);
      variables.add(variable);
    }
}
