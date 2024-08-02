# Reverse Adapter

Instead of migrating code when migrating the platform, you could migrate your code first and your implementation later.

This example shows how this can be done in an easy way.

## How to set it up

* Start Camunda Run in dev mode (using `start.sh` from the root directory of the distribution).
* Start Camunda 8 locally (using `docker-compose-core.yaml` from the `camunda/camunda-platform` GitHub repo)
* Start this application
* Open `./example-process-camunda7.bpmn` with your Camunda modeler and deploy it to the Camunda Run instance
* Open `./example-process-camunda8.bpmn` with your Camunda modeler and deploy it to the Camunda 8 instance
* Start instances from both processes

## What is demonstrated

We now have an easy way to define a function that can be used by Camunda 7 (inside an external task client), but also by Camunda 8.