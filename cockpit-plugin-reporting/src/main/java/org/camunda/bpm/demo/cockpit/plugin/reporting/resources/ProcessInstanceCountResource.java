package org.camunda.bpm.demo.cockpit.plugin.reporting.resources;

import java.util.List;
import javax.ws.rs.GET;

import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.plugin.resource.AbstractPluginResource;
import org.camunda.bpm.demo.cockpit.plugin.reporting.ProcessInstanceCountDto;

public class ProcessInstanceCountResource extends AbstractPluginResource {

  public ProcessInstanceCountResource(String engineName) {
    super(engineName);
  }

  @GET
  public List<ProcessInstanceCountDto> getProcessInstanceCounts() {
    return getQueryService()
        .executeQuery(
          "cockpit.reporting.selectProcessInstanceCountsByProcessDefinition",
          // TODO: Null possible?
          new QueryParameters<ProcessInstanceCountDto>());
  }
}
