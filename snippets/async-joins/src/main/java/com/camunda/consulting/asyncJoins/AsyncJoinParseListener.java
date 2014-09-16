package com.camunda.consulting.asyncJoins;

import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse;
import org.camunda.bpm.engine.impl.jobexecutor.MessageJobDeclaration;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class AsyncJoinParseListener extends AbstractBpmnParseListener {
  
  private static final Logger log = Logger.getLogger(AsyncJoinParseListener.class.getName());

  @Override
  public void parseInclusiveGateway(Element inclusiveGwElement, ScopeImpl scope, ActivityImpl activity) {
    log.info("parsing Inclusive Gateway for async join");
    String nameAttribute = inclusiveGwElement.attribute("name");
    if (nameAttribute != null && nameAttribute.startsWith("async-")) {
      log.info("async inclusive gateway");
      makeAsynchronous(activity);
    }
  }

  @Override
  public void parseEndEvent(Element endEventElement, ScopeImpl scope, ActivityImpl activity) {
    log.info("parsing End Event for async join");
    String nameAttribute = endEventElement.attribute("name");
    if (nameAttribute != null && nameAttribute.startsWith("async-")) {
      log.info("async multi instance subprocess end event");
      makeAsynchronous(activity);
    }
  }

  @Override
  public void parseParallelGateway(Element parallelGwElement, ScopeImpl scope, ActivityImpl activity) {
    log.info("parsing Parallel Gateway for async join");
    String nameAttribute = parallelGwElement.attribute("name");
    if (nameAttribute != null && nameAttribute.startsWith("async-")) {
      log.info("async parallel gateway");
      makeAsynchronous(activity);
    }
  }

  private void makeAsynchronous(ActivityImpl activity) {
    activity.setAsync(true);
    activity.setExclusive(true);
    // create message event declaration:
    MessageJobDeclaration messageJobDecl = new MessageJobDeclaration();
    messageJobDecl.setExclusive(true);
    messageJobDecl.setActivityId(activity.getId());
    activity.setProperty(BpmnParse.PROPERTYNAME_MESSAGE_JOB_DECLARATION, messageJobDecl);
  } 
}
