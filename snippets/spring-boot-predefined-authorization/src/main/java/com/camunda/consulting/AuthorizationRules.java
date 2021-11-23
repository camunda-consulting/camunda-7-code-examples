package com.camunda.consulting;

import java.util.Set;

public class AuthorizationRules {
  private final Set<AuthorizationRule> rules;

  public AuthorizationRules(Set<AuthorizationRule> rules) {
    this.rules = rules;
  }

  public Set<AuthorizationRule> getRules() {
    return this.rules;
  }

}
