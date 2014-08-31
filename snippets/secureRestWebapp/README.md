## Rest-Webapp which doesn't allow to change engine data

This is an example for a customized rest webapp which doesn't allow changes on the process data except from the public Java-API.

There are two small changes in this project:

In the web.xml the authorization is activated.

An additional Filter answers all POST-, PUT-, and DELETE-Requests with 403 Forbidden.

This REST-Webapp doesn't work with readOnlyCockpit.

If you want to try it, you must undeploy the original camunda-engine-rest.war

