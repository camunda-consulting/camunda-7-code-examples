

# HistoricExternalTaskLogQueryDto

A Historic External Task Log instance query which defines a list of Historic External Task Log instances

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**logId** | **String** | Filter by historic external task log id. |  [optional]
**externalTaskId** | **String** | Filter by external task id. |  [optional]
**topicName** | **String** | Filter by an external task topic. |  [optional]
**workerId** | **String** | Filter by the id of the worker that the task was most recently locked by. |  [optional]
**errorMessage** | **String** | Filter by external task exception message. |  [optional]
**activityIdIn** | **List&lt;String&gt;** | Only include historic external task logs which belong to one of the passed activity ids. |  [optional]
**activityInstanceIdIn** | **List&lt;String&gt;** | Only include historic external task logs which belong to one of the passed activity instance ids. |  [optional]
**executionIdIn** | **List&lt;String&gt;** | Only include historic external task logs which belong to one of the passed execution ids. |  [optional]
**processInstanceId** | **String** | Filter by process instance id. |  [optional]
**processDefinitionId** | **String** | Filter by process definition id. |  [optional]
**processDefinitionKey** | **String** | Filter by process definition key. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Only include historic external task log entries which belong to one of the passed and comma-separated tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only include historic external task log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**priorityLowerThanOrEquals** | **Long** | Only include logs for which the associated external task had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**priorityHigherThanOrEquals** | **Long** | Only include logs for which the associated external task had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**creationLog** | **Boolean** | Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**failureLog** | **Boolean** | Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**successLog** | **Boolean** | Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**deletionLog** | **Boolean** | Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**sorting** | [**List&lt;HistoricExternalTaskLogQueryDtoSorting&gt;**](HistoricExternalTaskLogQueryDtoSorting.md) | An array of criteria to sort the result by. Each element of the array is                        an object that specifies one ordering. The position in the array                        identifies the rank of an ordering, i.e., whether it is primary, secondary,                        etc. Sorting has no effect for &#x60;count&#x60; endpoints. |  [optional]



