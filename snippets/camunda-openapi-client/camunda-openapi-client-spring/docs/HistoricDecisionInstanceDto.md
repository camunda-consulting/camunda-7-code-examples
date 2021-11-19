

# HistoricDecisionInstanceDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the decision instance. |  [optional]
**decisionDefinitionId** | **String** | The id of the decision definition that this decision instance belongs to. |  [optional]
**decisionDefinitionKey** | **String** | The key of the decision definition that this decision instance belongs to. |  [optional]
**decisionDefinitionName** | **String** | The name of the decision definition that this decision instance belongs to. |  [optional]
**evaluationTime** | **OffsetDateTime** | The time the instance was evaluated.  [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the instance should be removed by the History Cleanup job. [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**processDefinitionId** | **String** | The id of the process definition that this decision instance belongs to. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition that this decision instance belongs to. |  [optional]
**processInstanceId** | **String** | The id of the process instance that this decision instance belongs to. |  [optional]
**caseDefinitionId** | **String** | The id of the case definition that this decision instance belongs to. |  [optional]
**caseDefinitionKey** | **String** | The key of the case definition that this decision instance belongs to. |  [optional]
**caseInstanceId** | **String** | The id of the case instance that this decision instance belongs to. |  [optional]
**activityId** | **String** | The id of the activity that this decision instance belongs to. |  [optional]
**activityInstanceId** | **String** | The id of the activity instance that this decision instance belongs to. |  [optional]
**tenantId** | **String** | The tenant id of the historic decision instance. |  [optional]
**userId** | **String** | The id of the authenticated user that has evaluated this decision instance without a process or case instance. |  [optional]
**inputs** | [**List&lt;HistoricDecisionInputInstanceDto&gt;**](HistoricDecisionInputInstanceDto.md) | The list of decision input values. **Only exists** if &#x60;includeInputs&#x60; was set to &#x60;true&#x60; in the query. |  [optional]
**ouputs** | [**List&lt;HistoricDecisionOutputInstanceDto&gt;**](HistoricDecisionOutputInstanceDto.md) | The list of decision output values. **Only exists** if &#x60;includeOutputs&#x60; was set to &#x60;true&#x60; in the query. |  [optional]
**collectResultValue** | **Double** | The result of the collect aggregation of the decision result if used. &#x60;null&#x60; if no aggregation was used. |  [optional]
**rootDecisionInstanceId** | **String** | The decision instance id of the evaluated root decision. Can be &#x60;null&#x60; if this instance is the root decision instance of the evaluation. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the evaluation of this decision. Can be &#x60;null&#x60; if this decision instance is not evaluated as part of a BPMN process. |  [optional]
**decisionRequirementsDefinitionId** | **String** | The id of the decision requirements definition that this decision instance belongs to. |  [optional]
**decisionRequirementsDefinitionKey** | **String** | The key of the decision requirements definition that this decision instance belongs to. |  [optional]



