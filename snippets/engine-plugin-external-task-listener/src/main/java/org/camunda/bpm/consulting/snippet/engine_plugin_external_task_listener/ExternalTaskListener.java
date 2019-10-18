package org.camunda.bpm.consulting.snippet.engine_plugin_external_task_listener;

import org.camunda.bpm.engine.externaltask.ExternalTask;

public interface ExternalTaskListener {
  
  public void notify(ExternalTask externalTask);

}
