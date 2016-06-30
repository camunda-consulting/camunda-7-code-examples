package com.camunda.demo.task.escalation;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;

public class CreateWatchDuedateJobTaskListener implements TaskListener {

	private static final Logger log = Logger.getLogger(CreateWatchDuedateJobTaskListener.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
		if (delegateTask.getDueDate() != null) {
			log.info("Add WatchDueDateJob for task " + delegateTask.getId());
			
			ExecutionEntity execution = (ExecutionEntity) delegateTask.getExecution();
			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) execution.getProcessDefinition();

			TimerEntity timer = new TimerEntity();
			timer.setExecution(execution);
			timer.setDuedate(delegateTask.getDueDate());
			timer.setJobHandlerType(UserTaskOverdueJobHandler.USER_TASK_ESCALATION_JOB_HANDLER_TYPE);
			timer.setProcessDefinitionKey(processDefinition.getKey());
			timer.setDeploymentId(processDefinition.getDeploymentId());
			Context.getCommandContext().getJobManager().schedule(timer);
		} else {
			log.info("Do not add WatchDueDateJob for task " + delegateTask.getId() + " as it has no due date configured");			
		}
	}

}
