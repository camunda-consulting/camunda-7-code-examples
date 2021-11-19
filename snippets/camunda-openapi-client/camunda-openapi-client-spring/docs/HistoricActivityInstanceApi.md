# HistoricActivityInstanceApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHistoricActivityInstance**](HistoricActivityInstanceApi.md#getHistoricActivityInstance) | **GET** /history/activity-instance/{id} | Get
[**getHistoricActivityInstances**](HistoricActivityInstanceApi.md#getHistoricActivityInstances) | **GET** /history/activity-instance | Get List
[**getHistoricActivityInstancesCount**](HistoricActivityInstanceApi.md#getHistoricActivityInstancesCount) | **GET** /history/activity-instance/count | Get List Count
[**queryHistoricActivityInstances**](HistoricActivityInstanceApi.md#queryHistoricActivityInstances) | **POST** /history/activity-instance | Get List (POST)
[**queryHistoricActivityInstancesCount**](HistoricActivityInstanceApi.md#queryHistoricActivityInstancesCount) | **POST** /history/activity-instance/count | Get List Count (POST)



## getHistoricActivityInstance

> HistoricActivityInstanceDto getHistoricActivityInstance(id)

Get

Retrieves a historic activity instance by id, according to the &#x60;HistoricActivityInstance&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricActivityInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricActivityInstanceApi apiInstance = new HistoricActivityInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the historic activity instance to be retrieved.
        try {
            HistoricActivityInstanceDto result = apiInstance.getHistoricActivityInstance(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricActivityInstanceApi#getHistoricActivityInstance");
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
 **id** | **String**| The id of the historic activity instance to be retrieved. |

### Return type

[**HistoricActivityInstanceDto**](HistoricActivityInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Not Found Historic activity instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricActivityInstances

> List&lt;HistoricActivityInstanceDto&gt; getHistoricActivityInstances(sortBy, sortOrder, firstResult, maxResults, activityInstanceId, processInstanceId, processDefinitionId, executionId, activityId, activityName, activityType, taskAssignee, finished, unfinished, canceled, completeScope, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId)

Get List

Queries for historic activity instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Activity Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricActivityInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricActivityInstanceApi apiInstance = new HistoricActivityInstanceApi(defaultClient);
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String activityInstanceId = "activityInstanceId_example"; // String | Filter by activity instance id.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String executionId = "executionId_example"; // String | Filter by the id of the execution that executed the activity instance.
        String activityId = "activityId_example"; // String | Filter by the activity id (according to BPMN 2.0 XML).
        String activityName = "activityName_example"; // String | Filter by the activity name (according to BPMN 2.0 XML).
        String activityType = "activityType_example"; // String | Filter by activity type.
        String taskAssignee = "taskAssignee_example"; // String | Only include activity instances that are user tasks and assigned to a given user.
        Boolean finished = true; // Boolean | Only include finished activity instances. Value may only be `true`, as `false` behaves the same as when the property is not set.
        Boolean unfinished = true; // Boolean | Only include unfinished activity instances. Value may only be `true`, as `false` behaves the same as when the property is not set.
        Boolean canceled = true; // Boolean | Only include canceled activity instances. Value may only be `true`, as `false` behaves the same as when the property is not set.
        Boolean completeScope = true; // Boolean | Only include activity instances which completed a scope. Value may only be `true`, as `false` behaves the same as when the property is not set.
        OffsetDateTime startedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime startedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic activity instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        try {
            List<HistoricActivityInstanceDto> result = apiInstance.getHistoricActivityInstances(sortBy, sortOrder, firstResult, maxResults, activityInstanceId, processInstanceId, processDefinitionId, executionId, activityId, activityName, activityType, taskAssignee, finished, unfinished, canceled, completeScope, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricActivityInstanceApi#getHistoricActivityInstances");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: activityInstanceId, instanceId, executionId, activityId, activityName, activityType, startTime, endTime, duration, definitionId, occurrence, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **activityInstanceId** | **String**| Filter by activity instance id. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **executionId** | **String**| Filter by the id of the execution that executed the activity instance. | [optional]
 **activityId** | **String**| Filter by the activity id (according to BPMN 2.0 XML). | [optional]
 **activityName** | **String**| Filter by the activity name (according to BPMN 2.0 XML). | [optional]
 **activityType** | **String**| Filter by activity type. | [optional]
 **taskAssignee** | **String**| Only include activity instances that are user tasks and assigned to a given user. | [optional]
 **finished** | **Boolean**| Only include finished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **unfinished** | **Boolean**| Only include unfinished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **canceled** | **Boolean**| Only include canceled activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **completeScope** | **Boolean**| Only include activity instances which completed a scope. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **startedBefore** | **OffsetDateTime**| Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedAfter** | **OffsetDateTime**| Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedBefore** | **OffsetDateTime**| Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedAfter** | **OffsetDateTime**| Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic activity instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

### Return type

[**List&lt;HistoricActivityInstanceDto&gt;**](HistoricActivityInstanceDto.md)

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


## getHistoricActivityInstancesCount

> CountResultDto getHistoricActivityInstancesCount(activityInstanceId, processInstanceId, processDefinitionId, executionId, activityId, activityName, activityType, taskAssignee, finished, unfinished, canceled, completeScope, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId)

Get List Count

Queries for the number of historic activity instances that fulfill the given parameters. Takes the same parameters as the [Get Historic Activity Instance](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query/)  method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricActivityInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricActivityInstanceApi apiInstance = new HistoricActivityInstanceApi(defaultClient);
        String activityInstanceId = "activityInstanceId_example"; // String | Filter by activity instance id.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String executionId = "executionId_example"; // String | Filter by the id of the execution that executed the activity instance.
        String activityId = "activityId_example"; // String | Filter by the activity id (according to BPMN 2.0 XML).
        String activityName = "activityName_example"; // String | Filter by the activity name (according to BPMN 2.0 XML).
        String activityType = "activityType_example"; // String | Filter by activity type.
        String taskAssignee = "taskAssignee_example"; // String | Only include activity instances that are user tasks and assigned to a given user.
        Boolean finished = true; // Boolean | Only include finished activity instances. Value may only be `true`, as `false` behaves the same as when the property is not set.
        Boolean unfinished = true; // Boolean | Only include unfinished activity instances. Value may only be `true`, as `false` behaves the same as when the property is not set.
        Boolean canceled = true; // Boolean | Only include canceled activity instances. Value may only be `true`, as `false` behaves the same as when the property is not set.
        Boolean completeScope = true; // Boolean | Only include activity instances which completed a scope. Value may only be `true`, as `false` behaves the same as when the property is not set.
        OffsetDateTime startedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime startedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic activity instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getHistoricActivityInstancesCount(activityInstanceId, processInstanceId, processDefinitionId, executionId, activityId, activityName, activityType, taskAssignee, finished, unfinished, canceled, completeScope, startedBefore, startedAfter, finishedBefore, finishedAfter, tenantIdIn, withoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricActivityInstanceApi#getHistoricActivityInstancesCount");
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
 **activityInstanceId** | **String**| Filter by activity instance id. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **executionId** | **String**| Filter by the id of the execution that executed the activity instance. | [optional]
 **activityId** | **String**| Filter by the activity id (according to BPMN 2.0 XML). | [optional]
 **activityName** | **String**| Filter by the activity name (according to BPMN 2.0 XML). | [optional]
 **activityType** | **String**| Filter by activity type. | [optional]
 **taskAssignee** | **String**| Only include activity instances that are user tasks and assigned to a given user. | [optional]
 **finished** | **Boolean**| Only include finished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **unfinished** | **Boolean**| Only include unfinished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **canceled** | **Boolean**| Only include canceled activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **completeScope** | **Boolean**| Only include activity instances which completed a scope. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. | [optional]
 **startedBefore** | **OffsetDateTime**| Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedAfter** | **OffsetDateTime**| Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedBefore** | **OffsetDateTime**| Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedAfter** | **OffsetDateTime**| Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of ids. An activity instance must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic activity instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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


## queryHistoricActivityInstances

> List&lt;HistoricActivityInstanceDto&gt; queryHistoricActivityInstances(firstResult, maxResults, historicActivityInstanceQueryDto)

Get List (POST)

Queries for historic activity instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Activity Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/activity-instance/get-activity-instance-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricActivityInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricActivityInstanceApi apiInstance = new HistoricActivityInstanceApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        HistoricActivityInstanceQueryDto historicActivityInstanceQueryDto = new HistoricActivityInstanceQueryDto(); // HistoricActivityInstanceQueryDto | 
        try {
            List<HistoricActivityInstanceDto> result = apiInstance.queryHistoricActivityInstances(firstResult, maxResults, historicActivityInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricActivityInstanceApi#queryHistoricActivityInstances");
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
 **historicActivityInstanceQueryDto** | [**HistoricActivityInstanceQueryDto**](HistoricActivityInstanceQueryDto.md)|  | [optional]

### Return type

[**List&lt;HistoricActivityInstanceDto&gt;**](HistoricActivityInstanceDto.md)

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


## queryHistoricActivityInstancesCount

> CountResultDto queryHistoricActivityInstancesCount(historicActivityInstanceQueryDto)

Get List Count (POST)

Queries for the number of historic activity instances that fulfill the given parameters.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricActivityInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricActivityInstanceApi apiInstance = new HistoricActivityInstanceApi(defaultClient);
        HistoricActivityInstanceQueryDto historicActivityInstanceQueryDto = new HistoricActivityInstanceQueryDto(); // HistoricActivityInstanceQueryDto | 
        try {
            CountResultDto result = apiInstance.queryHistoricActivityInstancesCount(historicActivityInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricActivityInstanceApi#queryHistoricActivityInstancesCount");
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
 **historicActivityInstanceQueryDto** | [**HistoricActivityInstanceQueryDto**](HistoricActivityInstanceQueryDto.md)|  | [optional]

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

