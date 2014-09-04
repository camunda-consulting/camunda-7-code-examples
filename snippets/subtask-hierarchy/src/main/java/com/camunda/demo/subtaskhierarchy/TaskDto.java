package com.camunda.demo.subtaskhierarchy;

public class TaskDto {

  private String id;
  private String name;
  private String assignee;
  private String dueDate;
  private long hierarchyLevel;
  private boolean ended;
  private String candidates;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public long getHierarchyLevel() {
    return hierarchyLevel;
  }

  public void setHierarchyLevel(long hierarchyLevel) {
    this.hierarchyLevel = hierarchyLevel;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setEnded(boolean ended) {
    this.ended = ended;

  }

  public boolean isEnded() {
    return ended;
  }

  public void setCandidates(String candidates) {
    this.candidates = candidates;    
  }

  public String getCandidates() {
    return candidates;
  }

}
