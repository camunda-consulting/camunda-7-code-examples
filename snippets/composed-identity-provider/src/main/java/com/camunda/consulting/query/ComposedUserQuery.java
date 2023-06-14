package com.camunda.consulting.query;

import java.util.List;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;

public class ComposedUserQuery extends AbstractComposedQuery<UserQuery, User> implements UserQuery {

  public ComposedUserQuery(List<UserQuery> userQueries) {
    super(userQueries);
  }

  @Override
  public UserQuery userId(String id) {
    return apply(q -> q.userId(id));
  }

  @Override
  public UserQuery userIdIn(String... ids) {
    return apply(q -> q.userIdIn(ids));
  }

  @Override
  public UserQuery userFirstName(String firstName) {
    return apply(q -> q.userFirstName(firstName));
  }

  @Override
  public UserQuery userFirstNameLike(String firstNameLike) {
    return apply(q -> q.userFirstNameLike(firstNameLike));
  }

  @Override
  public UserQuery userLastName(String lastName) {
    return apply(q -> q.userLastName(lastName));
  }

  @Override
  public UserQuery userLastNameLike(String lastNameLike) {
    return apply(q -> q.userLastNameLike(lastNameLike));
  }

  @Override
  public UserQuery userEmail(String email) {
    return apply(q -> q.userEmail(email));
  }

  @Override
  public UserQuery userEmailLike(String emailLike) {
    return apply(q -> q.userEmailLike(emailLike));
  }

  @Override
  public UserQuery memberOfGroup(String groupId) {
    return apply(q -> q.memberOfGroup(groupId));
  }

  @Override
  public UserQuery potentialStarter(String procDefId) {
    return apply(q -> q.potentialStarter(procDefId));
  }

  @Override
  public UserQuery memberOfTenant(String tenantId) {
    return apply(q -> q.memberOfTenant(tenantId));
  }

  @Override
  public UserQuery orderByUserId() {
    return apply(UserQuery::orderByUserId);
  }

  @Override
  public UserQuery orderByUserFirstName() {
    return apply(UserQuery::orderByUserFirstName);
  }

  @Override
  public UserQuery orderByUserLastName() {
    return apply(UserQuery::orderByUserLastName);
  }

  @Override
  public UserQuery orderByUserEmail() {
    return apply(UserQuery::orderByUserEmail);
  }

  @Override
  protected UserQuery query() {
    return this;
  }
}
