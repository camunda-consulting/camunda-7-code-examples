package org.example.incidents_as_tasks;

import org.camunda.bpm.engine.impl.cmd.FoxJobRetryCmd;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;

public class NoRetryFailedJobCommand extends FoxJobRetryCmd implements
		Command<Object> {

	public NoRetryFailedJobCommand(String jobId, Throwable exception) {
		super(jobId, exception);
	}
	
	@Override
	public Object execute(CommandContext commandContext) {
	    JobEntity job = Context.getCommandContext()
                .getJobManager()
                .findJobById(jobId);
	    job.setRetries(1);
		return super.execute(commandContext);
	}
}
