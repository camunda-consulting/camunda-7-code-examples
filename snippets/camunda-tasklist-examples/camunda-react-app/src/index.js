import React from 'react'
import { render } from 'react-dom'
import { BrowserRouter as Router } from 'react-router-dom'
import Root from './containers/Root'
import configureStore from './store/configureStore'
import 'semantic-ui-css/semantic.min.css'
import './css/index.css'

const store = configureStore()

render(
  <Router>
    <Root store={store} />
  </Router>,
  document.getElementById('root')
)
