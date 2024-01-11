package com.camunda.consulting;

import org.camunda.community.extension.internalTaskHandler.InternalTask;
import org.camunda.community.extension.internalTaskHandler.InternalTaskHandler;
import org.camunda.community.extension.internalTaskHandler.InternalTaskService;
import org.camunda.community.extension.internalTaskHandler.springBoot.InternalTaskSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@InternalTaskSubscription(topicName = "send-message", lockDuration = "PT1M")
public class OutboundMessageHandler implements InternalTaskHandler {
  private static final Logger LOG = LoggerFactory.getLogger(OutboundMessageHandler.class);

  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public OutboundMessageHandler(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void execute(InternalTask internalTask, InternalTaskService internalTaskService) {
    LOG.info("Sending message from task with id '{}'", internalTask.getId());
    rabbitTemplate.convertAndSend("q.example", new Message(internalTask.getId(), "abcabc"));
  }
}
