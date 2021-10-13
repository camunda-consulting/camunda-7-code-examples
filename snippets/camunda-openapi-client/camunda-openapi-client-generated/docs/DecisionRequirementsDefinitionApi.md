# DecisionRequirementsDefinitionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getDecisionRequirementsDefinitionById**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionById) | **GET** /decision-requirements-definition/{id} | Get Decision Requirements Definition by ID
[**getDecisionRequirementsDefinitionByKey**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionByKey) | **GET** /decision-requirements-definition/key/{key} | Get Decision Requirements Definition by Key
[**getDecisionRequirementsDefinitionByKeyAndTenantId**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionByKeyAndTenantId) | **GET** /decision-requirements-definition/key/{key}/tenant-id/{tenant-id} | Get Decision Requirements Definition by Key and Tenant ID
[**getDecisionRequirementsDefinitionDiagramById**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionDiagramById) | **GET** /decision-requirements-definition/{id}/diagram | Get Decision Requirements Diagram by ID
[**getDecisionRequirementsDefinitionDiagramByKey**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionDiagramByKey) | **GET** /decision-requirements-definition/key/{key}/diagram | Get Decision Requirements Diagram by Key
[**getDecisionRequirementsDefinitionDiagramByKeyAndTenantId**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionDiagramByKeyAndTenantId) | **GET** /decision-requirements-definition/key/{key}/tenant-id/{tenant-id}/diagram | Get Decision Requirements Diagram by Key and Tenant ID
[**getDecisionRequirementsDefinitionDmnXmlById**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionDmnXmlById) | **GET** /decision-requirements-definition/{id}/xml | Get DMN XML by ID
[**getDecisionRequirementsDefinitionDmnXmlByKey**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionDmnXmlByKey) | **GET** /decision-requirements-definition/key/{key}/xml | Get DMN XML by Key
[**getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId) | **GET** /decision-requirements-definition/key/{key}/tenant-id/{tenant-id}/xml | Get DMN XML by Key and Tenant ID
[**getDecisionRequirementsDefinitions**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitions) | **GET** /decision-requirements-definition | Get Decision Requirements Definitions
[**getDecisionRequirementsDefinitionsCount**](DecisionRequirementsDefinitionApi.md#getDecisionRequirementsDefinitionsCount) | **GET** /decision-requirements-definition/count | Get Decision Requirements Definition Count



## getDecisionRequirementsDefinitionById

> DecisionRequirementsDefinitionDto getDecisionRequirementsDefinitionById(id)

Get Decision Requirements Definition by ID

Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision requirements definition to be retrieved.
        try {
            DecisionRequirementsDefinitionDto result = apiInstance.getDecisionRequirementsDefinitionById(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionById");
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
 **id** | **String**| The id of the decision requirements definition to be retrieved. |

### Return type

[**DecisionRequirementsDefinitionDto**](DecisionRequirementsDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionRequirementsDefinitionByKey

> DecisionRequirementsDefinitionDto getDecisionRequirementsDefinitionByKey(key)

Get Decision Requirements Definition by Key

Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine.  Returns the latest version of the decision requirements definition  which belongs to no tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision requirements definition (the latest version thereof) to be retrieved.
        try {
            DecisionRequirementsDefinitionDto result = apiInstance.getDecisionRequirementsDefinitionByKey(key);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionByKey");
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
 **key** | **String**| The key of the decision requirements definition (the latest version thereof) to be retrieved. |

### Return type

[**DecisionRequirementsDefinitionDto**](DecisionRequirementsDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionRequirementsDefinitionByKeyAndTenantId

> DecisionRequirementsDefinitionDto getDecisionRequirementsDefinitionByKeyAndTenantId(key, tenantId)

Get Decision Requirements Definition by Key and Tenant ID

Retrieves a decision requirements definition according to the &#x60;DecisionRequirementsDefinition&#x60; interface in the engine. Returns the latest version of the decision requirements definition  for a tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision requirements definition (the latest version thereof) to be retrieved.
        String tenantId = "tenantId_example"; // String | The id of the tenant to which the decision requirements definition belongs to.
        try {
            DecisionRequirementsDefinitionDto result = apiInstance.getDecisionRequirementsDefinitionByKeyAndTenantId(key, tenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionByKeyAndTenantId");
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
 **key** | **String**| The key of the decision requirements definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant to which the decision requirements definition belongs to. |

### Return type

[**DecisionRequirementsDefinitionDto**](DecisionRequirementsDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionRequirementsDefinitionDiagramById

> File getDecisionRequirementsDefinitionDiagramById(id)

Get Decision Requirements Diagram by ID

Retrieves the diagram of a decision requirements definition.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision requirements definition.
        try {
            File result = apiInstance.getDecisionRequirementsDefinitionDiagramById(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionDiagramById");
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
 **id** | **String**| The id of the decision requirements definition. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: image/_*, application/octet-stream, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The image diagram of the decision requirements definition. |  -  |
| **204** | The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content. |  -  |
| **404** | Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionRequirementsDefinitionDiagramByKey

> File getDecisionRequirementsDefinitionDiagramByKey(key)

Get Decision Requirements Diagram by Key

Retrieves the diagram of a decision requirements definition. Returns the diagram for the latest version of the decision requirements  definition which belongs to no tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision requirements definition (the latest version thereof) to be retrieved.
        try {
            File result = apiInstance.getDecisionRequirementsDefinitionDiagramByKey(key);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionDiagramByKey");
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
 **key** | **String**| The key of the decision requirements definition (the latest version thereof) to be retrieved. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: image/_*, application/octet-stream, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The image diagram of the decision requirements definition. |  -  |
| **204** | The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content. |  -  |
| **404** | Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionRequirementsDefinitionDiagramByKeyAndTenantId

> File getDecisionRequirementsDefinitionDiagramByKeyAndTenantId(key, tenantId)

Get Decision Requirements Diagram by Key and Tenant ID

Retrieves the diagram of a decision requirements definition. Returns the diagram of the latest version of the decision requirements  definition for a tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision requirements definition (the latest version thereof) to be retrieved.
        String tenantId = "tenantId_example"; // String | The id of the tenant to which the decision requirements definition belongs to.
        try {
            File result = apiInstance.getDecisionRequirementsDefinitionDiagramByKeyAndTenantId(key, tenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionDiagramByKeyAndTenantId");
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
 **key** | **String**| The key of the decision requirements definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant to which the decision requirements definition belongs to. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: image/_*, application/octet-stream, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | The image diagram of the decision requirements definition. |  -  |
| **204** | The decision requirements definition doesn&#39;t have an associated diagram. This method returns no content. |  -  |
| **404** | Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionRequirementsDefinitionDmnXmlById

> DecisionRequirementsDefinitionXmlDto getDecisionRequirementsDefinitionDmnXmlById(id)

Get DMN XML by ID

Retrieves the DMN XML of a decision requirements definition.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision requirements definition.
        try {
            DecisionRequirementsDefinitionXmlDto result = apiInstance.getDecisionRequirementsDefinitionDmnXmlById(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionDmnXmlById");
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
 **id** | **String**| The id of the decision requirements definition. |

### Return type

[**DecisionRequirementsDefinitionXmlDto**](DecisionRequirementsDefinitionXmlDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionRequirementsDefinitionDmnXmlByKey

> DecisionRequirementsDefinitionXmlDto getDecisionRequirementsDefinitionDmnXmlByKey(key)

Get DMN XML by Key

Retrieves the DMN XML of a decision requirements definition. Returns the XML for the latest version of the decision requirements  definition which belongs to no tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision requirements definition (the latest version thereof) to be retrieved.
        try {
            DecisionRequirementsDefinitionXmlDto result = apiInstance.getDecisionRequirementsDefinitionDmnXmlByKey(key);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionDmnXmlByKey");
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
 **key** | **String**| The key of the decision requirements definition (the latest version thereof) to be retrieved. |

### Return type

[**DecisionRequirementsDefinitionXmlDto**](DecisionRequirementsDefinitionXmlDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** |  Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId

> DecisionRequirementsDefinitionXmlDto getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId(key, tenantId)

Get DMN XML by Key and Tenant ID

Retrieves the DMN XML of a decision requirements definition. Returns the XML of the latest version of the decision requirements  definition for a tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision requirements definition (the latest version thereof) to be retrieved.
        String tenantId = "tenantId_example"; // String | The id of the tenant to which the decision requirements definition belongs to.
        try {
            DecisionRequirementsDefinitionXmlDto result = apiInstance.getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId(key, tenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionDmnXmlByKeyAndTenantId");
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
 **key** | **String**| The key of the decision requirements definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant to which the decision requirements definition belongs to. |

### Return type

[**DecisionRequirementsDefinitionXmlDto**](DecisionRequirementsDefinitionXmlDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** |  Decision requirements definition with given id or key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format.  |  -  |


## getDecisionRequirementsDefinitions

> List&lt;DecisionRequirementsDefinitionDto&gt; getDecisionRequirementsDefinitions(decisionRequirementsDefinitionId, decisionRequirementsDefinitionIdIn, name, nameLike, deploymentId, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, tenantIdIn, withoutTenantId, includeDecisionRequirementsDefinitionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults)

Get Decision Requirements Definitions

Queries for decision requirements definitions that fulfill given parameters. Parameters may be the properties of decision requirements definitions, such as the name, key or version.  The size of the result set can be retrieved by using the [Get Decision Requirements Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/decision-requirements-definition/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String decisionRequirementsDefinitionId = "decisionRequirementsDefinitionId_example"; // String | Filter by decision requirements definition id.
        String decisionRequirementsDefinitionIdIn = "decisionRequirementsDefinitionIdIn_example"; // String | Filter by decision requirements definition ids.
        String name = "name_example"; // String | Filter by decision requirements definition name.
        String nameLike = "nameLike_example"; // String | Filter by decision requirements definition names that the parameter is a substring of.
        String deploymentId = "deploymentId_example"; // String | Filter by the id of the deployment a decision requirement definition belongs to.
        String key = "key_example"; // String | Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match.
        String keyLike = "keyLike_example"; // String | Filter by decision requirements definition keys that the parameter is a substring of.
        String category = "category_example"; // String | Filter by decision requirements definition category. Exact match.
        String categoryLike = "categoryLike_example"; // String | Filter by decision requirements definition categories that the parameter is a substring of.
        Integer version = 56; // Integer | Filter by decision requirements definition version.
        Boolean latestVersion = true; // Boolean | Only include those decision requirements definitions that are latest versions. Value may only be `true`, as `false` is the default behavior.
        String resourceName = "resourceName_example"; // String | Filter by the name of the decision requirements definition resource. Exact match.
        String resourceNameLike = "resourceNameLike_example"; // String | Filter by names of those decision requirements definition resources that the parameter is a substring of.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include decision requirements definitions which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean includeDecisionRequirementsDefinitionsWithoutTenantId = true; // Boolean | Include decision requirements definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<DecisionRequirementsDefinitionDto> result = apiInstance.getDecisionRequirementsDefinitions(decisionRequirementsDefinitionId, decisionRequirementsDefinitionIdIn, name, nameLike, deploymentId, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, tenantIdIn, withoutTenantId, includeDecisionRequirementsDefinitionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitions");
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
 **decisionRequirementsDefinitionId** | **String**| Filter by decision requirements definition id. | [optional]
 **decisionRequirementsDefinitionIdIn** | **String**| Filter by decision requirements definition ids. | [optional]
 **name** | **String**| Filter by decision requirements definition name. | [optional]
 **nameLike** | **String**| Filter by decision requirements definition names that the parameter is a substring of. | [optional]
 **deploymentId** | **String**| Filter by the id of the deployment a decision requirement definition belongs to. | [optional]
 **key** | **String**| Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match. | [optional]
 **keyLike** | **String**| Filter by decision requirements definition keys that the parameter is a substring of. | [optional]
 **category** | **String**| Filter by decision requirements definition category. Exact match. | [optional]
 **categoryLike** | **String**| Filter by decision requirements definition categories that the parameter is a substring of. | [optional]
 **version** | **Integer**| Filter by decision requirements definition version. | [optional]
 **latestVersion** | **Boolean**| Only include those decision requirements definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **resourceName** | **String**| Filter by the name of the decision requirements definition resource. Exact match. | [optional]
 **resourceNameLike** | **String**| Filter by names of those decision requirements definition resources that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include decision requirements definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeDecisionRequirementsDefinitionsWithoutTenantId** | **Boolean**| Include decision requirements definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: id, key, name, version, deploymentId, category, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;DecisionRequirementsDefinitionDto&gt;**](DecisionRequirementsDefinitionDto.md)

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


## getDecisionRequirementsDefinitionsCount

> CountResultDto getDecisionRequirementsDefinitionsCount(decisionRequirementsDefinitionId, decisionRequirementsDefinitionIdIn, name, nameLike, deploymentId, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, tenantIdIn, withoutTenantId, includeDecisionRequirementsDefinitionsWithoutTenantId)

Get Decision Requirements Definition Count

Requests the number of decision requirements definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Decision Requirements Definitions](https://docs.camunda.org/manual/7.16/reference/rest/decision-requirements-definition/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionRequirementsDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionRequirementsDefinitionApi apiInstance = new DecisionRequirementsDefinitionApi(defaultClient);
        String decisionRequirementsDefinitionId = "decisionRequirementsDefinitionId_example"; // String | Filter by decision requirements definition id.
        String decisionRequirementsDefinitionIdIn = "decisionRequirementsDefinitionIdIn_example"; // String | Filter by decision requirements definition ids.
        String name = "name_example"; // String | Filter by decision requirements definition name.
        String nameLike = "nameLike_example"; // String | Filter by decision requirements definition names that the parameter is a substring of.
        String deploymentId = "deploymentId_example"; // String | Filter by the id of the deployment a decision requirement definition belongs to.
        String key = "key_example"; // String | Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match.
        String keyLike = "keyLike_example"; // String | Filter by decision requirements definition keys that the parameter is a substring of.
        String category = "category_example"; // String | Filter by decision requirements definition category. Exact match.
        String categoryLike = "categoryLike_example"; // String | Filter by decision requirements definition categories that the parameter is a substring of.
        Integer version = 56; // Integer | Filter by decision requirements definition version.
        Boolean latestVersion = true; // Boolean | Only include those decision requirements definitions that are latest versions. Value may only be `true`, as `false` is the default behavior.
        String resourceName = "resourceName_example"; // String | Filter by the name of the decision requirements definition resource. Exact match.
        String resourceNameLike = "resourceNameLike_example"; // String | Filter by names of those decision requirements definition resources that the parameter is a substring of.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include decision requirements definitions which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean includeDecisionRequirementsDefinitionsWithoutTenantId = true; // Boolean | Include decision requirements definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getDecisionRequirementsDefinitionsCount(decisionRequirementsDefinitionId, decisionRequirementsDefinitionIdIn, name, nameLike, deploymentId, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, tenantIdIn, withoutTenantId, includeDecisionRequirementsDefinitionsWithoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionRequirementsDefinitionApi#getDecisionRequirementsDefinitionsCount");
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
 **decisionRequirementsDefinitionId** | **String**| Filter by decision requirements definition id. | [optional]
 **decisionRequirementsDefinitionIdIn** | **String**| Filter by decision requirements definition ids. | [optional]
 **name** | **String**| Filter by decision requirements definition name. | [optional]
 **nameLike** | **String**| Filter by decision requirements definition names that the parameter is a substring of. | [optional]
 **deploymentId** | **String**| Filter by the id of the deployment a decision requirement definition belongs to. | [optional]
 **key** | **String**| Filter by decision requirements definition key, i.e., the id in the DMN 1.3 XML. Exact match. | [optional]
 **keyLike** | **String**| Filter by decision requirements definition keys that the parameter is a substring of. | [optional]
 **category** | **String**| Filter by decision requirements definition category. Exact match. | [optional]
 **categoryLike** | **String**| Filter by decision requirements definition categories that the parameter is a substring of. | [optional]
 **version** | **Integer**| Filter by decision requirements definition version. | [optional]
 **latestVersion** | **Boolean**| Only include those decision requirements definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **resourceName** | **String**| Filter by the name of the decision requirements definition resource. Exact match. | [optional]
 **resourceNameLike** | **String**| Filter by names of those decision requirements definition resources that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A decision requirements definition must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include decision requirements definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeDecisionRequirementsDefinitionsWithoutTenantId** | **Boolean**| Include decision requirements definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

