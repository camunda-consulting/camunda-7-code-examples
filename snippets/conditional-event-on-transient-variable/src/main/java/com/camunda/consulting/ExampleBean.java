package com.camunda.consulting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExampleBean {
  private static final Logger LOG = LoggerFactory.getLogger(ExampleBean.class);
  private String exampleValue;

  public String getExampleValue() {
    return exampleValue;
  }

  public void setExampleValue(String exampleValue) {
    this.exampleValue = exampleValue;
  }
}
