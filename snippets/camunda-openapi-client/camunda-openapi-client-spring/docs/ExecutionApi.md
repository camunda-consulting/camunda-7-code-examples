# ExecutionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createIncident**](ExecutionApi.md#createIncident) | **POST** /execution/{id}/create-incident | Create Incident
[**deleteLocalExecutionVariable**](ExecutionApi.md#deleteLocalExecutionVariable) | **DELETE** /execution/{id}/localVariables/{varName} | Delete Local Execution Variable
[**getExecution**](ExecutionApi.md#getExecution) | **GET** /execution/{id} | Get Execution
[**getExecutions**](ExecutionApi.md#getExecutions) | **GET** /execution | Get Executions
[**getExecutionsCount**](ExecutionApi.md#getExecutionsCount) | **GET** /execution/count | Get Execution Count
[**getLocalExecutionVariable**](ExecutionApi.md#getLocalExecutionVariable) | **GET** /execution/{id}/localVariables/{varName} | Get Local Execution Variable
[**getLocalExecutionVariableBinary**](ExecutionApi.md#getLocalExecutionVariableBinary) | **GET** /execution/{id}/localVariables/{varName}/data | Get Local Execution Variable (Binary)
[**getLocalExecutionVariables**](ExecutionApi.md#getLocalExecutionVariables) | **GET** /execution/{id}/localVariables | Get Local Execution Variables
[**getMessageEventSubscription**](ExecutionApi.md#getMessageEventSubscription) | **GET** /execution/{id}/messageSubscriptions/{messageName} | Get Message Event Subscription
[**modifyLocalExecutionVariables**](ExecutionApi.md#modifyLocalExecutionVariables) | **POST** /execution/{id}/localVariables | Update/Delete Local Execution Variables
[**putLocalExecutionVariable**](ExecutionApi.md#putLocalExecutionVariable) | **PUT** /execution/{id}/localVariables/{varName} | Put Local Execution Variable
[**queryExecutions**](ExecutionApi.md#queryExecutions) | **POST** /execution | Get Executions (POST)
[**queryExecutionsCount**](ExecutionApi.md#queryExecutionsCount) | **POST** /execution/count | Get Execution Count (POST)
[**setLocalExecutionVariableBinary**](ExecutionApi.md#setLocalExecutionVariableBinary) | **POST** /execution/{id}/localVariables/{varName}/data | Post Local Execution Variable (Binary)
[**signalExecution**](ExecutionApi.md#signalExecution) | **POST** /execution/{id}/signal | Trigger Execution
[**triggerEvent**](ExecutionApi.md#triggerEvent) | **POST** /execution/{id}/messageSubscriptions/{messageName}/trigger | Trigger Message Event Subscription



## createIncident

> IncidentDto createIncident(id, createIncidentDto)

Create Incident

Creates a custom incident with given properties.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to create a new incident for.
        CreateIncidentDto createIncidentDto = new CreateIncidentDto(); // CreateIncidentDto | 
        try {
            IncidentDto result = apiInstance.createIncident(id, createIncidentDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#createIncident");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to create a new incident for. |
 **createIncidentDto** | [**CreateIncidentDto**](CreateIncidentDto.md)|  | [optional]

### Return type

[**IncidentDto**](IncidentDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if the incident type is null, the execution does not exist or the execution is not related to any activity. |  -  |


## deleteLocalExecutionVariable

> deleteLocalExecutionVariable(id, varName)

Delete Local Execution Variable

Deletes a variable in the context of a given execution by id. Deletion does not propagate upwards in the execution hierarchy.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to delete the variable from.
        String varName = "varName_example"; // String | The name of the variable to delete.
        try {
            apiInstance.deleteLocalExecutionVariable(id, varName);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#deleteLocalExecutionVariable");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to delete the variable from. |
 **varName** | **String**| The name of the variable to delete. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. This method returns no content. |  -  |


## getExecution

> ExecutionDto getExecution(id)

Get Execution

Retrieves an execution by id, according to the &#x60;Execution&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to be retrieved.
        try {
            ExecutionDto result = apiInstance.getExecution(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#getExecution");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to be retrieved. |

### Return type

[**ExecutionDto**](ExecutionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getExecutions

> List&lt;ExecutionDto&gt; getExecutions(businessKey, processDefinitionId, processDefinitionKey, processInstanceId, activityId, signalEventSubscriptionName, messageEventSubscriptionName, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, variables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, sortBy, sortOrder, firstResult, maxResults)

Get Executions

Queries for the executions that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of executions. The size of the result set can be retrieved by using the [Get Execution Count](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String businessKey = "businessKey_example"; // String | Filter by the business key of the process instances the executions belong to.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the executions run on.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the executions run on.
        String processInstanceId = "processInstanceId_example"; // String | Filter by the id of the process instance the execution belongs to.
        String activityId = "activityId_example"; // String | Filter by the id of the activity the execution currently executes.
        String signalEventSubscriptionName = "signalEventSubscriptionName_example"; // String | Select only those executions that expect a signal of the given name.
        String messageEventSubscriptionName = "messageEventSubscriptionName_example"; // String | Select only those executions that expect a message of the given name.
        Boolean active = true; // Boolean | Only include active executions. Value may only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | Only include suspended executions. Value may only be `true`, as `false` is the default behavior.
        String incidentId = "incidentId_example"; // String | Filter by the incident id.
        String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
        String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
        String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids.
        String variables = "variables_example"; // String | Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value. **Note:** Values are always treated as `String` objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
        String processVariables = "processVariables_example"; // String | Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value. **Note:** Values are always treated as `String` objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to. `key` and `value` may not contain underscore or comma characters.
        Boolean variableNamesIgnoreCase = true; // Boolean | Match all variable names provided in `variables` and `processVariables` case- insensitively. If set to `true` **variableName** and **variablename** are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match all variable values provided in `variables` and `processVariables` case- insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<ExecutionDto> result = apiInstance.getExecutions(businessKey, processDefinitionId, processDefinitionKey, processInstanceId, activityId, signalEventSubscriptionName, messageEventSubscriptionName, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, variables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#getExecutions");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **businessKey** | **String**| Filter by the business key of the process instances the executions belong to. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the executions run on. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the executions run on. | [optional]
 **processInstanceId** | **String**| Filter by the id of the process instance the execution belongs to. | [optional]
 **activityId** | **String**| Filter by the id of the activity the execution currently executes. | [optional]
 **signalEventSubscriptionName** | **String**| Select only those executions that expect a signal of the given name. | [optional]
 **messageEventSubscriptionName** | **String**| Select only those executions that expect a message of the given name. | [optional]
 **active** | **Boolean**| Only include active executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **incidentId** | **String**| Filter by the incident id. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids. | [optional]
 **variables** | **String**| Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **processVariables** | **String**| Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: instanceId, definitionKey, definitionId, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;ExecutionDto&gt;**](ExecutionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getExecutionsCount

> CountResultDto getExecutionsCount(businessKey, processDefinitionId, processDefinitionKey, processInstanceId, activityId, signalEventSubscriptionName, messageEventSubscriptionName, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, variables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase)

Get Execution Count

Queries for the number of executions that fulfill given parameters. Takes the same parameters as the [Get Executions](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String businessKey = "businessKey_example"; // String | Filter by the business key of the process instances the executions belong to.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the executions run on.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the executions run on.
        String processInstanceId = "processInstanceId_example"; // String | Filter by the id of the process instance the execution belongs to.
        String activityId = "activityId_example"; // String | Filter by the id of the activity the execution currently executes.
        String signalEventSubscriptionName = "signalEventSubscriptionName_example"; // String | Select only those executions that expect a signal of the given name.
        String messageEventSubscriptionName = "messageEventSubscriptionName_example"; // String | Select only those executions that expect a message of the given name.
        Boolean active = true; // Boolean | Only include active executions. Value may only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | Only include suspended executions. Value may only be `true`, as `false` is the default behavior.
        String incidentId = "incidentId_example"; // String | Filter by the incident id.
        String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
        String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
        String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids.
        String variables = "variables_example"; // String | Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value. **Note:** Values are always treated as `String` objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
        String processVariables = "processVariables_example"; // String | Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value. **Note:** Values are always treated as `String` objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to. `key` and `value` may not contain underscore or comma characters.
        Boolean variableNamesIgnoreCase = true; // Boolean | Match all variable names provided in `variables` and `processVariables` case- insensitively. If set to `true` **variableName** and **variablename** are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match all variable values provided in `variables` and `processVariables` case- insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal.
        try {
            CountResultDto result = apiInstance.getExecutionsCount(businessKey, processDefinitionId, processDefinitionKey, processInstanceId, activityId, signalEventSubscriptionName, messageEventSubscriptionName, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, variables, processVariables, variableNamesIgnoreCase, variableValuesIgnoreCase);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#getExecutionsCount");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **businessKey** | **String**| Filter by the business key of the process instances the executions belong to. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the executions run on. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the executions run on. | [optional]
 **processInstanceId** | **String**| Filter by the id of the process instance the execution belongs to. | [optional]
 **activityId** | **String**| Filter by the id of the activity the execution currently executes. | [optional]
 **signalEventSubscriptionName** | **String**| Select only those executions that expect a signal of the given name. | [optional]
 **messageEventSubscriptionName** | **String**| Select only those executions that expect a message of the given name. | [optional]
 **active** | **Boolean**| Only include active executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **incidentId** | **String**| Filter by the incident id. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. An execution must have one of the given tenant ids. | [optional]
 **variables** | **String**| Only include executions that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **processVariables** | **String**| Only include executions that belong to a process instance with variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. | [optional]

### Return type

[**CountResultDto**](CountResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getLocalExecutionVariable

> VariableValueDto getLocalExecutionVariable(id, varName, deserializeValue)

Get Local Execution Variable

Retrieves a variable from the context of a given execution by id. Does not traverse the parent execution hierarchy.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to retrieve the variable from.
        String varName = "varName_example"; // String | The name of the variable to get.
        Boolean deserializeValue = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath. If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            VariableValueDto result = apiInstance.getLocalExecutionVariable(id, varName, deserializeValue);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#getLocalExecutionVariable");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to retrieve the variable from. |
 **varName** | **String**| The name of the variable to get. |
 **deserializeValue** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath. If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**VariableValueDto**](VariableValueDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getLocalExecutionVariableBinary

> File getLocalExecutionVariableBinary(id, varName)

Get Local Execution Variable (Binary)

Retrieves a binary variable from the context of a given execution by id. Does not traverse the parent execution hierarchy. Applicable for byte array and file variables.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to retrieve the variable from.
        String varName = "varName_example"; // String | The name of the variable to get.
        try {
            File result = apiInstance.getLocalExecutionVariableBinary(id, varName);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#getLocalExecutionVariableBinary");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to retrieve the variable from. |
 **varName** | **String**| The name of the variable to get. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/octet-stream, text/plain, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set. |  -  |
| **400** | Variable instance with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Variable instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getLocalExecutionVariables

> Map&lt;String, VariableValueDto&gt; getLocalExecutionVariables(id, deserializeValues)

Get Local Execution Variables

Retrieves all variables of a given execution by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to retrieve the variables from.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            Map<String, VariableValueDto> result = apiInstance.getLocalExecutionVariables(id, deserializeValues);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#getLocalExecutionVariables");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to retrieve the variables from. |
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. Returns A JSON object of variables key-value pairs. Each key is a variable name and each value a VariableValueDto |  -  |
| **500** | Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getMessageEventSubscription

> EventSubscriptionDto getMessageEventSubscription(id, messageName)

Get Message Event Subscription

Retrieves a message event subscription for a given execution by id and a message name.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution that holds the subscription.
        String messageName = "messageName_example"; // String | The name of the message that the subscription corresponds to.
        try {
            EventSubscriptionDto result = apiInstance.getMessageEventSubscription(id, messageName);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#getMessageEventSubscription");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution that holds the subscription. |
 **messageName** | **String**| The name of the message that the subscription corresponds to. |

### Return type

[**EventSubscriptionDto**](EventSubscriptionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | A message subscription for the given name and execution does not exist. This may either mean that the execution does not exist, or that it is not subscribed on such a message. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## modifyLocalExecutionVariables

> modifyLocalExecutionVariables(id, patchVariablesDto)

Update/Delete Local Execution Variables

Updates or deletes the variables in the context of an execution by id. The updates do not propagate upwards in the execution hierarchy. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to set variables for.
        PatchVariablesDto patchVariablesDto = new PatchVariablesDto(); // PatchVariablesDto | 
        try {
            apiInstance.modifyLocalExecutionVariables(id, patchVariablesDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#modifyLocalExecutionVariables");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to set variables for. |
 **patchVariablesDto** | [**PatchVariablesDto**](PatchVariablesDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. This method returns no content. |  -  |
| **400** | The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error- handling) for the error response format. |  -  |
| **500** | Update or delete could not be executed, for example because the execution does not exist. |  -  |


## putLocalExecutionVariable

> putLocalExecutionVariable(id, varName, variableValueDto)

Put Local Execution Variable

Sets a variable in the context of a given execution by id. Update does not propagate upwards in the execution hierarchy.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to set the variable for.
        String varName = "varName_example"; // String | The name of the variable to set.
        VariableValueDto variableValueDto = new VariableValueDto(); // VariableValueDto | 
        try {
            apiInstance.putLocalExecutionVariable(id, varName, variableValueDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#putLocalExecutionVariable");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to set the variable for. |
 **varName** | **String**| The name of the variable to set. |
 **variableValueDto** | [**VariableValueDto**](VariableValueDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. This method returns no content. |  -  |
| **400** | The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error- handling) for the error response format. |  -  |


## queryExecutions

> List&lt;ExecutionDto&gt; queryExecutions(firstResult, maxResults, executionQueryDto)

Get Executions (POST)

Queries for executions that fulfill given parameters through a JSON object. This method is slightly more powerful than the [Get Executions](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query/) method because it allows to filter by multiple instance and execution variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        ExecutionQueryDto executionQueryDto = new ExecutionQueryDto(); // ExecutionQueryDto | 
        try {
            List<ExecutionDto> result = apiInstance.queryExecutions(firstResult, maxResults, executionQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#queryExecutions");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **executionQueryDto** | [**ExecutionQueryDto**](ExecutionQueryDto.md)|  | [optional]

### Return type

[**List&lt;ExecutionDto&gt;**](ExecutionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryExecutionsCount

> CountResultDto queryExecutionsCount(executionQueryDto)

Get Execution Count (POST)

Queries for the number of executions that fulfill given parameters. This method takes the same message body as the [Get Executions POST](https://docs.camunda.org/manual/7.16/reference/rest/execution/post-query/) method and therefore it is slightly more powerful than the [Get Execution Count](https://docs.camunda.org/manual/7.16/reference/rest/execution/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        ExecutionQueryDto executionQueryDto = new ExecutionQueryDto(); // ExecutionQueryDto | 
        try {
            CountResultDto result = apiInstance.queryExecutionsCount(executionQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#queryExecutionsCount");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **executionQueryDto** | [**ExecutionQueryDto**](ExecutionQueryDto.md)|  | [optional]

### Return type

[**CountResultDto**](CountResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setLocalExecutionVariableBinary

> setLocalExecutionVariableBinary(id, varName, data, valueType)

Post Local Execution Variable (Binary)

Sets the serialized value for a binary variable or the binary value for a file variable in the context of a given execution by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to set the variable for.
        String varName = "varName_example"; // String | The name of the variable to set.
        File data = new File("/path/to/file"); // File | The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory.
        String valueType = "valueType_example"; // String | The name of the variable type. Either Bytes for a byte array variable or File for a file variable.
        try {
            apiInstance.setLocalExecutionVariableBinary(id, varName, data, valueType);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#setLocalExecutionVariableBinary");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to set the variable for. |
 **varName** | **String**| The name of the variable to set. |
 **data** | **File**| The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. | [optional]
 **valueType** | **String**| The name of the variable type. Either Bytes for a byte array variable or File for a file variable. | [optional] [enum: Bytes, File]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: multipart/form-data
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. This method returns no content. |  -  |
| **400** | The variable value or type is invalid, for example if no filename is set. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## signalExecution

> signalExecution(id, executionTriggerDto)

Trigger Execution

Signals an execution by id. Can for example be used to explicitly skip user tasks or signal asynchronous continuations.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to signal.
        ExecutionTriggerDto executionTriggerDto = new ExecutionTriggerDto(); // ExecutionTriggerDto | 
        try {
            apiInstance.signalExecution(id, executionTriggerDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#signalExecution");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to signal. |
 **executionTriggerDto** | [**ExecutionTriggerDto**](ExecutionTriggerDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. This method returns no content. |  -  |
| **400** | The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## triggerEvent

> triggerEvent(id, messageName, executionTriggerDto)

Trigger Message Event Subscription

Delivers a message to a specific execution by id, to trigger an existing message event subscription. Inject process variables as the message&#39;s payload.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExecutionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        ExecutionApi apiInstance = new ExecutionApi(defaultClient);
        String id = "id_example"; // String | The id of the execution to submit the message to.
        String messageName = "messageName_example"; // String | The name of the message that the addressed subscription corresponds to.
        ExecutionTriggerDto executionTriggerDto = new ExecutionTriggerDto(); // ExecutionTriggerDto | 
        try {
            apiInstance.triggerEvent(id, messageName, executionTriggerDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling ExecutionApi#triggerEvent");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| The id of the execution to submit the message to. |
 **messageName** | **String**| The name of the message that the addressed subscription corresponds to. |
 **executionTriggerDto** | [**ExecutionTriggerDto**](ExecutionTriggerDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. This method returns no content. |  -  |
| **400** | The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The addressed execution has no pending message subscriptions for the given message. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

