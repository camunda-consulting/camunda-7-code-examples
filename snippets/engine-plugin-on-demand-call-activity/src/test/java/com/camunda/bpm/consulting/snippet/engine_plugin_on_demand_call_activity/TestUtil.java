package com.camunda.bpm.consulting.snippet.engine_plugin_on_demand_call_activity;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.test.ProcessEngineRule;

import static org.camunda.bpm.engine.impl.test.TestHelper.dropSchema;

public class TestUtil {

    public static ProcessEngineRule cleanUpAndCreateEngine(String configFile, String... deployments){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessEngine processEngine = null;
        if(defaultProcessEngine != null) {
            dropSchema((ProcessEngineConfigurationImpl) defaultProcessEngine.getProcessEngineConfiguration());
        }
        processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(configFile).buildProcessEngine();

        for(String deployment : deployments){
            processEngine.getRepositoryService().createDeployment().addClasspathResource(deployment).deploy();
        }

        return new ProcessEngineRule(processEngine, false);
    }
}
