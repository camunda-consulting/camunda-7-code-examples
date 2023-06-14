package com.camunda.consulting;

import static com.camunda.consulting.ComposedIdentityProviderUtil.*;

import com.camunda.consulting.query.ComposedGroupQuery;
import com.camunda.consulting.query.ComposedNativeUserQuery;
import com.camunda.consulting.query.ComposedTenantQuery;
import com.camunda.consulting.query.ComposedUserQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.identity.NativeUserQuery;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.TenantQuery;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.impl.identity.IdentityOperationResult;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.identity.WritableIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.Session;

public class ComposedIdentityProvider
    implements WritableIdentityProvider, ReadOnlyIdentityProvider {
  private final List<ReadOnlyIdentityProvider> readOnlyIdentityProviders;
  private final WritableIdentityProvider writableIdentityProvider;

  public ComposedIdentityProvider(
      List<ReadOnlyIdentityProvider> identityProviders,
      WritableIdentityProvider writableIdentityProvider) {
    this.readOnlyIdentityProviders = identityProviders;
    this.writableIdentityProvider = writableIdentityProvider;
  }

  @Override
  public User findUserById(String userId) {
    return readOnlyIdentityProviders.stream()
        .map(identityProvider -> identityProvider.findUserById(userId))
        .reduce(atMostOne())
        .orElse(null);
  }

  @Override
  public UserQuery createUserQuery() {
    return new ComposedUserQuery(
        readOnlyIdentityProviders.stream().map(ReadOnlyIdentityProvider::createUserQuery).toList());
  }

  @Override
  public UserQuery createUserQuery(CommandContext commandContext) {
    return new ComposedUserQuery(
        readOnlyIdentityProviders.stream().map(p -> p.createUserQuery(commandContext)).toList());
  }

  @Override
  public NativeUserQuery createNativeUserQuery() {
    return new ComposedNativeUserQuery(
        readOnlyIdentityProviders.stream()
            .map(ReadOnlyIdentityProvider::createNativeUserQuery)
            .toList());
  }

  @Override
  public boolean checkPassword(String userId, String password) {
    return readOnlyIdentityProviders.stream().anyMatch(p -> p.checkPassword(userId, password));
  }

  @Override
  public Group findGroupById(String groupId) {
    return readOnlyIdentityProviders.stream()
        .map(identityProvider -> identityProvider.findGroupById(groupId))
        .reduce(atMostOne())
        .orElse(null);
  }

  @Override
  public GroupQuery createGroupQuery() {
    return new ComposedGroupQuery(
        readOnlyIdentityProviders.stream()
            .map(ReadOnlyIdentityProvider::createGroupQuery)
            .toList());
  }

  @Override
  public GroupQuery createGroupQuery(CommandContext commandContext) {
    return new ComposedGroupQuery(
        readOnlyIdentityProviders.stream().map(p -> p.createGroupQuery(commandContext)).toList());
  }

  @Override
  public Tenant findTenantById(String tenantId) {
    return readOnlyIdentityProviders.stream()
        .map(identityProvider -> identityProvider.findTenantById(tenantId))
        .reduce(atMostOne())
        .orElse(null);
  }

  @Override
  public TenantQuery createTenantQuery() {
    return new ComposedTenantQuery(
        readOnlyIdentityProviders.stream()
            .map(ReadOnlyIdentityProvider::createTenantQuery)
            .toList());
  }

  @Override
  public TenantQuery createTenantQuery(CommandContext commandContext) {
    return new ComposedTenantQuery(
        readOnlyIdentityProviders.stream()
            .map(identityProvider -> identityProvider.createTenantQuery(commandContext))
            .toList());
  }

  @Override
  public void flush() {
    readOnlyIdentityProviders.forEach(Session::flush);
  }

  @Override
  public void close() {
    readOnlyIdentityProviders.forEach(Session::close);
  }

  @Override
  public User createNewUser(String userId) {
    return execute(i -> i.createNewUser(userId));
  }

  @Override
  public IdentityOperationResult saveUser(User user) {
    return execute(i -> i.saveUser(user));
  }

  @Override
  public IdentityOperationResult deleteUser(String userId) {
    return execute(i -> i.deleteUser(userId));
  }

  @Override
  public IdentityOperationResult unlockUser(String userId) {
    return execute(i -> i.unlockUser(userId));
  }

  @Override
  public Group createNewGroup(String groupId) {
    return execute(i -> i.createNewGroup(groupId));
  }

  @Override
  public IdentityOperationResult saveGroup(Group group) {
    return execute(i -> i.saveGroup(group));
  }

  @Override
  public IdentityOperationResult deleteGroup(String groupId) {
    return execute(i -> i.deleteGroup(groupId));
  }

  @Override
  public Tenant createNewTenant(String tenantId) {
    return execute(i -> i.createNewTenant(tenantId));
  }

  @Override
  public IdentityOperationResult saveTenant(Tenant tenant) {
    return execute(i -> i.saveTenant(tenant));
  }

  @Override
  public IdentityOperationResult deleteTenant(String tenantId) {
    return execute(i -> i.deleteTenant(tenantId));
  }

  @Override
  public IdentityOperationResult createMembership(String userId, String groupId) {
    return execute(i -> i.createMembership(userId, groupId));
  }

  @Override
  public IdentityOperationResult deleteMembership(String userId, String groupId) {
    return execute(i -> i.deleteMembership(userId, groupId));
  }

  @Override
  public IdentityOperationResult createTenantUserMembership(String tenantId, String userId) {
    return execute(i -> i.createTenantUserMembership(tenantId, userId));
  }

  @Override
  public IdentityOperationResult createTenantGroupMembership(String tenantId, String groupId) {
    return execute(i -> i.createTenantGroupMembership(tenantId, groupId));
  }

  @Override
  public IdentityOperationResult deleteTenantUserMembership(String tenantId, String userId) {
    return execute(i -> i.deleteTenantUserMembership(tenantId, userId));
  }

  @Override
  public IdentityOperationResult deleteTenantGroupMembership(String tenantId, String groupId) {
    return execute(i -> i.deleteTenantGroupMembership(tenantId, groupId));
  }

  private <T> T execute(Function<WritableIdentityProvider, T> function) {
    return Optional.ofNullable(writableIdentityProvider)
        .map(function)
        .orElseThrow(() -> new IllegalStateException("No writable identity provider present"));
  }
}
