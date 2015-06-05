package com.camunda.demo.versicherungsneuantrag.cockpit.resources;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.GET;

import org.camunda.bpm.cockpit.db.QueryParameters;
import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.engine.impl.util.json.JSONObject;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.variable.value.ObjectValue;

import com.camunda.demo.versicherungsneuantrag.cockpit.dto.InstanceCountChart;
import com.camunda.demo.versicherungsneuantrag.cockpit.dto.InstanceCountDTO;
import com.camunda.demo.versicherungsneuantrag.cockpit.dto.StatisticsDto;

public class ProcessInstanceCountResource extends AbstractCockpitPluginResource {

  public ProcessInstanceCountResource(String engineName) {
    super(engineName);
  }

  @GET
  public StatisticsDto getStatistics() {   
    StatisticsDto result = new StatisticsDto();
    NumberFormat n = NumberFormat.getCurrencyInstance(Locale.GERMANY); 
    
    // When having a lot of data it may make more sense to store onl the sum in a seperate variable to improve performance (then the database could do the count)
    long sumOpenApplications = 0;
    List<VariableInstance> applications = getProcessEngine().getRuntimeService().createVariableInstanceQuery() //
        .variableName("neuantrag") //       
        .disableCustomObjectDeserialization() //
        .list();
    
    for (VariableInstance application : applications) {
      if (application.getProcessInstanceId()!=null) { // only from root process instance
        String applicationJson = ((ObjectValue)application.getTypedValue()).getValueSerialized();
        if (new JSONObject(applicationJson).has("preisindikationInCent")) {
          // might not be true for old applications
          long preisindikationInCent = new JSONObject(applicationJson).getLong("preisindikationInCent");
          sumOpenApplications += preisindikationInCent;
        }
      }
    }
    result.setOpenApplicationSum(n.format(sumOpenApplications / 100.0));
    
    // get ratio of end events
    long countIssued = getProcessEngine().getHistoryService().createHistoricActivityInstanceQuery() //
        .activityId("EndEventAntragPoliciert") //
        .finished() //
        .count();
    long countRejected = getProcessEngine().getHistoryService().createHistoricActivityInstanceQuery()//
        .activityId("EndEventAntragAbgelehnt") //
        .finished() //
        .count();
    result.setEndEventStatistics(countIssued, countRejected);

    InstanceCountChart instanceCountChart = new InstanceCountChart();
    
    List<InstanceCountDTO> processInstances = getQueryService().executeQuery("cockpit.versicherungsneuantrag.selectProcessInstanceCountPerHourForLast7Days",
        new QueryParameters<InstanceCountDTO>());    

    List<InstanceCountDTO> caseInstances = getQueryService().executeQuery("cockpit.versicherungsneuantrag.selectCaseInstanceCountPerHourForLast7Days",
        new QueryParameters<InstanceCountDTO>());    

    addInstances(instanceCountChart, processInstances);
    addInstances(instanceCountChart, caseInstances);

    result.setInstanceCountChart(instanceCountChart);
    return result;
  }

  private void addInstances(InstanceCountChart processInstanceCountChart, List<InstanceCountDTO> processInstances) {
    HashSet<String> processDefinitionKeys = new HashSet<String>();
    for (InstanceCountDTO processInstanceCountDTO : processInstances) {
      processDefinitionKeys.add(processInstanceCountDTO.getName());
    }

    SimpleDateFormat df = new SimpleDateFormat("dd.MM");
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, -14);
    for (int i = 0; i < 15; i++) {
      processInstanceCountChart.getCategories().add(df.format(calendar.getTime()));

      for (String key : processDefinitionKeys) {
        long count = 0;
        for (InstanceCountDTO processInstanceCountDTO : processInstances) {
          Calendar calendar2 = Calendar.getInstance();
          calendar2.setTime(processInstanceCountDTO.getDate());

          if (processInstanceCountDTO.getName().equals(key)
              && calendar2.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
            count = processInstanceCountDTO.getCount();
          }
        }
        processInstanceCountChart.addSeriesValue(key, count);
      }

      calendar.add(Calendar.DAY_OF_YEAR, 1);
    }
  }
  
  
}
