import React from 'react'
import PropTypes from 'prop-types'
import { Provider } from 'react-redux'
import { Route } from 'react-router-dom'
import App from './App'
import StartProcessPage from './StartProcessPage'
import StartProcessListPage from './StartProcessListPage'
import TasklistPage from './TasklistPage'
import Header from '../components/Header'
import Footer from '../components/Footer'

const Root = ({ store }) => (
  <Provider store={store}>
    <div>
      <Header/>
      <Route path="/" component={App} exact/>
      <Route path="/startprocess/key/:process" component={StartProcessPage}/>
      <Route path="/startprocess/list" component={StartProcessListPage}/>
      <Route path="/tasklist" component={TasklistPage} exact/>
      <Route path="/tasklist/:processDefinitionId/:taskId" component={TasklistPage}/>
      <Footer/>
    </div>
  </Provider>
)

Root.propTypes = {
  store: PropTypes.object.isRequired,
}
export default Root
