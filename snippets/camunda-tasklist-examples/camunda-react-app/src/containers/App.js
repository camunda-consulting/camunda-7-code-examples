import React from 'react'
import PropTypes from 'prop-types'
import { connect } from 'react-redux'

const App = ({actions, children}) => (
  <div>This is just a Demo Application</div>
)

const mapStateToProps = state => ({
})

export default connect(
  mapStateToProps,
)(App)
