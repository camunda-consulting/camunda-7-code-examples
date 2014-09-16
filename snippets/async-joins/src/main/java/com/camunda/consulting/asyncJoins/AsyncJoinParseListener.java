package com.camunda.consulting.asyncJoins;

import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse;
import org.camunda.bpm.engine.impl.jobexecutor.MessageJobDeclaration;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

public class AsyncJoinParseListener extends AbstractBpmnParseListener {
  
  private static final Logger log = Logger.getLogger(AsyncJoinParseListener.class.getName());
  
  @Override
  public void parseProcess(Element processElement, ProcessDefinitionEntity processDefinition) {
    log.info("Parse Process for async join: " + processElement.attribute("id"));
    List<ActivityImpl> activities = processDefinition.getActivities();
    for (ActivityImpl activity : activities) {
      handleAsyncJoinActivity(activity);
    }
  }

  private void handleAsyncJoinActivity(ActivityImpl activity) {
    if (activity.getProperty("type").equals("parallelGateway")) {
      log.fine("Handle parallel gateway:");
      if (activity.getIncomingTransitions() != null
          && activity.getIncomingTransitions().size() > 1) {
        log.info(activity.getActivityId() + " is join parallel gateway");
        makeAsynchronous(activity);
      }
    } else if (activity.getProperty("type").equals("inclusiveGateway")) {
      log.fine("Handle inclusive gateway:");
      if (activity.getIncomingTransitions() != null 
          && activity.getIncomingTransitions().size() > 1) {
        log.info(activity.getActivityId() + " is join inclusive gateway");
        makeAsynchronous(activity);
      }
//    } else if (activity.getProperty("type").equals("endEvent")) {
//      log.fine("Handle end event " + activity.getActivityId());
//      if (activity.getParentActivity() != null) {
//        log.fine(activity.getParentActivity().getActivityId());
//      }
    } else if (activity.getProperty("type").equals("subProcess")) {
      log.fine("Handle outer subProcess " + activity.getActivityId());
      handleSubProcessforAsyncJoin(activity);
    }
  }
  
  private void handleSubProcessforAsyncJoin(ActivityImpl activity) {
    if (activity.getProperty("type").equals("subProcess")) {
      if (activity.getProperty("multiInstance") != null) {
        // search for endEvent and makeAsynchronous
        List<ActivityImpl> innerActivities = activity.getActivities();
        for (ActivityImpl innerActivity : innerActivities) {
          if (innerActivity.getProperty("type").equals("endEvent")) {
            log.fine("Handle end event " + innerActivity.getActivityId());
            if (innerActivity.getParentActivity() != null) {
              log.info(innerActivity.getActivityId() + " is multi instance join end event");
              makeAsynchronous(innerActivity);
            }
          }
        }
      } else {
        List<ActivityImpl> innerActivities = activity.getActivities();
        for (ActivityImpl innerActivity : innerActivities) {
          handleAsyncJoinActivity(innerActivity);
        }
      }
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
