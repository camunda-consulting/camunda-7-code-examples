# Process automation without Java classes

This example uses only scripts (all JavaScript for convenience) and http-service-calls to validate claims, that are feeded to it.

The input will be an array of claims as shown [in this small](src/test/resources/claims/claims-rsp-ids-unique.json) or [in this larger example](src/test/resources/claims/six-claims.json)

## Show me the important parts!
![BPMN Process](src/main/resources/process.png)

## How does it work?

The process runs through a list of validations on the same data. The rules are written either as script tasks, if they don't depend of separate data or as REST services build with Express and Node.js, if the need access to some master data tables. The process uses the http-connector to call the services.

The input data are stored in a single process variable `claims`. Each check result will be saved in a single process variable, i.e. `resultCheckUniqueRspID`. The result can have a list of errors.

### Using script tasks
All script tasks are build with Javascript. They return a process variable, built with SPIN from the stringified JSON Object. As they are serialized for the process engine, they have unlimited size.

An external script example:

    var errorList = [];
    errorList.push('my first error');
    // more to follow
    S(errorList, "application/json");

The complete script task:

    <bpmn:scriptTask id="ScriptTask_1" name="Check unique RSP ID" scriptFormat="JavaScript" camunda:resultVariable="resultCheckUniqueRspID" camunda:resource="scripts/check_unique_rsp_id.js">
    </bpmn:scriptTask>


### Modeling conditions
As all variables are stored as json object variables, you can use script conditions instead of JUEL expressions. The JUEL Expressions use Java-Objects and Java should be used as less as possible.

To check the result on a conditional sequence flow, you have to deserialize the process variable and check for the conditions. This can be done in a script with 

	S(resultCheckUniqueRspID).elements().length == 0;
	
