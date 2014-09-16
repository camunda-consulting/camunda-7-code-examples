package com.camunda.consulting.tasklist.fulltext.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity to collect some business values of a task and allow a fast query
 * without join to these values or a full text query to the CLOB column.
 * 
 * @author Ingo Richtsmeier
 *
 */
@Entity
@Table(name = "USER_TASK")
public class UserTask {

  String id;
  String name;
  String taskId;
  String taskDefinitionKey;
  String processInstanceId;
  String processDefinitionId;
  String candidates;
  String assignee;
  String businessKey;
  Date createTime;
  
  // business attributes
  String incidentActivityId;
  String incidentProcessDefinitionId;
  String incidentJobId;
  String incidentExecutionId;
  String incidentProcessInstanceId;
  String incidentMessage;
  String incidentException;
  
  @Id
  @Column(name = "ID_")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  @Column(name = "NAME_")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  @Column(name = "TASK_ID_")
  public String getTaskId() {
    return taskId;
  }
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
  @Column(name = "TASK_DEF_ID_")
  public String getTaskDefinitionKey() {
    return taskDefinitionKey;
  }
  public void setTaskDefinitionKey(String taskDefinitionKey) {
    this.taskDefinitionKey = taskDefinitionKey;
  }
  @Column(name = "PROC_INST_ID_")
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
  @Column(name = "PROC_DEF_ID_")
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }
  @Column(name = "CANDIDATES_", length=1024)
  public String getCandidates() {
    return candidates;
  }
  public void setCandidates(String candidates) {
    this.candidates = candidates;
  }
  @Column(name = "ASSIGNEE_")
  public String getAssignee() {
    return assignee;
  }
  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }
  @Column(name = "BUSINESS_KEY_")
  public String getBusinessKey() {
    return businessKey;
  }
  public void setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
  }
  @Column(name = "CREATE_TIME_")
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  @Column(name = "INC_ACTIVITY_ID_")
  public String getIncidentActivityId() {
    return incidentActivityId;
  }
  public void setIncidentActivityId(String incidentActivityId) {
    this.incidentActivityId = incidentActivityId;
  }
  @Column(name = "INC_PROC_DEF_ID_")
  public String getIncidentProcessDefinitionId() {
    return incidentProcessDefinitionId;
  }
  public void setIncidentProcessDefinitionId(String incidentProcessDefinitionId) {
    this.incidentProcessDefinitionId = incidentProcessDefinitionId;
  }
  @Column(name = "INC_JOB_ID_")
  public String getIncidentJobId() {
    return incidentJobId;
  }
  public void setIncidentJobId(String incidentJobId) {
    this.incidentJobId = incidentJobId;
  }
  @Column(name = "INC_EXECUTION_ID_")
  public String getIncidentExecutionId() {
    return incidentExecutionId;
  }
  public void setIncidentExecutionId(String incidentExecutionId) {
    this.incidentExecutionId = incidentExecutionId;
  }
  @Column(name = "INC_PROC_INST_ID_")
  public String getIncidentProcessInstanceId() {
    return incidentProcessInstanceId;
  }
  public void setIncidentProcessInstanceId(String incidentProcessInstanceId) {
    this.incidentProcessInstanceId = incidentProcessInstanceId;
  }
  @Column(name = "INC_MESSAGE_", length = 4000)
  public String getIncidentMessage() {
    return incidentMessage;
  }
  public void setIncidentMessage(String incidentMessage) {
    this.incidentMessage = incidentMessage;
  }
  @Column(name = "INC_EXCEPTION_", columnDefinition="CLOB")
  public String getIncidentException() {
    return incidentException;
  }
  public void setIncidentException(String incidentException) {
    this.incidentException = incidentException;
  }
}
