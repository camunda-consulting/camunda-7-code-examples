package com.camunda.demo.task.escalation;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.jobexecutor.TimerDeclarationImpl;
import org.camunda.bpm.engine.impl.jobexecutor.TimerDeclarationType;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class WatchDuedateTaskListener implements TaskListener {
	
	private static final Logger log = Logger.getLogger(WatchDuedateTaskListener.class.getName());  

	@Override
	public void notify(DelegateTask delegateTask) {
		log.info("notify");
		if (TaskListener.EVENTNAME_CREATE.equals(delegateTask.getEventName())) {
			if (delegateTask.getDueDate() != null) {
				createJobForEscalation(delegateTask);
			}
		} else if (TaskListener.EVENTNAME_COMPLETE.equals(delegateTask.getEventName())) {
			deleteJobForEscalation(delegateTask);
		}
	}

	private void createJobForEscalation(DelegateTask delegateTask) {
		log.info("create job for escalation for task " + delegateTask.getId());
		ExecutionEntity execution = (ExecutionEntity) delegateTask.getExecution();
		Long dueDateLong = delegateTask.getDueDate().getTime();
		DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();
		String isoDate = dateTimeFormatter.print(dueDateLong);
		Expression dueDate = Context.getProcessEngineConfiguration().getExpressionManager().createExpression(isoDate);		
		TimerDeclarationImpl timerDeclaration = 
				new TimerDeclarationImpl(
						dueDate, 
						TimerDeclarationType.DATE, 
						UserTaskOverdueJobHandler.USER_TASK_ESCALATION_JOB_HANDLER_TYPE);
		TimerEntity timerEntity = timerDeclaration.createTimer(execution);
		timerEntity.setProcessDefinitionId(delegateTask.getProcessDefinitionId());
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) execution.getProcessDefinition();
		timerEntity.setProcessDefinitionKey(processDefinition.getKey());
		timerEntity.setDeploymentId(processDefinition.getDeploymentId());
		// handlerCfg can be used to transport data
	}

	private void deleteJobForEscalation(DelegateTask delegateTask) {
		log.info("should delete job from escalation for task " + delegateTask.getId());
		ExecutionEntity execution = (ExecutionEntity) delegateTask.getExecution();
		List<JobEntity> jobs = execution.getJobs();
		for (JobEntity jobEntity : jobs) {
			if (jobEntity.getJobHandlerType().equals(UserTaskOverdueJobHandler.USER_TASK_ESCALATION_JOB_HANDLER_TYPE)) {
				jobEntity.delete();
				log.info("job from escalation deleted for task " + delegateTask.getId());
			}
		}
	}

}
