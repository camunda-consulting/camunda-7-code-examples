package com.camunda.consulting;

import com.camunda.consulting.config.CustomConfig;
import com.camunda.consulting.db.DatabaseCalculator;
import com.camunda.consulting.simulator.ProcessInstanceExecutor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final CustomConfig customConfig;
    private final DatabaseCalculator databaseReporter;
    private final ProcessInstanceExecutor<Long> processInstanceExecutor;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        logger.info("Creating {} process instances for process with key {}", customConfig.getProcessInstanceAmount(), customConfig.getProcessKey());
        LongStream.rangeClosed(1, customConfig.getProcessInstanceAmount()).forEach(processInstanceExecutor::executeInstance);
        databaseReporter.calculateDatabaseSize();
    }
}
