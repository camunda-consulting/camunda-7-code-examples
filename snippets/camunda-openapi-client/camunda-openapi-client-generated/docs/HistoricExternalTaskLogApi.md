# HistoricExternalTaskLogApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getErrorDetailsHistoricExternalTaskLog**](HistoricExternalTaskLogApi.md#getErrorDetailsHistoricExternalTaskLog) | **GET** /history/external-task-log/{id}/error-details | Get External Task Log Error Details
[**getHistoricExternalTaskLog**](HistoricExternalTaskLogApi.md#getHistoricExternalTaskLog) | **GET** /history/external-task-log/{id} | Get External Task Log
[**getHistoricExternalTaskLogs**](HistoricExternalTaskLogApi.md#getHistoricExternalTaskLogs) | **GET** /history/external-task-log | Get External Task Logs
[**getHistoricExternalTaskLogsCount**](HistoricExternalTaskLogApi.md#getHistoricExternalTaskLogsCount) | **GET** /history/external-task-log/count | Get External Task Log Count
[**queryHistoricExternalTaskLogs**](HistoricExternalTaskLogApi.md#queryHistoricExternalTaskLogs) | **POST** /history/external-task-log | Get External Task Logs (POST)
[**queryHistoricExternalTaskLogsCount**](HistoricExternalTaskLogApi.md#queryHistoricExternalTaskLogsCount) | **POST** /history/external-task-log/count | Get External Task Log Count (POST)



## getErrorDetailsHistoricExternalTaskLog

> Object getErrorDetailsHistoricExternalTaskLog(id)

Get External Task Log Error Details

Retrieves the corresponding error details of the passed historic external task log by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricExternalTaskLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricExternalTaskLogApi apiInstance = new HistoricExternalTaskLogApi(defaultClient);
        String id = "id_example"; // String | The id of the historic external task log to get the error details for.
        try {
            Object result = apiInstance.getErrorDetailsHistoricExternalTaskLog(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricExternalTaskLogApi#getErrorDetailsHistoricExternalTaskLog");
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
 **id** | **String**| The id of the historic external task log to get the error details for. |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Historic external task log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricExternalTaskLog

> HistoricExternalTaskLogDto getHistoricExternalTaskLog(id)

Get External Task Log

Retrieves a historic external task log by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricExternalTaskLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricExternalTaskLogApi apiInstance = new HistoricExternalTaskLogApi(defaultClient);
        String id = "id_example"; // String | The id of the log entry.
        try {
            HistoricExternalTaskLogDto result = apiInstance.getHistoricExternalTaskLog(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricExternalTaskLogApi#getHistoricExternalTaskLog");
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
 **id** | **String**| The id of the log entry. |

### Return type

[**HistoricExternalTaskLogDto**](HistoricExternalTaskLogDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Historic external task log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricExternalTaskLogs

> List&lt;HistoricExternalTaskLogDto&gt; getHistoricExternalTaskLogs(logId, externalTaskId, topicName, workerId, errorMessage, activityIdIn, activityInstanceIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, tenantIdIn, withoutTenantId, priorityLowerThanOrEquals, priorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog, sortBy, sortOrder, firstResult, maxResults)

Get External Task Logs

Queries for historic external task logs that fulfill the given parameters. The size of the result set can be retrieved by using the [Get External Task Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricExternalTaskLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricExternalTaskLogApi apiInstance = new HistoricExternalTaskLogApi(defaultClient);
        String logId = "logId_example"; // String | Filter by historic external task log id.
        String externalTaskId = "externalTaskId_example"; // String | Filter by external task id.
        String topicName = "topicName_example"; // String | Filter by an external task topic.
        String workerId = "workerId_example"; // String | Filter by the id of the worker that the task was most recently locked by.
        String errorMessage = "errorMessage_example"; // String | Filter by external task exception message.
        String activityIdIn = "activityIdIn_example"; // String | Only include historic external task logs which belong to one of the passed activity ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include historic external task logs which belong to one of the passed activity instance ids.
        String executionIdIn = "executionIdIn_example"; // String | Only include historic external task logs which belong to one of the passed execution ids.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by process definition key.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic external task log entries that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Long priorityLowerThanOrEquals = 56L; // Long | Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid `long` value.
        Long priorityHigherThanOrEquals = 56L; // Long | Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid `long` value.
        Boolean creationLog = true; // Boolean | Only include creation logs. Value may only be `true`, as `false` is the default behavior.
        Boolean failureLog = true; // Boolean | Only include failure logs. Value may only be `true`, as `false` is the default behavior.
        Boolean successLog = true; // Boolean | Only include success logs. Value may only be `true`, as `false` is the default behavior.
        Boolean deletionLog = true; // Boolean | Only include deletion logs. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<HistoricExternalTaskLogDto> result = apiInstance.getHistoricExternalTaskLogs(logId, externalTaskId, topicName, workerId, errorMessage, activityIdIn, activityInstanceIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, tenantIdIn, withoutTenantId, priorityLowerThanOrEquals, priorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricExternalTaskLogApi#getHistoricExternalTaskLogs");
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
 **logId** | **String**| Filter by historic external task log id. | [optional]
 **externalTaskId** | **String**| Filter by external task id. | [optional]
 **topicName** | **String**| Filter by an external task topic. | [optional]
 **workerId** | **String**| Filter by the id of the worker that the task was most recently locked by. | [optional]
 **errorMessage** | **String**| Filter by external task exception message. | [optional]
 **activityIdIn** | **String**| Only include historic external task logs which belong to one of the passed activity ids. | [optional]
 **activityInstanceIdIn** | **String**| Only include historic external task logs which belong to one of the passed activity instance ids. | [optional]
 **executionIdIn** | **String**| Only include historic external task logs which belong to one of the passed execution ids. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionKey** | **String**| Filter by process definition key. | [optional]
 **tenantIdIn** | **String**| Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic external task log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **priorityLowerThanOrEquals** | **Long**| Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **priorityHigherThanOrEquals** | **Long**| Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **creationLog** | **Boolean**| Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **failureLog** | **Boolean**| Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **successLog** | **Boolean**| Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **deletionLog** | **Boolean**| Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: timestamp, externalTaskId, topicName, workerId, retries, priority, activityId, activityInstanceId, executionId, processInstanceId, processDefinitionId, processDefinitionKey, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;HistoricExternalTaskLogDto&gt;**](HistoricExternalTaskLogDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricExternalTaskLogsCount

> CountResultDto getHistoricExternalTaskLogsCount(logId, externalTaskId, topicName, workerId, errorMessage, activityIdIn, activityInstanceIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, tenantIdIn, withoutTenantId, priorityLowerThanOrEquals, priorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog)

Get External Task Log Count

Queries for the number of historic external task logs that fulfill the given parameters. Takes the same parameters as the [Get External Task Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricExternalTaskLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricExternalTaskLogApi apiInstance = new HistoricExternalTaskLogApi(defaultClient);
        String logId = "logId_example"; // String | Filter by historic external task log id.
        String externalTaskId = "externalTaskId_example"; // String | Filter by external task id.
        String topicName = "topicName_example"; // String | Filter by an external task topic.
        String workerId = "workerId_example"; // String | Filter by the id of the worker that the task was most recently locked by.
        String errorMessage = "errorMessage_example"; // String | Filter by external task exception message.
        String activityIdIn = "activityIdIn_example"; // String | Only include historic external task logs which belong to one of the passed activity ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include historic external task logs which belong to one of the passed activity instance ids.
        String executionIdIn = "executionIdIn_example"; // String | Only include historic external task logs which belong to one of the passed execution ids.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by process definition key.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic external task log entries that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Long priorityLowerThanOrEquals = 56L; // Long | Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid `long` value.
        Long priorityHigherThanOrEquals = 56L; // Long | Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid `long` value.
        Boolean creationLog = true; // Boolean | Only include creation logs. Value may only be `true`, as `false` is the default behavior.
        Boolean failureLog = true; // Boolean | Only include failure logs. Value may only be `true`, as `false` is the default behavior.
        Boolean successLog = true; // Boolean | Only include success logs. Value may only be `true`, as `false` is the default behavior.
        Boolean deletionLog = true; // Boolean | Only include deletion logs. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getHistoricExternalTaskLogsCount(logId, externalTaskId, topicName, workerId, errorMessage, activityIdIn, activityInstanceIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, tenantIdIn, withoutTenantId, priorityLowerThanOrEquals, priorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricExternalTaskLogApi#getHistoricExternalTaskLogsCount");
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
 **logId** | **String**| Filter by historic external task log id. | [optional]
 **externalTaskId** | **String**| Filter by external task id. | [optional]
 **topicName** | **String**| Filter by an external task topic. | [optional]
 **workerId** | **String**| Filter by the id of the worker that the task was most recently locked by. | [optional]
 **errorMessage** | **String**| Filter by external task exception message. | [optional]
 **activityIdIn** | **String**| Only include historic external task logs which belong to one of the passed activity ids. | [optional]
 **activityInstanceIdIn** | **String**| Only include historic external task logs which belong to one of the passed activity instance ids. | [optional]
 **executionIdIn** | **String**| Only include historic external task logs which belong to one of the passed execution ids. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionKey** | **String**| Filter by process definition key. | [optional]
 **tenantIdIn** | **String**| Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic external task log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **priorityLowerThanOrEquals** | **Long**| Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **priorityHigherThanOrEquals** | **Long**| Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **creationLog** | **Boolean**| Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **failureLog** | **Boolean**| Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **successLog** | **Boolean**| Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **deletionLog** | **Boolean**| Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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
| **400** | Returned if some of the query parameters are invalid. |  -  |


## queryHistoricExternalTaskLogs

> List&lt;HistoricExternalTaskLogDto&gt; queryHistoricExternalTaskLogs(historicExternalTaskLogQueryDto)

Get External Task Logs (POST)

Queries for historic external task logs that fulfill the given parameters. This method is slightly more powerful than the [Get External Task Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query/) method because it allows filtering by historic external task logs values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricExternalTaskLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricExternalTaskLogApi apiInstance = new HistoricExternalTaskLogApi(defaultClient);
        HistoricExternalTaskLogQueryDto historicExternalTaskLogQueryDto = new HistoricExternalTaskLogQueryDto(); // HistoricExternalTaskLogQueryDto | 
        try {
            List<HistoricExternalTaskLogDto> result = apiInstance.queryHistoricExternalTaskLogs(historicExternalTaskLogQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricExternalTaskLogApi#queryHistoricExternalTaskLogs");
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
 **historicExternalTaskLogQueryDto** | [**HistoricExternalTaskLogQueryDto**](HistoricExternalTaskLogQueryDto.md)|  | [optional]

### Return type

[**List&lt;HistoricExternalTaskLogDto&gt;**](HistoricExternalTaskLogDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryHistoricExternalTaskLogsCount

> CountResultDto queryHistoricExternalTaskLogsCount(historicExternalTaskLogQueryDto)

Get External Task Log Count (POST)

Queries for the number of historic external task logs that fulfill the given parameters. This method takes the same message body as the [Get External Task Logs (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/post-external-task-log-query/) method and therefore it is slightly more powerful than the [Get External Task Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/external-task-log/get-external-task-log-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricExternalTaskLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricExternalTaskLogApi apiInstance = new HistoricExternalTaskLogApi(defaultClient);
        HistoricExternalTaskLogQueryDto historicExternalTaskLogQueryDto = new HistoricExternalTaskLogQueryDto(); // HistoricExternalTaskLogQueryDto | 
        try {
            CountResultDto result = apiInstance.queryHistoricExternalTaskLogsCount(historicExternalTaskLogQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricExternalTaskLogApi#queryHistoricExternalTaskLogsCount");
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
 **historicExternalTaskLogQueryDto** | [**HistoricExternalTaskLogQueryDto**](HistoricExternalTaskLogQueryDto.md)|  | [optional]

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
| **400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

