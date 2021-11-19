

# MigrationPlanReportDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**instructionReports** | [**List&lt;MigrationInstructionValidationReportDto&gt;**](MigrationInstructionValidationReportDto.md) | The list of instruction validation reports. If no validation errors are detected it is an empty list. |  [optional]
**variableReports** | [**Map&lt;String, MigrationVariableValidationReportDto&gt;**](MigrationVariableValidationReportDto.md) | A map of variable reports. Each key is a variable name and each value a JSON object consisting of the variable&#39;s type, value, value info object and a list of failures. |  [optional]



