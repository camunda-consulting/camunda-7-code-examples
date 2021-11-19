# HistoricJobLogApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHistoricJobLog**](HistoricJobLogApi.md#getHistoricJobLog) | **GET** /history/job-log/{id} | Get Job Log
[**getHistoricJobLogs**](HistoricJobLogApi.md#getHistoricJobLogs) | **GET** /history/job-log | Get Job Logs
[**getHistoricJobLogsCount**](HistoricJobLogApi.md#getHistoricJobLogsCount) | **GET** /history/job-log/count | Get Job Log Count
[**getStacktraceHistoricJobLog**](HistoricJobLogApi.md#getStacktraceHistoricJobLog) | **GET** /history/job-log/{id}/stacktrace | Get Job Log Exception Stacktrace
[**queryHistoricJobLogs**](HistoricJobLogApi.md#queryHistoricJobLogs) | **POST** /history/job-log | Get Job Logs (POST)
[**queryHistoricJobLogsCount**](HistoricJobLogApi.md#queryHistoricJobLogsCount) | **POST** /history/job-log/count | Get Job Log Count (POST)



## getHistoricJobLog

> HistoricJobLogDto getHistoricJobLog(id)

Get Job Log

Retrieves a historic job log by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricJobLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricJobLogApi apiInstance = new HistoricJobLogApi(defaultClient);
        String id = "id_example"; // String | The id of the log entry.
        try {
            HistoricJobLogDto result = apiInstance.getHistoricJobLog(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricJobLogApi#getHistoricJobLog");
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

[**HistoricJobLogDto**](HistoricJobLogDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Historic job log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricJobLogs

> List&lt;HistoricJobLogDto&gt; getHistoricJobLogs(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog, sortBy, sortOrder, firstResult, maxResults)

Get Job Logs

Queries for historic job logs that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricJobLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricJobLogApi apiInstance = new HistoricJobLogApi(defaultClient);
        String logId = "logId_example"; // String | Filter by historic job log id.
        String jobId = "jobId_example"; // String | Filter by job id.
        String jobExceptionMessage = "jobExceptionMessage_example"; // String | Filter by job exception message.
        String jobDefinitionId = "jobDefinitionId_example"; // String | Filter by job definition id.
        String jobDefinitionType = "jobDefinitionType_example"; // String | Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types.
        String jobDefinitionConfiguration = "jobDefinitionConfiguration_example"; // String | Filter by job definition configuration.
        String activityIdIn = "activityIdIn_example"; // String | Only include historic job logs which belong to one of the passed activity ids.
        String failedActivityIdIn = "failedActivityIdIn_example"; // String | Only include historic job logs which belong to failures of one of the passed activity ids.
        String executionIdIn = "executionIdIn_example"; // String | Only include historic job logs which belong to one of the passed execution ids.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by process definition key.
        String deploymentId = "deploymentId_example"; // String | Filter by deployment id.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include historic job log entries which belong to one of the passed and comma- separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic job log entries that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String hostname = "hostname_example"; // String | Filter by hostname.
        Long jobPriorityLowerThanOrEquals = 56L; // Long | Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid `long` value.
        Long jobPriorityHigherThanOrEquals = 56L; // Long | Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid `long` value.
        Boolean creationLog = true; // Boolean | Only include creation logs. Value may only be `true`, as `false` is the default behavior.
        Boolean failureLog = true; // Boolean | Only include failure logs. Value may only be `true`, as `false` is the default behavior.
        Boolean successLog = true; // Boolean | Only include success logs. Value may only be `true`, as `false` is the default behavior.
        Boolean deletionLog = true; // Boolean | Only include deletion logs. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<HistoricJobLogDto> result = apiInstance.getHistoricJobLogs(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricJobLogApi#getHistoricJobLogs");
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
 **logId** | **String**| Filter by historic job log id. | [optional]
 **jobId** | **String**| Filter by job id. | [optional]
 **jobExceptionMessage** | **String**| Filter by job exception message. | [optional]
 **jobDefinitionId** | **String**| Filter by job definition id. | [optional]
 **jobDefinitionType** | **String**| Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. | [optional]
 **jobDefinitionConfiguration** | **String**| Filter by job definition configuration. | [optional]
 **activityIdIn** | **String**| Only include historic job logs which belong to one of the passed activity ids. | [optional]
 **failedActivityIdIn** | **String**| Only include historic job logs which belong to failures of one of the passed activity ids. | [optional]
 **executionIdIn** | **String**| Only include historic job logs which belong to one of the passed execution ids. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionKey** | **String**| Filter by process definition key. | [optional]
 **deploymentId** | **String**| Filter by deployment id. | [optional]
 **tenantIdIn** | **String**| Only include historic job log entries which belong to one of the passed and comma- separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic job log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **hostname** | **String**| Filter by hostname. | [optional]
 **jobPriorityLowerThanOrEquals** | **Long**| Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **jobPriorityHigherThanOrEquals** | **Long**| Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **creationLog** | **Boolean**| Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **failureLog** | **Boolean**| Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **successLog** | **Boolean**| Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **deletionLog** | **Boolean**| Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: timestamp, jobId, jobDefinitionId, jobDueDate, jobRetries, jobPriority, activityId, executionId, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, hostname, occurrence, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;HistoricJobLogDto&gt;**](HistoricJobLogDto.md)

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


## getHistoricJobLogsCount

> CountResultDto getHistoricJobLogsCount(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog)

Get Job Log Count

Queries for the number of historic job logs that fulfill the given parameters. Takes the same parameters as the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricJobLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricJobLogApi apiInstance = new HistoricJobLogApi(defaultClient);
        String logId = "logId_example"; // String | Filter by historic job log id.
        String jobId = "jobId_example"; // String | Filter by job id.
        String jobExceptionMessage = "jobExceptionMessage_example"; // String | Filter by job exception message.
        String jobDefinitionId = "jobDefinitionId_example"; // String | Filter by job definition id.
        String jobDefinitionType = "jobDefinitionType_example"; // String | Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types.
        String jobDefinitionConfiguration = "jobDefinitionConfiguration_example"; // String | Filter by job definition configuration.
        String activityIdIn = "activityIdIn_example"; // String | Only include historic job logs which belong to one of the passed activity ids.
        String failedActivityIdIn = "failedActivityIdIn_example"; // String | Only include historic job logs which belong to failures of one of the passed activity ids.
        String executionIdIn = "executionIdIn_example"; // String | Only include historic job logs which belong to one of the passed execution ids.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by process definition key.
        String deploymentId = "deploymentId_example"; // String | Filter by deployment id.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include historic job log entries which belong to one of the passed and comma- separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic job log entries that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String hostname = "hostname_example"; // String | Filter by hostname.
        Long jobPriorityLowerThanOrEquals = 56L; // Long | Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid `long` value.
        Long jobPriorityHigherThanOrEquals = 56L; // Long | Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid `long` value.
        Boolean creationLog = true; // Boolean | Only include creation logs. Value may only be `true`, as `false` is the default behavior.
        Boolean failureLog = true; // Boolean | Only include failure logs. Value may only be `true`, as `false` is the default behavior.
        Boolean successLog = true; // Boolean | Only include success logs. Value may only be `true`, as `false` is the default behavior.
        Boolean deletionLog = true; // Boolean | Only include deletion logs. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getHistoricJobLogsCount(logId, jobId, jobExceptionMessage, jobDefinitionId, jobDefinitionType, jobDefinitionConfiguration, activityIdIn, failedActivityIdIn, executionIdIn, processInstanceId, processDefinitionId, processDefinitionKey, deploymentId, tenantIdIn, withoutTenantId, hostname, jobPriorityLowerThanOrEquals, jobPriorityHigherThanOrEquals, creationLog, failureLog, successLog, deletionLog);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricJobLogApi#getHistoricJobLogsCount");
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
 **logId** | **String**| Filter by historic job log id. | [optional]
 **jobId** | **String**| Filter by job id. | [optional]
 **jobExceptionMessage** | **String**| Filter by job exception message. | [optional]
 **jobDefinitionId** | **String**| Filter by job definition id. | [optional]
 **jobDefinitionType** | **String**| Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. | [optional]
 **jobDefinitionConfiguration** | **String**| Filter by job definition configuration. | [optional]
 **activityIdIn** | **String**| Only include historic job logs which belong to one of the passed activity ids. | [optional]
 **failedActivityIdIn** | **String**| Only include historic job logs which belong to failures of one of the passed activity ids. | [optional]
 **executionIdIn** | **String**| Only include historic job logs which belong to one of the passed execution ids. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionKey** | **String**| Filter by process definition key. | [optional]
 **deploymentId** | **String**| Filter by deployment id. | [optional]
 **tenantIdIn** | **String**| Only include historic job log entries which belong to one of the passed and comma- separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic job log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **hostname** | **String**| Filter by hostname. | [optional]
 **jobPriorityLowerThanOrEquals** | **Long**| Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **jobPriorityHigherThanOrEquals** | **Long**| Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
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


## getStacktraceHistoricJobLog

> Object getStacktraceHistoricJobLog(id)

Get Job Log Exception Stacktrace

Retrieves the corresponding exception stacktrace to the passed historic job log by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricJobLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricJobLogApi apiInstance = new HistoricJobLogApi(defaultClient);
        String id = "id_example"; // String | The id of the historic job log to get the exception stacktrace for.
        try {
            Object result = apiInstance.getStacktraceHistoricJobLog(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricJobLogApi#getStacktraceHistoricJobLog");
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
 **id** | **String**| The id of the historic job log to get the exception stacktrace for. |

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
| **404** | Historic job log with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryHistoricJobLogs

> List&lt;HistoricJobLogDto&gt; queryHistoricJobLogs(firstResult, maxResults, historicJobLogQueryDto)

Get Job Logs (POST)

Queries for historic job logs that fulfill the given parameters. This method is slightly more powerful than the [Get Job Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query/) method because it allows filtering by historic job logs values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricJobLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricJobLogApi apiInstance = new HistoricJobLogApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        HistoricJobLogQueryDto historicJobLogQueryDto = new HistoricJobLogQueryDto(); // HistoricJobLogQueryDto | 
        try {
            List<HistoricJobLogDto> result = apiInstance.queryHistoricJobLogs(firstResult, maxResults, historicJobLogQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricJobLogApi#queryHistoricJobLogs");
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
 **historicJobLogQueryDto** | [**HistoricJobLogQueryDto**](HistoricJobLogQueryDto.md)|  | [optional]

### Return type

[**List&lt;HistoricJobLogDto&gt;**](HistoricJobLogDto.md)

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


## queryHistoricJobLogsCount

> CountResultDto queryHistoricJobLogsCount(historicJobLogQueryDto)

Get Job Log Count (POST)

Queries for the number of historic job logs that fulfill the given parameters. This method takes the same message body as the [Get Job Logs (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/post-job-log-query/) method and therefore it is slightly more powerful than the [Get Job Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/job-log/get-job-log-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricJobLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricJobLogApi apiInstance = new HistoricJobLogApi(defaultClient);
        HistoricJobLogQueryDto historicJobLogQueryDto = new HistoricJobLogQueryDto(); // HistoricJobLogQueryDto | 
        try {
            CountResultDto result = apiInstance.queryHistoricJobLogsCount(historicJobLogQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricJobLogApi#queryHistoricJobLogsCount");
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
 **historicJobLogQueryDto** | [**HistoricJobLogQueryDto**](HistoricJobLogQueryDto.md)|  | [optional]

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

