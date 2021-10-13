

# JobDefinitionsSuspensionStateDtoAllOf

Defines by which selection criterion to activate or suspend job definitions. The selection criteria are mutually exclusive and can only be one of: * `processDefinitionId` * `processDefinitionKey`

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**processDefinitionId** | **String** | The process definition id of the job definitions to activate or suspend. |  [optional]
**processDefinitionKey** | **String** | The process definition key of the job definitions to activate or suspend. |  [optional]
**processDefinitionTenantId** | **String** | Only activate or suspend job definitions of a process definition which belongs to a tenant with the given id.  Note that this parameter will only be considered  in combination with &#x60;processDefinitionKey&#x60;. |  [optional]
**processDefinitionWithoutTenantId** | **Boolean** | Only activate or suspend job definitions of a process definition which belongs to no tenant. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior.  Note that this parameter will only be considered  in combination with &#x60;processDefinitionKey&#x60;. |  [optional]



