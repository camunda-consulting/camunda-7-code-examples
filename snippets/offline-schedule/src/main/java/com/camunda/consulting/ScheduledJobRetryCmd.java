package com.camunda.consulting;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import org.camunda.bpm.engine.impl.ProcessEngineLogger;
import org.camunda.bpm.engine.impl.calendar.DurationHelper;
import org.camunda.bpm.engine.impl.cmd.DefaultJobRetryCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutorLogger;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;

public class ScheduledJobRetryCmd extends DefaultJobRetryCmd {
  private final static JobExecutorLogger LOG = ProcessEngineLogger.JOB_EXECUTOR_LOGGER;

  private final Set<JobExecutorBreak> schedule;

  public ScheduledJobRetryCmd(String jobId, Throwable exception, Set<JobExecutorBreak> schedule) {
    super(jobId, exception);
    this.schedule = schedule;
  }

  @Override
  protected void executeStandardStrategy(CommandContext commandContext) {
    JobEntity job = this.getJob();
    if (job != null) {
      job.setDuedate(this.calculateStartDateFromSchedule());
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
    return new DurationHelper(failedJobRetryTimeCycle, this.calculateStartDateFromSchedule());
  }

  private Date calculateStartDateFromSchedule() {
    LocalDateTime startDate = LocalDateTime.now();
    boolean startDateChanged = true;
    while (startDateChanged) {
      startDateChanged = false;
      for (JobExecutorBreak jobExecutorBreak : this.schedule) {
        DayOfWeek dayOfWeek = startDate.getDayOfWeek();
        if ((jobExecutorBreak.getDayOfWeek() == null) || jobExecutorBreak.getDayOfWeek().equals(dayOfWeek)) {
          // will be applied on every day or if the day fits
          if (JobExecutorBreakUtils.timestampFrom(jobExecutorBreak).isBefore(startDate)
              && JobExecutorBreakUtils.timestampTo(jobExecutorBreak).isAfter(startDate)) {
            // startDate lies in break time
            startDate = JobExecutorBreakUtils.timestampTo(jobExecutorBreak);
            startDateChanged = true;
          }
        }
      }
    }
    return Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant());
  }

}
