# JobDefinitionApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getJobDefinition**](JobDefinitionApi.md#getJobDefinition) | **GET** /job-definition/{id} | Get Job Definition
[**getJobDefinitions**](JobDefinitionApi.md#getJobDefinitions) | **GET** /job-definition | Get Job Definitions
[**getJobDefinitionsCount**](JobDefinitionApi.md#getJobDefinitionsCount) | **GET** /job-definition/count | Get Job Definition Count
[**queryJobDefinitions**](JobDefinitionApi.md#queryJobDefinitions) | **POST** /job-definition | Get Job Definitions (POST)
[**queryJobDefinitionsCount**](JobDefinitionApi.md#queryJobDefinitionsCount) | **POST** /job-definition/count | Get Job Definition Count (POST)
[**setJobPriorityJobDefinition**](JobDefinitionApi.md#setJobPriorityJobDefinition) | **PUT** /job-definition/{id}/jobPriority | Set Job Definition Priority by Id
[**setJobRetriesJobDefinition**](JobDefinitionApi.md#setJobRetriesJobDefinition) | **PUT** /job-definition/{id}/retries | Set Job Retries By Job Definition Id
[**updateSuspensionStateJobDefinition**](JobDefinitionApi.md#updateSuspensionStateJobDefinition) | **PUT** /job-definition/{id}/suspended | Activate/Suspend Job Definition By Id
[**updateSuspensionStateJobDefinitions**](JobDefinitionApi.md#updateSuspensionStateJobDefinitions) | **PUT** /job-definition/suspended | Activate/Suspend Job Definitions



## getJobDefinition

> JobDefinitionDto getJobDefinition(id)

Get Job Definition

Retrieves a job definition by id, according to the &#x60;JobDefinition&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the job definition to be retrieved.
        try {
            JobDefinitionDto result = apiInstance.getJobDefinition(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#getJobDefinition");
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
 **id** | **String**| The id of the job definition to be retrieved. |

### Return type

[**JobDefinitionDto**](JobDefinitionDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Job definition with given id does not exist.  See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getJobDefinitions

> List&lt;JobDefinitionDto&gt; getJobDefinitions(jobDefinitionId, activityIdIn, processDefinitionId, processDefinitionKey, jobType, jobConfiguration, active, suspended, withOverridingJobPriority, tenantIdIn, withoutTenantId, includeJobDefinitionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults)

Get Job Definitions

Queries for job definitions that fulfill given parameters. The size of the result set can be retrieved by using the [Get Job Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        String jobDefinitionId = "jobDefinitionId_example"; // String | Filter by job definition id.
        String activityIdIn = "activityIdIn_example"; // String | Only include job definitions which belong to one of the passed and comma-separated activity ids.
        String processDefinitionId = "processDefinitionId_example"; // String | Only include job definitions which exist for the given process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Only include job definitions which exist for the given process definition key.
        String jobType = "jobType_example"; // String | Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types.
        String jobConfiguration = "jobConfiguration_example"; // String | Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration.
        Boolean active = true; // Boolean | Only include active job definitions. Value may only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | Only include suspended job definitions. Value may only be `true`, as `false` is the default behavior.
        Boolean withOverridingJobPriority = true; // Boolean | Only include job definitions that have an overriding job priority defined. The only effective value is `true`. If set to `false`, this filter is not applied.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include job definitions which belong to one of the passed and comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include job definitions which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean includeJobDefinitionsWithoutTenantId = true; // Boolean | Include job definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<JobDefinitionDto> result = apiInstance.getJobDefinitions(jobDefinitionId, activityIdIn, processDefinitionId, processDefinitionKey, jobType, jobConfiguration, active, suspended, withOverridingJobPriority, tenantIdIn, withoutTenantId, includeJobDefinitionsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#getJobDefinitions");
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
 **jobDefinitionId** | **String**| Filter by job definition id. | [optional]
 **activityIdIn** | **String**| Only include job definitions which belong to one of the passed and comma-separated activity ids. | [optional]
 **processDefinitionId** | **String**| Only include job definitions which exist for the given process definition id. | [optional]
 **processDefinitionKey** | **String**| Only include job definitions which exist for the given process definition key. | [optional]
 **jobType** | **String**| Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. | [optional]
 **jobConfiguration** | **String**| Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration. | [optional]
 **active** | **Boolean**| Only include active job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **withOverridingJobPriority** | **Boolean**| Only include job definitions that have an overriding job priority defined. The only effective value is &#x60;true&#x60;. If set to &#x60;false&#x60;, this filter is not applied. | [optional]
 **tenantIdIn** | **String**| Only include job definitions which belong to one of the passed and comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include job definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeJobDefinitionsWithoutTenantId** | **Boolean**| Include job definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: jobDefinitionId, activityId, processDefinitionId, processDefinitionKey, jobType, jobConfiguration, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;JobDefinitionDto&gt;**](JobDefinitionDto.md)

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


## getJobDefinitionsCount

> CountResultDto getJobDefinitionsCount(jobDefinitionId, activityIdIn, processDefinitionId, processDefinitionKey, jobType, jobConfiguration, active, suspended, withOverridingJobPriority, tenantIdIn, withoutTenantId, includeJobDefinitionsWithoutTenantId)

Get Job Definition Count

Queries for the number of job definitions that fulfill given parameters. Takes the same parameters as the [Get Job Definitions](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        String jobDefinitionId = "jobDefinitionId_example"; // String | Filter by job definition id.
        String activityIdIn = "activityIdIn_example"; // String | Only include job definitions which belong to one of the passed and comma-separated activity ids.
        String processDefinitionId = "processDefinitionId_example"; // String | Only include job definitions which exist for the given process definition id.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Only include job definitions which exist for the given process definition key.
        String jobType = "jobType_example"; // String | Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types.
        String jobConfiguration = "jobConfiguration_example"; // String | Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration.
        Boolean active = true; // Boolean | Only include active job definitions. Value may only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | Only include suspended job definitions. Value may only be `true`, as `false` is the default behavior.
        Boolean withOverridingJobPriority = true; // Boolean | Only include job definitions that have an overriding job priority defined. The only effective value is `true`. If set to `false`, this filter is not applied.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include job definitions which belong to one of the passed and comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include job definitions which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean includeJobDefinitionsWithoutTenantId = true; // Boolean | Include job definitions which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getJobDefinitionsCount(jobDefinitionId, activityIdIn, processDefinitionId, processDefinitionKey, jobType, jobConfiguration, active, suspended, withOverridingJobPriority, tenantIdIn, withoutTenantId, includeJobDefinitionsWithoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#getJobDefinitionsCount");
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
 **jobDefinitionId** | **String**| Filter by job definition id. | [optional]
 **activityIdIn** | **String**| Only include job definitions which belong to one of the passed and comma-separated activity ids. | [optional]
 **processDefinitionId** | **String**| Only include job definitions which exist for the given process definition id. | [optional]
 **processDefinitionKey** | **String**| Only include job definitions which exist for the given process definition key. | [optional]
 **jobType** | **String**| Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. | [optional]
 **jobConfiguration** | **String**| Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration. | [optional]
 **active** | **Boolean**| Only include active job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **withOverridingJobPriority** | **Boolean**| Only include job definitions that have an overriding job priority defined. The only effective value is &#x60;true&#x60;. If set to &#x60;false&#x60;, this filter is not applied. | [optional]
 **tenantIdIn** | **String**| Only include job definitions which belong to one of the passed and comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include job definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeJobDefinitionsWithoutTenantId** | **Boolean**| Include job definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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


## queryJobDefinitions

> List&lt;JobDefinitionDto&gt; queryJobDefinitions(firstResult, maxResults, jobDefinitionQueryDto)

Get Job Definitions (POST)

Queries for job definitions that fulfill given parameters. This method is slightly more powerful than the [Get Job Definitions](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query/) method because it allows filtering by multiple job definitions of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        JobDefinitionQueryDto jobDefinitionQueryDto = new JobDefinitionQueryDto(); // JobDefinitionQueryDto | 
        try {
            List<JobDefinitionDto> result = apiInstance.queryJobDefinitions(firstResult, maxResults, jobDefinitionQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#queryJobDefinitions");
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
 **jobDefinitionQueryDto** | [**JobDefinitionQueryDto**](JobDefinitionQueryDto.md)|  | [optional]

### Return type

[**List&lt;JobDefinitionDto&gt;**](JobDefinitionDto.md)

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


## queryJobDefinitionsCount

> CountResultDto queryJobDefinitionsCount(jobDefinitionQueryDto)

Get Job Definition Count (POST)

Queries for the number of job definitions that fulfill given parameters. This method takes the same message body as the [Get Job Definitions (POST)](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/post-query/) method and therefore it is slightly more powerful than the [Get Job Definition Count](https://docs.camunda.org/manual/7.16/reference/rest/job-definition/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        JobDefinitionQueryDto jobDefinitionQueryDto = new JobDefinitionQueryDto(); // JobDefinitionQueryDto | 
        try {
            CountResultDto result = apiInstance.queryJobDefinitionsCount(jobDefinitionQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#queryJobDefinitionsCount");
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
 **jobDefinitionQueryDto** | [**JobDefinitionQueryDto**](JobDefinitionQueryDto.md)|  | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setJobPriorityJobDefinition

> setJobPriorityJobDefinition(id, jobDefinitionPriorityDto)

Set Job Definition Priority by Id

Sets an overriding execution priority for jobs with the given definition id. Optionally, the priorities of all the definitions&#39; existing jobs are updated accordingly. The priority can be reset by setting it to &#x60;null&#x60;, meaning that a new job&#39;s priority will not be determined based on its definition&#39;s priority any longer. See the [user guide on job prioritization](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#set-job-definition-priorities-via-managementservice-api) for details.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the job definition to be updated.
        JobDefinitionPriorityDto jobDefinitionPriorityDto = new JobDefinitionPriorityDto(); // JobDefinitionPriorityDto | 
        try {
            apiInstance.setJobPriorityJobDefinition(id, jobDefinitionPriorityDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#setJobPriorityJobDefinition");
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
 **id** | **String**| The id of the job definition to be updated. |
 **jobDefinitionPriorityDto** | [**JobDefinitionPriorityDto**](JobDefinitionPriorityDto.md)|  | [optional]

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
| **404** | Job definition with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setJobRetriesJobDefinition

> setJobRetriesJobDefinition(id, retriesDto)

Set Job Retries By Job Definition Id

Sets the number of retries of all **failed** jobs associated with the given job definition id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the job definition to be updated.
        RetriesDto retriesDto = new RetriesDto(); // RetriesDto | 
        try {
            apiInstance.setJobRetriesJobDefinition(id, retriesDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#setJobRetriesJobDefinition");
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
 **id** | **String**| The id of the job definition to be updated. |
 **retriesDto** | [**RetriesDto**](RetriesDto.md)|  | [optional]

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
| **500** | The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateSuspensionStateJobDefinition

> updateSuspensionStateJobDefinition(id, jobDefinitionSuspensionStateDto)

Activate/Suspend Job Definition By Id

Activates or suspends a given job definition by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        String id = "id_example"; // String | The id of the job definition to activate or suspend.
        JobDefinitionSuspensionStateDto jobDefinitionSuspensionStateDto = new JobDefinitionSuspensionStateDto(); // JobDefinitionSuspensionStateDto | 
        try {
            apiInstance.updateSuspensionStateJobDefinition(id, jobDefinitionSuspensionStateDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#updateSuspensionStateJobDefinition");
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
 **id** | **String**| The id of the job definition to activate or suspend. |
 **jobDefinitionSuspensionStateDto** | [**JobDefinitionSuspensionStateDto**](JobDefinitionSuspensionStateDto.md)|  | [optional]

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
| **400** | Returned if some of the request parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateSuspensionStateJobDefinitions

> updateSuspensionStateJobDefinitions(jobDefinitionsSuspensionStateDto)

Activate/Suspend Job Definitions

Activates or suspends job definitions with the given process definition id or process definition key.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobDefinitionApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobDefinitionApi apiInstance = new JobDefinitionApi(defaultClient);
        JobDefinitionsSuspensionStateDto jobDefinitionsSuspensionStateDto = new JobDefinitionsSuspensionStateDto(); // JobDefinitionsSuspensionStateDto | 
        try {
            apiInstance.updateSuspensionStateJobDefinitions(jobDefinitionsSuspensionStateDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobDefinitionApi#updateSuspensionStateJobDefinitions");
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
 **jobDefinitionsSuspensionStateDto** | [**JobDefinitionsSuspensionStateDto**](JobDefinitionsSuspensionStateDto.md)|  | [optional]

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
| **400** | Returned if some of the request parameters are invalid, for example if the provided &#x60;executionDate&#x60; parameter doesn&#39;t have the expected format. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

