package com.camunda.demo.failedjobhandler;

import org.camunda.bpm.engine.impl.ProcessEngineLogger;
import org.camunda.bpm.engine.impl.cmd.FoxJobRetryCmd;
import org.camunda.bpm.engine.impl.cmd.JobRetryCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutorLogger;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;

public class MyFailedJobCmd extends FoxJobRetryCmd {
  
  private final static JobExecutorLogger LOG = ProcessEngineLogger.JOB_EXECUTOR_LOGGER;

  public MyFailedJobCmd(String jobId, Throwable exception) {
    super(jobId, exception);
  }
  
  public Object execute(CommandContext commandContext) {
    if (super.exception.getMessage().equals("business")) {
      new SetJobRetriesToZeroCmd(jobId, exception).execute(commandContext);
      return null;
    } else {
      return super.execute(commandContext);
    }
  }

  public static class SetJobRetriesToZeroCmd extends JobRetryCmd {

    public SetJobRetriesToZeroCmd(String jobId, Throwable exception) {
      super(jobId, exception);
    }

    public Object execute(CommandContext commandContext) {
      JobEntity job = getJob();

      job.unlock();
      logException(job);
      job.setRetries(0);
      notifyAcquisition(commandContext);

      return null;
    }

  }
}
