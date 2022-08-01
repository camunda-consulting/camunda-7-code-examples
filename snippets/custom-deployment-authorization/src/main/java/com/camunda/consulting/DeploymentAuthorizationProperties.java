package com.camunda.consulting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class DeploymentAuthorizationProperties {
  private DeploymentAuthorizationListing bpmn = new DeploymentAuthorizationListing();
  private DeploymentAuthorizationListing dmn = new DeploymentAuthorizationListing();
  private DeploymentAuthorizationListing drd = new DeploymentAuthorizationListing();

  public DeploymentAuthorizationListing getBpmn() {
    return bpmn;
  }

  public void setBpmn(DeploymentAuthorizationListing bpmn) {
    this.bpmn = bpmn;
  }

  public DeploymentAuthorizationListing getDmn() {
    return dmn;
  }

  public void setDmn(DeploymentAuthorizationListing dmn) {
    this.dmn = dmn;
  }

  public DeploymentAuthorizationListing getDrd() {
    return drd;
  }

  public void setDrd(DeploymentAuthorizationListing drd) {
    this.drd = drd;
  }

  public static class DeploymentAuthorizationListing {
    private Map<String, Set<String>> byUser = new HashMap<>();
    private Map<String, Set<String>> byGroup = new HashMap<>();

    public Map<String, Set<String>> getByUser() {
      return byUser;
    }

    public void setByUser(Map<String, Set<String>> byUser) {
      this.byUser = byUser;
    }

    public Map<String, Set<String>> getByGroup() {
      return byGroup;
    }

    public void setByGroup(Map<String, Set<String>> byGroup) {
      this.byGroup = byGroup;
    }
  }
}
