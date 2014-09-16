package com.camunda.consulting.asyncJoins;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

public class ParseListenerProcessEnginePlugin implements ProcessEnginePlugin {

  private static final Logger log = Logger.getLogger(ParseListenerProcessEnginePlugin.class.getName());
  
  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    log.info("add Post Parse Listener");
    List<BpmnParseListener> postParseListeners = processEngineConfiguration.getPostParseListeners();
    if (postParseListeners == null) {
      postParseListeners = new ArrayList<BpmnParseListener>();
      processEngineConfiguration.setPostParseListeners(postParseListeners);
    }
    postParseListeners.add(new AsyncJoinParseListener());
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
    // TODO Auto-generated method stub

  }

}
