package com.camunda.consulting;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.AuthorizationQuery;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationProcessEnginePlugin extends AbstractProcessEnginePlugin {
  private static final Logger LOG = LoggerFactory.getLogger(AuthorizationProcessEnginePlugin.class);
  private final AuthorizationRules rules;

  @Autowired
  public AuthorizationProcessEnginePlugin(AuthorizationRules rules) {
    this.rules = rules;
  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
    this.rules.getRules().forEach(rule -> {
      LOG.info("Adding rule {}", rule);
      AuthorizationQuery query = processEngine
        .getAuthorizationService()
        .createAuthorizationQuery()
        .authorizationType(rule.getType())
        .resourceId(rule.getResourceId())
        .resourceType(rule.getResource());

      if (StringUtils.isNotBlank(rule.getGroup())) {
        query.groupIdIn(rule.getGroup());
      }
      if (StringUtils.isNotBlank(rule.getUser())) {
        query.userIdIn(rule.getUser());
      }
      List<Authorization> authList = query.list();
      Authorization auth = null;
      if (authList.size() == 0) {
        auth = processEngine.getAuthorizationService().createNewAuthorization(rule.getType());
      } else if (authList.size() == 1) {
        auth = authList.get(0);
      } else {
        return;
      }
      auth.setGroupId(rule.getGroup());
      auth.setUserId(rule.getUser());
      auth.setResourceId(rule.getResourceId());
      auth.setResource(rule.getResource());
      auth.setPermissions(rule.getPermissions().toArray(new Permission[rule.getPermissions().size()]));
      processEngine.getAuthorizationService().saveAuthorization(auth);
    });

  }
}
