package com.camunda.consulting.cmmn_listener_extension;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cmmn.transformer.CmmnTransformListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmmnTransformerProcessEnginePlugin implements ProcessEnginePlugin {
  
  private static final Logger log = LoggerFactory.getLogger(ExampleTaskListener.class);

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    log.info("register post transform listener");
    
    List<CmmnTransformListener> postCmmnTransformListeners = processEngineConfiguration.getCustomPostCmmnTransformListeners();
    if (postCmmnTransformListeners == null) {
      postCmmnTransformListeners = new ArrayList<CmmnTransformListener>();
      processEngineConfiguration.setCustomPostCmmnTransformListeners(postCmmnTransformListeners);
    }
    postCmmnTransformListeners.add(new ExampleCmmnTransformListener());
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

  }

  @Override
  public void postProcessEngineBuild(ProcessEngine processEngine) {

  }

}
