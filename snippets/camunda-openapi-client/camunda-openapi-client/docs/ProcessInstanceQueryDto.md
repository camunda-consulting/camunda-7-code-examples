

# ProcessInstanceQueryDto

A process instance query which defines a group of process instances
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**deploymentId** | **String** | Filter by the deployment the id belongs to. |  [optional]
**processDefinitionId** | **String** | Filter by the process definition the instances run on. |  [optional]
**processDefinitionKey** | **String** | Filter by the key of the process definition the instances run on. |  [optional]
**processDefinitionKeyIn** | **List&lt;String&gt;** | Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Must be a JSON array of Strings. |  [optional]
**processDefinitionKeyNotIn** | **List&lt;String&gt;** | Exclude instances by a list of process definition keys. A process instance must not have one of the given process definition keys. Must be a JSON array of Strings. |  [optional]
**businessKey** | **String** | Filter by process instance business key. |  [optional]
**businessKeyLike** | **String** | Filter by process instance business key that the parameter is a substring of. |  [optional]
**caseInstanceId** | **String** | Filter by case instance id. |  [optional]
**superProcessInstance** | **String** | Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. |  [optional]
**subProcessInstance** | **String** | Restrict query to all process instances that have the given process instance as a sub process instance. Takes a process instance id. |  [optional]
**superCaseInstance** | **String** | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. |  [optional]
**subCaseInstance** | **String** | Restrict query to all process instances that have the given case instance as a sub case instance. Takes a case instance id. |  [optional]
**active** | **Boolean** | Only include active process instances. Value may only be true, as false is the default behavior. |  [optional]
**suspended** | **Boolean** | Only include suspended process instances. Value may only be true, as false is the default behavior. |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | Filter by a list of process instance ids. Must be a JSON array of Strings. |  [optional]
**withIncident** | **Boolean** | Filter by presence of incidents. Selects only process instances that have an incident. |  [optional]
**incidentId** | **String** | Filter by the incident id. |  [optional]
**incidentType** | **String** | Filter by the incident type. See the User Guide for a list of incident types. |  [optional]
**incidentMessage** | **String** | Filter by the incident message. Exact match. |  [optional]
**incidentMessageLike** | **String** | Filter by the incident message that the parameter is a substring of. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Must be a JSON array of Strings. |  [optional]
**withoutTenantId** | **Boolean** | Only include process instances which belong to no tenant. Value may only be true, as false is the default behavior. |  [optional]
**processDefinitionWithoutTenantId** | **Boolean** | Only include process instances which process definition has no tenant id. |  [optional]
**activityIdIn** | **List&lt;String&gt;** | Filter by a list of activity ids. A process instance must currently wait in a leaf activity with one of the given activity ids. |  [optional]
**rootProcessInstances** | **Boolean** | Restrict the query to all process instances that are top level process instances. |  [optional]
**leafProcessInstances** | **Boolean** | Restrict the query to all process instances that are leaf instances. (i.e. don&#39;t have any sub instances) |  [optional]
**variables** | [**List&lt;VariableQueryParameterDto&gt;**](VariableQueryParameterDto.md) | A JSON array to only include process instances that have variables with certain values. The array consists of objects with the three properties &#x60;name&#x60;, &#x60;operator&#x60; and &#x60;value&#x60;. &#x60;name&#x60; (String) is the variable name, &#x60;operator&#x60; (String) is the comparison operator to be used and &#x60;value&#x60; the variable value. The &#x60;value&#x60; may be String, Number or Boolean.  Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. |  [optional]
**variableNamesIgnoreCase** | **Boolean** | Match all variable names in this query case-insensitively. If set to true variableName and variablename are treated as equal. |  [optional]
**variableValuesIgnoreCase** | **Boolean** | Match all variable values in this query case-insensitively. If set to true variableValue and variablevalue are treated as equal. |  [optional]
**orQueries** | [**List&lt;ProcessInstanceQueryDto&gt;**](ProcessInstanceQueryDto.md) | A JSON array of nested process instance queries with OR semantics. A process instance matches a nested query if it fulfills at least one of the query&#39;s predicates. With multiple nested queries, a process instance must fulfill at least one predicate of each query (Conjunctive Normal Form). All process instance query properties can be used except for: &#x60;sorting&#x60; See the [User guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/process-engine-api/#or-queries) for more information about OR queries. |  [optional]
**sorting** | [**List&lt;ProcessInstanceQueryDtoSorting&gt;**](ProcessInstanceQueryDtoSorting.md) | Apply sorting of the result |  [optional]



