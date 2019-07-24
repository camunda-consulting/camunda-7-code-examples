const { Client, logger } = require("camunda-external-task-client-js");
const { Variables } = require("camunda-external-task-client-js");

// configuration for the Client:
//  - 'baseUrl': url to the Workflow Engine
//  - 'logger': utility to automatically log important events
const config = { baseUrl: "http://localhost:8080/engine-rest", use: logger };

// create a Client instance with custom configuration
const client = new Client(config);

// susbscribe to the topic: 'creditScoreChecker'
client.subscribe("DecideOnExpansion", async function({ task, taskService }) {
  // Put your business logic

  var north = Math.random() >= 0.5;
  const processVariables = new Variables();
  processVariables.set("north", north);

  // complete the task
  await taskService.complete(task, processVariables);
});
