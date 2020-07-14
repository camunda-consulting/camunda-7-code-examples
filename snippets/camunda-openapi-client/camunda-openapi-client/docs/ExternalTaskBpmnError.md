

# ExternalTaskBpmnError

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**workerId** | **String** | The id of the worker that reports the failure. Must match the id of the worker who has most recently locked the task. |  [optional]
**errorCode** | **String** | An error code that indicates the predefined error. It is used to identify the BPMN error handler. |  [optional]
**errorMessage** | **String** | An error message that describes the error. |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A JSON object containing variable key-value pairs. |  [optional]



