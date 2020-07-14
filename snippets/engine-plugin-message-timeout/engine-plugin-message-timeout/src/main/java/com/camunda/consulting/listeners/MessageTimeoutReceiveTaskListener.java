package com.camunda.consulting.listeners;

import com.camunda.consulting.TimeoutListenerTypes;
import com.camunda.consulting.TimerMessageListenerJobHandler;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.el.Expression;
import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static com.camunda.consulting.TimerMessageListenerJobHandler.TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER;

public class MessageTimeoutReceiveTaskListener implements ExecutionListener {

    private final Expression timeoutDuration;
    private final String timeoutListener;
    private final TimeoutListenerTypes timeoutListenerType;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MessageTimeoutReceiveTaskListener(Expression timeoutDuration, String timeoutListener, TimeoutListenerTypes timeoutListenerType) {
        this.timeoutDuration = timeoutDuration;
        this.timeoutListener = timeoutListener;
        this.timeoutListenerType = timeoutListenerType;
    }

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        Date dueDate = (Date) timeoutDuration.getValue(execution);

        ProcessDefinition processDefinition = execution.getProcessEngine().getRepositoryService()
                .createProcessDefinitionQuery().processDefinitionId(execution.getProcessDefinitionId()).singleResult();

        TimerEntity timer = new TimerEntity();

        timer.setDeploymentId(processDefinition.getDeploymentId());
        timer.setProcessDefinitionId(processDefinition.getId());
        timer.setProcessDefinitionKey(processDefinition.getKey());
        timer.setTenantId(processDefinition.getTenantId());
        timer.setDuedate(dueDate);
        timer.setExecutionId(execution.getId());

        timer.setJobHandlerType(TimerMessageListenerJobHandler.TYPE);
        timer.setJobHandlerConfigurationRaw(timeoutListener + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER + timeoutListenerType + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER + execution.getId());

        logger.info("Create start process instance job for: " + processDefinition.getKey());

        Context.getCommandContext().getJobManager().schedule(timer);
    }
}
