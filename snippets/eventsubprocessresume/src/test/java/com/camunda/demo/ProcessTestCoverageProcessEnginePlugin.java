package com.camunda.demo;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.ProcessCoverageConfigurator;
import org.springframework.stereotype.Component;

@Component
public class ProcessTestCoverageProcessEnginePlugin extends AbstractProcessEnginePlugin implements ProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    ProcessCoverageConfigurator.initializeProcessCoverageExtensions(processEngineConfiguration);
  }

}
