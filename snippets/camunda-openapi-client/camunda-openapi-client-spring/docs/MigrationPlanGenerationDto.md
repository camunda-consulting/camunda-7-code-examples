

# MigrationPlanGenerationDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sourceProcessDefinitionId** | **String** | The id of the source process definition for the migration. |  [optional]
**targetProcessDefinitionId** | **String** | The id of the target process definition for the migration. |  [optional]
**updateEventTriggers** | **Boolean** | A boolean flag indicating whether instructions between events should be configured to update the event triggers. |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A map of variables which will be set into the process instances&#39; scope. Each key is a variable name and each value a JSON variable value object. |  [optional]



