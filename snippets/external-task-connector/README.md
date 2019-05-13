# External Task Connector

This is a simple example of how an external task can be used to replace and improve the functionality of the old camunda http-connector for REST calls


## External Worker
If you run the worker it will connect to a locally running engine with the rest endpoint
``
http://localhost:8080/engine-rest
``
Once it does it will be subscribed to the topic *REST_CONNECTOR*
If it fails to complete the rest call with the valiables given then it will create an incidnet in the engine - otherwise it will return with the status code and response body to the process instance. 

https://github.com/camunda/camunda-external-task-client-java 

## Test Process
There is a test process in the resources folder that can be deployed via the REST API and started via task list in order to test the worker. 

