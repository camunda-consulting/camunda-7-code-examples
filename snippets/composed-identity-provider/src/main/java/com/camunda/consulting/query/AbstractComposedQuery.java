package com.camunda.consulting.query;

import static com.camunda.consulting.ComposedIdentityProviderUtil.*;

import java.util.List;
import java.util.function.Consumer;
import org.camunda.bpm.engine.query.Query;

public abstract class AbstractComposedQuery<Q extends Query<?, R>, R> implements Query<Q, R> {
  protected final List<Q> queryList;

  public AbstractComposedQuery(List<Q> queryList) {
    this.queryList = queryList;
  }

  @Override
  public final Q asc() {
    return apply(Query::asc);
  }

  @Override
  public final Q desc() {
    return apply(Query::desc);
  }

  @Override
  public final long count() {
    return queryList.stream().mapToLong(Query::count).sum();
  }

  @Override
  public final R singleResult() {
    return queryList.stream().map(Query::singleResult).reduce(atMostOne()).orElse(null);
  }

  @Override
  public final List<R> list() {
    return queryList.stream().map(Query::list).flatMap(List::stream).toList();
  }

  @Override
  public final List<R> unlimitedList() {
    return queryList.stream().map(Query::unlimitedList).flatMap(List::stream).toList();
  }

  @Override
  public final List<R> listPage(int firstResult, int maxResults) {
    return queryList.stream()
        .map(q -> q.listPage(firstResult, maxResults))
        .flatMap(List::stream)
        .toList();
  }

  protected Q apply(Consumer<Q> action) {
    queryList.forEach(action);
    return query();
  }

  protected abstract Q query();
}
