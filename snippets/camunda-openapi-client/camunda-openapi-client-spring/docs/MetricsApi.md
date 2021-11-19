# MetricsApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteTaskMetrics**](MetricsApi.md#deleteTaskMetrics) | **DELETE** /metrics/task-worker | Delete Task Worker Metrics
[**getMetrics**](MetricsApi.md#getMetrics) | **GET** /metrics/{metrics-name}/sum | Get Sum
[**interval**](MetricsApi.md#interval) | **GET** /metrics | Get Metrics in Interval



## deleteTaskMetrics

> deleteTaskMetrics(date)

Delete Task Worker Metrics

Deletes all task worker metrics prior to the given date or all if no date is provided.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.MetricsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        MetricsApi apiInstance = new MetricsApi(defaultClient);
        OffsetDateTime date = OffsetDateTime.now(); // OffsetDateTime | The date prior to which all task worker metrics should be deleted.
        try {
            apiInstance.deleteTaskMetrics(date);
        } catch (ApiException e) {
            System.err.println("Exception when calling MetricsApi#deleteTaskMetrics");
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
 **date** | **OffsetDateTime**| The date prior to which all task worker metrics should be deleted. | [optional]

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
| **403** | If the user who performs the operation is not a &lt;b&gt;camunda-admin&lt;/b&gt; user. |  -  |


## getMetrics

> MetricsResultDto getMetrics(metricsName, startDate, endDate)

Get Sum

Retrieves the &#x60;sum&#x60; (count) for a given metric.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.MetricsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        MetricsApi apiInstance = new MetricsApi(defaultClient);
        String metricsName = "metricsName_example"; // String | The name of the metric.
        OffsetDateTime startDate = OffsetDateTime.now(); // OffsetDateTime | The start date (inclusive).
        OffsetDateTime endDate = OffsetDateTime.now(); // OffsetDateTime | The end date (exclusive).
        try {
            MetricsResultDto result = apiInstance.getMetrics(metricsName, startDate, endDate);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MetricsApi#getMetrics");
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
 **metricsName** | **String**| The name of the metric. | [enum: activity-instance-start, activity-instance-end, job-acquisition-attempt, job-acquired-success, job-acquired-failure, job-execution-rejected, job-successful, job-failed, job-locked-exclusive, executed-decision-elements, history-cleanup-removed-process-instances, history-cleanup-removed-case-instances, history-cleanup-removed-decision-instances, history-cleanup-removed-batch-operations, history-cleanup-removed-task-metrics, unique-task-workers]
 **startDate** | **OffsetDateTime**| The start date (inclusive). | [optional]
 **endDate** | **OffsetDateTime**| The end date (exclusive). | [optional]

### Return type

[**MetricsResultDto**](MetricsResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |


## interval

> List&lt;MetricsIntervalResultDto&gt; interval(name, reporter, startDate, endDate, firstResult, maxResults, interval, aggregateByReporter)

Get Metrics in Interval

Retrieves a list of metrics, aggregated for a given interval.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.MetricsApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        MetricsApi apiInstance = new MetricsApi(defaultClient);
        String name = "name_example"; // String | The name of the metric.
        String reporter = "reporter_example"; // String | The name of the reporter (host), on which the metrics was logged. This will have value provided by the [hostname configuration property](https://docs.camunda.org/manual/7.16/reference/deployment-descriptors/tags/process-engine/#hostname).
        OffsetDateTime startDate = OffsetDateTime.now(); // OffsetDateTime | The start date (inclusive).
        OffsetDateTime endDate = OffsetDateTime.now(); // OffsetDateTime | The end date (exclusive).
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String interval = "900"; // String | The interval for which the metrics should be aggregated. Time unit is seconds. Default: The interval is set to 15 minutes (900 seconds).
        String aggregateByReporter = "aggregateByReporter_example"; // String | Aggregate metrics by reporter.
        try {
            List<MetricsIntervalResultDto> result = apiInstance.interval(name, reporter, startDate, endDate, firstResult, maxResults, interval, aggregateByReporter);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MetricsApi#interval");
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
 **name** | **String**| The name of the metric. | [optional] [enum: activity-instance-start, activity-instance-end, job-acquisition-attempt, job-acquired-success, job-acquired-failure, job-execution-rejected, job-successful, job-failed, job-locked-exclusive, executed-decision-elements, history-cleanup-removed-process-instances, history-cleanup-removed-case-instances, history-cleanup-removed-decision-instances, history-cleanup-removed-batch-operations, history-cleanup-removed-task-metrics]
 **reporter** | **String**| The name of the reporter (host), on which the metrics was logged. This will have value provided by the [hostname configuration property](https://docs.camunda.org/manual/7.16/reference/deployment-descriptors/tags/process-engine/#hostname). | [optional]
 **startDate** | **OffsetDateTime**| The start date (inclusive). | [optional]
 **endDate** | **OffsetDateTime**| The end date (exclusive). | [optional]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **interval** | **String**| The interval for which the metrics should be aggregated. Time unit is seconds. Default: The interval is set to 15 minutes (900 seconds). | [optional] [default to 900]
 **aggregateByReporter** | **String**| Aggregate metrics by reporter. | [optional]

### Return type

[**List&lt;MetricsIntervalResultDto&gt;**](MetricsIntervalResultDto.md)

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

