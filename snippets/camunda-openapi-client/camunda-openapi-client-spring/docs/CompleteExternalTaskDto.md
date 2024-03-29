

# CompleteExternalTaskDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing variable key-value pairs. Each key is a variable name and each value a JSON variable value object with the following properties: |  [optional]
**localVariables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing local variable key-value pairs. Local variables are set only in the scope of external task. Each key is a variable name and each value a JSON variable value object with the following properties: |  [optional]
**workerId** | **String** | **Mandatory.** The ID of the worker who is performing the operation on the external task. If the task is already locked, must match the id of the worker who has most recently locked the task. |  [optional]



