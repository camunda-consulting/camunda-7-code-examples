import React from 'react'
import { connect } from 'react-redux'
import { Field, reduxForm } from 'redux-form'
import { Form, Button } from 'semantic-ui-react'
import { InputField, CheckboxField, TextAreaField } from 'react-semantic-redux-form'
import * as Validation from '../../../constants/ValidationOptions'

let SimpleForm = props => {
  const { handleSubmit } = props
  return (
    <Form onSubmit={handleSubmit}>
      <legend>Approve Form</legend>
      <Field name='lastName' component={InputField} label='Last Name' placeholder='Last Name'
        validate={[ Validation.required, Validation.maxLength15, Validation.minLength2 ]}/>
      <Field name='items' component={TextAreaField} label='Items'/>
      <Form.Group>
        <Field name='approved' component={CheckboxField} label='Approve'/>
      </Form.Group>

      <Form.Field control={Button} primary type='submit'>Complete</Form.Field>
    </Form>
  )
}

SimpleForm = reduxForm({
  form: 'simpleForm',
  enableReinitialize: true
})(SimpleForm)
SimpleForm = connect(
  state => ({
    initialValues: state.entities.taskVariables ? state.entities.taskVariables.variables : {}
  })
)(SimpleForm)
export default SimpleForm
