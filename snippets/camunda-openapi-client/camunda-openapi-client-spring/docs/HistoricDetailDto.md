

# HistoricDetailDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the historic detail. |  [optional]
**type** | **String** | The type of the historic detail. Either &#x60;formField&#x60; for a submitted form field value or &#x60;variableUpdate&#x60; for variable updates. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition that this historic detail belongs to. |  [optional]
**processDefinitionId** | **String** | The id of the process definition that this historic detail belongs to. |  [optional]
**processInstanceId** | **String** | The id of the process instance the historic detail belongs to. |  [optional]
**activityInstanceId** | **String** | The id of the activity instance the historic detail belongs to. |  [optional]
**executionId** | **String** | The id of the execution the historic detail belongs to. |  [optional]
**caseDefinitionKey** | **String** | The key of the case definition that this historic detail belongs to. |  [optional]
**caseDefinitionId** | **String** | The id of the case definition that this historic detail belongs to. |  [optional]
**caseInstanceId** | **String** | The id of the case instance the historic detail belongs to. |  [optional]
**caseExecutionId** | **String** | The id of the case execution the historic detail belongs to. |  [optional]
**taskId** | **String** | The id of the task the historic detail belongs to. |  [optional]
**tenantId** | **String** | The id of the tenant that this historic detail belongs to. |  [optional]
**userOperationId** | **String** | The id of user operation which links historic detail with [user operation log](https://docs.camunda.org/manual/7.16/reference/rest/history/user-operation-log/) entries. |  [optional]
**time** | **OffsetDateTime** | The time when this historic detail occurred. Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the historic detail should be removed by the History Cleanup job. Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing this historic detail. |  [optional]
**fieldId** | **String** | The id of the form field.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;formField&#x60;. |  [optional]
**fieldValue** | **Object** | The submitted form field value. The value differs depending on the form field&#39;s type and on the &#x60;deserializeValue&#x60; parameter.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;formField&#x60;. |  [optional]
**variableName** | **String** | The name of the variable which has been updated.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]
**variableInstanceId** | **String** | The id of the associated variable instance.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]
**variableType** | **String** | The value type of the variable.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]
**value** | **Object** | The variable&#39;s value. Value differs depending on the variable&#39;s type and on the deserializeValues parameter.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]
**valueInfo** | **Map&lt;String, Object&gt;** | A JSON object containing additional, value-type-dependent properties. For variables of type &#x60;Object&#x60;, the following properties are returned:  * &#x60;objectTypeName&#x60;: A string representation of the object&#39;s type name. * &#x60;serializationDataFormat&#x60;: The serialization format used to store the variable.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]
**initial** | **Boolean** | Returns &#x60;true&#x60; for variable updates that contains the initial values of the variables.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]
**revision** | **Integer** | The revision of the historic variable update.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]
**errorMessage** | **String** | An error message in case a Java Serialized Object could not be de-serialized.  **Note:** This property is only set for a &#x60;HistoricVariableUpdate&#x60; historic details. In these cases, the value of the &#x60;type&#x60; property is &#x60;variableUpdate&#x60;. |  [optional]



