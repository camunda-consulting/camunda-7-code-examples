package com.camunda.consulting.eventhub.plugin;

import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

    private static final String PROCESS_DEFINITION_KEY = "camunda-plugin-signal-to-event";

    @Rule
    public ProcessEngineRule rule = new ProcessEngineRule();

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    EventHubClientBuilder clientBuilder;
    @Mock
    EventHubProducerClient producer;
    @Mock
    EventDataBatch dataBatch;

    @Before
    public void setup() {
        init(rule.getProcessEngine());
        doReturn(dataBatch).when(producer).createBatch();
        doReturn(producer).when(clientBuilder).buildProducerClient();
        AzureEventHubClient.setClientBuilder(clientBuilder);
    }

    @Test
    @Deployment(resources = "process.bpmn")
    public void testHappyPath() {
        AzureEventHubClient.setClientBuilder(clientBuilder);
        ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY, Variables.putValue("testVariable", "testValue"));
        assertThat(processInstance).task("Task_DoSomething");
        verify(producer, times(1)).send(any(EventDataBatch.class));

        complete(task());
        assertThat(processInstance).isEnded();

        /*ArgumentCaptor<EventDataBatch> eventDataCaptor = ArgumentCaptor.forClass(EventDataBatch.class);
        verify(producer, times(3)).send(eventDataCaptor.capture());

        List<SpinJsonNode> payloads = eventDataCaptor.getAllValues().stream().map(dataBatch->JSON(new String(dataBatch.))).collect(Collectors.toList());

        assertThat(payloads.get(0).prop("payload").prop("testVariable").stringValue()).isEqualTo("testValue");
        assertThat(payloads.get(0).prop("payload").prop("testExpression").stringValue()).isEqualTo("Here we go 2 times");
        assertThat(payloads.get(2).prop("payload").prop("testObject").prop("key2").stringValue()).isEqualTo("val2");*/
    }

}
