# FilterApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createFilter**](FilterApi.md#createFilter) | **POST** /filter/create | Create Filter
[**deleteFilter**](FilterApi.md#deleteFilter) | **DELETE** /filter/{id} | Delete Filter
[**executeFilterCount**](FilterApi.md#executeFilterCount) | **GET** /filter/{id}/count | Execute Filter Count
[**executeFilterList**](FilterApi.md#executeFilterList) | **GET** /filter/{id}/list | Execute Filter List
[**executeFilterSingleResult**](FilterApi.md#executeFilterSingleResult) | **GET** /filter/{id}/singleResult | Execute Filter Single Result
[**filterResourceOptions**](FilterApi.md#filterResourceOptions) | **OPTIONS** /filter | Filter Resource Options
[**filterResourceOptionsSingle**](FilterApi.md#filterResourceOptionsSingle) | **OPTIONS** /filter/{id} | Filter Resource Options
[**getFilterCount**](FilterApi.md#getFilterCount) | **GET** /filter/count | Get Filter Count
[**getFilterList**](FilterApi.md#getFilterList) | **GET** /filter | Get Filters
[**getSingleFilter**](FilterApi.md#getSingleFilter) | **GET** /filter/{id} | Get Single Filter
[**postExecuteFilterCount**](FilterApi.md#postExecuteFilterCount) | **POST** /filter/{id}/count | Execute Filter Count (POST)
[**postExecuteFilterList**](FilterApi.md#postExecuteFilterList) | **POST** /filter/{id}/list | Execute Filter List (POST)
[**postExecuteFilterSingleResult**](FilterApi.md#postExecuteFilterSingleResult) | **POST** /filter/{id}/singleResult | Execute Filter Single Result (POST)
[**updateFilter**](FilterApi.md#updateFilter) | **PUT** /filter/{id} | Update Filter



## createFilter

> FilterDto createFilter(createFilterDto)

Create Filter

Creates a new filter.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        CreateFilterDto createFilterDto = new CreateFilterDto(); // CreateFilterDto | 
        try {
            FilterDto result = apiInstance.createFilter(createFilterDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#createFilter");
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
 **createFilterDto** | [**CreateFilterDto**](CreateFilterDto.md)|  | [optional]

### Return type

[**FilterDto**](FilterDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** |  Filter was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **403** |  The authenticated user is unauthorized to create a new filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## deleteFilter

> deleteFilter(id)

Delete Filter

Deletes a filter by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to be deleted.
        try {
            apiInstance.deleteFilter(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#deleteFilter");
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
 **id** | **String**| The id of the filter to be deleted. |

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
| **204** | Request successful. This method returns no content. |  -  |
| **403** |  The authenticated user is unauthorized to delete this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## executeFilterCount

> CountResultDto executeFilterCount(id)

Execute Filter Count

Executes the saved query of the filter by id and returns the count.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to execute.
        try {
            CountResultDto result = apiInstance.executeFilterCount(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#executeFilterCount");
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
 **id** | **String**| The id of the filter to execute. |

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
| **403** |  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## executeFilterList

> List&lt;Object&gt; executeFilterList(id, firstResult, maxResults)

Execute Filter List

Executes the saved query of the filter by id and returns the result list.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to execute.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<Object> result = apiInstance.executeFilterList(id, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#executeFilterList");
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
 **id** | **String**| The id of the filter to execute. |
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

**List&lt;Object&gt;**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. A JSON array containing JSON objects corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine. |  -  |
| **403** |  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## executeFilterSingleResult

> Object executeFilterSingleResult(id)

Execute Filter Single Result

Executes the saved query of the filter by id and returns the single result.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to execute.
        try {
            Object result = apiInstance.executeFilterSingleResult(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#executeFilterSingleResult");
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
 **id** | **String**| The id of the filter to execute. |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. A JSON object corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine. |  -  |
| **204** | Request successful, but the result was empty. This method returns no content. |  -  |
| **400** |  The executed filter returned more than one single result. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **403** |  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## filterResourceOptions

> ResourceOptionsDto filterResourceOptions()

Filter Resource Options

The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        try {
            ResourceOptionsDto result = apiInstance.filterResourceOptions();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#filterResourceOptions");
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

[**ResourceOptionsDto**](ResourceOptionsDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |


## filterResourceOptionsSingle

> ResourceOptionsDto filterResourceOptionsSingle(id)

Filter Resource Options

The OPTIONS request allows you to check for the set of available operations  that the currently authenticated user can perform on the &#x60;/filter&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to be checked.
        try {
            ResourceOptionsDto result = apiInstance.filterResourceOptionsSingle(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#filterResourceOptionsSingle");
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
 **id** | **String**| The id of the filter to be checked. |

### Return type

[**ResourceOptionsDto**](ResourceOptionsDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |


## getFilterCount

> CountResultDto getFilterCount(filterId, resourceType, name, nameLike, owner)

Get Filter Count

Retrieves the number of filters that fulfill a provided query. Corresponds to the size of the result set when using the  [Get Filters](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String filterId = "filterId_example"; // String | Filter by the id of the filter.
        String resourceType = "resourceType_example"; // String | Filter by the resource type of the filter, e.g., `Task`.
        String name = "name_example"; // String | Filter by the name of the filter.
        String nameLike = "nameLike_example"; // String | Filter by the name that the parameter is a substring of.
        String owner = "owner_example"; // String | Filter by the user id of the owner of the filter.
        try {
            CountResultDto result = apiInstance.getFilterCount(filterId, resourceType, name, nameLike, owner);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#getFilterCount");
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
 **filterId** | **String**| Filter by the id of the filter. | [optional]
 **resourceType** | **String**| Filter by the resource type of the filter, e.g., &#x60;Task&#x60;. | [optional]
 **name** | **String**| Filter by the name of the filter. | [optional]
 **nameLike** | **String**| Filter by the name that the parameter is a substring of. | [optional]
 **owner** | **String**| Filter by the user id of the owner of the filter. | [optional]

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
| **400** |  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60;parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## getFilterList

> List&lt;FilterDto&gt; getFilterList(filterId, resourceType, name, nameLike, owner, itemCount, sortBy, sortOrder, firstResult, maxResults)

Get Filters

Queries for a list of filters using a list of parameters. The size of the result set can be retrieved by using the [Get Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String filterId = "filterId_example"; // String | Filter by the id of the filter.
        String resourceType = "resourceType_example"; // String | Filter by the resource type of the filter, e.g., `Task`.
        String name = "name_example"; // String | Filter by the name of the filter.
        String nameLike = "nameLike_example"; // String | Filter by the name that the parameter is a substring of.
        String owner = "owner_example"; // String | Filter by the user id of the owner of the filter.
        Boolean itemCount = true; // Boolean | If set to `true`, each filter result will contain an `itemCount` property with the number of items matched by the filter itself.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<FilterDto> result = apiInstance.getFilterList(filterId, resourceType, name, nameLike, owner, itemCount, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#getFilterList");
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
 **filterId** | **String**| Filter by the id of the filter. | [optional]
 **resourceType** | **String**| Filter by the resource type of the filter, e.g., &#x60;Task&#x60;. | [optional]
 **name** | **String**| Filter by the name of the filter. | [optional]
 **nameLike** | **String**| Filter by the name that the parameter is a substring of. | [optional]
 **owner** | **String**| Filter by the user id of the owner of the filter. | [optional]
 **itemCount** | **Boolean**| If set to &#x60;true&#x60;, each filter result will contain an &#x60;itemCount&#x60; property with the number of items matched by the filter itself. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: filterId, resourceType, name, owner]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;FilterDto&gt;**](FilterDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** |  Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## getSingleFilter

> FilterDto getSingleFilter(id, itemCount)

Get Single Filter

Retrieves a single filter by id, according to the &#x60;Filter&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to be retrieved.
        Boolean itemCount = true; // Boolean | If set to `true`, each filter result will contain an `itemCount` property with the number of items matched by the filter itself.
        try {
            FilterDto result = apiInstance.getSingleFilter(id, itemCount);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#getSingleFilter");
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
 **id** | **String**| The id of the filter to be retrieved. |
 **itemCount** | **Boolean**| If set to &#x60;true&#x60;, each filter result will contain an &#x60;itemCount&#x60; property with the number of items matched by the filter itself. | [optional]

### Return type

[**FilterDto**](FilterDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **403** | The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## postExecuteFilterCount

> CountResultDto postExecuteFilterCount(id, body)

Execute Filter Count (POST)

Executes the saved query of the filter by id and returns the count. This method is slightly more powerful then the [Get Execute Filter Count](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-count/)  method because it allows to extend the saved query of the filter.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to execute.
        Object body = {"assignee":"jonny1","taskDefinitionKey":"aTaskKey"}; // Object | A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource.
        try {
            CountResultDto result = apiInstance.postExecuteFilterCount(id, body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#postExecuteFilterCount");
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
 **id** | **String**| The id of the filter to execute. |
 **body** | **Object**| A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. | [optional]

### Return type

[**CountResultDto**](CountResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** |  The extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **403** |  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## postExecuteFilterList

> List&lt;Object&gt; postExecuteFilterList(id, firstResult, maxResults, body)

Execute Filter List (POST)

Executes the saved query of the filter by id and returns the result list. This method is slightly more powerful then the  [Get Execute FilterList](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-list/) method because it allows to extend the saved query of the filter.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to execute.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        Object body = {"assignee":"jonny1","taskDefinitionKey":"aTaskKey"}; // Object | A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource.
        try {
            List<Object> result = apiInstance.postExecuteFilterList(id, firstResult, maxResults, body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#postExecuteFilterList");
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
 **id** | **String**| The id of the filter to execute. |
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **body** | **Object**| A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. | [optional]

### Return type

**List&lt;Object&gt;**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. A JSON array containing JSON objects corresponding to the matching entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible to specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine. |  -  |
| **400** |  The extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **403** |  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## postExecuteFilterSingleResult

> Object postExecuteFilterSingleResult(id, body)

Execute Filter Single Result (POST)

Executes the saved query of the filter by id and returns the single result. This method is slightly more powerful then the [Get Execute Filter Single Result](https://docs.camunda.org/manual/7.16/reference/rest/filter/get-execute-single-result/) method because it allows to extend the saved query of the filter.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to execute.
        Object body = {"assignee":"jonny1","taskDefinitionKey":"aTaskKey"}; // Object | A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource.
        try {
            Object result = apiInstance.postExecuteFilterSingleResult(id, body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#postExecuteFilterSingleResult");
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
 **id** | **String**| The id of the filter to execute. |
 **body** | **Object**| A JSON object which corresponds to the type of the saved query of the filter, i.e., if the resource type of the filter is Task the body should form a valid task query corresponding to the Task resource. | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. A JSON object corresponding to the corresponding entity interface in the engine. This depends on the saved query in the filter. Therefore it is not possible specify a generic result format, i.e., if the resource type of the filter is Task the result will correspond to the Task interface in the engine. |  -  |
| **204** | Request successful, but the result was empty. This method returns no content. |  -  |
| **400** |  The executed filter returned more than one single result or the extending query was invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **403** |  The authenticated user is unauthorized to read this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## updateFilter

> updateFilter(id, createFilterDto)

Update Filter

Updates an existing filter.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.FilterApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        FilterApi apiInstance = new FilterApi(defaultClient);
        String id = "id_example"; // String | The id of the filter to be updated.
        CreateFilterDto createFilterDto = new CreateFilterDto(); // CreateFilterDto | 
        try {
            apiInstance.updateFilter(id, createFilterDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling FilterApi#updateFilter");
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
 **id** | **String**| The id of the filter to be updated. |
 **createFilterDto** | [**CreateFilterDto**](CreateFilterDto.md)|  | [optional]

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
| **204** | Request successful. This method returns no content. |  -  |
| **400** |  Filter was invalid. See [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **403** |  The authenticated user is unauthorized to update this filter. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |
| **404** |  Filter cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |

