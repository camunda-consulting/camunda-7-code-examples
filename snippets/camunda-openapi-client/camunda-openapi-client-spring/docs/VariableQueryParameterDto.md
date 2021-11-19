

# VariableQueryParameterDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**name** | **String** | Variable name |  [optional]
**operator** | [**OperatorEnum**](#OperatorEnum) | Comparison operator to be used. &#x60;notLike&#x60; is not supported by all endpoints. |  [optional]
**value** | **Object** | Can be any value - string, number, boolean, array or object.  **Note**: Not every endpoint supports every type. |  [optional]



## Enum: OperatorEnum

Name | Value
---- | -----
EQ | &quot;eq&quot;
NEQ | &quot;neq&quot;
GT | &quot;gt&quot;
GTEQ | &quot;gteq&quot;
LT | &quot;lt&quot;
LTEQ | &quot;lteq&quot;
LIKE | &quot;like&quot;
NOTLIKE | &quot;notLike&quot;



