import React, { Component } from 'react'
import { deployProcess } from '../actions'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { Form, Button } from 'semantic-ui-react'
import FileReaderInput from 'react-file-reader-input'

class DeployProcess extends Component {
  handleChange = (e, results) => {
    results.forEach(result => {
      const [e, file] = result
      this.uploadFile(file.name, e.target.result)
    });
  }

  uploadFile(filename, file) {
    this.props.deployProcess(filename, file)
  }

  render() {
    if (!this.props.processDeployment) {
      return (
        <Form>
          <FileReaderInput as="binary" id="my-file-input" onChange={this.handleChange}>
            <Button primary>Select a BPMN file that you want to deploy to the Engine!</Button>
          </FileReaderInput>
        </Form>
      )
    } else {
      return (
        <p>Successfully uploaded bpmn file to engine</p>
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
  deployProcess
})(DeployProcess))
