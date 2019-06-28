const { Client, logger } = require("camunda-external-task-client-js");

// configuration for the Client:
//  - 'baseUrl': url to the Workflow Engine
//  - 'logger': utility to automatically log important events
const config = { baseUrl: "http://localhost:8080/engine-rest", use: logger, asyncResponseTimeout: 50000 };

// create a Client instance with custom configuration
const client = new Client(config);

// susbscribe to the topic: 'creditScoreChecker'
client.subscribe("AnswerQuestion", async function({ task, taskService }) {
  // Put your business logic
	var goodQuestion = Math.random() >= 0.5;
  
	
	if(goodQuestion){
		// complete the task
		await taskService.complete(task);
	}else{
		// Throw BPMN error
		await taskService.handleBpmnError(task, "NoIdea", "NoIdea");
	}
  
  
  console.log("Answered a question on the forum")
  
});