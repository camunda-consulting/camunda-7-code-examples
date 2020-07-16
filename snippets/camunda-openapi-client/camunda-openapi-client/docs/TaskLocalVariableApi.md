# TaskLocalVariableApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteTaskLocalVariable**](TaskLocalVariableApi.md#deleteTaskLocalVariable) | **DELETE** /task/{id}/localVariables/{varName} | 
[**getTaskLocalVariable**](TaskLocalVariableApi.md#getTaskLocalVariable) | **GET** /task/{id}/localVariables/{varName} | 
[**getTaskLocalVariableBinary**](TaskLocalVariableApi.md#getTaskLocalVariableBinary) | **GET** /task/{id}/localVariables/{varName}/data | 
[**getTaskLocalVariables**](TaskLocalVariableApi.md#getTaskLocalVariables) | **GET** /task/{id}/localVariables | 
[**modifyTaskLocalVariables**](TaskLocalVariableApi.md#modifyTaskLocalVariables) | **POST** /task/{id}/localVariables | 
[**putTaskLocalVariable**](TaskLocalVariableApi.md#putTaskLocalVariable) | **PUT** /task/{id}/localVariables/{varName} | 
[**setBinaryTaskLocalVariable**](TaskLocalVariableApi.md#setBinaryTaskLocalVariable) | **POST** /task/{id}/localVariables/{varName}/data | 


<a name="deleteTaskLocalVariable"></a>
# **deleteTaskLocalVariable**
> deleteTaskLocalVariable(id, varName)



Removes a local variable from a task by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskLocalVariableApi apiInstance = new TaskLocalVariableApi(defaultClient);
    String id = "id_example"; // String | The id of the task to delete the variable from.
    String varName = "varName_example"; // String | The name of the variable to be removed.
    try {
      apiInstance.deleteTaskLocalVariable(id, varName);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskLocalVariableApi#deleteTaskLocalVariable");
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
 **id** | **String**| The id of the task to delete the variable from. |
 **varName** | **String**| The name of the variable to be removed. |

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
**500** | Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getTaskLocalVariable"></a>
# **getTaskLocalVariable**
> VariableValueDto getTaskLocalVariable(id, varName, deserializeValue)



Retrieves a variable from the context of a given task by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskLocalVariableApi apiInstance = new TaskLocalVariableApi(defaultClient);
    String id = "id_example"; // String | The id of the task to retrieve the variable from.
    String varName = "varName_example"; // String | The name of the variable to get
    Boolean deserializeValue = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      VariableValueDto result = apiInstance.getTaskLocalVariable(id, varName, deserializeValue);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskLocalVariableApi#getTaskLocalVariable");
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
 **id** | **String**| The id of the task to retrieve the variable from. |
 **varName** | **String**| The name of the variable to get |
 **deserializeValue** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

### Return type

[**VariableValueDto**](VariableValueDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getTaskLocalVariableBinary"></a>
# **getTaskLocalVariableBinary**
> File getTaskLocalVariableBinary(id, varName)



Retrieves a binary variable from the context of a given task by id. Applicable for byte array and file variables.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskLocalVariableApi apiInstance = new TaskLocalVariableApi(defaultClient);
    String id = "id_example"; // String | The id of the task to retrieve the variable for.
    String varName = "varName_example"; // String | The name of the variable to retrieve.
    try {
      File result = apiInstance.getTaskLocalVariableBinary(id, varName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskLocalVariableApi#getTaskLocalVariableBinary");
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
 **id** | **String**| The id of the task to retrieve the variable for. |
 **varName** | **String**| The name of the variable to retrieve. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream, text/plain, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful.         For binary variables or files without any MIME type information, a byte stream is returned.         File variables with MIME type information are returned as the saved type.         Additionally, for file variables the Content-Disposition header will be set. |  -  |
**400** | Variable with given id exists but is not a binary variable.See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Variable with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getTaskLocalVariables"></a>
# **getTaskLocalVariables**
> Map&lt;String, VariableValueDto&gt; getTaskLocalVariables(id, deserializeValues)



Retrieves all variables of a given task by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskLocalVariableApi apiInstance = new TaskLocalVariableApi(defaultClient);
    String id = "id_example"; // String | The id of the task to retrieve the variables from.
    Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default `true`).  If set to `true`, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to `false`, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While `true` is the default value for reasons of backward compatibility, we recommend setting this parameter to `false` when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      Map<String, VariableValueDto> result = apiInstance.getTaskLocalVariables(id, deserializeValues);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskLocalVariableApi#getTaskLocalVariables");
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
 **id** | **String**| The id of the task to retrieve the variables from. |
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on the server side (default &#x60;true&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note:** While &#x60;true&#x60; is the default value for reasons of backward compatibility, we recommend setting this parameter to &#x60;false&#x60; when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

### Return type

[**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**500** | Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="modifyTaskLocalVariables"></a>
# **modifyTaskLocalVariables**
> modifyTaskLocalVariables(id, patchVariablesDto)



Updates or deletes the variables in the context of a task. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskLocalVariableApi apiInstance = new TaskLocalVariableApi(defaultClient);
    String id = "id_example"; // String | The id of the task to set variables for.
    PatchVariablesDto patchVariablesDto = {"modifications":{"aVariable":{"value":"aValue","type":"String"},"anotherVariable":{"value":42,"type":"Integer"}},"deletions":["aThirdVariable","FourthVariable"]}; // PatchVariablesDto | 
    try {
      apiInstance.modifyTaskLocalVariables(id, patchVariablesDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskLocalVariableApi#modifyTaskLocalVariables");
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
 **id** | **String**| The id of the task to set variables for. |
 **patchVariablesDto** | [**PatchVariablesDto**](PatchVariablesDto.md)|  | [optional]

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
**400** | The variable value or type is invalid. For example the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | Update or delete could not be executed because the task is &#x60;null&#x60; or does not exist.. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="putTaskLocalVariable"></a>
# **putTaskLocalVariable**
> putTaskLocalVariable(id, varName, variableValueDto)



Sets a variable in the context of a given task.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskLocalVariableApi apiInstance = new TaskLocalVariableApi(defaultClient);
    String id = "id_example"; // String | The id of the task to set the variable for.
    String varName = "varName_example"; // String | The name of the variable to set.
    VariableValueDto variableValueDto = {"value":"someValue","type":"String"}; // VariableValueDto | 
    try {
      apiInstance.putTaskLocalVariable(id, varName, variableValueDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskLocalVariableApi#putTaskLocalVariable");
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
 **id** | **String**| The id of the task to set the variable for. |
 **varName** | **String**| The name of the variable to set. |
 **variableValueDto** | [**VariableValueDto**](VariableValueDto.md)|  | [optional]

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
**400** | The variable name, value or type is invalid, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported or a new transient variable has the name that is already persisted. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The variable name is &#x60;null&#x60;, or the Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="setBinaryTaskLocalVariable"></a>
# **setBinaryTaskLocalVariable**
> setBinaryTaskLocalVariable(id, varName, data, valueType)



Sets the serialized value for a binary variable or the binary value for a file variable.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.TaskLocalVariableApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    TaskLocalVariableApi apiInstance = new TaskLocalVariableApi(defaultClient);
    String id = "id_example"; // String | The id of the task to retrieve the variable for.
    String varName = "varName_example"; // String | The name of the variable to retrieve.
    File data = new File("/path/to/file"); // File | The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory.
    String valueType = "valueType_example"; // String | The name of the variable type. Either Bytes for a byte array variable or File for a file variable.
    try {
      apiInstance.setBinaryTaskLocalVariable(id, varName, data, valueType);
    } catch (ApiException e) {
      System.err.println("Exception when calling TaskLocalVariableApi#setBinaryTaskLocalVariable");
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
 **id** | **String**| The id of the task to retrieve the variable for. |
 **varName** | **String**| The name of the variable to retrieve. |
 **data** | **File**| The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory. | [optional]
 **valueType** | **String**| The name of the variable type. Either Bytes for a byte array variable or File for a file variable. | [optional] [enum: Bytes, File]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Request successful. |  -  |
**400** | The variable value or type is invalid, for example if no filename is set. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | Variable name is &#x60;null&#x60;, or the Task id is &#x60;null&#x60; or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

