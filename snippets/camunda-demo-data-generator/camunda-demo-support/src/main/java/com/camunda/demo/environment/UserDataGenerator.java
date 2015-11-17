package com.camunda.demo.environment;

import static com.camunda.demo.environment.DefaultFilter.useFilter;
import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.ACCESS;
import static org.camunda.bpm.engine.authorization.Permissions.ALL;
import static org.camunda.bpm.engine.authorization.Permissions.READ;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;
import static org.camunda.bpm.engine.authorization.Resources.FILTER;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;

public class UserDataGenerator {

  private final static Logger LOGGER = Logger.getLogger(UserDataGenerator.class.getName());

  public static void createDemoData(ProcessEngine engine) {

  }

  public static void createDefaultUsers(ProcessEngine engine) {
    LOGGER.info("Generating default users for showroom");

    // /////////////////////////////////////
    // create user as otherwise the invoice exmaple will re-create users
    // gets no authorizations - cannot do any harm :-)
    addUser(engine, "demo", "demo", "Demo", "Demo");

    // Admin USer - is allowed to do anything
    addUser(engine, "admin", "bpm", "Camunda", "BPM");
    if (addGroup(engine, Groups.CAMUNDA_ADMIN, "Camunda BPM Admin", "admin")) {
      // should be there already!!
      // create ADMIN authorizations on all built-in resources
      for (Resource resource : Resources.values()) {
        if (engine.getAuthorizationService().createAuthorizationQuery().groupIdIn(Groups.CAMUNDA_ADMIN).resourceType(resource).resourceId(ANY).count() == 0) {
          AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
          userAdminAuth.setGroupId(Groups.CAMUNDA_ADMIN);
          userAdminAuth.setResource(resource);
          userAdminAuth.setResourceId(ANY);
          userAdminAuth.addPermission(ALL);
          engine.getAuthorizationService().saveAuthorization(userAdminAuth);
        }
      }
    } else {
      // addMembership(engine, Groups.CAMUNDA_ADMIN, "admin");
    }
  }

  public static void createGrantUserAuthorization(ProcessEngine engine, String[] userIds, Permission[] permissions, Resource resource, String[] resourceIds) {
    for (String userId : userIds) {
      for (String resourceId : resourceIds) {

        if (engine.getAuthorizationService().createAuthorizationQuery().userIdIn(userId).resourceId(resourceId).count() == 0) {
          Authorization createAuth = engine.getAuthorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
          createAuth.setUserId(userId);
          for (Permission permission : permissions) {
            createAuth.addPermission(permission);
          }
          createAuth.setResourceId(resourceId);
          createAuth.setResource(resource);
          engine.getAuthorizationService().saveAuthorization(createAuth);
        }
      }
    }
  }

  public static void createGrantGroupAuthorization(ProcessEngine engine, String[] groups, Permission[] permissions, Resource resource, String[] resourceIds) {
    for (String group : groups) {
      for (String resourceId : resourceIds) {

        if (engine.getAuthorizationService().createAuthorizationQuery().groupIdIn(group).resourceId(resourceId).count() == 0) {

          Authorization createAuth = engine.getAuthorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
          createAuth.setGroupId(group);
          for (Permission permission : permissions) {
            createAuth.addPermission(permission);
          }
          createAuth.setResourceId(resourceId);
          createAuth.setResource(resource);
          engine.getAuthorizationService().saveAuthorization(createAuth);
        }
      }
    }
  }

  public static boolean addGroup(ProcessEngine engine, String groupId, String groupName, String... members) {
    if (engine.getIdentityService().isReadOnly()) {
      LOGGER.severe("Identity service provider is Read Only, not creating any demo users.");
      return false;
    }
    if (engine.getIdentityService().createGroupQuery().groupId(groupId).count() > 0) {
      addMembership(engine, groupId, members);
      return false;
    }

    Group salesGroup = engine.getIdentityService().newGroup(groupId);
    salesGroup.setName(groupName);
    salesGroup.setType("WORKFLOW");
    engine.getIdentityService().saveGroup(salesGroup);

    // authorize groups for tasklist only:
    Authorization auth = engine.getAuthorizationService().createNewAuthorization(AUTH_TYPE_GRANT);
    auth.setGroupId(groupId);
    auth.addPermission(ACCESS);
    auth.setResourceId("tasklist");
    auth.setResource(APPLICATION);
    engine.getAuthorizationService().saveAuthorization(auth);

    addMembership(engine, groupId, members);
    return true;
  }

  public static void addMembership(ProcessEngine engine, String groupId, String... userIds) {
    for (String userId : userIds) {
      engine.getIdentityService().deleteMembership(userId, groupId);
      try {
        engine.getIdentityService().createMembership(userId, groupId);
      } catch (Exception ex) {
        // memebership already there - ignore
      }
    }
  }

  public static String getPasswordSuffix() {
    String passwordSuffix = UserProperties.readProperty("camunda.password.suffix");
    if (passwordSuffix == null) {
      return "";
    } else {
      return passwordSuffix;
    }
  }

  public static boolean addUser(ProcessEngine engine, String userName, String password, String firstname, String lastname) {
    if (engine.getIdentityService().isReadOnly()) {
      LOGGER.severe("Identity service provider is Read Only, not creating any demo users.");
      return false;
    }
    if (engine.getIdentityService().createUserQuery().userId(userName).count() > 0) {
      return false;
    }
    User user = engine.getIdentityService().newUser(userName);
    user.setFirstName(firstname);
    user.setLastName(lastname);
    user.setPassword(password + getPasswordSuffix());
    user.setEmail("demo@camunda.org");
    engine.getIdentityService().saveUser(user);
    return true;
  }

  public static void addFilterUserAuthorization(ProcessEngine engine, String userId, String... filterNames) {
    for (String filterName : filterNames) {
      String filterId = useFilter(engine, filterName);

      Authorization managementGroupFilterRead = engine.getAuthorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      managementGroupFilterRead.setResource(FILTER);
      managementGroupFilterRead.setResourceId(filterId);
      managementGroupFilterRead.addPermission(READ);
      managementGroupFilterRead.setUserId(userId);
      engine.getAuthorizationService().saveAuthorization(managementGroupFilterRead);
    }
  }

  public static void addFilterGroupAuthorization(ProcessEngine engine, String groupId, String... filterNames) {
    for (String filterName : filterNames) {
      String filterId = useFilter(engine, filterName);

      Authorization managementGroupFilterRead = engine.getAuthorizationService().createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      managementGroupFilterRead.setResource(FILTER);
      managementGroupFilterRead.setResourceId(filterId);
      managementGroupFilterRead.addPermission(READ);
      managementGroupFilterRead.setGroupId(groupId);
      engine.getAuthorizationService().saveAuthorization(managementGroupFilterRead);
    }
  }

}