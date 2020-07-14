

# TransitionInstanceDto

A JSON object corresponding to the Activity Instance tree of the given process instance.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the transition instance. |  [optional]
**parentActivityInstanceId** | **String** | The id of the parent activity instance, for example a sub process instance. |  [optional]
**activityId** | **String** | The id of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job) |  [optional]
**activityName** | **String** | The name of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job) |  [optional]
**activityType** | **String** | The type of the activity that this instance enters (asyncBefore job) or leaves (asyncAfter job) |  [optional]
**processInstanceId** | **String** | The id of the process instance this instance is part of. |  [optional]
**processDefinitionId** | **String** | The id of the process definition. |  [optional]
**executionId** | **String** | The execution id. |  [optional]
**incidentIds** | **List&lt;String&gt;** | A list of incident ids. |  [optional]
**incidents** | [**List&lt;ActivityInstanceIncidentDto&gt;**](ActivityInstanceIncidentDto.md) | A list of JSON objects containing incident specific properties: * &#x60;id&#x60;: the id of the incident * &#x60;activityId&#x60;: the activity id in which the incident occurred |  [optional]



