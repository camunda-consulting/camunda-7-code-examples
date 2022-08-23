package com.camunda.consulting.example.identity;

import org.junit.jupiter.api.Test;

public class CustomIdentityServiceTest {

  @Test
  public void shouldWork() {

    CustomIdentityService service = new CustomIdentityService();
    service.save(demo());
    service.save(admin());
    service.assignGroupMembershipForUser(demo().getId(), admin().getId());
  }

  public static CustomUserEntity demo(){
    CustomUserEntity demo = new CustomUserEntity();
    demo.setId("demo");
    demo.setFirstName("Demo");
    demo.setLastName("Demo");
    demo.setEmail("demo@demo.org");
    demo.setPassword("demo");
    return demo;
  }

  public static CustomGroupEntity admin(){
    CustomGroupEntity admin = new CustomGroupEntity();
    admin.setId("admin");
    admin.setName("Admin");
    admin.setType("SYSTEM");
    return admin;
  }
}
