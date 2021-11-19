# HistoricDetailApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHistoricDetails**](HistoricDetailApi.md#getHistoricDetails) | **GET** /history/detail | Get Historic Details
[**getHistoricDetailsCount**](HistoricDetailApi.md#getHistoricDetailsCount) | **GET** /history/detail/count | Get Historic Detail Count
[**historicDetail**](HistoricDetailApi.md#historicDetail) | **GET** /history/detail/{id} | Get Historic Detail
[**historicDetailBinary**](HistoricDetailApi.md#historicDetailBinary) | **GET** /history/detail/{id}/data | Get Historic Detail (Binary)
[**queryHistoricDetails**](HistoricDetailApi.md#queryHistoricDetails) | **POST** /history/detail | Get Historic Details (POST)



## getHistoricDetails

> List&lt;HistoricDetailDto&gt; getHistoricDetails(processInstanceId, processInstanceIdIn, executionId, taskId, activityInstanceId, caseInstanceId, caseExecutionId, variableInstanceId, variableTypeIn, tenantIdIn, withoutTenantId, userOperationId, formFields, variableUpdates, excludeTaskDetails, initial, occurredBefore, occurredAfter, sortBy, sortOrder, firstResult, maxResults, deserializeValues)

Get Historic Details

Queries for historic details that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Historic Detail Count](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDetailApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDetailApi apiInstance = new HistoricDetailApi(defaultClient);
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processInstanceIdIn = "processInstanceIdIn_example"; // String | Only include historic details which belong to one of the passed comma-separated process instance ids.
        String executionId = "executionId_example"; // String | Filter by execution id.
        String taskId = "taskId_example"; // String | Filter by task id.
        String activityInstanceId = "activityInstanceId_example"; // String | Filter by activity instance id.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by case instance id.
        String caseExecutionId = "caseExecutionId_example"; // String | Filter by case execution id.
        String variableInstanceId = "variableInstanceId_example"; // String | Filter by variable instance id.
        String variableTypeIn = "variableTypeIn_example"; // String | Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type `serializable`.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic details that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String userOperationId = "userOperationId_example"; // String | Filter by a user operation id.
        Boolean formFields = true; // Boolean | Only include `HistoricFormFields`. Value may only be `true`, as `false` is the default behavior.
        Boolean variableUpdates = true; // Boolean | Only include `HistoricVariableUpdates`. Value may only be `true`, as `false` is the default behavior.
        Boolean excludeTaskDetails = true; // Boolean | Excludes all task-related `HistoricDetails`, so only items which have no task id set will be selected. When this parameter is used together with `taskId`, this call is ignored and task details are not excluded. Value may only be `true`, as `false` is the default behavior.
        Boolean initial = true; // Boolean | Restrict to historic variable updates that contain only initial variable values. Value may only be `true`, as `false` is the default behavior.
        OffsetDateTime occurredBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        OffsetDateTime occurredAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            List<HistoricDetailDto> result = apiInstance.getHistoricDetails(processInstanceId, processInstanceIdIn, executionId, taskId, activityInstanceId, caseInstanceId, caseExecutionId, variableInstanceId, variableTypeIn, tenantIdIn, withoutTenantId, userOperationId, formFields, variableUpdates, excludeTaskDetails, initial, occurredBefore, occurredAfter, sortBy, sortOrder, firstResult, maxResults, deserializeValues);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDetailApi#getHistoricDetails");
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
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processInstanceIdIn** | **String**| Only include historic details which belong to one of the passed comma-separated process instance ids. | [optional]
 **executionId** | **String**| Filter by execution id. | [optional]
 **taskId** | **String**| Filter by task id. | [optional]
 **activityInstanceId** | **String**| Filter by activity instance id. | [optional]
 **caseInstanceId** | **String**| Filter by case instance id. | [optional]
 **caseExecutionId** | **String**| Filter by case execution id. | [optional]
 **variableInstanceId** | **String**| Filter by variable instance id. | [optional]
 **variableTypeIn** | **String**| Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#x60;serializable&#x60;. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic details that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **userOperationId** | **String**| Filter by a user operation id. | [optional]
 **formFields** | **Boolean**| Only include &#x60;HistoricFormFields&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **variableUpdates** | **Boolean**| Only include &#x60;HistoricVariableUpdates&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **excludeTaskDetails** | **Boolean**| Excludes all task-related &#x60;HistoricDetails&#x60;, so only items which have no task id set will be selected. When this parameter is used together with &#x60;taskId&#x60;, this call is ignored and task details are not excluded. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **initial** | **Boolean**| Restrict to historic variable updates that contain only initial variable values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **occurredBefore** | **OffsetDateTime**| Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]
 **occurredAfter** | **OffsetDateTime**| Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: processInstanceId, variableName, variableType, variableRevision, formPropertyId, time, occurrence, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**List&lt;HistoricDetailDto&gt;**](HistoricDetailDto.md)

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


## getHistoricDetailsCount

> CountResultDto getHistoricDetailsCount(processInstanceId, processInstanceIdIn, executionId, taskId, activityInstanceId, caseInstanceId, caseExecutionId, variableInstanceId, variableTypeIn, tenantIdIn, withoutTenantId, userOperationId, formFields, variableUpdates, excludeTaskDetails, initial, occurredBefore, occurredAfter)

Get Historic Detail Count

Queries for the number of historic details that fulfill the given parameters. Takes the same parameters as the [Get Historic Details](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDetailApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDetailApi apiInstance = new HistoricDetailApi(defaultClient);
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String processInstanceIdIn = "processInstanceIdIn_example"; // String | Only include historic details which belong to one of the passed comma-separated process instance ids.
        String executionId = "executionId_example"; // String | Filter by execution id.
        String taskId = "taskId_example"; // String | Filter by task id.
        String activityInstanceId = "activityInstanceId_example"; // String | Filter by activity instance id.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by case instance id.
        String caseExecutionId = "caseExecutionId_example"; // String | Filter by case execution id.
        String variableInstanceId = "variableInstanceId_example"; // String | Filter by variable instance id.
        String variableTypeIn = "variableTypeIn_example"; // String | Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type `serializable`.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic details that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String userOperationId = "userOperationId_example"; // String | Filter by a user operation id.
        Boolean formFields = true; // Boolean | Only include `HistoricFormFields`. Value may only be `true`, as `false` is the default behavior.
        Boolean variableUpdates = true; // Boolean | Only include `HistoricVariableUpdates`. Value may only be `true`, as `false` is the default behavior.
        Boolean excludeTaskDetails = true; // Boolean | Excludes all task-related `HistoricDetails`, so only items which have no task id set will be selected. When this parameter is used together with `taskId`, this call is ignored and task details are not excluded. Value may only be `true`, as `false` is the default behavior.
        Boolean initial = true; // Boolean | Restrict to historic variable updates that contain only initial variable values. Value may only be `true`, as `false` is the default behavior.
        OffsetDateTime occurredBefore = OffsetDateTime.now(); // OffsetDateTime | Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        OffsetDateTime occurredAfter = OffsetDateTime.now(); // OffsetDateTime | Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        try {
            CountResultDto result = apiInstance.getHistoricDetailsCount(processInstanceId, processInstanceIdIn, executionId, taskId, activityInstanceId, caseInstanceId, caseExecutionId, variableInstanceId, variableTypeIn, tenantIdIn, withoutTenantId, userOperationId, formFields, variableUpdates, excludeTaskDetails, initial, occurredBefore, occurredAfter);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDetailApi#getHistoricDetailsCount");
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
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **processInstanceIdIn** | **String**| Only include historic details which belong to one of the passed comma-separated process instance ids. | [optional]
 **executionId** | **String**| Filter by execution id. | [optional]
 **taskId** | **String**| Filter by task id. | [optional]
 **activityInstanceId** | **String**| Filter by activity instance id. | [optional]
 **caseInstanceId** | **String**| Filter by case instance id. | [optional]
 **caseExecutionId** | **String**| Filter by case execution id. | [optional]
 **variableInstanceId** | **String**| Filter by variable instance id. | [optional]
 **variableTypeIn** | **String**| Only include historic details where the variable updates belong to one of the passed comma-separated list of variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#x60;serializable&#x60;. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic details that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **userOperationId** | **String**| Filter by a user operation id. | [optional]
 **formFields** | **Boolean**| Only include &#x60;HistoricFormFields&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **variableUpdates** | **Boolean**| Only include &#x60;HistoricVariableUpdates&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **excludeTaskDetails** | **Boolean**| Excludes all task-related &#x60;HistoricDetails&#x60;, so only items which have no task id set will be selected. When this parameter is used together with &#x60;taskId&#x60;, this call is ignored and task details are not excluded. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **initial** | **Boolean**| Restrict to historic variable updates that contain only initial variable values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **occurredBefore** | **OffsetDateTime**| Restrict to historic details that occured before the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]
 **occurredAfter** | **OffsetDateTime**| Restrict to historic details that occured after the given date (including the date). Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]

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


## historicDetail

> HistoricDetailDto historicDetail(id, deserializeValue)

Get Historic Detail

Retrieves a historic detail by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDetailApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDetailApi apiInstance = new HistoricDetailApi(defaultClient);
        String id = "id_example"; // String | The id of the detail.
        Boolean deserializeValue = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            HistoricDetailDto result = apiInstance.historicDetail(id, deserializeValue);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDetailApi#historicDetail");
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
 **id** | **String**| The id of the detail. |
 **deserializeValue** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**HistoricDetailDto**](HistoricDetailDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## historicDetailBinary

> File historicDetailBinary(id)

Get Historic Detail (Binary)

Retrieves the content of a historic variable update by id. Applicable for byte array and file variables.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDetailApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDetailApi apiInstance = new HistoricDetailApi(defaultClient);
        String id = "id_example"; // String | The id of the historic variable update.
        try {
            File result = apiInstance.historicDetailBinary(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDetailApi#historicDetailBinary");
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
 **id** | **String**| The id of the historic variable update. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/octet-stream, */*, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Detail with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Detail with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryHistoricDetails

> List&lt;HistoricDetailDto&gt; queryHistoricDetails(firstResult, maxResults, deserializeValues, historicDetailQueryDto)

Get Historic Details (POST)

Queries for historic details that fulfill the given parameters. This method is slightly more powerful than the [Get Historic Details](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query/) method because it allows sorting by multiple parameters. The size of the result set can be retrieved by using the [Get Historic Detail Count](https://docs.camunda.org/manual/7.16/reference/rest/history/detail/get-detail-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricDetailApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricDetailApi apiInstance = new HistoricDetailApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        HistoricDetailQueryDto historicDetailQueryDto = new HistoricDetailQueryDto(); // HistoricDetailQueryDto | 
        try {
            List<HistoricDetailDto> result = apiInstance.queryHistoricDetails(firstResult, maxResults, deserializeValues, historicDetailQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricDetailApi#queryHistoricDetails");
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
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]
 **historicDetailQueryDto** | [**HistoricDetailQueryDto**](HistoricDetailQueryDto.md)|  | [optional]

### Return type

[**List&lt;HistoricDetailDto&gt;**](HistoricDetailDto.md)

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

