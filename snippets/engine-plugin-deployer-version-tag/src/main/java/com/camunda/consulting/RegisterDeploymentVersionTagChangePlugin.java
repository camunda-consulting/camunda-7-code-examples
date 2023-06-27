package com.camunda.consulting;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;

import java.util.ArrayList;

public class RegisterDeploymentVersionTagChangePlugin extends AbstractProcessEnginePlugin {
  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    if (processEngineConfiguration.getCustomPreDeployers() == null) {
      processEngineConfiguration.setCustomPreDeployers(new ArrayList<Deployer>());
    }
    processEngineConfiguration.getCustomPreDeployers().add(new VersionTagChangeDeployer());
  }
}
