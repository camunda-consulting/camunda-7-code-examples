package org.example.incidents_as_tasks;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.impl.cfg.AbstractProcessEnginePlugin;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.incident.IncidentHandler;

public class IncidentAsTaskProcessEnginePlugin extends AbstractProcessEnginePlugin {

  @Override
  public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
    List<IncidentHandler> customIncidentHandlers = new ArrayList<IncidentHandler>();
    customIncidentHandlers.add(new UserTaskFailedJobIncidentHandler());
    processEngineConfiguration.setCustomIncidentHandlers(customIncidentHandlers );
  }

}
