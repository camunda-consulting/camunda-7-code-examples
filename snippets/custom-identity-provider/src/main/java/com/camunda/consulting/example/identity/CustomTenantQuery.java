package com.camunda.consulting.example.identity;

import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.TenantQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;

import java.util.List;

public class CustomTenantQuery extends TenantQueryImpl {
  public CustomTenantQuery(){}
  public CustomTenantQuery(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  @Override
  public long executeCount(CommandContext commandContext) {
    return getCustomIdentityProvider(commandContext).findTenantCountByQueryCriteria(this);
  }

  @Override
  public List<Tenant> executeList(CommandContext commandContext, Page page) {
    return getCustomIdentityProvider(commandContext).findTenantByQueryCriteria(this);
  }

  protected CustomIdentityProviderSession getCustomIdentityProvider(CommandContext commandContext) {
    return (CustomIdentityProviderSession) commandContext.getReadOnlyIdentityProvider();
  }
}
