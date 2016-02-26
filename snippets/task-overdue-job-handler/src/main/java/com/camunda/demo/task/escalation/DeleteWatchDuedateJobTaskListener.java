package com.camunda.demo.task.escalation;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;

public class DeleteWatchDuedateJobTaskListener implements TaskListener {
	
	private static final Logger log = Logger.getLogger(DeleteWatchDuedateJobTaskListener.class.getName());  

	@Override
	public void notify(DelegateTask delegateTask) {
		log.info("Delete WatchDueDateJob for task " + delegateTask.getId());
		
		ExecutionEntity execution = (ExecutionEntity) delegateTask.getExecution();
		List<JobEntity> jobs = execution.getJobs();
		for (JobEntity jobEntity : jobs) {
			if (jobEntity.getJobHandlerType().equals(UserTaskOverdueJobHandler.USER_TASK_ESCALATION_JOB_HANDLER_TYPE)) {
				jobEntity.delete();
				log.info("WatchDueDateJob deleted for task " + delegateTask.getId());
			}
		}
	}

}
