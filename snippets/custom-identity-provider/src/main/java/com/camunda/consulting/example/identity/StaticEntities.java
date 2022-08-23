package com.camunda.consulting.example.identity;

public class StaticEntities {
  public static CustomUserEntity demo() {
    CustomUserEntity demo = new CustomUserEntity();
    demo.setId("demo");
    demo.setFirstName("Demo");
    demo.setLastName("Demo");
    demo.setEmail("demo@demo.org");
    demo.setPassword("demo");
    return demo;
  }

  public static CustomGroupEntity admin() {
    CustomGroupEntity admin = new CustomGroupEntity();
    admin.setId("admin");
    admin.setName("Admin");
    admin.setType("SYSTEM");
    return admin;
  }

  public static CustomTenantEntity sales() {
    CustomTenantEntity sales = new CustomTenantEntity();
    sales.setName("Sales");
    sales.setId("sales");
    return sales;
  }
}
