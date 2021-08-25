package org.example;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.ProcessCoverageConfigurator;
import org.springframework.stereotype.Component;

@Component
public class ProcessTestCoverageProcessEnginePlugin extends AbstractProcessEnginePlugin implements ProcessEnginePlugin {

  @Override
  // TODO change back to preInit once this pull request is merged and released:
  // https://github.com/camunda/camunda-bpm-process-test-coverage/pull/50
  // using postInit removes all other history listeners from the configuration
  // including the Spring Eventing Bridge: https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/the-spring-event-bridge/
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    ProcessCoverageConfigurator.initializeProcessCoverageExtensions(processEngineConfiguration);
  }

}
