package com.camunda.consulting.scheduleJobExecution;

import java.util.Date;

import org.camunda.bpm.engine.impl.cmd.AcquireJobsCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.AcquiredJobs;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camunda.consulting.OfflineSchedule;

public class ScheduleAwareAquireJobsCmd extends AcquireJobsCmd {
  private static final Logger LOG = LoggerFactory.getLogger(ScheduleAwareAquireJobsCmd.class);
  private final OfflineSchedule schedule;

  public ScheduleAwareAquireJobsCmd(JobExecutor jobExecutor, OfflineSchedule schedule) {
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
