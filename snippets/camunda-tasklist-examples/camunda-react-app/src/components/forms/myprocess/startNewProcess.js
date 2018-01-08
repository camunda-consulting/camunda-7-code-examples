import React from 'react'
import { connect } from 'react-redux'
import { Field, reduxForm } from 'redux-form'
import { Form, Icon, Button } from 'semantic-ui-react'
import { InputField, CheckboxField } from 'react-semantic-redux-form'
import * as Validation from '../../../constants/ValidationOptions'

const SimpleForm = props => {
  const { handleSubmit, pristine, reset, submitting } = props
  return (
    <Form onSubmit={handleSubmit}>
      <Field name='firstName' component={InputField} label='First Name' placeholder='First Name'
        validate={[Validation.required, Validation.maxLength15, Validation.minLength2]}/>
      <Field name='lastName' component={InputField} label='Last Name' placeholder='Last Name' />
      <Field name='email' component={InputField} label='E-Mail' placeholder='E-Mail' />

      <Form.Field control={Button} primary type='submit'>Complete</Form.Field>
    </Form>
  )
}

export default reduxForm({
  form: 'simple' // a unique identifier for this form
})(SimpleForm)
