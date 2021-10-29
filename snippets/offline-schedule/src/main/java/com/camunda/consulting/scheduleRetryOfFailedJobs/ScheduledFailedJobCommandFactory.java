package com.camunda.consulting.scheduleRetryOfFailedJobs;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.DefaultFailedJobCommandFactory;

import com.camunda.consulting.JobExecutorSchedule;

public class ScheduledFailedJobCommandFactory extends DefaultFailedJobCommandFactory {

  private final JobExecutorSchedule schedule;

  public ScheduledFailedJobCommandFactory(JobExecutorSchedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public Command<Object> getCommand(String jobId, Throwable exception) {
    return new ScheduledJobRetryCmd(jobId, exception, this.schedule);
  }
}
