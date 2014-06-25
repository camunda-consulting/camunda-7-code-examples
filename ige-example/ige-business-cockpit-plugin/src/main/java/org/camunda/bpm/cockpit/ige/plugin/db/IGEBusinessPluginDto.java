package org.camunda.bpm.cockpit.ige.plugin.db;

import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedMap;

import org.camunda.bpm.engine.rest.dto.LinkableDto;

public class IGEBusinessPluginDto extends LinkableDto {
  
  private static final Logger log = Logger.getLogger(IGEBusinessPluginDto.class.getName());
  
  private String processInstanceId;
  private String processName;
  private String processDefinitionId;
  private Timestamp startTime;
  private String incState;
  private String component;
  private String runState;
  private String activity;
  private String assignee;
  private String titleNumber;
  private String requestNumber;
  
  public IGEBusinessPluginDto() {
  }
  
  public IGEBusinessPluginDto(MultivaluedMap<String, String> queryParameters) {
    if (queryParameters.containsKey("requestnumber")) {
      this.requestNumber = queryParameters.getFirst("requestnumber");
      log.fine("requestNumber = " + this.requestNumber);
    }
    if (queryParameters.containsKey("titlenumber")) {
      this.titleNumber = queryParameters.getFirst("titlenumber");
      log.fine("titleNumber = " + this.titleNumber);
    }
  }
  
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
  public String getProcessName() {
    return processName;
  }
  public void setProcessName(String processName) {
    this.processName = processName;
  }
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  public Timestamp getStartTime() {
    return startTime;
  }
  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }
  public String getIncState() {
    return incState;
  }
  public void setIncState(String incState) {
    this.incState = incState;
  }
  public String getComponent() {
    return component;
  }
  public void setComponent(String component) {
    this.component = component;
  }
  public String getRunState() {
    if (this.runState == null) {
      return "active";
    } else {
      return "completed";
    }
  }
  public void setRunState(String runState) {
    this.runState = runState;
  }
  public String getActivity() {
    return activity;
  }
  public void setActivity(String activity) {
    this.activity = activity;
  }
  public String getAssignee() {
    return assignee;
  }
  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }
  public String getTitleNumber() {
    return titleNumber;
  }
  public void setTitleNumber(String titleNumber) {
    this.titleNumber = titleNumber;
  }
  public String getRequestNumber() {
    return requestNumber;
  }
  public void setRequestNumber(String requestNumber) {
    this.requestNumber = requestNumber;
  }
  @Override
  public String toString() {
    return "DTO: " + 
        processInstanceId + " " + 
        processName + " " +
        startTime + " " + 
        incState + " " +
        component + " " + 
        runState + " " +
        activity + " " +
        assignee;
  }
  

}
