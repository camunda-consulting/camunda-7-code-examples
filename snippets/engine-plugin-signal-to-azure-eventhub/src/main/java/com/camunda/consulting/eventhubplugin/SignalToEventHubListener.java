package com.camunda.consulting.eventhubplugin;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.model.bpmn.instance.EventDefinition;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.SignalEventDefinition;
import org.camunda.bpm.model.bpmn.instance.ThrowEvent;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaIn;
import org.camunda.spin.json.SpinJsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.camunda.spin.Spin.JSON;

public class SignalToEventHubListener implements ExecutionListener {

  private final Logger LOGGER = LoggerFactory.getLogger(SignalToEventHubListener.class);

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    String currentActivityId = execution.getCurrentActivityId();
    String currentActivityName = execution.getCurrentActivityName();
    String processBusinessKey = execution.getProcessBusinessKey();

    ThrowEvent throwEvent = (ThrowEvent) execution.getBpmnModelElementInstance();

    if (throwEvent.getEventDefinitions().size() != 1) {
      throw new RuntimeException("Signal throw event should have exactly one event definition");
    }
    EventDefinition eventDefinition = throwEvent.getEventDefinitions().iterator().next();
    if (!(eventDefinition instanceof SignalEventDefinition)) {
      throw new RuntimeException("Signal throw event does not have signal event definition");
    }
    SignalEventDefinition signalEventDefinition = (SignalEventDefinition) eventDefinition;
    String signalName = signalEventDefinition.getSignal().getName();

    SpinJsonNode payload = JSON("{}");

    ExtensionElements extensionElements = signalEventDefinition.getExtensionElements();
    if (extensionElements != null) {
      extensionElements.getElementsQuery().filterByType(CamundaIn.class).list().forEach(camundaIn -> {

        Object value;
        if (camundaIn.getCamundaSource() != null) {
          value = execution.getVariable(camundaIn.getCamundaSource());
        } else if (camundaIn.getCamundaSourceExpression() != null) {
          value = Context.getProcessEngineConfiguration().getExpressionManager()
              .createExpression(camundaIn.getCamundaSourceExpression())
              .getValue(execution);
        } else {
          throw new RuntimeException("Either source or sourceExpression must be set");
        }

        if (value instanceof String) {
          payload.prop(camundaIn.getCamundaTarget(), (String) value);
        } else {
          payload.prop(camundaIn.getCamundaTarget(), JSON(value));
        }

      });
    }

    SpinJsonNode message = JSON("{}");
    message.prop("signalName", signalName);
    message.prop("symbolName", currentActivityName);
    message.prop("symbolId", currentActivityId);
    message.prop("businessKey", processBusinessKey);
    message.prop("payload", payload);

    LOGGER.trace(message.toString());
    AzureEventHubClient.sendMessage(message);
  }
}
