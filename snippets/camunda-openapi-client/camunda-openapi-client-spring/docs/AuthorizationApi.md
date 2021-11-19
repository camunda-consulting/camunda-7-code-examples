# AuthorizationApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**availableOperationsAuthorization**](AuthorizationApi.md#availableOperationsAuthorization) | **OPTIONS** /authorization | Authorization Resource Options
[**availableOperationsAuthorizationInstance**](AuthorizationApi.md#availableOperationsAuthorizationInstance) | **OPTIONS** /authorization/{id} | Authorization Resource Options
[**createAuthorization**](AuthorizationApi.md#createAuthorization) | **POST** /authorization/create | Create a New Authorization
[**deleteAuthorization**](AuthorizationApi.md#deleteAuthorization) | **DELETE** /authorization/{id} | Delete Authorization
[**getAuthorization**](AuthorizationApi.md#getAuthorization) | **GET** /authorization/{id} | Get Authorization
[**getAuthorizationCount**](AuthorizationApi.md#getAuthorizationCount) | **GET** /authorization/count | Get Authorization Count
[**isUserAuthorized**](AuthorizationApi.md#isUserAuthorized) | **GET** /authorization/check | Perform an Authorization Check
[**queryAuthorizations**](AuthorizationApi.md#queryAuthorizations) | **GET** /authorization | Get Authorizations
[**updateAuthorization**](AuthorizationApi.md#updateAuthorization) | **PUT** /authorization/{id} | Update an Authorization



## availableOperationsAuthorization

> ResourceOptionsDto availableOperationsAuthorization()

Authorization Resource Options

The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        try {
            ResourceOptionsDto result = apiInstance.availableOperationsAuthorization();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#availableOperationsAuthorization");
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


## availableOperationsAuthorizationInstance

> ResourceOptionsDto availableOperationsAuthorizationInstance(id)

Authorization Resource Options

The OPTIONS request allows you to check for the set of available operations that the currently authenticated user can perform on a given instance of the &#x60;/authorization&#x60; resource. Whether the user can perform an operation or not may depend on various factors, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        String id = "id_example"; // String | The id of the authorization to be retrieved.
        try {
            ResourceOptionsDto result = apiInstance.availableOperationsAuthorizationInstance(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#availableOperationsAuthorizationInstance");
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
 **id** | **String**| The id of the authorization to be retrieved. |

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


## createAuthorization

> AuthorizationDto createAuthorization(authorizationCreateDto)

Create a New Authorization

Creates a new authorization.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        AuthorizationCreateDto authorizationCreateDto = new AuthorizationCreateDto(); // AuthorizationCreateDto | 
        try {
            AuthorizationDto result = apiInstance.createAuthorization(authorizationCreateDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#createAuthorization");
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
 **authorizationCreateDto** | [**AuthorizationCreateDto**](AuthorizationCreateDto.md)|  | [optional]

### Return type

[**AuthorizationDto**](AuthorizationDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the properties in the request body are invalid, for example if a permission parameter is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **403** | The authenticated user is unauthorized to create an instance of this resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The authorization could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## deleteAuthorization

> deleteAuthorization(id)

Delete Authorization

Deletes an authorization by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        String id = "id_example"; // String | The id of the authorization to be deleted.
        try {
            apiInstance.deleteAuthorization(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#deleteAuthorization");
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
 **id** | **String**| The id of the authorization to be deleted. |

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
| **403** | If the authenticated user is unauthorized to delete the resource instance. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Authorization cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getAuthorization

> AuthorizationDto getAuthorization(id)

Get Authorization

Retrieves an authorization by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        String id = "id_example"; // String | The id of the authorization to be retrieved.
        try {
            AuthorizationDto result = apiInstance.getAuthorization(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#getAuthorization");
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
 **id** | **String**| The id of the authorization to be retrieved. |

### Return type

[**AuthorizationDto**](AuthorizationDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Authorization with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getAuthorizationCount

> CountResultDto getAuthorizationCount(id, type, userIdIn, groupIdIn, resourceType, resourceId)

Get Authorization Count

Queries for authorizations using a list of parameters and retrieves the count.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        String id = "id_example"; // String | Filter by the id of the authorization.
        Integer type = 56; // Integer | Filter by authorization type. (0=global, 1=grant, 2=revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types.
        String userIdIn = "userIdIn_example"; // String | Filter by a comma-separated list of userIds.
        String groupIdIn = "groupIdIn_example"; // String | Filter by a comma-separated list of groupIds.
        Integer resourceType = 56; // Integer | Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types.
        String resourceId = "resourceId_example"; // String | Filter by resource id.
        try {
            CountResultDto result = apiInstance.getAuthorizationCount(id, type, userIdIn, groupIdIn, resourceType, resourceId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#getAuthorizationCount");
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
 **id** | **String**| Filter by the id of the authorization. | [optional]
 **type** | **Integer**| Filter by authorization type. (0&#x3D;global, 1&#x3D;grant, 2&#x3D;revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types. | [optional]
 **userIdIn** | **String**| Filter by a comma-separated list of userIds. | [optional]
 **groupIdIn** | **String**| Filter by a comma-separated list of groupIds. | [optional]
 **resourceType** | **Integer**| Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. | [optional]
 **resourceId** | **String**| Filter by resource id. | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## isUserAuthorized

> AuthorizationCheckResultDto isUserAuthorized(permissionName, resourceName, resourceType, resourceId, userId)

Perform an Authorization Check

Performs an authorization check for the currently authenticated user.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        String permissionName = "permissionName_example"; // String | String value representing the permission name to check for.
        String resourceName = "resourceName_example"; // String | String value for the name of the resource to check permissions for.
        Integer resourceType = 56; // Integer | An integer representing the resource type to check permissions for. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types.
        String resourceId = "resourceId_example"; // String | The id of the resource to check permissions for. If left blank, a check for global permissions on the resource is performed.
        String userId = "userId_example"; // String | The id of the user to check permissions for. The currently authenticated user must have a READ permission for the Authorization resource. If `userId` is blank, a check for the currently authenticated user is performed.
        try {
            AuthorizationCheckResultDto result = apiInstance.isUserAuthorized(permissionName, resourceName, resourceType, resourceId, userId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#isUserAuthorized");
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
 **permissionName** | **String**| String value representing the permission name to check for. |
 **resourceName** | **String**| String value for the name of the resource to check permissions for. |
 **resourceType** | **Integer**| An integer representing the resource type to check permissions for. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. |
 **resourceId** | **String**| The id of the resource to check permissions for. If left blank, a check for global permissions on the resource is performed. | [optional]
 **userId** | **String**| The id of the user to check permissions for. The currently authenticated user must have a READ permission for the Authorization resource. If &#x60;userId&#x60; is blank, a check for the currently authenticated user is performed. | [optional]

### Return type

[**AuthorizationCheckResultDto**](AuthorizationCheckResultDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a permission parameterName is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **401** | The user is not authenticated. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **403** | When a &#x60;userId&#x60; is passed and the user does not possess a READ permission for the Authorization resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Authorization with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryAuthorizations

> List&lt;AuthorizationDto&gt; queryAuthorizations(id, type, userIdIn, groupIdIn, resourceType, resourceId, sortBy, sortOrder, firstResult, maxResults)

Get Authorizations

Queries for a list of authorizations using a list of parameters. The size of the result set can be retrieved by using the [Get Authorization Count](https://docs.camunda.org/manual/7.16/reference/rest/authorization/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        String id = "id_example"; // String | Filter by the id of the authorization.
        Integer type = 56; // Integer | Filter by authorization type. (0=global, 1=grant, 2=revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types.
        String userIdIn = "userIdIn_example"; // String | Filter by a comma-separated list of userIds.
        String groupIdIn = "groupIdIn_example"; // String | Filter by a comma-separated list of groupIds.
        Integer resourceType = 56; // Integer | Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types.
        String resourceId = "resourceId_example"; // String | Filter by resource id.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<AuthorizationDto> result = apiInstance.queryAuthorizations(id, type, userIdIn, groupIdIn, resourceType, resourceId, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#queryAuthorizations");
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
 **id** | **String**| Filter by the id of the authorization. | [optional]
 **type** | **Integer**| Filter by authorization type. (0&#x3D;global, 1&#x3D;grant, 2&#x3D;revoke). See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#authorization-type) for more information about authorization types. | [optional]
 **userIdIn** | **String**| Filter by a comma-separated list of userIds. | [optional]
 **groupIdIn** | **String**| Filter by a comma-separated list of groupIds. | [optional]
 **resourceType** | **Integer**| Filter by an integer representation of the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. | [optional]
 **resourceId** | **String**| Filter by resource id. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: resourceType, resourceId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;AuthorizationDto&gt;**](AuthorizationDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateAuthorization

> updateAuthorization(id, authorizationUpdateDto)

Update an Authorization

Updates an authorization by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.AuthorizationApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        AuthorizationApi apiInstance = new AuthorizationApi(defaultClient);
        String id = "id_example"; // String | The id of the authorization to be updated.
        AuthorizationUpdateDto authorizationUpdateDto = new AuthorizationUpdateDto(); // AuthorizationUpdateDto | 
        try {
            apiInstance.updateAuthorization(id, authorizationUpdateDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling AuthorizationApi#updateAuthorization");
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
 **id** | **String**| The id of the authorization to be updated. |
 **authorizationUpdateDto** | [**AuthorizationUpdateDto**](AuthorizationUpdateDto.md)|  | [optional]

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
| **400** | Returned if some of the properties in the request body are invalid, for example if a permission parameter is not valid for the provided resourceType. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **403** | The authenticated user is unauthorized to update this resource. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | The authorization with the requested Id cannot be found. |  -  |
| **500** | The authorization could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

