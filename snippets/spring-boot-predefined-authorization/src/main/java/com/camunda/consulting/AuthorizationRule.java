package com.camunda.consulting;

import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Resource;

public class AuthorizationRule {
  private int type;
  private Resource resource;
  private String user;
  private String group;
  private Set<Permission> permissions = new HashSet<>();
  private String resourceId;

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Resource getResource() {
    return this.resource;
  }

  public void setResource(Resource resource) {
    this.resource = resource;
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

  public Set<Permission> getPermissions() {
    return this.permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
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
      .append("AuthorizationRule [type=")
      .append(this.type)
      .append(", resource=")
      .append(this.resource)
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
