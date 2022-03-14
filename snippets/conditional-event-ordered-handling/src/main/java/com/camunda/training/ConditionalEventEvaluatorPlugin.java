package com.camunda.training;

import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConditionalEventEvaluatorPlugin extends AbstractProcessEnginePlugin {
  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    List<BpmnParseListener> parseListeners = getCustomPostBpmnParseListeners(processEngineConfiguration);
    parseListeners.add(new ConditionalEventEvaluatorParseListener());
  }

  private List<BpmnParseListener> getCustomPostBpmnParseListeners(ProcessEngineConfigurationImpl processEngineConfiguration){
    List<BpmnParseListener> customPostBpmnParseListeners = processEngineConfiguration.getCustomPostBPMNParseListeners();
    if(customPostBpmnParseListeners == null){
      processEngineConfiguration.setCustomPostBPMNParseListeners(new ArrayList<>());
      return processEngineConfiguration.getCustomPostBPMNParseListeners();
    }
    return customPostBpmnParseListeners;
  }
}
