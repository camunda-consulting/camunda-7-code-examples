package com.camunda.consulting.rest.auth;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationFilterWithBypass extends ProcessEngineAuthenticationFilter {
  
  private static final Logger log = LoggerFactory.getLogger(AuthenticationFilterWithBypass.class);
  
  protected void setAuthenticatedUser(ProcessEngine engine, String userId, List<String> groupIds, List<String> tenantIds){
    log.info("get groups and tenants for authenticated user {}", userId);
    
    List<String> bypassGroupIds = ((AuthenticationProviderWithBypass) authenticationProvider).getBypassGroupIds();
    if (userId.equals(((AuthenticationProviderWithBypass)authenticationProvider).getBypassUserId())
        && bypassGroupIds != null) {
      log.info("set user and group from bypassUser");
      groupIds = bypassGroupIds;
      tenantIds = new ArrayList<String>();
    } else {
      groupIds = getGroupsOfUser(engine, userId);
      tenantIds = getTenantsOfUser(engine, userId);
    }
    engine.getIdentityService().setAuthentication(userId, groupIds, tenantIds);
  }

}
