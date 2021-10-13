

# ProcessInstanceSuspensionStateAsyncDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**processInstanceIds** | **List&lt;String&gt;** | A list of process instance ids which defines a group of process instances which will be activated or suspended by the operation. |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**historicProcessInstanceQuery** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md) |  |  [optional]
**suspended** | **Boolean** | A Boolean value which indicates whether to activate or suspend a given instance  (e.g. process instance, job, job definition, or batch). When the value is set to true,  the given instance will be suspended and when the value is set to false,  the given instance will be activated. |  [optional]



