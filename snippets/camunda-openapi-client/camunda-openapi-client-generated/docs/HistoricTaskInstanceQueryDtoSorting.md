

# HistoricTaskInstanceQueryDtoSorting


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sortBy** | [**SortByEnum**](#SortByEnum) | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. |  [optional]
**sortOrder** | [**SortOrderEnum**](#SortOrderEnum) | Sort the results in a given order. Values may be &#x60;asc&#x60; for ascending order or &#x60;desc&#x60; for descending order. Must be used in conjunction with the sortBy parameter. |  [optional]



## Enum: SortByEnum

Name | Value
---- | -----
TASKID | &quot;taskId&quot;
ACTIVITYINSTANCEID | &quot;activityInstanceId&quot;
PROCESSDEFINITIONID | &quot;processDefinitionId&quot;
PROCESSINSTANCEID | &quot;processInstanceId&quot;
EXECUTIONID | &quot;executionId&quot;
DURATION | &quot;duration&quot;
ENDTIME | &quot;endTime&quot;
STARTTIME | &quot;startTime&quot;
TASKNAME | &quot;taskName&quot;
TASKDESCRIPTION | &quot;taskDescription&quot;
ASSIGNEE | &quot;assignee&quot;
OWNER | &quot;owner&quot;
DUEDATE | &quot;dueDate&quot;
FOLLOWUPDATE | &quot;followUpDate&quot;
DELETEREASON | &quot;deleteReason&quot;
TASKDEFINITIONKEY | &quot;taskDefinitionKey&quot;
PRIORITY | &quot;priority&quot;
CASEDEFINITIONID | &quot;caseDefinitionId&quot;
CASEINSTANCEID | &quot;caseInstanceId&quot;
CASEEXECUTIONID | &quot;caseExecutionId&quot;
TENANTID | &quot;tenantId&quot;



## Enum: SortOrderEnum

Name | Value
---- | -----
ASC | &quot;asc&quot;
DESC | &quot;desc&quot;



