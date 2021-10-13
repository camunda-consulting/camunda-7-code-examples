

# UserOperationLogEntryDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The unique identifier of this log entry. |  [optional]
**userId** | **String** | The user who performed this operation. |  [optional]
**timestamp** | **OffsetDateTime** | Timestamp of this operation. |  [optional]
**operationId** | **String** | The unique identifier of this operation. A composite operation that changes multiple properties has a common &#x60;operationId&#x60;. |  [optional]
**operationType** | **String** | The type of this operation, e.g., &#x60;Assign&#x60;, &#x60;Claim&#x60; and so on. |  [optional]
**entityType** | **String** | The type of the entity on which this operation was executed, e.g., &#x60;Task&#x60; or &#x60;Attachment&#x60;. |  [optional]
**category** | **String** | The name of the category this operation was associated with, e.g., &#x60;TaskWorker&#x60; or &#x60;Admin&#x60;. |  [optional]
**annotation** | **String** | An arbitrary annotation set by a user for auditing reasons. |  [optional]
**property** | **String** | The property changed by this operation. |  [optional]
**orgValue** | **String** | The original value of the changed property. |  [optional]
**newValue** | **String** | The new value of the changed property. |  [optional]
**deploymentId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this deployment. |  [optional]
**processDefinitionId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this process definition. |  [optional]
**processDefinitionKey** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to process definitions with this key. |  [optional]
**processInstanceId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this process instance. |  [optional]
**executionId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this execution. |  [optional]
**caseDefinitionId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this case definition. |  [optional]
**caseInstanceId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this case instance. |  [optional]
**caseExecutionId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this case execution. |  [optional]
**taskId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this task. |  [optional]
**externalTaskId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this external task. |  [optional]
**batchId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this batch. |  [optional]
**jobId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this job. |  [optional]
**jobDefinitionId** | **String** | If not &#x60;null&#x60;, the operation is restricted to entities in relation to this job definition. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the entry should be removed by the History Cleanup job. [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing this entry. |  [optional]



