import { normalize, schema } from 'normalizr'
import { camelizeKeys } from 'humps'

const API_ROOT = 'http://localhost:8080/engine-rest/'

// Fetches an API response and normalizes the result JSON according to schema.
// This makes every API response have the same shape, regardless of how nested it was.
const callApi = (endpoint, schema, settings = {}) => {
  const fullUrl = (endpoint.indexOf(API_ROOT) === -1) ? API_ROOT + endpoint : endpoint

  return fetch(fullUrl, settings)
    .then(response =>
      response.json().then(json => {
        if (!response.ok) {
          return Promise.reject(json)
        }

        const camelizedJson = camelizeKeys(json)
          return Object.assign({},
          normalize(camelizedJson, schema),
          {  }
        )
      })
    )
}

// We use this Normalizr schemas to transform API responses from a nested form
// to a flat form where repos and users are placed in `entities`, and nested
// JSON objects are replaced with their IDs. This is very convenient for
// consumption by reducers, because we can easily build a normalized tree
// and keep it updated as we fetch more data.

// Read more about Normalizr: https://github.com/paularmstrong/normalizr

const processDefinitionSchema = new schema.Entity('processDefinition', {}, {
  id: processDefinition => processDefinition.id,
  name: processDefinition => processDefinition.name
})

const formKeySchema = new schema.Entity('formKey', {}, {
  idAttribute: 'test',
});

const processInstanceStartedSchema = new schema.Entity('processInstanceStarted', {}, {

});

const taskSchema = new schema.Entity('task', {}, {
  id: task => task.id,
});

const taskVariableSchema = new schema.Entity('taskVariable', {}, {
  processStrategy: (value, parent, key) => {
    return {
      values: value,
      test: key,
      parent: parent
    }
  }
  //id: taskVariable => taskVariable.id,
});
const taskVariableArraySchema = new schema.Entity('taskVariables', {}, {
  idAttribute: variable => 'variables',
  processStrategy: (value, parent, key) => {
    let values = {};
    Object.keys(value).forEach((item) => {
      values[item] = value[item].value
    })
    return values
  }
});

// Schemas for Github API responses.
export const Schemas = {
  PROCESS_DEFINITION: processDefinitionSchema,
  PROCESS_DEFINITION_ARRAY: [processDefinitionSchema],
  FORM_KEY: formKeySchema,
  TASK: taskSchema,
  TASK_ARRAY: [taskSchema],
  PROCESS_INSTANCE_STARTED: processInstanceStartedSchema,
  TASK_VARIABLE: taskVariableSchema,
  TASK_VARIABLES: taskVariableArraySchema
}

// Action key that carries API call info interpreted by this Redux middleware.
export const CALL_API = 'Call API'

// A Redux middleware that interprets actions with CALL_API info specified.
// Performs the call and promises when such actions are dispatched.
export default store => next => action => {
  const callAPI = action[CALL_API]

  if (typeof callAPI === 'undefined') {
    return next(action)
  }

  let { endpoint } = callAPI
  const { schema, types, settings } = callAPI

  if (typeof endpoint === 'function') {
    endpoint = endpoint(store.getState())
  }

  if (typeof endpoint !== 'string') {
    throw new Error('Specify a string endpoint URL.')
  }
  if (!schema) {
    throw new Error('Specify one of the exported Schemas.')
  }
  if (!Array.isArray(types) || types.length !== 3) {
    throw new Error('Expected an array of three action types.')
  }
  if (!types.every(type => typeof type === 'string')) {
    throw new Error('Expected action types to be strings.')
  }

  const actionWith = data => {
    const finalAction = Object.assign({}, action, data)
    delete finalAction[CALL_API]
    return finalAction
  }

  const [ requestType, successType, failureType ] = types
  next(actionWith({ type: requestType }))

  return callApi(endpoint, schema, settings).then(
    response => next(actionWith({
      response,
      type: successType
    })),
    error => next(actionWith({
      type: failureType,
      error: error.message || 'Something bad happened'
    }))
  )
}
