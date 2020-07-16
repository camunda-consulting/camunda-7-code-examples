

# DeleteProcessInstancesDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**processInstanceIds** | **List&lt;String&gt;** | A list process instance ids to delete. |  [optional]
**deleteReason** | **String** | A string with delete reason. |  [optional]
**skipCustomListeners** | **Boolean** | Skip execution listener invocation for activities that are started or ended as part of this request. |  [optional]
**skipSubprocesses** | **Boolean** | Skip deletion of the subprocesses related to deleted processes as part of this request. |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**historicProcessInstanceQuery** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md) |  |  [optional]



