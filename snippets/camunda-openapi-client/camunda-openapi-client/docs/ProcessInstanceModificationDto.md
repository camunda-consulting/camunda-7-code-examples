

# ProcessInstanceModificationDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**skipCustomListeners** | **Boolean** | Skip execution listener invocation for activities that are started or ended as part of this request. |  [optional]
**skipIoMappings** | **Boolean** | Skip execution of [input/output variable mappings](https://docs.camunda.org/manual/7.13/user-guide/process-engine/variables/#input-output-variable-mapping) for activities that are started or ended as part of this request. |  [optional]
**instructions** | [**List&lt;ProcessInstanceModificationInstructionDto&gt;**](ProcessInstanceModificationInstructionDto.md) | JSON array of modification instructions. The instructions are executed in the order they are in. |  [optional]
**annotation** | **String** | An arbitrary text annotation set by a user for auditing reasons. |  [optional]



