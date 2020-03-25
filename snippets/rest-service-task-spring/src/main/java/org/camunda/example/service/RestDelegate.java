package org.camunda.example.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class RestDelegate implements JavaDelegate {

    RestOperations restTemplate;

    public RestDelegate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        //access process variable
        String userId = execution.getVariable("userId").toString();

        //call REST service to lookup account
        Response response = (restTemplate.getForObject("https://reqres.in/api/users/" + userId, Response.class));

        //access object in Java, store a new process variable
        if (response != null) execution.setVariable("email", response.getData().getEmail());

        //serialize a java object into JSON and stored it in this way so Camunda knows it is JSON
        ObjectValue adJson = Variables
                .objectValue(response.getAd())
                .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                .create();
        //add json object value as process variable
        execution.setVariable("Ad", adJson);
    }
}
