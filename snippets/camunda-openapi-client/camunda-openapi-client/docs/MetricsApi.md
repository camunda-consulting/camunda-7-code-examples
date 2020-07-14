# MetricsApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getMetrics**](MetricsApi.md#getMetrics) | **GET** /metrics/{metrics-name}/sum | 
[**interval**](MetricsApi.md#interval) | **GET** /metrics | 


<a name="getMetrics"></a>
# **getMetrics**
> MetricsResultDto getMetrics(metricsName, startDate, endDate)



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
    OffsetDateTime startDate = new OffsetDateTime(); // OffsetDateTime | The start date (inclusive).
    OffsetDateTime endDate = new OffsetDateTime(); // OffsetDateTime | The end date (exclusive).
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
 **metricsName** | **String**| The name of the metric. | [enum: activity-instance-start, activity-instance-end, job-acquisition-attempt, job-acquired-success, job-acquired-failure, job-execution-rejected, job-successful, job-failed, job-locked-exclusive, executed-decision-elements, history-cleanup-removed-process-instances, history-cleanup-removed-case-instances, history-cleanup-removed-decision-instances, history-cleanup-removed-batch-operations]
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
**200** | Request successful. |  -  |

<a name="interval"></a>
# **interval**
> List&lt;MetricsIntervalResultDto&gt; interval(name, reporter, startDate, endDate, firstResult, maxResults, interval, aggregateByReporter)



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
    String reporter = "reporter_example"; // String | The name of the reporter (host), on which the metrics was logged. This will have value provided by the [hostname configuration property](https://docs.camunda.org/manual/7.13/reference/deployment-descriptors/tags/process-engine/#hostname).
    OffsetDateTime startDate = new OffsetDateTime(); // OffsetDateTime | The start date (inclusive).
    OffsetDateTime endDate = new OffsetDateTime(); // OffsetDateTime | The end date (exclusive).
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    String interval = "\"900\""; // String | The interval for which the metrics should be aggregated. Time unit is seconds. Default: The interval is set to 15 minutes (900 seconds).
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
 **name** | **String**| The name of the metric. | [optional] [enum: activity-instance-start, activity-instance-end, job-acquisition-attempt, job-acquired-success, job-acquired-failure, job-execution-rejected, job-successful, job-failed, job-locked-exclusive, executed-decision-elements, history-cleanup-removed-process-instances, history-cleanup-removed-case-instances, history-cleanup-removed-decision-instances, history-cleanup-removed-batch-operations]
 **reporter** | **String**| The name of the reporter (host), on which the metrics was logged. This will have value provided by the [hostname configuration property](https://docs.camunda.org/manual/7.13/reference/deployment-descriptors/tags/process-engine/#hostname). | [optional]
 **startDate** | **OffsetDateTime**| The start date (inclusive). | [optional]
 **endDate** | **OffsetDateTime**| The end date (exclusive). | [optional]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **interval** | **String**| The interval for which the metrics should be aggregated. Time unit is seconds. Default: The interval is set to 15 minutes (900 seconds). | [optional] [default to &quot;900&quot;]
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
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. |  -  |

