package com.camunda.consulting.example.identity;

import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;

public class EntityMapper {
  public static User map(CustomUserEntity user) {
    return new User() {
      @Override
      public String getId() {
        return user.getId();
      }

      @Override
      public void setId(String id) {
        user.setId(id);
      }

      @Override
      public String getFirstName() {
        return user.getFirstName();
      }

      @Override
      public void setFirstName(String firstName) {
        user.setFirstName(firstName);
      }

      @Override
      public void setLastName(String lastName) {
        user.setLastName(lastName);
      }

      @Override
      public String getLastName() {
        return user.getLastName();
      }

      @Override
      public void setEmail(String email) {
        user.setEmail(email);
      }

      @Override
      public String getEmail() {
        return user.getEmail();
      }

      @Override
      public String getPassword() {
        return user.getPassword();
      }

      @Override
      public void setPassword(String password) {
        user.setPassword(password);
      }
    };
  }

  public static Group map(CustomGroupEntity group){
    return new Group() {
      @Override
      public String getId() {
        return group.getId();
      }

      @Override
      public void setId(String id) {
        group.setId(id);
      }

      @Override
      public String getName() {
        return group.getName();
      }

      @Override
      public void setName(String name) {
        group.setName(name);
      }

      @Override
      public String getType() {
        return group.getType();
      }

      @Override
      public void setType(String string) {
        group.setType(string);
      }
    };
  }

  public static Tenant map(CustomTenantEntity tenant){
    return new Tenant() {
      @Override
      public String getId() {
        return tenant.getId();
      }

      @Override
      public void setId(String id) {
        tenant.setId(id);
      }

      @Override
      public String getName() {
        return tenant.getName();
      }

      @Override
      public void setName(String name) {
        tenant.setName(name);
      }
    };
  }
}
