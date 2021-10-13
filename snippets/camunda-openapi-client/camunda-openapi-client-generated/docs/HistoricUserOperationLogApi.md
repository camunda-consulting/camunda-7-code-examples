# HistoricUserOperationLogApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**clearAnnotationUserOperationLog**](HistoricUserOperationLogApi.md#clearAnnotationUserOperationLog) | **PUT** /history/user-operation/{operationId}/clear-annotation | Clear Annotation of an User Operation Log (Historic)
[**queryUserOperationCount**](HistoricUserOperationLogApi.md#queryUserOperationCount) | **GET** /history/user-operation/count | Get User Operation Log Count
[**queryUserOperationEntries**](HistoricUserOperationLogApi.md#queryUserOperationEntries) | **GET** /history/user-operation | Get User Operation Log (Historic)
[**setAnnotationUserOperationLog**](HistoricUserOperationLogApi.md#setAnnotationUserOperationLog) | **PUT** /history/user-operation/{operationId}/set-annotation | Set Annotation to an User Operation Log (Historic)



## clearAnnotationUserOperationLog

> clearAnnotationUserOperationLog(operationId)

Clear Annotation of an User Operation Log (Historic)

Clear the annotation which was previously set for auditing reasons.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricUserOperationLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricUserOperationLogApi apiInstance = new HistoricUserOperationLogApi(defaultClient);
        String operationId = "operationId_example"; // String | The operation id of the operation log to be updated.
        try {
            apiInstance.clearAnnotationUserOperationLog(operationId);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricUserOperationLogApi#clearAnnotationUserOperationLog");
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
 **operationId** | **String**| The operation id of the operation log to be updated. |

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
| **400** | Returned if some of the request parameters are invalid, for example if the &#x60;operationId&#x60; path parameter value does not exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryUserOperationCount

> CountResultDto queryUserOperationCount(deploymentId, processDefinitionId, processDefinitionKey, processInstanceId, executionId, caseDefinitionId, caseInstanceId, caseExecutionId, taskId, externalTaskId, batchId, jobId, jobDefinitionId, userId, operationId, operationType, entityType, entityTypeIn, category, categoryIn, property, afterTimestamp, beforeTimestamp)

Get User Operation Log Count

Queries for the number of user operation log entries that fulfill the given parameters. Takes the same parameters as the [Get User Operation Log (Historic)](https://docs.camunda.org/manual/7.16/reference/rest/history/user-operation-log/get-user-operation-log-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricUserOperationLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricUserOperationLogApi apiInstance = new HistoricUserOperationLogApi(defaultClient);
        String deploymentId = "deploymentId_example"; // String | Filter by deployment id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by process definition key.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String executionId = "executionId_example"; // String | Filter by execution id.
        String caseDefinitionId = "caseDefinitionId_example"; // String | Filter by case definition id.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by case instance id.
        String caseExecutionId = "caseExecutionId_example"; // String | Filter by case execution id.
        String taskId = "taskId_example"; // String | Only include operations on this task.
        String externalTaskId = "externalTaskId_example"; // String | Only include operations on this external task.
        String batchId = "batchId_example"; // String | Only include operations on this batch.
        String jobId = "jobId_example"; // String | Filter by job id.
        String jobDefinitionId = "jobDefinitionId_example"; // String | Filter by job definition id.
        String userId = "userId_example"; // String | Only include operations of this user.
        String operationId = "operationId_example"; // String | Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation.
        String operationType = "operationType_example"; // String | Filter by the type of the operation like `Claim` or `Delegate`. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types.
        String entityType = "entityType_example"; // String | Filter by the type of the entity that was affected by this operation, possible values are `Task`, `Attachment` or `IdentityLink`.
        String entityTypeIn = "entityTypeIn_example"; // String | Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are `Task`, `Attachment` or `IdentityLink`.
        String category = "category_example"; // String | Filter by the category that this operation is associated with, possible values are `TaskWorker`, `Admin` or `Operator`.
        String categoryIn = "categoryIn_example"; // String | Filter by a comma-separated list of categories that this operation is associated with, possible values are `TaskWorker`, `Admin` or `Operator`.
        String property = "property_example"; // String | Only include operations that changed this property, e.g., `owner` or `assignee`.
        OffsetDateTime afterTimestamp = OffsetDateTime.now(); // OffsetDateTime | Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        OffsetDateTime beforeTimestamp = OffsetDateTime.now(); // OffsetDateTime | Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        try {
            CountResultDto result = apiInstance.queryUserOperationCount(deploymentId, processDefinitionId, processDefinitionKey, processInstanceId, executionId, caseDefinitionId, caseInstanceId, caseExecutionId, taskId, externalTaskId, batchId, jobId, jobDefinitionId, userId, operationId, operationType, entityType, entityTypeIn, category, categoryIn, property, afterTimestamp, beforeTimestamp);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricUserOperationLogApi#queryUserOperationCount");
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
 **deploymentId** | **String**| Filter by deployment id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionKey** | **String**| Filter by process definition key. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **executionId** | **String**| Filter by execution id. | [optional]
 **caseDefinitionId** | **String**| Filter by case definition id. | [optional]
 **caseInstanceId** | **String**| Filter by case instance id. | [optional]
 **caseExecutionId** | **String**| Filter by case execution id. | [optional]
 **taskId** | **String**| Only include operations on this task. | [optional]
 **externalTaskId** | **String**| Only include operations on this external task. | [optional]
 **batchId** | **String**| Only include operations on this batch. | [optional]
 **jobId** | **String**| Filter by job id. | [optional]
 **jobDefinitionId** | **String**| Filter by job definition id. | [optional]
 **userId** | **String**| Only include operations of this user. | [optional]
 **operationId** | **String**| Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation. | [optional]
 **operationType** | **String**| Filter by the type of the operation like &#x60;Claim&#x60; or &#x60;Delegate&#x60;. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types. | [optional]
 **entityType** | **String**| Filter by the type of the entity that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. | [optional]
 **entityTypeIn** | **String**| Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. | [optional]
 **category** | **String**| Filter by the category that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. | [optional]
 **categoryIn** | **String**| Filter by a comma-separated list of categories that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. | [optional]
 **property** | **String**| Only include operations that changed this property, e.g., &#x60;owner&#x60; or &#x60;assignee&#x60;. | [optional]
 **afterTimestamp** | **OffsetDateTime**| Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]
 **beforeTimestamp** | **OffsetDateTime**| Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]

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


## queryUserOperationEntries

> List&lt;UserOperationLogEntryDto&gt; queryUserOperationEntries(deploymentId, processDefinitionId, processDefinitionKey, processInstanceId, executionId, caseDefinitionId, caseInstanceId, caseExecutionId, taskId, externalTaskId, batchId, jobId, jobDefinitionId, userId, operationId, operationType, entityType, entityTypeIn, category, categoryIn, property, afterTimestamp, beforeTimestamp, sortBy, sortOrder, firstResult, maxResults)

Get User Operation Log (Historic)

Queries for user operation log entries that fulfill the given parameters. The size of the result set can be retrieved by using the [Get User Operation Log Count](https://docs.camunda.org/manual/7.16/reference/rest/history/user-operation-log/get-user-operation-log-query-count/) method.  Note that the properties of operation log entries are interpreted as restrictions on the entities they apply to. That means, if a single process instance is updated, the field &#x60;processInstanceId&#x60; is populated. If a single operation updates all process instances of the same process definition, the field &#x60;processInstanceId&#x60; is &#x60;null&#x60; (a &#x60;null&#x60; restriction is viewed as a wildcard, i.e., matches a process instance with any id) and the field &#x60;processDefinitionId&#x60; is populated. This way, which entities were changed by a user operation can easily be reconstructed.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricUserOperationLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricUserOperationLogApi apiInstance = new HistoricUserOperationLogApi(defaultClient);
        String deploymentId = "deploymentId_example"; // String | Filter by deployment id.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by process definition key.
        String processInstanceId = "processInstanceId_example"; // String | Filter by process instance id.
        String executionId = "executionId_example"; // String | Filter by execution id.
        String caseDefinitionId = "caseDefinitionId_example"; // String | Filter by case definition id.
        String caseInstanceId = "caseInstanceId_example"; // String | Filter by case instance id.
        String caseExecutionId = "caseExecutionId_example"; // String | Filter by case execution id.
        String taskId = "taskId_example"; // String | Only include operations on this task.
        String externalTaskId = "externalTaskId_example"; // String | Only include operations on this external task.
        String batchId = "batchId_example"; // String | Only include operations on this batch.
        String jobId = "jobId_example"; // String | Filter by job id.
        String jobDefinitionId = "jobDefinitionId_example"; // String | Filter by job definition id.
        String userId = "userId_example"; // String | Only include operations of this user.
        String operationId = "operationId_example"; // String | Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation.
        String operationType = "operationType_example"; // String | Filter by the type of the operation like `Claim` or `Delegate`. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types.
        String entityType = "entityType_example"; // String | Filter by the type of the entity that was affected by this operation, possible values are `Task`, `Attachment` or `IdentityLink`.
        String entityTypeIn = "entityTypeIn_example"; // String | Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are `Task`, `Attachment` or `IdentityLink`.
        String category = "category_example"; // String | Filter by the category that this operation is associated with, possible values are `TaskWorker`, `Admin` or `Operator`.
        String categoryIn = "categoryIn_example"; // String | Filter by a comma-separated list of categories that this operation is associated with, possible values are `TaskWorker`, `Admin` or `Operator`.
        String property = "property_example"; // String | Only include operations that changed this property, e.g., `owner` or `assignee`.
        OffsetDateTime afterTimestamp = OffsetDateTime.now(); // OffsetDateTime | Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        OffsetDateTime beforeTimestamp = OffsetDateTime.now(); // OffsetDateTime | Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format `yyyy-MM-dd'T'HH:mm:ss.SSSZ`, e.g., 2013-01-23T14:42:45.000+0200.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<UserOperationLogEntryDto> result = apiInstance.queryUserOperationEntries(deploymentId, processDefinitionId, processDefinitionKey, processInstanceId, executionId, caseDefinitionId, caseInstanceId, caseExecutionId, taskId, externalTaskId, batchId, jobId, jobDefinitionId, userId, operationId, operationType, entityType, entityTypeIn, category, categoryIn, property, afterTimestamp, beforeTimestamp, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricUserOperationLogApi#queryUserOperationEntries");
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
 **deploymentId** | **String**| Filter by deployment id. | [optional]
 **processDefinitionId** | **String**| Filter by process definition id. | [optional]
 **processDefinitionKey** | **String**| Filter by process definition key. | [optional]
 **processInstanceId** | **String**| Filter by process instance id. | [optional]
 **executionId** | **String**| Filter by execution id. | [optional]
 **caseDefinitionId** | **String**| Filter by case definition id. | [optional]
 **caseInstanceId** | **String**| Filter by case instance id. | [optional]
 **caseExecutionId** | **String**| Filter by case execution id. | [optional]
 **taskId** | **String**| Only include operations on this task. | [optional]
 **externalTaskId** | **String**| Only include operations on this external task. | [optional]
 **batchId** | **String**| Only include operations on this batch. | [optional]
 **jobId** | **String**| Filter by job id. | [optional]
 **jobDefinitionId** | **String**| Filter by job definition id. | [optional]
 **userId** | **String**| Only include operations of this user. | [optional]
 **operationId** | **String**| Filter by the id of the operation. This allows fetching of multiple entries which are part of a composite operation. | [optional]
 **operationType** | **String**| Filter by the type of the operation like &#x60;Claim&#x60; or &#x60;Delegate&#x60;. See the [Javadoc](https://docs.camunda.org/manual/7.16/reference/javadoc/?org/camunda/bpm/engine/history/UserOperationLogEntry.html) for a list of available operation types. | [optional]
 **entityType** | **String**| Filter by the type of the entity that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. | [optional]
 **entityTypeIn** | **String**| Filter by a comma-separated list of types of the entities that was affected by this operation, possible values are &#x60;Task&#x60;, &#x60;Attachment&#x60; or &#x60;IdentityLink&#x60;. | [optional]
 **category** | **String**| Filter by the category that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. | [optional]
 **categoryIn** | **String**| Filter by a comma-separated list of categories that this operation is associated with, possible values are &#x60;TaskWorker&#x60;, &#x60;Admin&#x60; or &#x60;Operator&#x60;. | [optional]
 **property** | **String**| Only include operations that changed this property, e.g., &#x60;owner&#x60; or &#x60;assignee&#x60;. | [optional]
 **afterTimestamp** | **OffsetDateTime**| Restrict to entries that were created after the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]
 **beforeTimestamp** | **OffsetDateTime**| Restrict to entries that were created before the given timestamp. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the timestamp must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., 2013-01-23T14:42:45.000+0200. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: timestamp]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;UserOperationLogEntryDto&gt;**](UserOperationLogEntryDto.md)

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


## setAnnotationUserOperationLog

> setAnnotationUserOperationLog(operationId, annotationDto)

Set Annotation to an User Operation Log (Historic)

Set an annotation for auditing reasons.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.HistoricUserOperationLogApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        HistoricUserOperationLogApi apiInstance = new HistoricUserOperationLogApi(defaultClient);
        String operationId = "operationId_example"; // String | The operation id of the operation log to be updated.
        AnnotationDto annotationDto = new AnnotationDto(); // AnnotationDto | 
        try {
            apiInstance.setAnnotationUserOperationLog(operationId, annotationDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoricUserOperationLogApi#setAnnotationUserOperationLog");
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
 **operationId** | **String**| The operation id of the operation log to be updated. |
 **annotationDto** | [**AnnotationDto**](AnnotationDto.md)|  | [optional]

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
| **400** | Returned if some of the request parameters are invalid, for example if the &#x60;operationId&#x60; path parameter value does not exists. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

