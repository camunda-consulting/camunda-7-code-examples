import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter, Link } from 'react-router-dom'
import { List, Grid } from 'semantic-ui-react'
import { loadTasks } from '../actions'
import TaskformPage from './TaskformPage'

class TasklistPage extends Component {
  componentWillMount() {
    this.props.loadTasks();
  }

  renderItem(task) {
    return (<List.Item key={task.id}>
      <List.Icon name='browser' size='large' verticalAlign='middle' />
      <List.Content>
        <Link to={`/tasklist/${task.processDefinitionId}/${task.id}`}>
          <List.Header>{task.name}</List.Header>
          <List.Description>{task.created}</List.Description>
        </Link>
      </List.Content>
    </List.Item>)
  }

  render() {
    const { task } = this.props
    let taskForm = ''
    if (this.props.processDefinitionId) {
      taskForm = <TaskformPage/>
    } else {
      taskForm = <div>Please choose task.</div>
    }

    if (!task) {
      return (<div>Loading tasks</div>)
    } else {
      return (
        <Grid divided>
          <Grid.Row>
            <Grid.Column width={4}>
              <h3>Tasklist</h3>
              <List divided relaxed>
              {Object.keys(task).map((id) => this.renderItem(task[id]))}
              </List>
            </Grid.Column>
            <Grid.Column width={12}>
              {taskForm}
            </Grid.Column>
          </Grid.Row>
        </Grid>
      )
    }

  }
}

const mapStateToProps = (state, ownProps) => {
  console.log(state)
  const params = ownProps.match.params
  return {
    ...params,
    ...state.entities
  }
}

export default withRouter(connect(mapStateToProps, {
  loadTasks
})(TasklistPage))
