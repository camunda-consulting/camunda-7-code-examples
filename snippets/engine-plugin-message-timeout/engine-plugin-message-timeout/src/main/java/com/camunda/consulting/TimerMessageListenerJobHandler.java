package com.camunda.consulting;

import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.impl.el.Expression;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandlerConfiguration;
import org.camunda.bpm.engine.impl.jobexecutor.TimerEventJobHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.persistence.entity.JobEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.camunda.bpm.engine.impl.util.ClassDelegateUtil.instantiateDelegate;

public class TimerMessageListenerJobHandler implements JobHandler<TimerMessageListenerJobHandler.MessageTimeoutJobHandlerConfiguration> {

    public static final String TYPE = "timer-message-listener";
    private final Logger logger = LoggerFactory.getLogger(getType());
    public final static String TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER = "-";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void execute(MessageTimeoutJobHandlerConfiguration configuration, ExecutionEntity execution, CommandContext commandContext, String tenantId) {
        logger.info("YAY! We got it into the JobHandler! Configuration: {}", configuration.toString());

        TimeoutListenerTypes timeoutListenerType = configuration.timeoutListenerType;
        String timeoutListener = configuration.getTimeoutListener();

        if (timeoutListenerType.equals(TimeoutListenerTypes.DELEGATEEXPRESSION)) {
            Expression expression = commandContext.getProcessEngineConfiguration().getExpressionManager().createExpression(configuration.getTimeoutListener());

            ExecutionListener executionListener = (ExecutionListener) expression.getValue(execution);
            try {
                executionListener.notify(execution);
            } catch (Exception e) {
                logger.error("Error when calling listener", e);
            }
        } else if (timeoutListenerType.equals(TimeoutListenerTypes.EXPRESSION)) {
            Expression expression = commandContext.getProcessEngineConfiguration().getExpressionManager().createExpression(configuration.getTimeoutListener());
            expression.getValue(execution);
        } else if (timeoutListenerType.equals(TimeoutListenerTypes.JAVA)) {
            instantiateDelegate(timeoutListener, new ArrayList<>());

        } else if (timeoutListenerType.equals(TimeoutListenerTypes.SCRIPT)) {
            throw new UnsupportedOperationException("We don't support script yet.");
        }
    }

    @Override
    public MessageTimeoutJobHandlerConfiguration newConfiguration(String canonicalString) {
        String[] configParts = canonicalString.split("\\" + TIMER_MESSAGE_LISTENER_JOB_HANDLER_DELIMITER);
        if (configParts.length != 2) {
            throw new ProcessEngineException("Illegal timer message listener job handler configuration: '" + canonicalString
                    + "': expecting two part configuration seperated by '" + TimerEventJobHandler.JOB_HANDLER_CONFIG_PROPERTY_DELIMITER + "'.");
        }

        return new MessageTimeoutJobHandlerConfiguration(configParts[0], TimeoutListenerTypes.valueOf(configParts[1]));
    }

    @Override
    public void onDelete(MessageTimeoutJobHandlerConfiguration configuration, JobEntity jobEntity) {
        //For now, we do nothing
    }

    public static class MessageTimeoutJobHandlerConfiguration implements JobHandlerConfiguration {

        private final String timeoutListener;
        private final TimeoutListenerTypes timeoutListenerType;

        public MessageTimeoutJobHandlerConfiguration(String timeoutListener, TimeoutListenerTypes timeoutListenerType) {
            this.timeoutListener = timeoutListener;
            this.timeoutListenerType = timeoutListenerType;
        }

        public String getTimeoutListener() {
            return timeoutListener;
        }

        public TimeoutListenerTypes getTimeoutListenerType() {
            return timeoutListenerType;
        }

        @Override
        public String toCanonicalString() {
            return timeoutListener + TimerEventJobHandler.JOB_HANDLER_CONFIG_PROPERTY_DELIMITER + timeoutListenerType;
        }

        @Override
        public String toString() {
            return "MessageTimeoutJobHandlerConfiguration{" +
                    "timeoutListener='" + timeoutListener + '\'' +
                    ", timeoutListenerType='" + timeoutListenerType + '\'' +
                    '}';
        }
    }
}
