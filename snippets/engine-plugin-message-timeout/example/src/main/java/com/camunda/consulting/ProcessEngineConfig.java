package com.camunda.consulting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessEngineConfig {

    @Bean
    public MessageTimeoutPlugin processEnginePlugin() {
        return new MessageTimeoutPlugin();
    }

}
