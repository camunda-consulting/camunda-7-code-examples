

# HistoricJobLogQueryDto

A Historic Job Log instance query which defines a list of Historic Job Log instances

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**logId** | **String** | Filter by historic job log id. |  [optional]
**jobId** | **String** | Filter by job id. |  [optional]
**jobExceptionMessage** | **String** | Filter by job exception message. |  [optional]
**jobDefinitionId** | **String** | Filter by job definition id. |  [optional]
**jobDefinitionType** | **String** | Filter by job definition type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job definition types. |  [optional]
**jobDefinitionConfiguration** | **String** | Filter by job definition configuration. |  [optional]
**activityIdIn** | **List&lt;String&gt;** | Only include historic job logs which belong to one of the passed activity ids. |  [optional]
**failedActivityIdIn** | **List&lt;String&gt;** | Only include historic job logs which belong to failures of one of the passed activity ids. |  [optional]
**executionIdIn** | **List&lt;String&gt;** | Only include historic job logs which belong to one of the passed execution ids. |  [optional]
**processInstanceId** | **String** | Filter by process instance id. |  [optional]
**processDefinitionId** | **String** | Filter by process definition id. |  [optional]
**processDefinitionKey** | **String** | Filter by process definition key. |  [optional]
**deploymentId** | **String** | Filter by deployment id. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Only include historic job log entries which belong to one of the passed and comma- separated tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only include historic job log entries that belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**hostname** | **String** | Filter by hostname. |  [optional]
**jobPriorityLowerThanOrEquals** | **Long** | Only include logs for which the associated job had a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**jobPriorityHigherThanOrEquals** | **Long** | Only include logs for which the associated job had a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**creationLog** | **Boolean** | Only include creation logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**failureLog** | **Boolean** | Only include failure logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**successLog** | **Boolean** | Only include success logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**deletionLog** | **Boolean** | Only include deletion logs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**sorting** | [**List&lt;HistoricJobLogQueryDtoSorting&gt;**](HistoricJobLogQueryDtoSorting.md) | An array of criteria to sort the result by. Each element of the array is                        an object that specifies one ordering. The position in the array                        identifies the rank of an ordering, i.e., whether it is primary, secondary,                        etc. Sorting has no effect for &#x60;count&#x60; endpoints |  [optional]



