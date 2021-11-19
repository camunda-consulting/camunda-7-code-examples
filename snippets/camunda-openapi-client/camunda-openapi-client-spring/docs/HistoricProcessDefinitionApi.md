# HistoricProcessDefinitionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCleanableHistoricProcessInstanceReport**](HistoricProcessDefinitionApi.md#getCleanableHistoricProcessInstanceReport) | **GET** /history/process-definition/cleanable-process-instance-report | Get Cleanable Process Instance Report
[**getCleanableHistoricProcessInstanceReportCount**](HistoricProcessDefinitionApi.md#getCleanableHistoricProcessInstanceReportCount) | **GET** /history/process-definition/cleanable-process-instance-report/count | Get Cleanable Process Instance Report Count
[**getHistoricActivityStatistics**](HistoricProcessDefinitionApi.md#getHistoricActivityStatistics) | **GET** /history/process-definition/{id}/statistics | Get Historic Activity Statistics



## getCleanableHistoricProcessInstanceReport

> List&lt;CleanableHistoricProcessInstanceReportResultDto&gt; getCleanableHistoricProcessInstanceReport(processDefinitionIdIn, processDefinitionKeyIn, tenantIdIn, withoutTenantId, compact, sortBy, sortOrder, firstResult, maxResults)

Get Cleanable Process Instance Report

Retrieves a report about a process definition and finished process instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup))  so that you can tune the history time to live. These reports include the count of the finished historic process instances, cleanable process instances and basic process definition data - id, key, name and version. The size of the result set can be retrieved by using the [Get Cleanable Process Instance Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/process-definition/get-cleanable-process-instance-report-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessDefinitionApi apiInstance = new HistoricProcessDefinitionApi(defaultClient);
        String processDefinitionIdIn = "processDefinitionIdIn_example"; // String | Filter by process definition ids. Must be a comma-separated list of process definition ids.
        String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Filter by process definition keys. Must be a comma-separated list of process definition keys.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include process definitions which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean compact = true; // Boolean | Only include process instances which have more than zero finished instances. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<CleanableHistoricProcessInstanceReportResultDto> result = apiInstance.getCleanableHistoricProcessInstanceReport(processDefinitionIdIn, processDefinitionKeyIn, tenantIdIn, withoutTenantId, compact, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessDefinitionApi#getCleanableHistoricProcessInstanceReport");
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
 **processDefinitionIdIn** | **String**| Filter by process definition ids. Must be a comma-separated list of process definition ids. | [optional]
 **processDefinitionKeyIn** | **String**| Filter by process definition keys. Must be a comma-separated list of process definition keys. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include process definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **compact** | **Boolean**| Only include process instances which have more than zero finished instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: finished]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;CleanableHistoricProcessInstanceReportResultDto&gt;**](CleanableHistoricProcessInstanceReportResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **500** | See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getCleanableHistoricProcessInstanceReportCount

> CountResultDto getCleanableHistoricProcessInstanceReportCount(processDefinitionIdIn, processDefinitionKeyIn, tenantIdIn, withoutTenantId, compact)

Get Cleanable Process Instance Report Count

Queries for the number of report results about a process definition and finished process instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)). Takes the same parameters as the [Get Cleanable Process Instance Report](https://docs.camunda.org/manual/7.16/reference/rest/history/process-definition/get-cleanable-process-instance-report/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessDefinitionApi apiInstance = new HistoricProcessDefinitionApi(defaultClient);
        String processDefinitionIdIn = "processDefinitionIdIn_example"; // String | Filter by process definition ids. Must be a comma-separated list of process definition ids.
        String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Filter by process definition keys. Must be a comma-separated list of process definition keys.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include process definitions which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean compact = true; // Boolean | Only include process instances which have more than zero finished instances. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getCleanableHistoricProcessInstanceReportCount(processDefinitionIdIn, processDefinitionKeyIn, tenantIdIn, withoutTenantId, compact);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessDefinitionApi#getCleanableHistoricProcessInstanceReportCount");
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
 **processDefinitionIdIn** | **String**| Filter by process definition ids. Must be a comma-separated list of process definition ids. | [optional]
 **processDefinitionKeyIn** | **String**| Filter by process definition keys. Must be a comma-separated list of process definition keys. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A process definition must have one of the given  tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include process definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **compact** | **Boolean**| Only include process instances which have more than zero finished instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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
| **500** | See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricActivityStatistics

> List&lt;HistoricActivityStatisticsDto&gt; getHistoricActivityStatistics(id, canceled, finished, completeScope, incidents, startedBefore, startedAfter, finishedBefore, finishedAfter, processInstanceIdIn, sortBy, sortOrder)

Get Historic Activity Statistics

Retrieves historic statistics of a given process definition, grouped by activities. These statistics include the number of running activity instances and, optionally, the number of canceled activity instances, finished activity instances and activity instances which completed a scope (i.e., in BPMN 2.0 manner: a scope is completed by an activity instance when the activity instance consumed a token but did not emit a new token). **Note:** This only includes historic data.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricProcessDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricProcessDefinitionApi apiInstance = new HistoricProcessDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the process definition.
        Boolean canceled = true; // Boolean | Whether to include the number of canceled activity instances in the result or not. Valid values are `true` or `false`. Default: `false`.
        Boolean finished = true; // Boolean | Whether to include the number of finished activity instances in the result or not. Valid values are `true` or `false`. Default: `false`.
        Boolean completeScope = true; // Boolean | Whether to include the number of activity instances which completed a scope in the result or not. Valid values are `true` or `false`. Default: `false`.
        Boolean incidents = true; // Boolean | Whether to include the number of incidents. Valid values are `true` or `false`. Default: `false`.
        OffsetDateTime startedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to process instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`,  e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime startedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to process instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`,  e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to process instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`,  e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime finishedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to process instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`,  e.g., `2013-01-23T14:42:45.000+0200`.
        String processInstanceIdIn = "processInstanceIdIn_example"; // String | Restrict to process instances with the given IDs. The IDs must be provided as a comma- separated list.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        try {
            List<HistoricActivityStatisticsDto> result = apiInstance.getHistoricActivityStatistics(id, canceled, finished, completeScope, incidents, startedBefore, startedAfter, finishedBefore, finishedAfter, processInstanceIdIn, sortBy, sortOrder);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricProcessDefinitionApi#getHistoricActivityStatistics");
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
 **id** | **String**| The id of the process definition. |
 **canceled** | **Boolean**| Whether to include the number of canceled activity instances in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. | [optional]
 **finished** | **Boolean**| Whether to include the number of finished activity instances in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. | [optional]
 **completeScope** | **Boolean**| Whether to include the number of activity instances which completed a scope in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. | [optional]
 **incidents** | **Boolean**| Whether to include the number of incidents. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. Default: &#x60;false&#x60;. | [optional]
 **startedBefore** | **OffsetDateTime**| Restrict to process instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **startedAfter** | **OffsetDateTime**| Restrict to process instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedBefore** | **OffsetDateTime**| Restrict to process instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **finishedAfter** | **OffsetDateTime**| Restrict to process instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/),  the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;,  e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **processInstanceIdIn** | **String**| Restrict to process instances with the given IDs. The IDs must be provided as a comma- separated list. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: activityId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]

### Return type

[**List&lt;HistoricActivityStatisticsDto&gt;**](HistoricActivityStatisticsDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

