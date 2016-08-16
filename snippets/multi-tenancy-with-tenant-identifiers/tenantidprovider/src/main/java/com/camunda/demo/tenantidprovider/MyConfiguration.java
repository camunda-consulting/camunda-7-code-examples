package com.camunda.demo.tenantidprovider;

import org.camunda.bpm.engine.impl.cfg.StandaloneProcessEngineConfiguration;

public class MyConfiguration extends StandaloneProcessEngineConfiguration{
	
	@Override
	public void init() {
		super.init();
		tenantIdProvider = new CustomTenantIdProvider();
	}

}
