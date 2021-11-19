

# ExecutionQueryDto

A Execution instance query which defines a list of Execution instances

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**businessKey** | **String** | Filter by the business key of the process instances the executions belong to. |  [optional]
**processDefinitionId** | **String** | Filter by the process definition the executions run on. |  [optional]
**processDefinitionKey** | **String** | Filter by the key of the process definition the executions run on. |  [optional]
**processInstanceId** | **String** | Filter by the id of the process instance the execution belongs to. |  [optional]
**activityId** | **String** | Filter by the id of the activity the execution currently executes. |  [optional]
**signalEventSubscriptionName** | **String** | Select only those executions that expect a signal of the given name. |  [optional]
**messageEventSubscriptionName** | **String** | Select only those executions that expect a message of the given name. |  [optional]
**active** | **Boolean** | Only include active executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**suspended** | **Boolean** | Only include suspended executions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**incidentId** | **String** | Filter by the incident id. |  [optional]
**incidentType** | **String** | Filter by the incident type. See the [User Guide](/manual/develop/user-guide/process-engine/incidents/#incident-types) for a list of incident types. |  [optional]
**incidentMessage** | **String** | Filter by the incident message. Exact match. |  [optional]
**incidentMessageLike** | **String** | Filter by the incident message that the parameter is a substring of. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Filter by a  list of tenant ids. An execution must have one of the given tenant ids. |  [optional]
**variables** | [**List&lt;VariableQueryParameterDto&gt;**](VariableQueryParameterDto.md) | An array to only include executions that have variables with certain values.  The array consists of objects with the three properties &#x60;name&#x60;, &#x60;operator&#x60; and &#x60;value&#x60;. &#x60;name (String)&#x60; is the variable name, &#x60;operator (String)&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. &#x60;value&#x60; may be &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. |  [optional]
**processVariables** | [**List&lt;VariableQueryParameterDto&gt;**](VariableQueryParameterDto.md) | An array to only include executions that belong to a process instance with variables with certain values.  The array consists of objects with the three properties &#x60;name&#x60;, &#x60;operator&#x60; and &#x60;value&#x60;. &#x60;name (String)&#x60; is the variable name, &#x60;operator (String)&#x60; is the comparison operator to be used and &#x60;value&#x60; the variable value. &#x60;value&#x60; may be &#x60;String&#x60;, &#x60;Number&#x60; or &#x60;Boolean&#x60;.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to. |  [optional]
**variableNamesIgnoreCase** | **Boolean** | Match all variable names provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableName** and **variablename** are treated as equal. |  [optional]
**variableValuesIgnoreCase** | **Boolean** | Match all variable values provided in &#x60;variables&#x60; and &#x60;processVariables&#x60; case- insensitively. If set to &#x60;true&#x60; **variableValue** and **variablevalue** are treated as equal. |  [optional]
**sorting** | [**List&lt;ExecutionQueryDtoSorting&gt;**](ExecutionQueryDtoSorting.md) | An array of criteria to sort the result by. Each element of the array is                        an object that specifies one ordering. The position in the array                        identifies the rank of an ordering, i.e., whether it is primary, secondary,                        etc. Has no effect for the &#x60;/count&#x60; endpoint |  [optional]



