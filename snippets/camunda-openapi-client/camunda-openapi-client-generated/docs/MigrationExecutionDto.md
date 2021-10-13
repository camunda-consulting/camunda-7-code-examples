

# MigrationExecutionDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**migrationPlan** | [**MigrationPlanDto**](MigrationPlanDto.md) |  |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | A list of process instance ids to migrate. |  [optional]
**processInstanceQuery** | [**ProcessInstanceQueryDto**](ProcessInstanceQueryDto.md) |  |  [optional]
**skipCustomListeners** | **Boolean** | A boolean value to control whether execution listeners should be invoked during migration. |  [optional]
**skipIoMappings** | **Boolean** | A boolean value to control whether input/output mappings should be executed during migration. |  [optional]



