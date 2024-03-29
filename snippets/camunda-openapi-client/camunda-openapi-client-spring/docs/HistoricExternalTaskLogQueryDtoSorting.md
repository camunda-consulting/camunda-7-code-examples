

# HistoricExternalTaskLogQueryDtoSorting


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sortBy** | [**SortByEnum**](#SortByEnum) | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. |  [optional]
**sortOrder** | [**SortOrderEnum**](#SortOrderEnum) | Sort the results in a given order. Values may be &#x60;asc&#x60; for ascending order or &#x60;desc&#x60; for descending order. Must be used in conjunction with the sortBy parameter. |  [optional]



## Enum: SortByEnum

Name | Value
---- | -----
TIMESTAMP | &quot;timestamp&quot;
EXTERNALTASKID | &quot;externalTaskId&quot;
TOPICNAME | &quot;topicName&quot;
WORKERID | &quot;workerId&quot;
RETRIES | &quot;retries&quot;
PRIORITY | &quot;priority&quot;
ACTIVITYID | &quot;activityId&quot;
ACTIVITYINSTANCEID | &quot;activityInstanceId&quot;
EXECUTIONID | &quot;executionId&quot;
PROCESSINSTANCEID | &quot;processInstanceId&quot;
PROCESSDEFINITIONID | &quot;processDefinitionId&quot;
PROCESSDEFINITIONKEY | &quot;processDefinitionKey&quot;
TENANTID | &quot;tenantId&quot;



## Enum: SortOrderEnum

Name | Value
---- | -----
ASC | &quot;asc&quot;
DESC | &quot;desc&quot;



