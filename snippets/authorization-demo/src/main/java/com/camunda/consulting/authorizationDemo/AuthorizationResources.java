package com.camunda.consulting.authorizationDemo;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.authorization.Resource;

public class AuthorizationResources {
  
  public static Map<String, Resource> resources = new HashMap<String, Resource>();
  
  static {
    resources.put("authorization-demo-management", new Resource() {
      
      @Override
      public int resourceType() {
        return 10;
      }
      
      @Override
      public String resourceName() {
        return "authorization-demo-management";
      }
    });
    
    resources.put("authorization-demo-sales", new Resource() {
      
      @Override
      public int resourceType() {
        return 11;
      }
      
      @Override
      public String resourceName() {
        return "authorization-demo-sales";
      }
    });
  }

  public static final String AUTH_DEMO_MANAGEMENT_RESOURCE = InMemoryH2Test.PROCESS_DEFINITION_KEY + "-management";
  public static final String AUTH_DEMO_SALES_RESOURCE = InMemoryH2Test.PROCESS_DEFINITION_KEY + "-sales";

}
