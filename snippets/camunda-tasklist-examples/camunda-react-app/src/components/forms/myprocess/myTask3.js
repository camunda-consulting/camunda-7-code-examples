import React from 'react'
import { connect } from 'react-redux'
import { Field, reduxForm } from 'redux-form'
import { Form, Icon, Button } from 'semantic-ui-react'
import { InputField, CheckboxField } from 'react-semantic-redux-form'
import * as Validation from '../../../constants/ValidationOptions'

let SimpleForm = props => {
  const { handleSubmit, pristine, reset, submitting } = props
  return (
    <Form onSubmit={handleSubmit}>
      <legend>Approve Form</legend>
      <Field name='firstName' component={InputField} label='First Name' placeholder='First Name'
        validate={[Validation.required, Validation.maxLength15, Validation.minLength2]}
       />
      <Field name='lastName' component={InputField} label='Last Name' placeholder='Last Name' />
      <Form.Group>
        <Field name='approve' component={CheckboxField} label='Approve'/>
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
