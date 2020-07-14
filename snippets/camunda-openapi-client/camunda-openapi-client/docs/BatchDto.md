

# BatchDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the batch. |  [optional]
**type** | **String** | The type of the batch. |  [optional]
**totalJobs** | **Integer** | The total jobs of a batch is the number of batch execution jobs required to complete the batch. |  [optional]
**jobsCreated** | **Integer** | The number of batch execution jobs already created by the seed job. |  [optional]
**batchJobsPerSeed** | **Integer** | The number of batch execution jobs created per seed job invocation. The batch seed job is invoked until it has created all batch execution jobs required by the batch (see totalJobs property). |  [optional]
**invocationsPerBatchJob** | **Integer** | Every batch execution job invokes the command executed by the batch invocationsPerBatchJob times. E.g., for a process instance migration batch this specifies the number of process instances which are migrated per batch execution job. |  [optional]
**seedJobDefinitionId** | **String** | The job definition id for the seed jobs of this batch. |  [optional]
**monitorJobDefinitionId** | **String** | The job definition id for the monitor jobs of this batch. |  [optional]
**batchJobDefinitionId** | **String** | The job definition id for the batch execution jobs of this batch. |  [optional]
**suspended** | **Boolean** | Indicates whether this batch is suspended or not. |  [optional]
**tenantId** | **String** | The tenant id of the batch. |  [optional]
**createUserId** | **String** | The id of the user that created the batch. |  [optional]



