# ProcessInstanceApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteAsyncHistoricQueryBased**](ProcessInstanceApi.md#deleteAsyncHistoricQueryBased) | **POST** /process-instance/delete-historic-query-based | 
[**deleteProcessInstance**](ProcessInstanceApi.md#deleteProcessInstance) | **DELETE** /process-instance/{id} | 
[**deleteProcessInstanceVariable**](ProcessInstanceApi.md#deleteProcessInstanceVariable) | **DELETE** /process-instance/{id}/variables/{varName} | 
[**deleteProcessInstancesAsyncOperation**](ProcessInstanceApi.md#deleteProcessInstancesAsyncOperation) | **POST** /process-instance/delete | 
[**getActivityInstanceTree**](ProcessInstanceApi.md#getActivityInstanceTree) | **GET** /process-instance/{id}/activity-instances | 
[**getProcessInstanceVariable**](ProcessInstanceApi.md#getProcessInstanceVariable) | **GET** /process-instance/{id}/variables/{varName} | 
[**getProcessInstanceVariableBinary**](ProcessInstanceApi.md#getProcessInstanceVariableBinary) | **GET** /process-instance/{id}/variables/{varName}/data | 
[**getProcessInstanceVariables**](ProcessInstanceApi.md#getProcessInstanceVariables) | **GET** /process-instance/{id}/variables | 
[**getProcessInstances**](ProcessInstanceApi.md#getProcessInstances) | **GET** /process-instance | 
[**getProcessInstancesCount**](ProcessInstanceApi.md#getProcessInstancesCount) | **GET** /process-instance/count | 
[**modifyProcessInstance**](ProcessInstanceApi.md#modifyProcessInstance) | **POST** /process-instance/{id}/modification | 
[**modifyProcessInstanceAsyncOperation**](ProcessInstanceApi.md#modifyProcessInstanceAsyncOperation) | **POST** /process-instance/{id}/modification-async | 
[**modifyProcessInstanceVariables**](ProcessInstanceApi.md#modifyProcessInstanceVariables) | **POST** /process-instance/{id}/variables | 
[**queryProcessInstances**](ProcessInstanceApi.md#queryProcessInstances) | **POST** /process-instance | 
[**queryProcessInstancesCount**](ProcessInstanceApi.md#queryProcessInstancesCount) | **POST** /process-instance/count | 
[**setProcessInstanceVariable**](ProcessInstanceApi.md#setProcessInstanceVariable) | **PUT** /process-instance/{id}/variables/{varName} | 
[**setProcessInstanceVariableBinary**](ProcessInstanceApi.md#setProcessInstanceVariableBinary) | **POST** /process-instance/{id}/variables/{varName}/data | 
[**setRetriesByProcess**](ProcessInstanceApi.md#setRetriesByProcess) | **POST** /process-instance/job-retries | 
[**setRetriesByProcessHistoricQueryBased**](ProcessInstanceApi.md#setRetriesByProcessHistoricQueryBased) | **POST** /process-instance/job-retries-historic-query-based | 
[**updateSuspensionState**](ProcessInstanceApi.md#updateSuspensionState) | **PUT** /process-instance/suspended | 
[**updateSuspensionStateAsyncOperation**](ProcessInstanceApi.md#updateSuspensionStateAsyncOperation) | **POST** /process-instance/suspended-async | 
[**updateSuspensionStateById**](ProcessInstanceApi.md#updateSuspensionStateById) | **PUT** /process-instance/{id}/suspended | 


<a name="deleteAsyncHistoricQueryBased"></a>
# **deleteAsyncHistoricQueryBased**
> BatchDto deleteAsyncHistoricQueryBased(deleteProcessInstancesDto)



Deletes a set of process instances asynchronously (batch) based on a historic process instance query.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    DeleteProcessInstancesDto deleteProcessInstancesDto = {"deleteReason":"aReason","historicProcessInstanceQuery":{"startedBefore":"2017-04-28T11:24:37.765+0200"},"skipCustomListeners":true,"skipSubprocesses":true}; // DeleteProcessInstancesDto | **Unallowed property**: `processInstanceQuery`
    try {
      BatchDto result = apiInstance.deleteAsyncHistoricQueryBased(deleteProcessInstancesDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#deleteAsyncHistoricQueryBased");
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
 **deleteProcessInstancesDto** | [**DeleteProcessInstancesDto**](DeleteProcessInstancesDto.md)| **Unallowed property**: &#x60;processInstanceQuery&#x60; | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the query parameters are invalid, i.e., neither processInstanceIds, nor historicProcessInstanceQuery is present |  -  |

<a name="deleteProcessInstance"></a>
# **deleteProcessInstance**
> deleteProcessInstance(id, skipCustomListeners, skipIoMappings, skipSubprocesses, failIfNotExists)



Deletes a running process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to be deleted.
    Boolean skipCustomListeners = false; // Boolean | If set to true, the custom listeners will be skipped.
    Boolean skipIoMappings = false; // Boolean | If set to true, the input/output mappings will be skipped.
    Boolean skipSubprocesses = false; // Boolean | If set to true, subprocesses related to deleted processes will be skipped.
    Boolean failIfNotExists = true; // Boolean | If set to false, the request will still be successful if the process id is not found.
    try {
      apiInstance.deleteProcessInstance(id, skipCustomListeners, skipIoMappings, skipSubprocesses, failIfNotExists);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#deleteProcessInstance");
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
 **id** | **String**| The id of the process instance to be deleted. |
 **skipCustomListeners** | **Boolean**| If set to true, the custom listeners will be skipped. | [optional] [default to false]
 **skipIoMappings** | **Boolean**| If set to true, the input/output mappings will be skipped. | [optional] [default to false]
 **skipSubprocesses** | **Boolean**| If set to true, subprocesses related to deleted processes will be skipped. | [optional] [default to false]
 **failIfNotExists** | **Boolean**| If set to false, the request will still be successful if the process id is not found. | [optional] [default to true]

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
**404** | Not found Process instance with given id does not exist.  |  -  |

<a name="deleteProcessInstanceVariable"></a>
# **deleteProcessInstanceVariable**
> deleteProcessInstanceVariable(id, varName)



Deletes a variable of a process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to delete the variable from.
    String varName = "varName_example"; // String | The name of the variable to delete.
    try {
      apiInstance.deleteProcessInstanceVariable(id, varName);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#deleteProcessInstanceVariable");
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
 **id** | **String**| The id of the process instance to delete the variable from. |
 **varName** | **String**| The name of the variable to delete. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Request successful. |  -  |

<a name="deleteProcessInstancesAsyncOperation"></a>
# **deleteProcessInstancesAsyncOperation**
> BatchDto deleteProcessInstancesAsyncOperation(deleteProcessInstancesDto)



Deletes multiple process instances asynchronously (batch).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    DeleteProcessInstancesDto deleteProcessInstancesDto = {"deleteReason":"aReason","processInstanceIds":["aProcess","secondProcess"],"skipCustomListeners":true,"skipSubprocesses":true}; // DeleteProcessInstancesDto | **Unallowed property**: `historicProcessInstanceQuery`
    try {
      BatchDto result = apiInstance.deleteProcessInstancesAsyncOperation(deleteProcessInstancesDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#deleteProcessInstancesAsyncOperation");
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
 **deleteProcessInstancesDto** | [**DeleteProcessInstancesDto**](DeleteProcessInstancesDto.md)| **Unallowed property**: &#x60;historicProcessInstanceQuery&#x60; | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the query parameters are invalid, i.e., neither processInstanceIds, nor processInstanceQuery is present |  -  |

<a name="getActivityInstanceTree"></a>
# **getActivityInstanceTree**
> ActivityInstanceDto getActivityInstanceTree(id)



Retrieves an Activity Instance (Tree) for a given process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance for which the activity instance should be retrieved.
    try {
      ActivityInstanceDto result = apiInstance.getActivityInstanceTree(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#getActivityInstanceTree");
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
 **id** | **String**| The id of the process instance for which the activity instance should be retrieved. |

### Return type

[**ActivityInstanceDto**](ActivityInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**500** | Process instance with given id does not exist. |  -  |

<a name="getProcessInstanceVariable"></a>
# **getProcessInstanceVariable**
> VariableValueDto getProcessInstanceVariable(id, varName, deserializeValue)



Retrieves a variable of a given process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to retrieve the variable for.
    String varName = "varName_example"; // String | The name of the variable to retrieve.
    Boolean deserializeValue = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      VariableValueDto result = apiInstance.getProcessInstanceVariable(id, varName, deserializeValue);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#getProcessInstanceVariable");
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
 **id** | **String**| The id of the process instance to retrieve the variable for. |
 **varName** | **String**| The name of the variable to retrieve. |
 **deserializeValue** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

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
**400** | Bad Request Variable with given id does not exist. |  -  |

<a name="getProcessInstanceVariableBinary"></a>
# **getProcessInstanceVariableBinary**
> File getProcessInstanceVariableBinary(id, varName)



Retrieves the content of a Process Variable by the Process Instance id and the Process Variable name. Applicable for byte array or file Process Variables.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to retrieve the variable for.
    String varName = "varName_example"; // String | The name of the variable to retrieve.
    try {
      File result = apiInstance.getProcessInstanceVariableBinary(id, varName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#getProcessInstanceVariableBinary");
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
 **id** | **String**| The id of the process instance to retrieve the variable for. |
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
**400** | Bad Request A Process Variable with the given id exists but does not serialize as binary data. |  -  |
**404** | Not Found A Process Variable with the given id does not exist.  |  -  |

<a name="getProcessInstanceVariables"></a>
# **getProcessInstanceVariables**
> Map&lt;String, VariableValueDto&gt; getProcessInstanceVariables(id, deserializeValue)



Retrieves all variables of a given process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to retrieve the variables from.
    Boolean deserializeValue = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      Map<String, VariableValueDto> result = apiInstance.getProcessInstanceVariables(id, deserializeValue);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#getProcessInstanceVariables");
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
 **id** | **String**| The id of the process instance to retrieve the variables from. |
 **deserializeValue** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  Note: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

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
**500** | Process instance with given id does not exist. |  -  |

<a name="getProcessInstances"></a>
# **getProcessInstances**
> List&lt;ProcessInstanceDto&gt; getProcessInstances(sortBy, sortOrder, firstResult, maxResults, processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase)



Queries for process instances that fulfill given parameters. Parameters may be static as well as dynamic runtime properties of process instances. The size of the result set can be retrieved by using the Get Instance Count method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
    String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    String processInstanceIds = "processInstanceIds_example"; // String | Filter by a comma-separated list of process instance ids.
    String businessKey = "businessKey_example"; // String | Filter by process instance business key.
    String businessKeyLike = "businessKeyLike_example"; // String | Filter by process instance business key that the parameter is a substring of.
    String caseInstanceId = "caseInstanceId_example"; // String | Filter by case instance id.
    String processDefinitionId = "processDefinitionId_example"; // String | Filter by the deployment the id belongs to.
    String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the instances run on.
    String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys.
    String processDefinitionKeyNotIn = "processDefinitionKeyNotIn_example"; // String | Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys.
    String deploymentId = "deploymentId_example"; // String | Filter by the deployment the id belongs to.
    String superProcessInstance = "superProcessInstance_example"; // String | Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id.
    String subProcessInstance = "subProcessInstance_example"; // String | Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id.
    String superCaseInstance = "superCaseInstance_example"; // String | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id.
    String subCaseInstance = "subCaseInstance_example"; // String | Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id.
    Boolean active = false; // Boolean | Only include active process instances. Value may only be true, as false is the default behavior.
    Boolean suspended = false; // Boolean | Only include suspended process instances. Value may only be true, as false is the default behavior.
    Boolean withIncident = false; // Boolean | Filter by presence of incidents. Selects only process instances that have an incident.
    String incidentId = "incidentId_example"; // String | Filter by the incident id.
    String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
    String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids.
    Boolean withoutTenantId = false; // Boolean | Only include process instances which belong to no tenant.
    Boolean processDefinitionWithoutTenantId = false; // Boolean | Only include process instances which process definition has no tenant id.
    String activityIdIn = "activityIdIn_example"; // String | Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids.
    Boolean rootProcessInstances = false; // Boolean | Restrict the query to all process instances that are top level process instances.
    Boolean leafProcessInstances = false; // Boolean | Restrict the query to all process instances that are leaf instances. (i.e. don't have any sub instances).
    String variables = "variables_example"; // String | Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    Boolean variableNamesIgnoreCase = false; // Boolean | Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal.
    Boolean variableValuesIgnoreCase = false; // Boolean | Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal.
    try {
      List<ProcessInstanceDto> result = apiInstance.getProcessInstances(sortBy, sortOrder, firstResult, maxResults, processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#getProcessInstances");
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
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: instanceId, definitionKey, definitionId, tenantId, businessKey]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]
 **processInstanceIds** | **String**| Filter by a comma-separated list of process instance ids. | [optional]
 **businessKey** | **String**| Filter by process instance business key. | [optional]
 **businessKeyLike** | **String**| Filter by process instance business key that the parameter is a substring of. | [optional]
 **caseInstanceId** | **String**| Filter by case instance id. | [optional]
 **processDefinitionId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the instances run on. | [optional]
 **processDefinitionKeyIn** | **String**| Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys. | [optional]
 **processDefinitionKeyNotIn** | **String**| Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys. | [optional]
 **deploymentId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **superProcessInstance** | **String**| Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. | [optional]
 **subProcessInstance** | **String**| Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id. | [optional]
 **superCaseInstance** | **String**| Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. | [optional]
 **subCaseInstance** | **String**| Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id. | [optional]
 **active** | **Boolean**| Only include active process instances. Value may only be true, as false is the default behavior. | [optional] [default to false]
 **suspended** | **Boolean**| Only include suspended process instances. Value may only be true, as false is the default behavior. | [optional] [default to false]
 **withIncident** | **Boolean**| Filter by presence of incidents. Selects only process instances that have an incident. | [optional] [default to false]
 **incidentId** | **String**| Filter by the incident id. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include process instances which belong to no tenant. | [optional] [default to false]
 **processDefinitionWithoutTenantId** | **Boolean**| Only include process instances which process definition has no tenant id. | [optional] [default to false]
 **activityIdIn** | **String**| Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids. | [optional]
 **rootProcessInstances** | **Boolean**| Restrict the query to all process instances that are top level process instances. | [optional] [default to false]
 **leafProcessInstances** | **Boolean**| Restrict the query to all process instances that are leaf instances. (i.e. don&#39;t have any sub instances). | [optional] [default to false]
 **variables** | **String**| Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal. | [optional] [default to false]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal. | [optional] [default to false]

### Return type

[**List&lt;ProcessInstanceDto&gt;**](ProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy, or if an invalid operator for variable comparison is used. |  -  |

<a name="getProcessInstancesCount"></a>
# **getProcessInstancesCount**
> CountResultDto getProcessInstancesCount(processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase)



Queries for the number of process instances that fulfill given parameters.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String processInstanceIds = "processInstanceIds_example"; // String | Filter by a comma-separated list of process instance ids.
    String businessKey = "businessKey_example"; // String | Filter by process instance business key.
    String businessKeyLike = "businessKeyLike_example"; // String | Filter by process instance business key that the parameter is a substring of.
    String caseInstanceId = "caseInstanceId_example"; // String | Filter by case instance id.
    String processDefinitionId = "processDefinitionId_example"; // String | Filter by the deployment the id belongs to.
    String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the instances run on.
    String processDefinitionKeyIn = "processDefinitionKeyIn_example"; // String | Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys.
    String processDefinitionKeyNotIn = "processDefinitionKeyNotIn_example"; // String | Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys.
    String deploymentId = "deploymentId_example"; // String | Filter by the deployment the id belongs to.
    String superProcessInstance = "superProcessInstance_example"; // String | Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id.
    String subProcessInstance = "subProcessInstance_example"; // String | Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id.
    String superCaseInstance = "superCaseInstance_example"; // String | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id.
    String subCaseInstance = "subCaseInstance_example"; // String | Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id.
    Boolean active = false; // Boolean | Only include active process instances. Value may only be true, as false is the default behavior.
    Boolean suspended = false; // Boolean | Only include suspended process instances. Value may only be true, as false is the default behavior.
    Boolean withIncident = false; // Boolean | Filter by presence of incidents. Selects only process instances that have an incident.
    String incidentId = "incidentId_example"; // String | Filter by the incident id.
    String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
    String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids.
    Boolean withoutTenantId = false; // Boolean | Only include process instances which belong to no tenant.
    Boolean processDefinitionWithoutTenantId = false; // Boolean | Only include process instances which process definition has no tenant id.
    String activityIdIn = "activityIdIn_example"; // String | Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids.
    Boolean rootProcessInstances = false; // Boolean | Restrict the query to all process instances that are top level process instances.
    Boolean leafProcessInstances = false; // Boolean | Restrict the query to all process instances that are leaf instances. (i.e. don't have any sub instances).
    String variables = "variables_example"; // String | Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form `key_operator_value`. `key` is the variable name, `operator` is the comparison operator to be used and `value` the variable value.  **Note**: Values are always treated as String objects on server side.  Valid `operator` values are: `eq` - equal to; `neq` - not equal to; `gt` - greater than; `gteq` - greater than or equal to; `lt` - lower than; `lteq` - lower than or equal to; `like`. `key` and `value` may not contain underscore or comma characters.
    Boolean variableNamesIgnoreCase = false; // Boolean | Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal.
    Boolean variableValuesIgnoreCase = false; // Boolean | Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal.
    try {
      CountResultDto result = apiInstance.getProcessInstancesCount(processInstanceIds, businessKey, businessKeyLike, caseInstanceId, processDefinitionId, processDefinitionKey, processDefinitionKeyIn, processDefinitionKeyNotIn, deploymentId, superProcessInstance, subProcessInstance, superCaseInstance, subCaseInstance, active, suspended, withIncident, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, processDefinitionWithoutTenantId, activityIdIn, rootProcessInstances, leafProcessInstances, variables, variableNamesIgnoreCase, variableValuesIgnoreCase);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#getProcessInstancesCount");
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
 **processInstanceIds** | **String**| Filter by a comma-separated list of process instance ids. | [optional]
 **businessKey** | **String**| Filter by process instance business key. | [optional]
 **businessKeyLike** | **String**| Filter by process instance business key that the parameter is a substring of. | [optional]
 **caseInstanceId** | **String**| Filter by case instance id. | [optional]
 **processDefinitionId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the instances run on. | [optional]
 **processDefinitionKeyIn** | **String**| Filter by a comma-separated list of process definition keys. A process instance must have one of the given process definition keys. | [optional]
 **processDefinitionKeyNotIn** | **String**| Exclude instances by a comma-separated list of process definition keys. A process instance must not have one of the given process definition keys. | [optional]
 **deploymentId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **superProcessInstance** | **String**| Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. | [optional]
 **subProcessInstance** | **String**| Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id. | [optional]
 **superCaseInstance** | **String**| Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. | [optional]
 **subCaseInstance** | **String**| Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id. | [optional]
 **active** | **Boolean**| Only include active process instances. Value may only be true, as false is the default behavior. | [optional] [default to false]
 **suspended** | **Boolean**| Only include suspended process instances. Value may only be true, as false is the default behavior. | [optional] [default to false]
 **withIncident** | **Boolean**| Filter by presence of incidents. Selects only process instances that have an incident. | [optional] [default to false]
 **incidentId** | **String**| Filter by the incident id. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A process instance must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include process instances which belong to no tenant. | [optional] [default to false]
 **processDefinitionWithoutTenantId** | **Boolean**| Only include process instances which process definition has no tenant id. | [optional] [default to false]
 **activityIdIn** | **String**| Filter by a comma-separated list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids. | [optional]
 **rootProcessInstances** | **Boolean**| Restrict the query to all process instances that are top level process instances. | [optional] [default to false]
 **leafProcessInstances** | **Boolean**| Restrict the query to all process instances that are leaf instances. (i.e. don&#39;t have any sub instances). | [optional] [default to false]
 **variables** | **String**| Only include process instances that have variables with certain values. Variable filtering expressions are comma-separated and are structured as follows:  A valid parameter value has the form &#x60;key_operator_value&#x60;. &#x60;key&#x60; is the variable name, &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value.  **Note**: Values are always treated as String objects on server side.  Valid &#x60;operator&#x60; values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. &#x60;key&#x60; and &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **variableNamesIgnoreCase** | **Boolean**| Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal. | [optional] [default to false]
 **variableValuesIgnoreCase** | **Boolean**| Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal. | [optional] [default to false]

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
**400** | Bad Request Returned if some of the query parameters are invalid, for example an invalid operator for variable comparison is used. |  -  |

<a name="modifyProcessInstance"></a>
# **modifyProcessInstance**
> modifyProcessInstance(id, processInstanceModificationDto)



Submits a list of modification instructions to change a process instance&#39;s execution state. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Canceling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed immediately and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/develop/user-guide/process-engine/process-instance-modification/).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to modify.
    ProcessInstanceModificationDto processInstanceModificationDto = {"skipCustomListeners":true,"skipIoMappings":true,"instructions":[{"type":"startBeforeActivity","activityId":"activityId","variables":{"var":{"value":"aVariableValue","local":false,"type":"String"},"varLocal":{"value":"anotherVariableValue","local":true,"type":"String"}}},{"type":"cancel","activityInstanceId":"anActivityInstanceId"}],"annotation":"Modified to resolve an error."}; // ProcessInstanceModificationDto | 
    try {
      apiInstance.modifyProcessInstance(id, processInstanceModificationDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#modifyProcessInstance");
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
 **id** | **String**| The id of the process instance to modify. |
 **processInstanceModificationDto** | [**ProcessInstanceModificationDto**](ProcessInstanceModificationDto.md)|  | [optional]

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
**400** | At least one modification instruction misses required parameters. |  -  |
**500** | The modification cannot be performed, for example because it starts a failing activity. |  -  |

<a name="modifyProcessInstanceAsyncOperation"></a>
# **modifyProcessInstanceAsyncOperation**
> BatchDto modifyProcessInstanceAsyncOperation(id, processInstanceModificationDto)



Submits a list of modification instructions to change a process instance&#39;s execution state async. A modification instruction is one of the following:  * Starting execution before an activity * Starting execution after an activity on its single outgoing sequence flow * Starting execution on a specific sequence flow * Cancelling an activity instance, transition instance, or all instances (activity or transition) for an activity  Instructions are executed asynchronous and in the order they are provided in this request&#39;s body. Variables can be provided with every starting instruction.  The exact semantics of modification can be read about in the [User guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/process-instance-modification/).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to modify.
    ProcessInstanceModificationDto processInstanceModificationDto = {"skipCustomListeners":true,"skipIoMappings":true,"instructions":[{"type":"startBeforeActivity","activityId":"activityId"},{"type":"cancel","activityInstanceId":"anActivityInstanceId"}],"annotation":"Modified to resolve an error."}; // ProcessInstanceModificationDto | 
    try {
      BatchDto result = apiInstance.modifyProcessInstanceAsyncOperation(id, processInstanceModificationDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#modifyProcessInstanceAsyncOperation");
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
 **id** | **String**| The id of the process instance to modify. |
 **processInstanceModificationDto** | [**ProcessInstanceModificationDto**](ProcessInstanceModificationDto.md)|  | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request At least one modification instruction misses required parameters. |  -  |
**403** | Forbidden If the user is not allowed to execute batches. See the Introduction for the error response format. |  -  |
**500** | The modification cannot be performed, for example because it starts a failing activity. |  -  |

<a name="modifyProcessInstanceVariables"></a>
# **modifyProcessInstanceVariables**
> modifyProcessInstanceVariables(id, patchVariablesDto)



Updates or deletes the variables of a process instance by id. Updates precede deletions. So, if a variable is updated AND deleted, the deletion overrides the update.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to set variables for.
    PatchVariablesDto patchVariablesDto = {"modifications":{"aVariable":{"value":"aValue","type":"String"},"anotherVariable":{"value":42,"type":"Integer"}},"deletions":["aThirdVariable","FourthVariable"]}; // PatchVariablesDto | 
    try {
      apiInstance.modifyProcessInstanceVariables(id, patchVariablesDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#modifyProcessInstanceVariables");
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
 **id** | **String**| The id of the process instance to set variables for. |
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
**400** | Bad Request The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. |  -  |
**500** | Update or delete could not be executed, for example because the process instance does not exist. |  -  |

<a name="queryProcessInstances"></a>
# **queryProcessInstances**
> List&lt;ProcessInstanceDto&gt; queryProcessInstances(firstResult, maxResults, processInstanceQueryDto)



Queries for process instances that fulfill given parameters through a JSON object. This method is slightly more powerful than the Get Instances method because it allows filtering by multiple process variables of types &#x60;string&#x60;, &#x60;number&#x60; or &#x60;boolean&#x60;.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    ProcessInstanceQueryDto processInstanceQueryDto = {"variables":[{"name":"myVariable","operator":"eq","value":"camunda"},{"name":"mySecondVariable","operator":"neq","value":124}],"processDefinitionId":"aProcessDefinitionId","sorting":[{"sortBy":"definitionKey","sortOrder":"asc"},{"sortBy":"instanceId","sortOrder":"desc"}]}; // ProcessInstanceQueryDto | 
    try {
      List<ProcessInstanceDto> result = apiInstance.queryProcessInstances(firstResult, maxResults, processInstanceQueryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#queryProcessInstances");
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
 **processInstanceQueryDto** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md)|  | [optional]

### Return type

[**List&lt;ProcessInstanceDto&gt;**](ProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the query parameters are invalid, for example if a sortOrder parameter is supplied, but no sortBy, or if an invalid operator for variable comparison is used. |  -  |

<a name="queryProcessInstancesCount"></a>
# **queryProcessInstancesCount**
> CountResultDto queryProcessInstancesCount(processInstanceQueryDto)



Queries for the number of process instances that fulfill the given parameters. This method takes the same message body as the Get Instances (POST) method and therefore it is slightly more powerful than the Get Instance Count method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    ProcessInstanceQueryDto processInstanceQueryDto = {"variables":[{"name":"myVariable","operator":"eq","value":"camunda"},{"name":"mySecondVariable","operator":"neq","value":124}],"processDefinitionId":"aProcessDefinitionId"}; // ProcessInstanceQueryDto | 
    try {
      CountResultDto result = apiInstance.queryProcessInstancesCount(processInstanceQueryDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#queryProcessInstancesCount");
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
 **processInstanceQueryDto** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md)|  | [optional]

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
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the query parameters are invalid, for example if an invalid operator for variable comparison is used. |  -  |

<a name="setProcessInstanceVariable"></a>
# **setProcessInstanceVariable**
> setProcessInstanceVariable(id, varName, variableValueDto)



Sets a variable of a given process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to set the variable for.
    String varName = "varName_example"; // String | The name of the variable to set.
    VariableValueDto variableValueDto = {"value":"someValue","type":"String"}; // VariableValueDto | 
    try {
      apiInstance.setProcessInstanceVariable(id, varName, variableValueDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#setProcessInstanceVariable");
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
 **id** | **String**| The id of the process instance to set the variable for. |
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
**400** | Bad Request The variable value or type is invalid, for example if the value could not be parsed to an Integer value or the passed variable type is not supported. |  -  |

<a name="setProcessInstanceVariableBinary"></a>
# **setProcessInstanceVariableBinary**
> setProcessInstanceVariableBinary(id, varName, data, valueType)



Sets the serialized value for a binary variable or the binary value for a file variable.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to retrieve the variable for.
    String varName = "varName_example"; // String | The name of the variable to retrieve.
    File data = new File("/path/to/file"); // File | The binary data to be set. For File variables, this multipart can contain the filename, binary value and MIME type of the file variable to be set Only the filename is mandatory.
    String valueType = "valueType_example"; // String | The name of the variable type. Either Bytes for a byte array variable or File for a file variable.
    try {
      apiInstance.setProcessInstanceVariableBinary(id, varName, data, valueType);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#setProcessInstanceVariableBinary");
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
 **id** | **String**| The id of the process instance to retrieve the variable for. |
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
**400** | Bad Request The variable value or type is invalid, for example if no filename is set. |  -  |

<a name="setRetriesByProcess"></a>
# **setRetriesByProcess**
> BatchDto setRetriesByProcess(setJobRetriesByProcessDto)



Create a batch to set retries of jobs associated with given processes asynchronously.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    SetJobRetriesByProcessDto setJobRetriesByProcessDto = {"retries":5,"processInstances":["aProcess","secondProcess"],"processInstanceQuery":{"processDefinitionId":"aProcessDefinitionId"}}; // SetJobRetriesByProcessDto | Please note that if both processInstances and processInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: `historicProcessInstanceQuery`
    try {
      BatchDto result = apiInstance.setRetriesByProcess(setJobRetriesByProcessDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#setRetriesByProcess");
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
 **setJobRetriesByProcessDto** | [**SetJobRetriesByProcessDto**](SetJobRetriesByProcessDto.md)| Please note that if both processInstances and processInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: &#x60;historicProcessInstanceQuery&#x60; | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the query parameters are invalid, for example if neither processInstanceIds, nor processInstanceQuery is present. Or if the retry count is not specified. |  -  |

<a name="setRetriesByProcessHistoricQueryBased"></a>
# **setRetriesByProcessHistoricQueryBased**
> BatchDto setRetriesByProcessHistoricQueryBased(setJobRetriesByProcessDto)



Create a batch to set retries of jobs asynchronously based on a historic process instance query.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    SetJobRetriesByProcessDto setJobRetriesByProcessDto = {"retries":5,"historicProcessInstanceQuery":{"startedBefore":"2017-04-28T11:24:37.769+0200"},"processInstances":["aProcess","secondProcess"]}; // SetJobRetriesByProcessDto | Please note that if both processInstances and historicProcessInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: `processInstanceQuery`
    try {
      BatchDto result = apiInstance.setRetriesByProcessHistoricQueryBased(setJobRetriesByProcessDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#setRetriesByProcessHistoricQueryBased");
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
 **setJobRetriesByProcessDto** | [**SetJobRetriesByProcessDto**](SetJobRetriesByProcessDto.md)| Please note that if both processInstances and historicProcessInstanceQuery are provided, then the resulting execution will be performed on the union of these sets. **Unallowed property**: &#x60;processInstanceQuery&#x60; | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the query parameters are invalid, for example if neither processInstanceIds, nor historicProcessInstanceQuery is present. Or if the retry count is not specified. |  -  |

<a name="updateSuspensionState"></a>
# **updateSuspensionState**
> updateSuspensionState(processInstanceSuspensionStateDto)



Activates or suspends process instances by providing certain criteria:  # Activate/Suspend Process Instance By Process Definition Id * &#x60;suspend&#x60; * &#x60;processDefinitionId&#x60;  # Activate/Suspend Process Instance By Process Definition Key  * &#x60;suspend&#x60; * &#x60;processDefinitionKey&#x60; * &#x60;processDefinitionTenantId&#x60; * &#x60;processDefinitionWithoutTenantId&#x60;  # Activate/Suspend Process Instance In Group * &#x60;suspend&#x60; * &#x60;processInstanceIds&#x60; * &#x60;processInstanceQuery&#x60; * &#x60;historicProcessInstanceQuery&#x60;

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    ProcessInstanceSuspensionStateDto processInstanceSuspensionStateDto = {"processDefinitionId":"aProcDefId","suspended":true}; // ProcessInstanceSuspensionStateDto | 
    try {
      apiInstance.updateSuspensionState(processInstanceSuspensionStateDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#updateSuspensionState");
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
 **processInstanceSuspensionStateDto** | [**ProcessInstanceSuspensionStateDto**](ProcessInstanceSuspensionStateDto.md)|  | [optional]

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
**400** | Bad Request Returned if some of the request parameters are invalid, for example if the provided processDefinitionId or processDefinitionKey parameter is null. |  -  |

<a name="updateSuspensionStateAsyncOperation"></a>
# **updateSuspensionStateAsyncOperation**
> BatchDto updateSuspensionStateAsyncOperation(processInstanceSuspensionStateAsyncDto)



Activates or suspends process instances asynchronously with a list of process instance ids, a process instance query, and/or a historical process instance query.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    ProcessInstanceSuspensionStateAsyncDto processInstanceSuspensionStateAsyncDto = {"processInstanceIds":["processInstanceId1","processInstanceIdN"],"suspended":true}; // ProcessInstanceSuspensionStateAsyncDto | 
    try {
      BatchDto result = apiInstance.updateSuspensionStateAsyncOperation(processInstanceSuspensionStateAsyncDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#updateSuspensionStateAsyncOperation");
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
 **processInstanceSuspensionStateAsyncDto** | [**ProcessInstanceSuspensionStateAsyncDto**](ProcessInstanceSuspensionStateAsyncDto.md)|  | [optional]

### Return type

[**BatchDto**](BatchDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Bad Request Returned if some of the request parameters are invalid, for example if the provided processDefinitionId or processDefinitionKey parameter is null. |  -  |

<a name="updateSuspensionStateById"></a>
# **updateSuspensionStateById**
> updateSuspensionStateById(id, suspensionStateDto)



Activates or suspends a given process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessInstanceApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessInstanceApi apiInstance = new ProcessInstanceApi(defaultClient);
    String id = "id_example"; // String | The id of the process instance to activate or suspend.
    SuspensionStateDto suspensionStateDto = {"suspended":true}; // SuspensionStateDto | 
    try {
      apiInstance.updateSuspensionStateById(id, suspensionStateDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessInstanceApi#updateSuspensionStateById");
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
 **id** | **String**| The id of the process instance to activate or suspend. |
 **suspensionStateDto** | [**SuspensionStateDto**](SuspensionStateDto.md)|  | [optional]

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
**204** | Request successful. |  -  |

