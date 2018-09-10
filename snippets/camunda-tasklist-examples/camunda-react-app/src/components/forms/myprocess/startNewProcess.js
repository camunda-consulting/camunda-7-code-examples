import React from 'react'
import { Field, reduxForm } from 'redux-form'
import { Form, Button } from 'semantic-ui-react'
import { InputField, TextAreaField } from 'react-semantic-redux-form'
import * as Validation from '../../../constants/ValidationOptions'

const SimpleForm = props => {
  const { handleSubmit } = props
  return (
    <Form onSubmit={handleSubmit}>
      <Field name='firstName' component={InputField} label='First Name' placeholder='First Name'
        validate={[ Validation.required, Validation.maxLength15, Validation.minLength2 ]}/>
      <Field name='lastName' component={InputField} label='Last Name' placeholder='Last Name'
        validate={[ Validation.required, Validation.maxLength15, Validation.minLength2 ]} />
      <Field name='email' component={InputField} label='E-Mail' placeholder='E-Mail'
        validate={[ Validation.required, Validation.minLength2, Validation.email ]}/>
      <Field name='items' component={TextAreaField} label='Items' />

      <Form.Field control={Button} primary type='submit'>Order!</Form.Field>
    </Form>
  )
}

export default reduxForm({
  form: 'simple' // a unique identifier for this form
})(SimpleForm)
