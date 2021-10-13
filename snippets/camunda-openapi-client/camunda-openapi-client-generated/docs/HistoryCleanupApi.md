# HistoryCleanupApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cleanupAsync**](HistoryCleanupApi.md#cleanupAsync) | **POST** /history/cleanup | Clean up history (POST)
[**findCleanupJob**](HistoryCleanupApi.md#findCleanupJob) | **GET** /history/cleanup/job | Find clean up history job (GET)
[**findCleanupJobs**](HistoryCleanupApi.md#findCleanupJobs) | **GET** /history/cleanup/jobs | Find clean up history jobs (GET)
[**getHistoryCleanupConfiguration**](HistoryCleanupApi.md#getHistoryCleanupConfiguration) | **GET** /history/cleanup/configuration | Get History Cleanup Configuration



## cleanupAsync

> JobDto cleanupAsync(immediatelyDue)

Clean up history (POST)

Schedules asynchronous history cleanup (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).  **Note:** This endpoint will return at most a single history cleanup job. Since version &#x60;7.9.0&#x60; it is possible to configure multiple [parallel history cleanup jobs](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#parallel-execution). Use [&#x60;GET /history/cleanup/jobs&#x60;](https://docs.camunda.org/manual/7.16/reference/rest/history/history-cleanup/get-history-cleanup-jobs) to find all the available history cleanup jobs.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoryCleanupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoryCleanupApi apiInstance = new HistoryCleanupApi(defaultClient);
        Boolean immediatelyDue = true; // Boolean | When true the job will be scheduled for nearest future. When `false`, the job will be scheduled for next batch window start time. Default is `true`.
        try {
            JobDto result = apiInstance.cleanupAsync(immediatelyDue);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoryCleanupApi#cleanupAsync");
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
 **immediatelyDue** | **Boolean**| When true the job will be scheduled for nearest future. When &#x60;false&#x60;, the job will be scheduled for next batch window start time. Default is &#x60;true&#x60;. | [optional]

### Return type

[**JobDto**](JobDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid or the engine does not participate in history cleanup. See [Cleanup Execution Participation per Node](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#cleanup-execution-participation-per-node). |  -  |


## findCleanupJob

> JobDto findCleanupJob()

Find clean up history job (GET)

**Deprecated!** Use &#x60;GET /history/cleanup/jobs&#x60; instead.  Finds history cleanup job (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoryCleanupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoryCleanupApi apiInstance = new HistoryCleanupApi(defaultClient);
        try {
            JobDto result = apiInstance.findCleanupJob();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoryCleanupApi#findCleanupJob");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**JobDto**](JobDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | History clean up job does not exist. |  -  |


## findCleanupJobs

> List&lt;JobDto&gt; findCleanupJobs()

Find clean up history jobs (GET)

Finds history cleanup jobs (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoryCleanupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoryCleanupApi apiInstance = new HistoryCleanupApi(defaultClient);
        try {
            List<JobDto> result = apiInstance.findCleanupJobs();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoryCleanupApi#findCleanupJobs");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;JobDto&gt;**](JobDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | History clean up jobs are empty. |  -  |


## getHistoryCleanupConfiguration

> HistoryCleanupConfigurationDto getHistoryCleanupConfiguration()

Get History Cleanup Configuration

Retrieves history cleanup batch window configuration (See [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoryCleanupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoryCleanupApi apiInstance = new HistoryCleanupApi(defaultClient);
        try {
            HistoryCleanupConfigurationDto result = apiInstance.getHistoryCleanupConfiguration();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoryCleanupApi#getHistoryCleanupConfiguration");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**HistoryCleanupConfigurationDto**](HistoryCleanupConfigurationDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |

