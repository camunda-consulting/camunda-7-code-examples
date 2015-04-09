package com.camunda.bpm.demo.externaltask;

import java.util.Collection;
import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.calendar.DurationBusinessCalendar;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.model.bpmn.instance.BaseElement;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;

public class ClaimListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {     
    CamundaProperties camundaProperties = 
        ((BaseElement)delegateTask.getBpmnModelInstance().getModelElementById(delegateTask.getTaskDefinitionKey())).
      getExtensionElements().getElementsQuery().filterByType(CamundaProperties.class).singleResult();

    Collection<CamundaProperty> properties = camundaProperties.getCamundaProperties();
    for (CamundaProperty camundaProperty : properties) {
      if ("lockTime".equals( camundaProperty.getCamundaName() )) {
          String lockTimeExpression = camundaProperty.getCamundaValue();
          String lockTimeIsoString = (String) Context.getProcessEngineConfiguration().getExpressionManager().createExpression(lockTimeExpression).getValue(delegateTask);
          
          Date duedate = new DurationBusinessCalendar().resolveDuedate(lockTimeIsoString);
          
          delegateTask.setVariable("externalTaskLockTime", duedate);
      }
    }
  }

}
