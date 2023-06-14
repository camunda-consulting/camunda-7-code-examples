package com.camunda.consulting.query;

import static com.camunda.consulting.ComposedIdentityProviderUtil.*;

import java.util.List;
import org.camunda.bpm.engine.identity.NativeUserQuery;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.query.NativeQuery;

public class ComposedNativeUserQuery implements NativeUserQuery {
  private final List<NativeUserQuery> userQueries;

  public ComposedNativeUserQuery(List<NativeUserQuery> userQueries) {
    this.userQueries = userQueries;
  }

  @Override
  public NativeUserQuery sql(String selectClause) {
    userQueries.forEach(q -> q.sql(selectClause));
    return this;
  }

  @Override
  public NativeUserQuery parameter(String name, Object value) {
    userQueries.forEach(q -> q.parameter(name, value));
    return this;
  }

  @Override
  public long count() {
    return userQueries.stream().mapToLong(NativeQuery::count).sum();
  }

  @Override
  public User singleResult() {
    return userQueries.stream().map(NativeQuery::singleResult).reduce(atMostOne()).orElse(null);
  }

  @Override
  public List<User> list() {
    return userQueries.stream().map(NativeQuery::list).flatMap(List::stream).toList();
  }

  @Override
  public List<User> listPage(int firstResult, int maxResults) {
    // TODO handle this better
    return userQueries.stream()
        .map(q -> q.listPage(firstResult, maxResults))
        .flatMap(List::stream)
        .toList();
  }
}
