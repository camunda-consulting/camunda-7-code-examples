package com.camunda.consulting.example.identity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomIdentityService {
  private final Set<CustomUserEntity> users = new HashSet<>();
  private final Set<CustomGroupEntity> groups = new HashSet<>();
  private final Set<CustomTenantEntity> tenants = new HashSet<>();

  private final Map<String, Set<String>> userGroupMemberships = new HashMap<>();
  private final Map<String, Set<String>> userTenantMemberships = new HashMap<>();
  private final Map<String, Set<String>> groupTenantMemberships = new HashMap<>();

  @PostConstruct
  public void init() {
    save(StaticEntities.demo());
    save(StaticEntities.admin());
    save(StaticEntities.sales());
    assignGroupMembershipForUser(
        StaticEntities
            .demo()
            .getId(),
        StaticEntities
            .admin()
            .getId()
    );
    assignTenantMembershipForGroup(
        StaticEntities
            .admin()
            .getId(),
        StaticEntities
            .sales()
            .getId()
    );
  }

  public Optional<CustomUserEntity> findUserById(String id) {
    return userStream()
        .filter(byId(id))
        .findFirst();
  }

  public Optional<CustomGroupEntity> findGroupById(String id) {
    return groupStream()
        .filter(byId(id))
        .findFirst();
  }

  public Optional<CustomTenantEntity> findTenantById(String id) {
    return tenantStream()
        .filter(byId(id))
        .findFirst();
  }

  public Stream<CustomUserEntity> userStream() {
    return users.stream();
  }

  public Stream<CustomGroupEntity> groupStream() {
    return groups.stream();
  }

  public Stream<CustomTenantEntity> tenantStream() {
    return tenants.stream();
  }

  public Set<CustomGroupEntity> findGroupsForUser(String userId) {
    return findUserById(userId)
        .map(user -> userGroupMemberships
            .getOrDefault(user.getId(), new HashSet<>())
            .stream()
            .map(groupId -> groups
                .stream()
                .filter(group -> group
                    .getId()
                    .equals(groupId))
                .findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet()))
        .orElseGet(Set::of);
  }

  public Set<CustomTenantEntity> findTenantsForUser(String userId) {
    return findUserById(userId)
        .map(user -> userTenantMemberships
            .getOrDefault(user.getId(), new HashSet<>())
            .stream()
            .map(tenantId -> tenants
                .stream()
                .filter(tenant -> tenant
                    .getId()
                    .equals(tenantId))
                .findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet()))
        .orElseGet(Set::of);
  }

  public Set<CustomUserEntity> findUsersForGroup(String groupId) {
    return findGroupById(groupId)
        .map(group -> this.userGroupMemberships
            .entrySet()
            .stream()
            .filter(e -> e
                .getValue()
                .contains(group.getId()))
            .map(Entry::getKey)
            .map(userId -> users
                .stream()
                .filter(user -> user
                    .getId()
                    .equals(userId))
                .findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet()))
        .orElseGet(Set::of);
  }

  public Set<CustomUserEntity> findUsersForTenant(String tenantId) {
    return findTenantById(tenantId)
        .map(tenant -> userTenantMemberships
            .entrySet()
            .stream()
            .filter(e -> e
                .getValue()
                .contains(tenant.getId()))
            .map(Entry::getKey)
            .map(userId -> users
                .stream()
                .filter(user -> user
                    .getId()
                    .equals(userId))
                .findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet()))
        .orElseGet(Set::of);

  }

  public Set<CustomGroupEntity> findGroupsForTenant(String tenantId) {
    return findTenantById(tenantId)
        .map(tenant -> groupTenantMemberships
            .entrySet()
            .stream()
            .filter(e -> e
                .getValue()
                .contains(tenant.getId()))
            .map(Entry::getKey)
            .map(groupId -> groups
                .stream()
                .filter(group -> group
                    .getId()
                    .equals(groupId))
                .findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet()))
        .orElseGet(Set::of);
  }

  public Set<CustomTenantEntity> findTenantsForGroup(String groupId) {
    return findGroupById(groupId)
        .map(group -> groupTenantMemberships
            .getOrDefault(group.getId(), new HashSet<>())
            .stream()
            .map(tenantId -> tenants
                .stream()
                .filter(tenant -> tenant
                    .getId()
                    .equals(tenantId))
                .findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet()))
        .orElseGet(Set::of);
  }

  private Predicate<IdentifiedEntity> byId(String id) {
    return user -> user
        .getId()
        .equals(id);
  }

  public boolean checkPassword(String userId, String password) {
    return findUserById(userId)
        .map(user -> user
            .getPassword()
            .equals(password))
        .orElse(false);
  }

  public void save(CustomUserEntity user) {
    users.remove(user);
    users.add(user);
  }

  public void save(CustomGroupEntity group) {
    groups.remove(group);
    groups.add(group);
  }

  public void save(CustomTenantEntity tenant) {
    tenants.remove(tenant);
    tenants.add(tenant);
  }

  public void assignGroupMembershipForUser(String userId, String groupId) {
    findUserById(userId).ifPresentOrElse(
        user -> findGroupById(groupId).ifPresentOrElse(group -> userGroupMemberships
            .computeIfAbsent(user.getId(), id -> new HashSet<>())
            .add(group.getId()), throwAction("Group with id " + groupId + " does not exist")),
        throwAction("User with id " + userId + " does not exist")
    );
  }

  public void assignTenantMembershipForUser(String userId, String tenantId) {
    findUserById(userId).ifPresentOrElse(
        user -> findTenantById(tenantId).ifPresentOrElse(tenant -> userTenantMemberships
            .computeIfAbsent(user.getId(), id -> new HashSet<>())
            .add(tenant.getId()), throwAction("Tenant with id " + tenantId + " does not exist")),
        throwAction("User with id " + userId + " does not exist")
    );
  }

  public void assignTenantMembershipForGroup(String groupId, String tenantId) {
    findGroupById(groupId).ifPresentOrElse(
        group -> findTenantById(tenantId).ifPresentOrElse(tenant -> groupTenantMemberships
            .computeIfAbsent(group.getId(), id -> new HashSet<>())
            .add(tenant.getId()), throwAction("Tenant with id " + tenantId + " does not exist")),
        throwAction("Group with id " + groupId + " does not exist")
    );
  }

  private Runnable throwAction(String message) {
    return () -> {
      throw new RuntimeException(message);
    };
  }

}
