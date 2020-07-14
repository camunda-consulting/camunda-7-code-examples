package com.camunda.consulting;

import org.camunda.bpm.engine.impl.bpmn.behavior.ReceiveTaskActivityBehavior;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.el.Expression;
import org.camunda.bpm.engine.impl.persistence.entity.TimerEntity;
import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static com.camunda.consulting.TimerMessageListenerJobHandler.TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER;

public class MessageTimeoutRTActivityBehavior extends ReceiveTaskActivityBehavior {

    private final Expression timeoutDuration;
    private final String timeoutListener;
    private final TimeoutListenerTypes timeoutListenerType;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MessageTimeoutRTActivityBehavior(Expression timeoutDuration, String timeoutListener, TimeoutListenerTypes timeoutListenerType) {
        this.timeoutDuration = timeoutDuration;
        this.timeoutListener = timeoutListener;
        this.timeoutListenerType = timeoutListenerType;
    }

    @Override
    public void performExecution(ActivityExecution execution) throws Exception {
        Date dueDate = (Date) timeoutDuration.getValue(execution);

        ProcessDefinition processDefinition = execution.getProcessEngine().getRepositoryService()
                .createProcessDefinitionQuery().processDefinitionId(execution.getProcessDefinitionId()).singleResult();

        TimerEntity timer = new TimerEntity();

        timer.setDeploymentId(processDefinition.getDeploymentId());
        timer.setProcessDefinitionId(processDefinition.getId());
        timer.setProcessDefinitionKey(processDefinition.getKey());
        timer.setTenantId(processDefinition.getTenantId());
        timer.setDuedate(dueDate);

        timer.setJobHandlerType(TimerMessageListenerJobHandler.TYPE);
        timer.setJobHandlerConfigurationRaw(timeoutListener + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER + timeoutListenerType);

        logger.info("Create start process instance job for: " + processDefinition.getKey());

        Context.getCommandContext().getJobManager().schedule(timer);
    }
}
