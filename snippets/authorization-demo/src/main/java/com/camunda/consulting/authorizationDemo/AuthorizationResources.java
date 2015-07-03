package com.camunda.consulting.authorizationDemo;

import org.camunda.bpm.engine.authorization.Resource;

public class AuthorizationResources {

  public static Resource resource = new Resource() {

    @Override
    public int resourceType() {
      return 6;
    }

    @Override
    public String resourceName() {
      return "Process Definition";
    }
  };

}
