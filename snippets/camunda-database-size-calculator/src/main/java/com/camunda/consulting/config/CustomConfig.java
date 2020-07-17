package com.camunda.consulting.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties("custom")
@PropertySource("classpath:custom.yaml")
@Data
@Component
public class CustomConfig {

    private Long processInstanceAmount = 10L;
    private String processKey;
    private String databaseName;
}
