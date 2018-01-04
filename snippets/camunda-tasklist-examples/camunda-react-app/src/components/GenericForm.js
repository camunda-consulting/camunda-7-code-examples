import React, { Component } from 'react'
import * as FormTypes from './forms';
import { completeTask, startProcessInstance } from '../actions'

export default class GenericForm extends Component {
  render() {
    const { formKey, processDefinitionKey, task } = this.props
    const Form = FormTypes[processDefinitionKey][formKey]
    if (task == null) {
      return (
        <div className="generic-form">
          <Form onSubmit={(values, dispatch) => this.handleStartInstance(values, dispatch)} />
        </div>
      )
    } else {
      return (
        <div className="generic-form">
          <Form onSubmit={(values, dispatch) => this.handleComplete(values, dispatch)} />
        </div>
      )
    }
  }

  handleComplete(values, dispatch) {
    values = this.getBody(values)
    return dispatch(completeTask(this.props.task, values))
  }

  handleStartInstance(values, dispatch) {
    values = this.getBody(values)
    return dispatch(startProcessInstance(this.props.processDefinitionKey, values))
  }

  getBody(values) {
    let variables = {}
    Object.keys(values).forEach((item) => {
      variables[item] = {'value': values[item]}
    });
    return {
      'variables': variables
    }
  }
}
