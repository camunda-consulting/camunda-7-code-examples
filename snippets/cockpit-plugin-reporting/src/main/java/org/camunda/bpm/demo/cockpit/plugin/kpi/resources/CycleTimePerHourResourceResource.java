package org.camunda.bpm.demo.cockpit.plugin.kpi.resources;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.CountPerDurationDto;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.CountPerOptionDto;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.CycleTimeDto;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.InstanceCountDto;
import org.camunda.bpm.demo.cockpit.plugin.kpi.util.ExtenstionElementReader;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.w3c.dom.Document;

public class CycleTimePerHourResourceResource extends AbstractCockpitPluginResource {

  private String processDefinitionKey;

  public CycleTimePerHourResourceResource(String engineName, String processDefinitionKey) {
    super(engineName);
    this.processDefinitionKey = processDefinitionKey;
  }

  @GET
//  @Produces()
  public CycleTimeDto getCycleTimePerHour() {
    CycleTimeDto result = new CycleTimeDto();
    
    HashMap<String, String> parameters = new HashMap<String, String>();
    parameters.put("processDefinitionKey", processDefinitionKey);

    ProcessDefinition processDefinition = getProcessEngine().getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
        .latestVersion().singleResult();
    InputStream bpmnXml = getProcessEngine().getRepositoryService().getProcessModel(processDefinition.getId());
    Document bpmn = ExtenstionElementReader.parseBpmnXml(bpmnXml);
    
    result.setName(ExtenstionElementReader.findProperty(bpmn, "KPI-Cycle-Start"));
    
    parameters.put("activityIdStart", ExtenstionElementReader.findActivityIdForProperty(bpmn, "KPI-Cycle-Start"));
    result.setStartName( ExtenstionElementReader.findActivityNameForProperty(bpmn, "KPI-Cycle-Start"));

    parameters.put("activityIdEnd", ExtenstionElementReader.findActivityIdForProperty(bpmn, "KPI-Cycle-End"));
    result.setEndName( ExtenstionElementReader.findActivityNameForProperty(bpmn, "KPI-Cycle-End"));    
    
    if (parameters.get("activityIdStart")==null || parameters.get("activityIdEnd")==null) {
      // TODO: If null -> use Start / End Events?
      return result;
    }
    
    QueryParameters<CountPerDurationDto> queryParameters = new QueryParameters<CountPerDurationDto>();
    queryParameters.setParameter(parameters);
    
    result.setTimesPerDuration(getQueryService().executeQuery("cockpit.kpi.selectCycleTimeInHours", queryParameters));
    
    return result;
  }

}
