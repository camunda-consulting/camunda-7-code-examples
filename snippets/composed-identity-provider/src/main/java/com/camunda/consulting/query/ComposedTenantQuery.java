package com.camunda.consulting.query;

import java.util.List;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.TenantQuery;

public class ComposedTenantQuery extends AbstractComposedQuery<TenantQuery, Tenant>
    implements TenantQuery {
  public ComposedTenantQuery(List<TenantQuery> queryList) {
    super(queryList);
  }

  @Override
  protected TenantQuery query() {
    return this;
  }

  @Override
  public TenantQuery tenantId(String tenantId) {
    return apply(q -> q.tenantId(tenantId));
  }

  @Override
  public TenantQuery tenantIdIn(String... ids) {
    return apply(q -> q.tenantIdIn(ids));
  }

  @Override
  public TenantQuery tenantName(String tenantName) {
    return apply(q -> q.tenantName(tenantName));
  }

  @Override
  public TenantQuery tenantNameLike(String tenantNameLike) {
    return apply(q -> q.tenantNameLike(tenantNameLike));
  }

  @Override
  public TenantQuery userMember(String userId) {
    return apply(q -> q.userMember(userId));
  }

  @Override
  public TenantQuery groupMember(String groupId) {
    return apply(q -> q.groupMember(groupId));
  }

  @Override
  public TenantQuery includingGroupsOfUser(boolean includingGroups) {
    return apply(q -> q.includingGroupsOfUser(includingGroups));
  }

  @Override
  public TenantQuery orderByTenantId() {
    return apply(TenantQuery::orderByTenantId);
  }

  @Override
  public TenantQuery orderByTenantName() {
    return apply(TenantQuery::orderByTenantName);
  }
}
