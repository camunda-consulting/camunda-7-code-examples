package com.camunda.consulting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleValueController {
  private final ExampleBean exampleBean;

  public ExampleValueController(ExampleBean exampleBean) {
    this.exampleBean = exampleBean;
  }

  @PutMapping("/example-value")
  public ResponseEntity<Void> setExampleValue(@RequestBody String value) {
    exampleBean.setExampleValue(value);
    return ResponseEntity.status(204).build();
  }
}
