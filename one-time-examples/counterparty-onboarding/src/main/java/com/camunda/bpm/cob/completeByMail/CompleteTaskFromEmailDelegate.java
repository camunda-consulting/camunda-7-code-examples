package com.camunda.bpm.cob.completeByMail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;

public class CompleteTaskFromEmailDelegate implements JavaDelegate {

	private static final Logger log = Logger.getLogger(CompleteTaskFromEmailDelegate.class.getName());
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.info("Inspect each email message and complete the task inside");
		CompleteTaskMessage completeTaskMessage = (CompleteTaskMessage) execution.getVariable("message");
		
		String taskId = null;
		Map<String, Object> variables = new HashMap<String, Object>();
		if (completeTaskMessage != null) {
			log.info("Subject of the message: " + completeTaskMessage.getSubject());
			log.info("Body of the message" + completeTaskMessage.getBody());
			if (completeTaskMessage.getSubject().contains("complete task")) {
				String message = completeTaskMessage.getBody();
				String[] lines = message.split("\n");
				for (int i = 0; i < lines.length; i++) {
					String line = lines[i];
					if (line.startsWith("**")) {
						taskId = line.substring(line.indexOf("=") + 1).trim();
					} else if (line.startsWith("##")){
						String variableName = line.substring(2, line.indexOf("=")).trim();
						String variableValue = line.substring(line.indexOf("=") + 1).trim();
						variables.put(variableName, variableValue);
					}
				}
				log.info("Complete task with id " + taskId + " with variables " + variables);
				TaskService taskService = execution.getProcessEngineServices().getTaskService();
				List<Task> listOfTasks = taskService.createTaskQuery().taskId(taskId).list();
				if (listOfTasks.size() == 1) {
					// example for authentication in the engine. 
					// You have to get the userId out of the from: part of the mail. 
					String userId = "user1";
					execution.getProcessEngineServices().getIdentityService().setAuthenticatedUserId(userId);
					taskService.complete(taskId, variables);
					execution.getProcessEngineServices().getIdentityService().clearAuthentication();
					execution.setVariable("message" + execution.getId(), "Task with Id " + taskId + " completed by mail");
				} else {
					execution.setVariable("message" + execution.getId(), "Task with Id " + taskId + " could not be found");
				}
			} else {
				log.info("no message to complete a task");
			}
		} else {
			log.info("Message was null");
		}
	}

}
