

# MigrationPlanDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sourceProcessDefinitionId** | **String** | The id of the source process definition for the migration. |  [optional]
**targetProcessDefinitionId** | **String** | The id of the target process definition for the migration. |  [optional]
**instructions** | [**List&lt;MigrationInstructionDto&gt;**](MigrationInstructionDto.md) | A list of migration instructions which map equal activities. Each migration instruction is a JSON object with the following properties: |  [optional]
**variables** | [**Map&lt;String, VariableValueDto&gt;**](VariableValueDto.md) | A map of variables which will be set into the process instances&#39; scope. Each key is a variable name and each value a JSON variable value object. |  [optional]



