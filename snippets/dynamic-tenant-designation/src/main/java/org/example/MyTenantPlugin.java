package org.example;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Order(Ordering.DEFAULT_ORDER + 1)
public class MyTenantPlugin extends AbstractProcessEnginePlugin {

    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

        LOGGER.info("++ in plugin preinit....");
        processEngineConfiguration.setTenantIdProvider(new CustomTenantIdProvider());

    }

}