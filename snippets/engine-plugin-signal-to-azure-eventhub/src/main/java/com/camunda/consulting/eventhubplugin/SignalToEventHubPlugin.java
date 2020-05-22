package com.camunda.consulting.eventhubplugin;

import com.microsoft.azure.eventhubs.EventHubClient;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

import java.util.ArrayList;
import java.util.List;

public class SignalToEventHubPlugin implements ProcessEnginePlugin {

  static private EventHubClient eventHubClient;

  public static EventHubClient getEventHubClient() {
    return eventHubClient;
  }

  private String namespaceName;
  private String eventHubName;
  private String sasKeyName;
  private String sasKey;
  private String endpoint;

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    registerParseListener(processEngineConfiguration);

    AzureEventHubClient.setConfig(namespaceName,eventHubName,sasKeyName,sasKey,endpoint);
  }

  private void registerParseListener(ProcessEngineConfigurationImpl processEngineConfiguration) {
    List<BpmnParseListener> postParseListeners = processEngineConfiguration.getCustomPostBPMNParseListeners();
    if (postParseListeners == null) {
      postParseListeners = new ArrayList<BpmnParseListener>();
      processEngineConfiguration.setCustomPostBPMNParseListeners(postParseListeners);
    }
    postParseListeners.add(new ParseListener());
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {

  }

  public String getNamespaceName() {
    return namespaceName;
  }

  public void setNamespaceName(String namespaceName) {
    this.namespaceName = namespaceName;
  }

  public String getEventHubName() {
    return eventHubName;
  }

  public void setEventHubName(String eventHubName) {
    this.eventHubName = eventHubName;
  }

  public String getSasKeyName() {
    return sasKeyName;
  }

  public void setSasKeyName(String sasKeyName) {
    this.sasKeyName = sasKeyName;
  }

  public String getSasKey() {
    return sasKey;
  }

  public void setSasKey(String sasKey) {
    this.sasKey = sasKey;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }
}
