import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter, Link } from 'react-router-dom'

import List from '../components/List'
import { loadProcessDefinitions } from '../actions'

class StartProcessListPage extends Component {
  componentWillMount() {
    this.props.loadProcessDefinitions();
  }

  renderProcess(process) {
    return <li key={process.id}><Link to={`/startProcess/key/${process.key}`}>{process.name} - Version {process.version}</Link></li>
  }

  render() {
    const { processDefinition } = this.props

    if (!processDefinition) {
      return (
        <div><p>Loading process definitions...</p></div>
      )
    } else {
      return (
        <div>
          <h3>Choose process to start</h3>
          <List renderItem={this.renderProcess}
            items={processDefinition}
            loadingLabel={`Loading process definitions...`}
            />
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
  loadProcessDefinitions
})(StartProcessListPage))
