package com.camunda.bpm.demo.statistics.milestone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.history.HistoricActivityInstance;

@SessionScoped
@Named
public class StatisticsPhasesBean implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private ProcessEngine engine;

  private List<DataItem> stats;

  public String getAxisDates() {
    ArrayList<String> names = new ArrayList<String>();
    for (DataItem item : getStats()) {
      names.add(item.getName().replaceAll("\n", " ").replaceAll("\r", ""));
    }
    String result= "[\""+join(names, "\",\"")+"\"]";
    return result;
  }

  public String getChartData() {
    ArrayList<String> values = new ArrayList<String>();
    for (DataItem item : getStats()) {
      values.add(String.valueOf( item.getValue() ));
    }
    String result="["+join(values, ",")+"]";
    return result;
  }

  public void updateStats() {
    stats = null;
  }

  public List<DataItem> getStats() {
    if (stats == null) {
      stats = getPhasesStatistics();
    }
    return stats;
  }

  public List<DataItem> getPhasesStatistics() {
    ArrayList<DataItem> dataItems = new ArrayList<DataItem>();
    DataItem lastDataItem = null;

    List<HistoricActivityInstance> activities = engine.getHistoryService().createHistoricActivityInstanceQuery() //
        .activityType("subProcess")
        .unfinished()
        .orderByActivityId().asc().list();

    for (HistoricActivityInstance historicActivityInstance : activities) {
      if (lastDataItem == null || !lastDataItem.getId().equals(historicActivityInstance.getActivityId())) {
        lastDataItem = new DataItem();
        lastDataItem.setId(historicActivityInstance.getActivityId());
        lastDataItem.setName(historicActivityInstance.getActivityName());
        lastDataItem.setValue(1);
        dataItems.add(lastDataItem);
      } else {
        lastDataItem.incValue();
      }
    }

    return dataItems;
  }

  static public String join(List<String> list, String conjunction) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (String item : list) {
      if (first)
        first = false;
      else
        sb.append(conjunction);
      sb.append(item);
    }
    return sb.toString();
  }
}
