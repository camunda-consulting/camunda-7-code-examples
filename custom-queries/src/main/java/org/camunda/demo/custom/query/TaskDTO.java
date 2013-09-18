package org.camunda.demo.custom.query;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Your own DTO class used to retrieve results with additional data in one query
 */
public class TaskDTO {

  private String id;
  private String nameWithoutCascade;
  private String parentTaskIdWithoutCascade;
  private String descriptionWithoutCascade;
  private int priorityWithoutCascade;
  private Date createTime;
  private String ownerWithoutCascade;
  private String assigneeWithoutCascade;
  private String delegationStateString;
  private String executionId;
  private String processInstanceId;
  private String processDefinitionId;
  private String taskDefinitionKeyWithoutCascade;
  private Date dueDateWithoutCascade;

  private Customer customer;

  private List<ProcessVariableDTO> variables = new ArrayList<ProcessVariableDTO>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNameWithoutCascade() {
    return nameWithoutCascade;
  }

  public void setNameWithoutCascade(String nameWithoutCascade) {
    this.nameWithoutCascade = nameWithoutCascade;
  }

  public String getParentTaskIdWithoutCascade() {
    return parentTaskIdWithoutCascade;
  }

  public void setParentTaskIdWithoutCascade(String parentTaskIdWithoutCascade) {
    this.parentTaskIdWithoutCascade = parentTaskIdWithoutCascade;
  }

  public String getDescriptionWithoutCascade() {
    return descriptionWithoutCascade;
  }

  public void setDescriptionWithoutCascade(String descriptionWithoutCascade) {
    this.descriptionWithoutCascade = descriptionWithoutCascade;
  }

  public int getPriorityWithoutCascade() {
    return priorityWithoutCascade;
  }

  public void setPriorityWithoutCascade(int priorityWithoutCascade) {
    this.priorityWithoutCascade = priorityWithoutCascade;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getOwnerWithoutCascade() {
    return ownerWithoutCascade;
  }

  public void setOwnerWithoutCascade(String ownerWithoutCascade) {
    this.ownerWithoutCascade = ownerWithoutCascade;
  }

  public String getAssigneeWithoutCascade() {
    return assigneeWithoutCascade;
  }

  public void setAssigneeWithoutCascade(String assigneeWithoutCascade) {
    this.assigneeWithoutCascade = assigneeWithoutCascade;
  }

  public String getDelegationStateString() {
    return delegationStateString;
  }

  public void setDelegationStateString(String delegationStateString) {
    this.delegationStateString = delegationStateString;
  }

  public String getExecutionId() {
    return executionId;
  }

  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }

  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  public String getTaskDefinitionKeyWithoutCascade() {
    return taskDefinitionKeyWithoutCascade;
  }

  public void setTaskDefinitionKeyWithoutCascade(String taskDefinitionKeyWithoutCascade) {
    this.taskDefinitionKeyWithoutCascade = taskDefinitionKeyWithoutCascade;
  }

  public Date getDueDateWithoutCascade() {
    return dueDateWithoutCascade;
  }

  public void setDueDateWithoutCascade(Date dueDateWithoutCascade) {
    this.dueDateWithoutCascade = dueDateWithoutCascade;
  }

  public List<ProcessVariableDTO> getVariables() {
    return variables;
  }

  public void setVariables(List<ProcessVariableDTO> variables) {
    this.variables = variables;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
