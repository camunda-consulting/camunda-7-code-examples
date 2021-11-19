# DecisionDefinitionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**evaluateDecisionById**](DecisionDefinitionApi.md#evaluateDecisionById) | **POST** /decision-definition/{id}/evaluate | Evaluate By Id
[**evaluateDecisionByKey**](DecisionDefinitionApi.md#evaluateDecisionByKey) | **POST** /decision-definition/key/{key}/evaluate | Evaluate By Key
[**evaluateDecisionByKeyAndTenant**](DecisionDefinitionApi.md#evaluateDecisionByKeyAndTenant) | **POST** /decision-definition/key/{key}/tenant-id/{tenant-id}/evaluate | Evaluate By Key And Tenant
[**getDecisionDefinitionById**](DecisionDefinitionApi.md#getDecisionDefinitionById) | **GET** /decision-definition/{id} | Get Decision Definition By Id
[**getDecisionDefinitionByKey**](DecisionDefinitionApi.md#getDecisionDefinitionByKey) | **GET** /decision-definition/key/{key} | Get Decision Definition By Key
[**getDecisionDefinitionByKeyAndTenantId**](DecisionDefinitionApi.md#getDecisionDefinitionByKeyAndTenantId) | **GET** /decision-definition/key/{key}/tenant-id/{tenant-id} | Get Decision Definition By Key And Tenant Id
[**getDecisionDefinitionDiagram**](DecisionDefinitionApi.md#getDecisionDefinitionDiagram) | **GET** /decision-definition/{id}/diagram | Get Diagram
[**getDecisionDefinitionDiagramByKey**](DecisionDefinitionApi.md#getDecisionDefinitionDiagramByKey) | **GET** /decision-definition/key/{key}/diagram | Get Diagram By Key
[**getDecisionDefinitionDiagramByKeyAndTenant**](DecisionDefinitionApi.md#getDecisionDefinitionDiagramByKeyAndTenant) | **GET** /decision-definition/key/{key}/tenant-id/{tenant-id}/diagram | Get Diagram By Key And Tenant
[**getDecisionDefinitionDmnXmlById**](DecisionDefinitionApi.md#getDecisionDefinitionDmnXmlById) | **GET** /decision-definition/{id}/xml | Get XML By Id
[**getDecisionDefinitionDmnXmlByKey**](DecisionDefinitionApi.md#getDecisionDefinitionDmnXmlByKey) | **GET** /decision-definition/key/{key}/xml | Get XML By Key
[**getDecisionDefinitionDmnXmlByKeyAndTenant**](DecisionDefinitionApi.md#getDecisionDefinitionDmnXmlByKeyAndTenant) | **GET** /decision-definition/key/{key}/tenant-id/{tenant-id}/xml | Get XML By Key and Tenant
[**getDecisionDefinitions**](DecisionDefinitionApi.md#getDecisionDefinitions) | **GET** /decision-definition | Get List
[**getDecisionDefinitionsCount**](DecisionDefinitionApi.md#getDecisionDefinitionsCount) | **GET** /decision-definition/count | Get List Count
[**updateHistoryTimeToLiveByDecisionDefinitionId**](DecisionDefinitionApi.md#updateHistoryTimeToLiveByDecisionDefinitionId) | **PUT** /decision-definition/{id}/history-time-to-live | Update History Time to Live
[**updateHistoryTimeToLiveByDecisionDefinitionKey**](DecisionDefinitionApi.md#updateHistoryTimeToLiveByDecisionDefinitionKey) | **PUT** /decision-definition/key/{key}/history-time-to-live | Update History Time to Live By Key
[**updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant**](DecisionDefinitionApi.md#updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant) | **PUT** /decision-definition/key/{key}/tenant-id/{tenant-id}/history-time-to-live | Update History Time to Live By Key And Tenant



## evaluateDecisionById

> List&lt;Map&lt;String, VariableValueDto&gt;&gt; evaluateDecisionById(id, evaluateDecisionDto)

Evaluate By Id

Evaluates a given decision and returns the result. The input values of the decision have to be supplied in the request body.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision definition to be evaluated.
        EvaluateDecisionDto evaluateDecisionDto = new EvaluateDecisionDto(); // EvaluateDecisionDto | 
        try {
            List<Map<String, VariableValueDto>> result = apiInstance.evaluateDecisionById(id, evaluateDecisionDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#evaluateDecisionById");
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
 **id** | **String**| The id of the decision definition to be evaluated. |
 **evaluateDecisionDto** | [**EvaluateDecisionDto**](EvaluateDecisionDto.md)|  | [optional]

### Return type

[**List&lt;Map&lt;String, VariableValueDto&gt;&gt;**](Map.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## evaluateDecisionByKey

> List&lt;Map&lt;String, VariableValueDto&gt;&gt; evaluateDecisionByKey(key, evaluateDecisionDto)

Evaluate By Key

Evaluates the latest version of the decision definition which belongs to no tenant. The input values of the decision have to be supplied in the request body.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof) to be evaluated.
        EvaluateDecisionDto evaluateDecisionDto = new EvaluateDecisionDto(); // EvaluateDecisionDto | 
        try {
            List<Map<String, VariableValueDto>> result = apiInstance.evaluateDecisionByKey(key, evaluateDecisionDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#evaluateDecisionByKey");
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
 **key** | **String**| The key of the decision definition (the latest version thereof) to be evaluated. |
 **evaluateDecisionDto** | [**EvaluateDecisionDto**](EvaluateDecisionDto.md)|  | [optional]

### Return type

[**List&lt;Map&lt;String, VariableValueDto&gt;&gt;**](Map.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## evaluateDecisionByKeyAndTenant

> List&lt;Map&lt;String, VariableValueDto&gt;&gt; evaluateDecisionByKeyAndTenant(key, tenantId, evaluateDecisionDto)

Evaluate By Key And Tenant

Evaluates the latest version of the decision definition for tenant. The input values of the decision have to be supplied in the request body.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof) to be evaluated.
        String tenantId = "tenantId_example"; // String | The id of the tenant the decision definition belongs to.
        EvaluateDecisionDto evaluateDecisionDto = new EvaluateDecisionDto(); // EvaluateDecisionDto | 
        try {
            List<Map<String, VariableValueDto>> result = apiInstance.evaluateDecisionByKeyAndTenant(key, tenantId, evaluateDecisionDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#evaluateDecisionByKeyAndTenant");
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
 **key** | **String**| The key of the decision definition (the latest version thereof) to be evaluated. |
 **tenantId** | **String**| The id of the tenant the decision definition belongs to. |
 **evaluateDecisionDto** | [**EvaluateDecisionDto**](EvaluateDecisionDto.md)|  | [optional]

### Return type

[**List&lt;Map&lt;String, VariableValueDto&gt;&gt;**](Map.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionById

> DecisionDefinitionDto getDecisionDefinitionById(id)

Get Decision Definition By Id

Retrieves a decision definition by id, according to the &#x60;DecisionDefinition&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision definition to be retrieved.
        try {
            DecisionDefinitionDto result = apiInstance.getDecisionDefinitionById(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionById");
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
 **id** | **String**| The id of the decision definition to be retrieved. |

### Return type

[**DecisionDefinitionDto**](DecisionDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionByKey

> DecisionDefinitionDto getDecisionDefinitionByKey(key)

Get Decision Definition By Key

Retrieves the latest version of the decision definition which belongs to no tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof) to be retrieved.
        try {
            DecisionDefinitionDto result = apiInstance.getDecisionDefinitionByKey(key);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionByKey");
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
 **key** | **String**| The key of the decision definition (the latest version thereof) to be retrieved. |

### Return type

[**DecisionDefinitionDto**](DecisionDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionByKeyAndTenantId

> DecisionDefinitionDto getDecisionDefinitionByKeyAndTenantId(key, tenantId)

Get Decision Definition By Key And Tenant Id

Retrieves the latest version of the decision definition for tenant

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof) to be retrieved.
        String tenantId = "tenantId_example"; // String | The id of the tenant the decision definition belongs to.
        try {
            DecisionDefinitionDto result = apiInstance.getDecisionDefinitionByKeyAndTenantId(key, tenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionByKeyAndTenantId");
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
 **key** | **String**| The key of the decision definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the decision definition belongs to. |

### Return type

[**DecisionDefinitionDto**](DecisionDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionDiagram

> File getDecisionDefinitionDiagram(id)

Get Diagram

Retrieves the diagram of a decision definition.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the process definition.
        try {
            File result = apiInstance.getDecisionDefinitionDiagram(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionDiagram");
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
 **id** | **String**| The id of the process definition. |

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
| **200** | Request successful. The image diagram of this process. |  -  |
| **204** | The decision definition doesn&#39;t have an associated diagram. |  -  |
| **404** | Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionDiagramByKey

> File getDecisionDefinitionDiagramByKey(key)

Get Diagram By Key

Returns the diagram for the latest version of the decision definition which belongs to no tenant

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof) to be retrieved.
        try {
            File result = apiInstance.getDecisionDefinitionDiagramByKey(key);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionDiagramByKey");
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
 **key** | **String**| The key of the decision definition (the latest version thereof) to be retrieved. |

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
| **200** | Request successful. The image diagram of this process. |  -  |
| **204** | The decision definition doesn&#39;t have an associated diagram. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionDiagramByKeyAndTenant

> File getDecisionDefinitionDiagramByKeyAndTenant(key, tenantId)

Get Diagram By Key And Tenant

Returns the XML of the latest version of the decision definition for tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof) to be retrieved.
        String tenantId = "tenantId_example"; // String | The id of the tenant the decision definition belongs to.
        try {
            File result = apiInstance.getDecisionDefinitionDiagramByKeyAndTenant(key, tenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionDiagramByKeyAndTenant");
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
 **key** | **String**| The key of the decision definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the decision definition belongs to. |

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
| **200** | Request successful. The image diagram of this process. |  -  |
| **204** | The decision definition doesn&#39;t have an associated diagram. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionDmnXmlById

> DecisionDefinitionDiagramDto getDecisionDefinitionDmnXmlById(id)

Get XML By Id

Retrieves the DMN XML of a decision definition.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision definition.
        try {
            DecisionDefinitionDiagramDto result = apiInstance.getDecisionDefinitionDmnXmlById(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionDmnXmlById");
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
 **id** | **String**| The id of the decision definition. |

### Return type

[**DecisionDefinitionDiagramDto**](DecisionDefinitionDiagramDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionDmnXmlByKey

> DecisionDefinitionDiagramDto getDecisionDefinitionDmnXmlByKey(key)

Get XML By Key

Retrieves the XML for the latest version of the decision definition which belongs to no tenant.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof).
        try {
            DecisionDefinitionDiagramDto result = apiInstance.getDecisionDefinitionDmnXmlByKey(key);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionDmnXmlByKey");
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
 **key** | **String**| The key of the decision definition (the latest version thereof). |

### Return type

[**DecisionDefinitionDiagramDto**](DecisionDefinitionDiagramDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitionDmnXmlByKeyAndTenant

> DecisionDefinitionDiagramDto getDecisionDefinitionDmnXmlByKeyAndTenant(key, tenantId)

Get XML By Key and Tenant

Retrieves the XML of the latest version of the decision definition for tenant

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definition (the latest version thereof).
        String tenantId = "tenantId_example"; // String | The id of the tenant the decision definition belongs to.
        try {
            DecisionDefinitionDiagramDto result = apiInstance.getDecisionDefinitionDmnXmlByKeyAndTenant(key, tenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionDmnXmlByKeyAndTenant");
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
 **key** | **String**| The key of the decision definition (the latest version thereof). |
 **tenantId** | **String**| The id of the tenant the decision definition belongs to. |

### Return type

[**DecisionDefinitionDiagramDto**](DecisionDefinitionDiagramDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getDecisionDefinitions

> List&lt;DecisionDefinitionDto&gt; getDecisionDefinitions(sortBy, sortOrder, firstResult, maxResults, decisionDefinitionId, decisionDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, withoutDecisionRequirementsDefinition, tenantIdIn, withoutTenantId, includeDecisionDefinitionsWithoutTenantId, versionTag, versionTagLike)

Get List

Queries for decision definitions that fulfill given parameters. Parameters may be the properties of decision definitions, such as the name, key or version. The size of the result set can be retrieved by using the [Get Decision Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/decision-definition/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        String decisionDefinitionId = "decisionDefinitionId_example"; // String | Filter by decision definition id.
        String decisionDefinitionIdIn = "decisionDefinitionIdIn_example"; // String | Filter by decision definition ids.
        String name = "name_example"; // String | Filter by decision definition name.
        String nameLike = "nameLike_example"; // String | Filter by decision definition names that the parameter is a substring of.
        String deploymentId = "deploymentId_example"; // String | Filter by the deployment the id belongs to.
        OffsetDateTime deployedAfter = OffsetDateTime.now(); // OffsetDateTime | Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time.
        OffsetDateTime deployedAt = OffsetDateTime.now(); // OffsetDateTime | Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match).
        String key = "key_example"; // String | Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match.
        String keyLike = "keyLike_example"; // String | Filter by decision definition keys that the parameter is a substring of.
        String category = "category_example"; // String | Filter by decision definition category. Exact match.
        String categoryLike = "categoryLike_example"; // String | Filter by decision definition categories that the parameter is a substring of.
        Integer version = 56; // Integer | Filter by decision definition version.
        Boolean latestVersion = true; // Boolean | Only include those decision definitions that are latest versions. Value may only be `true`, as `false` is the default behavior.
        String resourceName = "resourceName_example"; // String | Filter by the name of the decision definition resource. Exact match.
        String resourceNameLike = "resourceNameLike_example"; // String | Filter by names of those decision definition resources that the parameter is a substring of.
        String decisionRequirementsDefinitionId = "decisionRequirementsDefinitionId_example"; // String | Filter by the id of the decision requirements definition this decision definition belongs to.
        String decisionRequirementsDefinitionKey = "decisionRequirementsDefinitionKey_example"; // String | Filter by the key of the decision requirements definition this decision definition belongs to.
        Boolean withoutDecisionRequirementsDefinition = true; // Boolean | Only include decision definitions which does not belongs to any decision requirements definition. Value may only be `true`, as `false` is the default behavior.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of `Strings`. A decision definition must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include decision definitions which belong to no tenant. Value can effectively only be `true`, as `false` is the default behavior.
        Boolean includeDecisionDefinitionsWithoutTenantId = true; // Boolean | Include decision definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        String versionTag = "versionTag_example"; // String | Filter by the version tag.
        String versionTagLike = "versionTagLike_example"; // String | Filter by the version tags of those decision definition resources that the parameter is a substring of.
        try {
            List<DecisionDefinitionDto> result = apiInstance.getDecisionDefinitions(sortBy, sortOrder, firstResult, maxResults, decisionDefinitionId, decisionDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, withoutDecisionRequirementsDefinition, tenantIdIn, withoutTenantId, includeDecisionDefinitionsWithoutTenantId, versionTag, versionTagLike);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitions");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: category, decisionRequirementsDefinitionKey, key, id, name, version, deploymentId, deployTime, versionTag, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **decisionDefinitionId** | **String**| Filter by decision definition id. | [optional]
 **decisionDefinitionIdIn** | **String**| Filter by decision definition ids. | [optional]
 **name** | **String**| Filter by decision definition name. | [optional]
 **nameLike** | **String**| Filter by decision definition names that the parameter is a substring of. | [optional]
 **deploymentId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **deployedAfter** | **OffsetDateTime**| Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time. | [optional]
 **deployedAt** | **OffsetDateTime**| Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match). | [optional]
 **key** | **String**| Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match. | [optional]
 **keyLike** | **String**| Filter by decision definition keys that the parameter is a substring of. | [optional]
 **category** | **String**| Filter by decision definition category. Exact match. | [optional]
 **categoryLike** | **String**| Filter by decision definition categories that the parameter is a substring of. | [optional]
 **version** | **Integer**| Filter by decision definition version. | [optional]
 **latestVersion** | **Boolean**| Only include those decision definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **resourceName** | **String**| Filter by the name of the decision definition resource. Exact match. | [optional]
 **resourceNameLike** | **String**| Filter by names of those decision definition resources that the parameter is a substring of. | [optional]
 **decisionRequirementsDefinitionId** | **String**| Filter by the id of the decision requirements definition this decision definition belongs to. | [optional]
 **decisionRequirementsDefinitionKey** | **String**| Filter by the key of the decision requirements definition this decision definition belongs to. | [optional]
 **withoutDecisionRequirementsDefinition** | **Boolean**| Only include decision definitions which does not belongs to any decision requirements definition. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of &#x60;Strings&#x60;. A decision definition must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include decision definitions which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeDecisionDefinitionsWithoutTenantId** | **Boolean**| Include decision definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **versionTag** | **String**| Filter by the version tag. | [optional]
 **versionTagLike** | **String**| Filter by the version tags of those decision definition resources that the parameter is a substring of. | [optional]

### Return type

[**List&lt;DecisionDefinitionDto&gt;**](DecisionDefinitionDto.md)

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


## getDecisionDefinitionsCount

> CountResultDto getDecisionDefinitionsCount(decisionDefinitionId, decisionDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, withoutDecisionRequirementsDefinition, tenantIdIn, withoutTenantId, includeDecisionDefinitionsWithoutTenantId, versionTag, versionTagLike)

Get List Count

Requests the number of decision definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Decision Definition](https://docs.camunda.org/manual/7.16/reference/rest/decision-definition/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String decisionDefinitionId = "decisionDefinitionId_example"; // String | Filter by decision definition id.
        String decisionDefinitionIdIn = "decisionDefinitionIdIn_example"; // String | Filter by decision definition ids.
        String name = "name_example"; // String | Filter by decision definition name.
        String nameLike = "nameLike_example"; // String | Filter by decision definition names that the parameter is a substring of.
        String deploymentId = "deploymentId_example"; // String | Filter by the deployment the id belongs to.
        OffsetDateTime deployedAfter = OffsetDateTime.now(); // OffsetDateTime | Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time.
        OffsetDateTime deployedAt = OffsetDateTime.now(); // OffsetDateTime | Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match).
        String key = "key_example"; // String | Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match.
        String keyLike = "keyLike_example"; // String | Filter by decision definition keys that the parameter is a substring of.
        String category = "category_example"; // String | Filter by decision definition category. Exact match.
        String categoryLike = "categoryLike_example"; // String | Filter by decision definition categories that the parameter is a substring of.
        Integer version = 56; // Integer | Filter by decision definition version.
        Boolean latestVersion = true; // Boolean | Only include those decision definitions that are latest versions. Value may only be `true`, as `false` is the default behavior.
        String resourceName = "resourceName_example"; // String | Filter by the name of the decision definition resource. Exact match.
        String resourceNameLike = "resourceNameLike_example"; // String | Filter by names of those decision definition resources that the parameter is a substring of.
        String decisionRequirementsDefinitionId = "decisionRequirementsDefinitionId_example"; // String | Filter by the id of the decision requirements definition this decision definition belongs to.
        String decisionRequirementsDefinitionKey = "decisionRequirementsDefinitionKey_example"; // String | Filter by the key of the decision requirements definition this decision definition belongs to.
        Boolean withoutDecisionRequirementsDefinition = true; // Boolean | Only include decision definitions which does not belongs to any decision requirements definition. Value may only be `true`, as `false` is the default behavior.
        String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of `Strings`. A decision definition must have one of the given tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include decision definitions which belong to no tenant. Value can effectively only be `true`, as `false` is the default behavior.
        Boolean includeDecisionDefinitionsWithoutTenantId = true; // Boolean | Include decision definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        String versionTag = "versionTag_example"; // String | Filter by the version tag.
        String versionTagLike = "versionTagLike_example"; // String | Filter by the version tags of those decision definition resources that the parameter is a substring of.
        try {
            CountResultDto result = apiInstance.getDecisionDefinitionsCount(decisionDefinitionId, decisionDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, decisionRequirementsDefinitionId, decisionRequirementsDefinitionKey, withoutDecisionRequirementsDefinition, tenantIdIn, withoutTenantId, includeDecisionDefinitionsWithoutTenantId, versionTag, versionTagLike);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#getDecisionDefinitionsCount");
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
 **decisionDefinitionId** | **String**| Filter by decision definition id. | [optional]
 **decisionDefinitionIdIn** | **String**| Filter by decision definition ids. | [optional]
 **name** | **String**| Filter by decision definition name. | [optional]
 **nameLike** | **String**| Filter by decision definition names that the parameter is a substring of. | [optional]
 **deploymentId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **deployedAfter** | **OffsetDateTime**| Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed after (exclusive) a specific time. | [optional]
 **deployedAt** | **OffsetDateTime**| Filter by the deploy time of the deployment the decision definition belongs to. Only selects decision definitions that have been deployed at a specific time (exact match). | [optional]
 **key** | **String**| Filter by decision definition key, i.e., the id in the DMN 1.0 XML. Exact match. | [optional]
 **keyLike** | **String**| Filter by decision definition keys that the parameter is a substring of. | [optional]
 **category** | **String**| Filter by decision definition category. Exact match. | [optional]
 **categoryLike** | **String**| Filter by decision definition categories that the parameter is a substring of. | [optional]
 **version** | **Integer**| Filter by decision definition version. | [optional]
 **latestVersion** | **Boolean**| Only include those decision definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **resourceName** | **String**| Filter by the name of the decision definition resource. Exact match. | [optional]
 **resourceNameLike** | **String**| Filter by names of those decision definition resources that the parameter is a substring of. | [optional]
 **decisionRequirementsDefinitionId** | **String**| Filter by the id of the decision requirements definition this decision definition belongs to. | [optional]
 **decisionRequirementsDefinitionKey** | **String**| Filter by the key of the decision requirements definition this decision definition belongs to. | [optional]
 **withoutDecisionRequirementsDefinition** | **Boolean**| Only include decision definitions which does not belongs to any decision requirements definition. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of &#x60;Strings&#x60;. A decision definition must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include decision definitions which belong to no tenant. Value can effectively only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeDecisionDefinitionsWithoutTenantId** | **Boolean**| Include decision definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **versionTag** | **String**| Filter by the version tag. | [optional]
 **versionTagLike** | **String**| Filter by the version tags of those decision definition resources that the parameter is a substring of. | [optional]

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


## updateHistoryTimeToLiveByDecisionDefinitionId

> updateHistoryTimeToLiveByDecisionDefinitionId(id, historyTimeToLiveDto)

Update History Time to Live

Updates history time to live for decision definition. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the decision definition to change history time to live.
        HistoryTimeToLiveDto historyTimeToLiveDto = new HistoryTimeToLiveDto(); // HistoryTimeToLiveDto | 
        try {
            apiInstance.updateHistoryTimeToLiveByDecisionDefinitionId(id, historyTimeToLiveDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#updateHistoryTimeToLiveByDecisionDefinitionId");
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
 **id** | **String**| The id of the decision definition to change history time to live. |
 **historyTimeToLiveDto** | [**HistoryTimeToLiveDto**](HistoryTimeToLiveDto.md)|  | [optional]

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
| **400** | Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Decision definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateHistoryTimeToLiveByDecisionDefinitionKey

> updateHistoryTimeToLiveByDecisionDefinitionKey(key, historyTimeToLiveDto)

Update History Time to Live By Key

Updates the latest version of the decision definition which belongs to no tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definitions to change history time to live.
        HistoryTimeToLiveDto historyTimeToLiveDto = new HistoryTimeToLiveDto(); // HistoryTimeToLiveDto | 
        try {
            apiInstance.updateHistoryTimeToLiveByDecisionDefinitionKey(key, historyTimeToLiveDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#updateHistoryTimeToLiveByDecisionDefinitionKey");
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
 **key** | **String**| The key of the decision definitions to change history time to live. |
 **historyTimeToLiveDto** | [**HistoryTimeToLiveDto**](HistoryTimeToLiveDto.md)|  | [optional]

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
| **400** | Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant

> updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant(key, tenantId, historyTimeToLiveDto)

Update History Time to Live By Key And Tenant

Updates the latest version of the decision definition for tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.16/user-guide/process-engine/history/#history-cleanup).

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.DecisionDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        DecisionDefinitionApi apiInstance = new DecisionDefinitionApi(defaultClient);
        String key = "key_example"; // String | The key of the decision definitions to change history time to live.
        String tenantId = "tenantId_example"; // String | The id of the tenant the decision definition belongs to.
        HistoryTimeToLiveDto historyTimeToLiveDto = new HistoryTimeToLiveDto(); // HistoryTimeToLiveDto | 
        try {
            apiInstance.updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant(key, tenantId, historyTimeToLiveDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling DecisionDefinitionApi#updateHistoryTimeToLiveByDecisionDefinitionKeyAndTenant");
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
 **key** | **String**| The key of the decision definitions to change history time to live. |
 **tenantId** | **String**| The id of the tenant the decision definition belongs to. |
 **historyTimeToLiveDto** | [**HistoryTimeToLiveDto**](HistoryTimeToLiveDto.md)|  | [optional]

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
| **400** | Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Decision definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

