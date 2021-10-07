package com.camunda.consulting;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class JobExecutorBreak {
  private DayOfWeek dayOfWeek;
  private LocalTime timeFrom;
  private LocalTime timeTo;

  public LocalTime getTimeFrom() {
    return this.timeFrom;
  }

  public LocalTime getTimeTo() {
    return this.timeTo;
  }

  public void setTimeFrom(LocalTime timeFrom) {
    this.timeFrom = timeFrom;
  }

  public void setTimeTo(LocalTime timeTo) {
    this.timeTo = timeTo;
  }

  public DayOfWeek getDayOfWeek() {
    return this.dayOfWeek;
  }

  public void setDayOfWeek(DayOfWeek dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }
}
