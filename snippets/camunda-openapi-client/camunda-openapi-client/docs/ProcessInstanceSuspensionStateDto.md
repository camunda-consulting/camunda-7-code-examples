

# ProcessInstanceSuspensionStateDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**suspended** | **Boolean** | A &#x60;Boolean&#x60; value which indicates whether to activate or suspend a given process instance. When the value is set to &#x60;true&#x60;, the given process instance will be suspended and when the value is set to &#x60;false&#x60;, the given process instance will be activated. |  [optional]
**processDefinitionId** | **String** | The process definition id of the process instances to activate or suspend.  **Note**: This parameter can be used only with combination of &#x60;suspended&#x60;. |  [optional]
**processDefinitionKey** | **String** | The process definition key of the process instances to activate or suspend.  **Note**: This parameter can be used only with combination of &#x60;suspended&#x60;, &#x60;processDefinitionTenantId&#x60;, and &#x60;processDefinitionWithoutTenantId&#x60;. |  [optional]
**processDefinitionTenantId** | **String** | Only activate or suspend process instances of a process definition which belongs to a tenant with the given id.  **Note**: This parameter can be used only with combination of &#x60;suspended&#x60;, &#x60;processDefinitionKey&#x60;, and &#x60;processDefinitionWithoutTenantId&#x60;. |  [optional]
**processDefinitionWithoutTenantId** | **Boolean** | Only activate or suspend process instances of a process definition which belongs to no tenant. Value may only be true, as false is the default behavior.  **Note**: This parameter can be used only with combination of &#x60;suspended&#x60;, &#x60;processDefinitionKey&#x60;, and &#x60;processDefinitionTenantId&#x60;. |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | A list of process instance ids which defines a group of process instances which will be activated or suspended by the operation.  **Note**: This parameter can be used only with combination of &#x60;suspended&#x60;, &#x60;processInstanceQuery&#x60;, and &#x60;historicProcessInstanceQuery&#x60;. |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**historicProcessInstanceQuery** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md) |  |  [optional]



