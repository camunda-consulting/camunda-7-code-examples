package com.camunda.consulting;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.Expression;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandlerConfiguration;
import org.camunda.bpm.engine.impl.jobexecutor.TimerEventJobHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.camunda.bpm.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.camunda.bpm.engine.impl.util.ClassDelegateUtil.instantiateDelegate;

public class TimerMessageListenerJobHandler implements JobHandler<TimerMessageListenerJobHandler.MessageTimeoutJobHandlerConfiguration> {

    public static final String TYPE = "timer-message-listener";
    private final Logger logger = LoggerFactory.getLogger(getType());
    public final static String TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER = "|";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void execute(MessageTimeoutJobHandlerConfiguration configuration, ExecutionEntity execution, CommandContext commandContext, String tenantId) {
        logger.info("YAY! We got it into the JobHandler! Configuration: {}", configuration.toString());

        DelegateExecution actualExecution = commandContext.getExecutionManager().findExecutionById(configuration.getExecutionId());

        TimeoutListenerTypes timeoutListenerType = configuration.timeoutListenerType;
        String timeoutListener = configuration.getTimeoutListener();

        if (timeoutListenerType.equals(TimeoutListenerTypes.DELEGATEEXPRESSION)) {
            Expression expression = commandContext.getProcessEngineConfiguration().getExpressionManager().createExpression(configuration.getTimeoutListener());

            ExecutionListener executionListener = (ExecutionListener) expression.getValue(execution);
            try {
                executionListener.notify(actualExecution);
            } catch (Exception e) {
                logger.error("Error when calling listener", e);
            }
        } else if (timeoutListenerType.equals(TimeoutListenerTypes.EXPRESSION)) {
            Expression expression = commandContext.getProcessEngineConfiguration().getExpressionManager().createExpression(configuration.getTimeoutListener());
            expression.getValue(actualExecution);
        } else if (timeoutListenerType.equals(TimeoutListenerTypes.JAVA)) {
            ExecutionListener executionListener = (ExecutionListener) instantiateDelegate(timeoutListener, new ArrayList<>());
            try {
                executionListener.notify(actualExecution);
            } catch (Exception e) {
                logger.error("Error when calling listener", e);
            }

        } else if (timeoutListenerType.equals(TimeoutListenerTypes.SCRIPT)) {
            throw new UnsupportedOperationException("We don't support script yet.");
        }
    }

    @Override
    public MessageTimeoutJobHandlerConfiguration newConfiguration(String canonicalString) {
        String[] configParts = canonicalString.split("\\" + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER);
        if (configParts.length != 3) {
            throw new ProcessEngineException("Illegal timer message listener job handler configuration: '" + canonicalString
                    + "': expecting three part configuration seperated by '" + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER + "'.");
        }

        return new MessageTimeoutJobHandlerConfiguration(configParts[0], TimeoutListenerTypes.valueOf(configParts[1]), configParts[2]);
    }

    @Override
    public void onDelete(MessageTimeoutJobHandlerConfiguration configuration, JobEntity jobEntity) {
        //For now, we do nothing
    }

    public static class MessageTimeoutJobHandlerConfiguration implements JobHandlerConfiguration {

        private final String timeoutListener;
        private final TimeoutListenerTypes timeoutListenerType;
        private final String executionId;

        public MessageTimeoutJobHandlerConfiguration(String timeoutListener, TimeoutListenerTypes timeoutListenerType, String executionId) {
            this.timeoutListener = timeoutListener;
            this.timeoutListenerType = timeoutListenerType;
            this.executionId = executionId;
        }

        public String getExecutionId() {
            return executionId;
        }

        public String getTimeoutListener() {
            return timeoutListener;
        }

        public TimeoutListenerTypes getTimeoutListenerType() {
            return timeoutListenerType;
        }

        @Override
        public String toCanonicalString() {
            return timeoutListener + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER + timeoutListenerType + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER + executionId;
        }

        @Override
        public String toString() {
            return "MessageTimeoutJobHandlerConfiguration{" +
                    "timeoutListener='" + timeoutListener + '\'' +
                    ", timeoutListenerType=" + timeoutListenerType +
                    ", executionId='" + executionId + '\'' +
                    '}';
        }
    }
}
