package com.camunda.fox.demo.outerspace.task_name_beautifier;

import java.util.ArrayList;

import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.bpmn.parser.BpmnParseListener;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.task.Task;

import com.camunda.fox.platform.impl.configuration.JtaCmpeProcessEngineConfigurationFactory;
import com.camunda.fox.platform.impl.configuration.spi.ProcessEngineConfigurationFactory;

/**
 * {@link ProcessEngineConfigurationFactory} that customizes the BPMN Parser
 * with a {@link BpmnParseListener} that enhances all user tasks with a 
 * {@link TaskListener} that removes hyphens from {@link Task} names.
 *
 * @author Falko Menge (camunda)
 */
public class TaskNameBeautifierProcessEngineConfigurationFactory extends JtaCmpeProcessEngineConfigurationFactory {

  @Override
  public ProcessEngineConfigurationImpl getProcessEngineConfiguration() {
    ProcessEngineConfigurationImpl processEngineConfiguration = super.getProcessEngineConfiguration();

    // Change whatever you want to change in the configuration, see 
    // https://app.camunda.com/confluence/display/foxUserGuide/Extending+the+configuration+of+the+fox+platform#Extendingtheconfigurationofthefoxplatform-UseCases
    // for some typical use cases

    // normally no parse listeners should be set, so create an own list for it
    if (processEngineConfiguration.getPostParseListeners() == null) {
      processEngineConfiguration.setPostParseListeners(new ArrayList<BpmnParseListener>());
    }

    // add parse listener
    processEngineConfiguration.getPostParseListeners().add(new TaskNameBeautifierBpmnParseListener());

    return processEngineConfiguration;
  }

}
