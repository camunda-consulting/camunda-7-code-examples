package com.camunda.demo;

import com.camunda.demo.TenantIdProviderPlugIn.TenantIdProviderPlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.spring.boot.starter.CamundaBpmConfiguration;
import org.camunda.bpm.spring.boot.starter.configuration.Ordering;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by ben on 29.03.17.
 */
@Configuration
public class CamundaEngineConfig{

    @Bean
    @Order(Ordering.DEFAULT_ORDER + 1)
    public static ProcessEnginePlugin myCustomConfiguration() {
        return new TenantIdProviderPlugin();
    }

}
