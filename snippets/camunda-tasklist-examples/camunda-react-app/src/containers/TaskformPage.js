import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter, Redirect } from 'react-router-dom'
import GenericForm from '../components/GenericForm'
import { loadTaskFormKey, loadProcessDefinitions } from '../actions'

class TaskForm extends Component {
  componentDidMount() {
    this.props.loadTaskFormKey(this.props.taskId);
    this.props.loadProcessDefinitions(this.props.processDefinitionId);
  }

  render() {

    const { taskId, processDefinitionId, formKey, processDefinition, redirect, simpleForm } = this.props
    if (redirect) {
      return (
        <Redirect to={redirect}/>
      )
    }
    if (formKey && processDefinition && processDefinition[processDefinitionId] != null) {
      const process = processDefinition[processDefinitionId].key
      const key = formKey['undefined'].key
      return (
        <div>
          <GenericForm form={simpleForm} taskId={taskId} formKey={key} processDefinitionKey={process} />
        </div>
      )
    } else {
      return (
        <div>
          Loading Task
        </div>
      )
    }
  }
}

const mapStateToProps = (state, ownProps) => {
  const params = ownProps.match.params
  return {
    ...params,
    ...state.entities,
    ...state.form
  }
}

export default withRouter(connect(mapStateToProps, {
  loadTaskFormKey,
  loadProcessDefinitions
})(TaskForm))
