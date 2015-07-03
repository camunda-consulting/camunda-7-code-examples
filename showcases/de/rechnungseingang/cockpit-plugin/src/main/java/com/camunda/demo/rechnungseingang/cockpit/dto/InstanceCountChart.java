package com.camunda.demo.rechnungseingang.cockpit.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstanceCountChart {

  private List<String> categories = new ArrayList<String>();
  private Map<String, InstanceCountSeries> series = new HashMap<String, InstanceCountSeries>();

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
