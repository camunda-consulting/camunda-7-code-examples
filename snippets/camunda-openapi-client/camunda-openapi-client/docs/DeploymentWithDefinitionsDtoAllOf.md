

# DeploymentWithDefinitionsDtoAllOf

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**deployedProcessDefinitions** | [**Map&lt;String, ProcessDefinitionDto&gt;**](ProcessDefinitionDto.md) | A JSON Object containing a property for each of the process definitions, which are successfully deployed with that deployment. The key is the process definition id, the value is a JSON Object corresponding to the process definition. |  [optional]
**deployedDecisionDefinitions** | [**Map&lt;String, DecisionDefinitionDto&gt;**](DecisionDefinitionDto.md) | A JSON Object containing a property for each of the decision definitions, which are successfully deployed with that deployment. The key is the decision definition id, the value is a JSON Object corresponding to the decision definition. |  [optional]
**deployedDecisionRequirementsDefinitions** | [**Map&lt;String, DecisionRequirementsDefinitionDto&gt;**](DecisionRequirementsDefinitionDto.md) | A JSON Object containing a property for each of the decision requirements definitions, which are successfully deployed with that deployment. The key is the decision requirements definition id, the value is a JSON Object corresponding to the decision requirements definition. |  [optional]
**deployedCaseDefinitions** | [**Map&lt;String, CaseDefinitionDto&gt;**](CaseDefinitionDto.md) | A JSON Object containing a property for each of the case definitions, which are successfully deployed with that deployment. The key is the case definition id, the value is a JSON Object corresponding to the case definition. |  [optional]



