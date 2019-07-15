package com.camunda.demo.rest;

import com.camunda.demo.domain.EventTypeConfiguration;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class GenerateModelController {

	@Autowired
	RepositoryService repositoryService;
    
    @PostMapping("/model")
	public String createModel(@RequestBody EventTypeConfiguration configuration) {

		BpmnModelInstance model = ModelGenerator.generateModel(configuration);

    	repositoryService.createDeployment()
						.name(configuration.getEventType())
						.addModelInstance(configuration.getEventType(), model)
						.source("deployment-generator")
						.enableDuplicateFiltering(true);


		return configuration.toString();
	}
}