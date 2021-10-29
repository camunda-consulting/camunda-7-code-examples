package com.camunda.consulting;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

public class OfflineSchedule {
  private final Set<JobExecutorBreak> schedule;

  public OfflineSchedule(Set<JobExecutorBreak> schedule) {
    this.schedule = schedule;
  }

  public Date calculateStartDateFromSchedule() {
    LocalDateTime startDate = LocalDateTime.now();
    boolean startDateChanged = true;
    while (startDateChanged) {
      startDateChanged = false;
      for (JobExecutorBreak jobExecutorBreak : this.schedule) {
        DayOfWeek dayOfWeek = startDate.getDayOfWeek();
        if ((jobExecutorBreak.getDayOfWeek() == null) || jobExecutorBreak.getDayOfWeek().equals(dayOfWeek)) {
          // will be applied on every day or if the day fits
          if (JobExecutorBreakUtils.timestampFrom(jobExecutorBreak).isBefore(startDate)
              && JobExecutorBreakUtils.timestampTo(jobExecutorBreak).isAfter(startDate)) {
            // startDate lies in break time
            startDate = JobExecutorBreakUtils.timestampTo(jobExecutorBreak);
            startDateChanged = true;
          }
        }
      }
    }
    return Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant());
  }
}
