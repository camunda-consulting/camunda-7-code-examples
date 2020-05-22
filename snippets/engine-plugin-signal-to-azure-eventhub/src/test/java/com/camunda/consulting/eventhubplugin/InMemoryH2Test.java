package com.camunda.consulting.eventhubplugin;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.EventHubException;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.spin.json.SpinJsonNode;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.camunda.spin.Spin.JSON;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  private static final String PROCESS_DEFINITION_KEY = "camunda-plugin-signal-to-event";

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Mock
  EventHubClient eventHubClient;

  @Before
  public void setup() {
    init(rule.getProcessEngine());
    MockitoAnnotations.initMocks(this);
    AzureEventHubClient.setEventHubClient(eventHubClient);
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() throws EventHubException {
    ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, Variables.putValue("testVariable", "testValue"));

    assertThat(processInstance).task("Task_DoSomething");

    verify(eventHubClient, times(1)).sendSync(any(EventData.class));

    complete(task());

    assertThat(processInstance).isEnded();

    ArgumentCaptor<EventData> eventDataCaptor = ArgumentCaptor.forClass(EventData.class);
    verify(eventHubClient, times(3)).sendSync(eventDataCaptor.capture());


    List<SpinJsonNode> payloads = eventDataCaptor.getAllValues().stream().map(eventData->JSON(new String(eventData.getBytes()))).collect(Collectors.toList());

    assertThat(payloads.get(0).prop("payload").prop("testVariable").stringValue()).isEqualTo("testValue");
    assertThat(payloads.get(0).prop("payload").prop("testExpression").stringValue()).isEqualTo("Here we go 2 times");

    assertThat(payloads.get(2).prop("payload").prop("testObject").prop("key2").stringValue()).isEqualTo("val2");
  }

}
