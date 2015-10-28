package org.camunda.bpm.demo.cockpit.plugin.kpi.dto;

public class CountPerOptionDto {

  private String optionId;
  private String optionName;
  private long count;
  
  public long getCount() {
    return count;
  }
  public void setCount(long count) {
    this.count = count;
  }
  public String getOptionId() {
    return optionId;
  }
  public void setOptionId(String optionId) {
    this.optionId = optionId;
  }
  public String getOptionName() {
    return optionName;
  }
  public void setOptionName(String optionName) {
    this.optionName = optionName;
  }
}
