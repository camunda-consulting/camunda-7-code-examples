package com.camunda.consulting.example.identity;

import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.UserQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.List;

public class CustomUserQuery extends UserQueryImpl {

  public CustomUserQuery() {}

  public CustomUserQuery(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  @Override
  public long executeCount(CommandContext commandContext) {
    return getCustomIdentityProvider(commandContext).findUserCountByQueryCriteria(this);
  }

  @Override
  public List<User> executeList(CommandContext commandContext, Page page) {
    return getCustomIdentityProvider(commandContext).findUserByQueryCriteria(this);
  }

  protected CustomIdentityProviderSession getCustomIdentityProvider(CommandContext commandContext) {
    return (CustomIdentityProviderSession) commandContext.getReadOnlyIdentityProvider();
  }
}
