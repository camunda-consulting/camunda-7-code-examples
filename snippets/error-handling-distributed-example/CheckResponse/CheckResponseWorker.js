const { Client, logger } = require("camunda-external-task-client-js");
const { Variables } = require("camunda-external-task-client-js");

// configuration for the Client:
// - 'baseUrl': url to the Workflow Engine
// - 'logger': utility to automatically log important events
const config = { baseUrl: "http://localhost:8080/engine-rest", use: logger, asyncResponseTimeout: 50000 };

// create a Client instance with custom configuration
const client = new Client(config);

// susbscribe to the topic: 'creditScoreChecker'
client.subscribe("CheckResponse", async function({ task, taskService }) {
  // Put your business logic
  // complete the task
	 
	const processVariables = new Variables();
	var rando = Math.random() >= 0.5;	
	processVariables.set("responseGood", rando);	

	await taskService.complete(task, processVariables);
	  
    console.log("Response has been sent, was it good? "+ rando)
	  
});