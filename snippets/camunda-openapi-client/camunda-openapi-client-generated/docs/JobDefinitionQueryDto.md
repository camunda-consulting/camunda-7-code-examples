

# JobDefinitionQueryDto

A Job definition query which defines a list of Job definitions

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**jobDefinitionId** | **String** | Filter by job definition id. |  [optional]
**activityIdIn** | **List&lt;String&gt;** | Only include job definitions which belong to one of the passed activity ids. |  [optional]
**processDefinitionId** | **String** | Only include job definitions which exist for the given process definition id. |  [optional]
**processDefinitionKey** | **String** | Only include job definitions which exist for the given process definition key. |  [optional]
**jobType** | **String** | Only include job definitions which exist for the given job type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. |  [optional]
**jobConfiguration** | **String** | Only include job definitions which exist for the given job configuration. For example: for timer jobs it is the timer configuration. |  [optional]
**active** | **Boolean** | Only include active job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**suspended** | **Boolean** | Only include suspended job definitions. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**withOverridingJobPriority** | **Boolean** | Only include job definitions that have an overriding job priority defined. The only effective value is &#x60;true&#x60;. If set to &#x60;false&#x60;, this filter is not applied. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Only include job definitions which belong to one of the passed tenant ids. |  [optional]
**withoutTenantId** | **Boolean** | Only include job definitions which belong to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**includeJobDefinitionsWithoutTenantId** | **Boolean** | Include job definitions which belong to no tenant. Can be used in combination with &#x60;tenantIdIn&#x60;. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. |  [optional]
**sorting** | [**List&lt;JobDefinitionQueryDtoSorting&gt;**](JobDefinitionQueryDtoSorting.md) | An array of criteria to sort the result by. Each element of the array is                        an object that specifies one ordering. The position in the array                        identifies the rank of an ordering, i.e., whether it is primary, secondary,                        etc. Sorting has no effect for &#x60;count&#x60; endpoints. |  [optional]



