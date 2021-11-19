

# CorrelationMessageAsyncDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**messageName** | **String** | The name of the message to correlate. Corresponds to the &#39;name&#39; element of the message defined in BPMN 2.0 XML. Can be null to correlate by other criteria only. |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | A list of process instance ids that define a group of process instances to which the operation will correlate a message.  Please note that if &#x60;processInstanceIds&#x60;, &#x60;processInstanceQuery&#x60; and &#x60;historicProcessInstanceQuery&#x60; are defined, the resulting operation will be performed on the union of these sets. |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**historicProcessInstanceQuery** | [**HistoricProcessInstanceQueryDto**](HistoricProcessInstanceQueryDto.md) |  |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | All variables the operation will set in the root scope of the process instances the message is correlated to. |  [optional]



