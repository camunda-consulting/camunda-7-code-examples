

# HistoricProcessInstanceQueryDto

A historic process instance query which defines a group of historic process instances
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**processInstanceId** | **String** | Filter by process instance id. |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | Filter by process instance ids. Must be a JSON array process instance ids. |  [optional]
**processDefinitionId** | **String** | Filter by the process definition the instances run on. |  [optional]
**processDefinitionKey** | **String** | Filter by the key of the process definition the instances run on. |  [optional]
**processDefinitionKeyIn** | **List&lt;String&gt;** | Filter by a list of process definition keys. A process instance must have one of the given process definition keys. Must be a JSON array of Strings. |  [optional]
**processDefinitionName** | **String** | Filter by the name of the process definition the instances run on. |  [optional]
**processDefinitionNameLike** | **String** | Filter by process definition names that the parameter is a substring of. |  [optional]
**processDefinitionKeyNotIn** | **List&lt;String&gt;** | Exclude instances that belong to a set of process definitions. Must be a JSON array of process definition keys. |  [optional]
**processInstanceBusinessKey** | **String** | Filter by process instance business key. |  [optional]
**processInstanceBusinessKeyLike** | **String** | Filter by process instance business key that the parameter is a substring of. |  [optional]
**rootProcessInstances** | **Boolean** | Restrict the query to all process instances that are top level process instances. |  [optional]
**finished** | **Boolean** | Only include finished process instances. Value may only be true, as false is the default behavior. |  [optional]
**unfinished** | **Boolean** | Only include unfinished process instances. Value may only be true, as false is the default behavior. |  [optional]
**withIncidents** | **Boolean** | Only include process instances which have an incident. Value may only be true, as false is the default behavior. |  [optional]
**withRootIncidents** | **Boolean** | Only include process instances which have a root incident. Value may only be true, as false is the default behavior. |  [optional]
**incidentType** | **String** | Filter by the incident type. See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/incidents/#incident-types) for a list of incident types.  |  [optional]
**incidentStatus** | [**IncidentStatusEnum**](#IncidentStatusEnum) | Only include process instances which have an incident in status either open or resolved. To get all process instances, use the query parameter withIncidents. |  [optional]
**incidentMessage** | **String** | Filter by the incident message. Exact match. |  [optional]
**incidentMessageLike** | **String** | Filter by the incident message that the parameter is a substring of. |  [optional]
**startedBefore** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**startedAfter** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**finishedBefore** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**finishedAfter** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**executedActivityAfter** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that executed an activity after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**executedActivityBefore** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that executed an activity before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**executedJobAfter** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that executed an job after the given date (inclusive). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**executedJobBefore** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to instances that executed an job before the given date (inclusive). By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ, e.g., 2013-01-23T14:42:45.000+0200. |  [optional]
**startedBy** | **String** | Only include process instances that were started by the given user. |  [optional]
**superProcessInstanceId** | **String** | Restrict query to all process instances that are sub process instances of the given process instance. Takes a process instance id. |  [optional]
**subProcessInstanceId** | **String** | Restrict query to one process instance that has a sub process instance with the given id. |  [optional]
**superCaseInstanceId** | **String** | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. |  [optional]
**subCaseInstanceId** | **String** | Restrict query to one process instance that has a sub case instance with the given id. |  [optional]
**caseInstanceId** | **String** | Restrict query to all process instances that are sub process instances of the given case instance. Takes a case instance id. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Filter by a list of tenant ids. A process instance must have one of the given tenant ids. Must be a JSON array of Strings. |  [optional]
**withoutTenantId** | **Boolean** | Only include historic process instances which belong to no tenant. Value may only be true, as false is the default behavior. |  [optional]
**executedActivityIdIn** | **List&lt;String&gt;** | Restrict to instances that executed an activity with one of given ids. |  [optional]
**activeActivityIdIn** | **List&lt;String&gt;** | Restrict to instances that have an active activity with one of given ids. |  [optional]
**active** | **Boolean** | Restrict to instances that are active. |  [optional]
**suspended** | **Boolean** | Restrict to instances that are suspended. |  [optional]
**completed** | **Boolean** | Restrict to instances that are completed. |  [optional]
**externallyTerminated** | **Boolean** | Restrict to instances that are externallyTerminated. |  [optional]
**internallyTerminated** | **Boolean** | Restrict to instances that are internallyTerminated. |  [optional]
**variables** | [**List&lt;VariableQueryParameterDto&gt;**](VariableQueryParameterDto.md) | A JSON array to only include process instances that have/had variables with certain values. The array consists of objects with the three properties &#x60;name&#x60;, &#x60;operator&#x60; and &#x60;value&#x60;. &#x60;name&#x60; (String) is the variable name, &#x60;operator&#x60; (String) is the comparison operator to be used and &#x60;value&#x60; the variable value. &#x60;value&#x60; may be String, Number or Boolean. Valid operator values are: &#x60;eq&#x60; - equal to; &#x60;neq&#x60; - not equal to; &#x60;gt&#x60; - greater than; &#x60;gteq&#x60; - greater than or equal to; &#x60;lt&#x60; - lower than; &#x60;lteq&#x60; - lower than or equal to; &#x60;like&#x60;. |  [optional]
**variableNamesIgnoreCase** | **Boolean** | Match all variable names provided in variables case-insensitively. If set to true variableName and variablename are treated as equal. |  [optional]
**variableValuesIgnoreCase** | **Boolean** | Match all variable values provided in variables case-insensitively. If set to true variableValue and variablevalue are treated as equal. |  [optional]
**orQueries** | [**List&lt;HistoricProcessInstanceQueryDto&gt;**](HistoricProcessInstanceQueryDto.md) | A JSON array of nested historic process instance queries with OR semantics. A process instance matches a nested query if it fulfills at least one of the query&#39;s predicates. With multiple nested queries, a process instance must fulfill at least one predicate of each query (Conjunctive Normal Form). All process instance query properties can be used except for: sorting See the [User Guide](https://docs.camunda.org/manual/7.13/user-guide/process-engine/process-engine-api/#or-queries) for more information about OR queries. |  [optional]
**sorting** | [**List&lt;HistoricProcessInstanceQueryDtoSorting&gt;**](HistoricProcessInstanceQueryDtoSorting.md) | Apply sorting of the result |  [optional]



## Enum: IncidentStatusEnum

Name | Value
---- | -----
OPEN | &quot;open&quot;
RESOLVED | &quot;resolved&quot;



