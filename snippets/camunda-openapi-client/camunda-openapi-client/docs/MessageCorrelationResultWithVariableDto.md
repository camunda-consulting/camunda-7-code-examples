

# MessageCorrelationResultWithVariableDto

The `processInstance` property only has a value if the resultType is set to `ProcessDefinition`. The processInstance with the properties as described in the [get single instance](https://docs.camunda.org/manual/7.13/reference/rest/process-instance/get/) method.  The `execution` property only has a value if the resultType is set to `Execution`. The execution with the properties as described in the [get single execution](https://docs.camunda.org/manual/7.13/reference/rest/execution/get/) method.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**resultType** | [**ResultTypeEnum**](#ResultTypeEnum) | Indicates if the message was correlated to a message start event or an  intermediate message catching event. In the first case, the resultType is  &#x60;ProcessDefinition&#x60; and otherwise &#x60;Execution&#x60;. |  [optional]
**processInstance** | [**ProcessInstanceDto**](ProcessInstanceDto.md) |  |  [optional]
**execution** | [**ExecutionDto**](ExecutionDto.md) |  |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | This property is returned if the &#x60;variablesInResultEnabled&#x60; is set to &#x60;true&#x60;. Contains a list of the process variables.  |  [optional]



## Enum: ResultTypeEnum

Name | Value
---- | -----
EXECUTION | &quot;Execution&quot;
PROCESSDEFINITION | &quot;ProcessDefinition&quot;



