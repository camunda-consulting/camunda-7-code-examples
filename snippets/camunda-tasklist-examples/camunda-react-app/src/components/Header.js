import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'

export default class Header extends Component {
  render() {
    return (
      <header className="header">
        <h1>Camunda</h1>
        <Menu fluid widths={3}>
          <Menu.Item>
            <Link to="/">Home</Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/startProcess/list">Start Process</Link>
          </Menu.Item>
          <Menu.Item>
            <Link to="/tasklist">Tasklist</Link>
          </Menu.Item>
        </Menu>
      </header>
    )
  }
}
