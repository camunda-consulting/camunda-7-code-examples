package com.camunda.consulting;

import org.camunda.bpm.engine.impl.interceptor.Session;
import org.camunda.bpm.identity.impl.ldap.LdapIdentityProviderFactory;

public class ComposedLdapIdentityProviderFactory extends LdapIdentityProviderFactory {
  public Session openSession() {
    return new ComposedLdapIdentityProviderSession(ldapConfiguration);
  }
}
