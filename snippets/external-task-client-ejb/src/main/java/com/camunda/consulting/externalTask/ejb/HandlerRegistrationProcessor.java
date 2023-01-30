package com.camunda.consulting.externalTask.ejb;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.topic.TopicSubscriptionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
@Startup
public class HandlerRegistrationProcessor {
  private static final Logger LOG = LoggerFactory.getLogger(HandlerRegistrationProcessor.class);
  @Inject ExternalTaskClient client;
  @Inject BeanManager beanManager;

  @PostConstruct
  public void start() {
    LOG.info("Start external task client app");
    client.start();
    beanManager
        .createInstance()
        .select(ExternalTaskHandler.class)
        .forEach(
            externalTaskHandler -> {
              Optional.ofNullable(
                      externalTaskHandler.getClass().getAnnotation(ExternalTaskSubscription.class))
                  .ifPresent(
                      annotation -> {
                        SubscriptionConfiguration configuration = new SubscriptionConfiguration();
                        configuration.fromAnnotation(annotation);
                        initialize(configuration, externalTaskHandler);
                      });
            });
  }

  private String[] toArray(List<String> list) {
    return list.toArray(new String[] {});
  }

  public void initialize(
      SubscriptionConfiguration subscriptionConfiguration,
      ExternalTaskHandler externalTaskHandler) {
    String topicName = subscriptionConfiguration.getTopicName();
    TopicSubscriptionBuilder topicSubscriptionBuilder =
        client.subscribe(topicName).handler(externalTaskHandler);

    List<String> variableNames = subscriptionConfiguration.getVariableNames();
    if (variableNames != null) {
      topicSubscriptionBuilder.variables(toArray(variableNames));
    }
    Long lockDuration = subscriptionConfiguration.getLockDuration();
    if (lockDuration != null) {
      topicSubscriptionBuilder.lockDuration(lockDuration);
    }
    Boolean localVariables = subscriptionConfiguration.getLocalVariables();
    if (localVariables != null && localVariables) {
      topicSubscriptionBuilder.localVariables(true);
    }
    String businessKey = subscriptionConfiguration.getBusinessKey();
    if (businessKey != null) {
      topicSubscriptionBuilder.businessKey(businessKey);
    }
    String processDefinitionId = subscriptionConfiguration.getProcessDefinitionId();
    if (processDefinitionId != null) {
      topicSubscriptionBuilder.processDefinitionId(processDefinitionId);
    }
    List<String> processDefinitionIdIn = subscriptionConfiguration.getProcessDefinitionIdIn();
    if (processDefinitionIdIn != null) {
      topicSubscriptionBuilder.processDefinitionIdIn(toArray(processDefinitionIdIn));
    }
    String processDefinitionKey = subscriptionConfiguration.getProcessDefinitionKey();
    if (processDefinitionKey != null) {
      topicSubscriptionBuilder.processDefinitionKey(processDefinitionKey);
    }
    List<String> processDefinitionKeyIn = subscriptionConfiguration.getProcessDefinitionKeyIn();
    if (processDefinitionKeyIn != null) {
      topicSubscriptionBuilder.processDefinitionKeyIn(toArray(processDefinitionKeyIn));
    }
    String processDefinitionVersionTag = subscriptionConfiguration.getProcessDefinitionVersionTag();
    if (processDefinitionVersionTag != null) {
      topicSubscriptionBuilder.processDefinitionVersionTag(processDefinitionVersionTag);
    }
    Map<String, Object> processVariablesEqualsIn = subscriptionConfiguration.getProcessVariables();
    if (processVariablesEqualsIn != null) {
      topicSubscriptionBuilder.processVariablesEqualsIn(processVariablesEqualsIn);
    }
    Boolean withoutTenantId = subscriptionConfiguration.getWithoutTenantId();
    if (withoutTenantId != null && withoutTenantId) {
      topicSubscriptionBuilder.withoutTenantId();
    }
    List<String> tenantIdIn = subscriptionConfiguration.getTenantIdIn();
    if (tenantIdIn != null) {
      topicSubscriptionBuilder.tenantIdIn(toArray(tenantIdIn));
    }
    Boolean includeExtensionProperties = subscriptionConfiguration.getIncludeExtensionProperties();
    if (includeExtensionProperties != null && includeExtensionProperties) {
      topicSubscriptionBuilder.includeExtensionProperties(true);
    }
    if (subscriptionConfiguration.getAutoOpen()) {
      topicSubscriptionBuilder.open();
    }
    LOG.info("Subscribed to '{}' with handler '{}'", topicName, externalTaskHandler);
  }

  @PreDestroy
  public void stop() {
    LOG.info("Stop external task client app");
    client.stop();
  }
}
