

# JobDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the job. |  [optional]
**jobDefinitionId** | **String** | The id of the associated job definition. |  [optional]
**dueDate** | **OffsetDateTime** | The date on which this job is supposed to be processed. |  [optional]
**processInstanceId** | **String** | The id of the process instance which execution created the job. |  [optional]
**executionId** | **String** | The specific execution id on which the job was created. |  [optional]
**processDefinitionId** | **String** | The id of the process definition which this job belongs to. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition which this job belongs to. |  [optional]
**retries** | **Integer** | The number of retries this job has left. |  [optional]
**exceptionMessage** | **String** | The message of the exception that occurred, the last time the job was executed. Is null when no exception occurred. |  [optional]
**failedActivityId** | **String** | The id of the activity on which the last exception occurred, the last time the job was executed. Is null when no exception occurred. |  [optional]
**suspended** | **Boolean** | A flag indicating whether the job is suspended or not. |  [optional]
**priority** | **Long** | The job&#39;s priority for execution. |  [optional]
**tenantId** | **String** | The id of the tenant which this job belongs to. |  [optional]
**createTime** | **OffsetDateTime** | The date on which this job has been created. |  [optional]



