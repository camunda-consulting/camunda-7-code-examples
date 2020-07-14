

# ExternalTaskQueryDto

A JSON object with the following properties:
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**externalTaskId** | **String** | Filter by an external task&#39;s id. |  [optional]
**externalTaskIdIn** | **List&lt;String&gt;** | Filter by the comma-separated list of external task ids. |  [optional]
**topicName** | **String** | Filter by an external task topic. |  [optional]
**workerId** | **String** | Filter by the id of the worker that the task was most recently locked by. |  [optional]
**locked** | **Boolean** | Only include external tasks that are currently locked (i.e., they have a lock time and it has not expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. |  [optional]
**notLocked** | **Boolean** | Only include external tasks that are currently not locked (i.e., they have no lock or it has expired). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. |  [optional]
**withRetriesLeft** | **Boolean** | Only include external tasks that have a positive (&amp;gt; 0) number of retries (or &#x60;null&#x60;). Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. |  [optional]
**noRetriesLeft** | **Boolean** | Only include external tasks that have 0 retries. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. |  [optional]
**lockExpirationAfter** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to external tasks that have a lock that expires after a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**lockExpirationBefore** | [**OffsetDateTime**](OffsetDateTime.md) | Restrict to external tasks that have a lock that expires before a given date. By [default](https://docs.camunda.org/manual/7.13/reference/rest/overview/date-format/), the date must have the format &#x60;yyyy-MM-dd&#39;T&#39;HH:mm:ss.SSSZ&#x60;, e.g., &#x60;2013-01-23T14:42:45.000+0200&#x60;. |  [optional]
**activityId** | **String** | Filter by the id of the activity that an external task is created for. |  [optional]
**activityIdIn** | **List&lt;String&gt;** | Filter by the comma-separated list of ids of the activities that an external task is created for. |  [optional]
**executionId** | **String** | Filter by the id of the execution that an external task belongs to. |  [optional]
**processInstanceId** | **String** | Filter by the id of the process instance that an external task belongs to. |  [optional]
**processInstanceIdIn** | **List&lt;String&gt;** | Filter by a comma-separated list of process instance ids that an external task may belong to. |  [optional]
**processDefinitionId** | **String** | Filter by the id of the process definition that an external task belongs to. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Filter by a comma-separated list of tenant ids. An external task must have one of the given tenant ids. |  [optional]
**active** | **Boolean** | Only include active tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. |  [optional]
**suspended** | **Boolean** | Only include suspended tasks. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; matches any external task. |  [optional]
**priorityHigherThanOrEquals** | **Long** | Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**priorityLowerThanOrEquals** | **Long** | Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**sorting** | [**List&lt;ExternalTaskQueryDtoSorting&gt;**](ExternalTaskQueryDtoSorting.md) | A JSON array of criteria to sort the result by. Each element of the array is a JSON object that                     specifies one ordering. The position in the array identifies the rank of an ordering, i.e., whether                     it is primary, secondary, etc. The ordering objects have the following properties:                      **Note:** The &#x60;sorting&#x60; properties will not be applied to the External Task count query. |  [optional]



