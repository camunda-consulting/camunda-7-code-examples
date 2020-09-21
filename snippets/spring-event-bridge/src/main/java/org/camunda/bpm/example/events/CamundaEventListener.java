package org.camunda.bpm.example.events;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.spring.boot.starter.event.ExecutionEvent;
import org.camunda.bpm.spring.boot.starter.event.TaskEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * On the execution event stream, DelegateExecutions (mutable) and ExecutionEvents (immutable) can be received.
 * The task event stream offers DelegateTasks (mutable) and TaskEvents (immutable).
 * On the history event stream, only HistoryEvents (mutable) are published.
 *
 * @see <a href="https://docs.camunda.org/manual/latest/user-guide/spring-boot-integration/the-spring-event-bridge/"</a>
 */
@Slf4j
@Component
public class CamundaEventListener {

    @EventListener
    public void onExecutionEvent(DelegateExecution executionDelegate) {
        // handle mutable execution event
        log.info("ExecutionEvent listener processing mutable event. \n Current ActivityId: {} ExecutionId: {} \n >>> Event Name: {}  \n",
                executionDelegate.getCurrentActivityId(), executionDelegate.getId(), executionDelegate.getEventName());
    }

    @EventListener
    public void onHistoryEvent(HistoryEvent historyEvent) {
        // handle history event
        log.info("HistoryEvent listener processing mutable event. \n ProcessDefinitionKey: {} ProcessInstanceId: {} \n EventId: {} \n >>> EventType: {} \n",
                historyEvent.getProcessDefinitionKey(), historyEvent.getProcessInstanceId(), historyEvent.getId(), historyEvent.getEventType());
    }

    /**
     * The mutable event stream objects can be modified multiple times between creation and reception of the
     * event the listener has asynchronously subscribed to. However, immutable event objects reflect the state
     * at the event creation time, regardless of the time they are received by the listener.
     */

    @EventListener
    public void onExecutionEvent(ExecutionEvent executionEvent) {
        // handle immutable execution event
        log.info("ExecutionEvent listener processing immutable event. \n Current ActivityId: {} ExecutionId: {} \n >>> Event Name: {}  \n",
                executionEvent.getCurrentActivityId(), executionEvent.getId(), executionEvent.getEventName());
    }

    @EventListener
    public void onTaskEvent(TaskEvent taskEvent) {
        // handle immutable task event
        log.info("TaskEvent listener processing immutable task event. \n TaskDefinitionKey: {} TaskId: {} \n >>> Event Name: {}  \n",
                taskEvent.getTaskDefinitionKey(), taskEvent.getId(), taskEvent.getEventName());
    }

    /**
     * Multiple listeners for the same event type can be registered. Their order is defined via the @order annotation.
     *
     * @param taskDelegate event's context information
     */
    @Order(1)
    @EventListener
    public void firstTaskEventListener(DelegateTask taskDelegate) {
        // handle mutable task event
        log.info("1st TaskEvent listener processing mutable event. \n TaskDefinitionKey: {} TaskId: {} \n >>> Event Name: {}  \n",
                taskDelegate.getTaskDefinitionKey(), taskDelegate.getId(), taskDelegate.getEventName());
    }

    @Order(2)
    @EventListener
    public void secondTaskEventListener(DelegateTask taskDelegate) {
        // handle mutable task event
        log.info("2nd TaskEvent listener processing mutable event. \n TaskDefinitionKey: {} TaskId: {} \n >>> Event Name: {}  \n",
                taskDelegate.getTaskDefinitionKey(), taskDelegate.getId(), taskDelegate.getEventName());
    }
}
