package com.camunda.consulting;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order
public class OverrideHistoryCleanupBatchSizeProcessEnginePlugin extends AbstractProcessEnginePlugin {
  private static final Logger LOG = LoggerFactory.getLogger(OverrideHistoryCleanupBatchSizeProcessEnginePlugin.class);
  private int historyCleanupBatchSize;

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    historyCleanupBatchSize = processEngineConfiguration.getHistoryCleanupBatchSize();
    LOG.info("Configured history cleanup batch size: {}", historyCleanupBatchSize);
    if (historyCleanupBatchSize > 500) {
      LOG.info("Working around fixed limit");
      processEngineConfiguration.setHistoryCleanupBatchSize(500);
    }
  }

  @Override
  public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    LOG.info("Setting history cleanup batch size to: {}", historyCleanupBatchSize);
    processEngineConfiguration.setHistoryCleanupBatchSize(historyCleanupBatchSize);
  }
}
