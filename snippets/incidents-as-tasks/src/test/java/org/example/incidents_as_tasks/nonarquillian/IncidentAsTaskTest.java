package org.example.incidents_as_tasks.nonarquillian;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.incident.IncidentHandler;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.jobexecutor.FailedJobCommandFactory;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.Job;
import org.camunda.bpm.engine.task.Task;
import org.example.incidents_as_tasks.NoRetryFailedJobCommand;
import org.example.incidents_as_tasks.UserTaskFailedJobIncidentHandler;
import org.junit.Test;

public class IncidentAsTaskTest {

	@Test
	public void test() {
		List<IncidentHandler> incidentHandlers = new ArrayList<IncidentHandler>();
		incidentHandlers.add(new UserTaskFailedJobIncidentHandler());
		
		StandaloneInMemProcessEngineConfiguration config = new StandaloneInMemProcessEngineConfiguration();
		config.setCustomIncidentHandlers(incidentHandlers);
		config.setFailedJobCommandFactory(new FailedJobCommandFactory() {
			
			@Override
			public Command<Object> getCommand(String jobId, Throwable exception) {
				return new NoRetryFailedJobCommand(jobId, exception);

			}
		});
		ProcessEngine processEngine = config.buildProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();

		repositoryService.createDeployment()
			.addClasspathResource("process.bpmn")
			.deploy();
		runtimeService.startProcessInstanceByKey("incidents-as-tasks");
		
		ManagementService managementService = processEngine.getManagementService();
		Job job = managementService.createJobQuery().singleResult();
//		for (int i = 0; i < job.getRetries(); i++) {
			try {
				managementService.executeJob(job.getId());
			} catch (ProcessEngineException e) {
				// ignore exception
			}
//		}
		Incident incident = runtimeService.createIncidentQuery().singleResult();
		assertNotNull(incident);
		
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery().singleResult();
		assertNotNull(task);
		assertNotNull(taskService.getVariable(task.getId(), "executionId"));
	}

}
