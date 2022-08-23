package com.camunda.consulting.example.identity;

import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.engine.impl.interceptor.SessionFactory;

public class CustomIdentityProviderFactory implements SessionFactory {
  private final CustomIdentityService identityService;

  public CustomIdentityProviderFactory(CustomIdentityService identityService) {this.identityService = identityService;}

  @Override
  public Class<?> getSessionType() {
    return ReadOnlyIdentityProvider.class;
  }

  @Override
  public Session openSession() {
    return new CustomIdentityProviderSession(identityService);
  }
}
