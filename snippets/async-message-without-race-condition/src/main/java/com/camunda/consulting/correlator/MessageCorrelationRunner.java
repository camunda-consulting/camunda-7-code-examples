package com.camunda.consulting.correlator;

import java.util.function.Function;

import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;

/**
 * Invoked by the {@link ActualMessageCorrelator}
 * 
 * @author jonathanlukas
 *
 */
public interface MessageCorrelationRunner extends Function<MessageCorrelationBuilder, MessageCorrelationResult>
{
	String getMessageName();
}
