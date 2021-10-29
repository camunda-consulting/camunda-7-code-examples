package com.camunda.consulting.scheduleRetryOfFailedJobs;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.DefaultFailedJobCommandFactory;

import com.camunda.consulting.OfflineSchedule;

public class ScheduledFailedJobCommandFactory extends DefaultFailedJobCommandFactory {

  private final OfflineSchedule schedule;

  public ScheduledFailedJobCommandFactory(OfflineSchedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public Command<Object> getCommand(String jobId, Throwable exception) {
    return new ScheduledJobRetryCmd(jobId, exception, this.schedule);
  }
}
