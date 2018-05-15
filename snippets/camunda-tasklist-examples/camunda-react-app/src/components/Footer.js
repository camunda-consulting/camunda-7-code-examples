import React, { Component } from 'react'
import { Menu } from 'semantic-ui-react'

export default class Footer extends Component {
  render() {
    return (
      <footer className="footer">
        <Menu inverted fluid widths={1}>
          <Menu.Item>
            Camunda - React Redux Example Tasklist 2018
          </Menu.Item>
        </Menu>
      </footer>
    )
  }
}
