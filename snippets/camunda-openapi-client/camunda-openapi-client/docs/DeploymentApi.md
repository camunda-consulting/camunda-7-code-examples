# DeploymentApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createDeployment**](DeploymentApi.md#createDeployment) | **POST** /deployment/create | 
[**deleteDeployment**](DeploymentApi.md#deleteDeployment) | **DELETE** /deployment/{id} | 
[**getDeployment**](DeploymentApi.md#getDeployment) | **GET** /deployment/{id} | 
[**getDeploymentResource**](DeploymentApi.md#getDeploymentResource) | **GET** /deployment/{id}/resources/{resourceId} | 
[**getDeploymentResourceData**](DeploymentApi.md#getDeploymentResourceData) | **GET** /deployment/{id}/resources/{resourceId}/data | 
[**getDeploymentResources**](DeploymentApi.md#getDeploymentResources) | **GET** /deployment/{id}/resources | 
[**getDeployments**](DeploymentApi.md#getDeployments) | **GET** /deployment | 
[**getDeploymentsCount**](DeploymentApi.md#getDeploymentsCount) | **GET** /deployment/count | 
[**redeploy**](DeploymentApi.md#redeploy) | **POST** /deployment/{id}/redeploy | 


<a name="createDeployment"></a>
# **createDeployment**
> DeploymentWithDefinitionsDto createDeployment(tenantId, deploymentSource, deployChangedOnly, enableDuplicateFiltering, deploymentName, data)



Creates a deployment.  **Security Consideration**  Deployments can contain custom code in form of scripts or EL expressions to customize process behavior. This may be abused for remote execution of arbitrary code.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String tenantId = "tenantId_example"; // String | The tenant id for the deployment to be created.
    String deploymentSource = "deploymentSource_example"; // String | The source for the deployment to be created.
    Boolean deployChangedOnly = false; // Boolean | A flag indicating whether the process engine should perform duplicate checking on a per-resource basis. If set to true, only those resources that have actually changed are deployed. Checks are made against resources included previous deployments of the same name and only against the latest versions of those resources. If set to true, the option enable-duplicate-filtering is overridden and set to true.
    Boolean enableDuplicateFiltering = false; // Boolean | A flag indicating whether the process engine should perform duplicate checking for the deployment or not. This allows you to check if a deployment with the same name and the same resouces already exists and if true, not create a new deployment but instead return the existing deployment. The default value is false.
    String deploymentName = "deploymentName_example"; // String | The name for the deployment to be created.
    File data = new File("/path/to/file"); // File | The binary data to create the deployment resource. It is possible to have more than one form part with different form part names for the binary data to create a deployment.
    try {
      DeploymentWithDefinitionsDto result = apiInstance.createDeployment(tenantId, deploymentSource, deployChangedOnly, enableDuplicateFiltering, deploymentName, data);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#createDeployment");
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
 **tenantId** | **String**| The tenant id for the deployment to be created. | [optional]
 **deploymentSource** | **String**| The source for the deployment to be created. | [optional]
 **deployChangedOnly** | **Boolean**| A flag indicating whether the process engine should perform duplicate checking on a per-resource basis. If set to true, only those resources that have actually changed are deployed. Checks are made against resources included previous deployments of the same name and only against the latest versions of those resources. If set to true, the option enable-duplicate-filtering is overridden and set to true. | [optional] [default to false]
 **enableDuplicateFiltering** | **Boolean**| A flag indicating whether the process engine should perform duplicate checking for the deployment or not. This allows you to check if a deployment with the same name and the same resouces already exists and if true, not create a new deployment but instead return the existing deployment. The default value is false. | [optional] [default to false]
 **deploymentName** | **String**| The name for the deployment to be created. | [optional]
 **data** | **File**| The binary data to create the deployment resource. It is possible to have more than one form part with different form part names for the binary data to create a deployment. | [optional]

### Return type

[**DeploymentWithDefinitionsDto**](DeploymentWithDefinitionsDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request. In case one of the bpmn resources cannot be parsed.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#parse-exceptions) for the error response format. |  -  |

<a name="deleteDeployment"></a>
# **deleteDeployment**
> deleteDeployment(id, cascade, skipCustomListeners, skipIoMappings)



Deletes a deployment by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | The id of the deployment to be deleted.
    Boolean cascade = false; // Boolean | `true`, if all process instances, historic process instances and jobs for this deployment should be deleted.
    Boolean skipCustomListeners = false; // Boolean | `true`, if only the built-in ExecutionListeners should be notified with the end event.
    Boolean skipIoMappings = false; // Boolean | `true`, if all input/output mappings should not be invoked.
    try {
      apiInstance.deleteDeployment(id, cascade, skipCustomListeners, skipIoMappings);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#deleteDeployment");
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
 **id** | **String**| The id of the deployment to be deleted. |
 **cascade** | **Boolean**| &#x60;true&#x60;, if all process instances, historic process instances and jobs for this deployment should be deleted. | [optional] [default to false]
 **skipCustomListeners** | **Boolean**| &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. | [optional] [default to false]
 **skipIoMappings** | **Boolean**| &#x60;true&#x60;, if all input/output mappings should not be invoked. | [optional] [default to false]

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
**204** | Request successful. |  -  |
**404** | A Deployment with the provided id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeployment"></a>
# **getDeployment**
> List&lt;DeploymentDto&gt; getDeployment(id)



Retrieves a deployment by id, according to the &#x60;Deployment&#x60; interface of the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | The id of the deployment.
    try {
      List<DeploymentDto> result = apiInstance.getDeployment(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#getDeployment");
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
 **id** | **String**| The id of the deployment. |

### Return type

[**List&lt;DeploymentDto&gt;**](DeploymentDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**404** | Deployment with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeploymentResource"></a>
# **getDeploymentResource**
> DeploymentResourceDto getDeploymentResource(id, resourceId)



Retrieves a deployment resource by resource id for the given deployment.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | The id of the deployment
    String resourceId = "resourceId_example"; // String | The id of the deployment resource
    try {
      DeploymentResourceDto result = apiInstance.getDeploymentResource(id, resourceId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#getDeploymentResource");
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
 **id** | **String**| The id of the deployment |
 **resourceId** | **String**| The id of the deployment resource |

### Return type

[**DeploymentResourceDto**](DeploymentResourceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**404** | Deployment Resource with given resource id or deployment id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeploymentResourceData"></a>
# **getDeploymentResourceData**
> File getDeploymentResourceData(id, resourceId)



Retrieves the binary content of a deployment resource for the given deployment by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | The id of the deployment.
    String resourceId = "resourceId_example"; // String | The id of the deployment resource.
    try {
      File result = apiInstance.getDeploymentResourceData(id, resourceId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#getDeploymentResourceData");
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
 **id** | **String**| The id of the deployment. |
 **resourceId** | **String**| The id of the deployment resource. |

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
**200** | Request successful. The media type of the response depends on the filename. |  -  |
**400** | Deployment Resource with given resource id or deployment id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeploymentResources"></a>
# **getDeploymentResources**
> List&lt;DeploymentResourceDto&gt; getDeploymentResources(id)



Retrieves all deployment resources of a given deployment.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | The id of the deployment to retrieve the deployment resources for.
    try {
      List<DeploymentResourceDto> result = apiInstance.getDeploymentResources(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#getDeploymentResources");
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
 **id** | **String**| The id of the deployment to retrieve the deployment resources for. |

### Return type

[**List&lt;DeploymentResourceDto&gt;**](DeploymentResourceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**404** | Deployment resources for the given deployment do not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeployments"></a>
# **getDeployments**
> List&lt;DeploymentDto&gt; getDeployments(id, name, nameLike, source, withoutSource, tenantIdIn, withoutTenantId, includeDeploymentsWithoutTenantId, after, before, sortBy, sortOrder, firstResult, maxResults)



Queries for deployments that fulfill given parameters. Parameters may be the properties of deployments, such as the id or name or a range of the deployment time. The size of the result set can be retrieved by using the [Get Deployment count](https://docs.camunda.org/manual/7.13/reference/rest/deployment/get-query-count/) method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | Filter by deployment id
    String name = "name_example"; // String | Filter by the deployment name. Exact match.
    String nameLike = "nameLike_example"; // String | Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard `%` to express like-strategy such as: starts with (`%`name), ends with (name`%`) or contains (`%`name`%`).
    String source = "source_example"; // String | Filter by the deployment source.
    Boolean withoutSource = false; // Boolean | Filter by the deployment source whereby source is equal to `null`.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids.
    Boolean withoutTenantId = false; // Boolean | Only include deployments which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
    Boolean includeDeploymentsWithoutTenantId = false; // Boolean | Include deployments which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
    OffsetDateTime after = new OffsetDateTime(); // OffsetDateTime | Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
    String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    try {
      List<DeploymentDto> result = apiInstance.getDeployments(id, name, nameLike, source, withoutSource, tenantIdIn, withoutTenantId, includeDeploymentsWithoutTenantId, after, before, sortBy, sortOrder, firstResult, maxResults);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#getDeployments");
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
 **id** | **String**| Filter by deployment id | [optional]
 **name** | **String**| Filter by the deployment name. Exact match. | [optional]
 **nameLike** | **String**| Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). | [optional]
 **source** | **String**| Filter by the deployment source. | [optional]
 **withoutSource** | **Boolean**| Filter by the deployment source whereby source is equal to &#x60;null&#x60;. | [optional] [default to false]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include deployments which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **includeDeploymentsWithoutTenantId** | **Boolean**| Include deployments which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **after** | **OffsetDateTime**| Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **before** | **OffsetDateTime**| Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;DeploymentDto&gt;**](DeploymentDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeploymentsCount"></a>
# **getDeploymentsCount**
> CountResultDto getDeploymentsCount(id, name, nameLike, source, withoutSource, tenantIdIn, withoutTenantId, includeDeploymentsWithoutTenantId, after, before)



Queries for the number of deployments that fulfill given parameters. Takes the same parameters as the [Get Deployments](https://docs.camunda.org/manual/7.13/reference/rest/deployment/get-query/) method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | Filter by deployment id
    String name = "name_example"; // String | Filter by the deployment name. Exact match.
    String nameLike = "nameLike_example"; // String | Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard `%` to express like-strategy such as: starts with (`%`name), ends with (name`%`) or contains (`%`name`%`).
    String source = "source_example"; // String | Filter by the deployment source.
    Boolean withoutSource = false; // Boolean | Filter by the deployment source whereby source is equal to `null`.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids.
    Boolean withoutTenantId = false; // Boolean | Only include deployments which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
    Boolean includeDeploymentsWithoutTenantId = false; // Boolean | Include deployments which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
    OffsetDateTime after = new OffsetDateTime(); // OffsetDateTime | Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.000+0200`.
    try {
      CountResultDto result = apiInstance.getDeploymentsCount(id, name, nameLike, source, withoutSource, tenantIdIn, withoutTenantId, includeDeploymentsWithoutTenantId, after, before);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#getDeploymentsCount");
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
 **id** | **String**| Filter by deployment id | [optional]
 **name** | **String**| Filter by the deployment name. Exact match. | [optional]
 **nameLike** | **String**| Filter by the deployment name that the parameter is a substring of. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). | [optional]
 **source** | **String**| Filter by the deployment source. | [optional]
 **withoutSource** | **Boolean**| Filter by the deployment source whereby source is equal to &#x60;null&#x60;. | [optional] [default to false]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A deployment must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include deployments which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **includeDeploymentsWithoutTenantId** | **Boolean**| Include deployments which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional] [default to false]
 **after** | **OffsetDateTime**| Restricts to all deployments after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]
 **before** | **OffsetDateTime**| Restricts to all deployments before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. | [optional]

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
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid, for example, if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="redeploy"></a>
# **redeploy**
> DeploymentWithDefinitionsDto redeploy(id, redeploymentDto)



Re-deploys an existing deployment.  The deployment resources to re-deploy can be restricted by using the properties &#x60;resourceIds&#x60; or &#x60;resourceNames&#x60;. If no deployment resources to re-deploy are passed then all existing resources of the given deployment are re-deployed.  **Warning**: Deployments can contain custom code in form of scripts or EL expressions to customize process behavior. This may be abused for remote execution of arbitrary code. See the section on [security considerations for custom code](https://docs.camunda.org/manual/7.13/user-guide/process-engine/securing-custom-code/) in the user guide for details.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DeploymentApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    DeploymentApi apiInstance = new DeploymentApi(defaultClient);
    String id = "id_example"; // String | The id of the deployment to re-deploy.
    RedeploymentDto redeploymentDto = {"resourceIds":["aResourceId"],"resourceNames":["aResourceName"],"source":"cockpit"}; // RedeploymentDto | 
    try {
      DeploymentWithDefinitionsDto result = apiInstance.redeploy(id, redeploymentDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeploymentApi#redeploy");
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
 **id** | **String**| The id of the deployment to re-deploy. |
 **redeploymentDto** | [**RedeploymentDto**](RedeploymentDto.md)|  | [optional]

### Return type

[**DeploymentWithDefinitionsDto**](DeploymentWithDefinitionsDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**404** | Deployment or a deployment resource for the given deployment does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

