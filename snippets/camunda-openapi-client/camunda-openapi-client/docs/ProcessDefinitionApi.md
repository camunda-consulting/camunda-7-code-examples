# ProcessDefinitionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteProcessDefinition**](ProcessDefinitionApi.md#deleteProcessDefinition) | **DELETE** /process-definition/{id} | Delete
[**deleteProcessDefinitionsByKey**](ProcessDefinitionApi.md#deleteProcessDefinitionsByKey) | **DELETE** /process-definition/key/{key} | Delete By Key
[**deleteProcessDefinitionsByKeyAndTenantId**](ProcessDefinitionApi.md#deleteProcessDefinitionsByKeyAndTenantId) | **DELETE** /process-definition/key/{key}/tenant/{tenant-id} | Delete By Key
[**getActivityStatistics**](ProcessDefinitionApi.md#getActivityStatistics) | **GET** /process-definition/{id}/statistics | Get Activity Instance Statistics
[**getActivityStatisticsByProcessDefinitionKey**](ProcessDefinitionApi.md#getActivityStatisticsByProcessDefinitionKey) | **GET** /process-definition/key/{key}/statistics | Get Activity Instance Statistics
[**getActivityStatisticsByProcessDefinitionKeyAndTenantId**](ProcessDefinitionApi.md#getActivityStatisticsByProcessDefinitionKeyAndTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id}/statistics | Get Activity Instance Statistics
[**getDeployedStartForm**](ProcessDefinitionApi.md#getDeployedStartForm) | **GET** /process-definition/{id}/deployed-start-form | Get Deployed Start Form
[**getDeployedStartFormByKey**](ProcessDefinitionApi.md#getDeployedStartFormByKey) | **GET** /process-definition/key/{key}/deployed-start-form | Get Deployed Start Form
[**getDeployedStartFormByKeyAndTenantId**](ProcessDefinitionApi.md#getDeployedStartFormByKeyAndTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id}/deployed-start-form | Get Deployed Start Form
[**getLatestProcessDefinitionByTenantId**](ProcessDefinitionApi.md#getLatestProcessDefinitionByTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id} | Get
[**getProcessDefinition**](ProcessDefinitionApi.md#getProcessDefinition) | **GET** /process-definition/{id} | Get
[**getProcessDefinitionBpmn20Xml**](ProcessDefinitionApi.md#getProcessDefinitionBpmn20Xml) | **GET** /process-definition/{id}/xml | Get XML
[**getProcessDefinitionBpmn20XmlByKey**](ProcessDefinitionApi.md#getProcessDefinitionBpmn20XmlByKey) | **GET** /process-definition/key/{key}/xml | Get XML
[**getProcessDefinitionBpmn20XmlByKeyAndTenantId**](ProcessDefinitionApi.md#getProcessDefinitionBpmn20XmlByKeyAndTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id}/xml | Get XML
[**getProcessDefinitionByKey**](ProcessDefinitionApi.md#getProcessDefinitionByKey) | **GET** /process-definition/key/{key} | Get
[**getProcessDefinitionDiagram**](ProcessDefinitionApi.md#getProcessDefinitionDiagram) | **GET** /process-definition/{id}/diagram | Get Diagram
[**getProcessDefinitionDiagramByKey**](ProcessDefinitionApi.md#getProcessDefinitionDiagramByKey) | **GET** /process-definition/key/{key}/diagram | Get Diagram
[**getProcessDefinitionDiagramByKeyAndTenantId**](ProcessDefinitionApi.md#getProcessDefinitionDiagramByKeyAndTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id}/diagram | Get Diagram
[**getProcessDefinitionStatistics**](ProcessDefinitionApi.md#getProcessDefinitionStatistics) | **GET** /process-definition/statistics | Get Process Instance Statistics
[**getProcessDefinitions**](ProcessDefinitionApi.md#getProcessDefinitions) | **GET** /process-definition | Get List
[**getProcessDefinitionsCount**](ProcessDefinitionApi.md#getProcessDefinitionsCount) | **GET** /process-definition/count | Get List Count
[**getRenderedStartForm**](ProcessDefinitionApi.md#getRenderedStartForm) | **GET** /process-definition/{id}/rendered-form | Get Rendered Start Form
[**getRenderedStartFormByKey**](ProcessDefinitionApi.md#getRenderedStartFormByKey) | **GET** /process-definition/key/{key}/rendered-form | Get Rendered Start Form
[**getRenderedStartFormByKeyAndTenantId**](ProcessDefinitionApi.md#getRenderedStartFormByKeyAndTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id}/rendered-form | Get Rendered Start Form
[**getStartForm**](ProcessDefinitionApi.md#getStartForm) | **GET** /process-definition/{id}/startForm | Get Start Form Key
[**getStartFormByKey**](ProcessDefinitionApi.md#getStartFormByKey) | **GET** /process-definition/key/{key}/startForm | Get Start Form Key
[**getStartFormByKeyAndTenantId**](ProcessDefinitionApi.md#getStartFormByKeyAndTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id}/startForm | Get Start Form Key
[**getStartFormVariables**](ProcessDefinitionApi.md#getStartFormVariables) | **GET** /process-definition/{id}/form-variables | Get Start Form Variables
[**getStartFormVariablesByKey**](ProcessDefinitionApi.md#getStartFormVariablesByKey) | **GET** /process-definition/key/{key}/form-variables | Get Start Form Variables
[**getStartFormVariablesByKeyAndTenantId**](ProcessDefinitionApi.md#getStartFormVariablesByKeyAndTenantId) | **GET** /process-definition/key/{key}/tenant/{tenant-id}/form-variables | Get Start Form Variables
[**restartProcessInstance**](ProcessDefinitionApi.md#restartProcessInstance) | **POST** /process-definition/{id}/restart | Restart Process Instance
[**restartProcessInstanceAsyncOperation**](ProcessDefinitionApi.md#restartProcessInstanceAsyncOperation) | **POST** /process-definition/{id}/restart-async | Restart Process Instance Async
[**startProcessInstance**](ProcessDefinitionApi.md#startProcessInstance) | **POST** /process-definition/{id}/start | Start Instance
[**startProcessInstanceByKey**](ProcessDefinitionApi.md#startProcessInstanceByKey) | **POST** /process-definition/key/{key}/start | Start Instance
[**startProcessInstanceByKeyAndTenantId**](ProcessDefinitionApi.md#startProcessInstanceByKeyAndTenantId) | **POST** /process-definition/key/{key}/tenant/{tenant-id}/start | Start Instance
[**submitForm**](ProcessDefinitionApi.md#submitForm) | **POST** /process-definition/{id}/submit-form | Submit Start Form
[**submitFormByKey**](ProcessDefinitionApi.md#submitFormByKey) | **POST** /process-definition/key/{key}/submit-form | Submit Start Form
[**submitFormByKeyAndTenantId**](ProcessDefinitionApi.md#submitFormByKeyAndTenantId) | **POST** /process-definition/key/{key}/tenant/{tenant-id}/submit-form | Submit Start Form
[**updateHistoryTimeToLiveByProcessDefinitionId**](ProcessDefinitionApi.md#updateHistoryTimeToLiveByProcessDefinitionId) | **PUT** /process-definition/{id}/history-time-to-live | Update History Time to Live
[**updateHistoryTimeToLiveByProcessDefinitionKey**](ProcessDefinitionApi.md#updateHistoryTimeToLiveByProcessDefinitionKey) | **PUT** /process-definition/key/{key}/history-time-to-live | Update History Time to Live
[**updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId**](ProcessDefinitionApi.md#updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId) | **PUT** /process-definition/key/{key}/tenant/{tenant-id}/history-time-to-live | Update History Time to Live
[**updateProcessDefinitionSuspensionState**](ProcessDefinitionApi.md#updateProcessDefinitionSuspensionState) | **PUT** /process-definition/suspended | Activate/Suspend By Key
[**updateProcessDefinitionSuspensionStateById**](ProcessDefinitionApi.md#updateProcessDefinitionSuspensionStateById) | **PUT** /process-definition/{id}/suspended | Activate/Suspend By Id
[**updateProcessDefinitionSuspensionStateByKey**](ProcessDefinitionApi.md#updateProcessDefinitionSuspensionStateByKey) | **PUT** /process-definition/key/{key}/suspended | Activate/Suspend by Id
[**updateProcessDefinitionSuspensionStateByKeyAndTenantId**](ProcessDefinitionApi.md#updateProcessDefinitionSuspensionStateByKeyAndTenantId) | **PUT** /process-definition/key/{key}/tenant/{tenant-id}/suspended | Activate/Suspend by Id


<a name="deleteProcessDefinition"></a>
# **deleteProcessDefinition**
> deleteProcessDefinition(id, cascade, skipCustomListeners, skipIoMappings)

Delete

Deletes a running process instance by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to be deleted.
    Boolean cascade = true; // Boolean | `true`, if all process instances, historic process instances and jobs for this process definition should be deleted.
    Boolean skipCustomListeners = false; // Boolean | `true`, if only the built-in ExecutionListeners should be notified with the end event.
    Boolean skipIoMappings = false; // Boolean | A boolean value to control whether input/output mappings should be executed during deletion. `true`, if input/output mappings should not be invoked.
    try {
      apiInstance.deleteProcessDefinition(id, cascade, skipCustomListeners, skipIoMappings);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#deleteProcessDefinition");
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
 **id** | **String**| The id of the process definition to be deleted. |
 **cascade** | **Boolean**| &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. | [optional]
 **skipCustomListeners** | **Boolean**| &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. | [optional] [default to false]
 **skipIoMappings** | **Boolean**| A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. | [optional] [default to false]

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
**404** | Not found Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="deleteProcessDefinitionsByKey"></a>
# **deleteProcessDefinitionsByKey**
> deleteProcessDefinitionsByKey(key, cascade, skipCustomListeners, skipIoMappings)

Delete By Key

Deletes process definitions by a given key which belong to no tenant id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definitions to be deleted.
    Boolean cascade = true; // Boolean | `true`, if all process instances, historic process instances and jobs for this process definition should be deleted.
    Boolean skipCustomListeners = false; // Boolean | `true`, if only the built-in ExecutionListeners should be notified with the end event.
    Boolean skipIoMappings = false; // Boolean | A boolean value to control whether input/output mappings should be executed during deletion. `true`, if input/output mappings should not be invoked.
    try {
      apiInstance.deleteProcessDefinitionsByKey(key, cascade, skipCustomListeners, skipIoMappings);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#deleteProcessDefinitionsByKey");
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
 **key** | **String**| The key of the process definitions to be deleted. |
 **cascade** | **Boolean**| &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. | [optional]
 **skipCustomListeners** | **Boolean**| &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. | [optional] [default to false]
 **skipIoMappings** | **Boolean**| A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. | [optional] [default to false]

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
**403** | Forbidden The process definitions with the given &#x60;key&#x60; cannot be deleted due to missing permissions. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Not found Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="deleteProcessDefinitionsByKeyAndTenantId"></a>
# **deleteProcessDefinitionsByKeyAndTenantId**
> deleteProcessDefinitionsByKeyAndTenantId(key, tenantId, cascade, skipCustomListeners, skipIoMappings)

Delete By Key

Deletes process definitions by a given key and which belong to a tenant id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definitions to be deleted.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definitions belong to.
    Boolean cascade = true; // Boolean | `true`, if all process instances, historic process instances and jobs for this process definition should be deleted.
    Boolean skipCustomListeners = false; // Boolean | `true`, if only the built-in ExecutionListeners should be notified with the end event.
    Boolean skipIoMappings = false; // Boolean | A boolean value to control whether input/output mappings should be executed during deletion. `true`, if input/output mappings should not be invoked.
    try {
      apiInstance.deleteProcessDefinitionsByKeyAndTenantId(key, tenantId, cascade, skipCustomListeners, skipIoMappings);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#deleteProcessDefinitionsByKeyAndTenantId");
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
 **key** | **String**| The key of the process definitions to be deleted. |
 **tenantId** | **String**| The id of the tenant the process definitions belong to. |
 **cascade** | **Boolean**| &#x60;true&#x60;, if all process instances, historic process instances and jobs for this process definition should be deleted. | [optional]
 **skipCustomListeners** | **Boolean**| &#x60;true&#x60;, if only the built-in ExecutionListeners should be notified with the end event. | [optional] [default to false]
 **skipIoMappings** | **Boolean**| A boolean value to control whether input/output mappings should be executed during deletion. &#x60;true&#x60;, if input/output mappings should not be invoked. | [optional] [default to false]

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
**403** | Forbidden The process definitions with the given &#x60;key&#x60; cannot be deleted due to missing permissions. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Not found Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getActivityStatistics"></a>
# **getActivityStatistics**
> List&lt;ActivityStatisticsResultDto&gt; getActivityStatistics(id, failedJobs, incidents, incidentsForType)

Get Activity Instance Statistics

Retrieves runtime statistics of a given process definition, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition.
    Boolean failedJobs = true; // Boolean | Whether to include the number of failed jobs in the result or not. Valid values are `true` or `false`.
    Boolean incidents = true; // Boolean | Valid values for this property are `true` or `false`. If this property has been set to `true` the result will include the corresponding number of incidents for each occurred incident type. If it is set to `false`, the incidents will not be included in the result. Cannot be used in combination with `incidentsForType`.
    String incidentsForType = "incidentsForType_example"; // String | If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with `incidents`. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    try {
      List<ActivityStatisticsResultDto> result = apiInstance.getActivityStatistics(id, failedJobs, incidents, incidentsForType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getActivityStatistics");
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
 **failedJobs** | **Boolean**| Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. | [optional]
 **incidents** | **Boolean**| Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. | [optional]
 **incidentsForType** | **String**| If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]

### Return type

[**List&lt;ActivityStatisticsResultDto&gt;**](ActivityStatisticsResultDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getActivityStatisticsByProcessDefinitionKey"></a>
# **getActivityStatisticsByProcessDefinitionKey**
> List&lt;ActivityStatisticsResultDto&gt; getActivityStatisticsByProcessDefinitionKey(key, failedJobs, incidents, incidentsForType)

Get Activity Instance Statistics

Retrieves runtime statistics of the latest version of the given process definition which belongs to no tenant, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    Boolean failedJobs = true; // Boolean | Whether to include the number of failed jobs in the result or not. Valid values are `true` or `false`.
    Boolean incidents = true; // Boolean | Valid values for this property are `true` or `false`. If this property has been set to `true` the result will include the corresponding number of incidents for each occurred incident type. If it is set to `false`, the incidents will not be included in the result. Cannot be used in combination with `incidentsForType`.
    String incidentsForType = "incidentsForType_example"; // String | If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with `incidents`. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    try {
      List<ActivityStatisticsResultDto> result = apiInstance.getActivityStatisticsByProcessDefinitionKey(key, failedJobs, incidents, incidentsForType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getActivityStatisticsByProcessDefinitionKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **failedJobs** | **Boolean**| Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. | [optional]
 **incidents** | **Boolean**| Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. | [optional]
 **incidentsForType** | **String**| If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]

### Return type

[**List&lt;ActivityStatisticsResultDto&gt;**](ActivityStatisticsResultDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getActivityStatisticsByProcessDefinitionKeyAndTenantId"></a>
# **getActivityStatisticsByProcessDefinitionKeyAndTenantId**
> List&lt;ActivityStatisticsResultDto&gt; getActivityStatisticsByProcessDefinitionKeyAndTenantId(key, tenantId, failedJobs, incidents, incidentsForType)

Get Activity Instance Statistics

Retrieves runtime statistics of the latest version of the given process definition for a tenant, grouped by activities. These statistics include the number of running activity instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    Boolean failedJobs = true; // Boolean | Whether to include the number of failed jobs in the result or not. Valid values are `true` or `false`.
    Boolean incidents = true; // Boolean | Valid values for this property are `true` or `false`. If this property has been set to `true` the result will include the corresponding number of incidents for each occurred incident type. If it is set to `false`, the incidents will not be included in the result. Cannot be used in combination with `incidentsForType`.
    String incidentsForType = "incidentsForType_example"; // String | If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with `incidents`. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    try {
      List<ActivityStatisticsResultDto> result = apiInstance.getActivityStatisticsByProcessDefinitionKeyAndTenantId(key, tenantId, failedJobs, incidents, incidentsForType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getActivityStatisticsByProcessDefinitionKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |
 **failedJobs** | **Boolean**| Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. | [optional]
 **incidents** | **Boolean**| Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. | [optional]
 **incidentsForType** | **String**| If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]

### Return type

[**List&lt;ActivityStatisticsResultDto&gt;**](ActivityStatisticsResultDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeployedStartForm"></a>
# **getDeployedStartForm**
> File getDeployedStartForm(id)

Get Deployed Start Form

Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#embedded-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to get the deployed start form for.
    try {
      File result = apiInstance.getDeployedStartForm(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getDeployedStartForm");
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
 **id** | **String**| The id of the process definition to get the deployed start form for. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**403** | The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeployedStartFormByKey"></a>
# **getDeployedStartFormByKey**
> File getDeployedStartFormByKey(key)

Get Deployed Start Form

Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#embedded-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    try {
      File result = apiInstance.getDeployedStartFormByKey(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getDeployedStartFormByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**403** | The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getDeployedStartFormByKeyAndTenantId"></a>
# **getDeployedStartFormByKeyAndTenantId**
> File getDeployedStartFormByKeyAndTenantId(key, tenantId)

Get Deployed Start Form

Retrieves the deployed form that can be referenced from a start event. For further information please refer to [User Guide](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#embedded-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definitions belong to.
    try {
      File result = apiInstance.getDeployedStartFormByKeyAndTenantId(key, tenantId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getDeployedStartFormByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definitions belong to. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The form key has wrong format.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**403** | The deployed start form cannot be retrieved due to missing permissions on process definition resource. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | No deployed start form for a given process definition exists. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getLatestProcessDefinitionByTenantId"></a>
# **getLatestProcessDefinitionByTenantId**
> ProcessDefinitionDto getLatestProcessDefinitionByTenantId(key, tenantId)

Get

Retrieves the latest version of the process definition for tenant according to the &#x60;ProcessDefinition&#x60; interface in the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    try {
      ProcessDefinitionDto result = apiInstance.getLatestProcessDefinitionByTenantId(key, tenantId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getLatestProcessDefinitionByTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |

### Return type

[**ProcessDefinitionDto**](ProcessDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinition"></a>
# **getProcessDefinition**
> ProcessDefinitionDto getProcessDefinition(id)

Get

Retrieves a process definition according to the &#x60;ProcessDefinition&#x60; interface in the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to be retrieved.
    try {
      ProcessDefinitionDto result = apiInstance.getProcessDefinition(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinition");
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
 **id** | **String**| The id of the process definition to be retrieved. |

### Return type

[**ProcessDefinitionDto**](ProcessDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition with given &#x60;id&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionBpmn20Xml"></a>
# **getProcessDefinitionBpmn20Xml**
> ProcessDefinitionDiagramDto getProcessDefinitionBpmn20Xml(id)

Get XML

Retrieves the BPMN 2.0 XML of a process definition.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition.
    try {
      ProcessDefinitionDiagramDto result = apiInstance.getProcessDefinitionBpmn20Xml(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionBpmn20Xml");
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

[**ProcessDefinitionDiagramDto**](ProcessDefinitionDiagramDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**403** | The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionBpmn20XmlByKey"></a>
# **getProcessDefinitionBpmn20XmlByKey**
> ProcessDefinitionDiagramDto getProcessDefinitionBpmn20XmlByKey(key)

Get XML

Retrieves latest version the BPMN 2.0 XML of a process definition.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) whose XML should be retrieved.
    try {
      ProcessDefinitionDiagramDto result = apiInstance.getProcessDefinitionBpmn20XmlByKey(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionBpmn20XmlByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) whose XML should be retrieved. |

### Return type

[**ProcessDefinitionDiagramDto**](ProcessDefinitionDiagramDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**403** | The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionBpmn20XmlByKeyAndTenantId"></a>
# **getProcessDefinitionBpmn20XmlByKeyAndTenantId**
> ProcessDefinitionDiagramDto getProcessDefinitionBpmn20XmlByKeyAndTenantId(key, tenantId)

Get XML

Retrieves latest version the BPMN 2.0 XML of a process definition. Returns the XML for the latest version of the process definition for tenant.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) whose XML should be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    try {
      ProcessDefinitionDiagramDto result = apiInstance.getProcessDefinitionBpmn20XmlByKeyAndTenantId(key, tenantId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionBpmn20XmlByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) whose XML should be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |

### Return type

[**ProcessDefinitionDiagramDto**](ProcessDefinitionDiagramDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**403** | The Process Definition xml cannot be retrieved due to missing permissions on the Process Definition resource. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionByKey"></a>
# **getProcessDefinitionByKey**
> ProcessDefinitionDto getProcessDefinitionByKey(key)

Get

Retrieves the latest version of the process definition which belongs to no tenant according to the &#x60;ProcessDefinition&#x60; interface in the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    try {
      ProcessDefinitionDto result = apiInstance.getProcessDefinitionByKey(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |

### Return type

[**ProcessDefinitionDto**](ProcessDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition with given &#x60;key&#x60; does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionDiagram"></a>
# **getProcessDefinitionDiagram**
> File getProcessDefinitionDiagram(id)

Get Diagram

Retrieves the diagram of a process definition.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition.
    try {
      File result = apiInstance.getProcessDefinitionDiagram(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionDiagram");
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
**200** | Request successful. The image diagram of this process. |  -  |
**204** | The process definition doesn&#39;t have an associated diagram. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionDiagramByKey"></a>
# **getProcessDefinitionDiagramByKey**
> File getProcessDefinitionDiagramByKey(key)

Get Diagram

Retrieves the diagram for the latest version of the process definition which belongs to no tenant.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition.
    try {
      File result = apiInstance.getProcessDefinitionDiagramByKey(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionDiagramByKey");
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
 **key** | **String**| The key of the process definition. |

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
**200** | Request successful. The image diagram of this process. |  -  |
**204** | The process definition doesn&#39;t have an associated diagram. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionDiagramByKeyAndTenantId"></a>
# **getProcessDefinitionDiagramByKeyAndTenantId**
> File getProcessDefinitionDiagramByKeyAndTenantId(key, tenantId)

Get Diagram

Retrieves the diagram for the latest version of the process definition for tenant.  If the process definition&#39;s deployment contains an image resource with the same file name as the process definition, the deployed image will be returned by the Get Diagram endpoint. Example: &#x60;someProcess.bpmn&#x60; and &#x60;someProcess.png&#x60;. Supported file extentions for the image are: &#x60;svg&#x60;, &#x60;png&#x60;, &#x60;jpg&#x60;, and &#x60;gif&#x60;.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    try {
      File result = apiInstance.getProcessDefinitionDiagramByKeyAndTenantId(key, tenantId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionDiagramByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |

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
**200** | Request successful. The image diagram of this process. |  -  |
**204** | The process definition doesn&#39;t have an associated diagram. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionStatistics"></a>
# **getProcessDefinitionStatistics**
> List&lt;ProcessDefinitionStatisticsResultDto&gt; getProcessDefinitionStatistics(failedJobs, incidents, incidentsForType, rootIncidents)

Get Process Instance Statistics

Retrieves runtime statistics of the process engine, grouped by process definitions. These statistics include the number of running process instances, optionally the number of failed jobs and also optionally the number of incidents either grouped by incident types or for a specific incident type. **Note**: This does not include historic data.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    Boolean failedJobs = true; // Boolean | Whether to include the number of failed jobs in the result or not. Valid values are `true` or `false`.
    Boolean incidents = true; // Boolean | Valid values for this property are `true` or `false`. If this property has been set to `true` the result will include the corresponding number of incidents for each occurred incident type. If it is set to `false`, the incidents will not be included in the result. Cannot be used in combination with `incidentsForType`.
    String incidentsForType = "incidentsForType_example"; // String | If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with `incidents`. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    Boolean rootIncidents = true; // Boolean | Valid values for this property are `true` or `false`. If this property has been set to `true` the result will include the corresponding number of root incidents for each occurred incident type. If it is set to `false`, the incidents will not be included in the result. Cannot be used in combination with `incidentsForType` or `incidents`.
    try {
      List<ProcessDefinitionStatisticsResultDto> result = apiInstance.getProcessDefinitionStatistics(failedJobs, incidents, incidentsForType, rootIncidents);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionStatistics");
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
 **failedJobs** | **Boolean**| Whether to include the number of failed jobs in the result or not. Valid values are &#x60;true&#x60; or &#x60;false&#x60;. | [optional]
 **incidents** | **Boolean**| Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60;. | [optional]
 **incidentsForType** | **String**| If this property has been set with any incident type (i.e., a string value) the result will only include the number of incidents for the assigned incident type. Cannot be used in combination with &#x60;incidents&#x60;. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **rootIncidents** | **Boolean**| Valid values for this property are &#x60;true&#x60; or &#x60;false&#x60;. If this property has been set to &#x60;true&#x60; the result will include the corresponding number of root incidents for each occurred incident type. If it is set to &#x60;false&#x60;, the incidents will not be included in the result. Cannot be used in combination with &#x60;incidentsForType&#x60; or &#x60;incidents&#x60;. | [optional]

### Return type

[**List&lt;ProcessDefinitionStatisticsResultDto&gt;**](ProcessDefinitionStatisticsResultDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitions"></a>
# **getProcessDefinitions**
> List&lt;ProcessDefinitionDto&gt; getProcessDefinitions(processDefinitionId, processDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keysIn, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, startableBy, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, includeProcessDefinitionsWithoutTenantId, versionTag, versionTagLike, withoutVersionTag, startableInTasklist, notStartableInTasklist, startablePermissionCheck, sortBy, sortOrder, firstResult, maxResults)

Get List

Queries for process definitions that fulfill given parameters. Parameters may be the properties of  process definitions, such as the name, key or version. The size of the result set can be retrieved by using the [Get Definition Count](https://docs.camunda.org/manual/7.13/reference/rest/process-definition/get-query-count/) method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
    String processDefinitionIdIn = "processDefinitionIdIn_example"; // String | Filter by a comma-separated list of process definition ids.
    String name = "name_example"; // String | Filter by process definition name.
    String nameLike = "nameLike_example"; // String | Filter by process definition names that the parameter is a substring of.
    String deploymentId = "deploymentId_example"; // String | Filter by the deployment the id belongs to.
    OffsetDateTime deployedAfter = new OffsetDateTime(); // OffsetDateTime | Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.546+0200`.
    OffsetDateTime deployedAt = new OffsetDateTime(); // OffsetDateTime | Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.546+0200`.
    String key = "key_example"; // String | Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match.
    String keysIn = "keysIn_example"; // String | Filter by a comma-separated list of process definition keys.
    String keyLike = "keyLike_example"; // String | Filter by process definition keys that the parameter is a substring of.
    String category = "category_example"; // String | Filter by process definition category. Exact match.
    String categoryLike = "categoryLike_example"; // String | Filter by process definition categories that the parameter is a substring of.
    Integer version = 56; // Integer | Filter by process definition version.
    Boolean latestVersion = true; // Boolean | Only include those process definitions that are latest versions. Value may only be `true`, as `false` is the default behavior.
    String resourceName = "resourceName_example"; // String | Filter by the name of the process definition resource. Exact match.
    String resourceNameLike = "resourceNameLike_example"; // String | Filter by names of those process definition resources that the parameter is a substring of.
    String startableBy = "startableBy_example"; // String | Filter by a user name who is allowed to start the process.
    Boolean active = true; // Boolean | Only include active process definitions. Value may only be `true`, as `false` is the default behavior.
    Boolean suspended = true; // Boolean | Only include suspended process definitions. Value may only be `true`, as `false` is the default behavior.
    String incidentId = "incidentId_example"; // String | Filter by the incident id.
    String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
    String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids.
    Boolean withoutTenantId = true; // Boolean | Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior.
    Boolean includeProcessDefinitionsWithoutTenantId = true; // Boolean | Include process definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
    String versionTag = "versionTag_example"; // String | Filter by the version tag.
    String versionTagLike = "versionTagLike_example"; // String | Filter by the version tag that the parameter is a substring of.
    Boolean withoutVersionTag = true; // Boolean | Only include process definitions without a `versionTag`.
    Boolean startableInTasklist = true; // Boolean | Filter by process definitions which are startable in Tasklist..
    Boolean notStartableInTasklist = true; // Boolean | Filter by process definitions which are not startable in Tasklist.
    Boolean startablePermissionCheck = true; // Boolean | Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn't have these permissions the result will be empty list. The permissions are: * `CREATE` permission for all Process instances * `CREATE_INSTANCE` and `READ` permission on Process definition level
    String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
    String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
    Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
    Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
    try {
      List<ProcessDefinitionDto> result = apiInstance.getProcessDefinitions(processDefinitionId, processDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keysIn, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, startableBy, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, includeProcessDefinitionsWithoutTenantId, versionTag, versionTagLike, withoutVersionTag, startableInTasklist, notStartableInTasklist, startablePermissionCheck, sortBy, sortOrder, firstResult, maxResults);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitions");
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
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionIdIn** | **String**| Filter by a comma-separated list of process definition ids. | [optional]
 **name** | **String**| Filter by process definition name. | [optional]
 **nameLike** | **String**| Filter by process definition names that the parameter is a substring of. | [optional]
 **deploymentId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **deployedAfter** | **OffsetDateTime**| Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. | [optional]
 **deployedAt** | **OffsetDateTime**| Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. | [optional]
 **key** | **String**| Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match. | [optional]
 **keysIn** | **String**| Filter by a comma-separated list of process definition keys. | [optional]
 **keyLike** | **String**| Filter by process definition keys that the parameter is a substring of. | [optional]
 **category** | **String**| Filter by process definition category. Exact match. | [optional]
 **categoryLike** | **String**| Filter by process definition categories that the parameter is a substring of. | [optional]
 **version** | **Integer**| Filter by process definition version. | [optional]
 **latestVersion** | **Boolean**| Only include those process definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **resourceName** | **String**| Filter by the name of the process definition resource. Exact match. | [optional]
 **resourceNameLike** | **String**| Filter by names of those process definition resources that the parameter is a substring of. | [optional]
 **startableBy** | **String**| Filter by a user name who is allowed to start the process. | [optional]
 **active** | **Boolean**| Only include active process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **incidentId** | **String**| Filter by the incident id. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior. | [optional]
 **includeProcessDefinitionsWithoutTenantId** | **Boolean**| Include process definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **versionTag** | **String**| Filter by the version tag. | [optional]
 **versionTagLike** | **String**| Filter by the version tag that the parameter is a substring of. | [optional]
 **withoutVersionTag** | **Boolean**| Only include process definitions without a &#x60;versionTag&#x60;. | [optional]
 **startableInTasklist** | **Boolean**| Filter by process definitions which are startable in Tasklist.. | [optional]
 **notStartableInTasklist** | **Boolean**| Filter by process definitions which are not startable in Tasklist. | [optional]
 **startablePermissionCheck** | **Boolean**| Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn&#39;t have these permissions the result will be empty list. The permissions are: * &#x60;CREATE&#x60; permission for all Process instances * &#x60;CREATE_INSTANCE&#x60; and &#x60;READ&#x60; permission on Process definition level | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: category, key, id, name, version, deploymentId, deployTime, tenantId , versionTag]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;ProcessDefinitionDto&gt;**](ProcessDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getProcessDefinitionsCount"></a>
# **getProcessDefinitionsCount**
> CountResultDto getProcessDefinitionsCount(processDefinitionId, processDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keysIn, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, startableBy, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, includeProcessDefinitionsWithoutTenantId, versionTag, versionTagLike, withoutVersionTag, startableInTasklist, notStartableInTasklist, startablePermissionCheck)

Get List Count

Requests the number of process definitions that fulfill the query criteria. Takes the same filtering parameters as the [Get Definitions](https://docs.camunda.org/manual/7.13/reference/rest/process-definition/get-query/) method.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
    String processDefinitionIdIn = "processDefinitionIdIn_example"; // String | Filter by a comma-separated list of process definition ids.
    String name = "name_example"; // String | Filter by process definition name.
    String nameLike = "nameLike_example"; // String | Filter by process definition names that the parameter is a substring of.
    String deploymentId = "deploymentId_example"; // String | Filter by the deployment the id belongs to.
    OffsetDateTime deployedAfter = new OffsetDateTime(); // OffsetDateTime | Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.546+0200`.
    OffsetDateTime deployedAt = new OffsetDateTime(); // OffsetDateTime | Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., `2013-01-23T14:42:45.546+0200`.
    String key = "key_example"; // String | Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match.
    String keysIn = "keysIn_example"; // String | Filter by a comma-separated list of process definition keys.
    String keyLike = "keyLike_example"; // String | Filter by process definition keys that the parameter is a substring of.
    String category = "category_example"; // String | Filter by process definition category. Exact match.
    String categoryLike = "categoryLike_example"; // String | Filter by process definition categories that the parameter is a substring of.
    Integer version = 56; // Integer | Filter by process definition version.
    Boolean latestVersion = true; // Boolean | Only include those process definitions that are latest versions. Value may only be `true`, as `false` is the default behavior.
    String resourceName = "resourceName_example"; // String | Filter by the name of the process definition resource. Exact match.
    String resourceNameLike = "resourceNameLike_example"; // String | Filter by names of those process definition resources that the parameter is a substring of.
    String startableBy = "startableBy_example"; // String | Filter by a user name who is allowed to start the process.
    Boolean active = true; // Boolean | Only include active process definitions. Value may only be `true`, as `false` is the default behavior.
    Boolean suspended = true; // Boolean | Only include suspended process definitions. Value may only be `true`, as `false` is the default behavior.
    String incidentId = "incidentId_example"; // String | Filter by the incident id.
    String incidentType = "incidentType_example"; // String | Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.
    String incidentMessage = "incidentMessage_example"; // String | Filter by the incident message. Exact match.
    String incidentMessageLike = "incidentMessageLike_example"; // String | Filter by the incident message that the parameter is a substring of.
    String tenantIdIn = "tenantIdIn_example"; // String | Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids.
    Boolean withoutTenantId = true; // Boolean | Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior.
    Boolean includeProcessDefinitionsWithoutTenantId = true; // Boolean | Include process definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
    String versionTag = "versionTag_example"; // String | Filter by the version tag.
    String versionTagLike = "versionTagLike_example"; // String | Filter by the version tag that the parameter is a substring of.
    Boolean withoutVersionTag = true; // Boolean | Only include process definitions without a `versionTag`.
    Boolean startableInTasklist = true; // Boolean | Filter by process definitions which are startable in Tasklist..
    Boolean notStartableInTasklist = true; // Boolean | Filter by process definitions which are not startable in Tasklist.
    Boolean startablePermissionCheck = true; // Boolean | Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn't have these permissions the result will be empty list. The permissions are: * `CREATE` permission for all Process instances * `CREATE_INSTANCE` and `READ` permission on Process definition level
    try {
      CountResultDto result = apiInstance.getProcessDefinitionsCount(processDefinitionId, processDefinitionIdIn, name, nameLike, deploymentId, deployedAfter, deployedAt, key, keysIn, keyLike, category, categoryLike, version, latestVersion, resourceName, resourceNameLike, startableBy, active, suspended, incidentId, incidentType, incidentMessage, incidentMessageLike, tenantIdIn, withoutTenantId, includeProcessDefinitionsWithoutTenantId, versionTag, versionTagLike, withoutVersionTag, startableInTasklist, notStartableInTasklist, startablePermissionCheck);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getProcessDefinitionsCount");
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
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionIdIn** | **String**| Filter by a comma-separated list of process definition ids. | [optional]
 **name** | **String**| Filter by process definition name. | [optional]
 **nameLike** | **String**| Filter by process definition names that the parameter is a substring of. | [optional]
 **deploymentId** | **String**| Filter by the deployment the id belongs to. | [optional]
 **deployedAfter** | **OffsetDateTime**| Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed after (exclusive) a specific time. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. | [optional]
 **deployedAt** | **OffsetDateTime**| Filter by the deploy time of the deployment the process definition belongs to. Only selects process definitions that have been deployed at a specific time (exact match). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.546+0200&#x60;. | [optional]
 **key** | **String**| Filter by process definition key, i.e., the id in the BPMN 2.0 XML. Exact match. | [optional]
 **keysIn** | **String**| Filter by a comma-separated list of process definition keys. | [optional]
 **keyLike** | **String**| Filter by process definition keys that the parameter is a substring of. | [optional]
 **category** | **String**| Filter by process definition category. Exact match. | [optional]
 **categoryLike** | **String**| Filter by process definition categories that the parameter is a substring of. | [optional]
 **version** | **Integer**| Filter by process definition version. | [optional]
 **latestVersion** | **Boolean**| Only include those process definitions that are latest versions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **resourceName** | **String**| Filter by the name of the process definition resource. Exact match. | [optional]
 **resourceNameLike** | **String**| Filter by names of those process definition resources that the parameter is a substring of. | [optional]
 **startableBy** | **String**| Filter by a user name who is allowed to start the process. | [optional]
 **active** | **Boolean**| Only include active process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended process definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **incidentId** | **String**| Filter by the incident id. | [optional]
 **incidentType** | **String**| Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types. | [optional]
 **incidentMessage** | **String**| Filter by the incident message. Exact match. | [optional]
 **incidentMessageLike** | **String**| Filter by the incident message that the parameter is a substring of. | [optional]
 **tenantIdIn** | **String**| Filter by a comma-separated list of tenant ids. A process definition must have one of the given tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include process definitions which belong to no tenant. Value may only be true, as false is the default behavior. | [optional]
 **includeProcessDefinitionsWithoutTenantId** | **Boolean**| Include process definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **versionTag** | **String**| Filter by the version tag. | [optional]
 **versionTagLike** | **String**| Filter by the version tag that the parameter is a substring of. | [optional]
 **withoutVersionTag** | **Boolean**| Only include process definitions without a &#x60;versionTag&#x60;. | [optional]
 **startableInTasklist** | **Boolean**| Filter by process definitions which are startable in Tasklist.. | [optional]
 **notStartableInTasklist** | **Boolean**| Filter by process definitions which are not startable in Tasklist. | [optional]
 **startablePermissionCheck** | **Boolean**| Filter by process definitions which the user is allowed to start in Tasklist. If the user doesn&#39;t have these permissions the result will be empty list. The permissions are: * &#x60;CREATE&#x60; permission for all Process instances * &#x60;CREATE_INSTANCE&#x60; and &#x60;READ&#x60; permission on Process definition level | [optional]

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
**400** | Returned if some of the query parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getRenderedStartForm"></a>
# **getRenderedStartForm**
> File getRenderedStartForm(id)

Get Rendered Start Form

Retrieves the rendered form for a process definition. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to get the rendered start form for.
    try {
      File result = apiInstance.getRenderedStartForm(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getRenderedStartForm");
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
 **id** | **String**| The id of the process definition to get the rendered start form for. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getRenderedStartFormByKey"></a>
# **getRenderedStartFormByKey**
> File getRenderedStartFormByKey(key)

Get Rendered Start Form

Retrieves  the rendered form for the latest version of the process definition which belongs to no tenant. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    try {
      File result = apiInstance.getRenderedStartFormByKey(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getRenderedStartFormByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getRenderedStartFormByKeyAndTenantId"></a>
# **getRenderedStartFormByKeyAndTenantId**
> File getRenderedStartFormByKeyAndTenantId(key, tenantId)

Get Rendered Start Form

Retrieves  the rendered form for the latest version of the process definition for a tenant. This method can be used to get the HTML rendering of a [Generated Task Form](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    try {
      File result = apiInstance.getRenderedStartFormByKeyAndTenantId(key, tenantId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getRenderedStartFormByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/xhtml+xml, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition has no form field metadata defined. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getStartForm"></a>
# **getStartForm**
> FormDto getStartForm(id)

Get Start Form Key

Retrieves the key of the start form for a process definition. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to get the start form key for.
    try {
      FormDto result = apiInstance.getStartForm(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getStartForm");
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
 **id** | **String**| The id of the process definition to get the start form key for. |

### Return type

[**FormDto**](FormDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getStartFormByKey"></a>
# **getStartFormByKey**
> FormDto getStartFormByKey(key)

Get Start Form Key

Retrieves the key of the start form for the latest version of the process definition which belongs to no tenant. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) for which the form key is to be retrieved.
    try {
      FormDto result = apiInstance.getStartFormByKey(key);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getStartFormByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) for which the form key is to be retrieved. |

### Return type

[**FormDto**](FormDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getStartFormByKeyAndTenantId"></a>
# **getStartFormByKeyAndTenantId**
> FormDto getStartFormByKeyAndTenantId(key, tenantId)

Get Start Form Key

Retrieves the key of the start form for the latest version of the process definition for a tenant. The form key corresponds to the &#x60;FormData#formKey&#x60; property in the engine.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) for which the form key is to be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    try {
      FormDto result = apiInstance.getStartFormByKeyAndTenantId(key, tenantId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getStartFormByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) for which the form key is to be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |

### Return type

[**FormDto**](FormDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | Process definition has no start form defined. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getStartFormVariables"></a>
# **getStartFormVariables**
> Map&lt;String, VariableValueDto&gt; getStartFormVariables(id, variableNames, deserializeValues)

Get Start Form Variables

Retrieves the start form variables for a process definition (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to retrieve the variables for.
    String variableNames = "variableNames_example"; // String | A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored.
    Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      Map<String, VariableValueDto> result = apiInstance.getStartFormVariables(id, variableNames, deserializeValues);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getStartFormVariables");
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
 **id** | **String**| The id of the process definition to retrieve the variables for. |
 **variableNames** | **String**| A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. | [optional]
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

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
**200** | Request successful. A JSON object containing a property for each variable returned. |  -  |
**404** | The id is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getStartFormVariablesByKey"></a>
# **getStartFormVariablesByKey**
> Map&lt;String, VariableValueDto&gt; getStartFormVariablesByKey(key, variableNames, deserializeValues)

Get Start Form Variables

Retrieves the start form variables for the latest process definition which belongs to no tenant (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    String variableNames = "variableNames_example"; // String | A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored.
    Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      Map<String, VariableValueDto> result = apiInstance.getStartFormVariablesByKey(key, variableNames, deserializeValues);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getStartFormVariablesByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **variableNames** | **String**| A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. | [optional]
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

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
**200** | Request successful. A JSON object containing a property for each variable returned. |  -  |
**404** | The key is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="getStartFormVariablesByKeyAndTenantId"></a>
# **getStartFormVariablesByKeyAndTenantId**
> Map&lt;String, VariableValueDto&gt; getStartFormVariablesByKeyAndTenantId(key, tenantId, variableNames, deserializeValues)

Get Start Form Variables

Retrieves the start form variables for the latest process definition for a tenant (only if they are defined via the  [Generated Task Form](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms) approach). The start form variables take form data specified on the start event into account. If form fields are defined, the variable types and default values of the form fields are taken into account.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    String variableNames = "variableNames_example"; // String | A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored.
    Boolean deserializeValues = true; // Boolean | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson's](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API's classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine.
    try {
      Map<String, VariableValueDto> result = apiInstance.getStartFormVariablesByKeyAndTenantId(key, tenantId, variableNames, deserializeValues);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#getStartFormVariablesByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |
 **variableNames** | **String**| A comma-separated list of variable names. Allows restricting the list of requested variables to the variable names in the list. It is best practice to restrict the list of variables to the variables actually required by the form in order to minimize fetching of data. If the query parameter is ommitted all variables are fetched. If the query parameter contains non-existent variable names, the variable names are ignored. | [optional]
 **deserializeValues** | **Boolean**| Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default true).  If set to true, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](http://jackson.codehaus.org/) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to false, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML.  **Note**: While true is the default value for reasons of backward compatibility, we recommend setting this parameter to false when developing web applications that are independent of the Java process applications deployed to the engine. | [optional] [default to true]

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
**200** | Request successful. A JSON object containing a property for each variable returned. |  -  |
**404** | The key is null or does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="restartProcessInstance"></a>
# **restartProcessInstance**
> restartProcessInstance(id, restartProcessInstanceDto)

Restart Process Instance

Restarts process instances that were canceled or terminated synchronously. Can also restart completed process instances. It will create a new instance using the original instance information. To execute the restart asynchronously, use the [Restart Process Instance Async](https://docs.camunda.org/manual/7.13/reference/rest/process-definition/post-restart-process-instance-async/) method.  For more information about the difference between synchronous and asynchronous execution, please refer to the related section of the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/process-instance-restart/#execution).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition of the process instances to restart.
    RestartProcessInstanceDto restartProcessInstanceDto = {"instructions":[{"type":"startAfterActivity","activityId":"aUserTask"}],"processInstanceIds":["aProcessInstance","anotherProcessInstance"],"initialVariables":true,"skipCustomListeners":true,"withoutBusinessKey":true}; // RestartProcessInstanceDto | 
    try {
      apiInstance.restartProcessInstance(id, restartProcessInstanceDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#restartProcessInstance");
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
 **id** | **String**| The id of the process definition of the process instances to restart. |
 **restartProcessInstanceDto** | [**RestartProcessInstanceDto**](RestartProcessInstanceDto.md)|  | [optional]

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
**400** | In case following parameters are missing: &#x60;instructions&#x60;, &#x60;activityId&#x60; or &#x60;transitionId&#x60;, &#x60;processInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60;, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="restartProcessInstanceAsyncOperation"></a>
# **restartProcessInstanceAsyncOperation**
> BatchDto restartProcessInstanceAsyncOperation(id, restartProcessInstanceDto)

Restart Process Instance Async

Restarts process instances that were canceled or terminated asynchronously. Can also restart completed process instances. It will create a new instance using the original instance information. To execute the restart asynchronously, use the [Restart Process Instance](https://docs.camunda.org/manual/7.13/reference/rest/process-definition/post-restart-process-instance-sync/) method.  For more information about the difference between synchronous and asynchronous execution, please refer to the related section of the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/process-instance-restart/#execution).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition of the process instances to restart.
    RestartProcessInstanceDto restartProcessInstanceDto = {"instructions":[{"type":"startAfterActivity","activityId":"aUserTask"}],"processInstanceIds":["aProcessInstance","anotherProcessInstance"],"initialVariables":true,"skipCustomListeners":true,"withoutBusinessKey":true}; // RestartProcessInstanceDto | 
    try {
      BatchDto result = apiInstance.restartProcessInstanceAsyncOperation(id, restartProcessInstanceDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#restartProcessInstanceAsyncOperation");
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
 **id** | **String**| The id of the process definition of the process instances to restart. |
 **restartProcessInstanceDto** | [**RestartProcessInstanceDto**](RestartProcessInstanceDto.md)|  | [optional]

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
**400** | In case following parameters are missing: &#x60;instructions&#x60;, &#x60;activityId&#x60; or &#x60;transitionId&#x60;, &#x60;processInstanceIds&#x60; or &#x60;historicProcessInstanceQuery&#x60;, an exception of type &#x60;InvalidRequestException&#x60; is returned.  See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="startProcessInstance"></a>
# **startProcessInstance**
> ProcessInstanceWithVariablesDto startProcessInstance(id, startProcessInstanceDto)

Start Instance

Instantiates a given process definition. Process variables and business key may be supplied in the request body.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to be retrieved.
    StartProcessInstanceDto startProcessInstanceDto = {"variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}},"businessKey":"myBusinessKey"}; // StartProcessInstanceDto | 
    try {
      ProcessInstanceWithVariablesDto result = apiInstance.startProcessInstance(id, startProcessInstanceDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#startProcessInstance");
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
 **id** | **String**| The id of the process definition to be retrieved. |
 **startProcessInstanceDto** | [**StartProcessInstanceDto**](StartProcessInstanceDto.md)|  | [optional]

### Return type

[**ProcessInstanceWithVariablesDto**](ProcessInstanceWithVariablesDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="startProcessInstanceByKey"></a>
# **startProcessInstanceByKey**
> ProcessInstanceWithVariablesDto startProcessInstanceByKey(key, startProcessInstanceDto)

Start Instance

Instantiates a given process definition, starts the latest version of the process definition which belongs to no tenant. Process variables and business key may be supplied in the request body.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    StartProcessInstanceDto startProcessInstanceDto = {"variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}},"businessKey":"myBusinessKey"}; // StartProcessInstanceDto | 
    try {
      ProcessInstanceWithVariablesDto result = apiInstance.startProcessInstanceByKey(key, startProcessInstanceDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#startProcessInstanceByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **startProcessInstanceDto** | [**StartProcessInstanceDto**](StartProcessInstanceDto.md)|  | [optional]

### Return type

[**ProcessInstanceWithVariablesDto**](ProcessInstanceWithVariablesDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="startProcessInstanceByKeyAndTenantId"></a>
# **startProcessInstanceByKeyAndTenantId**
> ProcessInstanceWithVariablesDto startProcessInstanceByKeyAndTenantId(key, tenantId, startProcessInstanceDto)

Start Instance

Instantiates a given process definition, starts the latest version of the process definition for tenant. Process variables and business key may be supplied in the request body.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be retrieved.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    StartProcessInstanceDto startProcessInstanceDto = {"variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}},"businessKey":"myBusinessKey"}; // StartProcessInstanceDto | 
    try {
      ProcessInstanceWithVariablesDto result = apiInstance.startProcessInstanceByKeyAndTenantId(key, tenantId, startProcessInstanceDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#startProcessInstanceByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be retrieved. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |
 **startProcessInstanceDto** | [**StartProcessInstanceDto**](StartProcessInstanceDto.md)|  | [optional]

### Return type

[**ProcessInstanceWithVariablesDto**](ProcessInstanceWithVariablesDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="submitForm"></a>
# **submitForm**
> ProcessInstanceDto submitForm(id, startProcessInstanceFormDto)

Submit Start Form

Starts a process instance using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to submit the form for.
    StartProcessInstanceFormDto startProcessInstanceFormDto = {"variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}},"businessKey":"myBusinessKey"}; // StartProcessInstanceFormDto | 
    try {
      ProcessInstanceDto result = apiInstance.submitForm(id, startProcessInstanceFormDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#submitForm");
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
 **id** | **String**| The id of the process definition to submit the form for. |
 **startProcessInstanceFormDto** | [**StartProcessInstanceFormDto**](StartProcessInstanceFormDto.md)|  | [optional]

### Return type

[**ProcessInstanceDto**](ProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="submitFormByKey"></a>
# **submitFormByKey**
> ProcessInstanceDto submitFormByKey(key, startProcessInstanceFormDto)

Submit Start Form

Starts the latest version of the process definition which belongs to no tenant using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition to submit the form for.
    StartProcessInstanceFormDto startProcessInstanceFormDto = {"variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}},"businessKey":"myBusinessKey"}; // StartProcessInstanceFormDto | 
    try {
      ProcessInstanceDto result = apiInstance.submitFormByKey(key, startProcessInstanceFormDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#submitFormByKey");
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
 **key** | **String**| The key of the process definition to submit the form for. |
 **startProcessInstanceFormDto** | [**StartProcessInstanceFormDto**](StartProcessInstanceFormDto.md)|  | [optional]

### Return type

[**ProcessInstanceDto**](ProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="submitFormByKeyAndTenantId"></a>
# **submitFormByKeyAndTenantId**
> ProcessInstanceDto submitFormByKeyAndTenantId(key, tenantId, startProcessInstanceFormDto)

Submit Start Form

Starts the latest version of the process definition for a tenant using a set of process variables and the business key. If the start event has Form Field Metadata defined, the process engine will perform backend validation for any form fields which have validators defined. See [Documentation on Generated Task Forms](https://docs.camunda.org/manual/7.13/user-guide/task-forms/#generated-task-forms).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition to submit the form for.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    StartProcessInstanceFormDto startProcessInstanceFormDto = {"variables":{"aVariable":{"value":"aStringValue","type":"String"},"anotherVariable":{"value":true,"type":"Boolean"}},"businessKey":"myBusinessKey"}; // StartProcessInstanceFormDto | 
    try {
      ProcessInstanceDto result = apiInstance.submitFormByKeyAndTenantId(key, tenantId, startProcessInstanceFormDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#submitFormByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition to submit the form for. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |
 **startProcessInstanceFormDto** | [**StartProcessInstanceFormDto**](StartProcessInstanceFormDto.md)|  | [optional]

### Return type

[**ProcessInstanceDto**](ProcessInstanceDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Request successful. |  -  |
**400** | The instance could not be created due to an invalid variable value, for example if the value could not be parsed to an &#x60;Integer&#x60; value or the passed variable type is not supported. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**500** | The instance could not be created successfully. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateHistoryTimeToLiveByProcessDefinitionId"></a>
# **updateHistoryTimeToLiveByProcessDefinitionId**
> updateHistoryTimeToLiveByProcessDefinitionId(id, historyTimeToLiveDto)

Update History Time to Live

Updates history time to live for process definition. The field is used within [History cleanup](https://docs.camunda.org/manual/7.13/user-guide/process-engine/history/#history-cleanup).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to change history time to live.
    HistoryTimeToLiveDto historyTimeToLiveDto = {"historyTimeToLive":5}; // HistoryTimeToLiveDto | 
    try {
      apiInstance.updateHistoryTimeToLiveByProcessDefinitionId(id, historyTimeToLiveDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#updateHistoryTimeToLiveByProcessDefinitionId");
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
 **id** | **String**| The id of the process definition to change history time to live. |
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
**204** | Request successful. |  -  |
**400** | Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateHistoryTimeToLiveByProcessDefinitionKey"></a>
# **updateHistoryTimeToLiveByProcessDefinitionKey**
> updateHistoryTimeToLiveByProcessDefinitionKey(key, historyTimeToLiveDto)

Update History Time to Live

Updates history time to live for the latest version of the process definition which belongs to no tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.13/user-guide/process-engine/history/#history-cleanup).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition to change history time to live.
    HistoryTimeToLiveDto historyTimeToLiveDto = {"historyTimeToLive":5}; // HistoryTimeToLiveDto | 
    try {
      apiInstance.updateHistoryTimeToLiveByProcessDefinitionKey(key, historyTimeToLiveDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#updateHistoryTimeToLiveByProcessDefinitionKey");
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
 **key** | **String**| The key of the process definition to change history time to live. |
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
**204** | Request successful. |  -  |
**400** | Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId"></a>
# **updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId**
> updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId(key, tenantId, historyTimeToLiveDto)

Update History Time to Live

Updates history time to live for the latest version of the process definition for a tenant. The field is used within [History cleanup](https://docs.camunda.org/manual/7.13/user-guide/process-engine/history/#history-cleanup).

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition to change history time to live.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    HistoryTimeToLiveDto historyTimeToLiveDto = {"historyTimeToLive":5}; // HistoryTimeToLiveDto | 
    try {
      apiInstance.updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId(key, tenantId, historyTimeToLiveDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#updateHistoryTimeToLiveByProcessDefinitionKeyAndTenantId");
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
 **key** | **String**| The key of the process definition to change history time to live. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |
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
**204** | Request successful. |  -  |
**400** | Returned if some of the request parameters are invalid. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateProcessDefinitionSuspensionState"></a>
# **updateProcessDefinitionSuspensionState**
> updateProcessDefinitionSuspensionState(processDefinitionSuspensionStateDto)

Activate/Suspend By Key

Activates or suspends process definitions with the given process definition key.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto = {"processDefinitionKey":"aProcessDefinitionKey","suspended":true,"includeProcessInstances":true,"executionDate":"2013-11-21T10:49:45T14:42:45"}; // ProcessDefinitionSuspensionStateDto | **Note**: Unallowed property is `processDefinitionId`.
    try {
      apiInstance.updateProcessDefinitionSuspensionState(processDefinitionSuspensionStateDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#updateProcessDefinitionSuspensionState");
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
 **processDefinitionSuspensionStateDto** | [**ProcessDefinitionSuspensionStateDto**](ProcessDefinitionSuspensionStateDto.md)| **Note**: Unallowed property is &#x60;processDefinitionId&#x60;. | [optional]

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
**400** | Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateProcessDefinitionSuspensionStateById"></a>
# **updateProcessDefinitionSuspensionStateById**
> updateProcessDefinitionSuspensionStateById(id, processDefinitionSuspensionStateDto)

Activate/Suspend By Id

Activates or suspends a given process definition by id.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String id = "id_example"; // String | The id of the process definition to activate or suspend.
    ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto = {"suspended":true,"includeProcessInstances":true,"executionDate":"2013-11-21T10:49:45T14:42:45"}; // ProcessDefinitionSuspensionStateDto | **Note**: Unallowed properties are `processDefinitionId` and `processDefinitionKey`.
    try {
      apiInstance.updateProcessDefinitionSuspensionStateById(id, processDefinitionSuspensionStateDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#updateProcessDefinitionSuspensionStateById");
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
 **id** | **String**| The id of the process definition to activate or suspend. |
 **processDefinitionSuspensionStateDto** | [**ProcessDefinitionSuspensionStateDto**](ProcessDefinitionSuspensionStateDto.md)| **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. | [optional]

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
**400** | Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateProcessDefinitionSuspensionStateByKey"></a>
# **updateProcessDefinitionSuspensionStateByKey**
> updateProcessDefinitionSuspensionStateByKey(key, processDefinitionSuspensionStateDto)

Activate/Suspend by Id

Activates or suspends a given process definition by latest version of process definition key which belongs to no tenant.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be activated/suspended.
    ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto = {"suspended":true,"includeProcessInstances":true,"executionDate":"2013-11-21T10:49:45T14:42:45"}; // ProcessDefinitionSuspensionStateDto | **Note**: Unallowed properties are `processDefinitionId` and `processDefinitionKey`.
    try {
      apiInstance.updateProcessDefinitionSuspensionStateByKey(key, processDefinitionSuspensionStateDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#updateProcessDefinitionSuspensionStateByKey");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be activated/suspended. |
 **processDefinitionSuspensionStateDto** | [**ProcessDefinitionSuspensionStateDto**](ProcessDefinitionSuspensionStateDto.md)| **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. | [optional]

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
**400** | Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

<a name="updateProcessDefinitionSuspensionStateByKeyAndTenantId"></a>
# **updateProcessDefinitionSuspensionStateByKeyAndTenantId**
> updateProcessDefinitionSuspensionStateByKeyAndTenantId(key, tenantId, processDefinitionSuspensionStateDto)

Activate/Suspend by Id

Activates or suspends a given process definition by the latest version of the process definition for tenant.

### Example
```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.ProcessDefinitionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/engine-rest");

    ProcessDefinitionApi apiInstance = new ProcessDefinitionApi(defaultClient);
    String key = "key_example"; // String | The key of the process definition (the latest version thereof) to be activated/suspended.
    String tenantId = "tenantId_example"; // String | The id of the tenant the process definition belongs to.
    ProcessDefinitionSuspensionStateDto processDefinitionSuspensionStateDto = {"suspended":true,"includeProcessInstances":true,"executionDate":"2013-11-21T10:49:45T14:42:45"}; // ProcessDefinitionSuspensionStateDto | **Note**: Unallowed properties are `processDefinitionId` and `processDefinitionKey`.
    try {
      apiInstance.updateProcessDefinitionSuspensionStateByKeyAndTenantId(key, tenantId, processDefinitionSuspensionStateDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling ProcessDefinitionApi#updateProcessDefinitionSuspensionStateByKeyAndTenantId");
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
 **key** | **String**| The key of the process definition (the latest version thereof) to be activated/suspended. |
 **tenantId** | **String**| The id of the tenant the process definition belongs to. |
 **processDefinitionSuspensionStateDto** | [**ProcessDefinitionSuspensionStateDto**](ProcessDefinitionSuspensionStateDto.md)| **Note**: Unallowed properties are &#x60;processDefinitionId&#x60; and &#x60;processDefinitionKey&#x60;. | [optional]

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
**400** | Returned if some of the query parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format or if the &#x60;processDefinitionKey&#x60; parameter is &#x60;null&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |
**404** | Process definition with given key does not exist. See the [Introduction](https://docs.camunda.org/manual/7.13/reference/rest/overview/#error-handling) for the error response format. |  -  |

