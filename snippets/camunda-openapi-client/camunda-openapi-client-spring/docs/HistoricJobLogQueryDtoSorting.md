

# HistoricJobLogQueryDtoSorting


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sortBy** | [**SortByEnum**](#SortByEnum) | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. |  [optional]
**sortOrder** | [**SortOrderEnum**](#SortOrderEnum) | Sort the results in a given order. Values may be &#x60;asc&#x60; for ascending order or &#x60;desc&#x60; for descending order. Must be used in conjunction with the sortBy parameter. |  [optional]



## Enum: SortByEnum

Name | Value
---- | -----
TIMESTAMP | &quot;timestamp&quot;
JOBID | &quot;jobId&quot;
JOBDEFINITIONID | &quot;jobDefinitionId&quot;
JOBDUEDATE | &quot;jobDueDate&quot;
JOBRETRIES | &quot;jobRetries&quot;
JOBPRIORITY | &quot;jobPriority&quot;
ACTIVITYID | &quot;activityId&quot;
EXECUTIONID | &quot;executionId&quot;
PROCESSINSTANCEID | &quot;processInstanceId&quot;
PROCESSDEFINITIONID | &quot;processDefinitionId&quot;
PROCESSDEFINITIONKEY | &quot;processDefinitionKey&quot;
DEPLOYMENTID | &quot;deploymentId&quot;
HOSTNAME | &quot;hostname&quot;
OCCURRENCE | &quot;occurrence&quot;
TENANTID | &quot;tenantId&quot;



## Enum: SortOrderEnum

Name | Value
---- | -----
ASC | &quot;asc&quot;
DESC | &quot;desc&quot;



