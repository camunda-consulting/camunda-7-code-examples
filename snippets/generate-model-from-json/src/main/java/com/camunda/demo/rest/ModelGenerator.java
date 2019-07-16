package com.camunda.demo.rest;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.builder.ParallelGatewayBuilder;
import org.camunda.bpm.model.bpmn.builder.StartEventBuilder;
import org.camunda.bpm.model.bpmn.instance.ParallelGateway;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import com.camunda.demo.domain.EventTypeConfiguration;
import com.camunda.demo.domain.UserTaskData;

public class ModelGenerator {


	public BpmnModelInstance generateModel(EventTypeConfiguration configuration) {

		String predecessor;

		StartEventBuilder builder = Bpmn.createExecutableProcess(configuration.getEventType()).startEvent("start");
		BpmnModelInstance model = builder.done();

		if (configuration.getConfiguration().size() > 1) {
			// add a parallel gateway right after the start event
			StartEvent startEvent = (StartEvent) model.getModelElementById("start");
			ParallelGatewayBuilder parallelGateway = startEvent.builder().parallelGateway();
			String parallelGatewayId = parallelGateway.getElement().getId();
			predecessor = parallelGatewayId;
		} else {
			predecessor = "start";
		}

		
		for (UserTaskData utd : configuration.getConfiguration()) {
			addModelElement(model, predecessor, utd);
		}

		return model;
	}

	private void addModelElement(BpmnModelInstance model, String predecessor, UserTaskData userTaskData) {

		String newUserTaskId = "generatedUserTask_" + userTaskData.getId();

		ModelElementInstance modelElement = model.getModelElementById(predecessor);
		String modelElementType = modelElement.getElementType().getTypeName();

		switch (modelElementType) {
		case "startEvent":
			StartEvent startEvent = (StartEvent) modelElement;
			startEvent.builder().userTask(newUserTaskId).camundaFormKey(userTaskData.getFormkey());
			break;
		case "userTask":
			UserTask userTask = (UserTask) modelElement;
			userTask.builder().userTask(newUserTaskId).camundaFormKey(userTaskData.getFormkey());
			break;
		case "parallelGateway":
			ParallelGateway parallelGateway = (ParallelGateway) modelElement;
			parallelGateway.builder().userTask(newUserTaskId).camundaFormKey(userTaskData.getFormkey());
			break;
		default:
			System.out.println("Default type detected");
		}

		predecessor = newUserTaskId;


		if (!userTaskData.getChildren().isEmpty()) {

			if (userTaskData.getChildren().size() > 1) {
				UserTask userTask = model.getModelElementById(newUserTaskId);
				ParallelGatewayBuilder parallelGateway = userTask.builder().parallelGateway();
				String parallelGatewayId = parallelGateway.getElement().getId();
				predecessor = parallelGatewayId;
			}
			for (UserTaskData utd : userTaskData.getChildren()) {
				addModelElement(model, predecessor, utd);
			}

		} else {
			// add EndEvent
			UserTask userTask = (UserTask) model.getModelElementById(newUserTaskId);
			userTask.builder().endEvent();

		}

	}

}
