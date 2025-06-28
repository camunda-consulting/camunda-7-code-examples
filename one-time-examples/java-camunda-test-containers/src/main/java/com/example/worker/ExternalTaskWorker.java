
package com.example.worker;

import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.client.ExternalTaskClient;

public class ExternalTaskWorker {

	private static final Logger LOGGER = Logger.getLogger("ExternalTaskWorker");

	public static void start(String baseUrl, CreditService creditService) {
		ExternalTaskClient client = ExternalTaskClient
									.create()
									.baseUrl(baseUrl)
									.asyncResponseTimeout(10000)
									.build();

		client.subscribe("credit-score")
		.lockDuration(1000)
		.handler((externalTask, externalTaskService) -> {
			
			LOGGER.info("✔ credit-score triggered");
			
			int creditScore = creditService.getCreditScore();
			boolean customerExists = creditService.getCustomerExists();

			LOGGER.info("✔ Credit Score: " + creditScore);
			LOGGER.info("✔ Customer Exists: " + customerExists);

			externalTaskService.complete(externalTask,
					Map.of("creditScore", creditScore, "customerExists", customerExists));
		})
		.open();
		
		
		client.subscribe("add-customer")
		.lockDuration(1000)
		.handler((externalTask, externalTaskService) -> {
			
			LOGGER.info("✔ add-customer triggered");
			
			boolean customerExists = externalTask.getVariable("customerExists");
			
			LOGGER.info("customerExists" + customerExists);
			
			if (customerExists) {
				externalTaskService.handleBpmnError(externalTask, "CustomerExists","customer allready exist");
			}else {
				externalTaskService.complete(externalTask,
						Map.of("messageSend", true));
			}

			

		})
		.open();
		
		client.subscribe("send-confirmation")
		.lockDuration(1000)
		.handler((externalTask, externalTaskService) -> {

			LOGGER.info("✔ send-confirmation triggered");
			externalTaskService.complete(externalTask,
					Map.of("messageSend", true));
		})
		.open();
		
		
		client.subscribe("send-rejection")
		.lockDuration(1000)
		.handler((externalTask, externalTaskService) -> {

			LOGGER.info("✔ send-rejection triggered");
			externalTaskService.complete(externalTask,
					Map.of("rejected", true));
		})
		.open();
		
		
	}
}
