# HistoricIdentityLinkLogApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHistoricIdentityLinks**](HistoricIdentityLinkLogApi.md#getHistoricIdentityLinks) | **GET** /history/identity-link-log | Get Identity Link Logs
[**getHistoricIdentityLinksCount**](HistoricIdentityLinkLogApi.md#getHistoricIdentityLinksCount) | **GET** /history/identity-link-log/count | Get Identity Link Log Count



## getHistoricIdentityLinks

> List&lt;HistoricIdentityLinkLogDto&gt; getHistoricIdentityLinks(type, userId, groupId, dateBefore, dateAfter, taskId, processDefinitionId, processDefinitionKey, operationType, assignerId, tenantIdIn, withoutTenantId, sortBy, sortOrder, firstResult, maxResults)

Get Identity Link Logs

Queries for historic identity link logs that fulfill given parameters. The size of the result set can be retrieved by using the [Get Identity-Link-Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/identity-links/get-identity-link-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricIdentityLinkLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricIdentityLinkLogApi apiInstance = new HistoricIdentityLinkLogApi(defaultClient);
        String type = "type_example"; // String | Restricts to identity links that have the given type (candidate/assignee/owner).
        String userId = "userId_example"; // String | Restricts to identity links that have the given user id.
        String groupId = "groupId_example"; // String | Restricts to identity links that have the given group id.
        OffsetDateTime dateBefore = OffsetDateTime.now(); // OffsetDateTime | Restricts to identity links that have the time before the given time.
        OffsetDateTime dateAfter = OffsetDateTime.now(); // OffsetDateTime | Restricts to identity links that have the time after the given time.
        String taskId = "taskId_example"; // String | Restricts to identity links that have the given task id.
        String processDefinitionId = "processDefinitionId_example"; // String | Restricts to identity links that have the given process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Restricts to identity links that have the given process definition key.
        String operationType = "operationType_example"; // String | Restricts to identity links that have the given operationType (add/delete).
        String assignerId = "assignerId_example"; // String | Restricts to identity links that have the given assigner id.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic identity links that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<HistoricIdentityLinkLogDto> result = apiInstance.getHistoricIdentityLinks(type, userId, groupId, dateBefore, dateAfter, taskId, processDefinitionId, processDefinitionKey, operationType, assignerId, tenantIdIn, withoutTenantId, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricIdentityLinkLogApi#getHistoricIdentityLinks");
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
 **type** | **String**| Restricts to identity links that have the given type (candidate/assignee/owner). | [optional]
 **userId** | **String**| Restricts to identity links that have the given user id. | [optional]
 **groupId** | **String**| Restricts to identity links that have the given group id. | [optional]
 **dateBefore** | **OffsetDateTime**| Restricts to identity links that have the time before the given time. | [optional]
 **dateAfter** | **OffsetDateTime**| Restricts to identity links that have the time after the given time. | [optional]
 **taskId** | **String**| Restricts to identity links that have the given task id. | [optional]
 **processDefinitionId** | **String**| Restricts to identity links that have the given process definition id. | [optional]
 **processDefinitionKey** | **String**| Restricts to identity links that have the given process definition key. | [optional]
 **operationType** | **String**| Restricts to identity links that have the given operationType (add/delete). | [optional]
 **assignerId** | **String**| Restricts to identity links that have the given assigner id. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic identity links that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: time, type, userId, groupId, taskId, processDefinitionId, processDefinitionKey, operationType, assignerId, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;HistoricIdentityLinkLogDto&gt;**](HistoricIdentityLinkLogDto.md)

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


## getHistoricIdentityLinksCount

> CountResultDto getHistoricIdentityLinksCount(type, userId, groupId, dateBefore, dateAfter, taskId, processDefinitionId, processDefinitionKey, operationType, assignerId, tenantIdIn, withoutTenantId)

Get Identity Link Log Count

Queries for the number of historic identity link logs that fulfill the given parameters. Takes the same parameters as the [Get Identity-Link-Logs](https://docs.camunda.org/manual/7.16/reference/rest/history/identity-links/get-identity-link-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricIdentityLinkLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricIdentityLinkLogApi apiInstance = new HistoricIdentityLinkLogApi(defaultClient);
        String type = "type_example"; // String | Restricts to identity links that have the given type (candidate/assignee/owner).
        String userId = "userId_example"; // String | Restricts to identity links that have the given user id.
        String groupId = "groupId_example"; // String | Restricts to identity links that have the given group id.
        OffsetDateTime dateBefore = OffsetDateTime.now(); // OffsetDateTime | Restricts to identity links that have the time before the given time.
        OffsetDateTime dateAfter = OffsetDateTime.now(); // OffsetDateTime | Restricts to identity links that have the time after the given time.
        String taskId = "taskId_example"; // String | Restricts to identity links that have the given task id.
        String processDefinitionId = "processDefinitionId_example"; // String | Restricts to identity links that have the given process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Restricts to identity links that have the given process definition key.
        String operationType = "operationType_example"; // String | Restricts to identity links that have the given operationType (add/delete).
        String assignerId = "assignerId_example"; // String | Restricts to identity links that have the given assigner id.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic identity links that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getHistoricIdentityLinksCount(type, userId, groupId, dateBefore, dateAfter, taskId, processDefinitionId, processDefinitionKey, operationType, assignerId, tenantIdIn, withoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricIdentityLinkLogApi#getHistoricIdentityLinksCount");
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
 **type** | **String**| Restricts to identity links that have the given type (candidate/assignee/owner). | [optional]
 **userId** | **String**| Restricts to identity links that have the given user id. | [optional]
 **groupId** | **String**| Restricts to identity links that have the given group id. | [optional]
 **dateBefore** | **OffsetDateTime**| Restricts to identity links that have the time before the given time. | [optional]
 **dateAfter** | **OffsetDateTime**| Restricts to identity links that have the time after the given time. | [optional]
 **taskId** | **String**| Restricts to identity links that have the given task id. | [optional]
 **processDefinitionId** | **String**| Restricts to identity links that have the given process definition id. | [optional]
 **processDefinitionKey** | **String**| Restricts to identity links that have the given process definition key. | [optional]
 **operationType** | **String**| Restricts to identity links that have the given operationType (add/delete). | [optional]
 **assignerId** | **String**| Restricts to identity links that have the given assigner id. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic identity links that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

