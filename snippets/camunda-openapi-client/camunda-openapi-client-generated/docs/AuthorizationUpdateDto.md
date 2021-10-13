

# AuthorizationUpdateDto


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**permissions** | **List&lt;String&gt;** | An array of Strings holding the permissions provided by this authorization. |  [optional]
**userId** | **String** | The id of the user this authorization has been created for. The value &#x60;*&#x60; represents a global authorization ranging over all users. |  [optional]
**groupId** | **String** | The id of the group this authorization has been created for. |  [optional]
**resourceType** | **Integer** | An integer representing the resource type. See the [User Guide](https://docs.camunda.org/manual/7.16/user-guide/process-engine/authorization-service/#resources) for a list of integer representations of resource types. |  [optional]
**resourceId** | **String** | The resource Id. The value &#x60;*&#x60; represents an authorization ranging over all instances of a resource. |  [optional]



