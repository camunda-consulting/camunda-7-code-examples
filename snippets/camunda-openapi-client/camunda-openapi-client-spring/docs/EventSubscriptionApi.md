# EventSubscriptionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getEventSubscriptions**](EventSubscriptionApi.md#getEventSubscriptions) | **GET** /event-subscription | Get List
[**getEventSubscriptionsCount**](EventSubscriptionApi.md#getEventSubscriptionsCount) | **GET** /event-subscription/count | Get List Count



## getEventSubscriptions

> List&lt;EventSubscriptionDto&gt; getEventSubscriptions(eventSubscriptionId, eventName, eventType, executionId, processInstanceId, activityId, tenantIdIn, withoutTenantId, includeEventSubscriptionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults)

Get List

Queries for event subscriptions that fulfill given parameters. The size of the result set can be retrieved by using the [Get Event Subscriptions count](https://docs.camunda.org/manual/7.16/reference/rest/event-subscription/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.EventSubscriptionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        EventSubscriptionApi apiInstance = new EventSubscriptionApi(defaultClient);
        String eventSubscriptionId = "eventSubscriptionId_example"; // String | Only select subscription with the given id.
        String eventName = "eventName_example"; // String | Only select subscriptions for events with the given name.
        String eventType = "eventType_example"; // String | Only select subscriptions for events with the given type. Valid values: `message`, `signal`, `compensate` and `conditional`.
        String executionId = "executionId_example"; // String | Only select subscriptions that belong to an execution with the given id.
        String processInstanceId = "processInstanceId_example"; // String | Only select subscriptions that belong to a process instance with the given id.
        String activityId = "activityId_example"; // String | Only select subscriptions that belong to an activity with the given id.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only select subscriptions which have no tenant id. Value may only be `true`, as `false` is the default behavior.
        Boolean includeEventSubscriptionsWithoutTenantId = true; // Boolean | Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<EventSubscriptionDto> result = apiInstance.getEventSubscriptions(eventSubscriptionId, eventName, eventType, executionId, processInstanceId, activityId, tenantIdIn, withoutTenantId, includeEventSubscriptionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling EventSubscriptionApi#getEventSubscriptions");
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
 **eventSubscriptionId** | **String**| Only select subscription with the given id. | [optional]
 **eventName** | **String**| Only select subscriptions for events with the given name. | [optional]
 **eventType** | **String**| Only select subscriptions for events with the given type. Valid values: &#x60;message&#x60;, &#x60;signal&#x60;, &#x60;compensate&#x60; and &#x60;conditional&#x60;. | [optional] [enum: message, signal, compensate, conditional]
 **executionId** | **String**| Only select subscriptions that belong to an execution with the given id. | [optional]
 **processInstanceId** | **String**| Only select subscriptions that belong to a process instance with the given id. | [optional]
 **activityId** | **String**| Only select subscriptions that belong to an activity with the given id. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only select subscriptions which have no tenant id. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeEventSubscriptionsWithoutTenantId** | **Boolean**| Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: created, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;EventSubscriptionDto&gt;**](EventSubscriptionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Bad Request Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getEventSubscriptionsCount

> CountResultDto getEventSubscriptionsCount(eventSubscriptionId, eventName, eventType, executionId, processInstanceId, activityId, tenantIdIn, withoutTenantId, includeEventSubscriptionsWithoutTenantId)

Get List Count

Queries for the number of event subscriptions that fulfill given parameters. Takes the same parameters as the [Get Event Subscriptions](https://docs.camunda.org/manual/7.16/reference/rest/event-subscription/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.EventSubscriptionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        EventSubscriptionApi apiInstance = new EventSubscriptionApi(defaultClient);
        String eventSubscriptionId = "eventSubscriptionId_example"; // String | Only select subscription with the given id.
        String eventName = "eventName_example"; // String | Only select subscriptions for events with the given name.
        String eventType = "eventType_example"; // String | Only select subscriptions for events with the given type. Valid values: `message`, `signal`, `compensate` and `conditional`.
        String executionId = "executionId_example"; // String | Only select subscriptions that belong to an execution with the given id.
        String processInstanceId = "processInstanceId_example"; // String | Only select subscriptions that belong to a process instance with the given id.
        String activityId = "activityId_example"; // String | Only select subscriptions that belong to an activity with the given id.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only select subscriptions which have no tenant id. Value may only be `true`, as `false` is the default behavior.
        Boolean includeEventSubscriptionsWithoutTenantId = true; // Boolean | Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getEventSubscriptionsCount(eventSubscriptionId, eventName, eventType, executionId, processInstanceId, activityId, tenantIdIn, withoutTenantId, includeEventSubscriptionsWithoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling EventSubscriptionApi#getEventSubscriptionsCount");
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
 **eventSubscriptionId** | **String**| Only select subscription with the given id. | [optional]
 **eventName** | **String**| Only select subscriptions for events with the given name. | [optional]
 **eventType** | **String**| Only select subscriptions for events with the given type. Valid values: &#x60;message&#x60;, &#x60;signal&#x60;, &#x60;compensate&#x60; and &#x60;conditional&#x60;. | [optional] [enum: message, signal, compensate, conditional]
 **executionId** | **String**| Only select subscriptions that belong to an execution with the given id. | [optional]
 **processInstanceId** | **String**| Only select subscriptions that belong to a process instance with the given id. | [optional]
 **activityId** | **String**| Only select subscriptions that belong to an activity with the given id. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. Only select subscriptions that belong to one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only select subscriptions which have no tenant id. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeEventSubscriptionsWithoutTenantId** | **Boolean**| Select event subscriptions which have no tenant id. Can be used in combination with tenantIdIn parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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
| **400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

