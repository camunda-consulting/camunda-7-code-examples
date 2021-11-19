# TenantApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**availableTenantGroupMembersOperations**](TenantApi.md#availableTenantGroupMembersOperations) | **OPTIONS** /tenant/{id}/group-members | Tenant Group Membership Resource Options
[**availableTenantInstanceOperations**](TenantApi.md#availableTenantInstanceOperations) | **OPTIONS** /tenant/{id} | Tenant Resource Options
[**availableTenantResourceOperations**](TenantApi.md#availableTenantResourceOperations) | **OPTIONS** /tenant | Tenant Resource Options
[**availableTenantUserMembersOperations**](TenantApi.md#availableTenantUserMembersOperations) | **OPTIONS** /tenant/{id}/user-members | Tenant User Membership Resource Options
[**createGroupMembership**](TenantApi.md#createGroupMembership) | **PUT** /tenant/{id}/group-members/{groupId} | Create Tenant Group Membership
[**createTenant**](TenantApi.md#createTenant) | **POST** /tenant/create | Create Tenant
[**createUserMembership**](TenantApi.md#createUserMembership) | **PUT** /tenant/{id}/user-members/{userId} | Create Tenant User Membership
[**deleteGroupMembership**](TenantApi.md#deleteGroupMembership) | **DELETE** /tenant/{id}/group-members/{groupId} | Create Tenant Group Membership
[**deleteTenant**](TenantApi.md#deleteTenant) | **DELETE** /tenant/{id} | Delete Tenant
[**deleteUserMembership**](TenantApi.md#deleteUserMembership) | **DELETE** /tenant/{id}/user-members/{userId} | Delete a Tenant User Membership
[**getTenant**](TenantApi.md#getTenant) | **GET** /tenant/{id} | Get Tenant
[**getTenantCount**](TenantApi.md#getTenantCount) | **GET** /tenant/count | Get Tenant Count
[**queryTenants**](TenantApi.md#queryTenants) | **GET** /tenant | Get Tenants
[**updateTenant**](TenantApi.md#updateTenant) | **PUT** /tenant/{id} | Update Tenant



## availableTenantGroupMembersOperations

> ResourceOptionsDto availableTenantGroupMembersOperations(id)

Tenant Group Membership Resource Options

The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant
        try {
            ResourceOptionsDto result = apiInstance.availableTenantGroupMembersOperations(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#availableTenantGroupMembersOperations");
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
 **id** | **String**| The id of the tenant |

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


## availableTenantInstanceOperations

> ResourceOptionsDto availableTenantInstanceOperations(id)

Tenant Resource Options

The &#x60;/tenant&#x60; resource supports two custom OPTIONS requests, one for the resource as such and this one for individual tenant instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/tenant/{id}&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant
        try {
            ResourceOptionsDto result = apiInstance.availableTenantInstanceOperations(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#availableTenantInstanceOperations");
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
 **id** | **String**| The id of the tenant |

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


## availableTenantResourceOperations

> ResourceOptionsDto availableTenantResourceOperations()

Tenant Resource Options

The &#x60;/tenant&#x60; resource supports two custom OPTIONS requests, this one for the resource as such and one for individual tenant instances. The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the &#x60;/tenant&#x60; resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        try {
            ResourceOptionsDto result = apiInstance.availableTenantResourceOperations();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#availableTenantResourceOperations");
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


## availableTenantUserMembersOperations

> ResourceOptionsDto availableTenantUserMembersOperations(id)

Tenant User Membership Resource Options

The OPTIONS request allows checking for the set of available operations that the currently authenticated user can perform on the resource. If the user can perform an operation or not may depend on various things, including the users authorizations to interact with this resource and the internal configuration of the process engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant
        try {
            ResourceOptionsDto result = apiInstance.availableTenantUserMembersOperations(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#availableTenantUserMembersOperations");
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
 **id** | **String**| The id of the tenant |

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


## createGroupMembership

> createGroupMembership(id, groupId)

Create Tenant Group Membership

Creates a membership between a tenant and a group.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant.
        String groupId = "groupId_example"; // String | The id of the group.
        try {
            apiInstance.createGroupMembership(id, groupId);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#createGroupMembership");
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
 **id** | **String**| The id of the tenant. |
 **groupId** | **String**| The id of the group. |

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
| **403** | Identity service is read-only. |  -  |
| **500** | In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## createTenant

> createTenant(tenantDto)

Create Tenant

Create a new tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        TenantDto tenantDto = new TenantDto(); // TenantDto | 
        try {
            apiInstance.createTenant(tenantDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#createTenant");
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
 **tenantDto** | [**TenantDto**](TenantDto.md)|  | [optional]

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
| **403** | Identity service is read-only. |  -  |
| **500** | The tenant could not be created due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## createUserMembership

> createUserMembership(id, userId)

Create Tenant User Membership

Creates a membership between a tenant and an user.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant.
        String userId = "userId_example"; // String | The id of the user.
        try {
            apiInstance.createUserMembership(id, userId);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#createUserMembership");
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
 **id** | **String**| The id of the tenant. |
 **userId** | **String**| The id of the user. |

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
| **403** | Identity service is read-only. |  -  |
| **500** | In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## deleteGroupMembership

> deleteGroupMembership(id, groupId)

Create Tenant Group Membership

Creates a membership between a tenant and a group.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant.
        String groupId = "groupId_example"; // String | The id of the group.
        try {
            apiInstance.deleteGroupMembership(id, groupId);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#deleteGroupMembership");
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
 **id** | **String**| The id of the tenant. |
 **groupId** | **String**| The id of the group. |

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
| **403** | Identity service is read-only. |  -  |
| **500** | In case an internal error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## deleteTenant

> deleteTenant(id)

Delete Tenant

Deletes a tenant by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant to be deleted.
        try {
            apiInstance.deleteTenant(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#deleteTenant");
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
 **id** | **String**| The id of the tenant to be deleted. |

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
| **403** | Identity service is read-only. |  -  |
| **404** | Tenant cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## deleteUserMembership

> deleteUserMembership(id, userId)

Delete a Tenant User Membership

Deletes a membership between a tenant and an user.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant.
        String userId = "userId_example"; // String | The id of the user.
        try {
            apiInstance.deleteUserMembership(id, userId);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#deleteUserMembership");
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
 **id** | **String**| The id of the tenant. |
 **userId** | **String**| The id of the user. |

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
| **403** | Identity service is read-only. |  -  |
| **500** | In case an error occurs. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getTenant

> TenantDto getTenant(id)

Get Tenant

Retrieves a tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant to be retrieved.
        try {
            TenantDto result = apiInstance.getTenant(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#getTenant");
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
 **id** | **String**| The id of the tenant to be retrieved. |

### Return type

[**TenantDto**](TenantDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Tenant with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getTenantCount

> CountResultDto getTenantCount(id, name, nameLike, userMember, groupMember, includingGroupsOfUser)

Get Tenant Count

Query for tenants using a list of parameters and retrieves the count.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | Filter by the id of the tenant.
        String name = "name_example"; // String | Filter by the name of the tenant.
        String nameLike = "nameLike_example"; // String | Filter by the name that the parameter is a substring of.
        String userMember = "userMember_example"; // String | Select only tenants where the given user is a member of.
        String groupMember = "groupMember_example"; // String | Select only tenants where the given group is a member of.
        Boolean includingGroupsOfUser = true; // Boolean | Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the `userMember` parameter. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getTenantCount(id, name, nameLike, userMember, groupMember, includingGroupsOfUser);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#getTenantCount");
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
 **id** | **String**| Filter by the id of the tenant. | [optional]
 **name** | **String**| Filter by the name of the tenant. | [optional]
 **nameLike** | **String**| Filter by the name that the parameter is a substring of. | [optional]
 **userMember** | **String**| Select only tenants where the given user is a member of. | [optional]
 **groupMember** | **String**| Select only tenants where the given group is a member of. | [optional]
 **includingGroupsOfUser** | **Boolean**| Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the &#x60;userMember&#x60; parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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


## queryTenants

> List&lt;TenantDto&gt; queryTenants(sortBy, sortOrder, firstResult, maxResults, id, name, nameLike, userMember, groupMember, includingGroupsOfUser)

Get Tenants

Query for a list of tenants using a list of parameters. The size of the result set can be retrieved by using the [Get Tenant Count](https://docs.camunda.org/manual/7.16/reference/rest/tenant/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String id = "id_example"; // String | Filter by the id of the tenant.
        String name = "name_example"; // String | Filter by the name of the tenant.
        String nameLike = "nameLike_example"; // String | Filter by the name that the parameter is a substring of.
        String userMember = "userMember_example"; // String | Select only tenants where the given user is a member of.
        String groupMember = "groupMember_example"; // String | Select only tenants where the given group is a member of.
        Boolean includingGroupsOfUser = true; // Boolean | Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the `userMember` parameter. Value may only be `true`, as `false` is the default behavior.
        try {
            List<TenantDto> result = apiInstance.queryTenants(sortBy, sortOrder, firstResult, maxResults, id, name, nameLike, userMember, groupMember, includingGroupsOfUser);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#queryTenants");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: id, name]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **id** | **String**| Filter by the id of the tenant. | [optional]
 **name** | **String**| Filter by the name of the tenant. | [optional]
 **nameLike** | **String**| Filter by the name that the parameter is a substring of. | [optional]
 **userMember** | **String**| Select only tenants where the given user is a member of. | [optional]
 **groupMember** | **String**| Select only tenants where the given group is a member of. | [optional]
 **includingGroupsOfUser** | **Boolean**| Select only tenants where the user or one of his groups is a member of. Can only be used in combination with the &#x60;userMember&#x60; parameter. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

### Return type

[**List&lt;TenantDto&gt;**](TenantDto.md)

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


## updateTenant

> updateTenant(id, tenantDto)

Update Tenant

Updates a given tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TenantApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        TenantApi apiInstance = new TenantApi(defaultClient);
        String id = "id_example"; // String | The id of the tenant.
        TenantDto tenantDto = new TenantDto(); // TenantDto | 
        try {
            apiInstance.updateTenant(id, tenantDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantApi#updateTenant");
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
 **id** | **String**| The id of the tenant. |
 **tenantDto** | [**TenantDto**](TenantDto.md)|  | [optional]

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
| **403** | Identity service is read-only. |  -  |
| **404** | If the tenant with the requested Id cannot be found. |  -  |
| **500** | The tenant could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

