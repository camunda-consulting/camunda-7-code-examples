package com.camunda.consulting.actuator;

import java.util.Objects;

import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.metrics.Meter;
import org.camunda.bpm.engine.management.Metrics;
import org.camunda.bpm.engine.runtime.JobQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class JobExecutorMetricsConfiguration {
  
  private static final String NUMBER_OF_EXCLUSIVE_JOBS = "Number of exclusive jobs";
  private static final String NUMBER_OF_ACQUISITION_CYCLES = "Number of acquisition cycles";
  private static final String NUMBER_OF_JOBS = "Number of jobs";
  
  private final ManagementService service;
  private final ProcessEngineConfigurationImpl engineConf;

  public JobExecutorMetricsConfiguration(ManagementService service, ProcessEngine engine) {
    super();
    this.service = Objects.requireNonNull(service);
    this.engineConf = (ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration();
  }

  @Bean
  public Gauge jobExecutionsSuccessful(MeterRegistry registry) {
    Meter jobSuccessfulMeter = engineConf.getMetricsRegistry().getDbMeterByName(Metrics.JOB_SUCCESSFUL);
    
    return Gauge.builder("job.executions.successful", jobSuccessfulMeter::get)
        .description("Successful job executions")
        .baseUnit(NUMBER_OF_JOBS)
        .register(registry);
  }
  
  @Bean
  public Gauge jobExecutionsFailed(MeterRegistry registry) {
    Meter jobFailedMeter = engineConf.getMetricsRegistry().getDbMeterByName(Metrics.JOB_FAILED);
    
    return Gauge.builder("job.executions.failed", jobFailedMeter::get)
        .description("Failed job executions")
        .baseUnit(NUMBER_OF_JOBS)
        .register(registry);
  }
  
  @Bean
  public Gauge jobExecutionsRejected(MeterRegistry registry) {
    Meter jobExecutionsRejected = engineConf.getMetricsRegistry().getDbMeterByName(Metrics.JOB_EXECUTION_REJECTED);
    
    return Gauge.builder("job.executions.rejected", jobExecutionsRejected::get)
        .description("Rejected jobs due to saturated execution resources")
        .baseUnit(NUMBER_OF_JOBS)
        .register(registry);
  }

  @Bean
  public Gauge jobAcquisitionsAttempted(MeterRegistry registry) {
    Meter jobAcquisitionsAttempted = engineConf.getMetricsRegistry().getDbMeterByName(Metrics.JOB_ACQUISITION_ATTEMPT);
    
    return Gauge.builder("job.acquisitions.attempted", jobAcquisitionsAttempted::get)
        .description("Performed job acquisition cycles")
        .baseUnit(NUMBER_OF_ACQUISITION_CYCLES)
        .register(registry);
  }

  @Bean 
  public Gauge jobAcquisitionsSuccessful(MeterRegistry registry) {
    Meter jobAcquisitionsSuccessful = engineConf.getMetricsRegistry().getDbMeterByName(Metrics.JOB_ACQUIRED_SUCCESS);
    
    return Gauge.builder("job.acquisitions.successful", jobAcquisitionsSuccessful::get)
        .description("Successful job acquisitions")
        .baseUnit(NUMBER_OF_JOBS)
        .register(registry);
  }
  
  @Bean
  public Gauge jobAcquistionsFailed(MeterRegistry registry) {
    Meter jobAcquisitionsFailed = engineConf.getMetricsRegistry().getDbMeterByName(Metrics.JOB_ACQUIRED_FAILURE);
    
    return Gauge.builder("job.acquisitions.failed", jobAcquisitionsFailed::get)
        .description("Failed job acquisitions")
        .baseUnit(NUMBER_OF_JOBS)
        .register(registry);
  }
  
  @Bean
  public Gauge jobLocksExclusive(MeterRegistry registry) {
    Meter jobLocksExclusive = engineConf.getMetricsRegistry().getDbMeterByName(Metrics.JOB_LOCKED_EXCLUSIVE);
    
    return Gauge.builder("job.locks.exclusive", jobLocksExclusive::get)
        .description("Exclusive jobs that are immediately locked and executed")
        .baseUnit(NUMBER_OF_EXCLUSIVE_JOBS)
        .register(registry);
  }
  
  @Bean
  public Gauge dueJobsinDB(MeterRegistry registry) {
    JobQuery jobQuery = service.createJobQuery().executable().messages();
    
    return Gauge.builder("jobs.due", jobQuery::count)
        .description("Jobs from async continuation that are due")
        .register(registry);
  }
  
}
