package org.camunda.bpm.demo;

import org.camunda.bpm.demo.listener.ControlTimeListenerEnd;
import org.camunda.bpm.demo.listener.ControlTimeListenerStart;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@Deployment(resources = {"control-message-event.bpmn", "process.bpmn"})
public class TestProcessWithControlTimeProcess {

    private final String PROCESS_KEY = "MessageControlExampleProcess";

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();
    private ProcessInstance processInstance;
    private ProcessInstance cancelControlProcessInstance;

    @Test
    public void testMessageWithoutTimeout(){
        mainTestBlock();

        runtimeService().createMessageCorrelation("MessageToControl").correlate();

        assertThat(cancelControlProcessInstance).isEnded();
        assertThat(processInstance).isEnded();

        assertThat(cancelControlProcessInstance).hasPassed("EndWithoutTimeout");
        assertThat(cancelControlProcessInstance).hasNotPassed("EndWithTimeout");
    }

    @Test
    public void testMessageWithTimeout(){
        mainTestBlock();

        execute(job());

        assertThat(cancelControlProcessInstance).isNotEnded();
        assertThat(processInstance).isNotEnded();

        assertThat(cancelControlProcessInstance).hasPassed("NotificationTask").hasPassed("EndWithTimeout").hasNotPassed("EndWithoutTimeoutit ");
    }

    private void mainTestBlock(){
        Mocks.register("controlTimeListenerStart", new ControlTimeListenerStart());
        Mocks.register("controlTimeListenerEnd", new ControlTimeListenerEnd());

        processInstance = runtimeService().startProcessInstanceByKey(PROCESS_KEY);

        assertThat(processInstance).isWaitingAt("HumanTask");
        complete(task());

        assertThat(processInstance).isWaitingAt("MessageTask");

        List<ProcessInstance> cancelControlProcesses = processInstanceQuery().processDefinitionKey("MonitorMessageEvent").list();

        assertThat(cancelControlProcesses.size()).isEqualTo(1);
        cancelControlProcessInstance = cancelControlProcesses.get(0);

        assertThat(cancelControlProcessInstance).isWaitingAt("CancelControlTask");
        assertThat(jobQuery().timers().list().size()).isEqualTo(1);
    }
}
