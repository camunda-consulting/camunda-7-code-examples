package com.camunda.demo.rest;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.demo.domain.EventTypeConfiguration;


@RestController
public class GenerateModelController {

	@Autowired
	RepositoryService repositoryService;
    
    @PostMapping("/model")
	public String createModel(@RequestBody EventTypeConfiguration configuration) {

		BpmnModelInstance model = new ModelGenerator().generateModel(configuration);

		String xml = Bpmn.convertToString(model);
		System.out.println(xml);

    	repositoryService.createDeployment()
						.name(configuration.getEventType())
				.addModelInstance(configuration.getEventType() + ".bpmn", model)
						.source("deployment-generator")
				.enableDuplicateFiltering(true).deploy();


		return configuration.toString();
	}
}