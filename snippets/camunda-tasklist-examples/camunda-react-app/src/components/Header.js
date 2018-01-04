import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'

export default class Header extends Component {
  render() {
    return (
      <header className="header">
        <Menu fixed='top' inverted fluid>
          <Menu.Item as='a' header>
           Camunda Tasklist
         </Menu.Item>
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
