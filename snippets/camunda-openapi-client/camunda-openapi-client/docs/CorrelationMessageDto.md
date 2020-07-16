

# CorrelationMessageDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**messageName** | **String** | The name of the message to deliver. |  [optional]
**businessKey** | **String** | Used for correlation of process instances that wait for incoming messages. Will only correlate to executions that belong to a process instance with the provided business key. |  [optional]
**tenantId** | **String** | Used to correlate the message for a tenant with the given id. Will only correlate to executions and process definitions which belong to the tenant. Must not be supplied in conjunction with a &#x60;withoutTenantId&#x60;. |  [optional]
**withoutTenantId** | **Boolean** | A Boolean value that indicates whether the message should only be correlated to executions and process definitions which belong to no tenant or not. Value may only be &#x60;true&#x60;, as &#x60;false&#x60; is the default behavior. Must not be supplied in conjunction with a &#x60;tenantId&#x60;. |  [optional]
**processInstanceId** | **String** | Used to correlate the message to the process instance with the given id. |  [optional]
**correlationKeys** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | Used for correlation of process instances that wait for incoming messages. Has to be a JSON object containing key-value pairs that are matched against process instance variables during correlation. Each key is a variable name and each value a JSON variable value object with the following properties. |  [optional]
**localCorrelationKeys** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | Local variables used for correlation of executions (process instances) that wait for incoming messages. Has to be a JSON object containing key-value pairs that are matched against local variables during correlation. Each key is a variable name and each value a JSON variable value object with the following properties. |  [optional]
**processVariables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A map of variables that is injected into the triggered execution or process instance after the message has been delivered. Each key is a variable name and each value a JSON variable value object with the following properties. |  [optional]
**processVariablesLocal** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A map of local variables that is injected into the triggered execution or process instance after the message has been delivered. Each key is a variable name and each value a JSON variable value object with the following properties. |  [optional]
**all** | **Boolean** | A Boolean value that indicates whether the message should be correlated to exactly one entity or multiple entities. If the value is set to &#x60;false&#x60;, the message will be correlated to exactly one entity (execution or process definition). If the value is set to &#x60;true&#x60;, the message will be correlated to multiple executions and a process definition that can be instantiated by this message in one go. |  [optional]
**resultEnabled** | **Boolean** | A Boolean value that indicates whether the result of the correlation should be returned or not. If this property is set to &#x60;true&#x60;, there will be returned a list of message correlation result objects. Depending on the all property, there will be either one ore more returned results in the list.  The default value is &#x60;false&#x60;, which means no result will be returned. |  [optional]
**variablesInResultEnabled** | **Boolean** | A Boolean value that indicates whether the result of the correlation should contain process variables or not. The parameter resultEnabled should be set to &#x60;true&#x60; in order to use this it.  The default value is &#x60;false&#x60;, which means the variables will not be returned. |  [optional]



