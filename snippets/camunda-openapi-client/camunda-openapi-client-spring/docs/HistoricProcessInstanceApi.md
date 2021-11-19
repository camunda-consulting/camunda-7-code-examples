# HistoricProcessInstanceApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteHistoricProcessInstance**](HistoricProcessInstanceApi.md#deleteHistoricProcessInstance) | **DELETE** /history/process-instance/{id} | Delete
[**deleteHistoricProcessInstancesAsync**](HistoricProcessInstanceApi.md#deleteHistoricProcessInstancesAsync) | **POST** /history/process-instance/delete | Delete Async (POST)
[**deleteHistoricVariableInstancesOfHistoricProcessInstance**](HistoricProcessInstanceApi.md#deleteHistoricVariableInstancesOfHistoricProcessInstance) | **DELETE** /history/process-instance/{id}/variable-instances | Delete Variable Instances
[**getHistoricProcessInstance**](HistoricProcessInstanceApi.md#getHistoricProcessInstance) | **GET** /history/process-instance/{id} | Get
[**getHistoricProcessInstanceDurationReport**](HistoricProcessInstanceApi.md#getHistoricProcessInstanceDurationReport) | **GET** /history/process-instance/report | Get Duration Report
[**getHistoricProcessInstances**](HistoricProcessInstanceApi.md#getHistoricProcessInstances) | **GET** /history/process-instance | Get List
[**getHistoricProcessInstancesCount**](HistoricProcessInstanceApi.md#getHistoricProcessInstancesCount) | **GET** /history/process-instance/count | Get List Count
[**queryHistoricProcessInstances**](HistoricProcessInstanceApi.md#queryHistoricProcessInstances) | **POST** /history/process-instance | Get List (POST)
[**queryHistoricProcessInstancesCount**](HistoricProcessInstanceApi.md#queryHistoricProcessInstancesCount) | **POST** /history/process-instance/count | Get List Count (POST)
[**setRemovalTimeAsync**](HistoricProcessInstanceApi.md#setRemovalTimeAsync) | **POST** /history/process-instance/set-removal-time | Set Removal Time Async (POST)



## deleteHistoricProcessInstance

> deleteHistoricProcessInstance(id, failIfNotExists)

Delete

Deletes a process instance from the history by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the historic process instance to be deleted.
        Boolean failIfNotExists = true; // Boolean | If set to `false`, the request will still be successful if the process id is not found.
        try {
            apiInstance.deleteHistoricProcessInstance(id, failIfNotExists);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#deleteHistoricProcessInstance");
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
 **id** | **String**| The id of the historic process instance to be deleted. |
 **failIfNotExists** | **Boolean**| If set to &#x60;false&#x60;, the request will still be successful if the process id is not found. | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. |  -  |
| **404** | Not found Historic process instance with given id does not exist. |  -  |


## deleteHistoricProcessInstancesAsync

> BatchDto deleteHistoricProcessInstancesAsync(deleteHistoricProcessInstancesDto)

Delete Async (POST)

Delete multiple historic process instances asynchronously (batch). At least &#x60;historicProcessInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60; has to be provided. If both are provided then all instances matching query criterion and instances from the list will be deleted.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        DeleteHistoricProcessInstancesDto deleteHistoricProcessInstancesDto = new DeleteHistoricProcessInstancesDto(); // DeleteHistoricProcessInstancesDto | 
        try {
            BatchDto result = apiInstance.deleteHistoricProcessInstancesAsync(deleteHistoricProcessInstancesDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#deleteHistoricProcessInstancesAsync");
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
 **deleteHistoricProcessInstancesDto** | [**DeleteHistoricProcessInstancesDto**](DeleteHistoricProcessInstancesDto.md)|  | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, i.e. neither historicProcessInstanceIds, nor historicProcessInstanceQuery is present. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## deleteHistoricVariableInstancesOfHistoricProcessInstance

> deleteHistoricVariableInstancesOfHistoricProcessInstance(id)

Delete Variable Instances

Deletes all variables of a process instance from the history by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the process instance for which all historic variables are to be deleted.
        try {
            apiInstance.deleteHistoricVariableInstancesOfHistoricProcessInstance(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#deleteHistoricVariableInstancesOfHistoricProcessInstance");
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
 **id** | **String**| The id of the process instance for which all historic variables are to be deleted. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. |  -  |
| **404** | Not found Historic process instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#parse-exceptions) for the error response format. |  -  |


## getHistoricProcessInstance

> HistoricProcessInstanceDto getHistoricProcessInstance(id)

Get

Retrieves a historic process instance by id, according to the &#x60;HistoricProcessInstance&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the historic process instance to be retrieved.
        try {
            HistoricProcessInstanceDto result = apiInstance.getHistoricProcessInstance(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#getHistoricProcessInstance");
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
 **id** | **String**| The id of the historic process instance to be retrieved. |

### Return type

[**HistoricProcessInstanceDto**](HistoricProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Not Found Historic process instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricProcessInstanceDurationReport

> List&lt;DurationReportResultDto&gt; getHistoricProcessInstanceDurationReport(reportType, periodUnit, processDefinitionIdIn, processDefinitionKeyIn, startedBefore, startedAfter)

Get Duration Report

Retrieves a report about the duration of completed process instances, grouped by a period. These reports include the maximum, minimum and average duration of all completed process instances which were started in a given period.  **Note:** This only includes historic data.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        String reportType = "reportType_example"; // String | **Mandatory.** Specifies the type of the report to retrieve. To retrieve a report about the duration of process instances, the value must be set to `duration`.
        String periodUnit = "periodUnit_example"; // String | **Mandatory.** Specifies the granularity of the report. Valid values are `month` and `quarter`.
        String processDefinitionIdIn = "processDefinitionIdIn_example"; // String | Filter by process definition ids. Must be a comma-separated list of process definition ids.
        String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Filter by process definition keys. Must be a comma-separated list of process definition keys.
        OffsetDateTime startedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started before the given date. By [default](), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2016-01-23T14:42:45.000+0200`.
        OffsetDateTime startedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2016-01-23T14:42:45.000+0200`.
        try {
            List<DurationReportResultDto> result = apiInstance.getHistoricProcessInstanceDurationReport(reportType, periodUnit, processDefinitionIdIn, processDefinitionKeyIn, startedBefore, startedAfter);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#getHistoricProcessInstanceDurationReport");
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
 **reportType** | **String**| **Mandatory.** Specifies the type of the report to retrieve. To retrieve a report about the duration of process instances, the value must be set to &#x60;duration&#x60;. |
 **periodUnit** | **String**| **Mandatory.** Specifies the granularity of the report. Valid values are &#x60;month&#x60; and &#x60;quarter&#x60;. | [enum: month, quarter]
 **processDefinitionIdIn** | **String**| Filter by process definition ids. Must be a comma-separated list of process definition ids. | [optional]
 **processDefinitionKeyIn** | **String**| Filter by process definition keys. Must be a comma-separated list of process definition keys. | [optional]
 **startedBefore** | **OffsetDateTime**| Restrict to instances that were started before the given date. By [default](), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2016-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedAfter** | **OffsetDateTime**| Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2016-01-23T14:42:45.000+0200&#x60;. | [optional]

### Return type

[**List&lt;DurationReportResultDto&gt;**](DurationReportResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json, application/csv, text/csv


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid or mandatory parameters are not supplied. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **403** | If the authenticated user is unauthorized to read the history. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricProcessInstances

> List&lt;HistoricProcessInstanceDto&gt; getHistoricProcessInstances(sortBy, sortOrder, firstResult, maxResults, processInstanceId, processInstanceIds, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, processDefinitionKeyNotIn, processInstanceBusinessKey, processInstanceBusinessKeyLike, rootProcessInstances, finished, unfinished, withIncidents, withRootIncidents, incidentType, incidentStatus, incidentMessage, incidentMessageLike, startedBefore, startedAfter, finishedBefore, finishedAfter, executedActivityAfter, executedActivityBefore, executedJobAfter, executedJobBefore, startedBy, superProcessInstanceId, subProcessInstanceId, superCaseInstanceId, subCaseInstanceId, caseInstanceId, tenantIdIn, withoutTenantId, executedActivityIdIn, activeActivityIdIn, active, suspended, completed, externallyTerminated, internallyTerminated, variables, variableNamesIgnoreCase, variableValuesIgnoreCase)

Get List

Queries for historic process instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Process Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processInstanceIds = "processInstanceIds_example"; // String | Filter by process instance ids. Filter by a comma-separated list of `Strings`.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the instances run on.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the instances run on.
        String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of `Strings`.
        String processDefinitionName = "processDefinitionName_example"; // String | Filter by the name of the process definition the instances run on.
        String processDefinitionNameLike = "processDefinitionNameLike_example"; // String | Filter by process definition names that the parameter is a substring of.
        String processDefinitionKeyNotIn = "processDefinitionKeyNotIn_example"; // String | Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of `Strings`.
        String processInstanceBusinessKey = "processInstanceBusinessKey_example"; // String | Filter by process instance business key.
        String processInstanceBusinessKeyLike = "processInstanceBusinessKeyLike_example"; // String | Filter by process instance business key that the parameter is a substring of.
        Boolean rootProcessInstances = true; // Boolean | Restrict the query to all process instances that are top level process instances.
        Boolean finished = true; // Boolean | Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be `true`, as `false` is the default behavior.
        Boolean unfinished = true; // Boolean | Only include unfinished process instances. Value may only be `true`, as `false` is the default behavior.
        Boolean withIncidents = true; // Boolean | Only include process instances which have an incident. Value may only be `true`, as `false` is the default behavior.
        Boolean withRootIncidents = true; // Boolean | Only include process instances which have a root incident. Value may only be `true`, as `false` is the default behavior.
        String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
        String incidentStatus = "incidentStatus_example"; // String | Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents.
        String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
        String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
        OffsetDateTime startedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime startedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedActivityAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedActivityBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedJobAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedJobBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String startedBy = "startedBy_example"; // String | Only include process instances that were started by the given user.
        String superProcessInstanceId = "superProcessInstanceId_example"; // String | Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id.
        String subProcessInstanceId = "subProcessInstanceId_example"; // String | Restrict query to one process instance that has a sub process instance with the given id.
        String superCaseInstanceId = "superCaseInstanceId_example"; // String | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id.
        String subCaseInstanceId = "subCaseInstanceId_example"; // String | Restrict query to one process instance that has a sub case instance with the given id.
        String caseInstanceId = "caseInstanceId_example"; // String | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of `Strings`
        Boolean withoutTenantId = true; // Boolean | Only include historic process instances which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String executedActivityIdIn = "executedActivityIdIn_example"; // String | Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of `Strings`
        String activeActivityIdIn = "activeActivityIdIn_example"; // String | Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of `Strings`
        Boolean active = true; // Boolean | Restrict to instances that are active.
        Boolean suspended = true; // Boolean | Restrict to instances that are suspended.
        Boolean completed = true; // Boolean | Restrict to instances that are completed.
        Boolean externallyTerminated = true; // Boolean | Restrict to instances that are externallyTerminated.
        Boolean internallyTerminated = true; // Boolean | Restrict to instances that are internallyTerminated.
        String variables = "variables_example"; // String | Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`.  Key and value may not contain underscore or comma characters. 
        Boolean variableNamesIgnoreCase = true; // Boolean | Match all variable names provided in variables case-insensitively. If set to `true` variableName and variablename are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match all variable values provided in variables case-insensitively. If set to `true` variableValue and variablevalue are treated as equal.
        try {
            List<HistoricProcessInstanceDto> result = apiInstance.getHistoricProcessInstances(sortBy, sortOrder, firstResult, maxResults, processInstanceId, processInstanceIds, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, processDefinitionKeyNotIn, processInstanceBusinessKey, processInstanceBusinessKeyLike, rootProcessInstances, finished, unfinished, withIncidents, withRootIncidents, incidentType, incidentStatus, incidentMessage, incidentMessageLike, startedBefore, startedAfter, finishedBefore, finishedAfter, executedActivityAfter, executedActivityBefore, executedJobAfter, executedJobBefore, startedBy, superProcessInstanceId, subProcessInstanceId, superCaseInstanceId, subCaseInstanceId, caseInstanceId, tenantIdIn, withoutTenantId, executedActivityIdIn, activeActivityIdIn, active, suspended, completed, externallyTerminated, internallyTerminated, variables, variableNamesIgnoreCase, variableValuesIgnoreCase);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#getHistoricProcessInstances");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: instanceId, definitionId, definitionKey, definitionName, definitionVersion, businessKey, startTime, endTime, duration, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processInstanceIds** | **String**| Filter by process instance ids. Filter by a comma-separated list of &#x60;Strings&#x60;. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the instances run on. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the instances run on. | [optional]
 **processDefinitionKeyIn** | **String**| Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of &#x60;Strings&#x60;. | [optional]
 **processDefinitionName** | **String**| Filter by the name of the process definition the instances run on. | [optional]
 **processDefinitionNameLike** | **String**| Filter by process definition names that the parameter is a substring of. | [optional]
 **processDefinitionKeyNotIn** | **String**| Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of &#x60;Strings&#x60;. | [optional]
 **processInstanceBusinessKey** | **String**| Filter by process instance business key. | [optional]
 **processInstanceBusinessKeyLike** | **String**| Filter by process instance business key that the parameter is a substring of. | [optional]
 **rootProcessInstances** | **Boolean**| Restrict the query to all process instances that are top level process instances. | [optional]
 **finished** | **Boolean**| Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **unfinished** | **Boolean**| Only include unfinished process instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **withIncidents** | **Boolean**| Only include process instances which have an incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **withRootIncidents** | **Boolean**| Only include process instances which have a root incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentStatus** | **String**| Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents. | [optional] [enum: open, resolved]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **startedBefore** | **OffsetDateTime**| Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedAfter** | **OffsetDateTime**| Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedBefore** | **OffsetDateTime**| Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedAfter** | **OffsetDateTime**| Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedActivityAfter** | **OffsetDateTime**| Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedActivityBefore** | **OffsetDateTime**| Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedJobAfter** | **OffsetDateTime**| Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedJobBefore** | **OffsetDateTime**| Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedBy** | **String**| Only include process instances that were started by the given user. | [optional]
 **superProcessInstanceId** | **String**| Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. | [optional]
 **subProcessInstanceId** | **String**| Restrict query to one process instance that has a sub process instance with the given id. | [optional]
 **superCaseInstanceId** | **String**| Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. | [optional]
 **subCaseInstanceId** | **String**| Restrict query to one process instance that has a sub case instance with the given id. | [optional]
 **caseInstanceId** | **String**| Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. | [optional]
 **tenantIdIn** | **String**| Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of &#x60;Strings&#x60; | [optional]
 **withoutTenantId** | **Boolean**| Only include historic process instances which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **executedActivityIdIn** | **String**| Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; | [optional]
 **activeActivityIdIn** | **String**| Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; | [optional]
 **active** | **Boolean**| Restrict to instances that are active. | [optional]
 **suspended** | **Boolean**| Restrict to instances that are suspended. | [optional]
 **completed** | **Boolean**| Restrict to instances that are completed. | [optional]
 **externallyTerminated** | **Boolean**| Restrict to instances that are externallyTerminated. | [optional]
 **internallyTerminated** | **Boolean**| Restrict to instances that are internallyTerminated. | [optional]
 **variables** | **String**| Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;.  Key and value may not contain underscore or comma characters.  | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names provided in variables case-insensitively. If set to &#x60;true&#x60; variableName and variablename are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values provided in variables case-insensitively. If set to &#x60;true&#x60; variableValue and variablevalue are treated as equal. | [optional]

### Return type

[**List&lt;HistoricProcessInstanceDto&gt;**](HistoricProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricProcessInstancesCount

> CountResultDto getHistoricProcessInstancesCount(processInstanceId, processInstanceIds, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, processDefinitionKeyNotIn, processInstanceBusinessKey, processInstanceBusinessKeyLike, rootProcessInstances, finished, unfinished, withIncidents, withRootIncidents, incidentType, incidentStatus, incidentMessage, incidentMessageLike, startedBefore, startedAfter, finishedBefore, finishedAfter, executedActivityAfter, executedActivityBefore, executedJobAfter, executedJobBefore, startedBy, superProcessInstanceId, subProcessInstanceId, superCaseInstanceId, subCaseInstanceId, caseInstanceId, tenantIdIn, withoutTenantId, executedActivityIdIn, activeActivityIdIn, active, suspended, completed, externallyTerminated, internallyTerminated, variables, variableNamesIgnoreCase, variableValuesIgnoreCase)

Get List Count

Queries for the number of historic process instances that fulfill the given parameters. Takes the same parameters as the [Get Process Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processInstanceIds = "processInstanceIds_example"; // String | Filter by process instance ids. Filter by a comma-separated list of `Strings`.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the instances run on.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the instances run on.
        String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of `Strings`.
        String processDefinitionName = "processDefinitionName_example"; // String | Filter by the name of the process definition the instances run on.
        String processDefinitionNameLike = "processDefinitionNameLike_example"; // String | Filter by process definition names that the parameter is a substring of.
        String processDefinitionKeyNotIn = "processDefinitionKeyNotIn_example"; // String | Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of `Strings`.
        String processInstanceBusinessKey = "processInstanceBusinessKey_example"; // String | Filter by process instance business key.
        String processInstanceBusinessKeyLike = "processInstanceBusinessKeyLike_example"; // String | Filter by process instance business key that the parameter is a substring of.
        Boolean rootProcessInstances = true; // Boolean | Restrict the query to all process instances that are top level process instances.
        Boolean finished = true; // Boolean | Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be `true`, as `false` is the default behavior.
        Boolean unfinished = true; // Boolean | Only include unfinished process instances. Value may only be `true`, as `false` is the default behavior.
        Boolean withIncidents = true; // Boolean | Only include process instances which have an incident. Value may only be `true`, as `false` is the default behavior.
        Boolean withRootIncidents = true; // Boolean | Only include process instances which have a root incident. Value may only be `true`, as `false` is the default behavior.
        String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
        String incidentStatus = "incidentStatus_example"; // String | Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents.
        String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
        String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
        OffsetDateTime startedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime startedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedActivityAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedActivityBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedJobAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime executedJobBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String startedBy = "startedBy_example"; // String | Only include process instances that were started by the given user.
        String superProcessInstanceId = "superProcessInstanceId_example"; // String | Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id.
        String subProcessInstanceId = "subProcessInstanceId_example"; // String | Restrict query to one process instance that has a sub process instance with the given id.
        String superCaseInstanceId = "superCaseInstanceId_example"; // String | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id.
        String subCaseInstanceId = "subCaseInstanceId_example"; // String | Restrict query to one process instance that has a sub case instance with the given id.
        String caseInstanceId = "caseInstanceId_example"; // String | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of `Strings`
        Boolean withoutTenantId = true; // Boolean | Only include historic process instances which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String executedActivityIdIn = "executedActivityIdIn_example"; // String | Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of `Strings`
        String activeActivityIdIn = "activeActivityIdIn_example"; // String | Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of `Strings`
        Boolean active = true; // Boolean | Restrict to instances that are active.
        Boolean suspended = true; // Boolean | Restrict to instances that are suspended.
        Boolean completed = true; // Boolean | Restrict to instances that are completed.
        Boolean externallyTerminated = true; // Boolean | Restrict to instances that are externallyTerminated.
        Boolean internallyTerminated = true; // Boolean | Restrict to instances that are internallyTerminated.
        String variables = "variables_example"; // String | Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`.  Key and value may not contain underscore or comma characters. 
        Boolean variableNamesIgnoreCase = true; // Boolean | Match all variable names provided in variables case-insensitively. If set to `true` variableName and variablename are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match all variable values provided in variables case-insensitively. If set to `true` variableValue and variablevalue are treated as equal.
        try {
            CountResultDto result = apiInstance.getHistoricProcessInstancesCount(processInstanceId, processInstanceIds, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionName, processDefinitionNameLike, processDefinitionKeyNotIn, processInstanceBusinessKey, processInstanceBusinessKeyLike, rootProcessInstances, finished, unfinished, withIncidents, withRootIncidents, incidentType, incidentStatus, incidentMessage, incidentMessageLike, startedBefore, startedAfter, finishedBefore, finishedAfter, executedActivityAfter, executedActivityBefore, executedJobAfter, executedJobBefore, startedBy, superProcessInstanceId, subProcessInstanceId, superCaseInstanceId, subCaseInstanceId, caseInstanceId, tenantIdIn, withoutTenantId, executedActivityIdIn, activeActivityIdIn, active, suspended, completed, externallyTerminated, internallyTerminated, variables, variableNamesIgnoreCase, variableValuesIgnoreCase);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#getHistoricProcessInstancesCount");
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
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processInstanceIds** | **String**| Filter by process instance ids. Filter by a comma-separated list of &#x60;Strings&#x60;. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the instances run on. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the instances run on. | [optional]
 **processDefinitionKeyIn** | **String**| Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Filter by a comma-separated list of &#x60;Strings&#x60;. | [optional]
 **processDefinitionName** | **String**| Filter by the name of the process definition the instances run on. | [optional]
 **processDefinitionNameLike** | **String**| Filter by process definition names that the parameter is a substring of. | [optional]
 **processDefinitionKeyNotIn** | **String**| Exclude instances that belong to a set of process definitions. Filter by a comma-separated list of &#x60;Strings&#x60;. | [optional]
 **processInstanceBusinessKey** | **String**| Filter by process instance business key. | [optional]
 **processInstanceBusinessKeyLike** | **String**| Filter by process instance business key that the parameter is a substring of. | [optional]
 **rootProcessInstances** | **Boolean**| Restrict the query to all process instances that are top level process instances. | [optional]
 **finished** | **Boolean**| Only include finished process instances. This flag includes all process instances that are completed or terminated. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **unfinished** | **Boolean**| Only include unfinished process instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **withIncidents** | **Boolean**| Only include process instances which have an incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **withRootIncidents** | **Boolean**| Only include process instances which have a root incident. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentStatus** | **String**| Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents. | [optional] [enum: open, resolved]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **startedBefore** | **OffsetDateTime**| Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedAfter** | **OffsetDateTime**| Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedBefore** | **OffsetDateTime**| Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedAfter** | **OffsetDateTime**| Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedActivityAfter** | **OffsetDateTime**| Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedActivityBefore** | **OffsetDateTime**| Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedJobAfter** | **OffsetDateTime**| Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **executedJobBefore** | **OffsetDateTime**| Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedBy** | **String**| Only include process instances that were started by the given user. | [optional]
 **superProcessInstanceId** | **String**| Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. | [optional]
 **subProcessInstanceId** | **String**| Restrict query to one process instance that has a sub process instance with the given id. | [optional]
 **superCaseInstanceId** | **String**| Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. | [optional]
 **subCaseInstanceId** | **String**| Restrict query to one process instance that has a sub case instance with the given id. | [optional]
 **caseInstanceId** | **String**| Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. | [optional]
 **tenantIdIn** | **String**| Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Filter by a comma-separated list of &#x60;Strings&#x60; | [optional]
 **withoutTenantId** | **Boolean**| Only include historic process instances which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **executedActivityIdIn** | **String**| Restrict to instances that executed an activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; | [optional]
 **activeActivityIdIn** | **String**| Restrict to instances that have an active activity with one of given ids. Filter by a comma-separated list of &#x60;Strings&#x60; | [optional]
 **active** | **Boolean**| Restrict to instances that are active. | [optional]
 **suspended** | **Boolean**| Restrict to instances that are suspended. | [optional]
 **completed** | **Boolean**| Restrict to instances that are completed. | [optional]
 **externallyTerminated** | **Boolean**| Restrict to instances that are externallyTerminated. | [optional]
 **internallyTerminated** | **Boolean**| Restrict to instances that are internallyTerminated. | [optional]
 **variables** | **String**| Only include process instances that have/had variables with certain values. Variable filtering expressions are comma-separated and are structured as follows: A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as String objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;.  Key and value may not contain underscore or comma characters.  | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names provided in variables case-insensitively. If set to &#x60;true&#x60; variableName and variablename are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values provided in variables case-insensitively. If set to &#x60;true&#x60; variableValue and variablevalue are treated as equal. | [optional]

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
| **400** | Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryHistoricProcessInstances

> List&lt;HistoricProcessInstanceDto&gt; queryHistoricProcessInstances(firstResult, maxResults, historicProcessInstanceQueryDto)

Get List (POST)

Queries for historic process instances that fulfill the given parameters. This method is slightly more powerful than the [Get Process Instance](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) because it allows filtering by multiple process variables of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        HistoricProcessInstanceQueryDto historicProcessInstanceQueryDto = new HistoricProcessInstanceQueryDto(); // HistoricProcessInstanceQueryDto | 
        try {
            List<HistoricProcessInstanceDto> result = apiInstance.queryHistoricProcessInstances(firstResult, maxResults, historicProcessInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#queryHistoricProcessInstances");
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
 **historicProcessInstanceQueryDto** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md)|  | [optional]

### Return type

[**List&lt;HistoricProcessInstanceDto&gt;**](HistoricProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryHistoricProcessInstancesCount

> CountResultDto queryHistoricProcessInstancesCount(historicProcessInstanceQueryDto)

Get List Count (POST)

Queries for the number of historic process instances that fulfill the given parameters. This method takes the same message body as the [Get Process Instances (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/get-process-instance-query/) method and therefore it is slightly more powerful than the [Get Process Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-instance/post-process-instance-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        HistoricProcessInstanceQueryDto historicProcessInstanceQueryDto = new HistoricProcessInstanceQueryDto(); // HistoricProcessInstanceQueryDto | 
        try {
            CountResultDto result = apiInstance.queryHistoricProcessInstancesCount(historicProcessInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#queryHistoricProcessInstancesCount");
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
 **historicProcessInstanceQueryDto** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md)|  | [optional]

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
| **400** | Bad Request Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setRemovalTimeAsync

> BatchDto setRemovalTimeAsync(setRemovalTimeToHistoricProcessInstancesDto)

Set Removal Time Async (POST)

Sets the removal time to multiple historic process instances asynchronously (batch).  At least &#x60;historicProcessInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60; has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessInstanceApi apiInstance = new HistoricProcessInstanceApi(defaultClient);
        SetRemovalTimeToHistoricProcessInstancesDto setRemovalTimeToHistoricProcessInstancesDto = new SetRemovalTimeToHistoricProcessInstancesDto(); // SetRemovalTimeToHistoricProcessInstancesDto | 
        try {
            BatchDto result = apiInstance.setRemovalTimeAsync(setRemovalTimeToHistoricProcessInstancesDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessInstanceApi#setRemovalTimeAsync");
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
 **setRemovalTimeToHistoricProcessInstancesDto** | [**SetRemovalTimeToHistoricProcessInstancesDto**](SetRemovalTimeToHistoricProcessInstancesDto.md)|  | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Bad Request Request was unsuccessfull due to a bad user request. This occurs if some of the query parameters are invalid, e. g. if neither &#x60;historicProcessInstances&#x60; nor &#x60;historicProcessInstanceQuery&#x60; is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

