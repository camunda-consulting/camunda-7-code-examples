# HistoricDecisionInstanceApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteAsync**](HistoricDecisionInstanceApi.md#deleteAsync) | **POST** /history/decision-instance/delete | Delete Async (POST)
[**getHistoricDecisionInstance**](HistoricDecisionInstanceApi.md#getHistoricDecisionInstance) | **GET** /history/decision-instance/{id} | Get Historic Decision Instance
[**getHistoricDecisionInstances**](HistoricDecisionInstanceApi.md#getHistoricDecisionInstances) | **GET** /history/decision-instance | Get Historic Decision Instances
[**getHistoricDecisionInstancesCount**](HistoricDecisionInstanceApi.md#getHistoricDecisionInstancesCount) | **GET** /history/decision-instance/count | Get Historic Decision Instance Count
[**setRemovalTimeAsyncHistoricDecisionInstance**](HistoricDecisionInstanceApi.md#setRemovalTimeAsyncHistoricDecisionInstance) | **POST** /history/decision-instance/set-removal-time | Set Removal Time Async (POST)



## deleteAsync

> BatchDto deleteAsync(deleteHistoricDecisionInstancesDto)

Delete Async (POST)

Delete multiple historic decision instances asynchronously (batch). At least &#x60;historicDecisionInstanceIds&#x60; or &#x60;historicDecisionInstanceQuery&#x60;  has to be provided. If both are provided then all instances matching query  criterion and instances from the list will be deleted.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionInstanceApi apiInstance = new HistoricDecisionInstanceApi(defaultClient);
        DeleteHistoricDecisionInstancesDto deleteHistoricDecisionInstancesDto = new DeleteHistoricDecisionInstancesDto(); // DeleteHistoricDecisionInstancesDto | 
        try {
            BatchDto result = apiInstance.deleteAsync(deleteHistoricDecisionInstancesDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionInstanceApi#deleteAsync");
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
 **deleteHistoricDecisionInstancesDto** | [**DeleteHistoricDecisionInstancesDto**](DeleteHistoricDecisionInstancesDto.md)|  | [optional]

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
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, i.e. neither &#x60;historicDecisionInstanceIds&#x60; nor &#x60;historicDecisionInstanceQuery&#x60; is present. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling)  for the error response format. |  -  |


## getHistoricDecisionInstance

> HistoricDecisionInstanceDto getHistoricDecisionInstance(id, includeInputs, includeOutputs, disableBinaryFetching, disableCustomObjectDeserialization)

Get Historic Decision Instance

Retrieves a historic decision instance by id, according to the  &#x60;HistoricDecisionInstance&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionInstanceApi apiInstance = new HistoricDecisionInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the historic decision instance to be retrieved.
        Boolean includeInputs = true; // Boolean | Include input values in the result. Value may only be `true`, as `false` is the default behavior.
        Boolean includeOutputs = true; // Boolean | Include output values in the result. Value may only be `true`, as `false` is the default behavior.
        Boolean disableBinaryFetching = true; // Boolean | Disables fetching of byte array input and output values. Value may only be `true`, as `false` is the default behavior.
        Boolean disableCustomObjectDeserialization = true; // Boolean | Disables deserialization of input and output values that are custom objects. Value may only be `true`, as `false` is the default behavior.
        try {
            HistoricDecisionInstanceDto result = apiInstance.getHistoricDecisionInstance(id, includeInputs, includeOutputs, disableBinaryFetching, disableCustomObjectDeserialization);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionInstanceApi#getHistoricDecisionInstance");
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
 **id** | **String**| The id of the historic decision instance to be retrieved. |
 **includeInputs** | **Boolean**| Include input values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeOutputs** | **Boolean**| Include output values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **disableBinaryFetching** | **Boolean**| Disables fetching of byte array input and output values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **disableCustomObjectDeserialization** | **Boolean**| Disables deserialization of input and output values that are custom objects. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

### Return type

[**HistoricDecisionInstanceDto**](HistoricDecisionInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Historic decision instance with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricDecisionInstances

> List&lt;HistoricDecisionInstanceDto&gt; getHistoricDecisionInstances(decisionInstanceId, decisionInstanceIdIn, decisionDefinitionId, decisionDefinitionIdIn, decisionDefinitionKey, decisionDefinitionKeyIn, decisionDefinitionName, decisionDefinitionNameLike, processDefinitionId, processDefinitionKey, processInstanceId, caseDefinitionId, caseDefinitionKey, caseInstanceId, activityIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, evaluatedBefore, evaluatedAfter, userId, rootDecisionInstanceId, rootDecisionInstancesOnly, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, includeInputs, includeOutputs, disableBinaryFetching, disableCustomObjectDeserialization, sortBy, sortOrder, firstResult, maxResults)

Get Historic Decision Instances

Queries for historic decision instances that fulfill the given parameters.  The size of the result set can be retrieved by using the  [Get Historic Decision Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-instance/get-decision-instance-query-count/)  method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionInstanceApi apiInstance = new HistoricDecisionInstanceApi(defaultClient);
        String decisionInstanceId = "decisionInstanceId_example"; // String | Filter by decision instance id.
        String decisionInstanceIdIn = "decisionInstanceIdIn_example"; // String | Filter by decision instance ids. Must be a comma-separated list of decision instance ids.
        String decisionDefinitionId = "decisionDefinitionId_example"; // String | Filter by the decision definition the instances belongs to.
        String decisionDefinitionIdIn = "decisionDefinitionIdIn_example"; // String | Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids.
        String decisionDefinitionKey = "decisionDefinitionKey_example"; // String | Filter by the key of the decision definition the instances belongs to.
        String decisionDefinitionKeyIn = "decisionDefinitionKeyIn_example"; // String | Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys.
        String decisionDefinitionName = "decisionDefinitionName_example"; // String | Filter by the name of the decision definition the instances belongs to.
        String decisionDefinitionNameLike = "decisionDefinitionNameLike_example"; // String | Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the instances belongs to.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the instances belongs to.
        String processInstanceId = "processInstanceId_example"; // String | Filter by the process instance the instances belongs to.
        String caseDefinitionId = "caseDefinitionId_example"; // String | Filter by the case definition the instances belongs to.
        String caseDefinitionKey = "caseDefinitionKey_example"; // String | Filter by the key of the case definition the instances belongs to.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by the case instance the instances belongs to.
        String activityIdIn = "activityIdIn_example"; // String | Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic decision instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        OffsetDateTime evaluatedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM- dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime evaluatedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM- dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String userId = "userId_example"; // String | Restrict to instances that were evaluated by the given user.
        String rootDecisionInstanceId = "rootDecisionInstanceId_example"; // String | Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id.
        Boolean rootDecisionInstancesOnly = true; // Boolean | Restrict to instances those are the root decision instance of an evaluation. Value may only be `true`, as `false` is the default behavior.
        String decisionRequirementsDefinitionId = "decisionRequirementsDefinitionId_example"; // String | Filter by the decision requirements definition the instances belongs to.
        String decisionRequirementsDefinitionKey = "decisionRequirementsDefinitionKey_example"; // String | Filter by the key of the decision requirements definition the instances belongs to.
        Boolean includeInputs = true; // Boolean | Include input values in the result. Value may only be `true`, as `false` is the default behavior.
        Boolean includeOutputs = true; // Boolean | Include output values in the result. Value may only be `true`, as `false` is the default behavior.
        Boolean disableBinaryFetching = true; // Boolean | Disables fetching of byte array input and output values. Value may only be `true`, as `false` is the default behavior.
        Boolean disableCustomObjectDeserialization = true; // Boolean | Disables deserialization of input and output values that are custom objects. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<HistoricDecisionInstanceDto> result = apiInstance.getHistoricDecisionInstances(decisionInstanceId, decisionInstanceIdIn, decisionDefinitionId, decisionDefinitionIdIn, decisionDefinitionKey, decisionDefinitionKeyIn, decisionDefinitionName, decisionDefinitionNameLike, processDefinitionId, processDefinitionKey, processInstanceId, caseDefinitionId, caseDefinitionKey, caseInstanceId, activityIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, evaluatedBefore, evaluatedAfter, userId, rootDecisionInstanceId, rootDecisionInstancesOnly, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, includeInputs, includeOutputs, disableBinaryFetching, disableCustomObjectDeserialization, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionInstanceApi#getHistoricDecisionInstances");
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
 **decisionInstanceId** | **String**| Filter by decision instance id. | [optional]
 **decisionInstanceIdIn** | **String**| Filter by decision instance ids. Must be a comma-separated list of decision instance ids. | [optional]
 **decisionDefinitionId** | **String**| Filter by the decision definition the instances belongs to. | [optional]
 **decisionDefinitionIdIn** | **String**| Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids. | [optional]
 **decisionDefinitionKey** | **String**| Filter by the key of the decision definition the instances belongs to. | [optional]
 **decisionDefinitionKeyIn** | **String**| Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys. | [optional]
 **decisionDefinitionName** | **String**| Filter by the name of the decision definition the instances belongs to. | [optional]
 **decisionDefinitionNameLike** | **String**| Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the instances belongs to. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the instances belongs to. | [optional]
 **processInstanceId** | **String**| Filter by the process instance the instances belongs to. | [optional]
 **caseDefinitionId** | **String**| Filter by the case definition the instances belongs to. | [optional]
 **caseDefinitionKey** | **String**| Filter by the key of the case definition the instances belongs to. | [optional]
 **caseInstanceId** | **String**| Filter by the case instance the instances belongs to. | [optional]
 **activityIdIn** | **String**| Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids. | [optional]
 **activityInstanceIdIn** | **String**| Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic decision instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **evaluatedBefore** | **OffsetDateTime**| Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **evaluatedAfter** | **OffsetDateTime**| Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **userId** | **String**| Restrict to instances that were evaluated by the given user. | [optional]
 **rootDecisionInstanceId** | **String**| Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id. | [optional]
 **rootDecisionInstancesOnly** | **Boolean**| Restrict to instances those are the root decision instance of an evaluation. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **decisionRequirementsDefinitionId** | **String**| Filter by the decision requirements definition the instances belongs to. | [optional]
 **decisionRequirementsDefinitionKey** | **String**| Filter by the key of the decision requirements definition the instances belongs to. | [optional]
 **includeInputs** | **Boolean**| Include input values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeOutputs** | **Boolean**| Include output values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **disableBinaryFetching** | **Boolean**| Disables fetching of byte array input and output values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **disableCustomObjectDeserialization** | **Boolean**| Disables deserialization of input and output values that are custom objects. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: evaluationTime, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;HistoricDecisionInstanceDto&gt;**](HistoricDecisionInstanceDto.md)

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


## getHistoricDecisionInstancesCount

> CountResultDto getHistoricDecisionInstancesCount(decisionInstanceId, decisionInstanceIdIn, decisionDefinitionId, decisionDefinitionIdIn, decisionDefinitionKey, decisionDefinitionKeyIn, decisionDefinitionName, decisionDefinitionNameLike, processDefinitionId, processDefinitionKey, processInstanceId, caseDefinitionId, caseDefinitionKey, caseInstanceId, activityIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, evaluatedBefore, evaluatedAfter, userId, rootDecisionInstanceId, rootDecisionInstancesOnly, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey)

Get Historic Decision Instance Count

Queries for the number of historic decision instances that fulfill the given parameters.  Takes the same parameters as the  [Get Historic Decision Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/decision-instance/get-decision-instance-query/)  method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionInstanceApi apiInstance = new HistoricDecisionInstanceApi(defaultClient);
        String decisionInstanceId = "decisionInstanceId_example"; // String | Filter by decision instance id.
        String decisionInstanceIdIn = "decisionInstanceIdIn_example"; // String | Filter by decision instance ids. Must be a comma-separated list of decision instance ids.
        String decisionDefinitionId = "decisionDefinitionId_example"; // String | Filter by the decision definition the instances belongs to.
        String decisionDefinitionIdIn = "decisionDefinitionIdIn_example"; // String | Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids.
        String decisionDefinitionKey = "decisionDefinitionKey_example"; // String | Filter by the key of the decision definition the instances belongs to.
        String decisionDefinitionKeyIn = "decisionDefinitionKeyIn_example"; // String | Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys.
        String decisionDefinitionName = "decisionDefinitionName_example"; // String | Filter by the name of the decision definition the instances belongs to.
        String decisionDefinitionNameLike = "decisionDefinitionNameLike_example"; // String | Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the instances belongs to.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the instances belongs to.
        String processInstanceId = "processInstanceId_example"; // String | Filter by the process instance the instances belongs to.
        String caseDefinitionId = "caseDefinitionId_example"; // String | Filter by the case definition the instances belongs to.
        String caseDefinitionKey = "caseDefinitionKey_example"; // String | Filter by the key of the case definition the instances belongs to.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by the case instance the instances belongs to.
        String activityIdIn = "activityIdIn_example"; // String | Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic decision instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        OffsetDateTime evaluatedBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM- dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        OffsetDateTime evaluatedAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format `yyyy-MM- dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
        String userId = "userId_example"; // String | Restrict to instances that were evaluated by the given user.
        String rootDecisionInstanceId = "rootDecisionInstanceId_example"; // String | Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id.
        Boolean rootDecisionInstancesOnly = true; // Boolean | Restrict to instances those are the root decision instance of an evaluation. Value may only be `true`, as `false` is the default behavior.
        String decisionRequirementsDefinitionId = "decisionRequirementsDefinitionId_example"; // String | Filter by the decision requirements definition the instances belongs to.
        String decisionRequirementsDefinitionKey = "decisionRequirementsDefinitionKey_example"; // String | Filter by the key of the decision requirements definition the instances belongs to.
        try {
            CountResultDto result = apiInstance.getHistoricDecisionInstancesCount(decisionInstanceId, decisionInstanceIdIn, decisionDefinitionId, decisionDefinitionIdIn, decisionDefinitionKey, decisionDefinitionKeyIn, decisionDefinitionName, decisionDefinitionNameLike, processDefinitionId, processDefinitionKey, processInstanceId, caseDefinitionId, caseDefinitionKey, caseInstanceId, activityIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, evaluatedBefore, evaluatedAfter, userId, rootDecisionInstanceId, rootDecisionInstancesOnly, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionInstanceApi#getHistoricDecisionInstancesCount");
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
 **decisionInstanceId** | **String**| Filter by decision instance id. | [optional]
 **decisionInstanceIdIn** | **String**| Filter by decision instance ids. Must be a comma-separated list of decision instance ids. | [optional]
 **decisionDefinitionId** | **String**| Filter by the decision definition the instances belongs to. | [optional]
 **decisionDefinitionIdIn** | **String**| Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids. | [optional]
 **decisionDefinitionKey** | **String**| Filter by the key of the decision definition the instances belongs to. | [optional]
 **decisionDefinitionKeyIn** | **String**| Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys. | [optional]
 **decisionDefinitionName** | **String**| Filter by the name of the decision definition the instances belongs to. | [optional]
 **decisionDefinitionNameLike** | **String**| Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the instances belongs to. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the instances belongs to. | [optional]
 **processInstanceId** | **String**| Filter by the process instance the instances belongs to. | [optional]
 **caseDefinitionId** | **String**| Filter by the case definition the instances belongs to. | [optional]
 **caseDefinitionKey** | **String**| Filter by the key of the case definition the instances belongs to. | [optional]
 **caseInstanceId** | **String**| Filter by the case instance the instances belongs to. | [optional]
 **activityIdIn** | **String**| Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids. | [optional]
 **activityInstanceIdIn** | **String**| Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic decision instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **evaluatedBefore** | **OffsetDateTime**| Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **evaluatedAfter** | **OffsetDateTime**| Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **userId** | **String**| Restrict to instances that were evaluated by the given user. | [optional]
 **rootDecisionInstanceId** | **String**| Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id. | [optional]
 **rootDecisionInstancesOnly** | **Boolean**| Restrict to instances those are the root decision instance of an evaluation. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **decisionRequirementsDefinitionId** | **String**| Filter by the decision requirements definition the instances belongs to. | [optional]
 **decisionRequirementsDefinitionKey** | **String**| Filter by the key of the decision requirements definition the instances belongs to. | [optional]

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


## setRemovalTimeAsyncHistoricDecisionInstance

> BatchDto setRemovalTimeAsyncHistoricDecisionInstance(setRemovalTimeToHistoricDecisionInstancesDto)

Set Removal Time Async (POST)

Sets the removal time to multiple historic decision instances asynchronously (batch).  At least &#x60;historicDecisionInstanceIds&#x60; or &#x60;historicDecisionInstanceQuery&#x60; has to be provided. If both are provided, all instances matching query criterion and instances from the list will be updated with a removal time.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDecisionInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDecisionInstanceApi apiInstance = new HistoricDecisionInstanceApi(defaultClient);
        SetRemovalTimeToHistoricDecisionInstancesDto setRemovalTimeToHistoricDecisionInstancesDto = new SetRemovalTimeToHistoricDecisionInstancesDto(); // SetRemovalTimeToHistoricDecisionInstancesDto | 
        try {
            BatchDto result = apiInstance.setRemovalTimeAsyncHistoricDecisionInstance(setRemovalTimeToHistoricDecisionInstancesDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDecisionInstanceApi#setRemovalTimeAsyncHistoricDecisionInstance");
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
 **setRemovalTimeToHistoricDecisionInstancesDto** | [**SetRemovalTimeToHistoricDecisionInstancesDto**](SetRemovalTimeToHistoricDecisionInstancesDto.md)|  | [optional]

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
| **200** | Request successful. |  -  |
| **400** | Request was unsuccessfull due to a bad user request. This occurs if some of the query parameters are invalid, e. g. if neither historicDecisionInstances nor historicDecisionInstanceQuery is present or if no mode is specified.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

