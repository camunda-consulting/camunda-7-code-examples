

# HistoricActivityInstanceQueryDto

A historic activity instance query which defines a group of historic activity instances

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**activityInstanceId** | **String** | Filter by activity instance id. |  [optional]
**processInstanceId** | **String** | Filter by process instance id. |  [optional]
**processDefinitionId** | **String** | Filter by process definition id. |  [optional]
**executionId** | **String** | Filter by the id of the execution that executed the activity instance. |  [optional]
**activityId** | **String** | Filter by the activity id (according to BPMN 2.0 XML). |  [optional]
**activityName** | **String** | Filter by the activity name (according to BPMN 2.0 XML). |  [optional]
**activityType** | **String** | Filter by activity type. |  [optional]
**taskAssignee** | **String** | Only include activity instances that are user tasks and assigned to a given user. |  [optional]
**finished** | **Boolean** | Only include finished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. |  [optional]
**unfinished** | **Boolean** | Only include unfinished activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. |  [optional]
**canceled** | **Boolean** | Only include canceled activity instances. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. |  [optional]
**completeScope** | **Boolean** | Only include activity instances which completed a scope. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; behaves the same as when the property is not set. |  [optional]
**startedBefore** | **OffsetDateTime** | Restrict to instances that were started before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**startedAfter** | **OffsetDateTime** | Restrict to instances that were started after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**finishedBefore** | **OffsetDateTime** | Restrict to instances that were finished before the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**finishedAfter** | **OffsetDateTime** | Restrict to instances that were finished after the given date. By [default](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Must be a JSON array of Strings. An activity instance must have one of the given tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only include historic activity instances that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**sorting** | [**List&lt;HistoricActivityInstanceQueryDtoSorting&gt;**](HistoricActivityInstanceQueryDtoSorting.md) | Apply sorting of the result |  [optional]



