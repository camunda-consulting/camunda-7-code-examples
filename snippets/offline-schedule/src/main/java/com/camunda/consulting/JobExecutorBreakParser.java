package com.camunda.consulting;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobExecutorBreakParser {
  private static final Logger LOG = LoggerFactory.getLogger(JobExecutorBreakParser.class);

  public static JobExecutorBreak parse(String property) {
    try {
      LOG.debug("Creating job executor break for: {}", property);
      String[] split = property.split(",");
      JobExecutorBreak b = new JobExecutorBreak();
      String weekday = split.length == 2 ? null : split[0];
      String timeFrom = split.length == 2 ? split[0] : split[1];
      String timeTo = split.length == 2 ? split[1] : split[2];
      if (weekday != null) {
        b.setDayOfWeek(DayOfWeek.valueOf(weekday));
      }
      b.setTimeFrom(LocalTime.parse(timeFrom));
      b.setTimeTo(LocalTime.parse(timeTo));
      return b;
    } catch (Exception e) {
      throw new RuntimeException("Not a valid property: " + property, e);
    }
  }
}