Check [the docs](https://docs.camunda.org/manual/7.5/reference/spin/json/01-reading-json/#fetch-array-of-data) how to work on JSON arrays. 

The complete sequence flow may look like this:

    <bpmn:sequenceFlow id="SequenceFlow_1" name="yes" sourceRef="ExclusiveGateway_1" targetRef="ScriptTask_1">
      <bpmn:conditionExpression xsi:type="bpmn:tFormalExpression" language="JavaScript">S(resultCheckUniqueRspID).elements().length == 0;</bpmn:conditionExpression>
    </bpmn:sequenceFlow>
  

### Using connectors
All services are build as POST services, so the connectors need 4 input parameters:
* url
* method
* headers
* payload

The ouput parameter is a single process variable with the error list.

This is an example for a service task with [a connector](https://docs.camunda.org/manual/7.5/reference/connect/http-connector/#using-the-generic-api):

    <bpmn:serviceTask id="ServiceTask_1dwr4ge" name="Check mandatory field values">
      <bpmn:extensionElements>
        <camunda:connector>
          <camunda:connectorId>http-connector</camunda:connectorId>
          <camunda:inputOutput>
            <camunda:inputParameter name="url">http://localhost:3000/check-mandatory-fields</camunda:inputParameter>
            <camunda:inputParameter name="method">POST</camunda:inputParameter>
            <camunda:inputParameter name="headers">
              <camunda:map>
                <camunda:entry key="Content-Type">application/json</camunda:entry>
              </camunda:map>
            </camunda:inputParameter>
            <camunda:inputParameter name="payload">
              <camunda:script scriptFormat="javascript"><![CDATA[claims.toString();]]></camunda:script>
            </camunda:inputParameter>
            <camunda:outputParameter name="resultCheckMandatoryFields">
              <camunda:script scriptFormat="javaScript">S(response);</camunda:script>
            </camunda:outputParameter>
          </camunda:inputOutput>
        </camunda:connector>
      </bpmn:extensionElements>
    </bpmn:serviceTask>

As the prebuilt connector passes the payload as a String to the underlying http-framework, you have to convert the SPIN-Object to a String by calling `.toString()`.

To save [the response of the service](https://docs.camunda.org/manual/7.5/reference/connect/http-connector/#using-the-generic-api-1), which arrives as a String, into a JSON-Object-Variable, you have to use the SPIN-Expression. See the output parameter in the code above.

###Error handling with connectors

If you need to abort a service task with an attached error boundary event, you can throw a full qualified BpmnError from the script that evaluates the response. 

As the `statusCode` is a [predefined variable](https://docs.camunda.org/manual/7.5/reference/connect/http-connector/#using-the-generic-api-1) from the connector, it is available in the script for further inspection:

    if (statusCode == 403) {
	    throw new org.camunda.bpm.engine.delegate.BpmnError("tooManyErrors");
    }
    S(response);

Check the node script [route.js](node-scripts/routes/routes.js) how to respond with a different status. 

## How to use it?
There is no web interface to access the application.
To get started refer to the `InMemoryH2Test`.

To run the tests, start the node server first. See the [readme](node-scripts/) in the node-scripts folder for a quick how-to. 

To start the deployed process on a tomcat distro, you can use a REST client and call POST /process-definition/key/validate-claims/start with the payload

    {
    "variables": 
        {"claims":{
            "value": "{\n  \"data\": [\n    {\n      \"claimId\": \"189\",\n      \"rows\": [\n        {\n          \"repairServicePartnerId\": 12,\n          \"localClaimID\": 12,\n          \"serialNumberIn\" : \"123AE\",\n          \"customerComplaintCodePrimary\":\"100\"\n        },\n        {\n          \"repairServicePartnerId\": 11,\n          \"localClaimID\": 13,\n          \"serialNumberIn\": \"123\",\n          \"customerComplaintCodePrimary\":\"E100\"\n        }\n      ]\n    }\n  ]\n}", 
            "type": "json", 
            "valueInfo":{"serializationDataFormat":"application/json"}
            }, 
        "selectedRepairServicePartnerId":{
            "value": 11
            }
        }
    }

There is a simple html page to convert other claim-files (json-formatted) into the payload for the camunda start process instance rest service: [json-converter](utils/start-process-payload-generator.html)

(Sorry, http://htmlpreview.github.io/?https://github.com/camunda/camunda-consulting/blob/master/one-time-examples/validate-claims/utils/start-process-payload-generator.html didn't work for some reasons I can't find).

You can also use `ant` to build and deploy the example to an application server.
For that to work you need to copy the file `build.properties.example` to `build.properties`
and configure the path to your application server inside it.
Alternatively, you can also copy it to `${user.home}/.camunda/build.properties`
to have a central configuration that works with all projects generated by the
[Camunda BPM Maven Archetypes](http://docs.camunda.org/latest/guides/user-guide/#process-applications-maven-project-templates-archetypes).

Once you deployed the application you can run it using
[Camunda Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist)
and inspect it using
[Camunda Cockpit](http://docs.camunda.org/latest/guides/user-guide/#cockpit).

## Environment Restrictions
Built and tested against Camunda BPM version 7.5.0.

## Known Limitations

## Improvements Backlog
* multi-instance

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

A Process Application for [Camunda BPM](http://docs.camunda.org).

This project has been generated by the Maven archetype
[camunda-archetype-servlet-war-7.5.0](http://docs.camunda.org/latest/guides/user-guide/#process-applications-maven-project-templates-archetypes).

<!-- HTML snippet for index page
  <tr>
    <td><img src="snippets/validate-claims/src/main/resources/process.png" width="100"></td>
    <td><a href="snippets/validate-claims">Camunda BPM Process Application</a></td>
    <td>A Process Application for [Camunda BPM](http://docs.camunda.org).</td>
  </tr>
-->
<!-- Tweet
New @CamundaBPM example: Camunda BPM Process Application - A Process Application for [Camunda BPM](http://docs.camunda.org). https://github.com/camunda/camunda-consulting/tree/master/snippets/validate-claims
-->