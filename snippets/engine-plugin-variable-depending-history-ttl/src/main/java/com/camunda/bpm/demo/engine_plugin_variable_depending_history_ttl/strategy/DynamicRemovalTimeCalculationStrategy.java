package com.camunda.bpm.demo.engine_plugin_variable_depending_history_ttl.strategy;

import java.util.function.BiFunction;

import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;

/**
 * This interface just types a BiFunction
 * 
 * Any Implementation provides a strategy to find a history ttl dynamically
 * 
 * @author jonathanlukas
 *
 */
public interface DynamicRemovalTimeCalculationStrategy
	extends
	BiFunction<HistoricProcessInstanceEventEntity, ProcessDefinition, Integer>
{

}
