

# HistoricVariableInstanceQueryDto

A Historic Variable Instance instance query which defines a list of Historic Variable Instance instances

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**variableName** | **String** | Filter by variable name. |  [optional]
**variableNameLike** | **String** | Restrict to variables with a name like the parameter. |  [optional]
**variableValue** | **Object** | Filter by variable value. May be &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;. |  [optional]
**variableNamesIgnoreCase** | **Boolean** | Match the variable name provided in &#x60;variableName&#x60; and &#x60;variableNameLike&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. |  [optional]
**variableValuesIgnoreCase** | **Boolean** | Match the variable value provided in &#x60;variableValue&#x60; case-insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. |  [optional]
**variableTypeIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed and comma- separated variable types. A list of all supported variable types can be found [here](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#supported-variable-values). **Note:** All non-primitive variables are associated with the type &#39;serializable&#39;. |  [optional]
**includeDeleted** | **Boolean** | Include variables that has already been deleted during the execution. |  [optional]
**processInstanceId** | **String** | Filter by the process instance the variable belongs to. |  [optional]
**processInstanceIdIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed  process instance ids. |  [optional]
**processDefinitionId** | **String** | Filter by the process definition the variable belongs to. |  [optional]
**processDefinitionKey** | **String** | Filter by a key of the process definition the variable belongs to. |  [optional]
**executionIdIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed and  execution ids. |  [optional]
**caseInstanceId** | **String** | Filter by the case instance the variable belongs to. |  [optional]
**caseExecutionIdIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed and  case execution ids. |  [optional]
**caseActivityIdIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed and  case activity ids. |  [optional]
**taskIdIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed and  task ids. |  [optional]
**activityInstanceIdIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed and  activity instance ids. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Only include historic variable instances which belong to one of the passed and comma- separated tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only include historic variable instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**sorting** | [**List&lt;HistoricVariableInstanceQueryDtoSorting&gt;**](HistoricVariableInstanceQueryDtoSorting.md) | An array of criteria to sort the result by. Each element of the array is                      an object that specifies one ordering. The position in the array                      identifies the rank of an ordering, i.e., whether it is primary, secondary,                      etc. Sorting has no effect for &#x60;count&#x60; endpoints |  [optional]



