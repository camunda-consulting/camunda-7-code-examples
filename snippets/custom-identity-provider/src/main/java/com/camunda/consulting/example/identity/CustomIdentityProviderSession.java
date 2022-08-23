package com.camunda.consulting.example.identity;

import org.camunda.bpm.engine.BadUserRequestException;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.GroupQuery;
import org.camunda.bpm.engine.identity.NativeUserQuery;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.TenantQuery;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomIdentityProviderSession implements ReadOnlyIdentityProvider {
  private final CustomIdentityService identityService;

  public CustomIdentityProviderSession(CustomIdentityService identityService) {this.identityService = identityService;}

  @Override
  public void flush() {
    // nothing to do
  }

  @Override
  public void close() {
    // nothing to do
  }

  @Override
  public User findUserById(String userId) {
    return identityService
        .findUserById(userId)
        .map(EntityMapper::map)
        .orElse(null);
  }

  @Override
  public UserQuery createUserQuery() {
    return new CustomUserQuery(Context
        .getProcessEngineConfiguration()
        .getCommandExecutorTxRequired());
  }

  @Override
  public UserQuery createUserQuery(CommandContext commandContext) {
    return new CustomUserQuery();
  }

  @Override
  public NativeUserQuery createNativeUserQuery() {
    throw new BadUserRequestException("Native user queries are not supported for LDAP identity service provider.");
  }

  @Override
  public boolean checkPassword(String userId, String password) {
    return identityService.checkPassword(userId, password);
  }

  @Override
  public Group findGroupById(String groupId) {
    return identityService
        .findGroupById(groupId)
        .map(EntityMapper::map)
        .orElse(null);
  }

  @Override
  public GroupQuery createGroupQuery() {
    return new CustomGroupQuery(Context
        .getProcessEngineConfiguration()
        .getCommandExecutorTxRequired());
  }

  @Override
  public GroupQuery createGroupQuery(CommandContext commandContext) {
    return new CustomGroupQuery();
  }

  @Override
  public Tenant findTenantById(String tenantId) {
    return identityService
        .findTenantById(tenantId)
        .map(EntityMapper::map)
        .orElse(null);
  }

  @Override
  public TenantQuery createTenantQuery() {
    return new CustomTenantQuery(Context
        .getProcessEngineConfiguration()
        .getCommandExecutorTxRequired());
  }

  @Override
  public TenantQuery createTenantQuery(CommandContext commandContext) {
    return new CustomTenantQuery();
  }

  public long findUserCountByQueryCriteria(CustomUserQuery customUserQuery) {
    return findUserByQueryCriteria(customUserQuery).size();
  }

  public List<User> findUserByQueryCriteria(CustomUserQuery customUserQuery) {
    return ConditionalStreamFilter
        .with(identityService.userStream())
        .filter(customUserQuery.getId() != null,
            user -> user
                .getId()
                .equals(customUserQuery.getId())
        )
        .filter(customUserQuery.getIds() != null && customUserQuery.getIds().length > 0, user -> {
          for (String id : customUserQuery.getIds()) {
            if (user
                .getId()
                .equals(id)) {
              return true;
            }
          }
          return false;
        })
        .filter(customUserQuery.getFirstName() != null,
            user -> user
                .getFirstName()
                .equals(customUserQuery.getFirstName())
        )
        .filter(customUserQuery.getFirstNameLike() != null,
            user -> user
                .getFirstName()
                .contains(customUserQuery.getFirstName())
        )
        .filter(customUserQuery.getLastName() != null,
            user -> user
                .getLastName()
                .equals(customUserQuery.getLastName())
        )
        .filter(customUserQuery.getLastNameLike() != null,
            user -> user
                .getLastName()
                .contains(customUserQuery.getLastName())
        )
        .filter(customUserQuery.getEmail() != null,
            user -> user
                .getEmail()
                .equals(customUserQuery.getEmail())
        )
        .filter(customUserQuery.getEmailLike() != null,
            user -> user
                .getEmail()
                .contains(customUserQuery.getEmailLike())
        )
        .filter(customUserQuery.getGroupId() != null,
            user -> identityService
                .findUsersForGroup(customUserQuery.getGroupId())
                .contains(user)
        )
        .filter(customUserQuery.getTenantId() != null,
            user -> identityService
                .findUsersForTenant(customUserQuery.getTenantId())
                .contains(user)
        )
        .get()
        .map(EntityMapper::map)
        .collect(Collectors.toList());
  }

  public List<Group> findGroupByQueryCriteria(CustomGroupQuery customGroupQuery) {
    return ConditionalStreamFilter
        .with(identityService.groupStream())
        .filter(customGroupQuery.getId() != null,
            group -> group
                .getId()
                .equals(customGroupQuery.getId())
        )
        .filter(customGroupQuery.getIds() != null && customGroupQuery.getIds().length > 0, group -> {
          for (String id : customGroupQuery.getIds()) {
            if (group
                .getId()
                .equals(id)) {
              return true;
            }
          }
          return false;
        })
        .filter(customGroupQuery.getName() != null,
            group -> group
                .getName()
                .equals(customGroupQuery.getName())
        )
        .filter(customGroupQuery.getNameLike() != null,
            group -> group
                .getName()
                .contains(customGroupQuery.getNameLike())
        )
        .filter(customGroupQuery.getType() != null,
            group -> group
                .getType()
                .equals(customGroupQuery.getType())
        )
        .filter(customGroupQuery.getUserId() != null,
            group -> identityService
                .findGroupsForUser(customGroupQuery.getUserId())
                .contains(group)
        )
        .filter(customGroupQuery.getTenantId() != null,
            group -> identityService
                .findGroupsForTenant(customGroupQuery.getTenantId())
                .contains(group)
        )
        .get()
        .map(EntityMapper::map)
        .collect(Collectors.toList());
  }

  public long findGroupCountByQueryCriteria(CustomGroupQuery customGroupQuery) {
    return findGroupByQueryCriteria(customGroupQuery).size();
  }

  public long findTenantCountByQueryCriteria(CustomTenantQuery customTenantQuery) {
    return findTenantByQueryCriteria(customTenantQuery).size();
  }

  public List<Tenant> findTenantByQueryCriteria(CustomTenantQuery customTenantQuery) {
    return ConditionalStreamFilter
        .with(identityService.tenantStream())
        .filter(customTenantQuery.getId() != null,
            tenant -> tenant
                .getId()
                .equals(customTenantQuery.getId())
        )
        .filter(customTenantQuery.getIds() != null && customTenantQuery.getIds().length > 0, tenant -> {
          for (String id : customTenantQuery.getIds()) {
            if (tenant
                .getId()
                .equals(id)) {
              return true;
            }
          }
          return false;
        })
        .filter(customTenantQuery.getName() != null,
            tenant -> tenant
                .getName()
                .equals(customTenantQuery.getName())
        )
        .filter(customTenantQuery.getNameLike() != null,
            tenant -> tenant
                .getName()
                .contains(customTenantQuery.getNameLike())
        )
        .filter(customTenantQuery.getUserId() != null,
            tenant -> identityService
                .findTenantsForUser(customTenantQuery.getUserId())
                .contains(tenant)
        )
        .filter(customTenantQuery.getGroupId() != null,
            tenant -> identityService
                .findTenantsForGroup(customTenantQuery.getGroupId())
                .contains(tenant)
        )
        .get()
        .map(EntityMapper::map)
        .collect(Collectors.toList());
  }

  private static class ConditionalStreamFilter<T> {
    private Stream<T> stream;

    private ConditionalStreamFilter(Stream<T> stream) {
      this.stream = stream;
    }

    public ConditionalStreamFilter<T> filter(boolean doFilter, Predicate<T> filter) {
      if (doFilter) {
        stream = stream.filter(filter);
      }
      return this;
    }

    public Stream<T> get() {
      return stream;
    }

    public static <T> ConditionalStreamFilter<T> with(Stream<T> stream) {
      return new ConditionalStreamFilter<>(stream);
    }
  }
}
