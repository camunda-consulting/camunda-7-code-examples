

# ModificationDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**processDefinitionId** | **String** | The id of the process definition for the modification |  [optional]
**skipCustomListeners** | **Boolean** | Skip execution listener invocation for activities that are started or ended as part of this request. |  [optional]
**skipIoMappings** | **Boolean** | Skip execution of [input/output variable mappings](https://docs.camunda.org/manual/7.16/user-guide/process-engine/variables/#input-output-variable-mapping) for activities that are started or ended as part of this request. |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | A list of process instance ids to modify. |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**instructions** | [**List&lt;MultipleProcessInstanceModificationInstructionDto&gt;**](MultipleProcessInstanceModificationInstructionDto.md) | An array of modification instructions. The instructions are executed in the order they are in.  |  [optional]
**annotation** | **String** | An arbitrary text annotation set by a user for auditing reasons. |  [optional]



