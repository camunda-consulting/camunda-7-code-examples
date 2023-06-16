package com.camunda.consulting;

import static com.camunda.consulting.StaticIdentityProvider.*;

import java.util.List;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.identity.impl.ldap.LdapConfiguration;
import org.camunda.bpm.identity.impl.ldap.LdapGroupQuery;
import org.camunda.bpm.identity.impl.ldap.LdapIdentityProviderSession;
import org.camunda.bpm.identity.impl.ldap.LdapUserQueryImpl;

public class ComposedLdapIdentityProviderSession extends LdapIdentityProviderSession
    implements ReadOnlyIdentityProvider {

  public ComposedLdapIdentityProviderSession(LdapConfiguration ldapConfiguration) {
    super(ldapConfiguration);
  }

  @Override
  public User findUserById(String userId) {
    User userById = super.findUserById(userId);
    return userById == null ? getInstance().findUserById(userId) : userById;
  }

  @Override
  public boolean checkPassword(String userId, String password) {
    return super.checkPassword(userId, password) || getInstance().checkPassword(userId, password);
  }

  @Override
  public Group findGroupById(String groupId) {
    Group groupById = super.findGroupById(groupId);
    return groupById == null ? getInstance().findGroupById(groupId) : groupById;
  }

  @Override
  public List<Group> findGroupByQueryCriteria(LdapGroupQuery query) {
    List<Group> groupByQueryCriteria = super.findGroupByQueryCriteria(query);
    groupByQueryCriteria.addAll(getInstance().findGroupsByQuery(query));
    return groupByQueryCriteria;
  }

  @Override
  public List<User> findUserByQueryCriteria(LdapUserQueryImpl query) {
    List<User> userByQueryCriteria = super.findUserByQueryCriteria(query);
    userByQueryCriteria.addAll(getInstance().findUsersByQuery(query));
    return userByQueryCriteria;
  }
}
