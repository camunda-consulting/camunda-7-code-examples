

# StartProcessInstanceDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**businessKey** | **String** | The business key of the process instance. |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) |  |  [optional]
**caseInstanceId** | **String** | The case instance id the process instance is to be initialized with. |  [optional]
**startInstructions** | [**List&lt;ProcessInstanceModificationInstructionDto&gt;**](ProcessInstanceModificationInstructionDto.md) | **Optional**. A JSON array of instructions that specify which activities to start the process instance at. If this property is omitted, the process instance starts at its default blank start event. |  [optional]
**skipCustomListeners** | **Boolean** | Skip execution listener invocation for activities that are started or ended as part of this request. **Note**: This option is currently only respected when start instructions are submitted via the &#x60;startInstructions&#x60; property. |  [optional]
**skipIoMappings** | **Boolean** | Skip execution of [input/output variable mappings](https://docs.camunda.org/manual/7.13/user-guide/process-engine/variables/#input-output-variable-mapping) for activities that are started or ended as part of this request. **Note**: This option is currently only respected when start instructions are submitted via the &#x60;startInstructions&#x60; property. |  [optional]
**withVariablesInReturn** | **Boolean** | Indicates if the variables, which was used by the process instance during execution, should be returned. Default value: &#x60;false&#x60; |  [optional]



