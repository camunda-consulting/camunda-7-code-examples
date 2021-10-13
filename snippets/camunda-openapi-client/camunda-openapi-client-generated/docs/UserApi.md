# UserApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**availableOperations**](UserApi.md#availableOperations) | **OPTIONS** /user | Options
[**availableUserOperations**](UserApi.md#availableUserOperations) | **OPTIONS** /user/{id} | Options
[**createUser**](UserApi.md#createUser) | **POST** /user/create | Create
[**deleteUser**](UserApi.md#deleteUser) | **DELETE** /user/{id} | Delete
[**getUserCount**](UserApi.md#getUserCount) | **GET** /user/count | Get List Count
[**getUserProfile**](UserApi.md#getUserProfile) | **GET** /user/{id}/profile | Get Profile
[**getUsers**](UserApi.md#getUsers) | **GET** /user | Get List
[**unlockUser**](UserApi.md#unlockUser) | **POST** /user/{id}/unlock | Unlock User
[**updateCredentials**](UserApi.md#updateCredentials) | **PUT** /user/{id}/credentials | Update Credentials
[**updateProfile**](UserApi.md#updateProfile) | **PUT** /user/{id}/profile | Update User Profile



## availableOperations

> ResourceOptionsDto availableOperations()

Options

The &#x60;/user&#x60; resource supports two custom &#x60;OPTIONS&#x60; requests, one for the resource as such and one for individual user instances. The &#x60;OPTIONS&#x60; request allows checking for the set of available operations that the currently authenticated user can perform on the /user resource. If the user can perform an operation or not may depend on various things, including the user&#39;s authorizations to interact with this resource and the internal configuration of the process engine. &#x60;OPTIONS /user&#x60; returns available interactions on the resource.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        try {
            ResourceOptionsDto result = apiInstance.availableOperations();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#availableOperations");
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


## availableUserOperations

> ResourceOptionsDto availableUserOperations(id)

Options

The &#x60;/user&#x60; resource supports two custom &#x60;OPTIONS&#x60; requests, one for the resource as such and one for individual user instances. The &#x60;OPTIONS&#x60; request allows checking for the set of available operations that the currently authenticated user can perform on the /user resource. If the user can perform an operation or not may depend on various things, including the user&#39;s authorizations to interact with this resource and the internal configuration of the process engine. &#x60;OPTIONS /user/{id}&#x60; returns available interactions on a resource instance.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | The id of the user to be deleted.
        try {
            ResourceOptionsDto result = apiInstance.availableUserOperations(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#availableUserOperations");
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
 **id** | **String**| The id of the user to be deleted. |

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


## createUser

> createUser(userDto)

Create

Create a new user.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        UserDto userDto = new UserDto(); // UserDto | 
        try {
            apiInstance.createUser(userDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#createUser");
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
 **userDto** | [**UserDto**](UserDto.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Request successful. |  -  |


## deleteUser

> deleteUser(id)

Delete

Deletes a user by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | The id of the user to be deleted.
        try {
            apiInstance.deleteUser(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#deleteUser");
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
 **id** | **String**| The id of the user to be deleted. |

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
| **403** | Identity service is read-only (Cannot modify users / groups / memberships). |  -  |
| **404** | A Deployment with the provided id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getUserCount

> CountResultDto getUserCount(id, idIn, firstName, firstNameLike, lastName, lastNameLike, email, emailLike, memberOfGroup, memberOfTenant, potentialStarter)

Get List Count

Queries for the number of deployments that fulfill given parameters. Takes the same parameters as the [Get Users](https://docs.camunda.org/manual/7.16/reference/rest/user/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | Filter by user id
        String idIn = "idIn_example"; // String | Filter by a comma-separated list of user ids.
        String firstName = "firstName_example"; // String | Filter by the first name of the user. Exact match.
        String firstNameLike = "firstNameLike_example"; // String | Filter by the first name that the parameter is a substring of.
        String lastName = "lastName_example"; // String | Filter by the last name of the user. Exact match.
        String lastNameLike = "lastNameLike_example"; // String | Filter by the last name that the parameter is a substring of.
        String email = "email_example"; // String | Filter by the email of the user. Exact match.
        String emailLike = "emailLike_example"; // String | Filter by the email that the parameter is a substring of.
        String memberOfGroup = "memberOfGroup_example"; // String | Filter for users which are members of the given group.
        String memberOfTenant = "memberOfTenant_example"; // String | Filter for users which are members of the given tenant.
        String potentialStarter = "potentialStarter_example"; // String | Only select Users that are potential starter for the given process definition.
        try {
            CountResultDto result = apiInstance.getUserCount(id, idIn, firstName, firstNameLike, lastName, lastNameLike, email, emailLike, memberOfGroup, memberOfTenant, potentialStarter);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#getUserCount");
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
 **id** | **String**| Filter by user id | [optional]
 **idIn** | **String**| Filter by a comma-separated list of user ids. | [optional]
 **firstName** | **String**| Filter by the first name of the user. Exact match. | [optional]
 **firstNameLike** | **String**| Filter by the first name that the parameter is a substring of. | [optional]
 **lastName** | **String**| Filter by the last name of the user. Exact match. | [optional]
 **lastNameLike** | **String**| Filter by the last name that the parameter is a substring of. | [optional]
 **email** | **String**| Filter by the email of the user. Exact match. | [optional]
 **emailLike** | **String**| Filter by the email that the parameter is a substring of. | [optional]
 **memberOfGroup** | **String**| Filter for users which are members of the given group. | [optional]
 **memberOfTenant** | **String**| Filter for users which are members of the given tenant. | [optional]
 **potentialStarter** | **String**| Only select Users that are potential starter for the given process definition. | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example, if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getUserProfile

> UserProfileDto getUserProfile(id)

Get Profile

Retrieves a user&#39;s profile.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | The id of the user to retrieve.
        try {
            UserProfileDto result = apiInstance.getUserProfile(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#getUserProfile");
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
 **id** | **String**| The id of the user to retrieve. |

### Return type

[**UserProfileDto**](UserProfileDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Execution with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getUsers

> List&lt;UserProfileDto&gt; getUsers(id, idIn, firstName, firstNameLike, lastName, lastNameLike, email, emailLike, memberOfGroup, memberOfTenant, potentialStarter, sortBy, sortOrder, firstResult, maxResults)

Get List

Query for a list of users using a list of parameters. The size of the result set can be retrieved by using the Get User Count method. [Get User Count](https://docs.camunda.org/manual/7.16/reference/rest/user/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | Filter by user id
        String idIn = "idIn_example"; // String | Filter by a comma-separated list of user ids.
        String firstName = "firstName_example"; // String | Filter by the first name of the user. Exact match.
        String firstNameLike = "firstNameLike_example"; // String | Filter by the first name that the parameter is a substring of.
        String lastName = "lastName_example"; // String | Filter by the last name of the user. Exact match.
        String lastNameLike = "lastNameLike_example"; // String | Filter by the last name that the parameter is a substring of.
        String email = "email_example"; // String | Filter by the email of the user. Exact match.
        String emailLike = "emailLike_example"; // String | Filter by the email that the parameter is a substring of.
        String memberOfGroup = "memberOfGroup_example"; // String | Filter for users which are members of the given group.
        String memberOfTenant = "memberOfTenant_example"; // String | Filter for users which are members of the given tenant.
        String potentialStarter = "potentialStarter_example"; // String | Only select Users that are potential starter for the given process definition.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<UserProfileDto> result = apiInstance.getUsers(id, idIn, firstName, firstNameLike, lastName, lastNameLike, email, emailLike, memberOfGroup, memberOfTenant, potentialStarter, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#getUsers");
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
 **id** | **String**| Filter by user id | [optional]
 **idIn** | **String**| Filter by a comma-separated list of user ids. | [optional]
 **firstName** | **String**| Filter by the first name of the user. Exact match. | [optional]
 **firstNameLike** | **String**| Filter by the first name that the parameter is a substring of. | [optional]
 **lastName** | **String**| Filter by the last name of the user. Exact match. | [optional]
 **lastNameLike** | **String**| Filter by the last name that the parameter is a substring of. | [optional]
 **email** | **String**| Filter by the email of the user. Exact match. | [optional]
 **emailLike** | **String**| Filter by the email that the parameter is a substring of. | [optional]
 **memberOfGroup** | **String**| Filter for users which are members of the given group. | [optional]
 **memberOfTenant** | **String**| Filter for users which are members of the given tenant. | [optional]
 **potentialStarter** | **String**| Only select Users that are potential starter for the given process definition. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: userId, firstName, lastName, email]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;UserProfileDto&gt;**](UserProfileDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## unlockUser

> unlockUser(id)

Unlock User

Unlocks a user by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | The id of the user to be unlocked.
        try {
            apiInstance.unlockUser(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#unlockUser");
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
 **id** | **String**| The id of the user to be unlocked. |

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
| **403** | The user who performed the operation is not a Camunda admin user. |  -  |
| **404** | User cannot be found. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateCredentials

> updateCredentials(id, userCredentialsDto)

Update Credentials

Updates a user&#39;s credentials (password)

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | The id of the user to be updated.
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto(); // UserCredentialsDto | 
        try {
            apiInstance.updateCredentials(id, userCredentialsDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#updateCredentials");
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
 **id** | **String**| The id of the user to be updated. |
 **userCredentialsDto** | [**UserCredentialsDto**](UserCredentialsDto.md)|  | [optional]

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
| **400** | The authenticated user password does not match |  -  |
| **404** | If the corresponding user cannot be found |  -  |
| **500** | The user could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateProfile

> updateProfile(id, userProfileDto)

Update User Profile

Updates the profile information of an already existing user.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.UserApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        UserApi apiInstance = new UserApi(defaultClient);
        String id = "id_example"; // String | The id of the user.
        UserProfileDto userProfileDto = new UserProfileDto(); // UserProfileDto | 
        try {
            apiInstance.updateProfile(id, userProfileDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#updateProfile");
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
 **id** | **String**| The id of the user. |
 **userProfileDto** | [**UserProfileDto**](UserProfileDto.md)|  | [optional]

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
| **404** | If the user with the requested Id cannot be found. |  -  |
| **500** | The user could not be updated due to an internal server error. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

