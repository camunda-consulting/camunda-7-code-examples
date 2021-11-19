

# HistoricVariableInstanceDtoAllOf


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the variable instance. |  [optional]
**name** | **String** | The name of the variable instance. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition the variable instance belongs to. |  [optional]
**processDefinitionId** | **String** | The id of the process definition the variable instance belongs to. |  [optional]
**processInstanceId** | **String** | The process instance id the variable instance belongs to. |  [optional]
**executionId** | **String** | The execution id the variable instance belongs to. |  [optional]
**activityInstanceId** | **String** | The id of the activity instance in which the variable is valid. |  [optional]
**caseDefinitionKey** | **String** | The key of the case definition the variable instance belongs to. |  [optional]
**caseDefinitionId** | **String** | The id of the case definition the variable instance belongs to. |  [optional]
**caseInstanceId** | **String** | The case instance id the variable instance belongs to. |  [optional]
**caseExecutionId** | **String** | The case execution id the variable instance belongs to. |  [optional]
**taskId** | **String** | The id of the task the variable instance belongs to. |  [optional]
**tenantId** | **String** | The id of the tenant that this variable instance belongs to. |  [optional]
**errorMessage** | **String** | An error message in case a Java Serialized Object could not be de-serialized. |  [optional]
**state** | **String** | The current state of the variable. Can be &#39;CREATED&#39; or &#39;DELETED&#39;. |  [optional]
**createTime** | **OffsetDateTime** | The time the variable was inserted. [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the variable should be removed by the History Cleanup job. [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing this variable. |  [optional]



