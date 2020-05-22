package com.camunda.consulting.eventhubplugin;

import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse.SIGNAL_EVENT_DEFINITION;

public class ParseListener extends AbstractBpmnParseListener implements BpmnParseListener {

  private final Logger LOGGER = LoggerFactory.getLogger(ParseListener.class);

  @Override
  public void parseIntermediateThrowEvent(Element intermediateEventElement, ScopeImpl scope, ActivityImpl activity) {
    attachEventHubListener(intermediateEventElement, activity);
  }


  @Override
  public void parseEndEvent(Element endEventElement, ScopeImpl scope, ActivityImpl activity) {
    attachEventHubListener(endEventElement, activity);
  }

  private void attachEventHubListener(Element intermediateEventElement, ActivityImpl activity) {
    Element signalEventDefinitionElement = intermediateEventElement.element(SIGNAL_EVENT_DEFINITION);
    if (signalEventDefinitionElement != null) {
      LOGGER.debug("Adding signal to event hub listener to " + activity.getId());
      activity.addListener(ExecutionListener.EVENTNAME_END, new SignalToEventHubListener());
    }
  }
}
