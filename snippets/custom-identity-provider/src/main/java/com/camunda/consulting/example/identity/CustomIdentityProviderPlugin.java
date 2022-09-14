package com.camunda.consulting.example.identity;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomIdentityProviderPlugin extends AbstractProcessEnginePlugin {
  private final CustomIdentityService identityService;

  @Autowired
  public CustomIdentityProviderPlugin(CustomIdentityService identityService) {this.identityService = identityService;}

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    // this allows any string as user id
    processEngineConfiguration.setGeneralResourceWhitelistPattern(".+");
    processEngineConfiguration.setIdentityProviderSessionFactory(new CustomIdentityProviderFactory(identityService));
  }
}
