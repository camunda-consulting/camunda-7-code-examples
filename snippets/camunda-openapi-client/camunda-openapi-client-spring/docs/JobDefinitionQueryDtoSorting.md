

# JobDefinitionQueryDtoSorting


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sortBy** | [**SortByEnum**](#SortByEnum) | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. |  [optional]
**sortOrder** | [**SortOrderEnum**](#SortOrderEnum) | Sort the results in a given order. Values may be &#x60;asc&#x60; for ascending order or &#x60;desc&#x60; for descending order. Must be used in conjunction with the sortBy parameter. |  [optional]



## Enum: SortByEnum

Name | Value
---- | -----
JOBDEFINITIONID | &quot;jobDefinitionId&quot;
ACTIVITYID | &quot;activityId&quot;
PROCESSDEFINITIONID | &quot;processDefinitionId&quot;
PROCESSDEFINITIONKEY | &quot;processDefinitionKey&quot;
JOBTYPE | &quot;jobType&quot;
JOBCONFIGURATION | &quot;jobConfiguration&quot;
TENANTID | &quot;tenantId&quot;



## Enum: SortOrderEnum

Name | Value
---- | -----
ASC | &quot;asc&quot;
DESC | &quot;desc&quot;



