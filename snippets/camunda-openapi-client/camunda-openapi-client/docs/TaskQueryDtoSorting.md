

# TaskQueryDtoSorting

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sortBy** | [**SortByEnum**](#SortByEnum) | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. |  [optional]
**sortOrder** | [**SortOrderEnum**](#SortOrderEnum) | Sort the results in a given order. Values may be &#x60;asc&#x60; for ascending order or &#x60;desc&#x60; for descending order. Must be used in conjunction with the sortBy parameter. |  [optional]
**parameters** | [**SortTaskQueryParametersDto**](SortTaskQueryParametersDto.md) |  |  [optional]



## Enum: SortByEnum

Name | Value
---- | -----
INSTANCEID | &quot;instanceId&quot;
CASEINSTANCEID | &quot;caseInstanceId&quot;
DUEDATE | &quot;dueDate&quot;
EXECUTIONID | &quot;executionId&quot;
CASEEXECUTIONID | &quot;caseExecutionId&quot;
ASSIGNEE | &quot;assignee&quot;
CREATED | &quot;created&quot;
DESCRIPTION | &quot;description&quot;
ID | &quot;id&quot;
NAME | &quot;name&quot;
NAMECASEINSENSITIVE | &quot;nameCaseInsensitive&quot;
PRIORITY | &quot;priority&quot;
PROCESSVARIABLE | &quot;processVariable&quot;
EXECUTIONVARIABLE | &quot;executionVariable&quot;
TASKVARIABLE | &quot;taskVariable&quot;
CASEEXECUTIONVARIABLE | &quot;caseExecutionVariable&quot;
CASEINSTANCEVARIABLE | &quot;caseInstanceVariable&quot;



## Enum: SortOrderEnum

Name | Value
---- | -----
ASC | &quot;asc&quot;
DESC | &quot;desc&quot;



