package org.camunda.bpm.demo.cockpit.plugin.kpi.dto;

public class CountPerDurationDto {
  
  private int duration;
  private long count;
  
  public int getDuration() {
    return duration;
  }
  public CountPerDurationDto setDuration(int duration) {
    this.duration = duration;
    return this;
  }
  public long getCount() {
    return count;
  }
  public CountPerDurationDto setCount(long count) {
    this.count = count;
    return this;
  }

}
