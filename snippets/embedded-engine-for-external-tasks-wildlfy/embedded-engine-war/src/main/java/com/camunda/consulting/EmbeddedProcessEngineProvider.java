package com.camunda.consulting;

import java.util.Set;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.spi.ProcessEngineProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedProcessEngineProvider implements ProcessEngineProvider {
  
  private static final Logger LOG = LoggerFactory.getLogger(EmbeddedProcessEngineProvider.class);

  public ProcessEngine getDefaultProcessEngine() {
    LOG.debug("Default engine from REST wanted");
    return MyMainApplication.processEngine;
  }

  public ProcessEngine getProcessEngine(String name) {
    LOG.debug("Named engine {} from REST wanted", name);
    return MyMainApplication.processEngine;
  }

  public Set<String> getProcessEngineNames() {
    LOG.debug("Process engine names from REST wanted");
    return Set.of("default");
  }

}
