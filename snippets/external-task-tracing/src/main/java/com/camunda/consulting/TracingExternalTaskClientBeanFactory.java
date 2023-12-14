package com.camunda.consulting;

import io.micrometer.observation.ObservationRegistry;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.ExternalTaskClientBuilder;
import org.camunda.bpm.client.spring.boot.starter.impl.PropertiesAwareClientFactory;
import org.springframework.stereotype.Component;

@Component
public class TracingExternalTaskClientBeanFactory extends PropertiesAwareClientFactory {
  private final ObservationRegistry observationRegistry;

  public TracingExternalTaskClientBeanFactory(ObservationRegistry observationRegistry) {
    this.observationRegistry = observationRegistry;
  }

  @Override
  public ExternalTaskClient getObject() {
    if (client == null) {
      ExternalTaskClientBuilder clientBuilder = new TracingExternalTaskClientBuilderImpl(observationRegistry);
      if (clientConfiguration.getBaseUrl() != null) {
        clientBuilder.baseUrl(resolve(clientConfiguration.getBaseUrl()));
      }
      if (clientConfiguration.getWorkerId() != null) {
        clientBuilder.workerId(resolve(clientConfiguration.getWorkerId()));
      }

      addClientRequestInterceptors(clientBuilder);

      if (clientConfiguration.getMaxTasks() != null) {
        clientBuilder.maxTasks(clientConfiguration.getMaxTasks());
      }
      if (clientConfiguration.getUsePriority() != null && !clientConfiguration.getUsePriority()) {
        clientBuilder.usePriority(false);
      }
      if (clientConfiguration.getDefaultSerializationFormat() != null) {
        clientBuilder.defaultSerializationFormat(resolve(clientConfiguration.getDefaultSerializationFormat()));
      }
      if (clientConfiguration.getDateFormat() != null) {
        clientBuilder.dateFormat(resolve(clientConfiguration.getDateFormat()));
      }
      if (clientConfiguration.getAsyncResponseTimeout() != null) {
        clientBuilder.asyncResponseTimeout(clientConfiguration.getAsyncResponseTimeout());
      }
      if (clientConfiguration.getLockDuration() != null) {
        clientBuilder.lockDuration(clientConfiguration.getLockDuration());
      }
      if (clientConfiguration.getDisableAutoFetching() != null && clientConfiguration.getDisableAutoFetching()) {
        clientBuilder.disableAutoFetching();
      }
      if (clientConfiguration.getDisableBackoffStrategy() != null && clientConfiguration.getDisableBackoffStrategy()) {
        clientBuilder.disableBackoffStrategy();
      }
      if (backoffStrategy != null) {
        clientBuilder.backoffStrategy(backoffStrategy);
      }
      client = clientBuilder.build();
    }
    return super.getObject();
  }
}
