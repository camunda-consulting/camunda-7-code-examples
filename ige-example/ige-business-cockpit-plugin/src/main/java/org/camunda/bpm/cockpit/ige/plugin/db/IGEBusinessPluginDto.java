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
  private Integer titleNumber;
  private Integer requestNumber;
  
  public IGEBusinessPluginDto() {
  }
  
  public IGEBusinessPluginDto(MultivaluedMap<String, String> queryParameters) {
    if (queryParameters.containsKey("requestnumber")) {
      Integer requestNum;
      try {
        requestNum = new Integer(queryParameters.getFirst("requestnumber"));
      } catch (NumberFormatException e) {
        log.warning("let requestnumber be empty: NumberFormatException " + e.getMessage());
        requestNum = null;
      }
      this.requestNumber = requestNum;
    }
    if (queryParameters.containsKey("titlenumber")) {
      Integer titleNum;
      try {
        titleNum = new Integer(queryParameters.getFirst("titlenumber"));
      } catch (NumberFormatException e) {
        log.warning("let titlenumber be empty: NumberFormatException " + e.getMessage());
        titleNum = null;
      }
      this.titleNumber = titleNum;
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
    return runState;
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
  public Integer getTitleNumber() {
    return titleNumber;
  }
  public void setTitleNumber(Integer titleNumber) {
    this.titleNumber = titleNumber;
  }
  public Integer getRequestNumber() {
    return requestNumber;
  }
  public void setRequestNumber(Integer requestNumber) {
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
