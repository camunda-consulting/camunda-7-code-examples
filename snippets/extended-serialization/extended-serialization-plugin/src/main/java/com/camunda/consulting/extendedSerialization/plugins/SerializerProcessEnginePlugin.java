package com.camunda.consulting.extendedSerialization.plugins;

import java.util.logging.Logger;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;

public class SerializerProcessEnginePlugin extends AbstractProcessEnginePlugin {
  
  private static final Logger log = Logger.getLogger(SerializerProcessEnginePlugin.class.getName());

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {
    log.info("postProcessEngineBuild");
    // just an empty process engine plugin as placeholder
  }

}
