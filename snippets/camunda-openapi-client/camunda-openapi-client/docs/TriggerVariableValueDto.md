

# TriggerVariableValueDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**local** | **Boolean** | Indicates whether the variable should be a local variable or not. If set to true, the variable becomes a local variable of the execution entering the target activity. |  [optional]
**value** | [**Object**](.md) | The variable&#39;s value. Value differs depending on the variable&#39;s type and on the deserializeValues parameter. |  [optional]
**type** | **String** | The value type of the variable. |  [optional]
**valueInfo** | **Map&lt;String, Object&gt;** | A JSON object containing additional, value-type-dependent properties. For serialized variables of type Object, the following properties can be provided:  * &#x60;objectTypeName&#x60;: A string representation of the object&#39;s type name. * &#x60;serializationDataFormat&#x60;: The serialization format used to store the variable.  For serialized variables of type File, the following properties can be provided:  * &#x60;filename&#x60;: The name of the file. This is not the variable name but the name that will be used when downloading the file again. * &#x60;mimetype&#x60;: The MIME type of the file that is being uploaded. * &#x60;encoding&#x60;: The encoding of the file that is being uploaded. |  [optional]



