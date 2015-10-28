package org.camunda.bpm.demo.cockpit.plugin.kpi.dto;

public class ProcessInstanceCountDto {

  private String processDefinitionKey;

  private int runningInstanceCount;
  private int endedInstanceCount;
  private int failedInstanceCount;
  
  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }
  
  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }
  
  public int getRunningInstanceCount() {
    return runningInstanceCount;
  }
  
  public void setRunningInstanceCount(int runningInstanceCount) {
    this.runningInstanceCount = runningInstanceCount;
  }
  
  public int getEndedInstanceCount() {
    return endedInstanceCount;
  }
  
  public void setEndedInstanceCount(int endedInstanceCount) {
    this.endedInstanceCount = endedInstanceCount;
  }
  
  public int getFailedInstanceCount() {
    return failedInstanceCount;
  }
  
  public void setFailedInstanceCount(int failedInstanceCount) {
    this.failedInstanceCount = failedInstanceCount;
  }

}
