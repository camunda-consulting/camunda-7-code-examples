

# HistoricProcessInstanceDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the process instance. |  [optional]
**rootProcessInstanceId** | **String** | The process instance id of the root process instance that initiated the process. |  [optional]
**superProcessInstanceId** | **String** | The id of the parent process instance, if it exists. |  [optional]
**superCaseInstanceId** | **String** | The id of the parent case instance, if it exists. |  [optional]
**caseInstanceId** | **String** | The id of the parent case instance, if it exists. |  [optional]
**processDefinitionName** | **String** | The name of the process definition that this process instance belongs to. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition that this process instance belongs to. |  [optional]
**processDefinitionVersion** | **Integer** | The version of the process definition that this process instance belongs to. |  [optional]
**processDefinitionId** | **String** | The id of the process definition that this process instance belongs to. |  [optional]
**businessKey** | **String** | The business key of the process instance. |  [optional]
**startTime** | **OffsetDateTime** | The time the instance was started. Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**endTime** | **OffsetDateTime** | The time the instance ended. Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**removalTime** | **OffsetDateTime** | The time after which the instance should be removed by the History Cleanup job. Default [format](https://docs.camunda.org/manual/7.16/reference/rest/overview/date-format/) &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;. |  [optional]
**durationInMillis** | **Long** | The time the instance took to finish (in milliseconds). |  [optional]
**startUserId** | **String** | The id of the user who started the process instance. |  [optional]
**startActivityId** | **String** | The id of the initial activity that was executed (e.g., a start event). |  [optional]
**deleteReason** | **String** | The provided delete reason in case the process instance was canceled during execution. |  [optional]
**tenantId** | **String** | The tenant id of the process instance. |  [optional]
**state** | [**StateEnum**](#StateEnum) | Last state of the process instance, possible values are:  &#x60;ACTIVE&#x60; - running process instance  &#x60;SUSPENDED&#x60; - suspended process instances  &#x60;COMPLETED&#x60; - completed through normal end event  &#x60;EXTERNALLY_TERMINATED&#x60; - terminated externally, for instance through REST API  &#x60;INTERNALLY_TERMINATED&#x60; - terminated internally, for instance by terminating boundary event |  [optional]



## Enum: StateEnum

Name | Value
---- | -----
ACTIVE | &quot;ACTIVE&quot;
SUSPENDED | &quot;SUSPENDED&quot;
COMPLETED | &quot;COMPLETED&quot;
EXTERNALLY_TERMINATED | &quot;EXTERNALLY_TERMINATED&quot;
INTERNALLY_TERMINATED | &quot;INTERNALLY_TERMINATED&quot;



