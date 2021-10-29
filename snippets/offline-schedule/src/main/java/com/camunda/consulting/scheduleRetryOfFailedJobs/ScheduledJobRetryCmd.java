package com.camunda.consulting.scheduleRetryOfFailedJobs;

import org.camunda.bpm.engine.impl.ProcessEngineLogger;
import org.camunda.bpm.engine.impl.calendar.DurationHelper;
import org.camunda.bpm.engine.impl.cmd.DefaultJobRetryCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutorLogger;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;

import com.camunda.consulting.OfflineSchedule;

public class ScheduledJobRetryCmd extends DefaultJobRetryCmd {
  private final static JobExecutorLogger LOG = ProcessEngineLogger.JOB_EXECUTOR_LOGGER;

  private final OfflineSchedule schedule;

  public ScheduledJobRetryCmd(String jobId, Throwable exception, OfflineSchedule schedule) {
    super(jobId, exception);
    this.schedule = schedule;
  }

  @Override
  protected void executeStandardStrategy(CommandContext commandContext) {
    JobEntity job = this.getJob();
    if (job != null) {
      job.setDuedate(this.schedule.calculateStartDateFromSchedule());
      job.unlock();
      this.logException(job);
      this.decrementRetries(job);
      this.notifyAcquisition(commandContext);
    } else {
      LOG.debugFailedJobNotFound(this.jobId);
    }
  }

  @Override
  protected DurationHelper getDurationHelper(String failedJobRetryTimeCycle) throws Exception {
    return new DurationHelper(failedJobRetryTimeCycle, this.schedule.calculateStartDateFromSchedule());
  }

}
