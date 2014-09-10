package com.camunda.consulting.history.changeHistoryOutput;

import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

/**
 * @author Ingo Richtsmeier
 *
 */
public class FilterVariableHistoryPlugin implements ProcessEnginePlugin {
  
  private String variableNames;

  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    // Nothing here
  }

  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    List<String> variableNameList = null;
    if (variableNames != null) {
      variableNameList = Arrays.asList(variableNames.split(","));
    }
    processEngineConfiguration.setHistoryEventProducer(new FilterVariableHistoryEventProducer(variableNameList));
    processEngineConfiguration.setHistoryEventHandler(new FilterVariableHistoryEventHandler());
  }

  public void postProcessEngineBuild(ProcessEngine processEngine) {
    // nothing here
  }

  public String getVariableNames() {
    return variableNames;
  }

  public void setVariableNames(String variableNames) {
    this.variableNames = variableNames;
  }

}
