# HistoricDecisionDefinitionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getCleanableHistoricDecisionInstanceReport**](HistoricDecisionDefinitionApi.md#getCleanableHistoricDecisionInstanceReport) | **GET** /history/decision-definition/cleanable-decision-instance-report | Get Cleanable Decision Instance Report
[**getCleanableHistoricDecisionInstanceReportCount**](HistoricDecisionDefinitionApi.md#getCleanableHistoricDecisionInstanceReportCount) | **GET** /history/decision-definition/cleanable-decision-instance-report/count | Get Cleanable Decision Instance Report Count



## getCleanableHistoricDecisionInstanceReport

> List&lt;CleanableHistoricDecisionInstanceReportResultDto&gt; getCleanableHistoricDecisionInstanceReport(decisionDefinitionIdIn, decisionDefinitionKeyIn, tenantIdIn, withoutTenantId, compact, sortBy, sortOrder, firstResult, maxResults)

Get Cleanable Decision Instance Report

Retrieves a report about a decision definition and finished decision instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)),  so that you can tune the history time to live. These reports include the count of the finished historic decision instances, cleanable decision instances and basic decision definition data - id, key, name and version. The size of the result set can be retrieved by using the  [Get Cleanable Decision Instance Report Count](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-definition/get-cleanable-decision-instance-report-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionDefinitionApi apiInstance = new HistoricDecisionDefinitionApi(defaultClient);
        String decisionDefinitionIdIn = "decisionDefinitionIdIn_example"; // String | Filter by decision definition ids. Must be a comma-separated list of decision definition ids.
        String decisionDefinitionKeyIn = "decisionDefinitionKeyIn_example"; // String | Filter by decision definition keys. Must be a comma-separated list of decision definition keys.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids.
        Boolean withoutTenantId = true; // Boolean | Only include decision definitions which belong to no tenant. Value may only be `true`, as `false`  is the default behavior.
        Boolean compact = true; // Boolean | Only include decision instances which have more than zero finished instances. Value may only be `true`,  as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<CleanableHistoricDecisionInstanceReportResultDto> result = apiInstance.getCleanableHistoricDecisionInstanceReport(decisionDefinitionIdIn, decisionDefinitionKeyIn, tenantIdIn, withoutTenantId, compact, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionDefinitionApi#getCleanableHistoricDecisionInstanceReport");
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
 **decisionDefinitionIdIn** | **String**| Filter by decision definition ids. Must be a comma-separated list of decision definition ids. | [optional]
 **decisionDefinitionKeyIn** | **String**| Filter by decision definition keys. Must be a comma-separated list of decision definition keys. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include decision definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60;  is the default behavior. | [optional]
 **compact** | **Boolean**| Only include decision instances which have more than zero finished instances. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: finished]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;CleanableHistoricDecisionInstanceReportResultDto&gt;**](CleanableHistoricDecisionInstanceReportResultDto.md)

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


## getCleanableHistoricDecisionInstanceReportCount

> CountResultDto getCleanableHistoricDecisionInstanceReportCount(decisionDefinitionIdIn, decisionDefinitionKeyIn, tenantIdIn, withoutTenantId, compact)

Get Cleanable Decision Instance Report Count

Queries for the number of report results about a decision definition and finished decision instances relevant to history cleanup (see [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup)). Takes the same parameters as the [Get Cleanable Decision Instance Report](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-definition/get-cleanable-decision-instance-report/)  method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionDefinitionApi apiInstance = new HistoricDecisionDefinitionApi(defaultClient);
        String decisionDefinitionIdIn = "decisionDefinitionIdIn_example"; // String | Filter by decision definition ids. Must be a comma-separated list of decision definition ids.
        String decisionDefinitionKeyIn = "decisionDefinitionKeyIn_example"; // String | Filter by decision definition keys. Must be a comma-separated list of decision definition keys.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids.
        Boolean withoutTenantId = true; // Boolean | Only include decision definitions which belong to no tenant. Value may only be `true`, as `false`  is the default behavior.
        Boolean compact = true; // Boolean | Only include decision instances which have more than zero finished instances. Value may only be `true`,  as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getCleanableHistoricDecisionInstanceReportCount(decisionDefinitionIdIn, decisionDefinitionKeyIn, tenantIdIn, withoutTenantId, compact);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionDefinitionApi#getCleanableHistoricDecisionInstanceReportCount");
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
 **decisionDefinitionIdIn** | **String**| Filter by decision definition ids. Must be a comma-separated list of decision definition ids. | [optional]
 **decisionDefinitionKeyIn** | **String**| Filter by decision definition keys. Must be a comma-separated list of decision definition keys. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A decision definition must have one of the given tenant  ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include decision definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60;  is the default behavior. | [optional]
 **compact** | **Boolean**| Only include decision instances which have more than zero finished instances. Value may only be &#x60;true&#x60;,  as &#x60;false&#x60; is the default behavior. | [optional]

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

