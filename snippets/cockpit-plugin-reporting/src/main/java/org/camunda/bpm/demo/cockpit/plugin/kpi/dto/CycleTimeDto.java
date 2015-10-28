package org.camunda.bpm.demo.cockpit.plugin.kpi.dto;

import java.util.ArrayList;
import java.util.List;

public class CycleTimeDto {
  
  private String name;
  
  private String startName;
  private String endName;
  private List<CountPerDurationDto> timesPerDuration = new ArrayList<CountPerDurationDto>();
  
  public String getStartName() {
    return startName;
  }
  public CycleTimeDto setStartName(String startName) {
    this.startName = startName;
    return this;
  }
  public String getEndName() {
    return endName;
  }
  public CycleTimeDto setEndName(String endName) {
    this.endName = endName;
    return this;
  }
  public List<CountPerDurationDto> getTimesPerDuration() {
    return timesPerDuration;
  }
  public CycleTimeDto setTimesPerDuration(List<CountPerDurationDto> timesPerDuration) {
    this.timesPerDuration = new ArrayList<CountPerDurationDto>();
    int duration = 0;
    if (timesPerDuration.size()>0) {
      // start with min value
      duration = timesPerDuration.get(0).getDuration();
    }
    for (CountPerDurationDto countPerDurationDto : timesPerDuration) {
      while (duration < countPerDurationDto.getDuration()) {
        // fill empty spaces with zero value
        this.timesPerDuration.add(
            new CountPerDurationDto().setDuration(duration).setCount(0));
        duration++;
      }
      this.timesPerDuration.add(countPerDurationDto);
      duration++;
    }
    return this;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

}
