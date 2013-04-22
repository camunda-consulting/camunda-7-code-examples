package com.camunda.demo.outerspace.wjax.controller.performance;


import java.io.InputStream;

import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.util.ReflectUtil;

public class MyBatisExtendedSessionFactory extends StandaloneProcessEngineConfiguration {

  private String resourceName;

  protected void init() {
    throw new IllegalArgumentException(
            "Normal 'init' on process engine only used for extended MyBatis mappings is not allowed, please use 'initFromProcessEngineConfiguration'. You cannot construct a process engine with this configuration.");
  }

  /**
   * initialize the {@link ProcessEngineConfiguration} from an existing one,
   * just using the database settings and initialize the database / MyBatis
   * stuff.
   */
  public void initFromProcessEngineConfiguration(ProcessEngineConfigurationImpl processEngineConfiguration, String resourceName) {
    this.resourceName = resourceName;

    setDataSource(processEngineConfiguration.getDataSource());

    initDataSource();
    initVariableTypes();
    initCommandContextFactory();
    initTransactionFactory();
    initTransactionContextFactory();
    initCommandExecutors();
    initSqlSessionFactory();
    initSessionFactories();
  }

  @Override
  protected InputStream getMyBatisXmlConfigurationSteam() {
    return ReflectUtil.getResourceAsStream(resourceName);
  }
}