# TaskIdentityLinkApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addIdentityLink**](TaskIdentityLinkApi.md#addIdentityLink) | **POST** /task/{id}/identity-links | 
[**deleteIdentityLink**](TaskIdentityLinkApi.md#deleteIdentityLink) | **POST** /task/{id}/identity-links/delete | 
[**getIdentityLinks**](TaskIdentityLinkApi.md#getIdentityLinks) | **GET** /task/{id}/identity-links | 


<a name="addIdentityLink"></a>
# **addIdentityLink**
> addIdentityLink(id, identityLinkDto)



Adds an identity link to a task by id. Can be used to link any user or group to a task and specify a relation.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskIdentityLinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskIdentityLinkApi apiInstance = new TaskIdentityLinkApi(defaultClient);
    String id = "id_example"; // String | The id of the task to add a link to.
    IdentityLinkDto identityLinkDto = {"groupId":"aNewGroupId","type":"candidate"}; // IdentityLinkDto | 
    try {
      apiInstance.addIdentityLink(id, identityLinkDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskIdentityLinkApi#addIdentityLink");
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
 **id** | **String**| The id of the task to add a link to. |
 **identityLinkDto** | [**IdentityLinkDto**](IdentityLinkDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="deleteIdentityLink"></a>
# **deleteIdentityLink**
> deleteIdentityLink(id, identityLinkDto)



Removes an identity link from a task by id

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskIdentityLinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskIdentityLinkApi apiInstance = new TaskIdentityLinkApi(defaultClient);
    String id = "id_example"; // String | The id of the task to remove a link from.
    IdentityLinkDto identityLinkDto = {"groupId":"theOldGroupId","type":"candidate"}; // IdentityLinkDto | 
    try {
      apiInstance.deleteIdentityLink(id, identityLinkDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskIdentityLinkApi#deleteIdentityLink");
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
 **id** | **String**| The id of the task to remove a link from. |
 **identityLinkDto** | [**IdentityLinkDto**](IdentityLinkDto.md)|  | [optional]

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
**204** | Request successful. |  -  |
**400** | Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getIdentityLinks"></a>
# **getIdentityLinks**
> List&lt;IdentityLinkDto&gt; getIdentityLinks(id, type)



Gets the identity links for a task by id, which are the users and groups that are in *some* relation to it (including assignee and owner).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskIdentityLinkApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskIdentityLinkApi apiInstance = new TaskIdentityLinkApi(defaultClient);
    String id = "id_example"; // String | The id of the task to retrieve the identity links for.
    String type = "type_example"; // String | Filter by the type of links to include.
    try {
      List<IdentityLinkDto> result = apiInstance.getIdentityLinks(id, type);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskIdentityLinkApi#getIdentityLinks");
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
 **id** | **String**| The id of the task to retrieve the identity links for. |
 **type** | **String**| Filter by the type of links to include. | [optional]

### Return type

[**List&lt;IdentityLinkDto&gt;**](IdentityLinkDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Task with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

