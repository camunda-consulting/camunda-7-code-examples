package com.camunda.consulting;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StartInstanceController {
  private final RuntimeService runtimeService;

  @Autowired
  public StartInstanceController(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }

  @PostMapping("/start-instance")
  public ResponseEntity<ProcessInstanceDto> startInstance(@RequestBody ExampleComplexDto body) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("ExampleProcess",
        Map.of("exampleDto", body)
    );
    return ResponseEntity.ok(new ProcessInstanceDto(processInstance.getId(),
        processInstance.getProcessDefinitionId(),
        processInstance.getTenantId(),
        processInstance.getTenantId()
    ));
  }
}
