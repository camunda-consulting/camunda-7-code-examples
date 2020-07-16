

# SetRetriesForExternalTasksDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**retries** | **Integer** | The number of retries to set for the external task.  Must be &gt;&#x3D; 0. If this is 0, an incident is created and the task cannot be fetched anymore unless the retries are increased again. Can not be null. |  [optional]
**externalTaskIds** | **List&lt;String&gt;** | The ids of the external tasks to set the number of retries for. |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | The ids of process instances containing the tasks to set the number of retries for. |  [optional]
**externalTaskQuery** | [**ExternalTaskQueryDto**](ExternalTaskQueryDto.md) |  |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**historicProcessInstanceQuery** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md) |  |  [optional]



