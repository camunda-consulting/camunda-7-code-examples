package com.camunda.consulting;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("camunda.bpm.authorization")
public class AuthorizationProperties {
  private Set<AuthorizationRuleProperties> rules;

  public void setRules(Set<AuthorizationRuleProperties> rules) {
    this.rules = rules;
  }

  public Set<AuthorizationRuleProperties> getRuleProperties() {
    return this.rules;
  }

  public static class AuthorizationRuleProperties {
    private String resource;
    private String type;
    private String user;
    private String group;
    private Set<String> permissions;
    private String resourceId;

    public String getResource() {
      return this.resource;
    }

    public void setResource(String resource) {
      this.resource = resource;
    }

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getUser() {
      return this.user;
    }

    public void setUser(String user) {
      this.user = user;
    }

    public String getGroup() {
      return this.group;
    }

    public void setGroup(String group) {
      this.group = group;
    }

    public Set<String> getPermissions() {
      return this.permissions;
    }

    public void setPermissions(Set<String> permissions) {
      this.permissions = permissions;
    }

    public String getResourceId() {
      return this.resourceId;
    }

    public void setResourceId(String resourceId) {
      this.resourceId = resourceId;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder
        .append("AuthorizationRule [resource=")
        .append(this.resource)
        .append(", type=")
        .append(this.type)
        .append(", user=")
        .append(this.user)
        .append(", group=")
        .append(this.group)
        .append(", permissions=")
        .append(this.permissions)
        .append(", resourceId=")
        .append(this.resourceId)
        .append("]");
      return builder.toString();
    }

  }

}
