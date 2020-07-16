

# SortTaskQueryParametersDto

Mandatory when `sortBy` is one of the following values: `processVariable`, `executionVariable`, `taskVariable`, `caseExecutionVariable` or `caseInstanceVariable`. Must be a JSON object with the properties `variable` and `type` where `variable` is a variable name and `type` is the name of a variable value type.
## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**variable** | **String** | The name of the variable to sort by. |  [optional]
**type** | **String** | The name of the type of the variable value. |  [optional]



