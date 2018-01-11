import * as ActionTypes from '../constants/ActionTypes'
import merge from 'lodash/merge'
import { combineReducers } from 'redux'
import { reducer as reduxFormReducer } from 'redux-form'

// Updates an entity cache in response to any action with response.entities.
const entities = (state = {}, action) => {
  const { type } = action
  if (type === ActionTypes.TASK_SUBMITTED_SUCCESS || type === ActionTypes.TASK_SUBMITTED_FAILURE) {
    return merge({}, state, {
      redirect: '/tasklist'
    })
  } else {
    state = merge({}, state, {
      redirect: null
    })
  }
  if (type === ActionTypes.NEW_PROCESS_INSTANCE_SUCCESS) {
    state.formKey = null
  }
  if (type === ActionTypes.FORM_KEY_SUCCESS) {
    state.processInstanceStarted = null
  }
  if (type === ActionTypes.TASKS_SUCCESS) {
    state.task = null
  }

  if (action.response && action.response.entities) {
    return merge({}, state, action.response.entities)
  }
  return state
}

const rootReducer = combineReducers({
  form: reduxFormReducer,
  entities
})

export default rootReducer
