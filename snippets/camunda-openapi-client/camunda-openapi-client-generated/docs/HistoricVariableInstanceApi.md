# HistoricVariableInstanceApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteHistoricVariableInstance**](HistoricVariableInstanceApi.md#deleteHistoricVariableInstance) | **DELETE** /history/variable-instance/{id} | Delete Variable Instance
[**getHistoricVariableInstance**](HistoricVariableInstanceApi.md#getHistoricVariableInstance) | **GET** /history/variable-instance/{id} | Get Variable Instance
[**getHistoricVariableInstanceBinary**](HistoricVariableInstanceApi.md#getHistoricVariableInstanceBinary) | **GET** /history/variable-instance/{id}/data | Get Variable Instance (Binary)
[**getHistoricVariableInstances**](HistoricVariableInstanceApi.md#getHistoricVariableInstances) | **GET** /history/variable-instance | Get Variable Instances
[**getHistoricVariableInstancesCount**](HistoricVariableInstanceApi.md#getHistoricVariableInstancesCount) | **GET** /history/variable-instance/count | Get Variable Instance Count
[**queryHistoricVariableInstances**](HistoricVariableInstanceApi.md#queryHistoricVariableInstances) | **POST** /history/variable-instance | Get Variable Instances (POST)
[**queryHistoricVariableInstancesCount**](HistoricVariableInstanceApi.md#queryHistoricVariableInstancesCount) | **POST** /history/variable-instance/count | Get Variable Instance Count (POST)



## deleteHistoricVariableInstance

> deleteHistoricVariableInstance(id)

Delete Variable Instance

Deletes a historic variable instance by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricVariableInstanceApi apiInstance = new HistoricVariableInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the variable instance.
        try {
            apiInstance.deleteHistoricVariableInstance(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricVariableInstanceApi#deleteHistoricVariableInstance");
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
 **id** | **String**| The id of the variable instance. |

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
| **404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricVariableInstance

> HistoricVariableInstanceDto getHistoricVariableInstance(id, deserializeValues)

Get Variable Instance

Retrieves a historic variable by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricVariableInstanceApi apiInstance = new HistoricVariableInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the variable instance.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            HistoricVariableInstanceDto result = apiInstance.getHistoricVariableInstance(id, deserializeValues);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricVariableInstanceApi#getHistoricVariableInstance");
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
 **id** | **String**| The id of the variable instance. |
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**HistoricVariableInstanceDto**](HistoricVariableInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricVariableInstanceBinary

> File getHistoricVariableInstanceBinary(id)

Get Variable Instance (Binary)

Retrieves the content of a historic variable by id. Applicable for variables that are serialized as binary data.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricVariableInstanceApi apiInstance = new HistoricVariableInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the variable instance.
        try {
            File result = apiInstance.getHistoricVariableInstanceBinary(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricVariableInstanceApi#getHistoricVariableInstanceBinary");
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
 **id** | **String**| The id of the variable instance. |

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
| **200** | Request successful. |  -  |
| **400** | Variable with given id exists but is not a binary variable. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getHistoricVariableInstances

> List&lt;HistoricVariableInstanceDto&gt; getHistoricVariableInstances(variableName, variableNameLike, variableValue, variableNamesIgnoreCase, variableValuesIgnoreCase, variableTypeIn, includeDeleted, processInstanceId, processInstanceIdIn, processDefinitionId, processDefinitionKey, executionIdIn, caseInstanceId, caseExecutionIdIn, caseActivityIdIn, taskIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, sortBy, sortOrder, firstResult, maxResults, deserializeValues)

Get Variable Instances

Queries for historic variable instances that fulfill the given parameters. The size of the result set can be retrieved by using the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricVariableInstanceApi apiInstance = new HistoricVariableInstanceApi(defaultClient);
        String variableName = "variableName_example"; // String | Filter by variable name.
        String variableNameLike = "variableNameLike_example"; // String | Restrict to variables with a name like the parameter.
        Object variableValue = null; // Object | Filter by variable value. Is treated as a `String` object on server side.
        Boolean variableNamesIgnoreCase = true; // Boolean | Match the variable name provided in `variableName` and `variableNameLike` case- insensitively. If set to `true` **variableName** and **variablename** are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match the variable value provided in `variableValue` case-insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal.
        String variableTypeIn = "variableTypeIn_example"; // String | Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type 'serializable'.
        Boolean includeDeleted = true; // Boolean | Include variables that has already been deleted during the execution.
        String processInstanceId = "processInstanceId_example"; // String | Filter by the process instance the variable belongs to.
        String processInstanceIdIn = "processInstanceIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and comma-separated process instance ids.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the variable belongs to.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by a key of the process definition the variable belongs to.
        String executionIdIn = "executionIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated execution ids.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by the case instance the variable belongs to.
        String caseExecutionIdIn = "caseExecutionIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids.
        String caseActivityIdIn = "caseActivityIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids.
        String taskIdIn = "taskIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated task ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and comma- separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic variable instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            List<HistoricVariableInstanceDto> result = apiInstance.getHistoricVariableInstances(variableName, variableNameLike, variableValue, variableNamesIgnoreCase, variableValuesIgnoreCase, variableTypeIn, includeDeleted, processInstanceId, processInstanceIdIn, processDefinitionId, processDefinitionKey, executionIdIn, caseInstanceId, caseExecutionIdIn, caseActivityIdIn, taskIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId, sortBy, sortOrder, firstResult, maxResults, deserializeValues);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricVariableInstanceApi#getHistoricVariableInstances");
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
 **variableName** | **String**| Filter by variable name. | [optional]
 **variableNameLike** | **String**| Restrict to variables with a name like the parameter. | [optional]
 **variableValue** | [**Object**](.md)| Filter by variable value. Is treated as a &#x60;String&#x60; object on server side. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match the variable name provided in &#x60;variableName&#x60; and &#x60;variableNameLike&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match the variable value provided in &#x60;variableValue&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. | [optional]
 **variableTypeIn** | **String**| Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#39;serializable&#39;. | [optional]
 **includeDeleted** | **Boolean**| Include variables that has already been deleted during the execution. | [optional]
 **processInstanceId** | **String**| Filter by the process instance the variable belongs to. | [optional]
 **processInstanceIdIn** | **String**| Only include historic variable instances which belong to one of the passed and comma-separated process instance ids. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the variable belongs to. | [optional]
 **processDefinitionKey** | **String**| Filter by a key of the process definition the variable belongs to. | [optional]
 **executionIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated execution ids. | [optional]
 **caseInstanceId** | **String**| Filter by the case instance the variable belongs to. | [optional]
 **caseExecutionIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids. | [optional]
 **caseActivityIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids. | [optional]
 **taskIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated task ids. | [optional]
 **activityInstanceIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids. | [optional]
 **tenantIdIn** | **String**| Only include historic variable instances which belong to one of the passed and comma- separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic variable instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: instanceId, variableName, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**List&lt;HistoricVariableInstanceDto&gt;**](HistoricVariableInstanceDto.md)

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


## getHistoricVariableInstancesCount

> CountResultDto getHistoricVariableInstancesCount(variableName, variableNameLike, variableValue, variableNamesIgnoreCase, variableValuesIgnoreCase, variableTypeIn, includeDeleted, processInstanceId, processInstanceIdIn, processDefinitionId, processDefinitionKey, executionIdIn, caseInstanceId, caseExecutionIdIn, caseActivityIdIn, taskIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId)

Get Variable Instance Count

Queries for the number of historic variable instances that fulfill the given parameters. Takes the same parameters as the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricVariableInstanceApi apiInstance = new HistoricVariableInstanceApi(defaultClient);
        String variableName = "variableName_example"; // String | Filter by variable name.
        String variableNameLike = "variableNameLike_example"; // String | Restrict to variables with a name like the parameter.
        Object variableValue = null; // Object | Filter by variable value. Is treated as a `String` object on server side.
        Boolean variableNamesIgnoreCase = true; // Boolean | Match the variable name provided in `variableName` and `variableNameLike` case- insensitively. If set to `true` **variableName** and **variablename** are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match the variable value provided in `variableValue` case-insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal.
        String variableTypeIn = "variableTypeIn_example"; // String | Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type 'serializable'.
        Boolean includeDeleted = true; // Boolean | Include variables that has already been deleted during the execution.
        String processInstanceId = "processInstanceId_example"; // String | Filter by the process instance the variable belongs to.
        String processInstanceIdIn = "processInstanceIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and comma-separated process instance ids.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the process definition the variable belongs to.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by a key of the process definition the variable belongs to.
        String executionIdIn = "executionIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated execution ids.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by the case instance the variable belongs to.
        String caseExecutionIdIn = "caseExecutionIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids.
        String caseActivityIdIn = "caseActivityIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids.
        String taskIdIn = "taskIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated task ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include historic variable instances which belong to one of the passed and comma- separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include historic variable instances that belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getHistoricVariableInstancesCount(variableName, variableNameLike, variableValue, variableNamesIgnoreCase, variableValuesIgnoreCase, variableTypeIn, includeDeleted, processInstanceId, processInstanceIdIn, processDefinitionId, processDefinitionKey, executionIdIn, caseInstanceId, caseExecutionIdIn, caseActivityIdIn, taskIdIn, activityInstanceIdIn, tenantIdIn, withoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricVariableInstanceApi#getHistoricVariableInstancesCount");
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
 **variableName** | **String**| Filter by variable name. | [optional]
 **variableNameLike** | **String**| Restrict to variables with a name like the parameter. | [optional]
 **variableValue** | [**Object**](.md)| Filter by variable value. Is treated as a &#x60;String&#x60; object on server side. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match the variable name provided in &#x60;variableName&#x60; and &#x60;variableNameLike&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match the variable value provided in &#x60;variableValue&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. | [optional]
 **variableTypeIn** | **String**| Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#39;serializable&#39;. | [optional]
 **includeDeleted** | **Boolean**| Include variables that has already been deleted during the execution. | [optional]
 **processInstanceId** | **String**| Filter by the process instance the variable belongs to. | [optional]
 **processInstanceIdIn** | **String**| Only include historic variable instances which belong to one of the passed and comma-separated process instance ids. | [optional]
 **processDefinitionId** | **String**| Filter by the process definition the variable belongs to. | [optional]
 **processDefinitionKey** | **String**| Filter by a key of the process definition the variable belongs to. | [optional]
 **executionIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated execution ids. | [optional]
 **caseInstanceId** | **String**| Filter by the case instance the variable belongs to. | [optional]
 **caseExecutionIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated case execution ids. | [optional]
 **caseActivityIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated case activity ids. | [optional]
 **taskIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated task ids. | [optional]
 **activityInstanceIdIn** | **String**| Only include historic variable instances which belong to one of the passed and and comma-separated activity instance ids. | [optional]
 **tenantIdIn** | **String**| Only include historic variable instances which belong to one of the passed and comma- separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include historic variable instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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


## queryHistoricVariableInstances

> List&lt;HistoricVariableInstanceDto&gt; queryHistoricVariableInstances(firstResult, maxResults, deserializeValues, historicVariableInstanceQueryDto)

Get Variable Instances (POST)

Queries for historic variable instances that fulfill the given parameters. This method is slightly more powerful than the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query/) method because it allows filtering by variable values of the different types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricVariableInstanceApi apiInstance = new HistoricVariableInstanceApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        HistoricVariableInstanceQueryDto historicVariableInstanceQueryDto = new HistoricVariableInstanceQueryDto(); // HistoricVariableInstanceQueryDto | 
        try {
            List<HistoricVariableInstanceDto> result = apiInstance.queryHistoricVariableInstances(firstResult, maxResults, deserializeValues, historicVariableInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricVariableInstanceApi#queryHistoricVariableInstances");
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
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]
 **historicVariableInstanceQueryDto** | [**HistoricVariableInstanceQueryDto**](HistoricVariableInstanceQueryDto.md)|  | [optional]

### Return type

[**List&lt;HistoricVariableInstanceDto&gt;**](HistoricVariableInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryHistoricVariableInstancesCount

> CountResultDto queryHistoricVariableInstancesCount(historicVariableInstanceQueryDto)

Get Variable Instance Count (POST)

Queries for historic variable instances that fulfill the given parameters. This method takes the same message body as the [Get Variable Instances (POST)](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/post-variable-instance-query/) method and therefore it is more powerful regarding variable values than the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/history/variable-instance/get-variable-instance-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricVariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricVariableInstanceApi apiInstance = new HistoricVariableInstanceApi(defaultClient);
        HistoricVariableInstanceQueryDto historicVariableInstanceQueryDto = new HistoricVariableInstanceQueryDto(); // HistoricVariableInstanceQueryDto | 
        try {
            CountResultDto result = apiInstance.queryHistoricVariableInstancesCount(historicVariableInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricVariableInstanceApi#queryHistoricVariableInstancesCount");
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
 **historicVariableInstanceQueryDto** | [**HistoricVariableInstanceQueryDto**](HistoricVariableInstanceQueryDto.md)|  | [optional]

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

