# VariableInstanceApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getVariableInstance**](VariableInstanceApi.md#getVariableInstance) | **GET** /variable-instance/{id} | Get Variable Instance
[**getVariableInstanceBinary**](VariableInstanceApi.md#getVariableInstanceBinary) | **GET** /variable-instance/{id}/data | Get Variable Instance (Binary)
[**getVariableInstances**](VariableInstanceApi.md#getVariableInstances) | **GET** /variable-instance | Get Variable Instances
[**getVariableInstancesCount**](VariableInstanceApi.md#getVariableInstancesCount) | **GET** /variable-instance/count | Get Variable Instance Count
[**queryVariableInstances**](VariableInstanceApi.md#queryVariableInstances) | **POST** /variable-instance | Get Variable Instances (POST)
[**queryVariableInstancesCount**](VariableInstanceApi.md#queryVariableInstancesCount) | **POST** /variable-instance/count | Get Variable Instance Count (POST)



## getVariableInstance

> VariableInstanceDto getVariableInstance(id, deserializeValue)

Get Variable Instance

Retrieves a variable by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.VariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        VariableInstanceApi apiInstance = new VariableInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the variable instance.
        Boolean deserializeValue = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:**  While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            VariableInstanceDto result = apiInstance.getVariableInstance(id, deserializeValue);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VariableInstanceApi#getVariableInstance");
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
 **deserializeValue** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:**  While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**VariableInstanceDto**](VariableInstanceDto.md)

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


## getVariableInstanceBinary

> File getVariableInstanceBinary(id)

Get Variable Instance (Binary)

Retrieves the content of a variable by id. Applicable for byte array and file variables.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.VariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        VariableInstanceApi apiInstance = new VariableInstanceApi(defaultClient);
        String id = "id_example"; // String | The id of the variable instance.
        try {
            File result = apiInstance.getVariableInstanceBinary(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VariableInstanceApi#getVariableInstanceBinary");
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
| **200** | Request successful. For binary variables or files without any MIME type information, a byte stream is returned.                       File variables with MIME type information are returned as the saved type. Additionally, for file                       variables the Content-Disposition header will be set. |  -  |
| **400** | Variable with given id exists but does not serialize as binary data. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getVariableInstances

> List&lt;VariableInstanceDto&gt; getVariableInstances(variableName, variableNameLike, processInstanceIdIn, executionIdIn, caseInstanceIdIn, caseExecutionIdIn, taskIdIn, batchIdIn, activityInstanceIdIn, tenantIdIn, variableValues, variableNamesIgnoreCase, variableValuesIgnoreCase, variableScopeIdIn, sortBy, sortOrder, firstResult, maxResults, deserializeValues)

Get Variable Instances

Query for variable instances that fulfill given parameters. Parameters may be the properties of variable instances, such as the name or type. The size of the result set can be retrieved by using the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.VariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        VariableInstanceApi apiInstance = new VariableInstanceApi(defaultClient);
        String variableName = "variableName_example"; // String | Filter by variable instance name.
        String variableNameLike = "variableNameLike_example"; // String | Filter by the variable instance name. The parameter can include the wildcard `%` to express like-strategy such as: starts with (`%`name), ends with (name`%`) or contains (`%`name`%`).
        String processInstanceIdIn = "processInstanceIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated process instance ids.
        String executionIdIn = "executionIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated execution ids.
        String caseInstanceIdIn = "caseInstanceIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated case instance ids.
        String caseExecutionIdIn = "caseExecutionIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated case execution ids.
        String taskIdIn = "taskIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated task ids.
        String batchIdIn = "batchIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated batch ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated activity instance ids.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated tenant ids.
        String variableValues = "variableValues_example"; // String | Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note:** Values are always treated as `String` objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
        Boolean variableNamesIgnoreCase = true; // Boolean | Match all variable names provided in `variableValues` case-insensitively. If set to `true` **variableName** and **variablename** are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match all variable values provided in `variableValues` case-insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal.
        String variableScopeIdIn = "variableScopeIdIn_example"; // String | Only include variable instances which belong to one of passed scope ids.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        try {
            List<VariableInstanceDto> result = apiInstance.getVariableInstances(variableName, variableNameLike, processInstanceIdIn, executionIdIn, caseInstanceIdIn, caseExecutionIdIn, taskIdIn, batchIdIn, activityInstanceIdIn, tenantIdIn, variableValues, variableNamesIgnoreCase, variableValuesIgnoreCase, variableScopeIdIn, sortBy, sortOrder, firstResult, maxResults, deserializeValues);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VariableInstanceApi#getVariableInstances");
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
 **variableName** | **String**| Filter by variable instance name. | [optional]
 **variableNameLike** | **String**| Filter by the variable instance name. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). | [optional]
 **processInstanceIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated process instance ids. | [optional]
 **executionIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated execution ids. | [optional]
 **caseInstanceIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated case instance ids. | [optional]
 **caseExecutionIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated case execution ids. | [optional]
 **taskIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated task ids. | [optional]
 **batchIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated batch ids. | [optional]
 **activityInstanceIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated activity instance ids. | [optional]
 **tenantIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated tenant ids. | [optional]
 **variableValues** | **String**| Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. | [optional]
 **variableScopeIdIn** | **String**| Only include variable instances which belong to one of passed scope ids. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: variableName, variableType, activityInstanceId, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional]

### Return type

[**List&lt;VariableInstanceDto&gt;**](VariableInstanceDto.md)

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


## getVariableInstancesCount

> CountResultDto getVariableInstancesCount(variableName, variableNameLike, processInstanceIdIn, executionIdIn, caseInstanceIdIn, caseExecutionIdIn, taskIdIn, batchIdIn, activityInstanceIdIn, tenantIdIn, variableValues, variableNamesIgnoreCase, variableValuesIgnoreCase, variableScopeIdIn, sortBy, sortOrder)

Get Variable Instance Count

Query for the number of variable instances that fulfill given parameters. Takes the same parameters as the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.VariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        VariableInstanceApi apiInstance = new VariableInstanceApi(defaultClient);
        String variableName = "variableName_example"; // String | Filter by variable instance name.
        String variableNameLike = "variableNameLike_example"; // String | Filter by the variable instance name. The parameter can include the wildcard `%` to express like-strategy such as: starts with (`%`name), ends with (name`%`) or contains (`%`name`%`).
        String processInstanceIdIn = "processInstanceIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated process instance ids.
        String executionIdIn = "executionIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated execution ids.
        String caseInstanceIdIn = "caseInstanceIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated case instance ids.
        String caseExecutionIdIn = "caseExecutionIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated case execution ids.
        String taskIdIn = "taskIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated task ids.
        String batchIdIn = "batchIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated batch ids.
        String activityInstanceIdIn = "activityInstanceIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated activity instance ids.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include variable instances which belong to one of the passed and comma-separated tenant ids.
        String variableValues = "variableValues_example"; // String | Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note:** Values are always treated as `String` objects on server side.  Valid operator values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
        Boolean variableNamesIgnoreCase = true; // Boolean | Match all variable names provided in `variableValues` case-insensitively. If set to `true` **variableName** and **variablename** are treated as equal.
        Boolean variableValuesIgnoreCase = true; // Boolean | Match all variable values provided in `variableValues` case-insensitively. If set to `true` **variableValue** and **variablevalue** are treated as equal.
        String variableScopeIdIn = "variableScopeIdIn_example"; // String | Only include variable instances which belong to one of passed scope ids.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        try {
            CountResultDto result = apiInstance.getVariableInstancesCount(variableName, variableNameLike, processInstanceIdIn, executionIdIn, caseInstanceIdIn, caseExecutionIdIn, taskIdIn, batchIdIn, activityInstanceIdIn, tenantIdIn, variableValues, variableNamesIgnoreCase, variableValuesIgnoreCase, variableScopeIdIn, sortBy, sortOrder);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VariableInstanceApi#getVariableInstancesCount");
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
 **variableName** | **String**| Filter by variable instance name. | [optional]
 **variableNameLike** | **String**| Filter by the variable instance name. The parameter can include the wildcard &#x60;%&#x60; to express like-strategy such as: starts with (&#x60;%&#x60;name), ends with (name&#x60;%&#x60;) or contains (&#x60;%&#x60;name&#x60;%&#x60;). | [optional]
 **processInstanceIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated process instance ids. | [optional]
 **executionIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated execution ids. | [optional]
 **caseInstanceIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated case instance ids. | [optional]
 **caseExecutionIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated case execution ids. | [optional]
 **taskIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated task ids. | [optional]
 **batchIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated batch ids. | [optional]
 **activityInstanceIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated activity instance ids. | [optional]
 **tenantIdIn** | **String**| Only include variable instances which belong to one of the passed and comma-separated tenant ids. | [optional]
 **variableValues** | **String**| Only include variable instances that have the certain values. Value filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note:** Values are always treated as &#x60;String&#x60; objects on server side.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. | [optional]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values provided in &#x60;variableValues&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. | [optional]
 **variableScopeIdIn** | **String**| Only include variable instances which belong to one of passed scope ids. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: variableName, variableType, activityInstanceId, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]

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
| **400** | Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryVariableInstances

> List&lt;VariableInstanceDto&gt; queryVariableInstances(firstResult, maxResults, deserializeValues, variableInstanceQueryDto)

Get Variable Instances (POST)

Query for variable instances that fulfill given parameters through a JSON object. This method is slightly more powerful than the [Get Variable Instances](https://docs.camunda.org/manual/7.16/reference/rest/variable- instance/get-query/) method because it allows filtering by multiple variable instances of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.VariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        VariableInstanceApi apiInstance = new VariableInstanceApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
        VariableInstanceQueryDto variableInstanceQueryDto = new VariableInstanceQueryDto(); // VariableInstanceQueryDto | 
        try {
            List<VariableInstanceDto> result = apiInstance.queryVariableInstances(firstResult, maxResults, deserializeValues, variableInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VariableInstanceApi#queryVariableInstances");
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
 **variableInstanceQueryDto** | [**VariableInstanceQueryDto**](VariableInstanceQueryDto.md)|  | [optional]

### Return type

[**List&lt;VariableInstanceDto&gt;**](VariableInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryVariableInstancesCount

> CountResultDto queryVariableInstancesCount(variableInstanceQueryDto)

Get Variable Instance Count (POST)

Query for the number of variable instances that fulfill given parameters. This method takes the same message body as the [Get Variable Instances POST](https://docs.camunda.org/manual/7.16/reference/rest/variable- instance/post-query/) method and therefore it is slightly more powerful than the [Get Variable Instance Count](https://docs.camunda.org/manual/7.16/reference/rest/variable-instance/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.VariableInstanceApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        VariableInstanceApi apiInstance = new VariableInstanceApi(defaultClient);
        VariableInstanceQueryDto variableInstanceQueryDto = new VariableInstanceQueryDto(); // VariableInstanceQueryDto | 
        try {
            CountResultDto result = apiInstance.queryVariableInstancesCount(variableInstanceQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling VariableInstanceApi#queryVariableInstancesCount");
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
 **variableInstanceQueryDto** | [**VariableInstanceQueryDto**](VariableInstanceQueryDto.md)|  | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

