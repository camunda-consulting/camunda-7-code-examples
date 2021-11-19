

# HistoricDecisionInstanceQueryDto

A historic decision instance query which defines a list of historic decision instances

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**decisionInstanceId** | **String** | Filter by decision instance id. |  [optional]
**decisionInstanceIdIn** | **List&lt;String&gt;** | Filter by decision instance ids. Must be a comma-separated list of decision instance ids. |  [optional]
**decisionDefinitionId** | **String** | Filter by the decision definition the instances belongs to. |  [optional]
**decisionDefinitionIdIn** | **List&lt;String&gt;** | Filter by the decision definitions the instances belongs to. Must be a comma-separated list of decision definition ids. |  [optional]
**decisionDefinitionKey** | **String** | Filter by the key of the decision definition the instances belongs to. |  [optional]
**decisionDefinitionKeyIn** | **List&lt;String&gt;** | Filter by the keys of the decision definition the instances belongs to. Must be a comma- separated list of decision definition keys. |  [optional]
**decisionDefinitionName** | **String** | Filter by the name of the decision definition the instances belongs to. |  [optional]
**decisionDefinitionNameLike** | **String** | Filter by the name of the decision definition the instances belongs to, that the parameter is a substring of. |  [optional]
**processDefinitionId** | **String** | Filter by the process definition the instances belongs to. |  [optional]
**processDefinitionKey** | **String** | Filter by the key of the process definition the instances belongs to. |  [optional]
**processInstanceId** | **String** | Filter by the process instance the instances belongs to. |  [optional]
**caseDefinitionId** | **String** | Filter by the case definition the instances belongs to. |  [optional]
**caseDefinitionKey** | **String** | Filter by the key of the case definition the instances belongs to. |  [optional]
**caseInstanceId** | **String** | Filter by the case instance the instances belongs to. |  [optional]
**activityIdIn** | **List&lt;String&gt;** | Filter by the activity ids the instances belongs to. Must be a comma-separated list of acitvity ids. |  [optional]
**activityInstanceIdIn** | **List&lt;String&gt;** | Filter by the activity instance ids the instances belongs to. Must be a comma-separated list of acitvity instance ids. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Filter by a comma-separated list of tenant ids. A historic decision instance must have one of the given tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only include historic decision instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**evaluatedBefore** | **OffsetDateTime** | Restrict to instances that were evaluated before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**evaluatedAfter** | **OffsetDateTime** | Restrict to instances that were evaluated after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM- dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**userId** | **String** | Restrict to instances that were evaluated by the given user. |  [optional]
**rootDecisionInstanceId** | **String** | Restrict to instances that have a given root decision instance id. This also includes the decision instance with the given id. |  [optional]
**rootDecisionInstancesOnly** | **Boolean** | Restrict to instances those are the root decision instance of an evaluation. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**decisionRequirementsDefinitionId** | **String** | Filter by the decision requirements definition the instances belongs to. |  [optional]
**decisionRequirementsDefinitionKey** | **String** | Filter by the key of the decision requirements definition the instances belongs to. |  [optional]
**includeInputs** | **Boolean** | Include input values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**includeOutputs** | **Boolean** | Include output values in the result. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**disableBinaryFetching** | **Boolean** | Disables fetching of byte array input and output values. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**disableCustomObjectDeserialization** | **Boolean** | Disables deserialization of input and output values that are custom objects. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]



