package com.camunda.consulting;

public class TaskDataDto {
  private String taskId;
  private String orderNumber;
  private String workStep;
  private String singleProcess;
  private String bankCode;

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getWorkStep() {
    return workStep;
  }

  public void setWorkStep(String workStep) {
    this.workStep = workStep;
  }

  public String getSingleProcess() {
    return singleProcess;
  }

  public void setSingleProcess(String singleProcess) {
    this.singleProcess = singleProcess;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

}
