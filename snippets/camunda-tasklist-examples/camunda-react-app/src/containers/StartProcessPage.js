import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import GenericForm from '../components/GenericForm'
import { loadProcessDefinitions, loadFormKey } from '../actions'

class StartProcessPage extends Component {
  componentWillMount() {
    this.props.loadFormKey(this.props.process);
  }

  render() {
    const { process, formKey, processInstanceStarted } = this.props

    if (!formKey && !processInstanceStarted) {
      return (
        <div>Loading Process Start Form</div>
      )
    } else if (processInstanceStarted) {
      return (
        <div>Process instance has been started.</div>
      )
    } else {
      const key = formKey['undefined'].key
      return (
        <div>
          <GenericForm formKey={key} processDefinitionKey={process} />
        </div>
      )
    }
  }

}
const mapStateToProps = (state, ownProps) => {
  const params = ownProps.match.params
  return {
    ...params,
    ...state.entities
  }
}

export default withRouter(connect(mapStateToProps, {
  loadProcessDefinitions,
  loadFormKey
})(StartProcessPage))
