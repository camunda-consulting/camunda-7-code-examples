package com.camunda.consulting.eventhub.plugin;

import lombok.Setter;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

import java.util.ArrayList;
import java.util.List;

public class SignalToEventHubPlugin implements ProcessEnginePlugin {

  @Setter
  private static String eventHubName;
  @Setter
  private static String connectionString;

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    registerParseListener(processEngineConfiguration);
    AzureEventHubClient.setConfig(connectionString, eventHubName);
  }

  private void registerParseListener(ProcessEngineConfigurationImpl processEngineConfiguration) {
    List<BpmnParseListener> postParseListeners = processEngineConfiguration.getCustomPostBPMNParseListeners();
    if (postParseListeners == null) {
      postParseListeners = new ArrayList<>();
      processEngineConfiguration.setCustomPostBPMNParseListeners(postParseListeners);
    }
    postParseListeners.add(new AttachEventHubProducerParseListener());
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {

  }
}
