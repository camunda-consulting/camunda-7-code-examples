package org.camunda.demo;

import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;


public class TwitterWorkerV3 {

	 private final static Logger LOGGER = Logger.getLogger(TwitterWorkerV3.class.getName());

	  public static void main(String[] args) {
	    ExternalTaskClient client = ExternalTaskClient.create()
	        .baseUrl("http://localhost:8080/engine-rest")
	        .asyncResponseTimeout(10000) // long polling timeout
	        .build();

	    // subscribe to an external task topic as specified in the process
	    client.subscribe("PublishCoolTweet")
	        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
	        .handler((externalTask, externalTaskService) -> {
	          // Put your business logic here

	          // Get a process variable
	          LOGGER.info("Something very Cool has just been tweeted!");

	          // Complete the task
	          externalTaskService.complete(externalTask);
	        })
	        .open();
	    
	    client.subscribe("PublishSorryTweet")
        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
        .handler((externalTask, externalTaskService) -> {
          // Put your business logic here

          // Get a process variable
          LOGGER.info("We've said sorry... although the internet never forgives...");

          // Complete the task
          externalTaskService.complete(externalTask);
        })
        .open();

	  }

}
