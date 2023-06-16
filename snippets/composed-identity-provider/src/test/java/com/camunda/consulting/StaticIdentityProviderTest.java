package com.camunda.consulting;

import static com.camunda.consulting.StaticIdentityProvider.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.impl.Page;
import org.camunda.bpm.engine.impl.UserQueryImpl;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

public class StaticIdentityProviderTest {

  private static User createUser(String name) {
    return getInstance()
        .createUser(
            name.toLowerCase(), name.toLowerCase(), name, name, name.toLowerCase() + "@demo.org");
  }

  private static User createUser(String firstName, String lastName) {
    String id = firstName.toLowerCase() + "." + lastName.toLowerCase();
    return getInstance().createUser(id, id, firstName, lastName, id + "@demo.org");
  }

  @BeforeEach
  void resetIdentityProvider() {
    reset();
  }

  @Test
  void shouldCreateUser() {
    User demo = createUser("Demo");
    assertThat(demo).isNotNull().hasNoNullFieldsOrProperties();
  }

  @Test
  void shouldCreateGroup() {
    Group admins = getInstance().createGroup("admins", "Admins", "SYSTEM");
    assertThat(admins).isNotNull().hasNoNullFieldsOrProperties();
  }

  @Test
  void shouldRejectDoubleUserCreation() {
    createUser("Demo");
    assertThrows(
        IllegalStateException.class,
        () -> getInstance().createUser("demo", "demo", "Demo2", "Demo2", "demo@demo.org"));
  }

  @Test
  void shouldRejectDoubleGroupCreation() {
    getInstance().createGroup("admins", "Admins", "SYSTEM");
    assertThrows(
        IllegalStateException.class,
        () -> getInstance().createGroup("admins", "Admins2", "SYSTEM"));
  }

  @Test
  void shouldCreateGroupAssignment() {
    User demo = createUser("Demo");
    Group admins = getInstance().createGroup("admins", "Admins", "SYSTEM");
    getInstance().createAssignment(demo.getId(), admins.getId());
    List<Group> demoGroups = getInstance().getGroupAssignments(demo.getId());
    assertThat(demoGroups).contains(admins);
    List<User> adminsUsers = getInstance().getUserAssignments(admins.getId());
    assertThat(adminsUsers).contains(demo);
  }

  @TestFactory
  Stream<DynamicTest> shouldApplyUserQueryParameters() {
    User demo = createUser("Demo");
    User jonathanLukas = createUser("Jonathan", "Lukas");
    User stephanHaarmann = createUser("Stephan", "Haarmann");
    User jensLindner = createUser("Jens", "Lindner");
    User ingoRichtsmeier = createUser("Ingo", "Richtsmeier");
    User hurshidKadirov = createUser("Hurshid", "Kadirov");
    User hendrikKupitz = createUser("Hendrik", "Kupitz");
    Group admin = getInstance().createGroup("admin", "Admins", "SYSTEM");
    Group clerk = getInstance().createGroup("clerk", "Clerks", "USER");
    Group manager = getInstance().createGroup("manager", "Manager", "USER");
    getInstance().createAssignment(hurshidKadirov.getId(), manager.getId());
    getInstance().createAssignment(demo.getId(), admin.getId());
    getInstance().createAssignment(jonathanLukas.getId(), clerk.getId());
    getInstance().createAssignment(stephanHaarmann.getId(), clerk.getId());
    getInstance().createAssignment(jensLindner.getId(), clerk.getId());
    getInstance().createAssignment(ingoRichtsmeier.getId(), clerk.getId());
    getInstance().createAssignment(hendrikKupitz.getId(), clerk.getId());
    return Stream.of(
        dynamicTest("Find by id", () -> findUser(userQuery().userId(demo.getId()), List.of(demo))),
        dynamicTest(
            "Find by ids",
            () ->
                findUser(
                    userQuery().userIdIn(demo.getId(), ingoRichtsmeier.getId()),
                    List.of(demo, ingoRichtsmeier))),
        dynamicTest(
            "Find by firstName",
            () ->
                findUser(
                    userQuery().userFirstName(jonathanLukas.getFirstName()),
                    List.of(jonathanLukas))),
        dynamicTest(
            "Find by firstNameLike",
            () ->
                findUser(
                    userQuery().userFirstNameLike("s"),
                    List.of(stephanHaarmann, jensLindner, hurshidKadirov))),
        dynamicTest(
            "Find by lastName",
            () ->
                findUser(
                    userQuery().userLastName(hendrikKupitz.getLastName()), List.of(hendrikKupitz))),
        dynamicTest(
            "Find by lastNameLike",
            () ->
                findUser(
                    userQuery().userLastNameLike("k"),
                    List.of(jonathanLukas, hurshidKadirov, hendrikKupitz))),
        dynamicTest(
            "Find my email",
            () ->
                findUser(
                    userQuery().userEmail(ingoRichtsmeier.getEmail()), List.of(ingoRichtsmeier))),
        dynamicTest(
            "Find by emailLike",
            () -> findUser(userQuery().userEmailLike("r@"), List.of(jensLindner, ingoRichtsmeier))),
        dynamicTest(
            "Find by groupId",
            () -> findUser(userQuery().memberOfGroup(manager.getId()), List.of(hurshidKadirov))));
  }

  private void findUser(UserQuery query, List<User> expectedUsers) {
    List<User> usersByQuery = getInstance().findUsersByQuery((UserQueryImpl) query);
    assertThat(usersByQuery).containsExactlyInAnyOrderElementsOf(expectedUsers);
  }

  private UserQuery userQuery() {
    return new UserQueryImpl() {
      @Override
      public long executeCount(CommandContext commandContext) {
        throw new IllegalStateException("executeCount must not be called");
      }

      @Override
      public List<User> executeList(CommandContext commandContext, Page page) {
        throw new IllegalStateException("executeList must not be called");
      }
    };
  }

  private <T> void applyIfPresent(T value, Consumer<T> setter) {
    if (value != null) {
      setter.accept(value);
    }
  }
}
