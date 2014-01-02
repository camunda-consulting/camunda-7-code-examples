package org.camunda.bpm.examples;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

import java.util.ArrayList;
import java.util.List;

public class FourEyesAdvancedProcessEnginePlugin implements ProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    List<BpmnParseListener> customPreBPMNParseListeners = processEngineConfiguration.getCustomPreBPMNParseListeners();
    if (customPreBPMNParseListeners == null) {
      customPreBPMNParseListeners = new ArrayList<BpmnParseListener>();
      processEngineConfiguration.setCustomPreBPMNParseListeners(customPreBPMNParseListeners);
    }
    customPreBPMNParseListeners.add(new FourEyesExtensionsParseListener());
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
  }

}
