package com.camunda.consulting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.GroupQueryImpl;
import org.camunda.bpm.engine.impl.UserQueryImpl;
import org.camunda.bpm.identity.impl.ldap.LdapGroupEntity;
import org.camunda.bpm.identity.impl.ldap.LdapUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticIdentityProvider {
  private static final Logger LOG = LoggerFactory.getLogger(StaticIdentityProvider.class);
  private static final StaticIdentityProvider INSTANCE = new StaticIdentityProvider();
  private final Map<String, User> users = new HashMap<>();
  private final Map<String, Group> groups = new HashMap<>();
  private final Map<String, Set<String>> usersInGroups = new HashMap<>();
  private final Map<String, Set<String>> groupsWithUsers = new HashMap<>();

  private StaticIdentityProvider() {}

  public static StaticIdentityProvider getInstance() {
    return INSTANCE;
  }

  public static void reset() {
    INSTANCE.users.clear();
    INSTANCE.groups.clear();
    INSTANCE.usersInGroups.clear();
    INSTANCE.groupsWithUsers.clear();
  }

  public List<User> findAllUsers() {
    return List.copyOf(users.values());
  }

  public List<Group> findAllGroups() {
    return List.copyOf(groups.values());
  }

  public User findUserById(String id) {
    return users.get(id);
  }

  public Group findGroupById(String id) {
    return groups.get(id);
  }

  public List<User> findUsersByQuery(UserQueryImpl query) {
    // TODO improve query impl
    return users.values().stream()
        .filter(equalsIfPresent(User::getId, query.getId()))
        .filter(containsIfPresent(User::getId, query.getIds()))
        .filter(equalsIfPresent(User::getFirstName, query.getFirstName()))
        .filter(likeIfPresent(User::getFirstName, query.getFirstNameLike()))
        .filter(equalsIfPresent(User::getLastName, query.getLastName()))
        .filter(likeIfPresent(User::getLastName, query.getLastNameLike()))
        .filter(equalsIfPresent(User::getEmail, query.getEmail()))
        .filter(likeIfPresent(User::getEmail, query.getEmailLike()))
        .filter(
            ifPresent(
                query.getGroupId(),
                (user ->
                    usersInGroups.containsKey(user.getId())
                        && usersInGroups.get(user.getId()).contains(query.getGroupId()))))
        .skip(query.getFirstResult())
        .limit(query.getMaxResults())
        .toList();
  }

  private <T, C> Predicate<T> equalsIfPresent(Function<T, C> getter, C criteria) {
    return ifPresent(criteria, t -> Objects.equals(getter.apply(t), criteria));
  }

  private <T> Predicate<T> likeIfPresent(Function<T, String> getter, String criteria) {
    return ifPresent(
        criteria,
        t -> {
          String value = getter.apply(t);
          // if value is present
          if (value != null) {
            // value is like criteria
            return value.toLowerCase().contains(criteria.toLowerCase());
          }
          // no value present, value unlike criteria
          return false;
        });
  }

  private <T, C> Predicate<T> containsIfPresent(Function<T, C> getter, C[] criteria) {
    return ifPresent(criteria, t -> Arrays.asList(criteria).contains(getter.apply(t)));
  }

  private <T> Predicate<T> ifPresent(Object criteria, Predicate<T> predicate) {
    return t -> {
      if (criteria != null) {
        return predicate.test(t);
      }
      return true;
    };
  }

  public List<Group> findGroupsByQuery(GroupQueryImpl query) {
    // TODO improve query impl
    return groups.values().stream()
        .filter(equalsIfPresent(Group::getId, query.getId()))
        .filter(containsIfPresent(Group::getId, query.getIds()))
        .filter(equalsIfPresent(Group::getName, query.getName()))
        .filter(likeIfPresent(Group::getName, query.getNameLike()))
        .filter(equalsIfPresent(Group::getType, query.getType()))
        .filter(
            ifPresent(
                query.getUserId(),
                group ->
                    groupsWithUsers.containsKey(group.getId())
                        && groupsWithUsers.get(group.getId()).contains(query.getUserId())))
        .skip(query.getFirstResult())
        .limit(query.getMaxResults())
        .toList();
  }

  public User createUser(
      String id, String password, String firstName, String lastName, String email) {
    if (id == null || password == null) {
      throw new IllegalStateException("Cannot create user: id and password must be set");
    }
    if (users.containsKey(id)) {
      throw new IllegalStateException("User with id '" + id + "' already exists");
    }

    UserImpl user = new UserImpl();
    user.id = id;
    user.firstName = (firstName);
    user.lastName = (lastName);
    user.email = (email);
    user.password = (password);
    users.put(id, user);
    LOG.info("Created user {}", user);
    return user;
  }

  public Group createGroup(String id, String name, String type) {
    if (groups.containsKey(id)) {
      throw new IllegalStateException("Group with id '" + id + "' already exists");
    }
    GroupImpl group = new GroupImpl();
    group.id = (id);
    group.name = (name);
    group.type = (type);
    groups.put(id, group);
    LOG.info("Created group {}", group);
    return group;
  }

  public void createAssignment(String userId, String groupId) {
    if (!users.containsKey(userId)) {
      throw new IllegalStateException("User with id '" + userId + "' does not exist");
    }
    if (!groups.containsKey(groupId)) {
      throw new IllegalStateException("Group with id '" + groupId + "' does not exist");
    }
    usersInGroups.computeIfAbsent(userId, s -> new HashSet<>()).add(groupId);
    groupsWithUsers.computeIfAbsent(groupId, s -> new HashSet<>()).add(userId);
    LOG.info("Created assignment {} - {}", userId, groupId);
  }

  public List<Group> getGroupAssignments(String userId) {
    return usersInGroups.get(userId).stream().map(groups::get).toList();
  }

  public List<User> getUserAssignments(String groupId) {
    return groupsWithUsers.get(groupId).stream().map(users::get).toList();
  }

  public boolean checkPassword(String userId, String password) {
    if (userId == null || password == null) {
      return false;
    }
    User user = findUserById(userId);
    if (user == null) {
      return false;
    }
    return Objects.equals(user.getPassword(), password);
  }

  public static class UserImpl extends LdapUserEntity implements User {
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    @Override
    public String getId() {
      return id;
    }

    @Override
    public void setId(String id) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String getFirstName() {
      return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String getLastName() {
      return lastName;
    }

    @Override
    public void setLastName(String lastName) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String getEmail() {
      return email;
    }

    @Override
    public void setEmail(String email) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String getPassword() {
      return password;
    }

    @Override
    public void setPassword(String password) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String toString() {
      return "UserImpl{"
          + "id='"
          + id
          + '\''
          + ", firstName='"
          + firstName
          + '\''
          + ", lastName='"
          + lastName
          + '\''
          + ", password='"
          + password
          + '\''
          + ", email='"
          + email
          + '\''
          + '}';
    }
  }

  public static class GroupImpl extends LdapGroupEntity implements Group {
    private String id;
    private String name;
    private String type;

    @Override
    public String getId() {
      return id;
    }

    @Override
    public void setId(String id) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public void setName(String name) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String getType() {
      return type;
    }

    @Override
    public void setType(String type) {
      throw new IllegalStateException("Immutable");
    }

    @Override
    public String toString() {
      return "GroupImpl{"
          + "id='"
          + id
          + '\''
          + ", name='"
          + name
          + '\''
          + ", type='"
          + type
          + '\''
          + '}';
    }
  }
}
