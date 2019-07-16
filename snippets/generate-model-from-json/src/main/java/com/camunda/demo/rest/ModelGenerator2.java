package com.camunda.demo.rest;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.builder.StartEventBuilder;
import org.camunda.bpm.model.bpmn.instance.StartEvent;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

public class ModelGenerator2 {

	public static void main(String[] args) {

		StartEventBuilder builder = Bpmn.createExecutableProcess("Palim").startEvent("start");
		BpmnModelInstance model = builder.done();
		logModel(model);

		ModelElementInstance modelElement = model.getModelElementById("start");
		StartEvent startEvent = (StartEvent) modelElement;
		startEvent.builder().userTask("userTask_5f339abd-add8-4d52-8a4d-9368080aa268");// .camundaFormKey("aFormKey");

		logModel(model);

		UserTask userTask = (UserTask) model.getModelElementById("1");
		userTask.builder().endEvent();

		logModel(model);
	}

	private static void logModel(BpmnModelInstance model) {

		Bpmn.writeModelToStream(System.out, model);
//		String xml = Bpmn.convertToString(model);
//		System.out.println(xml);
	}
}
