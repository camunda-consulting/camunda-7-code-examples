package org.camunda.bpm.examples;

import javax.enterprise.inject.Specializes;

import org.camunda.bpm.examples.AuthenticationService;

/**
 * Replace the MockImplementation by some implementation which can be steered easily from the test case
 * using CDI specialization
 * 
 * @author ruecker
 */
@Specializes
public class MockAuthenticationService extends AuthenticationService {
  
  public static String loggedInUser; 
  
  @Override
  public String getLoggedInUser() {
    return loggedInUser;
  }

}
