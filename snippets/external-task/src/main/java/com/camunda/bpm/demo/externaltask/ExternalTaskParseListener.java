package com.camunda.bpm.demo.externaltask;

import java.util.List;

import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.impl.form.handler.DefaultTaskFormHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.task.TaskDecorator;
import org.camunda.bpm.engine.impl.task.TaskDefinition;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class ExternalTaskParseListener extends AbstractBpmnParseListener {

  @Override
  public void parseServiceTask(Element serviceTaskElement, ScopeImpl scope, ActivityImpl activity) {    
    String workerName = null;
    String lockTime = null;
    
    Element extensionElement = serviceTaskElement.element("extensionElements");
    if (extensionElement==null) {
      return;
    }    
    Element properties = extensionElement.element("properties");
    if (properties==null) {
      return;
    }
    
    List<Element> elements = properties.elements();
    for (Element propertyElement : elements) {
      if ("externalTaskName".equals(propertyElement.attribute("name"))) {
        workerName = propertyElement.attribute("value");
      }
//      if ("lockTime".equals(propertyElement.attribute("name"))) {
//        lockTime = propertyElement.attribute("value");        
//      }
    }
    if (workerName==null) {
      return;
    }
    
    ExpressionManager expressionManager = Context.getProcessEngineConfiguration().getExpressionManager();

    TaskDefinition taskDefinition = new TaskDefinition(new DefaultTaskFormHandler());
    taskDefinition.addTaskListener(TaskListener.EVENTNAME_ASSIGNMENT, new ClaimListener());
    taskDefinition.setKey(activity.getId());
    taskDefinition.addCandidateGroupIdExpression(expressionManager.createExpression(workerName));
    
    ((ProcessDefinitionEntity)activity.getProcessDefinition()).getTaskDefinitions().put(activity.getId(), taskDefinition);
    
    activity.setActivityBehavior(new ExternalTaskBehavior(new TaskDecorator(taskDefinition, expressionManager)));
    
    // we want to get the claim events to be able to update lock times
  }

}
