package com.camunda.consulting;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Objects;

@Document(indexName = "task-data")
public class TaskData {
  private String orderNumber;
  private String workStep;
  private String singleProcess;
  private String bankCode;
  @Id
  private String taskId;

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

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}
    TaskData taskData = (TaskData) o;
    return Objects.equals(orderNumber, taskData.orderNumber) && Objects.equals(workStep,
        taskData.workStep
    ) && Objects.equals(singleProcess, taskData.singleProcess) && Objects.equals(bankCode,
        taskData.bankCode
    ) && Objects.equals(taskId, taskData.taskId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderNumber, workStep, singleProcess, bankCode, taskId);
  }
}
