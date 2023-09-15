package com.camunda.consulting.scheduleWithCommandInterceptor;

import com.camunda.consulting.OfflineSchedule;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.db.entitymanager.DbEntityManager;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;
import org.camunda.bpm.engine.impl.jobexecutor.AcquiredJobs;
import org.camunda.bpm.engine.impl.persistence.entity.AcquirableJobEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OfflineScheduleCommandInterceptor extends CommandInterceptor {
  private static final Logger LOG =
      LoggerFactory.getLogger(OfflineScheduleCommandInterceptor.class);
  private final OfflineSchedule schedule;

  public OfflineScheduleCommandInterceptor(OfflineSchedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public <T> T execute(Command<T> command) {
    T result = next.execute(command);
    if (result instanceof AcquiredJobs acquiredJobs
        && !acquiredJobs.getJobIdBatches().isEmpty()
        && schedule.isSuspended()) {
      scheduleAcquiredJobs(acquiredJobs);
    }
    return result;
  }

  private void scheduleAcquiredJobs(AcquiredJobs acquiredJobs) {
    Date startDate = schedule.calculateStartDateFromSchedule();
    LOG.info("Scheduling acquired jobs to {}", DateFormat.getDateTimeInstance().format(startDate));
    List<String> jobIds =
        acquiredJobs.getJobIdBatches().stream().flatMap(Collection::stream).toList();
    DbEntityManager dbEntityManager = Context.getCommandContext().getDbEntityManager();
    jobIds.forEach(
        jobId -> {
          AcquirableJobEntity jobEntity =
              dbEntityManager.selectById(AcquirableJobEntity.class, jobId);
          dbEntityManager.getDbEntityCache().remove(jobEntity);
          JobEntity realJobEntity = dbEntityManager.selectById(JobEntity.class, jobId);
          realJobEntity.setDuedate(startDate);
          realJobEntity.unlock();
          acquiredJobs.removeJobId(jobId);
          LOG.info("  - {}", jobId);
        });
  }
}
