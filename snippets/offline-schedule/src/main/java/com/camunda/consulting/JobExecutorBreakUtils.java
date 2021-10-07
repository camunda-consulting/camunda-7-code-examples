package com.camunda.consulting;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobExecutorBreakUtils {
  public static LocalDateTime timestampFrom(JobExecutorBreak jobExecutorBreak) {
    return jobExecutorBreak.getTimeFrom().atDate(LocalDate.now());
  }

  public static LocalDateTime timestampTo(JobExecutorBreak jobExecutorBreak) {
    return jobExecutorBreak.getTimeTo()
        .atDate(jobExecutorBreak.getTimeTo().isBefore(jobExecutorBreak.getTimeFrom()) ? LocalDate.now().plusDays(1)
            : LocalDate.now());
  }
}
