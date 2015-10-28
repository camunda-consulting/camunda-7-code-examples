package org.camunda.bpm.demo.cockpit.plugin.kpi.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class InstanceCountChart {

  private List<String> categories = new ArrayList<String>();
  private Map<String, InstanceCountSeries> series = new HashMap<String, InstanceCountSeries>();
    
  public InstanceCountChart() {    
  }  
  
  public InstanceCountChart(List<InstanceCountDto> processInstances) {
    HashSet<String> processDefinitionKeys = new HashSet<String>();
    for (InstanceCountDto processInstanceCountDTO : processInstances) {
      processDefinitionKeys.add(processInstanceCountDTO.getName());
    }

    SimpleDateFormat df = new SimpleDateFormat("dd.MM");
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, -14);
    for (int i = 0; i < 15; i++) {
      categories.add(df.format(calendar.getTime()));

      for (String key : processDefinitionKeys) {
        long count = 0;
        for (InstanceCountDto processInstanceCountDTO : processInstances) {
          Calendar calendar2 = Calendar.getInstance();
          calendar2.setTime(processInstanceCountDTO.getDate());

          if (processInstanceCountDTO.getName().equals(key)
              && calendar2.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
            count = processInstanceCountDTO.getCount();
          }
        }
        addSeriesValue(key, count);
      }

      calendar.add(Calendar.DAY_OF_YEAR, 1);
    }
  }
  
  
  public List<String> getCategories() {
    return this.categories;
  }

  public void setCategories(final List<String> categories) {
    this.categories = categories;
  }

  public Collection<InstanceCountSeries> getSeries() {
    return this.series.values();
  }

  public void addSeriesValue(final String key, final long count) {
    if (!this.series.containsKey(key)) {
      InstanceCountSeries processInstanceCountSeries = new InstanceCountSeries();
      processInstanceCountSeries.setName(key);
      this.series.put(key, processInstanceCountSeries);
    }
    this.series.get(key).getData().add(count);
  }

}
