package com.camunda.consulting.example.identity;

import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.impl.GroupQueryImpl;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.List;

public class CustomGroupQuery extends GroupQueryImpl {
  public CustomGroupQuery(){}
  public CustomGroupQuery(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  @Override
  public long executeCount(CommandContext commandContext) {
    return getCustomIdentityProvider(commandContext).findGroupCountByQueryCriteria(this);
  }

  @Override
  public List<Group> executeList(CommandContext commandContext, Page page) {
    return getCustomIdentityProvider(commandContext).findGroupByQueryCriteria(this);

  }

  protected CustomIdentityProviderSession getCustomIdentityProvider(CommandContext commandContext) {
    return (CustomIdentityProviderSession) commandContext.getReadOnlyIdentityProvider();
  }
}
