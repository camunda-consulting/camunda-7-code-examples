package org.camunda.bpm.demo.cockpit.plugin.kpi.resources;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;

import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.CountPerOptionDto;
import org.camunda.bpm.demo.cockpit.plugin.kpi.dto.InstanceCountDto;
import org.camunda.bpm.demo.cockpit.plugin.kpi.util.ExtenstionElementReader;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.w3c.dom.Document;

public class RatioResource extends AbstractCockpitPluginResource {

  private String processDefinitionKey;
  
  public RatioResource(String engineName, String processDefinitionKey) {
    super(engineName);
    this.processDefinitionKey = processDefinitionKey;
  }


  @GET
  public List<CountPerOptionDto> getProcessInstanceCounts() {
    HashMap<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("processDefinitionKey", processDefinitionKey);

    ProcessDefinition processDefinition = getProcessEngine().getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
        .latestVersion().singleResult();
    InputStream bpmnXml = getProcessEngine().getRepositoryService().getProcessModel(processDefinition.getId());
    Document bpmn = ExtenstionElementReader.parseBpmnXml(bpmnXml);
    
    List<String> activityIds = ExtenstionElementReader.findActivityIdsForProperty(bpmn, "KPI-Ratio");
//    List<String> activityNames = ExtenstionElementReader.findActivityNamesForProperty(bpmn, "KPI-Ratio");
    List<String> propertyNames = ExtenstionElementReader.findPropertyValues(bpmn, "KPI-Ratio");

    parameters.put("activityIds", activityIds);

    QueryParameters<CountPerOptionDto> queryParameters = new QueryParameters<CountPerOptionDto>();
    queryParameters.setParameter(parameters);
    List<CountPerOptionDto> result = getQueryService().executeQuery("cockpit.kpi.selectCountPerActivityId", queryParameters);
    
    for (CountPerOptionDto countPerOptionDto : result) {
      int i = activityIds.indexOf(countPerOptionDto.getOptionId());
      countPerOptionDto.setOptionName(propertyNames.get(i));
    }
    return result;    
  }
  
}
