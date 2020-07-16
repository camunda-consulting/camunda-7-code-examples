

# LockedExternalTaskDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**activityId** | **String** | The id of the activity that this external task belongs to. |  [optional]
**activityInstanceId** | **String** | The id of the activity instance that the external task belongs to. |  [optional]
**errorMessage** | **String** | The full error message submitted with the latest reported failure executing this task;&#x60;null&#x60; if no failure was reported previously or if no error message was submitted |  [optional]
**errorDetails** | **String** | The error details submitted with the latest reported failure executing this task.&#x60;null&#x60; if no failure was reported previously or if no error details was submitted |  [optional]
**executionId** | **String** | The id of the execution that the external task belongs to. |  [optional]
**id** | **String** | The id of the external task. |  [optional]
**lockExpirationTime** | [**OffsetDateTime**](OffsetDateTime.md) | The date that the task&#39;s most recent lock expires or has expired. |  [optional]
**processDefinitionId** | **String** | The id of the process definition the external task is defined in. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition the external task is defined in. |  [optional]
**processDefinitionVersionTag** | **String** | The version tag of the process definition the external task is defined in. |  [optional]
**processInstanceId** | **String** | The id of the process instance the external task belongs to. |  [optional]
**tenantId** | **String** | The id of the tenant the external task belongs to. |  [optional]
**retries** | **Integer** | The number of retries the task currently has left. |  [optional]
**suspended** | **Boolean** | Whether the process instance the external task belongs to is suspended. |  [optional]
**workerId** | **String** | The id of the worker that posesses or posessed the most recent lock. |  [optional]
**priority** | **Long** | The priority of the external task. |  [optional]
**topicName** | **String** | The topic name of the external task. |  [optional]
**businessKey** | **String** | The business key of the process instance the external task belongs to. |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing a property for each of the requested variables. The key is the variable name, the value is a JSON object of serialized variable values with the following properties: |  [optional]



