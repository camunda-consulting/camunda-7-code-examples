package com.camunda.demo.migration;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

public class MigrationProcessEnginePlugin implements ProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    if(processEngineConfiguration.getPreParseListeners() == null) {
      processEngineConfiguration.setPreParseListeners(new ArrayList<BpmnParseListener>());
    }
    processEngineConfiguration.getPreParseListeners().add(new MigrationParseListener());
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
  }

}
