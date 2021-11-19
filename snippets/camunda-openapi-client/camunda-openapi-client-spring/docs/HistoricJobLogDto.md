

# HistoricJobLogDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the log entry. |  [optional]
**timestamp** | **OffsetDateTime** | The time when the log entry has been written. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the log entry should be removed by the History Cleanup job. Default format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. For further info see the [docs](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) |  [optional]
**jobId** | **String** | The id of the associated job. |  [optional]
**jobDueDate** | **OffsetDateTime** | The date on which the associated job is supposed to be processed. |  [optional]
**jobRetries** | **Integer** | The number of retries the associated job has left. |  [optional]
**jobPriority** | **Long** | The execution priority the job had when the log entry was created. |  [optional]
**jobExceptionMessage** | **String** | The message of the exception that occurred by executing the associated job. |  [optional]
**failedActivityId** | **String** | The id of the activity on which the last exception occurred by executing the associated job. |  [optional]
**jobDefinitionId** | **String** | The id of the job definition on which the associated job was created. |  [optional]
**jobDefinitionType** | **String** | The job definition type of the associated job. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. |  [optional]
**jobDefinitionConfiguration** | **String** | The job definition configuration type of the associated job. |  [optional]
**activityId** | **String** | The id of the activity on which the associated job was created. |  [optional]
**executionId** | **String** | The execution id on which the associated job was created. |  [optional]
**processInstanceId** | **String** | The id of the process instance on which the associated job was created. |  [optional]
**processDefinitionId** | **String** | The id of the process definition which the associated job belongs to. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition which the associated job belongs to. |  [optional]
**deploymentId** | **String** | The id of the deployment which the associated job belongs to. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process which the associated job belongs to. |  [optional]
**tenantId** | **String** | The id of the tenant that this historic job log entry belongs to. |  [optional]
**hostname** | **String** |  The name of the host of the Process Engine where the job of this historic job log entry was executed. |  [optional]
**creationLog** | **Boolean** | A flag indicating whether this log represents the creation of the associated job. |  [optional]
**failureLog** | **Boolean** | A flag indicating whether this log represents the failed execution of the associated job. |  [optional]
**successLog** | **Boolean** | A flag indicating whether this log represents the successful execution of the associated job. |  [optional]
**deletionLog** | **Boolean** | A flag indicating whether this log represents the deletion of the associated job. |  [optional]



