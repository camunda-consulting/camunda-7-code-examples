# ExternalTaskApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**completeExternalTaskResource**](ExternalTaskApi.md#completeExternalTaskResource) | **POST** /external-task/{id}/complete | 
[**extendLock**](ExternalTaskApi.md#extendLock) | **POST** /external-task/{id}/extendLock | 
[**fetchAndLock**](ExternalTaskApi.md#fetchAndLock) | **POST** /external-task/fetchAndLock | 
[**getExternalTask**](ExternalTaskApi.md#getExternalTask) | **GET** /external-task/{id} | 
[**getExternalTaskErrorDetails**](ExternalTaskApi.md#getExternalTaskErrorDetails) | **GET** /external-task/{id}/errorDetails | 
[**getExternalTasks**](ExternalTaskApi.md#getExternalTasks) | **GET** /external-task | 
[**getExternalTasksCount**](ExternalTaskApi.md#getExternalTasksCount) | **GET** /external-task/count | 
[**getTopicNames**](ExternalTaskApi.md#getTopicNames) | **GET** /external-task/topic-names | 
[**handleExternalTaskBpmnError**](ExternalTaskApi.md#handleExternalTaskBpmnError) | **POST** /external-task/{id}/bpmnError | 
[**handleFailure**](ExternalTaskApi.md#handleFailure) | **POST** /external-task/{id}/failure | 
[**queryExternalTasks**](ExternalTaskApi.md#queryExternalTasks) | **POST** /external-task | 
[**queryExternalTasksCount**](ExternalTaskApi.md#queryExternalTasksCount) | **POST** /external-task/count | 
[**setExternalTaskResourcePriority**](ExternalTaskApi.md#setExternalTaskResourcePriority) | **PUT** /external-task/{id}/priority | 
[**setExternalTaskResourceRetries**](ExternalTaskApi.md#setExternalTaskResourceRetries) | **PUT** /external-task/{id}/retries | 
[**setExternalTaskRetries**](ExternalTaskApi.md#setExternalTaskRetries) | **PUT** /external-task/retries | 
[**setExternalTaskRetriesAsyncOperation**](ExternalTaskApi.md#setExternalTaskRetriesAsyncOperation) | **POST** /external-task/retries-async | 
[**unlock**](ExternalTaskApi.md#unlock) | **POST** /external-task/{id}/unlock | 


<a name="completeExternalTaskResource"></a>
# **completeExternalTaskResource**
> completeExternalTaskResource(id, completeExternalTaskDto)



Completes an external task by id and updates process variables.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the task to complete.
    CompleteExternalTaskDto completeExternalTaskDto = {"workerId":"aWorker","variables":{"aVariable":{"value":"aStringValue"},"anotherVariable":{"value":42},"aThirdVariable":{"value":true}},"localVariables":{"aLocalVariable":{"value":"aStringValue"}}}; // CompleteExternalTaskDto | 
    try {
      apiInstance.completeExternalTaskResource(id, completeExternalTaskDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#completeExternalTaskResource");
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
 **id** | **String**| The id of the task to complete. |
 **completeExternalTaskDto** | [**CompleteExternalTaskDto**](CompleteExternalTaskDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Returned if the task&#39;s most recent lock was not acquired by the provided worker. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | Returned if the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="extendLock"></a>
# **extendLock**
> extendLock(id, extendLockOnExternalTaskDto)



Extends the timeout of the lock by a given amount of time.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task.
    ExtendLockOnExternalTaskDto extendLockOnExternalTaskDto = {"workerId":"anId","newDuration":100000}; // ExtendLockOnExternalTaskDto | 
    try {
      apiInstance.extendLock(id, extendLockOnExternalTaskDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#extendLock");
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
 **id** | **String**| The id of the external task. |
 **extendLockOnExternalTaskDto** | [**ExtendLockOnExternalTaskDto**](ExtendLockOnExternalTaskDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | In case the new lock duration is negative or the external task is not locked by the given worker or not  locked at all, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="fetchAndLock"></a>
# **fetchAndLock**
> List&lt;LockedExternalTaskDto&gt; fetchAndLock(fetchExternalTasksDto)



Fetches and locks a specific number of external tasks for execution by a worker. Query can be restricted to specific task topics and for each task topic an individual lock time can be provided.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    FetchExternalTasksDto fetchExternalTasksDto = {"workerId":"aWorkerId","maxTasks":2,"usePriority":true,"topics":[{"topicName":"createOrder","lockDuration":10000,"variables":["orderId"]}]}; // FetchExternalTasksDto | 
    try {
      List<LockedExternalTaskDto> result = apiInstance.fetchAndLock(fetchExternalTasksDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#fetchAndLock");
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
 **fetchExternalTasksDto** | [**FetchExternalTasksDto**](FetchExternalTasksDto.md)|  | [optional]

### Return type

[**List&lt;LockedExternalTaskDto&gt;**](LockedExternalTaskDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getExternalTask"></a>
# **getExternalTask**
> ExternalTaskDto getExternalTask(id)



Retrieves an external task by id, corresponding to the &#x60;ExternalTask&#x60; interface in the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task to be retrieved.
    try {
      ExternalTaskDto result = apiInstance.getExternalTask(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#getExternalTask");
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
 **id** | **String**| The id of the external task to be retrieved. |

### Return type

[**ExternalTaskDto**](ExternalTaskDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**404** | External task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getExternalTaskErrorDetails"></a>
# **getExternalTaskErrorDetails**
> String getExternalTaskErrorDetails(id)



Retrieves the error details in the context of a running external task by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task for which the error details should be retrieved.
    try {
      String result = apiInstance.getExternalTaskErrorDetails(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#getExternalTaskErrorDetails");
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
 **id** | **String**| The id of the external task for which the error details should be retrieved. |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**204** | Request successful. In case the external task has no error details. |  -  |
**500** | An external task with the given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getExternalTasks"></a>
# **getExternalTasks**
> List&lt;ExternalTaskDto&gt; getExternalTasks(externalTaskId, externalTaskIdIn, topicName, workerId, locked, notLocked, withRetriesLeft, noRetriesLeft, lockExpirationAfter, lockExpirationBefore, activityId, activityIdIn, executionId, processInstanceId, processInstanceIdIn, processDefinitionId, tenantIdIn, active, suspended, priorityHigherThanOrEquals, priorityLowerThanOrEquals, sortBy, sortOrder, firstResult, maxResults)



Queries for the external tasks that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of executions. The size of the result set can be retrieved by using the [Get External Task Count](https://docs.camunda.org/manual/7.13/reference/rest/external-task/get-query-count/) method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String externalTaskId = "externalTaskId_example"; // String | Filter by an external task's id.
    String externalTaskIdIn = "externalTaskIdIn_example"; // String | Filter by the comma-separated list of external task ids.
    String topicName = "topicName_example"; // String | Filter by an external task topic.
    String workerId = "workerId_example"; // String | Filter by the id of the worker that the task was most recently locked by.
    Boolean locked = true; // Boolean | Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be `true`, as `false` matches any external task.
    Boolean notLocked = true; // Boolean | Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be `true`, as `false` matches any external task.
    Boolean withRetriesLeft = true; // Boolean | Only include external tasks that have a positive (&gt; 0) number of retries (or `null`). Value may only be `true`, as `false` matches any external task.
    Boolean noRetriesLeft = true; // Boolean | Only include external tasks that have 0 retries. Value may only be `true`, as `false` matches any external task.
    OffsetDateTime lockExpirationAfter = new OffsetDateTime(); // OffsetDateTime | Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    OffsetDateTime lockExpirationBefore = new OffsetDateTime(); // OffsetDateTime | Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    String activityId = "activityId_example"; // String | Filter by the id of the activity that an external task is created for.
    String activityIdIn = "activityIdIn_example"; // String | Filter by the comma-separated list of ids of the activities that an external task is created for.
    String executionId = "executionId_example"; // String | Filter by the id of the execution that an external task belongs to.
    String processInstanceId = "processInstanceId_example"; // String | Filter by the id of the process instance that an external task belongs to.
    String processInstanceIdIn = "processInstanceIdIn_example"; // String | Filter by a comma-separated list of process instance ids that an external task may belong to.
    String processDefinitionId = "processDefinitionId_example"; // String | Filter by the id of the process definition that an external task belongs to.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids.
    Boolean active = true; // Boolean | Only include active tasks. Value may only be `true`, as `false` matches any external task.
    Boolean suspended = true; // Boolean | Only include suspended tasks. Value may only be `true`, as `false` matches any external task.
    Long priorityHigherThanOrEquals = 56L; // Long | Only include jobs with a priority higher than or equal to the given value. Value must be a valid `long` value.
    Long priorityLowerThanOrEquals = 56L; // Long | Only include jobs with a priority lower than or equal to the given value. Value must be a valid `long` value.
    String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
    String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    try {
      List<ExternalTaskDto> result = apiInstance.getExternalTasks(externalTaskId, externalTaskIdIn, topicName, workerId, locked, notLocked, withRetriesLeft, noRetriesLeft, lockExpirationAfter, lockExpirationBefore, activityId, activityIdIn, executionId, processInstanceId, processInstanceIdIn, processDefinitionId, tenantIdIn, active, suspended, priorityHigherThanOrEquals, priorityLowerThanOrEquals, sortBy, sortOrder, firstResult, maxResults);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#getExternalTasks");
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
 **externalTaskId** | **String**| Filter by an external task&#39;s id. | [optional]
 **externalTaskIdIn** | **String**| Filter by the comma-separated list of external task ids. | [optional]
 **topicName** | **String**| Filter by an external task topic. | [optional]
 **workerId** | **String**| Filter by the id of the worker that the task was most recently locked by. | [optional]
 **locked** | **Boolean**| Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **notLocked** | **Boolean**| Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **withRetriesLeft** | **Boolean**| Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **noRetriesLeft** | **Boolean**| Only include external tasks that have 0 retries. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **lockExpirationAfter** | **OffsetDateTime**| Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **lockExpirationBefore** | **OffsetDateTime**| Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **activityId** | **String**| Filter by the id of the activity that an external task is created for. | [optional]
 **activityIdIn** | **String**| Filter by the comma-separated list of ids of the activities that an external task is created for. | [optional]
 **executionId** | **String**| Filter by the id of the execution that an external task belongs to. | [optional]
 **processInstanceId** | **String**| Filter by the id of the process instance that an external task belongs to. | [optional]
 **processInstanceIdIn** | **String**| Filter by a comma-separated list of process instance ids that an external task may belong to. | [optional]
 **processDefinitionId** | **String**| Filter by the id of the process definition that an external task belongs to. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids. | [optional]
 **active** | **Boolean**| Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **suspended** | **Boolean**| Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **priorityHigherThanOrEquals** | **Long**| Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **priorityLowerThanOrEquals** | **Long**| Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: id, lockExpirationTime, processInstanceId, processDefinitionId, processDefinitionKey, taskPriority, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;ExternalTaskDto&gt;**](ExternalTaskDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getExternalTasksCount"></a>
# **getExternalTasksCount**
> CountResultDto getExternalTasksCount(externalTaskId, externalTaskIdIn, topicName, workerId, locked, notLocked, withRetriesLeft, noRetriesLeft, lockExpirationAfter, lockExpirationBefore, activityId, activityIdIn, executionId, processInstanceId, processInstanceIdIn, processDefinitionId, tenantIdIn, active, suspended, priorityHigherThanOrEquals, priorityLowerThanOrEquals)



Queries for the number of external tasks that fulfill given parameters. Takes the same parameters as the [Get External Tasks](https://docs.camunda.org/manual/7.13/reference/rest/external-task/get-query/) method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String externalTaskId = "externalTaskId_example"; // String | Filter by an external task's id.
    String externalTaskIdIn = "externalTaskIdIn_example"; // String | Filter by the comma-separated list of external task ids.
    String topicName = "topicName_example"; // String | Filter by an external task topic.
    String workerId = "workerId_example"; // String | Filter by the id of the worker that the task was most recently locked by.
    Boolean locked = true; // Boolean | Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be `true`, as `false` matches any external task.
    Boolean notLocked = true; // Boolean | Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be `true`, as `false` matches any external task.
    Boolean withRetriesLeft = true; // Boolean | Only include external tasks that have a positive (&gt; 0) number of retries (or `null`). Value may only be `true`, as `false` matches any external task.
    Boolean noRetriesLeft = true; // Boolean | Only include external tasks that have 0 retries. Value may only be `true`, as `false` matches any external task.
    OffsetDateTime lockExpirationAfter = new OffsetDateTime(); // OffsetDateTime | Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    OffsetDateTime lockExpirationBefore = new OffsetDateTime(); // OffsetDateTime | Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    String activityId = "activityId_example"; // String | Filter by the id of the activity that an external task is created for.
    String activityIdIn = "activityIdIn_example"; // String | Filter by the comma-separated list of ids of the activities that an external task is created for.
    String executionId = "executionId_example"; // String | Filter by the id of the execution that an external task belongs to.
    String processInstanceId = "processInstanceId_example"; // String | Filter by the id of the process instance that an external task belongs to.
    String processInstanceIdIn = "processInstanceIdIn_example"; // String | Filter by a comma-separated list of process instance ids that an external task may belong to.
    String processDefinitionId = "processDefinitionId_example"; // String | Filter by the id of the process definition that an external task belongs to.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids.
    Boolean active = true; // Boolean | Only include active tasks. Value may only be `true`, as `false` matches any external task.
    Boolean suspended = true; // Boolean | Only include suspended tasks. Value may only be `true`, as `false` matches any external task.
    Long priorityHigherThanOrEquals = 56L; // Long | Only include jobs with a priority higher than or equal to the given value. Value must be a valid `long` value.
    Long priorityLowerThanOrEquals = 56L; // Long | Only include jobs with a priority lower than or equal to the given value. Value must be a valid `long` value.
    try {
      CountResultDto result = apiInstance.getExternalTasksCount(externalTaskId, externalTaskIdIn, topicName, workerId, locked, notLocked, withRetriesLeft, noRetriesLeft, lockExpirationAfter, lockExpirationBefore, activityId, activityIdIn, executionId, processInstanceId, processInstanceIdIn, processDefinitionId, tenantIdIn, active, suspended, priorityHigherThanOrEquals, priorityLowerThanOrEquals);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#getExternalTasksCount");
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
 **externalTaskId** | **String**| Filter by an external task&#39;s id. | [optional]
 **externalTaskIdIn** | **String**| Filter by the comma-separated list of external task ids. | [optional]
 **topicName** | **String**| Filter by an external task topic. | [optional]
 **workerId** | **String**| Filter by the id of the worker that the task was most recently locked by. | [optional]
 **locked** | **Boolean**| Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **notLocked** | **Boolean**| Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **withRetriesLeft** | **Boolean**| Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **noRetriesLeft** | **Boolean**| Only include external tasks that have 0 retries. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **lockExpirationAfter** | **OffsetDateTime**| Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **lockExpirationBefore** | **OffsetDateTime**| Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **activityId** | **String**| Filter by the id of the activity that an external task is created for. | [optional]
 **activityIdIn** | **String**| Filter by the comma-separated list of ids of the activities that an external task is created for. | [optional]
 **executionId** | **String**| Filter by the id of the execution that an external task belongs to. | [optional]
 **processInstanceId** | **String**| Filter by the id of the process instance that an external task belongs to. | [optional]
 **processInstanceIdIn** | **String**| Filter by a comma-separated list of process instance ids that an external task may belong to. | [optional]
 **processDefinitionId** | **String**| Filter by the id of the process definition that an external task belongs to. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids. | [optional]
 **active** | **Boolean**| Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **suspended** | **Boolean**| Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **priorityHigherThanOrEquals** | **Long**| Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **priorityLowerThanOrEquals** | **Long**| Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]

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
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getTopicNames"></a>
# **getTopicNames**
> List&lt;String&gt; getTopicNames(withLockedTasks, withUnlockedTasks, withRetriesLeft)



Queries for distinct topic names of external tasks that fulfill given parameters. Query can be restricted to only tasks with retries left, tasks that are locked, or tasks that are unlocked. The parameters withLockedTasks and withUnlockedTasks are exclusive. Setting them both to true will return an empty list. Providing no parameters will return a list of all distinct topic names with external tasks.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    Boolean withLockedTasks = true; // Boolean | Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be `true`, as `false` matches any external task.
    Boolean withUnlockedTasks = true; // Boolean | Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be `true`, as `false` matches any external task.
    Boolean withRetriesLeft = true; // Boolean | Only include external tasks that have a positive (&gt; 0) number of retries (or `null`). Value may only be `true`, as `false` matches any external task.
    try {
      List<String> result = apiInstance.getTopicNames(withLockedTasks, withUnlockedTasks, withRetriesLeft);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#getTopicNames");
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
 **withLockedTasks** | **Boolean**| Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **withUnlockedTasks** | **Boolean**| Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]
 **withRetriesLeft** | **Boolean**| Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. | [optional]

### Return type

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. |  -  |

<a name="handleExternalTaskBpmnError"></a>
# **handleExternalTaskBpmnError**
> handleExternalTaskBpmnError(id, externalTaskBpmnError)



Reports a business error in the context of a running external task by id. The error code must be specified to identify the BPMN error handler.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task in which context a BPMN error is reported.
    ExternalTaskBpmnError externalTaskBpmnError = {"workerId":"aWorker","errorCode":"bpmn-error","errorMessage":"anErrorMessage","variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}}}; // ExternalTaskBpmnError | 
    try {
      apiInstance.handleExternalTaskBpmnError(id, externalTaskBpmnError);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#handleExternalTaskBpmnError");
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
 **id** | **String**| The id of the external task in which context a BPMN error is reported. |
 **externalTaskBpmnError** | [**ExternalTaskBpmnError**](ExternalTaskBpmnError.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Returned if the task&#39;s most recent lock was not acquired by the provided worker.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | Returned if the corresponding process instance could not be resumed successfully.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="handleFailure"></a>
# **handleFailure**
> handleFailure(id, externalTaskFailureDto)



Reports a failure to execute an external task by id. A number of retries and a timeout until the task can be retried can be specified. If retries are set to 0, an incident for this task is created.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task to report a failure for.
    ExternalTaskFailureDto externalTaskFailureDto = {"workerId":"aWorker","errorMessage":"Does not compute","retries":3,"retryTimeout":60000}; // ExternalTaskFailureDto | 
    try {
      apiInstance.handleFailure(id, externalTaskFailureDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#handleFailure");
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
 **id** | **String**| The id of the external task to report a failure for. |
 **externalTaskFailureDto** | [**ExternalTaskFailureDto**](ExternalTaskFailureDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Returned if the task&#39;s most recent lock was not acquired by the provided worker. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | Returned if the corresponding process instance could not be resumed successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="queryExternalTasks"></a>
# **queryExternalTasks**
> List&lt;ExternalTaskDto&gt; queryExternalTasks(firstResult, maxResults, externalTaskQueryDto)



Queries for external tasks that fulfill given parameters in the form of a JSON object.  This method is slightly more powerful than the [Get External Tasks](https://docs.camunda.org/manual/7.13/reference/rest/external-task/get-query/) method because it allows to specify a hierarchical result sorting.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    ExternalTaskQueryDto externalTaskQueryDto = {"processDefinitionId":"aProcessDefinitionId","sorting":[{"sortBy":"processDefinitionKey","sortOrder":"asc"},{"sortBy":"lockExpirationTime","sortOrder":"desc"}]}; // ExternalTaskQueryDto | 
    try {
      List<ExternalTaskDto> result = apiInstance.queryExternalTasks(firstResult, maxResults, externalTaskQueryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#queryExternalTasks");
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
 **externalTaskQueryDto** | [**ExternalTaskQueryDto**](ExternalTaskQueryDto.md)|  | [optional]

### Return type

[**List&lt;ExternalTaskDto&gt;**](ExternalTaskDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. The Response is a JSON array of external task objects. |  -  |
**400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="queryExternalTasksCount"></a>
# **queryExternalTasksCount**
> CountResultDto queryExternalTasksCount(externalTaskQueryDto)



Queries for the number of external tasks that fulfill given parameters. This method takes the same message body as the [Get External Tasks (POST)](https://docs.camunda.org/manual/7.13/reference/rest/external-task/post-query/) method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    ExternalTaskQueryDto externalTaskQueryDto = {"topicName":"aTopicName","withRetriesLeft":true}; // ExternalTaskQueryDto | 
    try {
      CountResultDto result = apiInstance.queryExternalTasksCount(externalTaskQueryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#queryExternalTasksCount");
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
 **externalTaskQueryDto** | [**ExternalTaskQueryDto**](ExternalTaskQueryDto.md)|  | [optional]

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
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="setExternalTaskResourcePriority"></a>
# **setExternalTaskResourcePriority**
> setExternalTaskResourcePriority(id, priorityDto)



Sets the priority of an existing external task by id. The default value of a priority is 0.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task to set the priority for.
    PriorityDto priorityDto = {"priority":5}; // PriorityDto | 
    try {
      apiInstance.setExternalTaskResourcePriority(id, priorityDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#setExternalTaskResourcePriority");
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
 **id** | **String**| The id of the external task to set the priority for. |
 **priorityDto** | [**PriorityDto**](PriorityDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="setExternalTaskResourceRetries"></a>
# **setExternalTaskResourceRetries**
> setExternalTaskResourceRetries(id, retriesDto)



Sets the number of retries left to execute an external task by id. If retries are set to 0, an  incident is created.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task to set the number of retries for.
    RetriesDto retriesDto = {"retries":123}; // RetriesDto | 
    try {
      apiInstance.setExternalTaskResourceRetries(id, retriesDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#setExternalTaskResourceRetries");
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
 **id** | **String**| The id of the external task to set the number of retries for. |
 **retriesDto** | [**RetriesDto**](RetriesDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | In case the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="setExternalTaskRetries"></a>
# **setExternalTaskRetries**
> setExternalTaskRetries(setRetriesForExternalTasksDto)



Sets the number of retries left to execute external tasks by id synchronously. If retries are set to 0,  an incident is created.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    SetRetriesForExternalTasksDto setRetriesForExternalTasksDto = {"retries":123,"externalTaskIds":["anExternalTask","anotherExternalTask"]}; // SetRetriesForExternalTasksDto | 
    try {
      apiInstance.setExternalTaskRetries(setRetriesForExternalTasksDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#setExternalTaskRetries");
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
 **setRetriesForExternalTasksDto** | [**SetRetriesForExternalTasksDto**](SetRetriesForExternalTasksDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | In case the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task,  e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="setExternalTaskRetriesAsyncOperation"></a>
# **setExternalTaskRetriesAsyncOperation**
> BatchDto setExternalTaskRetriesAsyncOperation(setRetriesForExternalTasksDto)



Sets the number of retries left to execute external tasks by id asynchronously. If retries are set to 0, an incident is created.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    SetRetriesForExternalTasksDto setRetriesForExternalTasksDto = {"retries":123,"externalTaskIds":["anExternalTask","anotherExternalTask"]}; // SetRetriesForExternalTasksDto | 
    try {
      BatchDto result = apiInstance.setExternalTaskRetriesAsyncOperation(setRetriesForExternalTasksDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#setExternalTaskRetriesAsyncOperation");
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
 **setRetriesForExternalTasksDto** | [**SetRetriesForExternalTasksDto**](SetRetriesForExternalTasksDto.md)|  | [optional]

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
**200** | Request successful. |  -  |
**400** | If neither externalTaskIds nor externalTaskQuery are present or externalTaskIds contains null value or  the number of retries is negative or null, an exception of type &#x60;InvalidRequestException&#x60; is returned. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task,  e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="unlock"></a>
# **unlock**
> unlock(id)



Unlocks an external task by id. Clears the task&#39;s lock expiration time and worker id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ExternalTaskApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ExternalTaskApi apiInstance = new ExternalTaskApi(defaultClient);
    String id = "id_example"; // String | The id of the external task to unlock.
    try {
      apiInstance.unlock(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling ExternalTaskApi#unlock");
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
 **id** | **String**| The id of the external task to unlock. |

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
**204** | Request successful. |  -  |
**404** | Returned if the task does not exist. This could indicate a wrong task id as well as a cancelled task, e.g., due to a caught BPMN boundary event. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

