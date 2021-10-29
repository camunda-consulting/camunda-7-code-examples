package com.camunda.consulting.scheduleJobExecution;

import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.AcquiredJobs;
import org.camunda.bpm.engine.impl.jobexecutor.DefaultAcquireJobsCommandFactory;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;

import com.camunda.consulting.JobExecutorSchedule;

public class ScheduleAwareAquireJobsCommandFactory extends DefaultAcquireJobsCommandFactory {
  private final JobExecutorSchedule schedule;

  public ScheduleAwareAquireJobsCommandFactory(JobExecutor jobExecutor, JobExecutorSchedule schedule) {
    super(jobExecutor);
    this.schedule = schedule;
  }

  @Override
  public Command<AcquiredJobs> getCommand(int numJobsToAcquire) {
    return new ScheduleAwareAquireJobsCmd(this.jobExecutor, this.schedule);
  }

}
