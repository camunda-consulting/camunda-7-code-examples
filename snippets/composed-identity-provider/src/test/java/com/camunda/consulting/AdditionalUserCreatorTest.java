package com.camunda.consulting;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Properties;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdditionalUserCreatorTest {

  @BeforeEach
  void cleanUp() {
    StaticIdentityProvider.reset();
  }

  @Test
  void shouldCreateUsers() {
    Properties properties = new Properties();
    properties.setProperty("user.demo.password", "demo");
    properties.setProperty("user.demo.firstName", "demo");
    AdditionalUserCreator.getInstance().createUsers(properties);
    User demo = StaticIdentityProvider.getInstance().findUserById("demo");
    assertThat(demo)
        .isNotNull()
        .matches(u -> u.getPassword().equals("demo"))
        .matches(u -> u.getFirstName().equals("demo"));
  }

  @Test
  void shouldCreateGroups() {
    Properties properties = new Properties();
    properties.setProperty("group.demo.name", "demo");
    properties.setProperty("group.demo.type", "SYSTEM");
    AdditionalUserCreator.getInstance().createUsers(properties);
    Group group = StaticIdentityProvider.getInstance().findGroupById("demo");
    assertThat(group).isNotNull().matches(g -> g.getName().equals("demo"));
  }

  @Test
  void shouldCreateAssignments() {
    Properties properties = new Properties();
    properties.setProperty("user.demo.password", "demo");
    properties.setProperty("group.demoGroup.name", "demo");
    properties.setProperty("group.demoGroup.type", "SYSTEM");
    properties.setProperty("assignment.demo", "demoGroup");
    AdditionalUserCreator.getInstance().createUsers(properties);
    List<Group> assignments = StaticIdentityProvider.getInstance().getGroupAssignments("demo");
    assertThat(assignments).hasSize(1).element(0).matches(g -> g.getId().equals("demoGroup"));
  }

  @Test
  void shouldIgnoreInvalidProperty() {
    Properties properties = new Properties();
    properties.setProperty("user.demo.password", "demo");
    properties.setProperty("user.demo.firstname", "demo");
    AdditionalUserCreator.getInstance().createUsers(properties);
    User demo = StaticIdentityProvider.getInstance().findUserById("demo");
    assertThat(demo)
        .isNotNull()
        .matches(u -> u.getPassword().equals("demo"))
        .matches(u -> u.getFirstName() == null);
  }
}
