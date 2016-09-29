package com.camunda.bpm.demo.engine_plugin_add_save_points;

import java.util.logging.Logger;

import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.camunda.bpm.engine.repository.ProcessDefinition;

public class AddSavePointsParseListener extends AbstractBpmnParseListener implements BpmnParseListener {

  private final Logger LOGGER = Logger.getLogger(AddSavePointsParseListener.class.getName());

  @Override
  public void parseStartEvent(Element startEventElement, ScopeImpl scope, ActivityImpl startEvent) {
    if (scope instanceof ProcessDefinition) {
      LOGGER.info("Adding save point before Start Event '" + startEvent.getId() + "'");
      startEvent.setAsyncBefore(true);
    }
  }

  @Override
  public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl userTask) {
    LOGGER.info("Adding save point after User Task '" + userTask.getId() + "'");
    userTask.setAsyncAfter(true);
  }

}
