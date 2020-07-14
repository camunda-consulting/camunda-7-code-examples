package com.camunda.consulting;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.impl.jobexecutor.JobHandler;

import java.util.ArrayList;
import java.util.List;

public class MessageTimeoutPlugin implements ProcessEnginePlugin {

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        List<BpmnParseListener> preParseListeners = processEngineConfiguration.getCustomPreBPMNParseListeners();
        if (preParseListeners == null) {
            preParseListeners = new ArrayList<>();
            processEngineConfiguration.setCustomPreBPMNParseListeners(preParseListeners);
        }

        preParseListeners.add(new MessageTimeoutBpmnParseListener(processEngineConfiguration));

        List<JobHandler> customJobHandlers = processEngineConfiguration.getCustomJobHandlers();

        if (customJobHandlers == null) {
            customJobHandlers = new ArrayList<>();
            processEngineConfiguration.setCustomJobHandlers(customJobHandlers);
        }

        customJobHandlers.add(new TimerMessageListenerJobHandler());
    }

    @Override
    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

    }

    @Override
    public void postProcessEngineBuild(ProcessEngine processEngine) {

    }
}
