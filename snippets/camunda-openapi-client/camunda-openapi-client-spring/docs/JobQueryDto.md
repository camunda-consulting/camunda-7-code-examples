

# JobQueryDto

A Job instance query which defines a list of Job instances

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**jobId** | **String** | Filter by job id. |  [optional]
**jobIds** | **List&lt;String&gt;** | Filter by a  list of job ids. |  [optional]
**jobDefinitionId** | **String** | Only select jobs which exist for the given job definition. |  [optional]
**processInstanceId** | **String** | Only select jobs which exist for the given process instance. |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | Only select jobs which exist for the given  list of process instance ids. |  [optional]
**executionId** | **String** | Only select jobs which exist for the given execution. |  [optional]
**processDefinitionId** | **String** | Filter by the id of the process definition the jobs run on. |  [optional]
**processDefinitionKey** | **String** | Filter by the key of the process definition the jobs run on. |  [optional]
**activityId** | **String** | Only select jobs which exist for an activity with the given id. |  [optional]
**withRetriesLeft** | **Boolean** | Only select jobs which have retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**executable** | **Boolean** | Only select jobs which are executable, i.e., retries &gt; 0 and due date is &#x60;null&#x60; or due date is in the past. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**timers** | **Boolean** | Only select jobs that are timers. Cannot be used together with &#x60;messages&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**messages** | **Boolean** | Only select jobs that are messages. Cannot be used together with &#x60;timers&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**dueDates** | [**List&lt;JobConditionQueryParameterDto&gt;**](JobConditionQueryParameterDto.md) | Only select jobs where the due date is lower or higher than the given date.  |  [optional]
**createTimes** | [**List&lt;JobConditionQueryParameterDto&gt;**](JobConditionQueryParameterDto.md) | Only select jobs created before or after the given date.  |  [optional]
**withException** | **Boolean** | Only select jobs that failed due to an exception. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**exceptionMessage** | **String** | Only select jobs that failed due to an exception with the given message. |  [optional]
**failedActivityId** | **String** | Only select jobs that failed due to an exception at an activity with the given id. |  [optional]
**noRetriesLeft** | **Boolean** | Only select jobs which have no retries left. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**active** | **Boolean** | Only include active jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**suspended** | **Boolean** | Only include suspended jobs. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**priorityLowerThanOrEquals** | **Long** | Only include jobs with a priority lower than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**priorityHigherThanOrEquals** | **Long** | Only include jobs with a priority higher than or equal to the given value. Value must be a valid &#x60;long&#x60; value. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Only include jobs which belong to one of the passed  tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only include jobs which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**includeJobsWithoutTenantId** | **Boolean** | Include jobs which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**sorting** | [**List&lt;JobQueryDtoSorting&gt;**](JobQueryDtoSorting.md) | An array of criteria to sort the result by. Each element of the array is                        an object that specifies one ordering. The position in the array                        identifies the rank of an ordering, i.e., whether it is primary, secondary,                        etc. Does not have an effect for the &#x60;count&#x60; endpoint. |  [optional]



