

# FetchExternalTaskTopicDto

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**topicName** | **String** | **Mandatory.** The topic&#39;s name. | 
**lockDuration** | **Long** | **Mandatory.** The duration to lock the external tasks for in milliseconds. | 
**variables** | **List&lt;String&gt;** | A JSON array of &#x60;String&#x60; values that represent variable names. For each result task belonging to this topic, the given variables are returned as well if they are accessible from the external task&#39;s execution. If not provided - all variables will be fetched. |  [optional]
**localVariables** | **Boolean** | If &#x60;true&#x60; only local variables will be fetched. |  [optional]
**businessKey** | **String** | A &#x60;String&#x60; value which enables the filtering of tasks based on process instance business key. |  [optional]
**processDefinitionId** | **String** | Filter tasks based on process definition id. |  [optional]
**processDefinitionIdIn** | **List&lt;String&gt;** | Filter tasks based on process definition ids. |  [optional]
**processDefinitionKey** | **String** | Filter tasks based on process definition key. |  [optional]
**processDefinitionKeyIn** | **List&lt;String&gt;** | Filter tasks based on process definition keys. |  [optional]
**processDefinitionVersionTag** | **String** | Filter tasks based on process definition version tag. |  [optional]
**withoutTenantId** | **Boolean** | Filter tasks without tenant id. |  [optional]
**tenantIdIn** | **List&lt;String&gt;** | Filter tasks based on tenant ids. |  [optional]
**processVariables** | **Map&lt;String, Object&gt;** | A &#x60;JSON&#x60; object used for filtering tasks based on process instance variable values. A property name of the object represents a process variable name, while the property value represents the process variable value to filter tasks by. |  [optional]
**deserializeValues** | **Boolean** | Determines whether serializable variable values (typically variables that store custom Java objects) should be deserialized on server side (default &#x60;false&#x60;).  If set to &#x60;true&#x60;, a serializable variable will be deserialized on server side and transformed to JSON using [Jackson&#39;s](https://github.com/FasterXML/jackson) POJO/bean property introspection feature. Note that this requires the Java classes of the variable value to be on the REST API&#39;s classpath.  If set to &#x60;false&#x60;, a serializable variable will be returned in its serialized format. For example, a variable that is serialized as XML will be returned as a JSON string containing XML. |  [optional]



