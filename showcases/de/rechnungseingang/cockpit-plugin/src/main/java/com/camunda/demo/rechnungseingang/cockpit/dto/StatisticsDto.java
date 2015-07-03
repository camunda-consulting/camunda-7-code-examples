package com.camunda.demo.rechnungseingang.cockpit.dto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class StatisticsDto {

  private String openApplicationSum;

  private List<List> endEvents = new ArrayList<List>();

  private InstanceCountChart instanceCountChart;

  public void setEventStatistics(long issued, long rejected, long clearingLoop) {
    List entry1 = new ArrayList<Object>();
    entry1.add("Processed");
    entry1.add(issued);
    endEvents.add(entry1);

    List entry2 = new ArrayList<Object>();
    entry2.add("Not Processed");
    entry2.add(rejected);
    endEvents.add(entry2);

//    List entry3 = new ArrayList<Object>();
//    entry3.add("Nach Klärung verarbeitet");
//    entry3.add(clearingLoop);
//    endEvents.add(entry3);
  }

  public String getOpenApplicationSum() {
    return openApplicationSum;
  }

  public void setOpenApplicationSum(String openApplicationSum) {
    this.openApplicationSum = openApplicationSum;
  }

  public List<List> getEndEvents() {
    return endEvents;
  }

  public void setInstanceCountChart(InstanceCountChart instanceCountChart) {
    this.instanceCountChart = instanceCountChart;    
  }

  public InstanceCountChart getInstanceCountChart() {
    return instanceCountChart;
  }

}
