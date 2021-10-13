

# HistoricVariableInstanceDto


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
**value** | **Object** | Can be any value - string, number, boolean, array or object.  **Note**: Not every endpoint supports every type. |  [optional]
**type** | **String** | The value type of the variable. |  [optional]
**valueInfo** | **Map&lt;String, Object&gt;** | A JSON object containing additional, value-type-dependent properties. For serialized variables of type Object, the following properties can be provided:  * &#x60;objectTypeName&#x60;: A string representation of the object&#39;s type name. * &#x60;serializationDataFormat&#x60;: The serialization format used to store the variable.  For serialized variables of type File, the following properties can be provided:  * &#x60;filename&#x60;: The name of the file. This is not the variable name but the name that will be used when downloading the file again. * &#x60;mimetype&#x60;: The MIME type of the file that is being uploaded. * &#x60;encoding&#x60;: The encoding of the file that is being uploaded.  The following property can be provided for all value types:  * &#x60;transient&#x60;: Indicates whether the variable should be transient or not. See [documentation](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables#transient-variables) for more informations. (Not applicable for &#x60;decision-definition&#x60;, &#x60; /process-instance/variables-async&#x60;, and &#x60;/migration/executeAsync&#x60; endpoints) |  [optional]



