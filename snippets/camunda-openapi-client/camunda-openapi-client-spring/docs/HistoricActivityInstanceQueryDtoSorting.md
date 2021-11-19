

# HistoricActivityInstanceQueryDtoSorting


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sortBy** | [**SortByEnum**](#SortByEnum) | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. |  [optional]
**sortOrder** | [**SortOrderEnum**](#SortOrderEnum) | Sort the results in a given order. Values may be &#x60;asc&#x60; for ascending order or &#x60;desc&#x60; for descending order. Must be used in conjunction with the sortBy parameter. |  [optional]



## Enum: SortByEnum

Name | Value
---- | -----
ACTIVITYINSTANCEID | &quot;activityInstanceId&quot;
INSTANCEID | &quot;instanceId&quot;
EXECUTIONID | &quot;executionId&quot;
ACTIVITYID | &quot;activityId&quot;
ACTIVITYNAME | &quot;activityName&quot;
ACTIVITYTYPE | &quot;activityType&quot;
STARTTIME | &quot;startTime&quot;
ENDTIME | &quot;endTime&quot;
DURATION | &quot;duration&quot;
DEFINITIONID | &quot;definitionId&quot;
OCCURRENCE | &quot;occurrence&quot;
TENANTID | &quot;tenantId&quot;



## Enum: SortOrderEnum

Name | Value
---- | -----
ASC | &quot;asc&quot;
DESC | &quot;desc&quot;



