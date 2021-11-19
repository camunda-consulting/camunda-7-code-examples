

# HistoricIncidentDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the incident. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition this incident is associated with. |  [optional]
**processDefinitionId** | **String** | The id of the process definition this incident is associated with. |  [optional]
**processInstanceId** | **String** | The key of the process definition this incident is associated with. |  [optional]
**executionId** | **String** | The id of the execution this incident is associated with. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing this incident. |  [optional]
**createTime** | **OffsetDateTime** | The time this incident happened.  [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**endTime** | **OffsetDateTime** | The time this incident has been deleted or resolved.  [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the incident should be removed by the History Cleanup job. [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**incidentType** | **String** | The type of incident, for example: &#x60;failedJobs&#x60; will be returned in case of an incident which identified a failed job during the execution of a process instance. See the [User Guide](/manual/develop/user- guide/process-engine/incidents/#incident-types) for a list of incident types. |  [optional]
**activityId** | **String** | The id of the activity this incident is associated with. |  [optional]
**failedActivityId** | **String** | The id of the activity on which the last exception occurred. |  [optional]
**causeIncidentId** | **String** | The id of the associated cause incident which has been triggered. |  [optional]
**rootCauseIncidentId** | **String** | The id of the associated root cause incident which has been triggered. |  [optional]
**_configuration** | **String** | The payload of this incident. |  [optional]
**historyConfiguration** | **String** | The payload of this incident at the time when it occurred. |  [optional]
**incidentMessage** | **String** | The message of this incident. |  [optional]
**tenantId** | **String** | The id of the tenant this incident is associated with. |  [optional]
**jobDefinitionId** | **String** | The job definition id the incident is associated with. |  [optional]
**open** | **Boolean** | If true, this incident is open. |  [optional]
**deleted** | **Boolean** | If true, this incident has been deleted. |  [optional]
**resolved** | **Boolean** | If true, this incident has been resolved. |  [optional]
**annotation** | **String** | The annotation set to the incident. |  [optional]



