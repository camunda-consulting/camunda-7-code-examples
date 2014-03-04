package org.example.incidents_as_tasks;

import org.camunda.bpm.engine.impl.TaskServiceImpl;
import org.camunda.bpm.engine.impl.incident.FailedJobIncidentHandler;
import org.camunda.bpm.engine.impl.incident.IncidentHandler;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutorImpl;
import org.camunda.bpm.engine.task.Task;

public class UserTaskFailedJobIncidentHandler extends FailedJobIncidentHandler
		implements IncidentHandler {

	@Override
	public void handleIncident(String processDefinitionId, String activityId,
			String executionId, String jobId, String message) {
		super.handleIncident(processDefinitionId, activityId, executionId, jobId,
				message);
		TaskServiceImpl taskServiceImpl = getTaskService();
		Task task = taskServiceImpl.newTask();
		task.setName("Handle Incident");
		task.setAssignee("demo");
		taskServiceImpl.saveTask(task);
		taskServiceImpl.setVariable(task.getId(), "executionId", executionId);
	}

	private TaskServiceImpl getTaskService() {
		TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
		taskServiceImpl.setCommandExecutor(new CommandExecutorImpl());
		return taskServiceImpl;
	}
	
	@Override
	public void resolveIncident(String processDefinitionId, String activityId,
			String executionId, String jobId) {
		Task task = getTaskService().createTaskQuery().taskName("Handle Incident").taskVariableValueEquals("executionId", executionId).singleResult();
		getTaskService().complete(task.getId());
		super.resolveIncident(processDefinitionId, activityId, executionId, jobId);
	}
}

