package com.camunda.consulting;

import org.camunda.bpm.dmn.engine.impl.el.JuelElProvider;
import org.camunda.bpm.dmn.engine.impl.spi.el.ElProvider;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.dmn.configuration.ProcessEngineDmnEngineConfiguration;
import org.camunda.bpm.engine.impl.dmn.el.ProcessEngineElProvider;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.variable.context.VariableContext;

import de.odysseus.el.util.SimpleContext;

public class DmnDecisionChainingPlugin extends AbstractProcessEnginePlugin {

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		
		processEngineConfiguration.getExpressionManager().addFunctionMapper(new DmnDecisionChaniningFunctionMapper());
		
//		
//		ProcessEngineDmnEngineConfiguration dmnEngineConfiguration = (ProcessEngineDmnEngineConfiguration) processEngineConfiguration.getDmnEngineConfiguration();
//		try {
//			ElProvider elProvider = dmnEngineConfiguration.getElProvider();
//			if (!(elProvider instanceof JuelElProvider)){
//				throw new RuntimeException(DmnDecisionChainingPlugin.class.getName() + " only works with ElProvider of type "+ JuelElProvider.class.getName() + " being used in DMN Engine. But found " + elProvider.getClass().getName());
//			}
//			JuelElProvider elProviderJuel = (JuelElProvider)elProvider;
//			ProcessEngineElProvider elProviderEngine = (ProcessEngineElProvider)elProvider;
//			ExpressionManager manager = null;
//			elProviderEngine.getClass()
//			
//			SimpleContext context = (SimpleContext) elProviderJuel.getParsingElContext();
//			context.setFunction("dmn", "evaluate", DecisionTableEvaluator.class.getMethod("evaluateDecision", String.class, VariableContext.class));
//			dmnEngineConfiguration.setElProvider(elProvider);
//		} catch (Exception e) {
//			throw new RuntimeException("Could not add DMN functions to ElProvider. Check nested exception for details.", e);
//		}
	}

}
