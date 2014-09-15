package org.camunda.showcase.engine.impl.bpmn.parser;

import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;
import org.camunda.showcase.engine.impl.bpmn.behavior.CmmnCallActivityBehavior;

public class ShowcaseBpmnParseListener extends AbstractBpmnParseListener implements BpmnParseListener {

  @Override
  public void parseCallActivity(Element callActivityElement, ScopeImpl scope, ActivityImpl activity) {
    String calledElement = callActivityElement.attribute("calledElement");

    CmmnCallActivityBehavior callActivityBehaviour = null;
    if (calledElement != null) {
      callActivityBehaviour = new CmmnCallActivityBehavior(calledElement);
    }
    activity.setActivityBehavior(callActivityBehaviour);
  }

}
