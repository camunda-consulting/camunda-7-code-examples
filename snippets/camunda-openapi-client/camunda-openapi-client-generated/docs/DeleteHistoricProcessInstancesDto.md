

# DeleteHistoricProcessInstancesDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**historicProcessInstanceIds** | **List&lt;String&gt;** | A list historic process instance ids to delete. |  [optional]
**historicProcessInstanceQuery** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md) |  |  [optional]
**deleteReason** | **String** | A string with delete reason. |  [optional]
**failIfNotExists** | **Boolean** | If set to &#x60;false&#x60;, the request will still be successful if one ore more of the process ids are not found. |  [optional]



