import * as ActionTypes from '../constants/ActionTypes'
import merge from 'lodash/merge'
import { combineReducers } from 'redux'
import { reducer as reduxFormReducer } from 'redux-form'

// Updates an entity cache in response to any action with response.entities.
const entities = (state = {}, action) => {
  const { type } = action
  console.log(type)
  state.redirect = null
  if (type === ActionTypes.TASK_SUBMITTED_SUCCESS || type === ActionTypes.TASK_SUBMITTED_FAILURE) {
    state.redirect = '/tasklist'
  }
  if (type === ActionTypes.NEW_PROCESS_INSTANCE_SUCCESS) {
    state.formKey = null
  }
  if (type === ActionTypes.FORM_KEY_SUCCESS) {
    state.processInstanceStarted = null
  }

  if (action.response && action.response.entities) {
    return merge({}, state, action.response.entities)
  }
  return state
}

// Updates error message to notify about the failed fetches.
const errorMessage = (state = null, action) => {
  const { type, error } = action

  if (type === ActionTypes.RESET_ERROR_MESSAGE) {
    return null
  } else if (error) {
    return error
  }

  return state
}

const rootReducer = combineReducers({
  form: reduxFormReducer,
  entities,
  errorMessage
})

export default rootReducer
