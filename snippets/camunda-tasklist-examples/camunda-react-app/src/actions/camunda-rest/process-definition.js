import * as AT from '../../constants/ActionTypes'
import { CALL_API, Schemas } from '../../middleware/api'

export const fetchProcessDefinitions = (processDefinitionId) => {
  if (processDefinitionId) {
    return {
      [CALL_API]: {
        types: [ AT.PROCESS_DEFINITION_REQUEST, AT.PROCESS_DEFINITION_SUCCESS, AT.PROCESS_DEFINITION_FAILURE ],
        endpoint: `process-definition?processDefinitionId=${processDefinitionId}`,
        schema: Schemas.PROCESS_DEFINITION_ARRAY
      }
    }
  } else {
    return {
      [CALL_API]: {
        types: [ AT.PROCESS_DEFINITION_REQUEST, AT.PROCESS_DEFINITION_SUCCESS, AT.PROCESS_DEFINITION_FAILURE ],
        endpoint: 'process-definition?latestVersion=true',
        schema: Schemas.PROCESS_DEFINITION_ARRAY
      }
    }
  }
}

export const fetchFormKey = (processDefinitionKey) => ({
  [CALL_API]: {
    types: [ AT.FORM_KEY_REQUEST, AT.FORM_KEY_SUCCESS, AT.FORM_KEY_FAILURE ],
    endpoint: `process-definition/key/${processDefinitionKey}/startForm`,
    schema: Schemas.FORM_KEY
  }
})

export const fetchProcessDefinitionXML = (processDefinitionId) => ({
  [CALL_API]: {
    types: [ AT.PROCESS_DEFINITION_XML_REQUEST, AT.PROCESS_DEFINITION_XML_SUCCESS, AT.PROCESS_DEFINITION_XML_FAILURE ],
    endpoint: `/process-definition/${processDefinitionId}/xml`,
    schema: Schemas.PROCESS_DEFINITION_XML
  }
})

export const postProcessInstance = (processDefinitionKey, values) => ({
  [CALL_API]: {
    types: [ AT.NEW_PROCESS_INSTANCE_REQUEST, AT.NEW_PROCESS_INSTANCE_SUCCESS, AT.NEW_PROCESS_INSTANCE_FAILURE ],
    endpoint: `process-definition/key/${processDefinitionKey}/start`,
    schema: Schemas.PROCESS_INSTANCE_STARTED,
    settings: {
      method: 'post',
      body: JSON.stringify(values),
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }
  }
})
