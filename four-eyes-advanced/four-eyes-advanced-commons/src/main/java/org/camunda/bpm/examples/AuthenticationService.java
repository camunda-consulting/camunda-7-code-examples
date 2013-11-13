package org.camunda.bpm.examples;


import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.identity.Authentication;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Replace this by your implementation or inject the Principal in a Java EE 6 environment.
 */
@Named
public class AuthenticationService {

  @Inject
  ProcessEngine processEngine;

  public String getLoggedInUser() {
    Authentication currentAuthentication = processEngine.getIdentityService().getCurrentAuthentication();
    if (currentAuthentication.getUserId() != null) {
      return currentAuthentication.getUserId();
    }
    return "test";
  }

  public List<String> getGroupsOfLoggedInUser() {
    ArrayList<String> list = new ArrayList<String>();
    list.add("test");
    list.add("management");
    return list;
  }

}
