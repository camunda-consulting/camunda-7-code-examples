package com.camunda.consulting;

import java.util.Date;

import org.camunda.bpm.engine.impl.cmd.AcquireJobsCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.AcquiredJobs;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleAwareAquireJobsCmd extends AcquireJobsCmd {
  private static final Logger LOG = LoggerFactory.getLogger(ScheduleAwareAquireJobsCmd.class);
  private final JobExecutorSchedule schedule;

  public ScheduleAwareAquireJobsCmd(JobExecutor jobExecutor, JobExecutorSchedule schedule) {
    super(jobExecutor);
    this.schedule = schedule;
  }

  @Override
  public AcquiredJobs execute(CommandContext commandContext) {
    Date nextJobDate = this.schedule.calculateStartDateFromSchedule();
    if (nextJobDate.after(new Date())) {
      LOG.debug("Not acquiring jobs until {}", nextJobDate);
      return new AcquiredJobs(this.numJobsToAcquire);
    }
    return super.execute(commandContext);
  }

}
