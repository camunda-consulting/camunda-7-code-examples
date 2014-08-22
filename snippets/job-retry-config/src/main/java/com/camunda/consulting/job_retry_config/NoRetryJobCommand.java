package com.camunda.consulting.job_retry_config;

import java.util.logging.Logger;

import org.camunda.bpm.engine.OptimisticLockingException;
import org.camunda.bpm.engine.impl.cmd.FoxJobRetryCmd;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;


/**
 * @author FritzJu, Ingo Richtsmeier
 *
 */
public class NoRetryJobCommand extends FoxJobRetryCmd {
  
  private static final Logger log = Logger.getLogger(NoRetryJobCommand.class.getName());

	/**
	 * @param jobId
	 * @param exception
	 * @param retries
	 */
	public NoRetryJobCommand(String jobId, Throwable exception) {

		super(jobId, exception);
	}

	@Override
	public Object execute(CommandContext commandContext) {
		JobEntity job = Context.getCommandContext().getJobManager().findJobById(jobId);
		if (exception instanceof OptimisticLockingException) {
      log.info("create Job entity with retries = 3");
		  job.setRetries(3);
		} else {
		  log.info("create Job entity with retries = 1");
		  job.setRetries(1);
		}

		return super.execute(commandContext);
	}
}
