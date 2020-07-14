

# SetJobRetriesByProcessDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**processInstances** | **List&lt;String&gt;** | A list of process instance ids to fetch jobs, for which retries will be set. |  [optional]
**retries** | **Integer** | An integer representing the number of retries. Please note that the value cannot be negative or null. |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**historicProcessInstanceQuery** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md) |  |  [optional]



