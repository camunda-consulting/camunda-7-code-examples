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
  private String endTime;
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
  
  /**
   * Gets an array to deal with javascript function to indicate incidents with a red circle
   * instead of a green one. <p>
   * The javascript-part is from file app/cockpit/directives/stateCircle.js 
   * in camunda-webapp.<p>
   * The html-part is 
   * <code>&lt;div state-circle incidents="businessdata.incidents"&gt;&lt/div&gt;</code>
   * 
   * @return array with one String which is queried in stateCircle.js as part of the response.
   */
  public String[] getIncidents() {
    if (incState == null) {
      return null;
    } else {
      return new String[]{"indicate that an Inc has occured"};
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
  /**
   * If end_time_ is null, then the process instance is still active.
   * To show the active lines at the top of the result, the sql statement
   * coalesce the null value to a high date.
   * @return 'active' or endTime
   */
  public String getEndTime() {
    if (this.endTime != null 
          && this.endTime.startsWith("9999-12-31")) {
      return "active";
    } else {
      return endTime;
    }
  }
  public void setEndTime(String endTime) {
    this.endTime = endTime;
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
        endTime + " " +
        activity + " " +
        assignee;
  }
  

}
