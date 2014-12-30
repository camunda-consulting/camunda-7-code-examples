package com.camunda.demo.webinar.tasklist;

import java.util.Date;

public class VacationRequest {

  private String name;
  private Date startDate;
  private long duration;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  @Override
  public String toString() {
    return "VacationRequest [name=" + name + ", startDate=" + startDate + ", duration=" + duration + "]";
  }

}
