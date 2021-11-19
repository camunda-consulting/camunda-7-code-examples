

# JobSuspensionStateDtoAllOf

Defines by which selection criterion to activate or suspend jobs. This selection criterion are mutually exclusive and can only be on of: * `jobDefinitionId` * `processDefinitionId` * `processInstanceId` * `processDefinitionKey`

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**jobDefinitionId** | **String** | The job definition id of the jobs to activate or suspend. |  [optional]
**processDefinitionId** | **String** | The process definition id of the jobs to activate or suspend. |  [optional]
**processInstanceId** | **String** | The process instance id of the jobs to activate or suspend. |  [optional]
**processDefinitionKey** | **String** | The process definition key of the jobs to activate or suspend. |  [optional]
**processDefinitionTenantId** | **String** | Only activate or suspend jobs of a process definition which belongs to a tenant with the given id. Works only when selecting with &#x60;processDefinitionKey&#x60;. |  [optional]
**processDefinitionWithoutTenantId** | **Boolean** | Only activate or suspend jobs of a process definition which belongs to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. Works only when selecting with &#x60;processDefinitionKey&#x60;. |  [optional]



