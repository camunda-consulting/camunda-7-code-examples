# JobApi

All URIs are relative to *http://localhost:8080/engine-rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**executeJob**](JobApi.md#executeJob) | **POST** /job/{id}/execute | Execute Job
[**getJob**](JobApi.md#getJob) | **GET** /job/{id} | Get Job
[**getJobs**](JobApi.md#getJobs) | **GET** /job | Get Jobs
[**getJobsCount**](JobApi.md#getJobsCount) | **GET** /job/count | Get Job Count
[**getStacktrace**](JobApi.md#getStacktrace) | **GET** /job/{id}/stacktrace | Get Exception Stacktrace
[**queryJobs**](JobApi.md#queryJobs) | **POST** /job | Get Jobs (POST)
[**queryJobsCount**](JobApi.md#queryJobsCount) | **POST** /job/count | Get Job Count (POST)
[**recalculateDuedate**](JobApi.md#recalculateDuedate) | **POST** /job/{id}/duedate/recalculate | Recalculate Job Due Date
[**setJobDuedate**](JobApi.md#setJobDuedate) | **PUT** /job/{id}/duedate | Set Job Due Date
[**setJobPriority**](JobApi.md#setJobPriority) | **PUT** /job/{id}/priority | Set Job Priority
[**setJobRetries**](JobApi.md#setJobRetries) | **PUT** /job/{id}/retries | Set Job Retries
[**setJobRetriesAsyncOperation**](JobApi.md#setJobRetriesAsyncOperation) | **POST** /job/retries | Set Job Retries Async (POST)
[**updateJobSuspensionState**](JobApi.md#updateJobSuspensionState) | **PUT** /job/{id}/suspended | Activate/Suspend Job By Id
[**updateSuspensionStateBy**](JobApi.md#updateSuspensionStateBy) | **PUT** /job/suspended | Activate/Suspend Jobs



## executeJob

> executeJob(id)

Execute Job

Executes a job by id. **Note:** The execution of the job happens synchronously in the same thread.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to be executed.
        try {
            apiInstance.executeJob(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#executeJob");
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
 **id** | **String**| The id of the job to be executed. |

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
| **404** | Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The job could not be executed successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getJob

> JobDto getJob(id)

Get Job

Retrieves a job by id, according to the &#x60;Job&#x60; interface in the engine.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to be retrieved.
        try {
            JobDto result = apiInstance.getJob(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#getJob");
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
 **id** | **String**| The id of the job to be retrieved. |

### Return type

[**JobDto**](JobDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getJobs

> List&lt;JobDto&gt; getJobs(jobId, jobIds, jobDefinitionId, processInstanceId, processInstanceIds, executionId, processDefinitionId, processDefinitionKey, activityId, withRetriesLeft, executable, timers, messages, dueDates, createTimes, withException, exceptionMessage, failedActivityId, noRetriesLeft, active, suspended, priorityLowerThanOrEquals, priorityHigherThanOrEquals, tenantIdIn, withoutTenantId, includeJobsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults)

Get Jobs

Queries for jobs that fulfill given parameters. The size of the result set can be retrieved by using the [Get Job Count](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String jobId = "jobId_example"; // String | Filter by job id.
        String jobIds = "jobIds_example"; // String | Filter by a comma-separated list of job ids.
        String jobDefinitionId = "jobDefinitionId_example"; // String | Only select jobs which exist for the given job definition.
        String processInstanceId = "processInstanceId_example"; // String | Only select jobs which exist for the given process instance.
        String processInstanceIds = "processInstanceIds_example"; // String | Only select jobs which exist for the given comma-separated list of process instance ids.
        String executionId = "executionId_example"; // String | Only select jobs which exist for the given execution.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the id of the process definition the jobs run on.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the jobs run on.
        String activityId = "activityId_example"; // String | Only select jobs which exist for an activity with the given id.
        Boolean withRetriesLeft = true; // Boolean | Only select jobs which have retries left. Value may only be `true`, as `false` is the default behavior.
        Boolean executable = true; // Boolean | Only select jobs which are executable, i.e., retries > 0 and due date is `null` or due date is in the past. Value may only be `true`, as `false` is the default behavior.
        Boolean timers = true; // Boolean | Only select jobs that are timers. Cannot be used together with `messages`. Value may only be `true`, as `false` is the default behavior.
        Boolean messages = true; // Boolean | Only select jobs that are messages. Cannot be used together with `timers`. Value may only be `true`, as `false` is the default behavior.
        String dueDates = "dueDates_example"; // String | Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form `operator_value`. `operator` is the comparison operator to be used and `value` the date value as string.  Valid operator values are: `gt` - greater than; `lt` - lower than. `value` may not contain underscore or comma characters.
        String createTimes = "createTimes_example"; // String | Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form `operator_value`. `operator` is the comparison operator to be used and `value` the date value as string.  Valid operator values are: `gt` - greater than; `lt` - lower than. `value` may not contain underscore or comma characters.
        Boolean withException = true; // Boolean | Only select jobs that failed due to an exception. Value may only be `true`, as `false` is the default behavior.
        String exceptionMessage = "exceptionMessage_example"; // String | Only select jobs that failed due to an exception with the given message.
        String failedActivityId = "failedActivityId_example"; // String | Only select jobs that failed due to an exception at an activity with the given id.
        Boolean noRetriesLeft = true; // Boolean | Only select jobs which have no retries left. Value may only be `true`, as `false` is the default behavior.
        Boolean active = true; // Boolean | Only include active jobs. Value may only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | Only include suspended jobs. Value may only be `true`, as `false` is the default behavior.
        Long priorityLowerThanOrEquals = 56L; // Long | Only include jobs with a priority lower than or equal to the given value. Value must be a valid `long` value.
        Long priorityHigherThanOrEquals = 56L; // Long | Only include jobs with a priority higher than or equal to the given value. Value must be a valid `long` value.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include jobs which belong to one of the passed comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include jobs which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean includeJobsWithoutTenantId = true; // Boolean | Include jobs which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        String sortBy = "sortBy_example"; // String | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter.
        String sortOrder = "sortOrder_example"; // String | Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter.
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        try {
            List<JobDto> result = apiInstance.getJobs(jobId, jobIds, jobDefinitionId, processInstanceId, processInstanceIds, executionId, processDefinitionId, processDefinitionKey, activityId, withRetriesLeft, executable, timers, messages, dueDates, createTimes, withException, exceptionMessage, failedActivityId, noRetriesLeft, active, suspended, priorityLowerThanOrEquals, priorityHigherThanOrEquals, tenantIdIn, withoutTenantId, includeJobsWithoutTenantId, sortBy, sortOrder, firstResult, maxResults);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#getJobs");
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
 **jobId** | **String**| Filter by job id. | [optional]
 **jobIds** | **String**| Filter by a comma-separated list of job ids. | [optional]
 **jobDefinitionId** | **String**| Only select jobs which exist for the given job definition. | [optional]
 **processInstanceId** | **String**| Only select jobs which exist for the given process instance. | [optional]
 **processInstanceIds** | **String**| Only select jobs which exist for the given comma-separated list of process instance ids. | [optional]
 **executionId** | **String**| Only select jobs which exist for the given execution. | [optional]
 **processDefinitionId** | **String**| Filter by the id of the process definition the jobs run on. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the jobs run on. | [optional]
 **activityId** | **String**| Only select jobs which exist for an activity with the given id. | [optional]
 **withRetriesLeft** | **Boolean**| Only select jobs which have retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **executable** | **Boolean**| Only select jobs which are executable, i.e., retries &gt; 0 and due date is &#x60;null&#x60; or due date is in the past. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **timers** | **Boolean**| Only select jobs that are timers. Cannot be used together with &#x60;messages&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **messages** | **Boolean**| Only select jobs that are messages. Cannot be used together with &#x60;timers&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **dueDates** | **String**| Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **createTimes** | **String**| Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **withException** | **Boolean**| Only select jobs that failed due to an exception. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **exceptionMessage** | **String**| Only select jobs that failed due to an exception with the given message. | [optional]
 **failedActivityId** | **String**| Only select jobs that failed due to an exception at an activity with the given id. | [optional]
 **noRetriesLeft** | **Boolean**| Only select jobs which have no retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **active** | **Boolean**| Only include active jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **priorityLowerThanOrEquals** | **Long**| Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **priorityHigherThanOrEquals** | **Long**| Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **tenantIdIn** | **String**| Only include jobs which belong to one of the passed comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include jobs which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeJobsWithoutTenantId** | **Boolean**| Include jobs which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **sortBy** | **String**| Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. | [optional] [enum: jobId, executionId, processInstanceId, processDefinitionId, processDefinitionKey, jobPriority, jobRetries, jobDueDate, tenantId]
 **sortOrder** | **String**| Sort the results in a given order. Values may be asc for ascending order or desc for descending order. Must be used in conjunction with the sortBy parameter. | [optional] [enum: asc, desc]
 **firstResult** | **Integer**| Pagination of results. Specifies the index of the first result to return. | [optional]
 **maxResults** | **Integer**| Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left. | [optional]

### Return type

[**List&lt;JobDto&gt;**](JobDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getJobsCount

> CountResultDto getJobsCount(jobId, jobIds, jobDefinitionId, processInstanceId, processInstanceIds, executionId, processDefinitionId, processDefinitionKey, activityId, withRetriesLeft, executable, timers, messages, dueDates, createTimes, withException, exceptionMessage, failedActivityId, noRetriesLeft, active, suspended, priorityLowerThanOrEquals, priorityHigherThanOrEquals, tenantIdIn, withoutTenantId, includeJobsWithoutTenantId)

Get Job Count

Queries for the number of jobs that fulfill given parameters. Takes the same parameters as the [Get Jobs](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String jobId = "jobId_example"; // String | Filter by job id.
        String jobIds = "jobIds_example"; // String | Filter by a comma-separated list of job ids.
        String jobDefinitionId = "jobDefinitionId_example"; // String | Only select jobs which exist for the given job definition.
        String processInstanceId = "processInstanceId_example"; // String | Only select jobs which exist for the given process instance.
        String processInstanceIds = "processInstanceIds_example"; // String | Only select jobs which exist for the given comma-separated list of process instance ids.
        String executionId = "executionId_example"; // String | Only select jobs which exist for the given execution.
        String processDefinitionId = "processDefinitionId_example"; // String | Filter by the id of the process definition the jobs run on.
        String processDefinitionKey = "processDefinitionKey_example"; // String | Filter by the key of the process definition the jobs run on.
        String activityId = "activityId_example"; // String | Only select jobs which exist for an activity with the given id.
        Boolean withRetriesLeft = true; // Boolean | Only select jobs which have retries left. Value may only be `true`, as `false` is the default behavior.
        Boolean executable = true; // Boolean | Only select jobs which are executable, i.e., retries > 0 and due date is `null` or due date is in the past. Value may only be `true`, as `false` is the default behavior.
        Boolean timers = true; // Boolean | Only select jobs that are timers. Cannot be used together with `messages`. Value may only be `true`, as `false` is the default behavior.
        Boolean messages = true; // Boolean | Only select jobs that are messages. Cannot be used together with `timers`. Value may only be `true`, as `false` is the default behavior.
        String dueDates = "dueDates_example"; // String | Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form `operator_value`. `operator` is the comparison operator to be used and `value` the date value as string.  Valid operator values are: `gt` - greater than; `lt` - lower than. `value` may not contain underscore or comma characters.
        String createTimes = "createTimes_example"; // String | Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form `operator_value`. `operator` is the comparison operator to be used and `value` the date value as string.  Valid operator values are: `gt` - greater than; `lt` - lower than. `value` may not contain underscore or comma characters.
        Boolean withException = true; // Boolean | Only select jobs that failed due to an exception. Value may only be `true`, as `false` is the default behavior.
        String exceptionMessage = "exceptionMessage_example"; // String | Only select jobs that failed due to an exception with the given message.
        String failedActivityId = "failedActivityId_example"; // String | Only select jobs that failed due to an exception at an activity with the given id.
        Boolean noRetriesLeft = true; // Boolean | Only select jobs which have no retries left. Value may only be `true`, as `false` is the default behavior.
        Boolean active = true; // Boolean | Only include active jobs. Value may only be `true`, as `false` is the default behavior.
        Boolean suspended = true; // Boolean | Only include suspended jobs. Value may only be `true`, as `false` is the default behavior.
        Long priorityLowerThanOrEquals = 56L; // Long | Only include jobs with a priority lower than or equal to the given value. Value must be a valid `long` value.
        Long priorityHigherThanOrEquals = 56L; // Long | Only include jobs with a priority higher than or equal to the given value. Value must be a valid `long` value.
        String tenantIdIn = "tenantIdIn_example"; // String | Only include jobs which belong to one of the passed comma-separated tenant ids.
        Boolean withoutTenantId = true; // Boolean | Only include jobs which belong to no tenant. Value may only be `true`, as `false` is the default behavior.
        Boolean includeJobsWithoutTenantId = true; // Boolean | Include jobs which belong to no tenant. Can be used in combination with `tenantIdIn`. Value may only be `true`, as `false` is the default behavior.
        try {
            CountResultDto result = apiInstance.getJobsCount(jobId, jobIds, jobDefinitionId, processInstanceId, processInstanceIds, executionId, processDefinitionId, processDefinitionKey, activityId, withRetriesLeft, executable, timers, messages, dueDates, createTimes, withException, exceptionMessage, failedActivityId, noRetriesLeft, active, suspended, priorityLowerThanOrEquals, priorityHigherThanOrEquals, tenantIdIn, withoutTenantId, includeJobsWithoutTenantId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#getJobsCount");
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
 **jobId** | **String**| Filter by job id. | [optional]
 **jobIds** | **String**| Filter by a comma-separated list of job ids. | [optional]
 **jobDefinitionId** | **String**| Only select jobs which exist for the given job definition. | [optional]
 **processInstanceId** | **String**| Only select jobs which exist for the given process instance. | [optional]
 **processInstanceIds** | **String**| Only select jobs which exist for the given comma-separated list of process instance ids. | [optional]
 **executionId** | **String**| Only select jobs which exist for the given execution. | [optional]
 **processDefinitionId** | **String**| Filter by the id of the process definition the jobs run on. | [optional]
 **processDefinitionKey** | **String**| Filter by the key of the process definition the jobs run on. | [optional]
 **activityId** | **String**| Only select jobs which exist for an activity with the given id. | [optional]
 **withRetriesLeft** | **Boolean**| Only select jobs which have retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **executable** | **Boolean**| Only select jobs which are executable, i.e., retries &gt; 0 and due date is &#x60;null&#x60; or due date is in the past. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **timers** | **Boolean**| Only select jobs that are timers. Cannot be used together with &#x60;messages&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **messages** | **Boolean**| Only select jobs that are messages. Cannot be used together with &#x60;timers&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **dueDates** | **String**| Only select jobs where the due date is lower or higher than the given date. Due date expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **createTimes** | **String**| Only select jobs created before or after the given date.  Create time expressions are comma-separated and are structured as follows:  A valid condition value has the form &#x60;operator_value&#x60;. &#x60;operator&#x60; is the comparison operator to be used and &#x60;value&#x60; the date value as string.  Valid operator values are: &#x60;gt&#x60; - greater than; &#x60;lt&#x60; - lower than. &#x60;value&#x60; may not contain underscore or comma characters. | [optional]
 **withException** | **Boolean**| Only select jobs that failed due to an exception. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **exceptionMessage** | **String**| Only select jobs that failed due to an exception with the given message. | [optional]
 **failedActivityId** | **String**| Only select jobs that failed due to an exception at an activity with the given id. | [optional]
 **noRetriesLeft** | **Boolean**| Only select jobs which have no retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **active** | **Boolean**| Only include active jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **suspended** | **Boolean**| Only include suspended jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **priorityLowerThanOrEquals** | **Long**| Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **priorityHigherThanOrEquals** | **Long**| Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. | [optional]
 **tenantIdIn** | **String**| Only include jobs which belong to one of the passed comma-separated tenant ids. | [optional]
 **withoutTenantId** | **Boolean**| Only include jobs which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]
 **includeJobsWithoutTenantId** | **Boolean**| Include jobs which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example, if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## getStacktrace

> Object getStacktrace(id)

Get Exception Stacktrace

Retrieves the exception stacktrace corresponding to the passed job id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to get the exception stacktrace for.
        try {
            Object result = apiInstance.getStacktrace(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#getStacktrace");
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
 **id** | **String**| The id of the job to get the exception stacktrace for. |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **404** | Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryJobs

> List&lt;JobDto&gt; queryJobs(firstResult, maxResults, jobQueryDto)

Get Jobs (POST)

Queries for jobs that fulfill given parameters. This method is slightly more powerful than the [Get Jobs](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query/) method because it allows filtering by multiple jobs of types &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        Integer firstResult = 56; // Integer | Pagination of results. Specifies the index of the first result to return.
        Integer maxResults = 56; // Integer | Pagination of results. Specifies the maximum number of results to return. Will return less results if there are no more results left.
        JobQueryDto jobQueryDto = new JobQueryDto(); // JobQueryDto | 
        try {
            List<JobDto> result = apiInstance.queryJobs(firstResult, maxResults, jobQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#queryJobs");
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
 **jobQueryDto** | [**JobQueryDto**](JobQueryDto.md)|  | [optional]

### Return type

[**List&lt;JobDto&gt;**](JobDto.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if a &#x60;sortOrder&#x60; parameter is supplied, but no &#x60;sortBy&#x60;, or if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## queryJobsCount

> CountResultDto queryJobsCount(jobQueryDto)

Get Job Count (POST)

Queries for jobs that fulfill given parameters. This method takes the same message body as the [Get Jobs POST](https://docs.camunda.org/manual/7.16/reference/rest/job/post- query/) method and therefore it is slightly more powerful than the [Get Job Count](https://docs.camunda.org/manual/7.16/reference/rest/job/get-query-count/) method.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        JobQueryDto jobQueryDto = new JobQueryDto(); // JobQueryDto | 
        try {
            CountResultDto result = apiInstance.queryJobsCount(jobQueryDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#queryJobsCount");
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
 **jobQueryDto** | [**JobQueryDto**](JobQueryDto.md)|  | [optional]

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
| **400** | Returned if some of the query parameters are invalid, for example, if an invalid operator for due date comparison is used. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## recalculateDuedate

> recalculateDuedate(id, creationDateBased)

Recalculate Job Due Date

Recalculates the due date of a job by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to be updated.
        Boolean creationDateBased = true; // Boolean | Recalculate the due date based on the creation date of the job or the current date. Value may only be `false`, as `true` is the default behavior. 
        try {
            apiInstance.recalculateDuedate(id, creationDateBased);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#recalculateDuedate");
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
 **id** | **String**| The id of the job to be updated. |
 **creationDateBased** | **Boolean**| Recalculate the due date based on the creation date of the job or the current date. Value may only be &#x60;false&#x60;, as &#x60;true&#x60; is the default behavior.  | [optional]

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
| **404** | Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The due date could not be recalculated successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setJobDuedate

> setJobDuedate(id, jobDuedateDto)

Set Job Due Date

Updates the due date of a job by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to be updated.
        JobDuedateDto jobDuedateDto = new JobDuedateDto(); // JobDuedateDto | 
        try {
            apiInstance.setJobDuedate(id, jobDuedateDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#setJobDuedate");
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
 **id** | **String**| The id of the job to be updated. |
 **jobDuedateDto** | [**JobDuedateDto**](JobDuedateDto.md)|  | [optional]

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
| **404** | Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The due date could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setJobPriority

> setJobPriority(id, priorityDto)

Set Job Priority

Sets the execution priority of a job by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to be updated.
        PriorityDto priorityDto = new PriorityDto(); // PriorityDto | 
        try {
            apiInstance.setJobPriority(id, priorityDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#setJobPriority");
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
 **id** | **String**| The id of the job to be updated. |
 **priorityDto** | [**PriorityDto**](PriorityDto.md)|  | [optional]

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
| **404** | Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The priority could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setJobRetries

> setJobRetries(id, retriesDto)

Set Job Retries

Sets the retries of the job to the given number of retries by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to be updated.
        RetriesDto retriesDto = new RetriesDto(); // RetriesDto | 
        try {
            apiInstance.setJobRetries(id, retriesDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#setJobRetries");
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
 **id** | **String**| The id of the job to be updated. |
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
| **404** | Job with given id does not exist. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |
| **500** | The retries could not be set successfully. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## setJobRetriesAsyncOperation

> BatchDto setJobRetriesAsyncOperation(setJobRetriesDto)

Set Job Retries Async (POST)

Create a batch to set retries of jobs asynchronously.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        SetJobRetriesDto setJobRetriesDto = new SetJobRetriesDto(); // SetJobRetriesDto | 
        try {
            BatchDto result = apiInstance.setJobRetriesAsyncOperation(setJobRetriesDto);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#setJobRetriesAsyncOperation");
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
 **setJobRetriesDto** | [**SetJobRetriesDto**](SetJobRetriesDto.md)|  | [optional]

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
| **200** | Request successful. |  -  |
| **400** | Returned if some of the query parameters are invalid, for example if neither processInstanceIds nor processInstanceQuery is present. Or if the retry count is not specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |


## updateJobSuspensionState

> updateJobSuspensionState(id, suspensionStateDto)

Activate/Suspend Job By Id

Activates or suspends a given job by id.

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        String id = "id_example"; // String | The id of the job to activate or suspend.
        SuspensionStateDto suspensionStateDto = new SuspensionStateDto(); // SuspensionStateDto | 
        try {
            apiInstance.updateJobSuspensionState(id, suspensionStateDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#updateJobSuspensionState");
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
 **id** | **String**| The id of the job to activate or suspend. |
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
| **204** | Request successful. This method returns no content. |  -  |


## updateSuspensionStateBy

> updateSuspensionStateBy(jobSuspensionStateDto)

Activate/Suspend Jobs

Activates or suspends jobs matching the given criterion. This can only be on of: * &#x60;jobDefinitionId&#x60; * &#x60;processDefinitionId&#x60; * &#x60;processInstanceId&#x60; * &#x60;processDefinitionKey&#x60;

### Example

```java
// Import classes:
import com.camunda.consulting.openapi.client.handler.ApiClient;
import com.camunda.consulting.openapi.client.handler.ApiException;
import com.camunda.consulting.openapi.client.handler.Configuration;
import com.camunda.consulting.openapi.client.handler.models.*;
import com.camunda.consulting.openapi.client.handler.JobApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080/engine-rest");

        JobApi apiInstance = new JobApi(defaultClient);
        JobSuspensionStateDto jobSuspensionStateDto = new JobSuspensionStateDto(); // JobSuspensionStateDto | 
        try {
            apiInstance.updateSuspensionStateBy(jobSuspensionStateDto);
        } catch (ApiException e) {
            System.err.println("Exception when calling JobApi#updateSuspensionStateBy");
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
 **jobSuspensionStateDto** | [**JobSuspensionStateDto**](JobSuspensionStateDto.md)|  | [optional]

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
| **400** | Returned if the request parameters are invalid, for example, if &#x60;jobDefinitionId&#x60; and &#x60;processDefinitionId&#x60; are both specified. See the [Introduction](https://docs.camunda.org/manual/7.16/reference/rest/overview/#error-handling) for the error response format. |  -  |

