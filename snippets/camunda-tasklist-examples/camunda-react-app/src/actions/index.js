import * as ProcessDefinitionActions from './camunda-rest/process-definition'
import * as TaskActions from './camunda-rest/task'
import * as DeploymentActions from './camunda-rest/deployment'

export const loadTasks = () => (dispatch, getState) => {
  return dispatch(TaskActions.fetchTasks())
}

export const loadTaskFormKey = (taskId) => (dispatch, getState) => {
  return dispatch(TaskActions.fetchTaskFormKey(taskId))
}

export const completeTask = (taskId, values) => (dispatch, getState) => {
  return dispatch(TaskActions.postCompleTask(taskId, values))
}

export const loadProcessDefinitions = (processDefinitionId) => (dispatch, getState) => {
  return dispatch(ProcessDefinitionActions.fetchProcessDefinitions(processDefinitionId))
}

export const loadProcessDefinitionsWithXML = (processDefinitionId) => (dispatch, getState) => {
  return dispatch(ProcessDefinitionActions.fetchProcessDefinitions(processDefinitionId)).then((data) => {
    data.response.result.forEach((id) => {
      dispatch(ProcessDefinitionActions.fetchProcessDefinitionXML(id))
    });

  })
}

export const loadProcessDefinitionXML = (processDefinitionId) => (dispatch, getState) => {
  return dispatch(ProcessDefinitionActions.fetchProcessDefinitionXML(processDefinitionId))
}

export const loadFormKey = (processDefinitionKey) => (dispatch, getState) => {
  return dispatch(ProcessDefinitionActions.fetchFormKey(processDefinitionKey))
}

export const startProcessInstance = (processDefinitionKey, values) => (dispatch, getState) => {
  return dispatch(ProcessDefinitionActions.postProcessInstance(processDefinitionKey, values))
}

export const loadTaskVariables = (taskId, variableNames) => (dispatch, getState) => {
  return dispatch(TaskActions.fetchTaskVariables(taskId, variableNames))
}

export const deployProcess = (filename, file) => (dispatch, getState) => {
  return dispatch(DeploymentActions.postProcessXML(filename, file))
}
