

# HistoricExternalTaskLogDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the log entry. |  [optional]
**externalTaskId** | **String** | The id of the external task. |  [optional]
**timestamp** | **OffsetDateTime** | The time when the log entry has been written. |  [optional]
**topicName** | **String** | The topic name of the associated external task. |  [optional]
**workerId** | **String** | The id of the worker that posessed the most recent lock. |  [optional]
**retries** | **Integer** | The number of retries the associated external task has left. |  [optional]
**priority** | **Long** | The execution priority the external task had when the log entry was created. |  [optional]
**errorMessage** | **String** | The message of the error that occurred by executing the associated external task. |  [optional]
**activityId** | **String** | The id of the activity on which the associated external task was created. |  [optional]
**activityInstanceId** | **String** | The id of the activity instance on which the associated external task was created. |  [optional]
**executionId** | **String** | The execution id on which the associated external task was created. |  [optional]
**processInstanceId** | **String** | The id of the process instance on which the associated external task was created. |  [optional]
**processDefinitionId** | **String** | The id of the process definition which the associated external task belongs to. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition which the associated external task belongs to. |  [optional]
**tenantId** | **String** | The id of the tenant that this historic external task log entry belongs to. |  [optional]
**creationLog** | **Boolean** | A flag indicating whether this log represents the creation of the associated external task. |  [optional]
**failureLog** | **Boolean** | A flag indicating whether this log represents the failed execution of the associated external task. |  [optional]
**successLog** | **Boolean** | A flag indicating whether this log represents the successful execution of the associated external task. |  [optional]
**deletionLog** | **Boolean** | A flag indicating whether this log represents the deletion of the associated external task. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which this log should be removed by the History Cleanup job. Default format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;.  For further information, please see the [documentation](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing this log. |  [optional]



