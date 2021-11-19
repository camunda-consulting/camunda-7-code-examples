# GroupApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**availableGroupInstanceOperations**](GroupApi.md#availableGroupInstanceOperations) | **OPTIONS** /group/{id} | Group Resource Instance Options
[**availableGroupMembersOperations**](GroupApi.md#availableGroupMembersOperations) | **OPTIONS** /group/{id}/members | Group Membership Resource Options
[**availableGroupOperations**](GroupApi.md#availableGroupOperations) | **OPTIONS** /group | Group Resource Options
[**createGroup**](GroupApi.md#createGroup) | **POST** /group/create | Create Group
[**createGroupMember**](GroupApi.md#createGroupMember) | **PUT** /group/{id}/members/{userId} | Create Group Member
[**deleteGroup**](GroupApi.md#deleteGroup) | **DELETE** /group/{id} | Delete Group
[**deleteGroupMember**](GroupApi.md#deleteGroupMember) | **DELETE** /group/{id}/members/{userId} | Delete a Group Member
[**getGroup**](GroupApi.md#getGroup) | **GET** /group/{id} | Get Group
[**getGroupCount**](GroupApi.md#getGroupCount) | **GET** /group/count | Get List Count
[**getQueryGroups**](GroupApi.md#getQueryGroups) | **GET** /group | Get List
[**postQueryGroups**](GroupApi.md#postQueryGroups) | **POST** /group | Get List (POST)
[**queryGroupCount**](GroupApi.md#queryGroupCount) | **POST** /group/count | Get List Count (POST)
[**updateGroup**](GroupApi.md#updateGroup) | **PUT** /group/{id} | Update Group



## availableGroupInstanceOperations

> ResourceOptionsDto availableGroupInstanceOperations(id)

Group Resource Instance Options

The &#x60;/group&#x60; resource supports two custom OPTIONS requests, one for the resource as such and this one for individual group instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/group/{id}&#x60; resource instance. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | The id of the group.
        try {
            ResourceOptionsDto result = apiInstance.availableGroupInstanceOperations(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#availableGroupInstanceOperations");
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
 **id** | **String**| The id of the group. |

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


## availableGroupMembersOperations

> ResourceOptionsDto availableGroupMembersOperations(id)

Group Membership Resource Options

The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | The id of the group.
        try {
            ResourceOptionsDto result = apiInstance.availableGroupMembersOperations(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#availableGroupMembersOperations");
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
 **id** | **String**| The id of the group. |

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


## availableGroupOperations

> ResourceOptionsDto availableGroupOperations()

Group Resource Options

The &#x60;/group&#x60; resource supports two custom OPTIONS requests, this one for the resource as such and one for individual group instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/group&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        try {
            ResourceOptionsDto result = apiInstance.availableGroupOperations();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#availableGroupOperations");
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


## createGroup

> createGroup(groupDto)

Create Group

Creates a new group.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        GroupDto groupDto = new GroupDto(); // GroupDto | 
        try {
            apiInstance.createGroup(groupDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#createGroup");
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
 **groupDto** | [**GroupDto**](GroupDto.md)|  | [optional]

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
| **403** | Identity service is read-only (Cannot modify users / groups / memberships). |  -  |
| **500** | The group could not be created due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## createGroupMember

> createGroupMember(id, userId)

Create Group Member

Adds a member to a group.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | The id of the group.
        String userId = "userId_example"; // String | The id of user to add to the group.
        try {
            apiInstance.createGroupMember(id, userId);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#createGroupMember");
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
 **id** | **String**| The id of the group. |
 **userId** | **String**| The id of user to add to the group. |

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
| **403** | Identity service is read-only (Cannot modify users / groups / memberships). |  -  |
| **500** | In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## deleteGroup

> deleteGroup(id)

Delete Group

Deletes a group by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | The id of the group to be deleted.
        try {
            apiInstance.deleteGroup(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#deleteGroup");
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
 **id** | **String**| The id of the group to be deleted. |

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
| **403** | Identity service is read-only (Cannot modify users / groups / memberships). |  -  |
| **404** | Group cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## deleteGroupMember

> deleteGroupMember(id, userId)

Delete a Group Member

Removes a member from a group.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | The id of the group.
        String userId = "userId_example"; // String | The id of user to remove from the group.
        try {
            apiInstance.deleteGroupMember(id, userId);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#deleteGroupMember");
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
 **id** | **String**| The id of the group. |
 **userId** | **String**| The id of user to remove from the group. |

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
| **403** | Identity service is read-only (Cannot modify users / groups / memberships). |  -  |
| **500** | In case an error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getGroup

> GroupDto getGroup(id)

Get Group

Retrieves a group by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | The id of the group to be retrieved.
        try {
            GroupDto result = apiInstance.getGroup(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#getGroup");
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
 **id** | **String**| The id of the group to be retrieved. |

### Return type

[**GroupDto**](GroupDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Group with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getGroupCount

> CountResultDto getGroupCount(id, idIn, name, nameLike, type, member, memberOfTenant)

Get List Count

Queries for groups using a list of parameters and retrieves the count.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | Filter by the id of the group.
        String idIn = "idIn_example"; // String | Filter by a comma seperated list of group ids.
        String name = "name_example"; // String | Filter by the name of the group.
        String nameLike = "nameLike_example"; // String | Filter by the name that the parameter is a substring of.
        String type = "type_example"; // String | Filter by the type of the group.
        String member = "member_example"; // String | Only retrieve groups where the given user id is a member of.
        String memberOfTenant = "memberOfTenant_example"; // String | Only retrieve groups which are members of the given tenant.
        try {
            CountResultDto result = apiInstance.getGroupCount(id, idIn, name, nameLike, type, member, memberOfTenant);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#getGroupCount");
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
 **id** | **String**| Filter by the id of the group. | [optional]
 **idIn** | **String**| Filter by a comma seperated list of group ids. | [optional]
 **name** | **String**| Filter by the name of the group. | [optional]
 **nameLike** | **String**| Filter by the name that the parameter is a substring of. | [optional]
 **type** | **String**| Filter by the type of the group. | [optional]
 **member** | **String**| Only retrieve groups where the given user id is a member of. | [optional]
 **memberOfTenant** | **String**| Only retrieve groups which are members of the given tenant. | [optional]

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


## getQueryGroups

> List&lt;GroupDto&gt; getQueryGroups(sortBy, sortOrder, firstResult, maxResults, id, idIn, name, nameLike, type, member, memberOfTenant)

Get List

Queries for a list of groups using a list of parameters. The size of the result set can be retrieved by using the [Get Group Count](https://docs.camunda.org/manual/7.16/reference/rest/group/get-query-count) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String id = "id_example"; // String | Filter by the id of the group.
        String idIn = "idIn_example"; // String | Filter by a comma seperated list of group ids.
        String name = "name_example"; // String | Filter by the name of the group.
        String nameLike = "nameLike_example"; // String | Filter by the name that the parameter is a substring of.
        String type = "type_example"; // String | Filter by the type of the group.
        String member = "member_example"; // String | Only retrieve groups where the given user id is a member of.
        String memberOfTenant = "memberOfTenant_example"; // String | Only retrieve groups which are members of the given tenant.
        try {
            List<GroupDto> result = apiInstance.getQueryGroups(sortBy, sortOrder, firstResult, maxResults, id, idIn, name, nameLike, type, member, memberOfTenant);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#getQueryGroups");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: id, name, type]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **id** | **String**| Filter by the id of the group. | [optional]
 **idIn** | **String**| Filter by a comma seperated list of group ids. | [optional]
 **name** | **String**| Filter by the name of the group. | [optional]
 **nameLike** | **String**| Filter by the name that the parameter is a substring of. | [optional]
 **type** | **String**| Filter by the type of the group. | [optional]
 **member** | **String**| Only retrieve groups where the given user id is a member of. | [optional]
 **memberOfTenant** | **String**| Only retrieve groups which are members of the given tenant. | [optional]

### Return type

[**List&lt;GroupDto&gt;**](GroupDto.md)

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


## postQueryGroups

> List&lt;GroupDto&gt; postQueryGroups(firstResult, maxResults, groupQueryDto)

Get List (POST)

Queries for a list of groups using a list of parameters. The size of the result set can be retrieved by using the [Get Group Count (POST)](https://docs.camunda.org/manual/7.16/reference/rest/group/post-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        GroupQueryDto groupQueryDto = new GroupQueryDto(); // GroupQueryDto | 
        try {
            List<GroupDto> result = apiInstance.postQueryGroups(firstResult, maxResults, groupQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#postQueryGroups");
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
 **groupQueryDto** | [**GroupQueryDto**](GroupQueryDto.md)|  | [optional]

### Return type

[**List&lt;GroupDto&gt;**](GroupDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60; is specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryGroupCount

> CountResultDto queryGroupCount(groupQueryDto)

Get List Count (POST)

Queries for groups using a list of parameters and retrieves the count.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        GroupQueryDto groupQueryDto = new GroupQueryDto(); // GroupQueryDto | 
        try {
            CountResultDto result = apiInstance.queryGroupCount(groupQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#queryGroupCount");
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
 **groupQueryDto** | [**GroupQueryDto**](GroupQueryDto.md)|  | [optional]

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
| **400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateGroup

> updateGroup(id, groupDto)

Update Group

Updates a given group by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.GroupApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        GroupApi apiInstance = new GroupApi(defaultClient);
        String id = "id_example"; // String | The id of the group.
        GroupDto groupDto = new GroupDto(); // GroupDto | 
        try {
            apiInstance.updateGroup(id, groupDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling GroupApi#updateGroup");
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
 **id** | **String**| The id of the group. |
 **groupDto** | [**GroupDto**](GroupDto.md)|  | [optional]

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
| **204** | Request successful. No content. |  -  |
| **403** | Identity service is read-only (Cannot modify users / groups / memberships). |  -  |
| **404** | If the group with the requested Id cannot be found. |  -  |
| **500** | The group could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

