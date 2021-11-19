

# JobDefinitionDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | The id of the job definition. |  [optional]
**processDefinitionId** | **String** | The id of the process definition this job definition is associated with. |  [optional]
**processDefinitionKey** | **String** | The key of the process definition this job definition is associated with. |  [optional]
**activityId** | **String** | The id of the activity this job definition is associated with. |  [optional]
**jobType** | **String** | The type of the job which is running for this job definition. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/the-job-executor/#job-creation) for more information about job types. |  [optional]
**jobConfiguration** | **String** | The configuration of a job definition provides details about the jobs which will be created. For example: for timer jobs it is the timer configuration. |  [optional]
**overridingJobPriority** | **Long** | The execution priority defined for jobs that are created based on this definition. May be &#x60;null&#x60; when the priority has not been overridden on the job definition level. |  [optional]
**suspended** | **Boolean** | Indicates whether this job definition is suspended or not. |  [optional]
**tenantId** | **String** | The id of the tenant this job definition is associated with. |  [optional]
**deploymentId** | **String** | The id of the deployment this job definition is related to. In a deployment-aware setup, this leads to all jobs of the same definition being executed on the same node. |  [optional]



