package com.camunda.bpm.cob;

import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

public class EscalationJobHandler implements JobHandler {
	
	private static final Logger log = Logger.getLogger(EscalationJobHandler.class.getName());

	public static final String ESCALATION_JOB_HANDLER_TYPE = "escalationJob";

	@Override
	public String getType() {
		return ESCALATION_JOB_HANDLER_TYPE;
	}

	@Override
	public void execute(String configuration, ExecutionEntity execution,
			CommandContext commandContext) {
		log.info("\n\nEscalation received! " + execution.getId() + "\n\n");
		// TODO handle your escalation here
	}

}
