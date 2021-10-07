package com.camunda.consulting;

import java.util.Set;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.DefaultFailedJobCommandFactory;

public class ScheduledFailedJobCommandFactory extends DefaultFailedJobCommandFactory {

  private final Set<JobExecutorBreak> schedule;

  public ScheduledFailedJobCommandFactory(Set<JobExecutorBreak> schedule) {
    this.schedule = schedule;
  }

  @Override
  public Command<Object> getCommand(String jobId, Throwable exception) {
    return new ScheduledJobRetryCmd(jobId, exception, this.schedule);
  }
}
