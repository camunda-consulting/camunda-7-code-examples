package org.camunda.bpm.demo.cockpit.plugin.kpi.resources;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;

import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.InstanceCountChart;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.InstanceCountDto;

public class ProcessInstanceCountResource extends AbstractCockpitPluginResource {

  private String processDefinitionKey;

  public ProcessInstanceCountResource(String engineName, String processDefinitionKey) {
    super(engineName);
    this.processDefinitionKey = processDefinitionKey;
  }

  @GET
  public InstanceCountChart getProcessInstanceCounts() {
    HashMap<String, String> parameters = new HashMap<String, String>();
    parameters.put("processDefinitionKey", processDefinitionKey);

    QueryParameters<InstanceCountDto> queryParameters = new QueryParameters<InstanceCountDto>();
    queryParameters.setParameter(parameters);
    
    List<InstanceCountDto> result = getQueryService().executeQuery("cockpit.kpi.selectProcessInstanceCountPerHourForLast7Days", queryParameters);    
    return new InstanceCountChart(result);    
  }
}
