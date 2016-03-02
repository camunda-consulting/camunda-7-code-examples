package com.camunda.demo.task.escalation;

import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

public class UserTaskOverdueJobHandler implements JobHandler {
	
	private static final Logger log = Logger.getLogger(UserTaskOverdueJobHandler.class.getName());

	public static final String USER_TASK_ESCALATION_JOB_HANDLER_TYPE = "userTaskOverdueJob";

	@Override
	public String getType() {
		return USER_TASK_ESCALATION_JOB_HANDLER_TYPE;
	}

	@Override
	public void execute(String configuration, ExecutionEntity execution, CommandContext commandContext) {
		log.info("\n\nEscalation received! " + execution.getId() + "\n\n");
		// TODO handle your escalation here
	}

}
