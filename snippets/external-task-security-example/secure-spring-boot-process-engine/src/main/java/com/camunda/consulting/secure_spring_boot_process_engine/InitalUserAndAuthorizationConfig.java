package com.camunda.consulting.secure_spring_boot_process_engine;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.ProcessDefinitionPermissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitalUserAndAuthorizationConfig {

  private static final Logger LOG = LoggerFactory.getLogger(InitalUserAndAuthorizationConfig.class);

  IdentityService identityService;
  AuthorizationService authorizationService;

  @Autowired
  public InitalUserAndAuthorizationConfig(IdentityService identityService, AuthorizationService authorizationService) {
    this.identityService = identityService;
    this.authorizationService = authorizationService;
  }

  @Bean
  public void initialUserSetup() {
    LOG.info("create users");
    User system1User = identityService.newUser("system1");
    system1User.setFirstName("System");
    system1User.setLastName("One");
    system1User.setPassword("secret");
    saveUserIfNotExists(system1User);

    User system2User = identityService.newUser("system2");
    system2User.setFirstName("System");
    system2User.setLastName("Two");
    system2User.setPassword("secret");
    saveUserIfNotExists(system2User);
    
    LOG.info("create authorizations");
    Authorization system1ProcessAAuth = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
    system1ProcessAAuth.setUserId(system1User.getId());
    system1ProcessAAuth.setResourceId("processA");
    setProcessDefinitionAuthorization(system1ProcessAAuth);
    saveAuthorizationIfNotExists(system1User, system1ProcessAAuth, "processA");
    
    Authorization system2ProcessBAuth = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
    system2ProcessBAuth.setUserId(system2User.getId());
    system2ProcessBAuth.setResourceId("processB");
    setProcessDefinitionAuthorization(system2ProcessBAuth);
    saveAuthorizationIfNotExists(system2User, system2ProcessBAuth, "processB");
  }

  public void saveUserIfNotExists(User user) {
    if (identityService.createUserQuery().userId(user.getId()).singleResult() == null) {
      identityService.saveUser(user);
      LOG.info("{} {} user created: {}", user.getFirstName(), user.getLastName(), user);
    } else {
      LOG.info("{} {} user already exists", user.getFirstName(), user.getLastName());
    }
  }

  public void setProcessDefinitionAuthorization(Authorization authorization) {
    authorization.setResourceType(Resources.PROCESS_DEFINITION.resourceType());
    authorization.addPermission(ProcessDefinitionPermissions.READ);
    authorization.addPermission(ProcessDefinitionPermissions.READ_INSTANCE);
    authorization.addPermission(ProcessDefinitionPermissions.UPDATE_INSTANCE);
    authorization.addPermission(ProcessDefinitionPermissions.READ_INSTANCE_VARIABLE);
    authorization.addPermission(ProcessDefinitionPermissions.UPDATE_INSTANCE_VARIABLE);
  }

  public void saveAuthorizationIfNotExists(User user, Authorization authorization, String processId) {
    if (authorizationService.createAuthorizationQuery().authorizationType(Authorization.AUTH_TYPE_GRANT)
        .resourceId(processId).resourceType(Resources.PROCESS_DEFINITION.resourceType()).userIdIn(user.getId())
        .singleResult() == null) {
      authorizationService.saveAuthorization(authorization);
      LOG.info("{} {} authorized for {}", user.getFirstName(), user.getLastName(), processId);
    } else {
      LOG.info("{} {} already authorized for {}", user.getFirstName(), user.getLastName(), processId);
    }
  }
}
