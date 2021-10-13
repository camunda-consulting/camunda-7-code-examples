# BatchApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteBatch**](BatchApi.md#deleteBatch) | **DELETE** /batch/{id} | Delete
[**getBatch**](BatchApi.md#getBatch) | **GET** /batch/{id} | Get
[**getBatchStatistics**](BatchApi.md#getBatchStatistics) | **GET** /batch/statistics | Get Statistics
[**getBatchStatisticsCount**](BatchApi.md#getBatchStatisticsCount) | **GET** /batch/statistics/count | Get Statistics Count
[**getBatches**](BatchApi.md#getBatches) | **GET** /batch | Get List
[**getBatchesCount**](BatchApi.md#getBatchesCount) | **GET** /batch/count | Get List Count
[**updateBatchSuspensionState**](BatchApi.md#updateBatchSuspensionState) | **PUT** /batch/{id}/suspended | Activate/Suspend



## deleteBatch

> deleteBatch(id, cascade)

Delete

Deletes a batch by id, including all related jobs and job definitions. Optionally also deletes the batch history.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.BatchApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        BatchApi apiInstance = new BatchApi(defaultClient);
        String id = "id_example"; // String | The id of the batch to be deleted.
        Boolean cascade = true; // Boolean | `true`, if the historic batch and historic job logs for this batch should also be deleted.
        try {
            apiInstance.deleteBatch(id, cascade);
        } catch (ApiException e) {
            System.err.println("Exception when calling BatchApi#deleteBatch");
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
 **id** | **String**| The id of the batch to be deleted. |
 **cascade** | **Boolean**| &#x60;true&#x60;, if the historic batch and historic job logs for this batch should also be deleted. | [optional]

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
| **400** | Batch with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getBatch

> BatchDto getBatch(id)

Get

Retrieves a batch by id, according to the Batch interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.BatchApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        BatchApi apiInstance = new BatchApi(defaultClient);
        String id = "id_example"; // String | The id of the batch to be retrieved.
        try {
            BatchDto result = apiInstance.getBatch(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling BatchApi#getBatch");
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
 **id** | **String**| The id of the batch to be retrieved. |

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Batch with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getBatchStatistics

> List&lt;BatchStatisticsDto&gt; getBatchStatistics(sortBy, sortOrder, firstResult, maxResults, batchId, type, tenantIdIn, withoutTenantId, suspended)

Get Statistics

Queries for batch statistics that fulfill given parameters. Parameters may be the properties of batches, such as the id or type. The size of the result set can be retrieved by using the  [Get Batch Statistics Count](https://docs.camunda.org/manual/7.16/reference/rest/batch/get-statistics-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.BatchApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        BatchApi apiInstance = new BatchApi(defaultClient);
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String batchId = "batchId_example"; // String | Filter by batch id.
        String type = "type_example"; // String | Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of `Strings`. A batch matches if it has one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include batches which belong to no tenant. Value can effectively only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | A `Boolean` value which indicates whether only active or suspended batches should be included. When the value is set to `true`, only suspended batches will be returned and when the value is set to `false`, only active batches will be returned.
        try {
            List<BatchStatisticsDto> result = apiInstance.getBatchStatistics(sortBy, sortOrder, firstResult, maxResults, batchId, type, tenantIdIn, withoutTenantId, suspended);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling BatchApi#getBatchStatistics");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: batchId, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **batchId** | **String**| Filter by batch id. | [optional]
 **type** | **String**| Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of &#x60;Strings&#x60;. A batch matches if it has one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| A &#x60;Boolean&#x60; value which indicates whether only active or suspended batches should be included. When the value is set to &#x60;true&#x60;, only suspended batches will be returned and when the value is set to &#x60;false&#x60;, only active batches will be returned. | [optional]

### Return type

[**List&lt;BatchStatisticsDto&gt;**](BatchStatisticsDto.md)

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


## getBatchStatisticsCount

> CountResultDto getBatchStatisticsCount(batchId, type, tenantIdIn, withoutTenantId, suspended)

Get Statistics Count

Requests the number of batch statistics that fulfill the query criteria. Takes the same filtering parameters as the [Get Batch Statistics](https://docs.camunda.org/manual/7.16/reference/rest/batch/get-statistics-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.BatchApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        BatchApi apiInstance = new BatchApi(defaultClient);
        String batchId = "batchId_example"; // String | Filter by batch id.
        String type = "type_example"; // String | Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of `Strings`. A batch matches if it has one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include batches which belong to no tenant. Value can effectively only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | A `Boolean` value which indicates whether only active or suspended batches should be included. When the value is set to `true`, only suspended batches will be returned and when the value is set to `false`, only active batches will be returned.
        try {
            CountResultDto result = apiInstance.getBatchStatisticsCount(batchId, type, tenantIdIn, withoutTenantId, suspended);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling BatchApi#getBatchStatisticsCount");
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
 **batchId** | **String**| Filter by batch id. | [optional]
 **type** | **String**| Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of &#x60;Strings&#x60;. A batch matches if it has one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| A &#x60;Boolean&#x60; value which indicates whether only active or suspended batches should be included. When the value is set to &#x60;true&#x60;, only suspended batches will be returned and when the value is set to &#x60;false&#x60;, only active batches will be returned. | [optional]

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


## getBatches

> List&lt;BatchDto&gt; getBatches(sortBy, sortOrder, firstResult, maxResults, batchId, type, tenantIdIn, withoutTenantId, suspended)

Get List

Queries for batches that fulfill given parameters. Parameters may be the properties of batches, such as the id or type. The size of the result set can be retrieved by using the [Get Batch Count](https://docs.camunda.org/manual/7.16/reference/rest/batch/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.BatchApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        BatchApi apiInstance = new BatchApi(defaultClient);
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String batchId = "batchId_example"; // String | Filter by batch id.
        String type = "type_example"; // String | Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of `Strings`. A batch matches if it has one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include batches which belong to no tenant. Value can effectively only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | A `Boolean` value which indicates whether only active or suspended batches should be included. When the value is set to `true`, only suspended batches will be returned and when the value is set to `false`, only active batches will be returned.
        try {
            List<BatchDto> result = apiInstance.getBatches(sortBy, sortOrder, firstResult, maxResults, batchId, type, tenantIdIn, withoutTenantId, suspended);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling BatchApi#getBatches");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: batchId, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **batchId** | **String**| Filter by batch id. | [optional]
 **type** | **String**| Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of &#x60;Strings&#x60;. A batch matches if it has one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| A &#x60;Boolean&#x60; value which indicates whether only active or suspended batches should be included. When the value is set to &#x60;true&#x60;, only suspended batches will be returned and when the value is set to &#x60;false&#x60;, only active batches will be returned. | [optional]

### Return type

[**List&lt;BatchDto&gt;**](BatchDto.md)

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


## getBatchesCount

> CountResultDto getBatchesCount(batchId, type, tenantIdIn, withoutTenantId, suspended)

Get List Count

Requests the number of batches that fulfill the query criteria. Takes the same filtering parameters as the [Get Batches](https://docs.camunda.org/manual/7.16/reference/rest/batch/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.BatchApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        BatchApi apiInstance = new BatchApi(defaultClient);
        String batchId = "batchId_example"; // String | Filter by batch id.
        String type = "type_example"; // String | Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of `Strings`. A batch matches if it has one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include batches which belong to no tenant. Value can effectively only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | A `Boolean` value which indicates whether only active or suspended batches should be included. When the value is set to `true`, only suspended batches will be returned and when the value is set to `false`, only active batches will be returned.
        try {
            CountResultDto result = apiInstance.getBatchesCount(batchId, type, tenantIdIn, withoutTenantId, suspended);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling BatchApi#getBatchesCount");
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
 **batchId** | **String**| Filter by batch id. | [optional]
 **type** | **String**| Filter by batch type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/batch/#creating-a-batch) for more information about batch types. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of &#x60;Strings&#x60;. A batch matches if it has one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include batches which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| A &#x60;Boolean&#x60; value which indicates whether only active or suspended batches should be included. When the value is set to &#x60;true&#x60;, only suspended batches will be returned and when the value is set to &#x60;false&#x60;, only active batches will be returned. | [optional]

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


## updateBatchSuspensionState

> updateBatchSuspensionState(id, suspensionStateDto)

Activate/Suspend

Activates or suspends a batch by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.BatchApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        BatchApi apiInstance = new BatchApi(defaultClient);
        String id = "id_example"; // String | The id of the batch to activate or suspend.
        SuspensionStateDto suspensionStateDto = new SuspensionStateDto(); // SuspensionStateDto | 
        try {
            apiInstance.updateBatchSuspensionState(id, suspensionStateDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling BatchApi#updateBatchSuspensionState");
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
 **id** | **String**| The id of the batch to activate or suspend. |
 **suspensionStateDto** | [**SuspensionStateDto**](SuspensionStateDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. |  -  |
| **400** | Returned if the batch cannot be suspended or activated. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

