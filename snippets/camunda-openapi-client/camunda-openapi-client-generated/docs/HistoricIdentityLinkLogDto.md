

# HistoricIdentityLinkLogDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | Id of the Historic identity link entry. |  [optional]
**time** | **OffsetDateTime** | The time when the identity link is logged.  [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**type** | **String** | The type of identity link (candidate/assignee/owner). |  [optional]
**userId** | **String** | The id of the user/assignee. |  [optional]
**groupId** | **String** | The id of the group. |  [optional]
**taskId** | **String** | The id of the task. |  [optional]
**processDefinitionId** | **String** | The id of the process definition. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition. |  [optional]
**operationType** | **String** | Type of operation (add/delete). |  [optional]
**assignerId** | **String** | The id of the assigner. |  [optional]
**tenantId** | **String** | The id of the tenant. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the identity link should be removed by the History Cleanup job.  [Default format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process containing this identity link. |  [optional]



