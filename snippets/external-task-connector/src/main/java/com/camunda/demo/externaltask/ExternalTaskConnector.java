package com.camunda.demo.externaltask;

import java.util.HashMap;
import java.util.Map;


import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.topic.TopicSubscriptionBuilder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ExternalTaskConnector {
	

	public static void main(String[] args) {
	    // bootstrap the client
	    ExternalTaskClient client = ExternalTaskClient.create()
	      .baseUrl("http://localhost:8080/engine-rest")
	      .lockDuration(60000)
	      .asyncResponseTimeout(100000)
	      .maxTasks(1)
	      .build();
	    
	    // subscribe to the topic
	    TopicSubscriptionBuilder subscribtionBuilder = client.subscribe("REST_CONNECTOR");
	    subscribtionBuilder
	      .handler((externalTask, externalTaskService) -> {
	    	  
	    	  System.out.println("Trying to Work on task " + externalTask.getActivityId());
	    	 
	    	  HttpResponse<String> response = null;
	    	  Map<String, Object> vars = new HashMap<String, Object>();
	    	  
	    	  String url = externalTask.getVariable("url");
	    	  String method = externalTask.getVariable("method");
	    	  //Map<String, String> headers = externalTask.getVariable("headers");
	    	  String headerKey = externalTask.getVariable("headerKey");
	    	  String headerValue = externalTask.getVariable("headerValue");
	    	  String payload = externalTask.getVariable("payload");
	    	  
	    	  try {
		    	  if(method == null) {
		    		  
		    		  externalTaskService.handleFailure(externalTask, "The method for the rest call is null", "You need to create a method variable whith either GET or POST as a value", 0, 0);
		    	  
		    	  }else if (method.equals("GET")) {
			    	  
						response = 
								Unirest.get(url)
								  .header(headerKey, headerValue)
								  .asString();
 
		    	  }else if (method.equals("POST")) {
						response = Unirest.post(url)
								  .header(headerKey, headerValue)
								  .body(payload)
								  .asString();
		    	  }else {
		    		  externalTaskService.handleFailure(externalTask, "The method is incorrect or not yet implemented", "You can either implement the method or maybe the type you supplied is not correct.", 0, 0);
		    	  }
		    	  
	    	  }catch (UnirestException e) {
					externalTaskService.handleFailure(externalTask, e.getMessage(), e.getCause().getMessage(), 0, 0);
					e.printStackTrace();
			  }catch (Exception e) {
					externalTaskService.handleFailure(externalTask, e.getMessage(), e.getCause().getMessage() , 0, 0);
					e.printStackTrace();
			  }
	    		  
	    	  
	    	  vars.put("response_"+externalTask.getActivityId(), response.getBody());
	    	  vars.put("response_status_"+externalTask.getActivityId(), response.getStatus());
	    	  
	    	  externalTaskService.complete(externalTask, vars);
	    	  
	      }).open();

	  }

}
