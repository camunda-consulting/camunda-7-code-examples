package com.camunda.consulting;

import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("job")
public class OfflineSchedulePluginProperties {
  @NestedConfigurationProperty
  private Set<String> breaks;

  public void setBreaks(Set<String> breaks) {
    this.breaks = breaks;
  }

  public Set<String> getBreaks() {
    return this.breaks;
  }
}
