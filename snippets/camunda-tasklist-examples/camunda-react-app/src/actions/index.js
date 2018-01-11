import { CALL_API, Schemas } from '../middleware/api'
import * as AT from '../constants/ActionTypes'
/* eslint import/no-webpack-loader-syntax: off */

const fetchTasks = () => ({
  [CALL_API]: {
    types: [ AT.TASKS_REQUEST, AT.TASKS_SUCCESS, AT.TASKS_FAILURE ],
    endpoint: 'task?sortBy=created&sortOrder=desc',
    schema: Schemas.TASK_ARRAY
  }
})

export const loadTasks = () => (dispatch, getState) => {
  return dispatch(fetchTasks())
}

const fetchProcessDefinitions = (processDefinitionId) => {
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

export const loadProcessDefinitions = (processDefinitionId) => (dispatch, getState) => {
  return dispatch(fetchProcessDefinitions(processDefinitionId))
}

const fetchFormKey = (processDefinitionKey) => ({
  [CALL_API]: {
    types: [ AT.FORM_KEY_REQUEST, AT.FORM_KEY_SUCCESS, AT.FORM_KEY_FAILURE ],
    endpoint: `process-definition/key/${processDefinitionKey}/startForm`,
    schema: Schemas.FORM_KEY
  }
})

export const loadFormKey = (processDefinitionKey) => (dispatch, getState) => {
  return dispatch(fetchFormKey(processDefinitionKey))
}

const fetchTaskFormKey = (taskId) => ({
  [CALL_API]: {
    types: [ AT.TASK_FORM_KEY_REQUEST, AT.TASK_FORM_KEY_SUCCESS, AT.TASK_FORM_KEY_FAILURE ],
    endpoint: `task/${taskId}/form`,
    schema: Schemas.FORM_KEY
  }
})

export const loadTaskFormKey = (taskId) => (dispatch, getState) => {
  return dispatch(fetchTaskFormKey(taskId))
}

const postProcessInstance = (processDefinitionKey, values) => ({
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

export const startProcessInstance = (processDefinitionKey, values) => (dispatch, getState) => {
  return dispatch(postProcessInstance(processDefinitionKey, values))
}

const postCompleTask = (taskId, values) => ({
  [CALL_API]: {
    types: [ AT.TASK_SUBMITTED_REQUEST, AT.TASK_SUBMITTED_SUCCESS, AT.TASK_SUBMITTED_FAILURE ],
    endpoint: `task/${taskId}/complete`,
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

export const completeTask = (taskId, values) => (dispatch, getState) => {
  return dispatch(postCompleTask(taskId, values))
}

const fetchTaskVariables = (taskId, variableNames) => {
  const variableName = Object.keys(variableNames).join(',')
  return {
    [CALL_API]: {
      types: [ AT.TASK_VARIABLES_REQUEST, AT.TASK_VARIABLES_SUCCESS, AT.TASK_VARIABLES_FAILURE ],
      endpoint: `task/${taskId}/form-variables?variableNames=${variableName}`,
      schema: Schemas.TASK_VARIABLES
    }
  }
}

export const loadTaskVariables = (taskId, variableNames) => (dispatch, getState) => {
  return dispatch(fetchTaskVariables(taskId, variableNames))
}

const postProcessXML = (filename, file) => {
  let body = new FormData()
  var blob = new Blob([file], { type: "text/xml"});
  body.append('data', blob, filename);
  return {
    [CALL_API]: {
      types: [ AT.PROCESS_DEPLOYMENT_REQUEST, AT.PROCESS_DEPLOYMENT_SUCCESS, AT.PROCESS_DEPLOYMENT_FAILURE ],
      endpoint: `deployment/create`,
      schema: Schemas.PROCESS_DEPLOYMENT,
      settings: {
        method: 'post',
        body: body,
        headers: {
          'Accept': 'application/json'
        }
      }
    }
  }
}

export const deployProcess = (filename, file) => (dispatch, getState) => {
  return dispatch(postProcessXML(filename, file))
}

// Resets the currently visible error message.
export const resetErrorMessage = () => ({
    type: AT.RESET_ERROR_MESSAGE
})
