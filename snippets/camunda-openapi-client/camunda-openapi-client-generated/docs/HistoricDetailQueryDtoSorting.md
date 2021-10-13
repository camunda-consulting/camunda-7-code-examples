

# HistoricDetailQueryDtoSorting


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sortBy** | [**SortByEnum**](#SortByEnum) | Sort the results lexicographically by a given criterion. Must be used in conjunction with the sortOrder parameter. |  [optional]
**sortOrder** | [**SortOrderEnum**](#SortOrderEnum) | Sort the results in a given order. Values may be &#x60;asc&#x60; for ascending order or &#x60;desc&#x60; for descending order. Must be used in conjunction with the sortBy parameter. |  [optional]



## Enum: SortByEnum

Name | Value
---- | -----
PROCESSINSTANCEID | &quot;processInstanceId&quot;
VARIABLENAME | &quot;variableName&quot;
VARIABLETYPE | &quot;variableType&quot;
VARIABLEREVISION | &quot;variableRevision&quot;
FORMPROPERTYID | &quot;formPropertyId&quot;
TIME | &quot;time&quot;
OCCURRENCE | &quot;occurrence&quot;
TENANTID | &quot;tenantId&quot;



## Enum: SortOrderEnum

Name | Value
---- | -----
ASC | &quot;asc&quot;
DESC | &quot;desc&quot;



