package com.camunda.demo.dmn.feel.operator;

import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

public class CustomFeelProcessEnginePlugin implements ProcessEnginePlugin {

	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		// cannot do this here because historyLevel (and maybe others) are not
		// yet initialized
	}

	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		// so do it here
		DefaultDmnEngineConfiguration dmnEngineConfiguration = (DefaultDmnEngineConfiguration) processEngineConfiguration.getDmnEngineConfiguration();
		dmnEngineConfiguration.setFeelEngineFactory(new CustomFeelEngineFactory());
		DmnEngine dmnEngine = dmnEngineConfiguration.buildEngine();

		processEngineConfiguration.setDmnEngineConfiguration(dmnEngineConfiguration);
		processEngineConfiguration.setDmnEngine(dmnEngine);

	}

	public void postProcessEngineBuild(ProcessEngine processEngine) {
	}

}