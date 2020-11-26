package com.example.workflow.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "batch.config")
public class ProcessBatchConfig {

    private Integer blockSize = 1000;
}
