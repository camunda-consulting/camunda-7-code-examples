package com.camunda.bpm.demo.extension_attributes_for_user_tasks;

import java.util.Collection;
import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;

public class EscalationStartTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		UserTask userTask = delegateTask.getBpmnModelElementInstance();
		ExtensionElements extensionElements = userTask.getExtensionElements();
		Collection<CamundaProperty> properties = extensionElements.getElementsQuery()
			.filterByType(CamundaProperties.class)
			.singleResult()
			.getCamundaProperties();
		for (CamundaProperty property : properties) {
			if ("escalation1".equals(property.getAttributeValue("name"))) { // in 7.2 one can use: property.getCamundaName()
				delegateTask.setDueDate(new Date());
			}
		}
	}

}
