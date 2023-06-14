package com.camunda.consulting.query;

import java.util.List;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;

public class ComposedGroupQuery extends AbstractComposedQuery<GroupQuery, Group>
    implements GroupQuery {
  public ComposedGroupQuery(List<GroupQuery> queryList) {
    super(queryList);
  }

  @Override
  protected GroupQuery query() {
    return this;
  }

  @Override
  public GroupQuery groupId(String groupId) {
    return apply(q -> q.groupId(groupId));
  }

  @Override
  public GroupQuery groupIdIn(String... ids) {
    return apply(q -> q.groupIdIn(ids));
  }

  @Override
  public GroupQuery groupName(String groupName) {
    return apply(q -> q.groupName(groupName));
  }

  @Override
  public GroupQuery groupNameLike(String groupNameLike) {
    return apply(q -> q.groupNameLike(groupNameLike));
  }

  @Override
  public GroupQuery groupType(String groupType) {
    return apply(q -> q.groupType(groupType));
  }

  @Override
  public GroupQuery groupMember(String groupMemberUserId) {
    return apply(q -> q.groupMember(groupMemberUserId));
  }

  @Override
  public GroupQuery potentialStarter(String procDefId) {
    return apply(q -> q.potentialStarter(procDefId));
  }

  @Override
  public GroupQuery memberOfTenant(String tenantId) {
    return apply(q -> q.memberOfTenant(tenantId));
  }

  @Override
  public GroupQuery orderByGroupId() {
    return apply(GroupQuery::orderByGroupId);
  }

  @Override
  public GroupQuery orderByGroupName() {
    return apply(GroupQuery::orderByGroupName);
  }

  @Override
  public GroupQuery orderByGroupType() {
    return apply(GroupQuery::orderByGroupType);
  }
}
