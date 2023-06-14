package com.camunda.consulting;

import static com.camunda.consulting.ComposedIdentityProviderUtil.*;

import java.util.List;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.identity.WritableIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;

public class ComposedIdentityProviderFactory implements SessionFactory {
  private final List<SessionFactory> identityProviderFactories;

  public ComposedIdentityProviderFactory(List<SessionFactory> identityProviderFactories) {
    this.identityProviderFactories = identityProviderFactories;
  }

  @Override
  public Class<?> getSessionType() {
    return ComposedIdentityProvider.class;
  }

  @Override
  public Session openSession() {
    return new ComposedIdentityProvider(
        getSessionsOfType(ReadOnlyIdentityProvider.class),
        getSessionOfType(WritableIdentityProvider.class));
  }

  private <T extends Session> List<T> getSessionsOfType(Class<T> type) {
    return identityProviderFactories.stream()
        .filter(sessionFactory -> type.isAssignableFrom(sessionFactory.getSessionType()))
        .map(SessionFactory::openSession)
        .map(type::cast)
        .toList();
  }

  private <T extends Session> T getSessionOfType(Class<T> type) {
    return getSessionsOfType(type).stream().reduce(atMostOne()).orElse(null);
  }
}
