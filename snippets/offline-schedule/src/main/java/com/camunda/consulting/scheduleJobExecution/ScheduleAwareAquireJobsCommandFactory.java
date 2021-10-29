package com.camunda.consulting.scheduleJobExecution;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.AcquiredJobs;
import org.camunda.bpm.engine.impl.jobexecutor.DefaultAcquireJobsCommandFactory;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;

import com.camunda.consulting.OfflineSchedule;

public class ScheduleAwareAquireJobsCommandFactory extends DefaultAcquireJobsCommandFactory {
  private final OfflineSchedule schedule;

  public ScheduleAwareAquireJobsCommandFactory(JobExecutor jobExecutor, OfflineSchedule schedule) {
    super(jobExecutor);
    this.schedule = schedule;
  }

  @Override
  public Command<AcquiredJobs> getCommand(int numJobsToAcquire) {
    return new ScheduleAwareAquireJobsCmd(this.jobExecutor, this.schedule);
  }

}
